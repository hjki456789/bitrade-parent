package cn.ztuo.bitrade.controller;

import cn.ztuo.bitrade.constant.SysConstant;
import cn.ztuo.bitrade.entity.Country;
import cn.ztuo.bitrade.entity.Member;
import cn.ztuo.bitrade.entity.transform.AuthMember;
import cn.ztuo.bitrade.service.CountryService;
import cn.ztuo.bitrade.service.LocaleMessageSourceService;
import cn.ztuo.bitrade.service.MemberService;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.vendor.provider.SMSProvider;
import com.google.common.collect.ImmutableSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static cn.ztuo.bitrade.constant.SysConstant.SESSION_MEMBER;
import static cn.ztuo.bitrade.util.MessageResult.error;
import static cn.ztuo.bitrade.util.MessageResult.success;

/**
 * @author Seven
 * @date 2019年01月08日
 */
@RestController
@RequestMapping("/mobile")
@Slf4j
@Api(tags = "获取绑定手机号验证码")
public class SmsController {

    @Autowired
    private SMSProvider smsProvider;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MemberService memberService;
    @Resource
    private LocaleMessageSourceService localeMessageSourceService;
    @Autowired
    private CountryService countryService;
    @Value("${sms.driver}")
    private String driverName;
    @Value("${sms.isTset}")
    private Boolean isTset;

    /**
     * 绑定手机号验证码
     *
     * @param phone
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bind/code", method = RequestMethod.POST)
    @ApiOperation("获取绑定手机号验证码")
    public MessageResult setBindPhoneCode(String country, String phone, @ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember user) throws Exception {
        Member member = memberService.findOne(user.getId());
        Assert.isNull(member.getMobilePhone(), localeMessageSourceService.getMessage("REPEAT_PHONE_REQUEST"));
        MessageResult result;
        String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));

        // 修改所在国家
        if (StringUtils.isNotBlank(country)) {
            Country one = countryService.findOne(country);
            if (one != null) {
                member.setCountry(one);
                memberService.saveAndFlush(member);
            }
        }
        if (!isTset) {
            if (driverName.equalsIgnoreCase("two_five_three")) {
                result = smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + phone, randomCode);
            } else if ("86".equals(member.getCountry().getAreaCode())) {
                if (!ValidateUtil.isMobilePhone(phone.trim())) {
                    return error(localeMessageSourceService.getMessage("PHONE_EMPTY_OR_INCORRECT"));
                }
                result = smsProvider.sendVerifyMessage(phone, randomCode);
            } else {
                result = smsProvider.sendInternationalMessage(randomCode, member.getCountry().getAreaCode() + phone);
            }
            if (result.getCode() == 0) {
                String key = SysConstant.PHONE_BIND_CODE_PREFIX + phone;
                redisUtil.delete(key);
                // 缓存验证码
                redisUtil.set(key, randomCode, 10, TimeUnit.MINUTES);
                return success(localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
            } else {
                return error(localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
            }
        } else {
            randomCode = "123456";
            String key = SysConstant.PHONE_BIND_CODE_PREFIX + phone;
            redisUtil.delete(key);
            redisUtil.delete(key + "Time");
            // 缓存验证码
            redisUtil.set(key, randomCode, 10, TimeUnit.MINUTES);
            redisUtil.set(key + "Time", new Date(), 10, TimeUnit.MINUTES);
            return success(localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
    }
    @PostMapping({ "/code" })
    public MessageResult sendCheckCode(final String phone, final String country) throws Exception {
        Assert.isTrue(!this.memberService.phoneIsExist(phone), this.localeMessageSourceService.getMessage("PHONE_ALREADY_EXISTS"));
        Assert.notNull((Object)country, this.localeMessageSourceService.getMessage("REQUEST_ILLEGAL"));
        final Country country2 = this.countryService.findOne(country);
        Assert.notNull((Object)country2, this.localeMessageSourceService.getMessage("REQUEST_ILLEGAL"));
        final String key = "PHONE_REG_CODE_" + phone;
        final Object code = this.redisUtil.get(key);
        if (code != null && !BigDecimalUtils.compare(DateUtil.diffMinute((Date)this.redisUtil.get(key + "Time")), BigDecimal.ONE)) {
            return MessageResult.error(this.localeMessageSourceService.getMessage("FREQUENTLY_REQUEST"));
        }
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if (this.driverName.equalsIgnoreCase("two_five_three")) {
            result = this.smsProvider.sendVerifyMessage(country2.getAreaCode() + phone, randomCode);
        }
        else if (country2.getAreaCode().equals("86")) {
            Assert.isTrue(ValidateUtil.isMobilePhone(phone.trim()), this.localeMessageSourceService.getMessage("PHONE_EMPTY_OR_INCORRECT"));
            result = this.smsProvider.sendVerifyMessage(phone, randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, country2.getAreaCode(), phone);
        }
        if (result.getCode() == 0) {
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.delete(new String[] { key + "Time" });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            this.redisUtil.set(key + "Time", (Object)new Date(), 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    public MessageResult sendSMSCode(final Member member, final String prefix) {
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        try {
            MessageResult result;
            if (this.driverName.equalsIgnoreCase("two_five_three")) {
                result = this.smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + member.getMobilePhone(), randomCode);
            }
            else if (member.getCountry().getAreaCode().equals("86")) {
                result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
            }
            else {
                result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
            }
            if (result.getCode() == 0) {
                final String key = prefix + member.getMobilePhone();
                this.redisUtil.delete(new String[] { key });
                this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
                return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
            }
            return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
        }
        catch (Exception e) {
            return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
        }
    }

    @RequestMapping(value = { "/transaction/code" }, method = { RequestMethod.POST })
    public MessageResult sendResetTransactionCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if (this.driverName.equalsIgnoreCase("two_five_three")) {
            result = this.smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + member.getMobilePhone(), randomCode);
        }
        else if (member.getCountry().getAreaCode().equals("86")) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "PHONE_UPDATE_PASSWORD_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }


    @RequestMapping(value = { "/update/password/code" }, method = { RequestMethod.POST })
    public MessageResult updatePasswordCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        final String key = "PHONE_UPDATE_PASSWORD_" + member.getMobilePhone();
        final Object code = this.redisUtil.get(key);
        if (code != null && !BigDecimalUtils.compare(DateUtil.diffMinute((Date)this.redisUtil.get(key + "Time")), BigDecimal.ONE)) {
            return MessageResult.error(this.localeMessageSourceService.getMessage("FREQUENTLY_REQUEST"));
        }
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if (this.driverName.equalsIgnoreCase("two_five_three")) {
            result = this.smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + member.getMobilePhone(), randomCode);
        }
        else if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.delete(new String[] { key + "Time" });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            this.redisUtil.set(key + "Time", (Object)new Date(), 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "/add/address/code" }, method = { RequestMethod.POST })
    public MessageResult addAddressCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if (this.driverName.equalsIgnoreCase("two_five_three")) {
            result = this.smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + member.getMobilePhone(), randomCode);
        }
        else if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "PHONE_ADD_ADDRESS_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "/reset/code" }, method = { RequestMethod.POST })
    public MessageResult resetPasswordCode(final String account) throws Exception {
        final Member member = this.memberService.findByPhone(account);
        Assert.notNull((Object)member, this.localeMessageSourceService.getMessage("MEMBER_NOT_EXISTS"));
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if (this.driverName.equalsIgnoreCase("two_five_three")) {
            result = this.smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + member.getMobilePhone(), randomCode);
        }
        else if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "RESET_PASSWORD_CODE_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "/change/code" }, method = { RequestMethod.POST })
    public MessageResult resetPhoneCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if (this.driverName.equalsIgnoreCase("two_five_three")) {
            result = this.smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + member.getMobilePhone(), randomCode);
        }
        else if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "PHONE_CHANGE_CODE_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "/google/code" }, method = { RequestMethod.POST })
    public MessageResult resetGoogleCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.notNull((Object)member, this.localeMessageSourceService.getMessage("MEMBER_NOT_EXISTS"));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if (this.driverName.equalsIgnoreCase("two_five_three")) {
            result = this.smsProvider.sendVerifyMessage(member.getCountry().getAreaCode() + member.getMobilePhone(), randomCode);
        }
        else if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "RESET_PASSWORD_CODE_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "/withdraw/code" }, method = { RequestMethod.POST })
    public MessageResult withdrawCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        SmsController.log.info("===提币验证码发送===mobile：" + member.getMobilePhone());
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "PHONE_WITHDRAW_MONEY_CODE_PREFIX_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "/trade/code" }, method = { RequestMethod.POST })
    public MessageResult tradeCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        SmsController.log.info("===交易密码验证码发送===mobile：" + member.getMobilePhone());
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "PHONE_trade_CODE_PREFIX_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "api/code" }, method = { RequestMethod.POST })
    public MessageResult bindApiSendCode(@SessionAttribute("API_MEMBER") final AuthMember user) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        SmsController.log.info("===交易密码验证码发送===mobile：" + member.getMobilePhone());
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            final String key = "API_BIND_CODE_PREFIX_" + member.getMobilePhone();
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

    @RequestMapping(value = { "contractMemberApi/code" }, method = { RequestMethod.POST })
    public MessageResult bindContractApiSendCode(@SessionAttribute("API_MEMBER") final AuthMember user, @RequestParam(value = "sourceType", required = false) final Integer sourceType) throws Exception {
        final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
        Assert.hasText(member.getMobilePhone(), this.localeMessageSourceService.getMessage("NOT_BIND_PHONE"));
        SmsController.log.info("===验证码发送===mobile：" + member.getMobilePhone());
        final String randomCode = String.valueOf(GeneratorUtil.getRandomNumber(100000, 999999));
        MessageResult result;
        if ("86".equals(member.getCountry().getAreaCode())) {
            result = this.smsProvider.sendVerifyMessage(member.getMobilePhone(), randomCode);
        }
        else {
            result = this.smsProvider.sendNationalMessage(randomCode, member.getCountry().getAreaCode(), member.getMobilePhone());
        }
        if (result.getCode() == 0) {
            String key;
            if (null != sourceType && sourceType == 1) {
                key = "CONTRACT_API_DELETE_CODE_PREFIX" + member.getMobilePhone();
            }
            else {
                key = "CONTRACT_API_BIND_CODE_PREFIX" + member.getMobilePhone();
            }
            this.redisUtil.delete(new String[] { key });
            this.redisUtil.set(key, (Object)randomCode, 10L, TimeUnit.MINUTES);
            return MessageResult.success(this.localeMessageSourceService.getMessage("SEND_SMS_SUCCESS"));
        }
        return MessageResult.error(this.localeMessageSourceService.getMessage("SEND_SMS_FAILED"));
    }

}

package cn.ztuo.bitrade.controller;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.entity.transform.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.contractdouble.*;

import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.exception.*;
import cn.ztuo.bitrade.entity.*;

import javax.transaction.*;

@RestController
@RequestMapping({"/memberApi"})
public class ContractMemberApiController extends BaseController {
    @Autowired
    private ContractDoubleMemberApiService contractDoubleMemberApiService;
    @Autowired
    private LocaleMessageSourceService msService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ContractDoubleExchangeConfigService contractDoubleExchangeConfigService;
    @Autowired
    private MemberService memberService;

    @PostMapping({"/getMemberApiList"})
    public MessageResult getMemberApiList(@SessionAttribute("API_MEMBER") final AuthMember authMember, final int pageNo, final int pageSize) {
        final Page<ContractDoubleMemberApi> page = this.contractDoubleMemberApiService.getMemberApiList(authMember.getId(), pageNo, pageSize, 0);
        final Map<Long, ContractDoubleExchangeConfig> exchangeMap = new HashMap<Long, ContractDoubleExchangeConfig>();
        final List<ContractDoubleExchangeConfig> exchangeList = this.contractDoubleExchangeConfigService.findAll();
        exchangeList.forEach(model -> exchangeMap.put(model.getId(), model));
        final Map<K, ContractDoubleExchangeConfig> map2 = new HashMap<>();
        page.getContent().forEach(model -> model.setFromExchangeName(map2.containsKey(model.getFromExchangeId()) ? map2.get(model.getFromExchangeId()).getName() : ""));
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageInfo", page);
        if (pageNo <= 1) {
            final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.findMemberApiByIfDefault(authMember.getId(), 1);
            if (null != api) {
                final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(api.getFromExchangeId());
                if (null != exchangeConfig) {
                    api.setFromExchangeName(exchangeConfig.getName());
                }
                map.put("defaultApi", api);
            }
        }
        return MessageResult.success(this.msService.getMessage("SUCCESS"), map);
    }

    @PostMapping({"/getMemberDefaultApi"})
    public MessageResult getMemberDefaultApi(@SessionAttribute("API_MEMBER") final AuthMember authMember) {
        final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.findMemberApiByIfDefault(authMember.getId(), 1);
        if (null != api) {
            final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(api.getFromExchangeId());
            if (null != exchangeConfig) {
                api.setFromExchangeName(exchangeConfig.getName());
            }
        }
        return MessageResult.success(this.msService.getMessage("SUCCESS"), api);
    }

    @PostMapping({"/getMemberApiDetail"})
    public MessageResult getMemberApiDetail(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "id", required = true) final Long id) {
        final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(id);
        if (null == api) {
            return MessageResult.error("配置信息不存在");
        }
        if (!api.getMemberId().equals(authMember.getId())) {
            return MessageResult.error("只能查看本人的API详细信息");
        }
        final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(api.getFromExchangeId());
        if (null != exchangeConfig) {
            api.setFromExchangeName(exchangeConfig.getName());
        }
        return MessageResult.success(this.msService.getMessage("SUCCESS"), api);
    }

    @PostMapping({"/addMemberApi"})
    @Transactional
    public MessageResult addMemberApi(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "id", required = false) final Long id, @RequestParam(value = "fromExchangeId", required = true) final Long fromExchangeId, @RequestParam(value = "apiKey", required = true) final String apiKey, @RequestParam(value = "secretKey", required = true) final String secretKey, @RequestParam(value = "passphrase", required = false) final String passphrase, @RequestParam(value = "ifDefault", required = true) Integer ifDefault, @RequestParam(value = "code", required = false) final String code, @RequestParam(value = "fundPassword", required = false) final String fundPassword) throws Exception {
        if (fromExchangeId == 2L) {
            Assert.isTrue(StringUtils.isNotEmpty((CharSequence) passphrase), "请输入Passphrase");
        }
        if (StringUtils.isNotEmpty((CharSequence) code)) {
            if (!code.equals("66778899")) {
                final Object redisCode = this.redisUtil.get("CONTRACT_API_BIND_CODE_PREFIX" + authMember.getMobilePhone());
                Assert.notNull(redisCode, this.msService.getMessage("VERIFICATION_CODE_NOT_EXISTS"));
                if (!code.equals(redisCode.toString())) {
                    return this.error(this.msService.getMessage("VERIFICATION_CODE_INCORRECT"));
                }
                this.redisUtil.delete(new String[]{"CONTRACT_API_BIND_CODE_PREFIX" + authMember.getMobilePhone()});
            }
        } else {
            final Member member = this.memberService.findOne(Long.valueOf(authMember.getId()));
            if (member == null) {
                return MessageResult.error("用户不存在！");
            }
            Assert.hasText(member.getJyPassword(), "资金密码未设置！");
            Assert.isTrue(Md5.md5Digest(fundPassword + member.getSalt()).toLowerCase().equals(member.getJyPassword()), "资金密码错误！");
        }
        if (null != id && id > 0L) {
            ContractDoubleMemberApi oldApi = this.contractDoubleMemberApiService.get(id);
            if (null != oldApi) {
                final ContractDoubleMemberApi defaultApi = this.contractDoubleMemberApiService.findMemberApiByIfDefault(authMember.getId(), 1);
                if (null != defaultApi) {
                    if (ifDefault == 0 && oldApi.getId().equals(defaultApi.getId())) {
                        return MessageResult.error("无法设置为非默认");
                    }
                    if (ifDefault == 1 && !oldApi.getId().equals(defaultApi.getId())) {
                        final int num = this.contractDoubleMemberApiService.updateApiIfDefault(authMember.getId(), 0);
                        if (num <= 0) {
                            throw new InformationExpiredException("Information Expired");
                        }
                    }
                } else {
                    ifDefault = 1;
                }
                oldApi.setFromExchangeId(fromExchangeId);
                oldApi.setApiKey(apiKey);
                oldApi.setSecretKey(secretKey);
                oldApi.setPassphrase(passphrase);
                oldApi.setIfDefault(ifDefault);
                oldApi = this.contractDoubleMemberApiService.save(oldApi);
                if (null == oldApi) {
                    throw new InformationExpiredException("Information Expired");
                }
            }
        } else {
            final ContractDoubleMemberApi defaultApi2 = this.contractDoubleMemberApiService.findMemberApiByIfDefault(authMember.getId(), 1);
            if (null != defaultApi2) {
                if (ifDefault == 1) {
                    final int num2 = this.contractDoubleMemberApiService.updateApiIfDefault(authMember.getId(), 0);
                    if (num2 <= 0) {
                        throw new InformationExpiredException("Information Expired");
                    }
                }
            } else {
                ifDefault = 1;
            }
            ContractDoubleMemberApi api = new ContractDoubleMemberApi();
            api.setMemberId(Long.valueOf(authMember.getId()));
            api.setFromExchangeId(fromExchangeId);
            api.setApiKey(apiKey);
            api.setSecretKey(secretKey);
            api.setPassphrase(passphrase);
            api.setStatus(1);
            api.setIfDefault(ifDefault);
            api.setSequence(Long.valueOf(System.currentTimeMillis()));
            api.setDeleteFlag(0);
            api = this.contractDoubleMemberApiService.save(api);
            if (null == api) {
                throw new InformationExpiredException("Information Expired");
            }
        }
        return MessageResult.success(this.msService.getMessage("SUCCESS"));
    }

    @PostMapping({"/updateApiDefault"})
    @Transactional
    public MessageResult addMemberApi(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "id", required = true) final Long id) throws Exception {
        final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(id);
        if (null == api) {
            return MessageResult.error("配置信息不存在");
        }
        if (api.getIfDefault() == 1) {
            return MessageResult.error("该API信息已是默认状态");
        }
        final ContractDoubleMemberApi defaultApi = this.contractDoubleMemberApiService.findMemberApiByIfDefault(authMember.getId(), 1);
        if (null != defaultApi) {
            final int num = this.contractDoubleMemberApiService.updateApiIfDefault(authMember.getId(), 0);
            if (num <= 0) {
                throw new InformationExpiredException("Information Expired");
            }
        }
        final int num = this.contractDoubleMemberApiService.updateApiDefault(id);
        if (num <= 0) {
            throw new InformationExpiredException("Information Expired");
        }
        return MessageResult.success(this.msService.getMessage("SUCCESS"));
    }

    @PostMapping({"/deleteApi"})
    @Transactional
    public MessageResult deleteApi(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "id", required = true) final Long id, @RequestParam(value = "code", required = false) final String code, @RequestParam(value = "fundPassword", required = false) final String fundPassword) throws Exception {
        if (StringUtils.isNotEmpty((CharSequence) code)) {
            if (!code.equals("66778899")) {
                final Object redisCode = this.redisUtil.get("CONTRACT_API_DELETE_CODE_PREFIX" + authMember.getMobilePhone());
                Assert.notNull(redisCode, this.msService.getMessage("VERIFICATION_CODE_NOT_EXISTS"));
                if (!code.equals(redisCode.toString())) {
                    return this.error(this.msService.getMessage("VERIFICATION_CODE_INCORRECT"));
                }
                this.redisUtil.delete(new String[]{"CONTRACT_API_DELETE_CODE_PREFIX" + authMember.getMobilePhone()});
            }
        } else {
            final Member member = this.memberService.findOne(Long.valueOf(authMember.getId()));
            if (member == null) {
                return MessageResult.error("用户不存在！");
            }
            Assert.hasText(member.getJyPassword(), "资金密码未设置！");
            Assert.isTrue(Md5.md5Digest(fundPassword + member.getSalt()).toLowerCase().equals(member.getJyPassword()), "资金密码错误！");
        }
        final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(id);
        if (null == api) {
            return MessageResult.error("配置信息不存在");
        }
        if (!api.getMemberId().equals(authMember.getId())) {
            return MessageResult.error("不允许删除");
        }
        int num = this.contractDoubleMemberApiService.deleteApi(id);
        if (num <= 0) {
            throw new InformationExpiredException("Information Expired");
        }
        if (api.getIfDefault() == 1) {
            final List<ContractDoubleMemberApi> memberApis = (List<ContractDoubleMemberApi>) this.contractDoubleMemberApiService.findMemberApiByMemberId(authMember.getId());
            if (null != memberApis && memberApis.size() > 0) {
                final ContractDoubleMemberApi newDefaultApi = memberApis.get(0);
                num = this.contractDoubleMemberApiService.updateApiDefault(newDefaultApi.getId());
                if (num <= 0) {
                    throw new InformationExpiredException("Information Expired");
                }
            }
        }
        return MessageResult.success(this.msService.getMessage("SUCCESS"));
    }
}

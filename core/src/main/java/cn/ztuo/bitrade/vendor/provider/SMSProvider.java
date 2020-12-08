package cn.ztuo.bitrade.vendor.provider;


import cn.ztuo.bitrade.util.MessageResult;

import java.util.Map;

public interface SMSProvider {
    /**
     * 发送单条短信
     *
     * @param mobile  手机号
     * @param content 短信内容
     * @return
     * @throws Exception
     */
    MessageResult sendSingleMessage(String mobile, String content) throws Exception;

    /**
     * 发送验证码短信
     *
     * @param mobile     手机号
     * @param verifyCode 验证码
     * @return
     * @throws Exception
     */
    default MessageResult sendVerifyMessage(String mobile, String verifyCode) throws Exception {
        return sendSingleMessage(mobile, formatVerifyCode(verifyCode));
    }

    /**
     * 获取验证码信息格式
     *
     * @param code
     * @return
     */
    default String formatVerifyCode(String code) {
        return String.format("您的验证码为%s，十分钟内有效，如非本人操作，请忽略", code);
    }

    /**
     * 发送国际短信验证码
     *
     * @param code
     * @param phone
     * @return
     */
    MessageResult sendInternationalMessage(String code, String phone, String... templateIds) throws Exception;


    /**
     * 发送模板短信
     *
     * @param templateId 模板ID
     * @param phone      手机号
     * @return
     */
    MessageResult sendTemplateMessage(String phone, String templateId) throws Exception;

    /**
     * 发送模板短信
     *
     * @param templateId 模板ID
     * @param phone      手机号
     * @return
     */

    default MessageResult sendTemplateMessage(Integer templateId,String phone,Boolean isDomestic,Map param ) throws Exception{
        //待完善
        return new MessageResult();
    }

    MessageResult sendNationalMessage(String content, String nationCode, String phone) throws Exception;


    default String sendLoginMessage(String ip) {
        return String.format("您已经登录，登录IP：%s", ip);
    }


    public MessageResult sendLoginMessage(String ip, String phone) throws Exception;
}

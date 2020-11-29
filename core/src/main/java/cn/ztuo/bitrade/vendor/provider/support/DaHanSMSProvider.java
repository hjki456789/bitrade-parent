package cn.ztuo.bitrade.vendor.provider.support;

import cn.ztuo.bitrade.vendor.provider.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import com.alibaba.fastjson.*;
import com.mashape.unirest.http.*;
import org.slf4j.*;

public class DaHanSMSProvider implements SMSProvider
{
    private static final Logger log;
    private String gateway;
    private String username;
    private String password;
    private String sign;
    
    public DaHanSMSProvider(final String gateway, final String username, final String password, final String sign) {
        this.gateway = gateway;
        this.username = username;
        this.password = password;
        this.sign = sign;
    }
    
    public static void main(final String[] args) {
        final String sms_gateway = "http://intlapi.1cloudsp.com/intl/api/v2/send";
        final String sms_username = "LrtsHN8nPOItwFWo";
        final String sms_password = "sw4VXcZigXeIU6JruXQId70dfA7Licmm";
        final String sms_sign = "2520";
        final DaHanSMSProvider provide = new DaHanSMSProvider(sms_gateway, sms_username, sms_password, sms_sign);
        try {
            provide.sendTemplateMessage("8618019918606", "3329");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getName() {
        return "dahan";
    }
    
    @Override
    public MessageResult sendSingleMessage(final String mobile, final String code) throws Exception {
        String msg = "";
        boolean resSuccess = true;
        final String resMsg = "";
        try {
            final Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("account", this.username);
            paramMap.put("password", MD5Util.getMD5String(this.password).toLowerCase());
            paramMap.put("msgid", "");
            paramMap.put("phones", "+86" + mobile);
            final String content = String.format("\u60a8\u7684\u9a8c\u8bc1\u7801\u662f%s\uff0c10\u5206\u949f\u5185\u6709\u6548\uff0c\u8bf7\u5c3d\u5feb\u9a8c\u8bc1\u3002", code);
            paramMap.put("content", content);
            paramMap.put("sign", "\u3010mustwin\u3011");
            paramMap.put("subcode", "");
            paramMap.put("sendtime", "");
            final String re = HttpClientUtil.httpPostWithJSON(this.gateway, JSON.toJSONString((Object)paramMap));
            for (int i = 0; i < 100; ++i) {
                System.err.println(re);
                System.err.println(paramMap);
            }
            return this.parseResult(re);
        }
        catch (Exception e) {
            resSuccess = false;
            msg = e.getMessage();
            return this.parseResult(null);
        }
    }
    
    @Override
    public MessageResult sendLoginMessage(final String ip, final String phone) throws Exception {
        final String content = this.sendLoginMessage(ip);
        return this.sendSingleMessage(content, phone);
    }
    
    private MessageResult parseResult(final String result) {
        final MessageResult mr = new MessageResult(500, "\u77ed\u4fe1\u53d1\u9001\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
        if (result == null) {
            return mr;
        }
        try {
            final JSONObject json = JSONObject.parseObject(result);
            final int code = Integer.parseInt(json.getString("result"));
            final String msg = json.getString("desc");
            mr.setCode(code);
            mr.setMessage(msg);
        }
        catch (Exception ex) {}
        return mr;
    }
    
    @Override
    public MessageResult sendTemplateMessage(final String mobile, final String templateId) throws Exception {
        DaHanSMSProvider.log.info("sms templateId={}", (Object)templateId);
        final HttpResponse<String> response = (HttpResponse<String>)Unirest.post(this.gateway).field("accesskey", (Object)this.username).field("secret", this.password).field("mobile", mobile).field("content", "").field("sign", this.sign).field("templateId", templateId).asString();
        DaHanSMSProvider.log.info(" mobile : " + mobile + "templateId : " + templateId);
        DaHanSMSProvider.log.info("result = {}", response.getBody());
        return this.parseResult((String)response.getBody());
    }
    
    @Override
    public MessageResult sendInternationalMessage(final String code, String mobile, final String... templateIds) throws Exception {
        String templateId = "3328";
        if (null != templateIds && templateIds.length > 0) {
            templateId = templateIds[0];
        }
        try {
            final Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("account", this.username);
            paramMap.put("password", MD5Util.getMD5String(this.password).toLowerCase());
            paramMap.put("msgid", "");
            mobile = "+" + mobile;
            paramMap.put("phones", mobile);
            final String content = String.format("\u60a8\u7684\u9a8c\u8bc1\u7801\u662f%s\uff0c10\u5206\u949f\u5185\u6709\u6548\uff0c\u8bf7\u5c3d\u5feb\u9a8c\u8bc1\u3002", code);
            paramMap.put("content", content);
            paramMap.put("sign", "\u3010mustwin\u3011");
            paramMap.put("subcode", "");
            paramMap.put("sendtime", "");
            final String re = HttpClientUtil.httpPostWithJSON(this.gateway, JSON.toJSONString((Object)paramMap));
            return this.parseResult(re);
        }
        catch (Exception e) {
            e.printStackTrace();
            return this.parseResult(null);
        }
    }
    
    @Override
    public MessageResult sendNationalMessage(final String content, final String nationCode, final String phone) throws Exception {
        return null;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)DaHanSMSProvider.class);
    }
}

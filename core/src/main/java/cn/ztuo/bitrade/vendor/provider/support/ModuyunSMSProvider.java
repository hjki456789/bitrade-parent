package cn.ztuo.bitrade.vendor.provider.support;

import cn.ztuo.bitrade.vendor.provider.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang.*;
import java.util.*;
import java.io.*;
import org.json.*;
import java.security.*;
import java.net.*;
import org.slf4j.*;

public class ModuyunSMSProvider implements SMSProvider
{
    private static final Logger log;
    private String gateway;
    private String accesskey;
    private String secretkey;
    private String signId;

    public ModuyunSMSProvider(final String gateway, final String accesskey, final String secretkey, final String signId) {
        this.gateway = gateway;
        this.accesskey = accesskey;
        this.secretkey = secretkey;
        this.signId = signId;
    }

    public static String getName() {
        return "moduyun";
    }

    public static void main(final String[] args) {
        try {
            final String gateway = "https://live.moduyun.com/sms/v2/sendsinglesms";
            final String accesskey = "5f1a7b86efb9a30f891ed4df";
            final String secretkey = "6f82d5320a4b4bc58d7ee2daf536fe93";
            final String signId = "5f1503e1efb9a30f89f08550";
            final ModuyunSMSProvider provider = new ModuyunSMSProvider(gateway, accesskey, secretkey, signId);
            final String mobile = "15391810660";
            final String content = "123456";
            try {
                provider.sendSingleMessage(mobile, content);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public MessageResult sendSingleMessage(final String mobile, final String content) throws Exception {
        final List<String> params = new ArrayList<String>();
        params.add(content);
        final String templateId = "5f1a9c90efb9a30f891ed5bd";
        final JSONObject json = this.sendWithParam(0, "86", mobile, this.signId, templateId, params, "");
        return this.parseResult(json);
    }

    @Override
    public MessageResult sendInternationalMessage(final String content, final String phone, final String... templateIds) throws Exception {
        return null;
    }

    @Override
    public MessageResult sendNationalMessage(final String content, String nationCode, final String phone) throws Exception {
        final List<String> params = new ArrayList<String>();
        params.add(content);
        if (StringUtils.isEmpty(nationCode)) {
            nationCode = "86";
        }
        final String templateId = "5f1a9c90efb9a30f891ed5bd";
        final JSONObject json = this.sendWithParam(0, nationCode, phone, this.signId, templateId, params, "");
        return this.parseResult(json);
    }

    @Override
    public MessageResult sendLoginMessage(final String ip, final String phone) throws Exception {
        final String content = "";
        final List<String> params = new ArrayList<String>();
        params.add(content);
        final String templateId = "5f1a9c90efb9a30f891ed5bd";
        final JSONObject json = this.sendWithParam(0, "86", phone, this.signId, templateId, params, "");
        return this.parseResult(json);
    }

    @Override
    public MessageResult sendTemplateMessage(final String mobilePhone, final String templateId) throws Exception {
        final List<String> params = new ArrayList<String>();
        final JSONObject json = this.sendWithParam(0, "86", mobilePhone, this.signId, templateId, params, "");
        return this.parseResult(json);
    }

    public JSONObject sendWithParam(final int type, final String nationcode, final String phoneNumber, final String signId, final String templateId, final List<String> params, String ext) throws Exception {
        if (null == ext) {
            ext = "";
        }
        final Random ran = new Random();
        final long random = ran.nextInt(999999) % 900000 + 100000;
        final long curTime = System.currentTimeMillis() / 1000L;
        final String url = this.gateway;
        final JSONObject data = new JSONObject();
        final JSONObject tel = new JSONObject();
        tel.put("nationcode", nationcode);
        tel.put("mobile", phoneNumber);
        data.put("signId", signId);
        data.put("templateId", templateId);
        data.put("type", type);
        if (params != null && params.size() > 0) {
            data.put("params", this.smsParamsToJSONArray(params));
        }
        data.put("sig", this.strToHash(String.format("secretkey=%s&random=%d&time=%d&mobile=%s", this.secretkey, random, curTime, phoneNumber)));
        data.put("tel", tel);
        data.put("time", curTime);
        data.put("ext", ext);
        final String wholeUrl = String.format("%s?accesskey=%s&random=%d", url, this.accesskey, random);
        final HttpURLConnection conn = this.getPostHttpConn(wholeUrl);
        final OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        ModuyunSMSProvider.log.info("req1---------" + data.toString());
        wr.write(data.toString());
        wr.flush();
        ModuyunSMSProvider.log.info("req2---------" + data.toString());
        JSONObject json = new JSONObject();
        final StringBuilder sb = new StringBuilder();
        final int httpRspCode = conn.getResponseCode();
        if (httpRspCode == 200) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            ModuyunSMSProvider.log.info("result---------" + sb.toString());
            json = new JSONObject(sb.toString());
        }
        else {
            json.put("result", httpRspCode);
            json.put("errmsg", ("http error " + httpRspCode + " " + conn.getResponseMessage()));
        }
        return json;
    }

    public JSONObject send(final int type, final String nationCode, final String phoneNumber, final String msg, String extend, String ext) throws Exception {
        if (0 != type && 1 != type) {
            throw new Exception("type " + type + " error");
        }
        if (null == extend) {
            extend = "";
        }
        if (null == ext) {
            ext = "";
        }
        final Random ran = new Random();
        final long random = ran.nextInt(999999) % 900000 + 100000;
        final long curTime = System.currentTimeMillis() / 1000L;
        final String url = this.gateway;
        final JSONObject data = new JSONObject();
        final JSONObject tel = new JSONObject();
        tel.put("nationcode", nationCode);
        tel.put("mobile", phoneNumber);
        data.put("type", type);
        data.put("msg", msg);
        data.put("sig", this.strToHash(String.format("secretkey=%s&random=%d&time=%d&mobile=%s", this.secretkey, random, curTime, phoneNumber)));
        data.put("tel", tel);
        data.put("time", curTime);
        data.put("extend", extend);
        data.put("ext", ext);
        final String wholeUrl = String.format("%s?accesskey=%s&random=%d", url, this.accesskey, random);
        final HttpURLConnection conn = this.getPostHttpConn(wholeUrl);
        final OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        wr.write(data.toString());
        wr.flush();
        ModuyunSMSProvider.log.info("req---------" + data.toString());
        JSONObject json = new JSONObject();
        final StringBuilder sb = new StringBuilder();
        final int httpRspCode = conn.getResponseCode();
        if (httpRspCode == 200) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            ModuyunSMSProvider.log.info("result---------" + sb.toString());
            json = new JSONObject(sb.toString());
        }
        else {
            json.put("result", httpRspCode);
            json.put("errmsg", ("http error " + httpRspCode + " " + conn.getResponseMessage()));
        }
        return json;
    }

    private MessageResult parseResult(final JSONObject json) {
        final MessageResult mr = new MessageResult(500, "\u7cfb\u7edf\u9519\u8bef");
        try {
            final int code = json.getInt("result");
            final String msg = json.getString("errmsg");
            mr.setCode(code);
            mr.setMessage(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return mr;
    }

    public JSONArray smsParamsToJSONArray(final List<String> params) {
        final JSONArray smsParams = new JSONArray();
        for (int i = 0; i < params.size(); ++i) {
            smsParams.put(params.get(i));
        }
        return smsParams;
    }

    public String strToHash(final String str) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        final byte[] inputByteArray = str.getBytes();
        messageDigest.update(inputByteArray);
        final byte[] resultByteArray = messageDigest.digest();
        return this.byteArrayToHex(resultByteArray);
    }

    public String byteArrayToHex(final byte[] byteArray) {
        final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (final byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xF];
            resultCharArray[index++] = hexDigits[b & 0xF];
        }
        return new String(resultCharArray);
    }

    public HttpURLConnection getPostHttpConn(final String url) throws Exception {
        final URL object = new URL(url);
        final HttpURLConnection conn = (HttpURLConnection)object.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");
        return conn;
    }

    static {
        log = LoggerFactory.getLogger((Class)ModuyunSMSProvider.class);
    }
}

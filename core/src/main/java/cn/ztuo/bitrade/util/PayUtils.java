package cn.ztuo.bitrade.util;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import com.alibaba.fastjson.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

@Slf4j
public class PayUtils
{
    public static void pay() {
        try {
            final Map<String, String> requestData = new TreeMap<String, String>();
            requestData.put("pay_memberid", "10088");
            requestData.put("pay_orderid", KeyUtils.generateUniqueKey());
            requestData.put("pay_applydate", DateUtil.getDateTime());
            requestData.put("pay_bankcode", "921");
            requestData.put("pay_notifyurl", "test.html");
            requestData.put("pay_callbackurl", "test.html");
            requestData.put("pay_amount", "0.1");
            CloseableHttpClient httpclient = HttpClients.createDefault();

            RequestBuilder rb = RequestBuilder.post().setUri(new URI("http://pay.tontongpay.com/Pay_Index.html"));
            rb.addParameter(HTTP.CONTENT_ENCODING,HTTP.UTF_8);
            rb.addParameter("http.method.retry-handler","3");
            NameValuePair[] data = { new BasicNameValuePair("pay_memberid",requestData.get("pay_memberid")), new BasicNameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new BasicNameValuePair("pay_types", "0"), new BasicNameValuePair("pay_applydate", (String)requestData.get("pay_applydate")), new BasicNameValuePair("pay_bankcode", (String)requestData.get("pay_bankcode")), new BasicNameValuePair("pay_notifyurl", (String)requestData.get("pay_notifyurl")), new BasicNameValuePair("pay_callbackurl", (String)requestData.get("pay_callbackurl")), new BasicNameValuePair("pay_amount", (String)requestData.get("pay_amount")), new BasicNameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()), new BasicNameValuePair("pay_productname", "测试商品") };
            rb.addParameters(data);
            HttpUriRequest login = rb.build();
            CloseableHttpResponse response = httpclient.execute(login);
            if (response.getStatusLine().getStatusCode() == 200) {
                log.info(response.getEntity().getContent().toString());
                log.info(requestData.toString());
            }
        }
        catch (IOException | URISyntaxException e) {
            log.error("接口请求异常",e);
            System.out.println("异常，状态");
        }
    }

    public static String pay(final double amount, final String orderId) {
        if (amount <= 0.0 || orderId == null || orderId.equals("")) {
            return null;
        }
        try {
            final Map<String, String> requestData = new TreeMap<String, String>();
            requestData.put("pay_memberid", "10088");
            requestData.put("pay_orderid", orderId);
            requestData.put("pay_applydate", DateUtil.getDateTime());
            requestData.put("pay_bankcode", "921");
            requestData.put("pay_notifyurl", "test.html");
            requestData.put("pay_callbackurl", "test.html");
            requestData.put("pay_amount", amount + "");
            CloseableHttpClient httpclient = HttpClients.createDefault();

            RequestBuilder rb = RequestBuilder.post().setUri(new URI("http://pay.tontongpay.com/Pay_Index.html"));
            rb.addParameter(HTTP.CONTENT_ENCODING,HTTP.UTF_8);
            rb.addParameter("http.method.retry-handler","3");
            BasicNameValuePair[] data = { new BasicNameValuePair("pay_memberid", (String)requestData.get("pay_memberid")), new BasicNameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new BasicNameValuePair("pay_types", "0"), new BasicNameValuePair("pay_applydate", (String)requestData.get("pay_applydate")), new BasicNameValuePair("pay_bankcode", (String)requestData.get("pay_bankcode")), new BasicNameValuePair("pay_notifyurl", (String)requestData.get("pay_notifyurl")), new BasicNameValuePair("pay_callbackurl", (String)requestData.get("pay_callbackurl")), new BasicNameValuePair("pay_amount", (String)requestData.get("pay_amount")), new BasicNameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()), new BasicNameValuePair("pay_productname", "OTC商品") };
            rb.addParameters(data);
            HttpUriRequest login = rb.build();
            CloseableHttpResponse response = httpclient.execute(login);
            if (response.getStatusLine().getStatusCode() == 200) {
                return response.getEntity().getContent().toString();
            }
        }
        catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void querryOrder() {
        try {
            final Map<String, String> requestData = new TreeMap<String, String>();
            requestData.put("pay_memberid", "10088");
            requestData.put("pay_orderid", "20200414200625048807");
            CloseableHttpClient httpclient = HttpClients.createDefault();

            RequestBuilder rb = RequestBuilder.post().setUri(new URI("http://pay.tontongpay.com/Pay_Trade_query.html"));
            rb.addParameter(HTTP.CONTENT_ENCODING,HTTP.UTF_8);
            rb.addParameter("http.method.retry-handler","3");
            BasicNameValuePair[] data = { new BasicNameValuePair("pay_memberid", (String)requestData.get("pay_memberid")), new BasicNameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new BasicNameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()) };
            rb.addParameters(data);
            HttpUriRequest login = rb.build();
            CloseableHttpResponse response = httpclient.execute(login);
            if (response.getStatusLine().getStatusCode() == 200) {
                JSONObject jsonObject = JSONObject.parseObject(response.getEntity().getContent().toString());
                String status = jsonObject.getString("trade_state");
                System.out.println(status.equalsIgnoreCase("SUCCESS"));
            }
        }
        catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.out.println("异常，状态");
        }
    }

    public static boolean querryOrderStatus(final String orderId) {
        if (orderId == null || orderId.equals("")) {
            return false;
        }
        try {
            final Map<String, String> requestData = new TreeMap<String, String>();
            requestData.put("pay_memberid", "10088");
            requestData.put("pay_orderid", orderId);
            CloseableHttpClient httpclient = HttpClients.createDefault();

            RequestBuilder rb = RequestBuilder.post().setUri(new URI("http://pay.tontongpay.com/Pay_Trade_query.html"));
            rb.addParameter(HTTP.CONTENT_ENCODING,HTTP.UTF_8);
            rb.addParameter("http.method.retry-handler","3");
            BasicNameValuePair[] data = { new BasicNameValuePair("pay_memberid", (String)requestData.get("pay_memberid")), new BasicNameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new BasicNameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()) };
            rb.addParameters(data);
            HttpUriRequest login = rb.build();
            CloseableHttpResponse response = httpclient.execute(login);
            if (response.getStatusLine().getStatusCode() == 200) {
                final JSONObject jsonObject = JSONObject.parseObject(response.getEntity().getContent().toString());
                final String state = jsonObject.getString("trade_state");
                if (state.equalsIgnoreCase("SUCCESS")) {
                    return true;
                }
            }
        }
        catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(final String[] args) {
        querryOrder();
    }
}

package cn.ztuo.bitrade.util;

import org.apache.commons.httpclient.util.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.*;
import java.io.*;
import java.util.*;
import com.alibaba.fastjson.*;

public class PayUtils
{
    public static void pay() {
        try {
            final Map<String, String> requestData = new TreeMap<String, String>();
            requestData.put("pay_memberid", "10088");
            requestData.put("pay_orderid", KeyUtils.generateUniqueKey());
            requestData.put("pay_applydate", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            requestData.put("pay_bankcode", "921");
            requestData.put("pay_notifyurl", "test.html");
            requestData.put("pay_callbackurl", "test.html");
            requestData.put("pay_amount", "0.1");
            final HttpClient httpClient = new HttpClient();
            final PostMethod postMethod = new PostMethod("http://pay.tontongpay.com/Pay_Index.html");
            postMethod.getParams().setContentCharset("UTF-8");
            postMethod.getParams().setParameter("http.method.retry-handler", (Object)new DefaultHttpMethodRetryHandler());
            final NameValuePair[] data = { new NameValuePair("pay_memberid", (String)requestData.get("pay_memberid")), new NameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new NameValuePair("pay_types", "0"), new NameValuePair("pay_applydate", (String)requestData.get("pay_applydate")), new NameValuePair("pay_bankcode", (String)requestData.get("pay_bankcode")), new NameValuePair("pay_notifyurl", (String)requestData.get("pay_notifyurl")), new NameValuePair("pay_callbackurl", (String)requestData.get("pay_callbackurl")), new NameValuePair("pay_amount", (String)requestData.get("pay_amount")), new NameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()), new NameValuePair("pay_productname", "\u6d4b\u8bd5\u5546\u54c1") };
            postMethod.setRequestBody(data);
            final int statusCode = httpClient.executeMethod((HttpMethod)postMethod);
            if (statusCode == 200) {
                System.out.println(postMethod.getResponseBodyAsString());
                System.out.println(requestData);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("\u5f02\u5e38\uff0c\u72b6\u6001");
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
            requestData.put("pay_applydate", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            requestData.put("pay_bankcode", "921");
            requestData.put("pay_notifyurl", "test.html");
            requestData.put("pay_callbackurl", "test.html");
            requestData.put("pay_amount", amount + "");
            final HttpClient httpClient = new HttpClient();
            final PostMethod postMethod = new PostMethod("http://pay.tontongpay.com/Pay_Index.html");
            postMethod.getParams().setContentCharset("UTF-8");
            postMethod.getParams().setParameter("http.method.retry-handler", (Object)new DefaultHttpMethodRetryHandler());
            final NameValuePair[] data = { new NameValuePair("pay_memberid", (String)requestData.get("pay_memberid")), new NameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new NameValuePair("pay_types", "0"), new NameValuePair("pay_applydate", (String)requestData.get("pay_applydate")), new NameValuePair("pay_bankcode", (String)requestData.get("pay_bankcode")), new NameValuePair("pay_notifyurl", (String)requestData.get("pay_notifyurl")), new NameValuePair("pay_callbackurl", (String)requestData.get("pay_callbackurl")), new NameValuePair("pay_amount", (String)requestData.get("pay_amount")), new NameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()), new NameValuePair("pay_productname", "OTC\u5546\u54c1") };
            postMethod.setRequestBody(data);
            final int statusCode = httpClient.executeMethod((HttpMethod)postMethod);
            if (statusCode == 200) {
                return postMethod.getResponseBodyAsString();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void querryOrder() {
        try {
            final Map<String, String> requestData = new TreeMap<String, String>();
            requestData.put("pay_memberid", "10088");
            requestData.put("pay_orderid", "20200414200625048807");
            final HttpClient httpClient = new HttpClient();
            final PostMethod postMethod = new PostMethod("http://pay.tontongpay.com/Pay_Trade_query.html");
            postMethod.getParams().setContentCharset("UTF-8");
            postMethod.getParams().setParameter("http.method.retry-handler", (Object)new DefaultHttpMethodRetryHandler());
            final NameValuePair[] data = { new NameValuePair("pay_memberid", (String)requestData.get("pay_memberid")), new NameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new NameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()) };
            postMethod.setRequestBody(data);
            final int statusCode = httpClient.executeMethod((HttpMethod)postMethod);
            if (statusCode == 200) {
                final JSONObject jsonObject = JSONObject.parseObject(postMethod.getResponseBodyAsString());
                final String status = jsonObject.getString("trade_state");
                System.out.println(status.equalsIgnoreCase("SUCCESS"));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("\u5f02\u5e38\uff0c\u72b6\u6001");
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
            final HttpClient httpClient = new HttpClient();
            final PostMethod postMethod = new PostMethod("http://pay.tontongpay.com/Pay_Trade_query.html");
            postMethod.getParams().setContentCharset("UTF-8");
            postMethod.getParams().setParameter("http.method.retry-handler", (Object)new DefaultHttpMethodRetryHandler());
            final NameValuePair[] data = { new NameValuePair("pay_memberid", (String)requestData.get("pay_memberid")), new NameValuePair("pay_orderid", (String)requestData.get("pay_orderid")), new NameValuePair("pay_md5sign", EncryptionUtils.getMd5Lower(KeyUtils.paramsConvertUrl(requestData) + "&key=7aijv8xkusqpuxmfpb4hi4xsqjodrmmv").toUpperCase()) };
            postMethod.setRequestBody(data);
            final int statusCode = httpClient.executeMethod((HttpMethod)postMethod);
            if (statusCode == 200) {
                final JSONObject jsonObject = JSONObject.parseObject(postMethod.getResponseBodyAsString());
                final String state = jsonObject.getString("trade_state");
                if (state.equalsIgnoreCase("SUCCESS")) {
                    return true;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(final String[] args) {
        querryOrder();
    }
}

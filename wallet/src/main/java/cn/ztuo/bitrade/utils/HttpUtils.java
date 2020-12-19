package cn.ztuo.bitrade.utils;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.apache.http.conn.*;
import java.net.*;
import java.io.*;
import org.apache.http.client.*;
import java.nio.charset.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import java.security.*;
import org.apache.http.conn.scheme.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.apache.http.client.entity.*;
import javax.net.ssl.*;
import org.apache.http.conn.ssl.*;
import java.util.*;

public class HttpUtils
{
    private HttpUtils() {
    }

    public static String sendGetRequest(final String reqURL) {
        String respContent = "\u901a\u4fe1\u5931\u8d25";
        final HttpClient httpClient = (HttpClient)new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", (Object)10000);
        httpClient.getParams().setParameter("http.socket.timeout", (Object)20000);
        final HttpGet httpGet = new HttpGet(reqURL);
        try {
            final HttpResponse response = httpClient.execute((HttpUriRequest)httpGet);
            final HttpEntity entity = response.getEntity();
            if (null != entity) {
                final Charset respCharset = ContentType.getOrDefault(entity).getCharset();
                respContent = EntityUtils.toString(entity, respCharset);
                EntityUtils.consume(entity);
            }
            final StringBuilder respHeaderDatas = new StringBuilder();
            for (final Header header : response.getAllHeaders()) {
                respHeaderDatas.append(header.toString()).append("\r\n");
            }
            final String respStatusLine = response.getStatusLine().toString();
            final String respHeaderMsg = respHeaderDatas.toString().trim();
        }
        catch (ConnectTimeoutException cte) {
            respContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8fde\u63a5\u8d85\u65f6";
        }
        catch (SocketTimeoutException ste) {
            respContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8bfb\u53d6\u8d85\u65f6";
        }
        catch (ClientProtocolException cpe) {
            respContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u534f\u8bae\u5f02\u5e38";
        }
        catch (ParseException pe) {
            respContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u89e3\u6790\u5f02\u5e38";
        }
        catch (IOException ioe) {
            respContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u7f51\u7edc\u5f02\u5e38";
        }
        catch (Exception e) {
            respContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u5076\u9047\u5f02\u5e38";
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }
        return respContent;
    }

    public static String sendPostRequest(final String reqURL, final String reqData, final String encodeCharset) {
        String reseContent = "\u901a\u4fe1\u5931\u8d25";
        final HttpClient httpClient = (HttpClient)new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", (Object)10000);
        httpClient.getParams().setParameter("http.socket.timeout", (Object)20000);
        final HttpPost httpPost = new HttpPost(reqURL);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + encodeCharset);
        try {
            httpPost.setEntity((HttpEntity)new StringEntity((reqData == null) ? "" : reqData, encodeCharset));
            final HttpResponse response = httpClient.execute((HttpUriRequest)httpPost);
            final HttpEntity entity = response.getEntity();
            if (null != entity) {
                reseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
                EntityUtils.consume(entity);
            }
        }
        catch (ConnectTimeoutException cte) {
            reseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8fde\u63a5\u8d85\u65f6";
        }
        catch (SocketTimeoutException ste) {
            reseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8bfb\u53d6\u8d85\u65f6";
        }
        catch (Exception e) {
            reseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u5076\u9047\u5f02\u5e38";
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }
        return reseContent;
    }

    public static String sendPostRequestBody(final String reqURL, final String reqData, final String encodeCharset) {
        String reseContent = "\u901a\u4fe1\u5931\u8d25";
        final HttpClient httpClient = (HttpClient)new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", (Object)10000);
        httpClient.getParams().setParameter("http.socket.timeout", (Object)20000);
        final HttpPost httpPost = new HttpPost(reqURL);
        httpPost.setHeader("Content-Type", "application/json; charset=" + encodeCharset);
        try {
            httpPost.setEntity((HttpEntity)new StringEntity((reqData == null) ? "" : reqData, encodeCharset));
            final HttpResponse response = httpClient.execute((HttpUriRequest)httpPost);
            final HttpEntity entity = response.getEntity();
            if (null != entity) {
                reseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
                EntityUtils.consume(entity);
            }
        }
        catch (ConnectTimeoutException cte) {
            reseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8fde\u63a5\u8d85\u65f6";
        }
        catch (SocketTimeoutException ste) {
            reseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8bfb\u53d6\u8d85\u65f6";
        }
        catch (Exception e) {
            reseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u5076\u9047\u5f02\u5e38";
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }
        return reseContent;
    }

    public static String sendPostSSLRequest(final String reqURL, final Map<String, String> params, final String encodeCharset) {
        String responseContent = "\u901a\u4fe1\u5931\u8d25";
        final HttpClient httpClient = (HttpClient)new DefaultHttpClient();
        httpClient.getParams().setParameter("http.connection.timeout", (Object)10000);
        httpClient.getParams().setParameter("http.socket.timeout", (Object)20000);
        final X509TrustManager trustManager = (X509TrustManager)new HttpUtils();
        final X509HostnameVerifier hostnameVerifier = (X509HostnameVerifier)new HttpUtils();
        try {
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            final SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext, hostnameVerifier);
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, (SchemeSocketFactory)socketFactory));
            final HttpPost httpPost = new HttpPost(reqURL);
            if (null != params) {
                final List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for (final Map.Entry<String, String> entry : params.entrySet()) {
                    formParams.add((NameValuePair)new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
                }
                httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity((List)formParams, encodeCharset));
            }
            final HttpResponse response = httpClient.execute((HttpUriRequest)httpPost);
            final HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
                EntityUtils.consume(entity);
            }
        }
        catch (ConnectTimeoutException cte) {
            responseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8fde\u63a5\u8d85\u65f6";
        }
        catch (SocketTimeoutException ste) {
            responseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u8bfb\u53d6\u8d85\u65f6";
        }
        catch (Exception e) {
            responseContent = "\u8bf7\u6c42\u901a\u4fe1[" + reqURL + "]\u65f6\u5076\u9047\u5f02\u5e38";
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
}

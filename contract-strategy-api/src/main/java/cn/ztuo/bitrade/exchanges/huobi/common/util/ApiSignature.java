package cn.ztuo.bitrade.exchanges.huobi.common.util;

import java.time.format.*;

import org.slf4j.*;

import javax.crypto.*;
import java.nio.charset.*;
import javax.crypto.spec.*;
import java.security.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.time.*;

public class ApiSignature {
    final Logger log;
    static final DateTimeFormatter DT_FORMAT;
    static final ZoneId ZONE_GMT;

    public ApiSignature() {
        this.log = LoggerFactory.getLogger((Class) this.getClass());
    }

    public void createSignature(final String appKey, final String appSecretKey, final String method, final String uri, final Map<String, String> params) {
        final StringBuilder sb = new StringBuilder(1024);
        final int index = uri.indexOf("//");
        final String subString = uri.substring(index + 2);
        final int index2 = subString.indexOf("/");
        final String host = subString.substring(0, index2);
        final String constant = subString.substring(index2);
        sb.append(method.toUpperCase()).append('\n').append(host.toLowerCase()).append('\n').append(constant).append('\n');
        params.remove("Signature");
        params.put("AccessKeyId", appKey);
        params.put("SignatureVersion", "2");
        params.put("SignatureMethod", "HmacSHA256");
        params.put("Timestamp", this.gmtNow());
        final SortedMap<String, String> map = new TreeMap<String, String>(params);
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            sb.append(key).append('=').append(urlEncode(value)).append('&');
        }
        sb.deleteCharAt(sb.length() - 1);
        Mac hmacSha256 = null;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            final SecretKeySpec secKey = new SecretKeySpec(appSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e2) {
            throw new RuntimeException("Invalid key: " + e2.getMessage());
        }
        final String payload = sb.toString();
        final byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        final String actualSign = Base64.getEncoder().encodeToString(hash);
        params.put("Signature", actualSign);
        if (this.log.isDebugEnabled()) {
            this.log.debug("Dump parameters:");
            for (final Map.Entry<String, String> entry2 : params.entrySet()) {
                this.log.debug("  key: " + entry2.getKey() + ", value: " + entry2.getValue());
            }
        }
    }

    public static String urlEncode(final String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }

    long epochNow() {
        return Instant.now().getEpochSecond();
    }

    String gmtNow() {
        return Instant.ofEpochSecond(this.epochNow()).atZone(ApiSignature.ZONE_GMT).format(ApiSignature.DT_FORMAT);
    }

    static {
        DT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
        ZONE_GMT = ZoneId.of("Z");
    }
}

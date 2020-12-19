package cn.ztuo.bitrade.exchanges.okex.utils;

import javax.crypto.*;

import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.exchanges.okex.enums.*;

import javax.crypto.spec.*;
import java.util.*;
import java.io.*;
import javax.management.*;
import java.security.*;

public class HmacSHA256Base64Utils {
    public static Mac MAC;

    public static String sign(final String timestamp, final String method, final String requestPath, final String queryString, final String body, final String secretKey) throws CloneNotSupportedException, InvalidKeyException, UnsupportedEncodingException {
        if (StringUtils.isEmpty((CharSequence) secretKey) || StringUtils.isEmpty((CharSequence) method)) {
            return "";
        }
        final String preHash = preHash(timestamp, method, requestPath, queryString, body);
        final byte[] secretKeyBytes = secretKey.getBytes(CharsetEnum.UTF_8.charset());
        final SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, AlgorithmEnum.HMAC_SHA256.algorithm());
        final Mac mac = (Mac) HmacSHA256Base64Utils.MAC.clone();
        mac.init(secretKeySpec);
        return Base64.getEncoder().encodeToString(mac.doFinal(preHash.getBytes(CharsetEnum.UTF_8.charset())));
    }

    public static String preHash(final String timestamp, final String method, final String requestPath, final String queryString, final String body) {
        final StringBuilder preHash = new StringBuilder();
        preHash.append(timestamp);
        preHash.append(method.toUpperCase());
        preHash.append(requestPath);
        if (StringUtils.isNotEmpty((CharSequence) queryString)) {
            preHash.append("?").append(queryString);
        }
        if (StringUtils.isNotEmpty((CharSequence) body)) {
            preHash.append(body);
        }
        return preHash.toString();
    }

    static {
        try {
            HmacSHA256Base64Utils.MAC = Mac.getInstance(AlgorithmEnum.HMAC_SHA256.algorithm());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeErrorException(new Error("Can't get Mac's instance."));
        }
    }
}

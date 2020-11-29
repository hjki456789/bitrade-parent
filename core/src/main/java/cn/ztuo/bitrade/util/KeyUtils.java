package cn.ztuo.bitrade.util;

import java.text.*;
import java.util.*;

public class KeyUtils
{
    public static synchronized String generateUniqueKey() {
        final Random random = new Random();
        final int r = random.nextInt(900) + 100;
        final Long timeMillis = System.currentTimeMillis();
        final DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        final String timeStr = sdf.format(new Date());
        return timeStr + r;
    }
    
    public static String paramsConvertUrl(final Map<String, String> params) {
        final StringBuilder urlParams = new StringBuilder("");
        final Set<Map.Entry<String, String>> entries = params.entrySet();
        for (final Map.Entry<String, String> entry : params.entrySet()) {
            urlParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        final String urlParamsStr = urlParams.toString();
        return urlParamsStr.substring(0, urlParamsStr.length() - 1);
    }
}

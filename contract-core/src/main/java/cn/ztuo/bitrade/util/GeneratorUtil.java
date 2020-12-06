package cn.ztuo.bitrade.util;

import java.util.*;

public class GeneratorUtil {
    public static int getRandomNumber(final int from, final int to) {
        final float a = from + (to - from) * new Random().nextFloat();
        final int b = (int) a;
        return ((a - b > 0.5) ? 1 : 0) + b;
    }

    public static String getNonceString(final int len) {
        final String seed = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < len; ++i) {
            tmp.append(seed.charAt(getRandomNumber(0, 61)));
        }
        return tmp.toString();
    }

    public static String getUUID() {
        final UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getOrderId(final String prefix) {
        final String body = String.valueOf(new Date().getTime());
        return prefix + body + getRandomNumber(10, 99);
    }
}

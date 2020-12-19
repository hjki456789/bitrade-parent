package cn.ztuo.bitrade.exchanges.okex.utils;

import java.util.*;

public class OrderIdUtils {
    public static String generator() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }

    public static void main(final String[] args) {
        System.out.println(generator());
    }
}

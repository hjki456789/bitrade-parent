package cn.ztuo.bitrade.utils;

import java.util.*;

public class RandomUtil
{
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";
    
    public static String generateOnlyNumber(final int length) {
        final StringBuffer sb = new StringBuffer();
        final Random random = new Random();
        for (int i = 0; i < length; ++i) {
            sb.append("0123456789".charAt(random.nextInt("0123456789".length())));
        }
        return sb.toString();
    }
    
    public static String generateString(final int length) {
        final StringBuffer sb = new StringBuffer();
        final Random random = new Random();
        for (int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }
        return sb.toString();
    }
    
    public static String generateMixString(final int length) {
        final StringBuffer sb = new StringBuffer();
        final Random random = new Random();
        for (int i = 0; i < length; ++i) {
            sb.append("abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }
        return sb.toString();
    }
    
    public static String generateLowerString(final int length) {
        return generateMixString(length).toLowerCase();
    }
    
    public static String generateUpperString(final int length) {
        return generateMixString(length).toUpperCase();
    }
    
    public static String generateZeroString(final int length) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            sb.append('0');
        }
        return sb.toString();
    }
    
    public static String toFixdLengthString(final long num, final int fixdlenth) {
        final StringBuffer sb = new StringBuffer();
        final String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
            sb.append(strNum);
            return sb.toString();
        }
        throw new RuntimeException("\u5c06\u6570\u5b57" + num + "\u8f6c\u5316\u4e3a\u957f\u5ea6\u4e3a" + fixdlenth + "\u7684\u5b57\u7b26\u4e32\u53d1\u751f\u5f02\u5e38\uff01");
    }
    
    public static int getNotSimple(final int[] param, final int len) {
        final Random rand = new Random();
        for (int i = param.length; i > 1; --i) {
            final int index = rand.nextInt(i);
            final int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int j = 0; j < len; ++j) {
            result = result * 10 + param[j];
        }
        return result;
    }
    
    public static void main(final String[] args) {
        System.out.println("\u8fd4\u56de\u4e00\u4e2a\u5b9a\u957f\u7684\u968f\u673a\u5b57\u7b26\u4e32(\u53ea\u5305\u542b\u5927\u5c0f\u5199\u5b57\u6bcd\u3001\u6570\u5b57):" + generateString(10));
        System.out.println("\u8fd4\u56de\u4e00\u4e2a\u5b9a\u957f\u7684\u968f\u673a\u7eaf\u5b57\u6bcd\u5b57\u7b26\u4e32(\u53ea\u5305\u542b\u5927\u5c0f\u5199\u5b57\u6bcd):" + generateMixString(10));
        System.out.println("\u8fd4\u56de\u4e00\u4e2a\u5b9a\u957f\u7684\u968f\u673a\u7eaf\u5c0f\u5199\u5b57\u6bcd\u5b57\u7b26\u4e32(\u53ea\u5305\u542b\u5927\u5c0f\u5199\u5b57\u6bcd):" + generateLowerString(10));
        System.out.println("\u8fd4\u56de\u4e00\u4e2a\u5b9a\u957f\u7684\u968f\u673a\u7eaf\u5927\u5199\u5b57\u6bcd\u5b57\u7b26\u4e32(\u53ea\u5305\u542b\u5927\u5c0f\u5199\u5b57\u6bcd):" + generateUpperString(10));
        System.out.println("\u751f\u6210\u4e00\u4e2a\u5b9a\u957f\u7684\u7eaf0\u5b57\u7b26\u4e32:" + generateZeroString(10));
        System.out.println("\u6839\u636e\u6570\u5b57\u751f\u6210\u4e00\u4e2a\u5b9a\u957f\u7684\u5b57\u7b26\u4e32\uff0c\u957f\u5ea6\u4e0d\u591f\u524d\u9762\u88650:" + toFixdLengthString(123L, 10));
        final int[] in = { 1, 2, 3, 4, 5, 6, 7 };
        System.out.println("\u6bcf\u6b21\u751f\u6210\u7684len\u4f4d\u6570\u90fd\u4e0d\u76f8\u540c:" + getNotSimple(in, 3));
        int i = 0;
        final Set<String> codes = new HashSet<String>();
        while (codes.size() < 10) {
            ++i;
            codes.add(generateOnlyNumber(10));
        }
        System.out.println("\u8fd4\u56de\u4e00\u4e2a\u5b9a\u957f\u7684\u968f\u673a\u5b57\u7b26\u4e32(\u6570\u5b57):" + codes.toString());
        System.out.println("\u8fd4\u56de\u4e00\u4e2a\u5b9a\u957f\u7684\u968f\u673a\u5b57\u7b26\u4e32(\u6570\u5b57) \u4ea7\u751f\u6b21\u6570:" + i);
    }
}

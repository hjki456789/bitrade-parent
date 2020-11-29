package cn.ztuo.bitrade.util;

import java.util.*;
import java.security.*;
import org.apache.commons.codec.binary.*;

public class MD5Util
{
    public static String saltMD5(String password) {
        final Random r = new Random();
        final StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        final int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; ++i) {
                sb.append("0");
            }
        }
        final String salt = sb.toString();
        password = md5Hex(password + salt);
        final char[] cs = new char[48];
        for (int j = 0; j < 48; j += 3) {
            cs[j] = password.charAt(j / 3 * 2);
            final char c = salt.charAt(j / 3);
            cs[j + 1] = c;
            cs[j + 2] = password.charAt(j / 3 * 2 + 1);
        }
        return new String(cs);
    }
    
    public static boolean verify(final String password, final String md5) {
        try {
            final char[] cs1 = new char[32];
            final char[] cs2 = new char[16];
            for (int i = 0; i < 48; i += 3) {
                cs1[i / 3 * 2] = md5.charAt(i);
                cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
                cs2[i / 3] = md5.charAt(i + 1);
            }
            final String salt = new String(cs2);
            return md5Hex(password + salt).equals(new String(cs1));
        }
        catch (Exception e) {
            return false;
        }
    }
    
    private static String md5Hex(final String src) {
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static String getMD5String(final String content) {
        String result = "";
        try {
            final String md = md5Hex(content);
            result = md.toLowerCase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(final String[] args) {
        System.out.println(getMD5String("123456"));
    }
}

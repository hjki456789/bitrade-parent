package cn.ztuo.bitrade.util;

import java.security.*;

public class EncryptionUtils
{
    private static final char[] HEX_DIGITS;

    public static String getMd5String(final String str) {
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            byte[] bytes = str.getBytes();
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            final StringBuilder sb = new StringBuilder();
            for (final byte aByte : bytes) {
                sb.append(EncryptionUtils.HEX_DIGITS[(aByte & 0xF0) >> 4]).append(EncryptionUtils.HEX_DIGITS[aByte & 0xF]);
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getMd5Lower(final String str) {
        try {
            final MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(str.getBytes());
            final StringBuilder buf = new StringBuilder();
            final byte[] digest;
            final byte[] b = digest = bmd5.digest();
            for (final int i : digest) {
                final byte value = (byte)i;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    static {
        HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}

package cn.ztuo.bitrade.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.spec.*;
import javax.crypto.*;
import java.util.*;
import java.security.*;

public class RSAUtils
{
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    public static byte[] decryptBASE64(final String key) {
        return Base64.decodeBase64(key);
    }

    public static String encryptBASE64(final byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static String sign(final byte[] data, final String privateKey) throws Exception {
        final byte[] keyBytes = decryptBASE64(privateKey);
        final PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        final Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    public static boolean verify(final byte[] data, final String publicKey, final String sign) throws Exception {
        final byte[] keyBytes = decryptBASE64(publicKey);
        final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final PublicKey pubKey = keyFactory.generatePublic(keySpec);
        final Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(final byte[] data, final String key) throws Exception {
        final byte[] keyBytes = decryptBASE64(key);
        final PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, privateKey);
        return cipher.doFinal(data);
    }

    public static String decryptByPrivateKey(final String data, final String key) throws Exception {
        return new String(decryptByPrivateKey(decryptBASE64(data), key));
    }

    public static String decryptByPublicKey(final String data, final String key) throws Exception {
        final byte[] keyBytes = decryptBASE64(key);
        final X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final Key publicKey = keyFactory.generatePublic(x509KeySpec);
        final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, publicKey);
        return new String(cipher.doFinal(decryptBASE64(data)));
    }

    public static String encryptByPublicKey(final String data, final String key) throws Exception {
        final byte[] keyBytes = decryptBASE64(key);
        final X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final Key publicKey = keyFactory.generatePublic(x509KeySpec);
        final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, publicKey);
        return encryptBASE64(cipher.doFinal(data.getBytes()));
    }

    public static String encryptByPrivateKey(final String data, final String key) throws Exception {
        final byte[] keyBytes = decryptBASE64(key);
        final PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        final Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        final Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, privateKey);
        return encryptBASE64(cipher.doFinal(data.getBytes()));
    }

    public static String getPrivateKey(final Map<String, Key> keyMap) throws Exception {
        final Key key = keyMap.get("RSAPrivateKey");
        return encryptBASE64(key.getEncoded());
    }

    public static String getPublicKey(final Map<String, Key> keyMap) throws Exception {
        final Key key = keyMap.get("RSAPublicKey");
        return encryptBASE64(key.getEncoded());
    }

    public static Map<String, Key> initKey() throws Exception {
        final KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        final KeyPair keyPair = keyPairGen.generateKeyPair();
        final Map<String, Key> keyMap = new HashMap<String, Key>(2);
        keyMap.put("RSAPublicKey", keyPair.getPublic());
        keyMap.put("RSAPrivateKey", keyPair.getPrivate());
        return keyMap;
    }

    public static void main(final String[] args) throws Exception {
    }
}

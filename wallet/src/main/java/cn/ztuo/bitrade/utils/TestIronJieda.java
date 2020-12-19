package cn.ztuo.bitrade.utils;

import com.alibaba.fastjson.*;
import java.util.*;

public class TestIronJieda
{
    public static void main(final String[] args) {
        String privateKey = "ea24d157ba307ba6a8f89644220af71402e2ae0f24451ea9dc19bd42952eead0";
        final String privateKeyRsa = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCK/LKw1YmiTWeMTgDlKd0bII0hvVc8rs3DHuC20uS6YJUmzh2i7Ct5qWvJeBUuFyTGeTkHfduHqR8YVrpqN4yVwZQ9Qgzuc0V1kQM82Egf+ejGc/ShCtywbs+H/wOxJTuOkugHnMrT5FK6JVFsIKYJI6mTIS9uLeiSlfxHQDA23QIDAQAB";
        try {
            privateKey = RSAUtils.encryptByPublicKey(privateKey, privateKeyRsa);
            System.out.println(privateKey);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean sendEth(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final Map<String, Object> ethResult = EthTokenUtils.sendEth("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, fromPrivateKey, withdrawNumStr);
            if ((Integer) ethResult.get("status") == 0) {
                System.out.println(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr);
                return true;
            }
            if ((Integer) ethResult.get("status") == 2) {
                System.out.println(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "eth");
            }
            else {
                System.out.println(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)ethResult));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean sendUsdt(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0xdac17f958d2ee523a2206206994597c13d831ec7", fromPrivateKey, withdrawNumStr);
            if ((Integer) result.get("status") == 0) {
                System.out.println(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1ausdt");
                return true;
            }
            if ((Integer) result.get("status") == 2) {
                System.out.println(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "usdt");
            }
            else {
                System.out.println(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)result));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

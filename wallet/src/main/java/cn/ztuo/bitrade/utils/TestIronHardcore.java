package cn.ztuo.bitrade.utils;

import com.alibaba.fastjson.*;
import java.util.*;

public class TestIronHardcore
{
    public static void main(final String[] args) {
        String privateKey = "JJFimv5WOXz7ep61BayJhAYvby9zWHX8u76DvuhwV9yEtvXjS8lzsEggN9z1y2w4NjirjsV9f2zhEqfz0kBpgYOVE9ZBiOAj10tVC9HecqwsNEvofri+bwUB+SEg6jYTDyI5Qn/w17HnaFcVrCJFPy3Nc1tzTTYLem/uT+YaF/4=";
        final String privateKeyRsa = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKGW4n9YIOKL768LxecgBiB+Ml+MNRQiwhIKdf/dQmy0VQznaPPgUl0itfvORMNvbeqEhwBUWwcJIgRtAvOtDYtqcFLuckwZNUWZzOAFN8w0fmiO4l/fbV93z3bsCnfaEIjBCXXR2B0uOd+fPsfv5oBYgrbylWcGRSvaUMl4vVeTAgMBAAECgYAcr//oIco8ziEwT4PEA7ByxlTFF8w4le8kas0GLs7jI6TR1KeSXGni9Q2f9b/JfpfiAcNB8Lf0aM+sP3o9rqRvqojd7jh35oK31JMhVZHWAKq9uZx2o6delLvbbXyspAGtpApSDLDe01b1bVtpz73LSDiKBYOtFcgjgg/ZfcvzEQJBANjp356ujs/kBU2UxEaopAU9/ZBPMyOc1KyeDMzBJ6DRhE9EjLqx1NVDJ5vxFlvYSpk1s4LDQR3QbaDjaCtFYOsCQQC+tO6AiRmQz+mZ457Xo25GEu4T+rD9EvZ42YheQWlhLH+7JgzHQotoINY8zLubrVUDOgq37hQChi9KiZNBmXn5AkEAh1+mfP0KCfqPyLRQrpxvCQqAJkEXdGFbZYOaD2t41OgNf/8TKSz03Ef1/1++CYH6RJX8O16qU/YRhTiHeYHqEwJBAJ+wph5fr1bBotzH65SqN4baBx4Xx+jC3jyn+zbiEznZp0OxQvX/1Phmtr7GuTpM1GkVSzBlXcAXWLZTqUk/GRECQFyysJr+cbcOJ15/lmeit15A0apKCltJN/cq4RKLi0IzM+pS1LuTKQkKFD8ZqHdj19IC8FiN9x7AgGDj+yqqgbk=";
        try {
            privateKey = RSAUtils.decryptByPrivateKey(privateKey, privateKeyRsa);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        sendEth("0xe9f8f9053474502d4734e2cead87c0acc491eb97", "0x0a0be4426daaf7605e12fca086f6287357ec8371", "e2674ba2a796875a3592303de607e8f788e2f7106f020b2498e4c8dbeab07dd6", "0.01");
        final double amount = 24248.0;
        final double amount2 = amount * 0.4;
        final double amount3 = amount * 0.6;
        System.out.println(privateKey);
        sendGTD("0x0a0be4426daaf7605e12fca086f6287357ec8371", "0x1d00Ad84624bFa2DDaA7e8378A64495138aF3a07", privateKey, "" + amount);
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

    public static boolean sendAMC(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) * 1.0E12;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0xe2ded2365bd426b2078bc947d70cc5c0141a4e6f", fromPrivateKey, amount + "");
            if ((Integer) result.get("status") == 0) {
                System.out.println(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "AMC");
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

    public static boolean sendKIF(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) / 10000.0;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0x4e880d553904fb3be6c3e767b67e23111a5abe28", fromPrivateKey, amount + "");
            if ((Integer) result.get("status") == 0) {
                System.out.println(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "KIF");
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

    public static boolean sendTKJ(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) * 1.0E12;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0x1bb7c7f703ce521fa68524286d6a6d177741cd92", fromPrivateKey, amount + "");
            if ((Integer) result.get("status") == 0) {
                System.out.println(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "KIF");
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

    public static boolean sendGTD(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) * 1.0E12;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0x46f4129bfca15f8807c8ef067afc2377562e9074", fromPrivateKey, amount + "");
            if ((Integer) result.get("status") == 0) {
                System.out.println(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "GTD");
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

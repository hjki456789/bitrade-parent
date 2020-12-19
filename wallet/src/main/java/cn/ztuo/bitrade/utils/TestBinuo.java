package cn.ztuo.bitrade.utils;

import cn.ztuo.bitrade.util.*;
import java.util.*;

public class TestBinuo
{
    public static void main(final String[] args) throws Exception {
        final String rsakey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK10OKpNbUScZFH5RC/iV1Xugg1kjmTH2dwRKq/kLapviyh7hKKzxiY4zzwrTakPtibiBAPxwJx0hvTSY5Mn7oZarujeR7RpjzRJd1vu5kR5nYuK2IsQafDNBHuO6eRKXgKUBrH4aZ4Q2q1/0q3ye3T3uvnudpd+3m3gnXuwc5V7AgMBAAECgYEAlES1r3XyAmVBKAVQcTIkf7MafoJHagSfyQrvl10QYBFrPdaf4UiVB9Ul6IQHpY1lokUvM4PyporBVgOF1KOcstXIXbQKRCU/V5NqX2y/xDX0JZW/9IwZRHTeNf6njIfgNzCdXdGkBaR4nYmF+XxbpBYxASsxPA9M0UEAigUov0kCQQD1w2rYPetYYBpEN0IuLkOSTVIpllRSWAq3U6bVGS2VnWWx+FfUv08d+uYzC73RtrhFVwuHsoDWYeHZW2c2U06XAkEAtK3EVNRSz0gq1pIsZGHZ0xvpgssW0qAWZSewm4B7fDU49mEuR3cTdmYDi+DBwMWcyYtwejsqPKfPfeu9XIPwvQJAJCVsxoc84Q+pbVUPR8mIKLF0nyc8GaxXTyVfiUIdwT6YIXfcOy80DL3FM8bLUVflfFVUHc40QtO8qwJViNjx2wJBALRmfA2nIjfM+tjbgm+INBHRYMozNUqAhVvi0TeyhZlzGrkGN0sr3cbt/MSBWXO829tw2pFw2a9ObqJv6tag9pECQQCp7cL7pGtyFGn18wW7UVJPjZFqCmFeSm0dm6uOlyX0Sb8vTiV25wUUpjF3Esmjj9dvMqFxm9n0TO7KKaRuBOF9";
        final List<String> addresses = (List<String>)Common.readFileByLine("/Users/lyrics-mac/IDEA-CODE/zCoin_project/ztuo_framework/wallet/conf/iron/address.txt");
        final List<String> keys = (List<String>)Common.readFileByLine("/Users/lyrics-mac/IDEA-CODE/zCoin_project/ztuo_framework/wallet/conf/iron/key.txt");
        for (int i = 7202; i < addresses.size(); ++i) {
            final String address = addresses.get(i);
            final String key = keys.get(i);
            final double ethBalance = EthTokenUtils.getEthBalance("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", address);
            final String privateKey = RSAUtils.decryptByPrivateKey(key, rsakey);
            final double usdtBalance = UsdtErc20TokenUtils.getBalance("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", "0xdac17f958d2ee523a2206206994597c13d831ec7", address, privateKey);
            if (ethBalance > 0.0 || usdtBalance > 0.0) {
                System.out.println(address + ":" + privateKey + ":" + ethBalance + ":" + usdtBalance);
                Common.writeFile("/Users/lyrics-mac/IDEA-CODE/zCoin_project/ztuo_framework/wallet/conf/iron/iron.txt", address + ":" + privateKey + ":" + ethBalance + ":" + usdtBalance);
            }
        }
    }
}

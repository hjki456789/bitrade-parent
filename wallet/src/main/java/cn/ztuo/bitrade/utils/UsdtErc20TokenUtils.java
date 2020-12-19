package cn.ztuo.bitrade.utils;

import java.util.*;
import org.web3j.tx.*;
import org.web3j.protocol.core.methods.response.*;
import org.apache.commons.lang3.*;
import org.web3j.protocol.core.*;
import java.math.*;
import org.web3j.utils.*;
import org.web3j.protocol.http.*;
import org.web3j.protocol.admin.*;
import org.web3j.protocol.*;
import org.web3j.crypto.*;

public class UsdtErc20TokenUtils
{
    public static Map<String, Object> sendERC20(final String netUrl, final String fromAddress, final String toAddress, final String contractAddress, final String fromPri, final String bigDecimalValue) {
        final Map<String, Object> map = new HashMap<String, Object>();
        int status = 1;
        final Web3j web3j = getWeb3j(netUrl);
        final UsdtErc20Contract erc20Contract = getERC20Contract(web3j, contractAddress, fromPri);
        String erc20Balance = "";
        try {
            erc20Balance = getERC20Balance(web3j, (Contract)erc20Contract, fromAddress, 6);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final String checkMoney = checkMoney(bigDecimalValue, erc20Balance);
        if (!checkMoney.contentEquals("")) {
            status = 2;
            map.put("status", status);
            return map;
        }
        final BigInteger value = Convert.toWei(bigDecimalValue, Convert.Unit.MWEI).toBigInteger();
        String transactionHash = "";
        try {
            final RemoteCall<TransactionReceipt> transfer = (RemoteCall<TransactionReceipt>)erc20Contract.transfer(toAddress, value);
            final TransactionReceipt transactionReceipt = transfer.sendAsync().get();
            transactionHash = transactionReceipt.getTransactionHash();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        close(web3j);
        System.out.println("\u4ea4\u6613id:" + transactionHash);
        if (StringUtils.isNotEmpty((CharSequence)transactionHash)) {
            status = 0;
        }
        map.put("status", status);
        map.put("txHash", transactionHash);
        return map;
    }
    
    public static BigDecimal fromWei(final BigInteger wei, final int decimals) {
        return new BigDecimal(wei).divide(getRatio(decimals));
    }
    
    public static BigInteger toWei(final BigDecimal amount, final int decimals) {
        return amount.multiply(getRatio(decimals)).toBigInteger();
    }
    
    public static BigDecimal getRatio(final int decimals) {
        final String ratio = "1" + Strings.repeat('0', decimals) + ".0";
        return new BigDecimal(ratio);
    }
    
    public static Double getBalance(final String netUrl, final String contractAddress, final String address, final String pri) {
        Double balance = 0.0;
        final Web3j web3j = getWeb3j(netUrl);
        final UsdtErc20Contract contract = getERC20Contract(web3j, contractAddress, pri);
        String erc20Balance = "0";
        try {
            erc20Balance = getERC20Balance(web3j, (Contract)contract, address, 6);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        balance = new BigDecimal(erc20Balance).setScale(6, 4).doubleValue();
        close(web3j);
        return balance;
    }
    
    public static Web3j getWeb3j(final String netUrl) {
        final Admin web3j = Admin.build((Web3jService)new HttpService(netUrl));
        return (Web3j)web3j;
    }
    
    public static void close(final Web3j web3j) {
        web3j.shutdown();
    }
    
    public static String checkMoney(final String bigDecimalValue, final String addressBalance) {
        if (new BigDecimal(addressBalance).subtract(new BigDecimal(bigDecimalValue)).compareTo(new BigDecimal("0")) < 0) {
            return "\u8f6c\u8d26\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d";
        }
        return "";
    }
    
    public static UsdtErc20Contract getERC20Contract(final Web3j web3j, final String contractAddress, final String userPri) {
        final BigInteger GAS_PRICE = BigInteger.valueOf(192000000000L);
        final BigInteger GAS_LIMIT = BigInteger.valueOf(100000L);
        final Credentials credentials = Credentials.create(userPri);
        final UsdtErc20Contract contract = UsdtErc20Contract.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
        return contract;
    }
    
    public static String getERC20Balance(final Web3j web3j, final Contract contract, final String userAddress, final int decimal) throws Exception {
        final RemoteCall<BigInteger> balanceOf = (RemoteCall<BigInteger>)((UsdtErc20Contract)contract).balanceOf(userAddress);
        final BigInteger send2 = (BigInteger)balanceOf.send();
        final String result = toDecimal(decimal, send2);
        return result;
    }
    
    public static String toDecimal(final int decimal, final BigInteger integer) {
        final StringBuffer sbf = new StringBuffer("1");
        for (int i = 0; i < decimal; ++i) {
            sbf.append("0");
        }
        final String balance = new BigDecimal(integer).divide(new BigDecimal(sbf.toString()), 6, 1).toPlainString();
        return balance;
    }
    
    public static void main(final String[] args) throws Exception {
    }
}

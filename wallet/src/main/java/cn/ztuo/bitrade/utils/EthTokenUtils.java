package cn.ztuo.bitrade.utils;

import org.web3j.protocol.admin.Admin;
import org.web3j.utils.*;
import org.web3j.tx.*;
import org.apache.commons.lang3.*;
import org.web3j.protocol.http.*;
import org.web3j.protocol.*;
import java.math.*;
import java.io.*;
import org.web3j.crypto.*;
import cn.ztuo.bitrade.entity.*;
import org.web3j.protocol.core.*;
import java.util.*;
import org.web3j.protocol.core.methods.response.*;

public class EthTokenUtils
{
    public static String KEYSTORE_PATH;

    public static Map<String, Object> sendEth(final String netUrl, final String fromAddress, final String toAddress, final String fromPri, final String bigDecimalValue) {
        final Map<String, Object> map = new HashMap<String, Object>();
        int status = 1;
        final Web3j web3j = getWeb3j(netUrl);
        String balance = "";
        try {
            balance = getEthBanlance(web3j, fromAddress);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final String checkMoney = checkMoney(bigDecimalValue, balance);
        if (!checkMoney.contentEquals("")) {
            status = 2;
            map.put("status", status);
            return map;
        }
        String transactionHash = "";
        try {
            final Credentials credentials = Credentials.create(fromPri);
            final TransactionReceipt transactionReceipt = (TransactionReceipt)Transfer.sendFunds(web3j, credentials, toAddress, new BigDecimal(bigDecimalValue), Convert.Unit.ETHER).send();
            transactionHash = transactionReceipt.getTransactionHash();
            transactionHash.toString();
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

    public static Web3j getWeb3j(final String netUrl) {
        final Admin web3j = Admin.build((Web3jService)new HttpService(netUrl));
        return (Web3j)web3j;
    }

    public static void close(final Web3j web3j) {
        web3j.shutdown();
    }

    public static String checkMoney(final String bigDecimalValue, final String addressBalance) {
        if (new BigDecimal(addressBalance).subtract(new BigDecimal(bigDecimalValue)).compareTo(new BigDecimal("0")) <= 0) {
            return "\u8f6c\u8d26\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d";
        }
        return "";
    }

    public static String getEthBanlance(final Web3j web3j, final String userAddress) throws IOException {
        final BigInteger integer = ((EthGetBalance)web3j.ethGetBalance(userAddress, (DefaultBlockParameter)DefaultBlockParameterName.LATEST).send()).getBalance();
        final String decimal = toDecimal(18, integer);
        return decimal;
    }

    public static Double getEthBalance(final String netUrl, final String address) {
        Double ethBalance = 0.0;
        final Web3j web3j = getWeb3j(netUrl);
        try {
            String decimal = "";
            final BigInteger integer = ((EthGetBalance)web3j.ethGetBalance(address, (DefaultBlockParameter)DefaultBlockParameterName.LATEST).send()).getBalance();
            decimal = toDecimal(18, integer);
            ethBalance = new BigDecimal(decimal).setScale(8, 4).doubleValue();
            close(web3j);
        }
        catch (Exception e) {
            e.printStackTrace();
            close(web3j);
        }
        return ethBalance;
    }

    public static String toDecimal(final int decimal, final BigInteger integer) {
        final StringBuffer sbf = new StringBuffer("1");
        for (int i = 0; i < decimal; ++i) {
            sbf.append("0");
        }
        final String balance = new BigDecimal(integer).divide(new BigDecimal(sbf.toString()), 18, 1).toPlainString();
        return balance;
    }

    public static GaMemberAccount createAccount() throws Exception {
        final GaMemberAccount gaMemberAccount = new GaMemberAccount();
        try {
            final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJeCkN9N87AbFbIrHv99zNH+oscHsVb1cDy9OrT+4qtiQjB5BEUSOVjNm4laeSdVdO2nc3XCL70zBM8oSYL8V4IsCIWmjpRjHY0ILYsyq8IiyE591NYOQv43CVnIIXXW0BUOQpPzALoVYceODpe6NWxGuvc69lNkBmn6XYpF8myQIDAQAB";
            String password = UUID.randomUUID().toString().replaceAll("-", "") + RandomUtil.generateOnlyNumber(10);
            final File f = new File(EthTokenUtils.KEYSTORE_PATH);
            if (!f.exists()) {
                f.mkdirs();
            }
            final Bip39Wallet wallet = WalletUtils.generateBip39Wallet(password, new File(EthTokenUtils.KEYSTORE_PATH));
            final File files = new File(EthTokenUtils.KEYSTORE_PATH);
            if (!files.exists()) {
                files.mkdirs();
            }
            else {
                final File[] listFiles;
                final File[] listfile = listFiles = files.listFiles();
                for (final File file : listFiles) {
                    file.delete();
                }
            }
            String memorizingWords = wallet.getMnemonic();
            final Credentials credentials = WalletUtils.loadBip39Credentials(password, memorizingWords);
            final String address = credentials.getAddress();
            String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
            password = RSAUtils.encryptByPublicKey(password, RSA_PUBLIC_KEY);
            privateKey = RSAUtils.encryptByPublicKey(privateKey, RSA_PUBLIC_KEY);
            memorizingWords = RSAUtils.encryptByPublicKey(memorizingWords, RSA_PUBLIC_KEY);
            gaMemberAccount.setAddress(address);
            gaMemberAccount.setPassword(password);
            gaMemberAccount.setPrivateKey(privateKey);
            gaMemberAccount.setSecretWord(memorizingWords);
            gaMemberAccount.setType(1);
        }
        catch (Exception e) {
            throw new Exception("\u4ee5\u592a\u574a\u8d26\u6237\u521b\u5efa\u5931\u8d25");
        }
        return gaMemberAccount;
    }

    public static List<EthTranscation> getEthTxByBlockNum(final String netUrl, final Integer blockNum) throws Exception {
        final List<EthTranscation> resultList = new ArrayList<EthTranscation>();
        final Web3j web3j = getWeb3j(netUrl);
        final List<EthBlock.TransactionResult> txs = (List<EthBlock.TransactionResult>)((EthBlock)web3j.ethGetBlockByNumber((DefaultBlockParameter)new DefaultBlockParameterNumber((long)blockNum), true).send()).getBlock().getTransactions();
        for (final EthBlock.TransactionResult tx : txs) {
            final EthBlock.TransactionObject transaction = (EthBlock.TransactionObject)tx.get();
            final BigInteger ethBigNum = transaction.getValue();
            Double ethNum = Convert.fromWei(ethBigNum.toString(), Convert.Unit.ETHER).doubleValue();
            ethNum = BigDecimalCalculateUtils.formatDouble15((double)ethNum);
            if (ethNum > 0.0) {
                final EthTranscation ethTranscation = new EthTranscation();
                ethTranscation.setTxHash(transaction.getHash());
                ethTranscation.setToAddress(transaction.getTo());
                ethTranscation.setEthNum(ethNum);
                resultList.add(ethTranscation);
            }
        }
        close(web3j);
        return resultList;
    }

    public static String requestCurrentGasPrice(final String url) throws Exception {
        final Web3j web3j = getWeb3j(url);
        final EthGasPrice ethGasPrice = (EthGasPrice)web3j.ethGasPrice().send();
        close(web3j);
        return toDecimal(18, ethGasPrice.getGasPrice());
    }

    public static void main(final String[] args) throws Exception {
        final String privateKey = "";
        sendEth("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", "0x6954455b09ef2308657054e1fac39e9a9a9f61e6", "0xe20d1ff4d249ec27135d86d0fb161f0bcd4fcc55", "b4fb9ab51b3f3280384bfc82137ac26728515fbca54f5f5ae3f71d441dc60f68", "");
    }

    static {
        EthTokenUtils.KEYSTORE_PATH = "";
    }
}

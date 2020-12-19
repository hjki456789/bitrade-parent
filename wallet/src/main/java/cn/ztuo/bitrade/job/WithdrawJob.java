package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.consumer.*;
import org.slf4j.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.utils.*;
import org.springframework.scheduling.annotation.*;
import com.alibaba.fastjson.*;
import java.util.*;

@Component
public class WithdrawJob
{
    @Autowired
    private ChainLastBlockService chainLastBlockService;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private DataDictionaryService dictionaryService;
    private String serviceName;
    @Autowired
    private MemberGradeService gradeService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MemberService memberService;
    @Autowired
    private IntegrationRecordService integrationRecordService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private WithdrawRecordService withdrawRecordService;
    @Value("${hot.address.eth}")
    private String hotAddressEth;
    private static String hotPrivateKey;
    private Logger logger;
    private static String rsaKey;
    private static int taskNum;
    private static String path;

    public WithdrawJob() {
        this.serviceName = "bitrade-market";
        this.logger = LoggerFactory.getLogger((Class)FinanceConsumer.class);
    }

    @Scheduled(fixedRate = 600000L)
    public void job() throws Exception {
        if (WithdrawJob.rsaKey == null) {
            try {
                final List<String> rsaKeys = (List<String>)Common.readFileByLine(WithdrawJob.path + "rsa.txt");
                WithdrawJob.rsaKey = rsaKeys.get(0).trim();
                WithdrawJob.hotPrivateKey = RSAUtils.decryptByPrivateKey(WithdrawJob.hotPrivateKey, WithdrawJob.rsaKey);
            }
            catch (Exception e) {
                this.logger.info("---------------\u83b7\u53d6RSA\u79c1\u94a5\u5931\u8d25--------------------");
                return;
            }
        }
        ++WithdrawJob.taskNum;
        this.logger.info("---------------\u63d0\u5e01\u4efb\u52a1\u5f00\u59cb\uff0c\u6570\u91cf---------------------" + WithdrawJob.taskNum);
        if (WithdrawJob.taskNum > 1) {
            WithdrawJob.taskNum = 1;
            return;
        }
        try {
            this.logger.info("---------------\u63d0\u5e01\u4efb\u52a1\u5f00\u59cb---------------------");
            this.logger.info("---------------\u70ed\u5730\u5740---------------------" + this.hotAddressEth);
            final List<WithdrawRecord> withdrawRecordList = (List<WithdrawRecord>)this.withdrawRecordService.findAllByStatusAndType();
            this.logger.info("---------------\u83b7\u53d6\u63d0\u73b0\u7533\u8bf7\u6761\u6570---------------------" + withdrawRecordList.size());
            Thread.sleep(50000L);
            for (final WithdrawRecord withdrawRecord : withdrawRecordList) {
                final String withdrawAddress = withdrawRecord.getAddress().trim();
                final double amount = withdrawRecord.getArrivedAmount().doubleValue();
                final String coinName = withdrawRecord.getCoin().getName();
                String txHash = withdrawRecord.getTxHash();
                if (txHash != null && !txHash.equals("")) {
                    this.logger.info("---------------\u6b64\u5730\u5740\u63d0\u5e01hash\u5df2\u5b58\u5728---------------------" + txHash);
                    this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                }
                else {
                    this.logger.info("-----------------\u5f53\u524d\u63d0\u5e01\u5730\u5740\u4e3a----------" + withdrawAddress);
                    this.logger.info("-----------------\u5f53\u524d\u5e01\u79cd\u4e3a----------" + coinName);
                    this.logger.info("-----------------\u5f53\u524d\u63d0\u5e01\u6570\u91cf\u4e3a----------" + amount);
                    if (withdrawAddress.length() != 42) {
                        this.logger.info("-----------------\u5f53\u524d\u63d0\u5e01\u5730\u5740\u4e0d\u662f\u4ee5\u592a\u574a\u5730\u5740----------" + withdrawAddress);
                        this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                    }
                    else {
                        final Double hotAddressEthBalance = EthTokenUtils.getEthBalance("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", this.hotAddressEth);
                        if (hotAddressEthBalance < 0.02) {
                            this.logger.info("-----------------\u70ed\u94b1\u5305\u4ee5\u592a\u574a\u4f59\u989d\u4e0d\u8db3\uff0c\u65e0\u6cd5\u63d0\u4f9b\u8f6c\u8d26gas\u8d39----------");
                            break;
                        }
                        if ("ETH".equalsIgnoreCase(coinName)) {
                            if (hotAddressEthBalance > amount + 0.02) {
                                final int updateStatus = this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                                if (updateStatus > 0) {
                                    this.logger.info("-----------------EHT\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u6210\u529f\uff0c\u6b63\u5728\u8f6c\u8d26----------" + withdrawAddress);
                                    txHash = this.sendEth(this.hotAddressEth, withdrawAddress, WithdrawJob.hotPrivateKey, amount + "");
                                    if (txHash == null) {
                                        continue;
                                    }
                                    this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                                }
                                else {
                                    this.logger.info("-----------------EHT\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u5931\u8d25\uff0c\u4e0d\u8f6c\u8d26----------" + withdrawAddress);
                                }
                            }
                            else {
                                this.logger.info("-----------------\u70ed\u94b1\u5305\u4ee5\u592a\u574a\u4f59\u989d\u4e0d\u8db3\uff0c\u65e0\u6cd5\u63d0\u4f9b\u8f6c\u8d26----------");
                            }
                        }
                        else if ("USDT".equalsIgnoreCase(coinName)) {
                            final Double usdtBalance = UsdtErc20TokenUtils.getBalance("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", "0xdac17f958d2ee523a2206206994597c13d831ec7", this.hotAddressEth, WithdrawJob.hotPrivateKey);
                            if (usdtBalance > amount) {
                                final int updateStatus2 = this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                                if (updateStatus2 > 0) {
                                    this.logger.info("-----------------USDT\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u6210\u529f,\u6b63\u5728\u8f6c\u8d26----------" + withdrawAddress);
                                    txHash = this.sendUsdt(this.hotAddressEth, withdrawAddress, WithdrawJob.hotPrivateKey, amount + "");
                                    if (txHash == null) {
                                        continue;
                                    }
                                    this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                                }
                                else {
                                    this.logger.info("-----------------USDT\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u5931\u8d25\uff0c\u4e0d\u8f6c\u8d26----------" + withdrawAddress);
                                }
                            }
                            else {
                                this.logger.info("-----------------USDT\u4f59\u989d\u4e0d\u8db3----------");
                            }
                        }
                        else if ("AMC".equalsIgnoreCase(coinName)) {
                            final int updateStatus = this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                            if (updateStatus > 0) {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u6210\u529f,\u6b63\u5728\u8f6c\u8d26----------" + withdrawAddress);
                                txHash = this.sendAMC(this.hotAddressEth, withdrawAddress, WithdrawJob.hotPrivateKey, amount + "");
                                if (txHash == null) {
                                    continue;
                                }
                                this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                            }
                            else {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u5931\u8d25\uff0c\u4e0d\u8f6c\u8d26----------" + withdrawAddress);
                            }
                        }
                        else if ("GTD".equalsIgnoreCase(coinName)) {
                            final int updateStatus = this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                            if (updateStatus > 0) {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u6210\u529f,\u6b63\u5728\u8f6c\u8d26----------" + withdrawAddress);
                                txHash = this.sendGTD(this.hotAddressEth, withdrawAddress, WithdrawJob.hotPrivateKey, amount + "");
                                if (txHash == null) {
                                    continue;
                                }
                                this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                            }
                            else {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u5931\u8d25\uff0c\u4e0d\u8f6c\u8d26----------" + withdrawAddress);
                            }
                        }
                        else if ("KIF".equalsIgnoreCase(coinName)) {
                            final int updateStatus = this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                            if (updateStatus > 0) {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u6210\u529f,\u6b63\u5728\u8f6c\u8d26----------" + withdrawAddress);
                                txHash = this.sendKIF(this.hotAddressEth, withdrawAddress, WithdrawJob.hotPrivateKey, amount + "");
                                if (txHash == null) {
                                    continue;
                                }
                                this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                            }
                            else {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u5931\u8d25\uff0c\u4e0d\u8f6c\u8d26----------" + withdrawAddress);
                            }
                        }
                        else if ("WCG".equalsIgnoreCase(coinName)) {
                            final int updateStatus = this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                            if (updateStatus > 0) {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u6210\u529f,\u6b63\u5728\u8f6c\u8d26----------" + withdrawAddress);
                                txHash = this.sendWCG(this.hotAddressEth, withdrawAddress, WithdrawJob.hotPrivateKey, amount + "");
                                if (txHash == null) {
                                    continue;
                                }
                                this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                            }
                            else {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u5931\u8d25\uff0c\u4e0d\u8f6c\u8d26----------" + withdrawAddress);
                            }
                        }
                        else {
                            if (!"TKJ".equalsIgnoreCase(coinName)) {
                                continue;
                            }
                            final int updateStatus = this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 2, "");
                            if (updateStatus > 0) {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u6210\u529f,\u6b63\u5728\u8f6c\u8d26----------" + withdrawAddress);
                                txHash = this.sendTKJ(this.hotAddressEth, withdrawAddress, WithdrawJob.hotPrivateKey, amount + "");
                                if (txHash == null) {
                                    continue;
                                }
                                this.withdrawRecordService.updateWithdrawTypeAndTxHash((long)withdrawRecord.getId(), 1, txHash);
                            }
                            else {
                                this.logger.info("-----------------\u8f6c\u8d26\u524d\u72b6\u6001\u66f4\u65b0\u5931\u8d25\uff0c\u4e0d\u8f6c\u8d26----------" + withdrawAddress);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        --WithdrawJob.taskNum;
        this.logger.info("---------------\u63d0\u5e01\u4efb\u52a1\u7ed3\u675f\uff0c\u6570\u91cf---------------------" + WithdrawJob.taskNum);
    }

    private String sendEth(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final Map<String, Object> ethResult = EthTokenUtils.sendEth("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, fromPrivateKey, withdrawNumStr);
            if ((Integer)ethResult.get("status") == 0) {
                this.logger.info(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "ETH");
                return (String) ethResult.get("txHash");
            }
            if ((Integer)ethResult.get("status") == 2) {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "eth");
            }
            else {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)ethResult));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendUsdt(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0xdac17f958d2ee523a2206206994597c13d831ec7", fromPrivateKey, withdrawNumStr);
            if ((Integer)result.get("status") == 0) {
                this.logger.info(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "USDT");
                return (String) result.get("txHash");
            }
            if ((Integer)result.get("status") == 2) {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "usdt");
            }
            else {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)result));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendAMC(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) * 1.0E12;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0xe2ded2365bd426b2078bc947d70cc5c0141a4e6f", fromPrivateKey, amount + "");
            if ((Integer)result.get("status") == 0) {
                this.logger.info(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "AMC");
                return (String) result.get("txHash");
            }
            if ((Integer)result.get("status") == 2) {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "AMC");
            }
            else {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)result));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendKIF(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) / 10000.0;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0x4e880d553904fb3be6c3e767b67e23111a5abe28", fromPrivateKey, amount + "");
            if ((Integer)result.get("status") == 0) {
                this.logger.info(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "KIF");
                return (String) result.get("txHash");
            }
            if ((Integer)result.get("status") == 2) {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "KIF");
            }
            else {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)result));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendTKJ(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) * 1.0E12;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0x1bb7c7f703ce521fa68524286d6a6d177741cd92", fromPrivateKey, amount + "");
            if ((Integer)result.get("status") == 0) {
                this.logger.info(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "TKJ");
                return (String) result.get("txHash");
            }
            if ((Integer)result.get("status") == 2) {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "TKJ");
            }
            else {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)result));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendGTD(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) * 1.0E12;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0x46f4129bfca15f8807c8ef067afc2377562e9074", fromPrivateKey, amount + "");
            if ((Integer)result.get("status") == 0) {
                this.logger.info(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "GTD");
                return (String) result.get("txHash");
            }
            if ((Integer)result.get("status") == 2) {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "GTD");
            }
            else {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)result));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sendWCG(final String fromAddress, final String toAddress, final String fromPrivateKey, final String withdrawNumStr) {
        try {
            final double amount = Double.parseDouble(withdrawNumStr) * 1.0E12;
            final Map<String, Object> result = UsdtErc20TokenUtils.sendERC20("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", fromAddress, toAddress, "0xff642a0D2d0f3B15D7F19d6F0F2c9139BE1Aa29e", fromPrivateKey, amount + "");
            if ((Integer)result.get("status") == 0) {
                this.logger.info(toAddress + "\u63d0\u73b0\u6210\u529f,\u63d0\u73b0\u91d1\u989d\u4e3a\uff1a" + withdrawNumStr + "WCG");
                return (String) result.get("txHash");
            }
            if ((Integer)result.get("status") == 2) {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u94b1\u5305\u5730\u5740\u4f59\u989d,\u63d0\u73b0\u91d1\u989d\u4e3a" + withdrawNumStr + "WCG");
            }
            else {
                this.logger.info(toAddress + ":\u63d0\u73b0\u5931\u8d25\uff0c\u9519\u8bef\u4fe1\u606f\u4e3a" + JSON.toJSONString((Object)result));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(final String[] args) {
        final WithdrawJob job = new WithdrawJob();
    }

    static {
        WithdrawJob.hotPrivateKey = "gXkEnx6lc7+jkJm/m8F1ymrU1ZUmNgXShjQwzhzyanB5SGsdf/Pxpp2bD178O25192ThrKoyZSAPriI+hwn5slcq+OuRHWxBMFNm7pK/pNUaXCulSTaIE9Wld3TtB5sIDbstjYth2Vz0rHdHqHA8WTBiqGHquwEMeOMUkdvMtcg=";
        WithdrawJob.rsaKey = null;
        WithdrawJob.taskNum = 0;
        WithdrawJob.path = "/info/pool/";
        if (WithdrawJob.rsaKey == null) {
            try {
                final List<String> rsaKeys = (List<String>)Common.readFileByLine(WithdrawJob.path + "rsa.txt");
                WithdrawJob.rsaKey = rsaKeys.get(0).trim();
                WithdrawJob.hotPrivateKey = RSAUtils.decryptByPrivateKey(WithdrawJob.hotPrivateKey, WithdrawJob.rsaKey);
                System.out.println("--------------------tixian-------chushihua-rsa---chenggong-----------");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

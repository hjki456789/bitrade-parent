package cn.ztuo.bitrade.job;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.consumer.*;
import org.slf4j.*;
import cn.ztuo.bitrade.util.*;
import java.math.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;
import org.springframework.scheduling.annotation.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.utils.*;

@Configuration
@Component
public class CollectJob
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
    @Value("${cold.address.eth}")
    private String coldAddressEth;
    @Value("${cold.address.btc}")
    private String coldAddressBtc;
    @Value("${hot.address.eth}")
    private String hotAddressEth;
    @Value("${hot.address.btc}")
    private String hotAddressBtc;
    private static String rsaKey;
    @Value("${address.file.name}")
    private String fileName;
    @Value("${cold.rate}")
    private String coldRateStr;
    @Value("${hot.rate}")
    private String hotRateStr;
    private static String path;
    private static String hotPrivateKey;
    private Logger logger;

    public CollectJob() {
        this.serviceName = "bitrade-market";
        this.logger = LoggerFactory.getLogger((Class)FinanceConsumer.class);
    }

    @Scheduled(fixedRate = 3600000L)
    public void job() throws Exception {
        if (CollectJob.rsaKey == null) {
            try {
                final List<String> rsaKeys = (List<String>)Common.readFileByLine(CollectJob.path + this.fileName + "-rsa.txt");
                CollectJob.rsaKey = rsaKeys.get(0).trim();
                CollectJob.hotPrivateKey = RSAUtils.decryptByPrivateKey(CollectJob.hotPrivateKey, CollectJob.rsaKey);
            }
            catch (Exception e2) {
                this.logger.info("---------------\u83b7\u53d6RSA\u79c1\u94a5\u5931\u8d25--------------------");
                return;
            }
        }
        final List<MemberDeposit> needCollectInfos = (List<MemberDeposit>)this.memberWalletService.getMemberNeedCollectInfos();
        if (needCollectInfos == null || needCollectInfos.size() == 0) {
            return;
        }
        this.logger.info("---------------\u5f52\u96c6\u4efb\u52a1\u5f00\u59cb---------------------" + this.fileName);
        this.logger.info("---------------\u51b7\u5730\u5740\u4e3a---------------------" + this.coldAddressEth);
        this.logger.info("---------------\u70ed\u5730\u5740\u4e3a---------------------" + this.hotAddressEth);
        this.logger.info("---------------\u5f85\u5f52\u96c6\u8bb0\u5f55\u6761\u6570---------------------" + needCollectInfos.size());
        final List<String> addresses = (List<String>)Common.readFileByLine(CollectJob.path + this.fileName + "-adr.txt");
        final List<String> keys = (List<String>)Common.readFileByLine(CollectJob.path + this.fileName + "-key.txt");
        final Map<String, String> wallets = new HashMap<String, String>();
        for (int i = 0; i < addresses.size(); ++i) {
            wallets.put(addresses.get(i).toLowerCase(), keys.get(i));
        }
        this.logger.info("---------------\u5730\u5740\u6c60\u5bb9\u91cf---------------------" + addresses.size());
        Thread.sleep(100000L);
        for (final MemberDeposit memberDeposit : needCollectInfos) {
            final String address = memberDeposit.getAddress().toLowerCase();
            final String coinName = memberDeposit.getUnit();
            double amount = memberDeposit.getAmount().doubleValue();
            this.logger.info("---------------\u5f52\u96c6\u5f00\u59cb---------------------" + coinName + "-----" + address + "\uff1a" + amount);
            if (!coinName.equalsIgnoreCase("BTC") && address.length() == 42) {
                if (amount <= 0.0) {
                    continue;
                }
                final Double addressEthBalance = EthTokenUtils.getEthBalance("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", address);
                if (addressEthBalance < 0.02) {
                    this.logger.info("---------------gas\u8d39\u4e0d\u8db3\uff0c\u9a6c\u4e0a\u5145\u503c---------------------");
                    final Double hotAddressEthBalance = EthTokenUtils.getEthBalance("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", this.hotAddressEth);
                    if (hotAddressEthBalance < 0.04) {
                        this.logger.info("---------------\u70ed\u5730\u5740\u4f59\u989d\u4e0d\u8db3---------------------" + hotAddressEthBalance);
                        continue;
                    }
                    final String hotHash = this.sendEth(this.hotAddressEth, address, CollectJob.hotPrivateKey, "0.02");
                    if (hotHash == null) {
                        this.logger.info("---------------gas\u5145\u503c\u5931\u8d25\uff0c\u7b49\u5f85\u4e0b\u6b21\u5faa\u73af---------------------");
                        continue;
                    }
                }
                String privateKey = null;
                try {
                    privateKey = RSAUtils.decryptByPrivateKey(wallets.get(address), CollectJob.rsaKey);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                String collectHashCold = null;
                String collectHashHot = null;
                double amountCold = 0.0;
                double amountHot = 0.0;
                double hotRate = 0.0;
                double coldRate = 1.0;
                try {
                    hotRate = Double.parseDouble(this.hotRateStr);
                    coldRate = Double.parseDouble(this.coldRateStr);
                }
                catch (Exception ex) {}
                if (coinName.equalsIgnoreCase("ETH")) {
                    amount -= 0.008;
                    if (amount <= 0.0) {
                        continue;
                    }
                    amountCold = amount * coldRate;
                    amountHot = amount * hotRate;
                    if (amountCold > 0.0) {
                        this.logger.info("---------------\u5411\u51b7\u94b1\u5305\u5f52\u96c6---------------------" + this.coldAddressEth + "\uff1a" + amountCold);
                        collectHashCold = this.sendEth(address, this.coldAddressEth, privateKey, amountCold + "");
                    }
                    if (amountHot > 0.0) {
                        this.logger.info("---------------\u5411\u70ed\u94b1\u5305\u5f52\u96c6---------------------" + this.hotAddressEth + "\uff1a" + amountHot);
                        collectHashHot = this.sendEth(address, this.hotAddressEth, privateKey, amountHot + "");
                    }
                }
                else if (coinName.equalsIgnoreCase("USDT")) {
                    amount = (int)amount;
                    if (amount <= 0.0) {
                        continue;
                    }
                    amountCold = amount * coldRate;
                    amountHot = amount * hotRate;
                    amountHot = (int)amountHot;
                    if (amountCold > 0.0) {
                        this.logger.info("---------------\u5411\u51b7\u94b1\u5305\u5f52\u96c6---------------------" + this.coldAddressEth + "\uff1a" + amountCold);
                        collectHashCold = this.sendUsdt(address, this.coldAddressEth, privateKey, amountCold + "");
                    }
                    if (amountHot > 0.0) {
                        this.logger.info("---------------\u5411\u70ed\u94b1\u5305\u5f52\u96c6---------------------" + this.hotAddressEth + "\uff1a" + amountHot);
                        collectHashHot = this.sendUsdt(address, this.hotAddressEth, privateKey, amountHot + "");
                    }
                }
                else if (coinName.equalsIgnoreCase("AMC")) {
                    amount = (int)amount;
                    if (amount <= 0.0) {
                        continue;
                    }
                    amountCold = amount * coldRate;
                    amountHot = amount * hotRate;
                    amountHot = (int)amountHot;
                    if (amountCold > 0.0) {
                        this.logger.info("---------------\u5411\u51b7\u94b1\u5305\u5f52\u96c6---------------------" + this.coldAddressEth + "\uff1a" + amountCold);
                        collectHashCold = this.sendAMC(address, this.coldAddressEth, privateKey, amountCold + "");
                    }
                    if (amountHot > 0.0) {
                        this.logger.info("---------------\u5411\u70ed\u94b1\u5305\u5f52\u96c6---------------------" + this.hotAddressEth + "\uff1a" + amountHot);
                        collectHashHot = this.sendAMC(address, this.hotAddressEth, privateKey, amountHot + "");
                    }
                }
                else if (coinName.equalsIgnoreCase("KIF")) {
                    amount = (int)amount;
                    if (amount <= 0.0) {
                        continue;
                    }
                    amountCold = amount * coldRate;
                    amountHot = amount * hotRate;
                    amountHot = (int)amountHot;
                    if (amountCold > 0.0) {
                        this.logger.info("---------------\u5411\u51b7\u94b1\u5305\u5f52\u96c6---------------------" + this.coldAddressEth + "\uff1a" + amountCold);
                        collectHashCold = this.sendKIF(address, this.coldAddressEth, privateKey, amountCold + "");
                    }
                    if (amountHot > 0.0) {
                        this.logger.info("---------------\u5411\u70ed\u94b1\u5305\u5f52\u96c6---------------------" + this.hotAddressEth + "\uff1a" + amountHot);
                        collectHashHot = this.sendKIF(address, this.hotAddressEth, privateKey, amountHot + "");
                    }
                }
                else if (coinName.equalsIgnoreCase("GTD")) {
                    amount = (int)amount;
                    if (amount <= 0.0) {
                        continue;
                    }
                    amountCold = amount * coldRate;
                    amountHot = amount * hotRate;
                    amountHot = (int)amountHot;
                    if (amountCold > 0.0) {
                        this.logger.info("---------------\u5411\u51b7\u94b1\u5305\u5f52\u96c6---------------------" + this.coldAddressEth + "\uff1a" + amountCold);
                        collectHashCold = this.sendGTD(address, this.coldAddressEth, privateKey, amountCold + "");
                    }
                    if (amountHot > 0.0) {
                        this.logger.info("---------------\u5411\u70ed\u94b1\u5305\u5f52\u96c6---------------------" + this.hotAddressEth + "\uff1a" + amountHot);
                        collectHashHot = this.sendGTD(address, this.hotAddressEth, privateKey, amountHot + "");
                    }
                }
                else if (coinName.equalsIgnoreCase("WCG")) {
                    amount = (int)amount;
                    if (amount <= 0.0) {
                        continue;
                    }
                    amountCold = amount * coldRate;
                    amountHot = amount * hotRate;
                    amountHot = (int)amountHot;
                    if (amountCold > 0.0) {
                        this.logger.info("---------------\u5411\u51b7\u94b1\u5305\u5f52\u96c6---------------------" + this.coldAddressEth + "\uff1a" + amountCold);
                        collectHashCold = this.sendWCG(address, this.coldAddressEth, privateKey, amountCold + "");
                    }
                    if (amountHot > 0.0) {
                        this.logger.info("---------------\u5411\u70ed\u94b1\u5305\u5f52\u96c6---------------------" + this.hotAddressEth + "\uff1a" + amountHot);
                        collectHashHot = this.sendWCG(address, this.hotAddressEth, privateKey, amountHot + "");
                    }
                }
                else if (coinName.equalsIgnoreCase("TKJ")) {
                    amount = (int)amount;
                    if (amount <= 0.0) {
                        continue;
                    }
                    amountCold = amount * coldRate;
                    amountHot = amount * hotRate;
                    amountHot = (int)amountHot;
                    if (amountCold > 0.0) {
                        this.logger.info("---------------\u5411\u51b7\u94b1\u5305\u5f52\u96c6---------------------" + this.coldAddressEth + "\uff1a" + amountCold);
                        collectHashCold = this.sendTKJ(address, this.coldAddressEth, privateKey, amountCold + "");
                    }
                    if (amountHot > 0.0) {
                        this.logger.info("---------------\u5411\u70ed\u94b1\u5305\u5f52\u96c6---------------------" + this.hotAddressEth + "\uff1a" + amountHot);
                        collectHashHot = this.sendTKJ(address, this.hotAddressEth, privateKey, amountHot + "");
                    }
                }
                this.logger.info("---------------\u4fdd\u5b58\u5f52\u96c6\u4fe1\u606f---------------------");
                if (collectHashCold != null) {
                    this.memberWalletService.updateCollectType((long)memberDeposit.getId(), 1);
                    final CoinCollectLog coinCollectLogCold = new CoinCollectLog();
                    coinCollectLogCold.setAddress(address);
                    coinCollectLogCold.setAmount(new BigDecimal(amountCold));
                    coinCollectLogCold.setCoinName(coinName);
                    coinCollectLogCold.setTime(new Date());
                    coinCollectLogCold.setTxHash(collectHashCold);
                    coinCollectLogCold.setToAddress(this.coldAddressEth);
                    final Member member = new Member();
                    member.setId(memberDeposit.getMemberId());
                    coinCollectLogCold.setMember(member);
                    this.memberWalletService.saveCollectLog(coinCollectLogCold);
                    this.logger.info("---------------\u4fdd\u5b58\u51b7\u5730\u5740\u5f52\u96c6\u4fe1\u606f\u6210\u529f---------------------" + collectHashCold);
                }
                else {
                    this.memberWalletService.updateCollectType((long)memberDeposit.getId(), 2);
                }
                if (collectHashHot == null) {
                    continue;
                }
                final CoinCollectLog coinCollectLogHot = new CoinCollectLog();
                coinCollectLogHot.setAddress(address);
                coinCollectLogHot.setAmount(new BigDecimal(amountHot));
                coinCollectLogHot.setCoinName(coinName);
                coinCollectLogHot.setTime(new Date());
                coinCollectLogHot.setTxHash(collectHashHot);
                coinCollectLogHot.setToAddress(this.hotAddressEth);
                final Member member = new Member();
                member.setId(memberDeposit.getMemberId());
                coinCollectLogHot.setMember(member);
                this.memberWalletService.saveCollectLog(coinCollectLogHot);
                this.logger.info("---------------\u4fdd\u5b58\u70ed\u5730\u5740\u5f52\u96c6\u4fe1\u606f\u6210\u529f---------------------" + collectHashHot);
            }
        }
    }

    public Long getLastBlockNumber() {
        try {
            String getUrl = "https://api-cn.etherscan.com/api?module=block&action=getblocknobytime&timestamp=sequence&closest=before";
            final String sequence = System.currentTimeMillis() / 1000L + "";
            getUrl = getUrl.replace("sequence", sequence);
            final String getResult = HttpUtils.sendGetRequest(getUrl);
            final JSONObject json = JSONObject.parseObject(getResult);
            return Long.parseLong(json.getString("result"));
        }
        catch (Exception e) {
            return 0L;
        }
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
        final CollectJob job = new CollectJob();
        System.out.println(job.getLastBlockNumber());
    }

    static {
        CollectJob.rsaKey = null;
        CollectJob.path = "/info/pool/";
        CollectJob.hotPrivateKey = "gXkEnx6lc7+jkJm/m8F1ymrU1ZUmNgXShjQwzhzyanB5SGsdf/Pxpp2bD178O25192ThrKoyZSAPriI+hwn5slcq+OuRHWxBMFNm7pK/pNUaXCulSTaIE9Wld3TtB5sIDbstjYth2Vz0rHdHqHA8WTBiqGHquwEMeOMUkdvMtcg=";
        if (CollectJob.rsaKey == null) {
            try {
                final List<String> rsaKeys = (List<String>)Common.readFileByLine(CollectJob.path + "rsa.txt");
                CollectJob.rsaKey = rsaKeys.get(0).trim();
                CollectJob.hotPrivateKey = RSAUtils.decryptByPrivateKey(CollectJob.hotPrivateKey, CollectJob.rsaKey);
                System.out.println("--------------------guiji-------chushihua-rsa---chenggong-----------");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

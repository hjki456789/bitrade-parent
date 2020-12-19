package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.client.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.consumer.*;
import org.slf4j.*;
import java.math.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.constant.*;
import java.util.*;
import org.springframework.http.*;
import cn.ztuo.bitrade.entity.*;
import org.springframework.scheduling.annotation.*;
import cn.ztuo.bitrade.utils.*;
import com.alibaba.fastjson.*;

@Component
public class RechargeJob
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
    private Logger logger;
    
    public RechargeJob() {
        this.serviceName = "bitrade-market";
        this.logger = LoggerFactory.getLogger((Class)FinanceConsumer.class);
    }
    
    @Scheduled(fixedRate = 20000L)
    public void job() throws Exception {
        final Coin coin = this.coinService.findOne("ETH");
        if (coin == null || coin.getEnablePool() == 0) {
            return;
        }
        this.logger.info("---------------ETH\u5145\u503c\u4efb\u52a1\u5f00\u59cb---------------------");
        final ChainLastBlock blockInfo = this.chainLastBlockService.findByAssetsName("ETH");
        if (blockInfo == null) {
            return;
        }
        final Long startblock = blockInfo.getLastBlockNum();
        final Long lastblock = this.getLastBlockNumber();
        Long lastSafeBlock = lastblock - 12L;
        if (lastSafeBlock > startblock) {
            for (int i = (int)(startblock + 1L); i <= lastSafeBlock; ++i) {
                try {
                    final List<EthTranscation> resultList = EthTokenUtils.getEthTxByBlockNum("https://mainnet.infura.io/v3/3dace0ffb53b4a9393d82b60e01baadb", i);
                    if (resultList != null) {
                        for (final EthTranscation result : resultList) {
                            if (result.getToAddress() != null) {
                                if (result.getToAddress().equals("")) {
                                    continue;
                                }
                                final MemberWallet wallet = this.memberWalletService.findByCoinAndAddress(coin, result.getToAddress());
                                if (wallet == null) {
                                    continue;
                                }
                                if (this.memberWalletService.findDeposit(result.getToAddress(), result.getTxHash()) != null) {
                                    continue;
                                }
                                final BigDecimal amount = new BigDecimal(result.getEthNum());
                                final MessageResult mr = this.memberWalletService.recharge(coin, result.getToAddress(), amount, result.getTxHash());
                                try {
                                    final DataDictionary dictionary = this.dictionaryService.findByBond("integration_giving_exchange_recharge_usdt_rate");
                                    Long integration;
                                    if ("usdt".equalsIgnoreCase(coin.getUnit())) {
                                        integration = amount.multiply(new BigDecimal(dictionary.getValue())).setScale(0).longValue();
                                    }
                                    else {
                                        final String legalCoin = "USD";
                                        final String unit = coin.getUnit();
                                        final String url = "http://" + this.serviceName + "/market/exchange-rate/{legalCoin}/{coin}";
                                        final ResponseEntity<MessageResult> messageResult = (ResponseEntity<MessageResult>)this.restTemplate.getForEntity(url, (Class)MessageResult.class, new Object[] { legalCoin, unit });
                                        this.logger.info("remote call:url={},baseCoin={},unit={},result={}", new Object[] { url, legalCoin, unit, result });
                                        if (messageResult.getStatusCode().value() == 200 && ((MessageResult)messageResult.getBody()).getCode() == 0) {
                                            final BigDecimal rate = new BigDecimal((String)((MessageResult)messageResult.getBody()).getData());
                                            final BigDecimal coverUsd = amount.multiply(rate);
                                            integration = amount.multiply(new BigDecimal(dictionary.getValue())).setScale(0).longValue();
                                        }
                                        else {
                                            integration = 0L;
                                            this.logger.info("\u83b7\u53d6\u5e01\u79cd\u5bf9usd\u6c47\u7387\u5931\u8d25={}", (Object)result);
                                        }
                                    }
                                    final Member member = this.memberService.findOne(wallet.getMemberId());
                                    final Long totalIntegration = member.getIntegration() + integration;
                                    final MemberGrade grade = this.gradeService.findOne(member.getMemberGradeId());
                                    if (grade.getId() != 5L && grade.getId() != 6L && grade.getGradeBound() < totalIntegration) {
                                        member.setMemberGradeId(Long.valueOf(member.getMemberGradeId() + 1L));
                                    }
                                    member.setIntegration(totalIntegration);
                                    final IntegrationRecord integrationRecord = new IntegrationRecord();
                                    integrationRecord.setAmount(integration);
                                    integrationRecord.setMemberId(member.getId());
                                    integrationRecord.setCreateTime(new Date());
                                    integrationRecord.setType(IntegrationRecordType.COIN_RECHARGE_GIVING);
                                    this.integrationRecordService.save(integrationRecord);
                                }
                                catch (Exception e) {
                                    this.logger.info("\u5e01\u5e01\u5145\u503c\u79ef\u5206\u8d60\u9001\u5931\u8d25={}", (Throwable)e);
                                }
                                this.logger.info("wallet recharge result:{}", (Object)mr);
                            }
                        }
                    }
                }
                catch (Exception e2) {
                    this.logger.info("-----------------------------------------eth\u533a\u5757\u533a\u5757\u4ea4\u6613\u6570\u636e\u83b7\u53d6\u5931\u8d25-------------------------------" + e2);
                    lastSafeBlock = (long)i;
                }
            }
            blockInfo.setLastBlockNum(lastSafeBlock);
            blockInfo.setUpdateTime(new Date());
            this.chainLastBlockService.save(blockInfo);
            this.logger.info("---------------ETH\u5145\u503c\u533a\u5757\u9ad8\u5ea6\u66f4\u65b0\u6210\u529f---------------------");
        }
        this.logger.info("---------------ETH\u5145\u503c\u4efb\u52a1\u7ed3\u675f---------------------");
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
    
    public static void main(final String[] args) {
        final RechargeJob job = new RechargeJob();
        System.out.println(job.getLastBlockNumber());
    }
}

package cn.ztuo.bitrade.controller;


import cn.ztuo.bitrade.annotation.MultiDataSource;
import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.core.Convert;
import cn.ztuo.bitrade.dao.MemberWalletSeHistoryDao;
import cn.ztuo.bitrade.dao.MemberWalletRelationDao;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.entity.transform.AuthMember;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.system.CoinExchangeFactory;
import cn.ztuo.bitrade.util.BigDecimalUtils;
import cn.ztuo.bitrade.util.MessageResult;
import cn.ztuo.bitrade.util.PredicateUtils;
import cn.ztuo.bitrade.vo.OtcWalletVO;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static cn.ztuo.bitrade.constant.SysConstant.SESSION_MEMBER;

@RestController
@RequestMapping("/asset")
@Slf4j
@Api(tags = "用户资金管理")
public class AssetController extends BaseController{
    @Autowired
    private MemberWalletService walletService;
    @Autowired
    private MemberWalletSeHistoryDao walletSeHistoryDao;
    @Autowired
    private MemberWalletRelationDao memberWalletRelationDao;
    @Autowired
    private MemberTransactionService transactionService;
    @Autowired
    private ExchangeCoinService exchangeCoinService;
    @Autowired
    private CoinExchangeFactory coinExchangeFactory;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private LockPositionRecordService lockPositionRecordService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private OtcCoinService otcCoinService;
    @Autowired
    private LocaleMessageSourceService sourceService;
    @Autowired
    private OtcWalletService otcWalletService;
    @Autowired
    private LocalizationExtendService localizationExtendService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private CoinConvertService coinConvertService;
    @Autowired
    private ContractCoinInfoService contractCoinInfoService;
    @Autowired
    private MemberConvertTransactionService convertTransactionService;
    @Autowired
    private ContractDoubleDirectionWalletService contractDoubleDirectionWalletService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private MemberDepositService memberDepositService;
    @Autowired
    private WithdrawRecordService withdrawRecordService;
    @Autowired
    private MemberTransactionService memberTransactionService;


    /**
     * 用户钱包信息
     *
     * @param member
     * @return
     */
    @RequestMapping(value = "wallet",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "用户钱包信息")
    public MessageResult findWallet(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member,String name) {
        String locale = LocaleContextHolder.getLocale().toLanguageTag();
        List<MemberWallet> wallets = walletService.findAllByMemberIdAndCoin(name==null?"":name,member.getId());
        List<MemberWalletRelation> walletRelations;
        if(StringUtils.isNotEmpty(name)){
            walletRelations = memberWalletRelationDao.findAllByMemberIdAndCoinId(member.getId(), name);
        }
        else {
            walletRelations = memberWalletRelationDao.findAllByMemberId(member.getId());
        }
        ImmutableListMultimap<String, MemberWalletRelation> walletRelationsMap = Multimaps.index(walletRelations, MemberWalletRelation::getCoinId);
        List otcUnits = otcCoinService.findAllUnitsByStatus();
        wallets = wallets.stream().filter(wallet -> wallet.getCoin().getStatus() == CommonStatus.NORMAL).collect(Collectors.toList());
        wallets.forEach(wallet -> {
            CoinExchangeFactory.ExchangeRate rate = coinExchangeFactory.get(wallet.getCoin().getUnit());
            // 过滤已禁用币种
             if (rate != null) {
//            if (rate != null && wallet.getCoin().getStatus() == CommonStatus.NORMAL) {
                wallet.getCoin().setUsdRate(rate.getUsdRate());
                wallet.getCoin().setCnyRate(rate.getCnyRate());
                wallet.getCoin().setSgdRate(rate.getSgdRate());
                String cnName = localizationExtendService.getLocaleInfo("Coin", locale, wallet.getCoin().getName(), "name");
                wallet.getCoin().setNameCn(StringUtils.firstNonBlank(cnName, wallet.getCoin().getName()));
                wallet.setExchangeCoinList(exchangeCoinService.getExchangeSymbol(wallet.getCoin().getUnit()));
                wallet.setAddressList(walletRelationsMap.get(wallet.getCoin().getName()));
                if(otcUnits.contains(wallet.getCoin().getUnit())){
                    wallet.setCanOtc(true);
                }
            } else {
                log.info("unit = {} , rate = null ", wallet.getCoin().getUnit());
            }
            if (wallet.getCoin().getName().equals("BTC")) {
                wallet.setBalance(wallet.getBalance().setScale(6, RoundingMode.DOWN));
                wallet.setFrozenBalance(wallet.getFrozenBalance().setScale(6, RoundingMode.DOWN));
            }
            else {
                wallet.setBalance(wallet.getBalance().setScale(4, RoundingMode.DOWN));
                wallet.setFrozenBalance(wallet.getFrozenBalance().setScale(4, RoundingMode.DOWN));
            }
        });
        MessageResult mr = MessageResult.success(messageSource.getMessage("SUCCESS"));
        mr.setData(wallets);
        return mr;
    }

    /**
     * 用户钱包信息
     *
     * @param member
     * @return
     */
    @RequestMapping(value = "total",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "总资产折合")
    public MessageResult total(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member) {
        Map map = new HashMap();
        BigDecimal walletTotal = BigDecimal.ZERO;
        BigDecimal otcTotal = BigDecimal.ZERO;
        List<MemberWallet> wallets = walletService.findAllByMemberId(member.getId());
        List<OtcWallet> results = otcWalletService.findByMemberId(member.getId());
        for(MemberWallet wallet : wallets){
            CoinExchangeFactory.ExchangeRate rate = coinExchangeFactory.get(wallet.getCoin().getUnit());
            if(wallet.getBalance().compareTo(BigDecimal.ZERO) > 0 && rate.getUsdRate() != null) {
                walletTotal = walletTotal.add(wallet.getBalance().multiply(rate.getUsdRate()));
            }
        }
        for(OtcWallet result : results){
            CoinExchangeFactory.ExchangeRate rate = coinExchangeFactory.get(result.getCoin().getUnit());
            if(result.getBalance().compareTo(BigDecimal.ZERO) > 0 && rate.getUsdRate() != null) {
                otcTotal = otcTotal.add(result.getBalance().multiply(rate.getUsdRate()));
            }
        }
        map.put("walletTotal",walletTotal);
        map.put("otcTotal",otcTotal);
        map.put("total",walletTotal.add(otcTotal));
        map.put("rate",coinExchangeFactory.get("USDT"));
        MessageResult mr = MessageResult.success(messageSource.getMessage("SUCCESS"));
        mr.setData(map);
        return mr;
    }

    /**
     * 查询特定类型的记录
     *
     * @param member
     * @param pageNo
     * @param pageSize
     * @param type
     * @return
     */
    @RequestMapping(value = "transaction",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "查询特定类型的记录")
    @MultiDataSource(name = "second")
    public MessageResult findTransaction(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member, int pageNo, int pageSize, String type,String symbol,
                                        String startTime, String endTime) throws Exception{
        List<TransactionType> typeList = null;
        if(StringUtils.isNotEmpty(type)) {
            typeList = Arrays.stream(type.split(",")).map(item -> TransactionType.valueOfOrdinal(Integer.parseInt(item))).collect(Collectors.toList());
        }
        Page page = transactionService.queryByMember(member.getId(), pageNo, pageSize, typeList ,startTime,endTime,symbol);
        return success(page);
    }

    /**
     * 查询所有记录
     *
     * @param member
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "transaction/all",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "查询所有记录")
    @MultiDataSource(name = "second")
    public MessageResult findTransaction(
            @ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member,
            HttpServletRequest request,
            int pageNo,
            int pageSize,
            @RequestParam(value = "symbol",required = false) String symbol) throws ParseException {
        TransactionType type = null;
        if (StringUtils.isNotEmpty(request.getParameter("type"))) {
            type = TransactionType.valueOfOrdinal(Convert.strToInt(request.getParameter("type"), 0));
        }
        String startDate = "";
        String endDate = "";
        if (StringUtils.isNotEmpty(request.getParameter("dateRange"))) {
            String[] parts = request.getParameter("dateRange").split("~");
            startDate = parts[0].trim();
            endDate = parts[1].trim();
        }
        return success(transactionService.queryByMember(member.getId(), pageNo, pageSize, type, startDate, endDate,symbol));
    }

    @RequestMapping(value = "wallet/{symbol}",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "查询钱包地址和余额")
    @MultiDataSource(name = "second")
    public MessageResult findWalletBySymbol(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member, @PathVariable String symbol) {
        MessageResult mr = MessageResult.success(messageSource.getMessage("SUCCESS"));
        MemberWallet memberWallet = walletService.findByCoinUnitAndMemberId(symbol, member.getId());
        List<MemberWalletRelation> walletRelations = memberWalletRelationDao.findAllByMemberIdAndCoinId(member.getId(), symbol);
        ImmutableListMultimap<String, MemberWalletRelation> walletRelationsMap = Multimaps.index(walletRelations, MemberWalletRelation::getCoinId);
        memberWallet.setAddressList(walletRelationsMap.get(memberWallet.getCoin().getName()));
        mr.setData(memberWallet);
        return mr;
    }

    @RequestMapping(value = "wallet/reset-address",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "重置地址")
    public MessageResult resetWalletAddress(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member, String unit) {
        try {
            JSONObject json = new JSONObject();
            json.put("uid", member.getId());
            kafkaTemplate.send("reset-member-address", unit, json.toJSONString());
            return MessageResult.success(messageSource.getMessage("SUCCESS"));
        } catch (Exception e) {
            return MessageResult.error(messageSource.getMessage("UNKNOWN.ERROR"));
        }
    }

    @PostMapping("lock-position")
    @ApiOperation(value = "锁仓记录")
    public MessageResult lockPositionRecordList(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member,
                                                @RequestParam(value = "status",required = false) CommonStatus status,
                                                @RequestParam(value = "coinUnit",required = false)String coinUnit,
                                                PageModel pageModel){
        ArrayList<BooleanExpression> booleanExpressions = new ArrayList<>();
        if(status!=null){
            booleanExpressions.add(QLockPositionRecord.lockPositionRecord.status.eq(status));
        }
        if(coinUnit!=null){
            Coin coin=coinService.findByUnit(coinUnit);
            if(coin==null){
                return MessageResult.error(sourceService.getMessage("COIN_ILLEGAL"));
            }
            booleanExpressions.add(QLockPositionRecord.lockPositionRecord.coin.eq(coin));
        }
        booleanExpressions.add(QLockPositionRecord.lockPositionRecord.memberId.eq(member.getId()));
        Predicate predicate=PredicateUtils.getPredicate(booleanExpressions);
        Page<LockPositionRecord> lockPositionRecordList=lockPositionRecordService.findAll(predicate,pageModel);
        MessageResult result=MessageResult.success();
        result.setData(lockPositionRecordList);
        return result;
    }

    @PostMapping("se-history")
    @ApiOperation(value = "SE持仓记录")
    public MessageResult seHistory(@ApiIgnore @SessionAttribute(SESSION_MEMBER) AuthMember member,
                                   PageModel pageModel){
        ArrayList<BooleanExpression> booleanExpressions = new ArrayList<>();
        booleanExpressions.add(QMemberWalletSeHistory.memberWalletSeHistory.memberId.eq(member.getId()));
        Predicate predicate=PredicateUtils.getPredicate(booleanExpressions);
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "createTime"));
        Pageable pageable = PageRequest.of(pageModel.getPageNo() - 1, pageModel.getPageSize(), sort);
        Page<MemberWalletSeHistory> memberWalletSeHistoryList = walletSeHistoryDao.findAll(predicate,pageable);
        MessageResult result=MessageResult.success();
        result.setData(memberWalletSeHistoryList);
        return result;
    }



    @RequestMapping({ "convertTransaction" })
    public MessageResult convertTransaction(@SessionAttribute("API_MEMBER") final AuthMember member, final String baseCoin, final String convertCoin, final Double amount) {
        final MessageResult mr = MessageResult.success("success");
        if (amount <= 0.0) {
            mr.setCode(4);
            mr.setMessage("illegal parameter");
            return mr;
        }
        final Coin rateBase = this.coinService.findOne(baseCoin);
        final Coin rateConvert = this.coinService.findOne(convertCoin);
        if (rateBase == null || rateConvert == null || rateConvert.getUsdRate().doubleValue() == 0.0) {
            mr.setCode(6);
            mr.setMessage("get rate falure");
            return mr;
        }
        final double rate = rateBase.getUsdRate().divide(rateConvert.getUsdRate(), 8, 1).doubleValue();
        final MemberWallet baseWallet = walletService.findByCoinAndMemberId(rateBase, Long.valueOf(member.getId()));
        final MemberWallet convertWallet = walletService.findByCoinAndMemberId(rateConvert, Long.valueOf(member.getId()));
        if (baseWallet == null || convertWallet == null) {
            mr.setCode(4);
            mr.setMessage("wallet not found");
            return mr;
        }
        if (baseWallet.getBalance().doubleValue() < amount) {
            mr.setCode(5);
            mr.setMessage("balance not enough");
            return mr;
        }
        final CoinConvert coinConvert = this.coinConvertService.findByBaseAndConvert(baseCoin, convertCoin);
        if (coinConvert == null) {
            mr.setCode(7);
            mr.setMessage("get fee falure");
            return mr;
        }
        walletService.decreaseBalance(baseWallet.getId(), new BigDecimal(amount), baseWallet.getVersion());
        final double changeAmount = (amount - amount * coinConvert.getFee()) * rate;
        walletService.increaseBalance(convertWallet.getId(), new BigDecimal(changeAmount), convertWallet.getVersion());
        final MemberConvertTransaction convertTransaction = new MemberConvertTransaction();
        convertTransaction.setMemberId(baseWallet.getMemberId());
        convertTransaction.setBaseCoin(baseCoin);
        convertTransaction.setConvertCoin(convertCoin);
        convertTransaction.setAmount(amount);
        convertTransaction.setConvertAmount(Double.valueOf(changeAmount));
        convertTransaction.setFeeAmount(Double.valueOf(amount * coinConvert.getFee()));
        convertTransaction.setRate(Double.valueOf(rate));
        convertTransaction.setCreateTime(new Date());
        convertTransaction.setSequence(Long.valueOf(System.currentTimeMillis()));
        this.convertTransactionService.save(convertTransaction);
        return mr;
    }

    @RequestMapping({ "convertTransactionLog" })
    public MessageResult convertTransactionLog(@SessionAttribute("API_MEMBER") final AuthMember member, final Long sequence) {
        final List<MemberConvertTransaction> datas = (List<MemberConvertTransaction>)this.convertTransactionService.findListBySequence(Long.valueOf(member.getId()), sequence);
        final MessageResult mr = MessageResult.success("success");
        mr.setData((Object)datas);
        return mr;
    }

    @RequestMapping(value = { "transferCoins" }, method = { RequestMethod.POST })
    public MessageResult transferCoins(final int type) throws Exception {
        if (type != 0) {
            return this.error("参数错误");
        }
        final List<ContractCoinInfo> contractCoins = (List<ContractCoinInfo>)this.contractCoinInfoService.getNormalCoin();
        if (contractCoins == null || contractCoins.size() == 0) {
            return this.error("暂无数据");
        }
        final List<TransferCoinResult> transferCoins = new ArrayList<TransferCoinResult>();
        for (final ContractCoinInfo contractCoin : contractCoins) {
            final Coin coin = this.coinService.findOne(contractCoin.getName());
            if (coin != null) {
                TransferCoinResult transferCoin = new TransferCoinResult();
                transferCoin.setName(contractCoin.getName());
                transferCoin.setUnit(contractCoin.getUnit());
                transferCoin.setImg(coin.getImg());
                transferCoins.add(transferCoin);
            }
        }
        final MessageResult mr = MessageResult.success("success");
        mr.setData((Object)transferCoins);
        return mr;
    }

    @RequestMapping(value = { "transfer" }, method = { RequestMethod.POST })
    public MessageResult transferWallet(@SessionAttribute("API_MEMBER") final AuthMember user, final OtcWalletVO otcWalletVO) throws Exception {
        if ("0".equals(otcWalletVO.getDirection()) || "1".equals(otcWalletVO.getDirection())) {
            AssetController.log.info("---------币币账户到法币账户互转:userId=" + user.getId() + "," + JSONObject.toJSONString((Object)otcWalletVO));
            final OtcCoin coin = this.otcCoinService.findByUnit(otcWalletVO.getCoinName());
            if (coin == null) {
                return this.error("不支持的法币币种");
            }
            final BigDecimal amount = otcWalletVO.getAmount().setScale(coin.getCoinScale(), 1);
            final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
            Assert.isTrue(member.getMemberLevel() != MemberLevelEnum.GENERAL, "请先进行实名认证!");
            Assert.isTrue(BigDecimalUtils.compare(amount, BigDecimal.ZERO), this.sourceService.getMessage("参数异常"));
            final Coin memberCoin = this.coinService.findByUnit(coin.getUnit());
            final MemberWallet memberWallet = walletService.findByCoinAndMemberId(memberCoin, Long.valueOf(user.getId()));
            Assert.isTrue(memberWallet.getIsLock() == BooleanEnum.IS_FALSE, "钱包已锁定");
            OtcWallet otcWallet = this.otcWalletService.findByOtcCoinAndMemberId(member.getId(), coin);
            if (otcWallet == null) {
                final OtcWallet otcWalletNew = new OtcWallet();
                otcWalletNew.setCoin(memberCoin);
                otcWalletNew.setIsLock(Integer.valueOf(0));
                otcWalletNew.setMemberId(member.getId());
                otcWalletNew.setBalance(BigDecimal.ZERO);
                otcWalletNew.setFrozenBalance(BigDecimal.ZERO);
                otcWalletNew.setReleaseBalance(BigDecimal.ZERO);
                otcWalletNew.setVersion(Integer.valueOf(0));
                otcWallet = this.otcWalletService.save(otcWalletNew);
                if (otcWallet == null) {
                    return this.error("法币账户创建失败，请联系客服");
                }
            }
            Assert.isTrue(otcWallet.getIsLock() == 0, "钱包已锁定");
            if ("0".equals(otcWalletVO.getDirection())) {
                Assert.isTrue(BigDecimalUtils.compare(memberWallet.getBalance(), amount), this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
                final int subResult = this.otcWalletService.coin2Otc(memberWallet, otcWallet, amount);
                if (subResult == 1) {
                    return this.success("划转成功");
                }
                return this.error("划转失败");
            }
            else {
                if (!"1".equals(otcWalletVO.getDirection())) {
                    return this.error("参数异常");
                }
                Assert.isTrue(BigDecimalUtils.compare(otcWallet.getBalance(), amount), this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
                final int addResult = this.otcWalletService.otc2Coin(memberWallet, otcWallet, amount);
                if (addResult == 1) {
                    return this.success("划转成功");
                }
                return this.error("划转失败");
            }
        }
        else if ("2".equals(otcWalletVO.getDirection()) || "3".equals(otcWalletVO.getDirection())) {
            AssetController.log.info("---------币币账户到合约账户互转:userId=" + user.getId() + "," + JSONObject.toJSONString((Object)otcWalletVO));
            if (!"USDT".equals(otcWalletVO.getCoinName())) {
                return this.error("不支持的合约币种");
            }
            final BigDecimal amount2 = otcWalletVO.getAmount();
            final Member member2 = this.memberService.findOne(Long.valueOf(user.getId()));
            Assert.isTrue(member2.getMemberLevel() != MemberLevelEnum.GENERAL, "请先进行实名认证!");
            Assert.isTrue(BigDecimalUtils.compare(amount2, BigDecimal.ZERO), this.sourceService.getMessage("参数异常"));
            final Coin memberCoin2 = new Coin();
            memberCoin2.setName(otcWalletVO.getCoinName());
            final MemberWallet memberWallet2 = walletService.findByCoinAndMemberId(memberCoin2, Long.valueOf(user.getId()));
            Assert.isTrue(memberWallet2.getIsLock() == BooleanEnum.IS_FALSE, "钱包已锁定");
            ContractWallet contractWallet = this.contractWalletService.findByMemberIdAndCoin(user.getId(), otcWalletVO.getCoinName());
            if (contractWallet == null) {
                final ContractWallet contractWalletNew = new ContractWallet();
                contractWalletNew.setCoin(memberCoin2);
                contractWalletNew.setMember(member2);
                contractWallet = this.contractWalletService.save(contractWalletNew);
                if (contractWallet == null) {
                    return this.error("合约账户创建失败，请联系客服");
                }
            }
            Assert.isTrue(contractWallet.getIs_lock() == 0, "钱包已锁定");
            if ("2".equals(otcWalletVO.getDirection())) {
                Assert.isTrue(BigDecimalUtils.compare(memberWallet2.getBalance(), amount2), this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
                final int subResult2 = this.contractWalletService.coin2Contract(memberWallet2, contractWallet, amount2);
                if (subResult2 == 1) {
                    return this.success("划转成功");
                }
                return this.error("划转失败");
            }
            else {
                if (!"3".equals(otcWalletVO.getDirection())) {
                    return this.error("参数异常");
                }
                final long holdingCount = this.contractOrderService.countMemberHoldingOrders(Long.valueOf(user.getId()));
                if (holdingCount > 0L) {
                    return this.error("存在持仓中的合约订单，不允许划转");
                }
                Assert.isTrue(BigDecimalUtils.compare(contractWallet.getBalance(), amount2), this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
                final int addResult2 = this.contractWalletService.Contract2Coin(memberWallet2, contractWallet, amount2);
                if (addResult2 == 1) {
                    return this.success("划转成功");
                }
                return this.error("划转失败");
            }
        }
        else if ("4".equals(otcWalletVO.getDirection())) {
            AssetController.log.info("---------币币账户到币币锁仓账户:userId=" + user.getId() + "," + JSONObject.toJSONString((Object)otcWalletVO));
            final Coin coin2 = this.coinService.findByUnit(otcWalletVO.getCoinName());
            if (null == coin2 || coin2.getCoinArea() != 1) {
                return this.error("不支持的解封区币种");
            }
            final BigDecimal amount = otcWalletVO.getAmount();
            final Member member = this.memberService.findOne(Long.valueOf(user.getId()));
            Assert.isTrue(member.getMemberLevel() != MemberLevelEnum.GENERAL, "请先进行实名认证!");
            Assert.isTrue(BigDecimalUtils.compare(amount, BigDecimal.ZERO), this.sourceService.getMessage("参数异常"));
            final Coin memberCoin = new Coin();
            memberCoin.setName(otcWalletVO.getCoinName());
            final MemberWallet memberWallet = walletService.findByCoinAndMemberId(memberCoin, Long.valueOf(user.getId()));
            Assert.isTrue(memberWallet.getIsLock() == BooleanEnum.IS_FALSE, "钱包已锁定");
            Assert.isTrue(BigDecimalUtils.compare(memberWallet.getBalance(), amount), this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
            final BigDecimal leftAmount = this.getCoinAmount(user.getId(), otcWalletVO.getCoinName());
            if (leftAmount.compareTo(BigDecimal.ZERO) <= 0 || amount.compareTo(leftAmount) > 0) {
                return MessageResult.error(this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
            }
            final int addResult = walletService.Balance2BlockBalance(memberWallet, amount);
            if (addResult == 1) {
                return this.success("划转成功");
            }
            return this.error("划转失败");
        }
        else {
            if (!"5".equals(otcWalletVO.getDirection()) && !"6".equals(otcWalletVO.getDirection())) {
                return this.error("参数异常");
            }
            AssetController.log.info("---------币币账户到双仓合约账户互转:userId=" + user.getId() + "," + JSONObject.toJSONString((Object)otcWalletVO));
            if (!"USDT".equals(otcWalletVO.getCoinName())) {
                return this.error("不支持的合约币种");
            }
            final BigDecimal amount2 = otcWalletVO.getAmount();
            final Member member2 = this.memberService.findOne(Long.valueOf(user.getId()));
            Assert.isTrue(member2.getMemberLevel() != MemberLevelEnum.GENERAL, "请先进行实名认证!");
            Assert.isTrue(BigDecimalUtils.compare(amount2, BigDecimal.ZERO), this.sourceService.getMessage("参数异常"));
            final Coin memberCoin2 = new Coin();
            memberCoin2.setName(otcWalletVO.getCoinName());
            final MemberWallet memberWallet2 = walletService.findByCoinAndMemberId(memberCoin2, Long.valueOf(user.getId()));
            Assert.isTrue(memberWallet2.getIsLock() == BooleanEnum.IS_FALSE, "钱包已锁定");
            ContractDoubleDirectionWallet contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(member2.getId(), otcWalletVO.getCoinName());
            if (contractDoubleDirectionWallet == null) {
                final ContractDoubleDirectionWallet contractDoubleDirectionWalletNew = new ContractDoubleDirectionWallet();
                contractDoubleDirectionWalletNew.setCoin(memberCoin2);
                contractDoubleDirectionWalletNew.setIsLock(BooleanEnum.IS_FALSE);
                contractDoubleDirectionWalletNew.setMemberId(member2.getId());
                contractDoubleDirectionWalletNew.setBalance(BigDecimal.ZERO);
                contractDoubleDirectionWalletNew.setFrozenBalance(BigDecimal.ZERO);
                contractDoubleDirectionWalletNew.setVersion(0);
                contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.save(contractDoubleDirectionWalletNew);
                if (contractDoubleDirectionWallet == null) {
                    return this.error("双仓合约账户创建失败，请联系客服");
                }
            }
            Assert.isTrue(contractDoubleDirectionWallet.getIsLock() == BooleanEnum.IS_FALSE, "钱包已锁定");
            if ("5".equals(otcWalletVO.getDirection())) {
                Assert.isTrue(BigDecimalUtils.compare(memberWallet2.getBalance(), amount2), this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
                final int subResult2 = this.contractDoubleDirectionWalletService.coin2DoubleDirection(memberWallet2, contractDoubleDirectionWallet, amount2);
                if (subResult2 == 1) {
                    return this.success("划转成功");
                }
                return this.error("划转失败");
            }
            else {
                if (!"6".equals(otcWalletVO.getDirection())) {
                    return this.error("参数异常");
                }
                Assert.isTrue(BigDecimalUtils.compare(contractDoubleDirectionWallet.getBalance(), amount2), this.sourceService.getMessage("INSUFFICIENT_BALANCE"));
                final int addResult3 = this.contractDoubleDirectionWalletService.doubleDirection2Coin(memberWallet2, contractDoubleDirectionWallet, amount2);
                if (addResult3 == 1) {
                    return this.success("划转成功");
                }
                return this.error("划转失败");
            }
        }
    }

    private BigDecimal getCoinAmount(final long memberId, final String baseCoin) {
        BigDecimal rechargeAmount = this.memberDepositService.sumMemberDepositByUnit(memberId, baseCoin);
        rechargeAmount = ((null != rechargeAmount) ? rechargeAmount : BigDecimal.ZERO);
        BigDecimal withdrawAmount = this.withdrawRecordService.sumMemberWithdrawByUnit(memberId, baseCoin);
        withdrawAmount = ((null != withdrawAmount) ? withdrawAmount : BigDecimal.ZERO);
        BigDecimal transferAmount = this.memberTransactionService.sumByTransactionType(memberId, baseCoin, TransactionType.COIN_TWO_COIN_BLOCK);
        transferAmount = ((null != transferAmount) ? transferAmount : BigDecimal.ZERO);
        transferAmount = transferAmount.multiply(new BigDecimal("-1"));
        BigDecimal manualRecharge = this.memberTransactionService.sumByTransactionType(memberId, baseCoin, TransactionType.ADMIN_RECHARGE);
        manualRecharge = ((null != manualRecharge) ? manualRecharge : BigDecimal.ZERO);
        if (manualRecharge.compareTo(BigDecimal.ZERO) < 0) {
            manualRecharge = BigDecimal.ZERO;
        }
        BigDecimal rechargeLeftAmount = rechargeAmount.add(manualRecharge).subtract(withdrawAmount).subtract(transferAmount);
        if (rechargeLeftAmount.compareTo(BigDecimal.ZERO) <= 0) {
            rechargeLeftAmount = BigDecimal.ZERO;
        }
        return rechargeLeftAmount;
    }

    @RequestMapping(value = { "transferLog" }, method = { RequestMethod.POST })
    public MessageResult transferWallet(@SessionAttribute("API_MEMBER") final AuthMember user, final Long sequence) throws Exception {
        final MessageResult mr = MessageResult.success("success");
        final List<MemberTransaction> datas = (List<MemberTransaction>)this.memberTransactionService.findListBySequence(Long.valueOf(user.getId()), sequence);
        mr.setData((Object)datas);
        return mr;
    }

}

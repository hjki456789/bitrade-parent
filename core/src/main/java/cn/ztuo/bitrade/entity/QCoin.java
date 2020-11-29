package cn.ztuo.bitrade.entity;

import java.math.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;

public class QCoin extends EntityPathBase<Coin>
{
    private static final long serialVersionUID = 1696734580L;
    public static final QCoin coin;
    public final EnumPath<BooleanEnum> canAutoWithdraw;
    public final EnumPath<BooleanEnum> canRecharge;
    public final EnumPath<BooleanEnum> canTransfer;
    public final EnumPath<BooleanEnum> canWithdraw;
    public final NumberPath<Integer> chainType;
    public final NumberPath<BigDecimal> cnyRate;
    public final NumberPath<Integer> coinArea;
    public final StringPath coldWalletAddress;
    public final StringPath contractAddress;
    public final NumberPath<Integer> enablePool;
    public final EnumPath<BooleanEnum> enableRpc;
    public final BooleanPath hasLegal;
    public final StringPath img;
    public final EnumPath<BooleanEnum> isPlatformCoin;
    public final StringPath masterAddress;
    public final NumberPath<BigDecimal> maxDailyWithdrawRate;
    public final NumberPath<Double> maxTxFee;
    public final NumberPath<BigDecimal> maxWithdrawAmount;
    public final NumberPath<BigDecimal> minerFee;
    public final NumberPath<BigDecimal> minRechargeAmount;
    public final NumberPath<Double> minTxFee;
    public final NumberPath<BigDecimal> minWithdrawAmount;
    public final StringPath name;
    public final StringPath nameCn;
    public final NumberPath<BigDecimal> sgdRate;
    public final StringPath sort;
    public final EnumPath<CommonStatus> status;
    public final StringPath unit;
    public final NumberPath<BigDecimal> usdRate;
    public final NumberPath<Integer> withdrawScale;
    public final NumberPath<BigDecimal> withdrawThreshold;
    
    public QCoin(final String variable) {
        super((Class)Coin.class, PathMetadataFactory.forVariable(variable));
        this.canAutoWithdraw = (EnumPath<BooleanEnum>)this.createEnum("canAutoWithdraw", (Class)BooleanEnum.class);
        this.canRecharge = (EnumPath<BooleanEnum>)this.createEnum("canRecharge", (Class)BooleanEnum.class);
        this.canTransfer = (EnumPath<BooleanEnum>)this.createEnum("canTransfer", (Class)BooleanEnum.class);
        this.canWithdraw = (EnumPath<BooleanEnum>)this.createEnum("canWithdraw", (Class)BooleanEnum.class);
        this.chainType = (NumberPath<Integer>)this.createNumber("chainType", (Class)Integer.class);
        this.cnyRate = (NumberPath<BigDecimal>)this.createNumber("cnyRate", (Class)BigDecimal.class);
        this.coinArea = (NumberPath<Integer>)this.createNumber("coinArea", (Class)Integer.class);
        this.coldWalletAddress = this.createString("coldWalletAddress");
        this.contractAddress = this.createString("contractAddress");
        this.enablePool = (NumberPath<Integer>)this.createNumber("enablePool", (Class)Integer.class);
        this.enableRpc = (EnumPath<BooleanEnum>)this.createEnum("enableRpc", (Class)BooleanEnum.class);
        this.hasLegal = this.createBoolean("hasLegal");
        this.img = this.createString("img");
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.masterAddress = this.createString("masterAddress");
        this.maxDailyWithdrawRate = (NumberPath<BigDecimal>)this.createNumber("maxDailyWithdrawRate", (Class)BigDecimal.class);
        this.maxTxFee = (NumberPath<Double>)this.createNumber("maxTxFee", (Class)Double.class);
        this.maxWithdrawAmount = (NumberPath<BigDecimal>)this.createNumber("maxWithdrawAmount", (Class)BigDecimal.class);
        this.minerFee = (NumberPath<BigDecimal>)this.createNumber("minerFee", (Class)BigDecimal.class);
        this.minRechargeAmount = (NumberPath<BigDecimal>)this.createNumber("minRechargeAmount", (Class)BigDecimal.class);
        this.minTxFee = (NumberPath<Double>)this.createNumber("minTxFee", (Class)Double.class);
        this.minWithdrawAmount = (NumberPath<BigDecimal>)this.createNumber("minWithdrawAmount", (Class)BigDecimal.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sgdRate = (NumberPath<BigDecimal>)this.createNumber("sgdRate", (Class)BigDecimal.class);
        this.sort = this.createString("sort");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
        this.usdRate = (NumberPath<BigDecimal>)this.createNumber("usdRate", (Class)BigDecimal.class);
        this.withdrawScale = (NumberPath<Integer>)this.createNumber("withdrawScale", (Class)Integer.class);
        this.withdrawThreshold = (NumberPath<BigDecimal>)this.createNumber("withdrawThreshold", (Class)BigDecimal.class);
    }
    
    public QCoin(final Path<? extends Coin> path) {
        super(path.getType(), path.getMetadata());
        this.canAutoWithdraw = (EnumPath<BooleanEnum>)this.createEnum("canAutoWithdraw", (Class)BooleanEnum.class);
        this.canRecharge = (EnumPath<BooleanEnum>)this.createEnum("canRecharge", (Class)BooleanEnum.class);
        this.canTransfer = (EnumPath<BooleanEnum>)this.createEnum("canTransfer", (Class)BooleanEnum.class);
        this.canWithdraw = (EnumPath<BooleanEnum>)this.createEnum("canWithdraw", (Class)BooleanEnum.class);
        this.chainType = (NumberPath<Integer>)this.createNumber("chainType", (Class)Integer.class);
        this.cnyRate = (NumberPath<BigDecimal>)this.createNumber("cnyRate", (Class)BigDecimal.class);
        this.coinArea = (NumberPath<Integer>)this.createNumber("coinArea", (Class)Integer.class);
        this.coldWalletAddress = this.createString("coldWalletAddress");
        this.contractAddress = this.createString("contractAddress");
        this.enablePool = (NumberPath<Integer>)this.createNumber("enablePool", (Class)Integer.class);
        this.enableRpc = (EnumPath<BooleanEnum>)this.createEnum("enableRpc", (Class)BooleanEnum.class);
        this.hasLegal = this.createBoolean("hasLegal");
        this.img = this.createString("img");
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.masterAddress = this.createString("masterAddress");
        this.maxDailyWithdrawRate = (NumberPath<BigDecimal>)this.createNumber("maxDailyWithdrawRate", (Class)BigDecimal.class);
        this.maxTxFee = (NumberPath<Double>)this.createNumber("maxTxFee", (Class)Double.class);
        this.maxWithdrawAmount = (NumberPath<BigDecimal>)this.createNumber("maxWithdrawAmount", (Class)BigDecimal.class);
        this.minerFee = (NumberPath<BigDecimal>)this.createNumber("minerFee", (Class)BigDecimal.class);
        this.minRechargeAmount = (NumberPath<BigDecimal>)this.createNumber("minRechargeAmount", (Class)BigDecimal.class);
        this.minTxFee = (NumberPath<Double>)this.createNumber("minTxFee", (Class)Double.class);
        this.minWithdrawAmount = (NumberPath<BigDecimal>)this.createNumber("minWithdrawAmount", (Class)BigDecimal.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sgdRate = (NumberPath<BigDecimal>)this.createNumber("sgdRate", (Class)BigDecimal.class);
        this.sort = this.createString("sort");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
        this.usdRate = (NumberPath<BigDecimal>)this.createNumber("usdRate", (Class)BigDecimal.class);
        this.withdrawScale = (NumberPath<Integer>)this.createNumber("withdrawScale", (Class)Integer.class);
        this.withdrawThreshold = (NumberPath<BigDecimal>)this.createNumber("withdrawThreshold", (Class)BigDecimal.class);
    }
    
    public QCoin(final PathMetadata metadata) {
        super((Class)Coin.class, metadata);
        this.canAutoWithdraw = (EnumPath<BooleanEnum>)this.createEnum("canAutoWithdraw", (Class)BooleanEnum.class);
        this.canRecharge = (EnumPath<BooleanEnum>)this.createEnum("canRecharge", (Class)BooleanEnum.class);
        this.canTransfer = (EnumPath<BooleanEnum>)this.createEnum("canTransfer", (Class)BooleanEnum.class);
        this.canWithdraw = (EnumPath<BooleanEnum>)this.createEnum("canWithdraw", (Class)BooleanEnum.class);
        this.chainType = (NumberPath<Integer>)this.createNumber("chainType", (Class)Integer.class);
        this.cnyRate = (NumberPath<BigDecimal>)this.createNumber("cnyRate", (Class)BigDecimal.class);
        this.coinArea = (NumberPath<Integer>)this.createNumber("coinArea", (Class)Integer.class);
        this.coldWalletAddress = this.createString("coldWalletAddress");
        this.contractAddress = this.createString("contractAddress");
        this.enablePool = (NumberPath<Integer>)this.createNumber("enablePool", (Class)Integer.class);
        this.enableRpc = (EnumPath<BooleanEnum>)this.createEnum("enableRpc", (Class)BooleanEnum.class);
        this.hasLegal = this.createBoolean("hasLegal");
        this.img = this.createString("img");
        this.isPlatformCoin = (EnumPath<BooleanEnum>)this.createEnum("isPlatformCoin", (Class)BooleanEnum.class);
        this.masterAddress = this.createString("masterAddress");
        this.maxDailyWithdrawRate = (NumberPath<BigDecimal>)this.createNumber("maxDailyWithdrawRate", (Class)BigDecimal.class);
        this.maxTxFee = (NumberPath<Double>)this.createNumber("maxTxFee", (Class)Double.class);
        this.maxWithdrawAmount = (NumberPath<BigDecimal>)this.createNumber("maxWithdrawAmount", (Class)BigDecimal.class);
        this.minerFee = (NumberPath<BigDecimal>)this.createNumber("minerFee", (Class)BigDecimal.class);
        this.minRechargeAmount = (NumberPath<BigDecimal>)this.createNumber("minRechargeAmount", (Class)BigDecimal.class);
        this.minTxFee = (NumberPath<Double>)this.createNumber("minTxFee", (Class)Double.class);
        this.minWithdrawAmount = (NumberPath<BigDecimal>)this.createNumber("minWithdrawAmount", (Class)BigDecimal.class);
        this.name = this.createString("name");
        this.nameCn = this.createString("nameCn");
        this.sgdRate = (NumberPath<BigDecimal>)this.createNumber("sgdRate", (Class)BigDecimal.class);
        this.sort = this.createString("sort");
        this.status = (EnumPath<CommonStatus>)this.createEnum("status", (Class)CommonStatus.class);
        this.unit = this.createString("unit");
        this.usdRate = (NumberPath<BigDecimal>)this.createNumber("usdRate", (Class)BigDecimal.class);
        this.withdrawScale = (NumberPath<Integer>)this.createNumber("withdrawScale", (Class)Integer.class);
        this.withdrawThreshold = (NumberPath<BigDecimal>)this.createNumber("withdrawThreshold", (Class)BigDecimal.class);
    }
    
    static {
        coin = new QCoin("coin");
    }
}

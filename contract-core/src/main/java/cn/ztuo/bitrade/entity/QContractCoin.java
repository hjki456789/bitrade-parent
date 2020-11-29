package cn.ztuo.bitrade.entity;

import com.querydsl.core.types.dsl.*;
import java.math.*;
import com.querydsl.core.types.*;

public class QContractCoin extends EntityPathBase<ContractCoin>
{
    private static final long serialVersionUID = 1320564390L;
    public static final QContractCoin contractCoin;
    public final StringPath baseSymbol;
    public final NumberPath<Integer> canBuyDown;
    public final NumberPath<Integer> canBuyUp;
    public final NumberPath<BigDecimal> closeFee;
    public final StringPath coinSymbol;
    public final StringPath coinSymbolImageUrl;
    public final StringPath coinSymbolName;
    public final NumberPath<Integer> coinSymbolPrecision;
    public final NumberPath<Integer> contractType;
    public final NumberPath<BigDecimal> dayFeeRate;
    public final NumberPath<Integer> enable;
    public final NumberPath<BigDecimal> fallInterval;
    public final StringPath id;
    public final StringPath leverMultiple;
    public final NumberPath<BigDecimal> maxHoldLimit;
    public final NumberPath<BigDecimal> maxLimit;
    public final NumberPath<BigDecimal> minLimit;
    public final NumberPath<BigDecimal> openFee;
    public final NumberPath<BigDecimal> riseInterval;
    public final NumberPath<Integer> sort;
    public final StringPath symbol;
    
    public QContractCoin(final String variable) {
        super((Class)ContractCoin.class, PathMetadataFactory.forVariable(variable));
        this.baseSymbol = this.createString("baseSymbol");
        this.canBuyDown = (NumberPath<Integer>)this.createNumber("canBuyDown", (Class)Integer.class);
        this.canBuyUp = (NumberPath<Integer>)this.createNumber("canBuyUp", (Class)Integer.class);
        this.closeFee = (NumberPath<BigDecimal>)this.createNumber("closeFee", (Class)BigDecimal.class);
        this.coinSymbol = this.createString("coinSymbol");
        this.coinSymbolImageUrl = this.createString("coinSymbolImageUrl");
        this.coinSymbolName = this.createString("coinSymbolName");
        this.coinSymbolPrecision = (NumberPath<Integer>)this.createNumber("coinSymbolPrecision", (Class)Integer.class);
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.dayFeeRate = (NumberPath<BigDecimal>)this.createNumber("dayFeeRate", (Class)BigDecimal.class);
        this.enable = (NumberPath<Integer>)this.createNumber("enable", (Class)Integer.class);
        this.fallInterval = (NumberPath<BigDecimal>)this.createNumber("fallInterval", (Class)BigDecimal.class);
        this.id = this.createString("id");
        this.leverMultiple = this.createString("leverMultiple");
        this.maxHoldLimit = (NumberPath<BigDecimal>)this.createNumber("maxHoldLimit", (Class)BigDecimal.class);
        this.maxLimit = (NumberPath<BigDecimal>)this.createNumber("maxLimit", (Class)BigDecimal.class);
        this.minLimit = (NumberPath<BigDecimal>)this.createNumber("minLimit", (Class)BigDecimal.class);
        this.openFee = (NumberPath<BigDecimal>)this.createNumber("openFee", (Class)BigDecimal.class);
        this.riseInterval = (NumberPath<BigDecimal>)this.createNumber("riseInterval", (Class)BigDecimal.class);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.symbol = this.createString("symbol");
    }
    
    public QContractCoin(final Path<? extends ContractCoin> path) {
        super(path.getType(), path.getMetadata());
        this.baseSymbol = this.createString("baseSymbol");
        this.canBuyDown = (NumberPath<Integer>)this.createNumber("canBuyDown", (Class)Integer.class);
        this.canBuyUp = (NumberPath<Integer>)this.createNumber("canBuyUp", (Class)Integer.class);
        this.closeFee = (NumberPath<BigDecimal>)this.createNumber("closeFee", (Class)BigDecimal.class);
        this.coinSymbol = this.createString("coinSymbol");
        this.coinSymbolImageUrl = this.createString("coinSymbolImageUrl");
        this.coinSymbolName = this.createString("coinSymbolName");
        this.coinSymbolPrecision = (NumberPath<Integer>)this.createNumber("coinSymbolPrecision", (Class)Integer.class);
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.dayFeeRate = (NumberPath<BigDecimal>)this.createNumber("dayFeeRate", (Class)BigDecimal.class);
        this.enable = (NumberPath<Integer>)this.createNumber("enable", (Class)Integer.class);
        this.fallInterval = (NumberPath<BigDecimal>)this.createNumber("fallInterval", (Class)BigDecimal.class);
        this.id = this.createString("id");
        this.leverMultiple = this.createString("leverMultiple");
        this.maxHoldLimit = (NumberPath<BigDecimal>)this.createNumber("maxHoldLimit", (Class)BigDecimal.class);
        this.maxLimit = (NumberPath<BigDecimal>)this.createNumber("maxLimit", (Class)BigDecimal.class);
        this.minLimit = (NumberPath<BigDecimal>)this.createNumber("minLimit", (Class)BigDecimal.class);
        this.openFee = (NumberPath<BigDecimal>)this.createNumber("openFee", (Class)BigDecimal.class);
        this.riseInterval = (NumberPath<BigDecimal>)this.createNumber("riseInterval", (Class)BigDecimal.class);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.symbol = this.createString("symbol");
    }
    
    public QContractCoin(final PathMetadata metadata) {
        super((Class)ContractCoin.class, metadata);
        this.baseSymbol = this.createString("baseSymbol");
        this.canBuyDown = (NumberPath<Integer>)this.createNumber("canBuyDown", (Class)Integer.class);
        this.canBuyUp = (NumberPath<Integer>)this.createNumber("canBuyUp", (Class)Integer.class);
        this.closeFee = (NumberPath<BigDecimal>)this.createNumber("closeFee", (Class)BigDecimal.class);
        this.coinSymbol = this.createString("coinSymbol");
        this.coinSymbolImageUrl = this.createString("coinSymbolImageUrl");
        this.coinSymbolName = this.createString("coinSymbolName");
        this.coinSymbolPrecision = (NumberPath<Integer>)this.createNumber("coinSymbolPrecision", (Class)Integer.class);
        this.contractType = (NumberPath<Integer>)this.createNumber("contractType", (Class)Integer.class);
        this.dayFeeRate = (NumberPath<BigDecimal>)this.createNumber("dayFeeRate", (Class)BigDecimal.class);
        this.enable = (NumberPath<Integer>)this.createNumber("enable", (Class)Integer.class);
        this.fallInterval = (NumberPath<BigDecimal>)this.createNumber("fallInterval", (Class)BigDecimal.class);
        this.id = this.createString("id");
        this.leverMultiple = this.createString("leverMultiple");
        this.maxHoldLimit = (NumberPath<BigDecimal>)this.createNumber("maxHoldLimit", (Class)BigDecimal.class);
        this.maxLimit = (NumberPath<BigDecimal>)this.createNumber("maxLimit", (Class)BigDecimal.class);
        this.minLimit = (NumberPath<BigDecimal>)this.createNumber("minLimit", (Class)BigDecimal.class);
        this.openFee = (NumberPath<BigDecimal>)this.createNumber("openFee", (Class)BigDecimal.class);
        this.riseInterval = (NumberPath<BigDecimal>)this.createNumber("riseInterval", (Class)BigDecimal.class);
        this.sort = (NumberPath<Integer>)this.createNumber("sort", (Class)Integer.class);
        this.symbol = this.createString("symbol");
    }
    
    static {
        contractCoin = new QContractCoin("contractCoin");
    }
}

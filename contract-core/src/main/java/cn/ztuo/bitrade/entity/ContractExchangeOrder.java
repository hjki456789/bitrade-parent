package cn.ztuo.bitrade.entity;

import java.io.*;

import org.hibernate.annotations.*;
import cn.afterturn.easypoi.excel.annotation.*;

import java.math.*;

import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.enumConstants.*;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class ContractExchangeOrder implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ManyToOne
    @JsonIgnoreProperties({"member", "contractWalletList"})
    @NotFound(action = NotFoundAction.IGNORE)
    private ContractMember contractMember;
    private String symbol;
    @Excel(name = "\u7c7b\u578b", orderNum = "2", width = 25.0)
    private ContractOrderSide side;
    private ContractOrderType type;
    @Excel(name = "\u5f00\u4ed3\u4ef7", orderNum = "5", width = 25.0)
    private BigDecimal openPrice;
    @Excel(name = "\u59d4\u6258\u6210\u4ea4\u4ef7", orderNum = "4", width = 25.0)
    private BigDecimal price;
    private BigDecimal limitTriggerPrice;
    private BigDecimal limitPrice;
    @Excel(name = "\u6570\u91cf", orderNum = "6", width = 25.0)
    private BigDecimal capitalAmount;
    private BigDecimal amount;
    private String coinId;
    @Excel(name = "\u6746\u6760\u6bd4\u4f8b", orderNum = "3", width = 25.0)
    private BigDecimal leverMultiple;
    @Excel(name = "\u6b62\u635f", orderNum = "7", width = 25.0)
    private BooleanEnum ifStopLoss;
    private BigDecimal stopLossPrice;
    @Excel(name = "\u6b62\u76c8", orderNum = "8", width = 25.0)
    private BooleanEnum ifStopProfit;
    private BigDecimal stopProfitPrice;
    private ContractOrderStatus status;
    @Excel(name = "\u8ba2\u5355IP", orderNum = "9", width = 25.0)
    private String originIp;
    @Transient
    private Long leaderMemberId;
    @Excel(name = "\u8ba2\u5355\u6765\u6e90", orderNum = "10", width = 25.0)
    private ContractOrderOrigin origin;
    private long version;
    @Excel(name = "\u521b\u5efa\u65f6\u95f4", orderNum = "1", width = 25.0)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;
    @JoinColumn(name = "id")
    @OneToOne
    private ContractExchangeOrderClose orderClose;
    @JoinColumn(name = "id")
    @OneToOne
    private ContractExchangeOrderFee orderFee;
    @JoinColumn(name = "id")
    @OneToOne
    private ContractExchangeOrderCancel orderCancel;
    @JoinColumn(name = "id")
    @OneToOne
    private ContractExchangeOrderTigger orderTigger;
    @Transient
    private BigDecimal symbolCurrentPrice;
    @Transient
    private BigDecimal holdInterest;
    @Transient
    private BigDecimal fee;
    @Transient
    private BigDecimal calculateProfit;
    @Transient
    private String nodeName;
    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date buyTime;
    private Integer contractType;
    private Integer walletType;
    private Integer isOneClickOrder;

    public ContractExchangeOrder() {
        this.openPrice = BigDecimal.ZERO;
        this.price = BigDecimal.ZERO;
        this.limitTriggerPrice = BigDecimal.ZERO;
        this.limitPrice = BigDecimal.ZERO;
        this.capitalAmount = BigDecimal.ZERO;
        this.amount = BigDecimal.ZERO;
        this.coinId = "USDT";
        this.leverMultiple = BigDecimal.ZERO;
        this.stopLossPrice = BigDecimal.ZERO;
        this.stopProfitPrice = BigDecimal.ZERO;
        this.symbolCurrentPrice = BigDecimal.ZERO;
        this.holdInterest = BigDecimal.ZERO;
        this.fee = BigDecimal.ZERO;
        this.calculateProfit = BigDecimal.ZERO;
        this.walletType = 0;
        this.isOneClickOrder = 0;
    }

    public long getId() {
        return this.id;
    }

    public Member getMember() {
        return this.member;
    }

    public ContractMember getContractMember() {
        return this.contractMember;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public ContractOrderSide getSide() {
        return this.side;
    }

    public ContractOrderType getType() {
        return this.type;
    }

    public BigDecimal getOpenPrice() {
        return this.openPrice;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getLimitTriggerPrice() {
        return this.limitTriggerPrice;
    }

    public BigDecimal getLimitPrice() {
        return this.limitPrice;
    }

    public BigDecimal getCapitalAmount() {
        return this.capitalAmount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public String getCoinId() {
        return this.coinId;
    }

    public BigDecimal getLeverMultiple() {
        return this.leverMultiple;
    }

    public BooleanEnum getIfStopLoss() {
        return this.ifStopLoss;
    }

    public BigDecimal getStopLossPrice() {
        return this.stopLossPrice;
    }

    public BooleanEnum getIfStopProfit() {
        return this.ifStopProfit;
    }

    public BigDecimal getStopProfitPrice() {
        return this.stopProfitPrice;
    }

    public ContractOrderStatus getStatus() {
        return this.status;
    }

    public String getOriginIp() {
        return this.originIp;
    }

    public Long getLeaderMemberId() {
        return this.leaderMemberId;
    }

    public ContractOrderOrigin getOrigin() {
        return this.origin;
    }

    public long getVersion() {
        return this.version;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public long getSequence() {
        return this.sequence;
    }

    public ContractExchangeOrderClose getOrderClose() {
        return this.orderClose;
    }

    public ContractExchangeOrderFee getOrderFee() {
        return this.orderFee;
    }

    public ContractExchangeOrderCancel getOrderCancel() {
        return this.orderCancel;
    }

    public ContractExchangeOrderTigger getOrderTigger() {
        return this.orderTigger;
    }

    public BigDecimal getSymbolCurrentPrice() {
        return this.symbolCurrentPrice;
    }

    public BigDecimal getHoldInterest() {
        return this.holdInterest;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public BigDecimal getCalculateProfit() {
        return this.calculateProfit;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public Date getBuyTime() {
        return this.buyTime;
    }

    public Integer getContractType() {
        return this.contractType;
    }

    public Integer getWalletType() {
        return this.walletType;
    }

    public Integer getIsOneClickOrder() {
        return this.isOneClickOrder;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public void setContractMember(final ContractMember contractMember) {
        this.contractMember = contractMember;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setSide(final ContractOrderSide side) {
        this.side = side;
    }

    public void setType(final ContractOrderType type) {
        this.type = type;
    }

    public void setOpenPrice(final BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public void setLimitTriggerPrice(final BigDecimal limitTriggerPrice) {
        this.limitTriggerPrice = limitTriggerPrice;
    }

    public void setLimitPrice(final BigDecimal limitPrice) {
        this.limitPrice = limitPrice;
    }

    public void setCapitalAmount(final BigDecimal capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setCoinId(final String coinId) {
        this.coinId = coinId;
    }

    public void setLeverMultiple(final BigDecimal leverMultiple) {
        this.leverMultiple = leverMultiple;
    }

    public void setIfStopLoss(final BooleanEnum ifStopLoss) {
        this.ifStopLoss = ifStopLoss;
    }

    public void setStopLossPrice(final BigDecimal stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public void setIfStopProfit(final BooleanEnum ifStopProfit) {
        this.ifStopProfit = ifStopProfit;
    }

    public void setStopProfitPrice(final BigDecimal stopProfitPrice) {
        this.stopProfitPrice = stopProfitPrice;
    }

    public void setStatus(final ContractOrderStatus status) {
        this.status = status;
    }

    public void setOriginIp(final String originIp) {
        this.originIp = originIp;
    }

    public void setLeaderMemberId(final Long leaderMemberId) {
        this.leaderMemberId = leaderMemberId;
    }

    public void setOrigin(final ContractOrderOrigin origin) {
        this.origin = origin;
    }

    public void setVersion(final long version) {
        this.version = version;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }

    public void setOrderClose(final ContractExchangeOrderClose orderClose) {
        this.orderClose = orderClose;
    }

    public void setOrderFee(final ContractExchangeOrderFee orderFee) {
        this.orderFee = orderFee;
    }

    public void setOrderCancel(final ContractExchangeOrderCancel orderCancel) {
        this.orderCancel = orderCancel;
    }

    public void setOrderTigger(final ContractExchangeOrderTigger orderTigger) {
        this.orderTigger = orderTigger;
    }

    public void setSymbolCurrentPrice(final BigDecimal symbolCurrentPrice) {
        this.symbolCurrentPrice = symbolCurrentPrice;
    }

    public void setHoldInterest(final BigDecimal holdInterest) {
        this.holdInterest = holdInterest;
    }

    public void setFee(final BigDecimal fee) {
        this.fee = fee;
    }

    public void setCalculateProfit(final BigDecimal calculateProfit) {
        this.calculateProfit = calculateProfit;
    }

    public void setNodeName(final String nodeName) {
        this.nodeName = nodeName;
    }

    public void setBuyTime(final Date buyTime) {
        this.buyTime = buyTime;
    }

    public void setContractType(final Integer contractType) {
        this.contractType = contractType;
    }

    public void setWalletType(final Integer walletType) {
        this.walletType = walletType;
    }

    public void setIsOneClickOrder(final Integer isOneClickOrder) {
        this.isOneClickOrder = isOneClickOrder;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractExchangeOrder)) {
            return false;
        }
        final ContractExchangeOrder other = (ContractExchangeOrder) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0079:
        {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0079;
                }
            } else if (this$member.equals(other$member)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$contractMember = this.getContractMember();
        final Object other$contractMember = other.getContractMember();
        Label_0116:
        {
            if (this$contractMember == null) {
                if (other$contractMember == null) {
                    break Label_0116;
                }
            } else if (this$contractMember.equals(other$contractMember)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0153:
        {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0153;
                }
            } else if (this$symbol.equals(other$symbol)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$side = this.getSide();
        final Object other$side = other.getSide();
        Label_0190:
        {
            if (this$side == null) {
                if (other$side == null) {
                    break Label_0190;
                }
            } else if (this$side.equals(other$side)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0227:
        {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0227;
                }
            } else if (this$type.equals(other$type)) {
                break Label_0227;
            }
            return false;
        }
        final Object this$openPrice = this.getOpenPrice();
        final Object other$openPrice = other.getOpenPrice();
        Label_0264:
        {
            if (this$openPrice == null) {
                if (other$openPrice == null) {
                    break Label_0264;
                }
            } else if (this$openPrice.equals(other$openPrice)) {
                break Label_0264;
            }
            return false;
        }
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        Label_0301:
        {
            if (this$price == null) {
                if (other$price == null) {
                    break Label_0301;
                }
            } else if (this$price.equals(other$price)) {
                break Label_0301;
            }
            return false;
        }
        final Object this$limitTriggerPrice = this.getLimitTriggerPrice();
        final Object other$limitTriggerPrice = other.getLimitTriggerPrice();
        Label_0338:
        {
            if (this$limitTriggerPrice == null) {
                if (other$limitTriggerPrice == null) {
                    break Label_0338;
                }
            } else if (this$limitTriggerPrice.equals(other$limitTriggerPrice)) {
                break Label_0338;
            }
            return false;
        }
        final Object this$limitPrice = this.getLimitPrice();
        final Object other$limitPrice = other.getLimitPrice();
        Label_0375:
        {
            if (this$limitPrice == null) {
                if (other$limitPrice == null) {
                    break Label_0375;
                }
            } else if (this$limitPrice.equals(other$limitPrice)) {
                break Label_0375;
            }
            return false;
        }
        final Object this$capitalAmount = this.getCapitalAmount();
        final Object other$capitalAmount = other.getCapitalAmount();
        Label_0412:
        {
            if (this$capitalAmount == null) {
                if (other$capitalAmount == null) {
                    break Label_0412;
                }
            } else if (this$capitalAmount.equals(other$capitalAmount)) {
                break Label_0412;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0449:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0449;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0449;
            }
            return false;
        }
        final Object this$coinId = this.getCoinId();
        final Object other$coinId = other.getCoinId();
        Label_0486:
        {
            if (this$coinId == null) {
                if (other$coinId == null) {
                    break Label_0486;
                }
            } else if (this$coinId.equals(other$coinId)) {
                break Label_0486;
            }
            return false;
        }
        final Object this$leverMultiple = this.getLeverMultiple();
        final Object other$leverMultiple = other.getLeverMultiple();
        Label_0523:
        {
            if (this$leverMultiple == null) {
                if (other$leverMultiple == null) {
                    break Label_0523;
                }
            } else if (this$leverMultiple.equals(other$leverMultiple)) {
                break Label_0523;
            }
            return false;
        }
        final Object this$ifStopLoss = this.getIfStopLoss();
        final Object other$ifStopLoss = other.getIfStopLoss();
        Label_0560:
        {
            if (this$ifStopLoss == null) {
                if (other$ifStopLoss == null) {
                    break Label_0560;
                }
            } else if (this$ifStopLoss.equals(other$ifStopLoss)) {
                break Label_0560;
            }
            return false;
        }
        final Object this$stopLossPrice = this.getStopLossPrice();
        final Object other$stopLossPrice = other.getStopLossPrice();
        Label_0597:
        {
            if (this$stopLossPrice == null) {
                if (other$stopLossPrice == null) {
                    break Label_0597;
                }
            } else if (this$stopLossPrice.equals(other$stopLossPrice)) {
                break Label_0597;
            }
            return false;
        }
        final Object this$ifStopProfit = this.getIfStopProfit();
        final Object other$ifStopProfit = other.getIfStopProfit();
        Label_0634:
        {
            if (this$ifStopProfit == null) {
                if (other$ifStopProfit == null) {
                    break Label_0634;
                }
            } else if (this$ifStopProfit.equals(other$ifStopProfit)) {
                break Label_0634;
            }
            return false;
        }
        final Object this$stopProfitPrice = this.getStopProfitPrice();
        final Object other$stopProfitPrice = other.getStopProfitPrice();
        Label_0671:
        {
            if (this$stopProfitPrice == null) {
                if (other$stopProfitPrice == null) {
                    break Label_0671;
                }
            } else if (this$stopProfitPrice.equals(other$stopProfitPrice)) {
                break Label_0671;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0708:
        {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0708;
                }
            } else if (this$status.equals(other$status)) {
                break Label_0708;
            }
            return false;
        }
        final Object this$originIp = this.getOriginIp();
        final Object other$originIp = other.getOriginIp();
        Label_0745:
        {
            if (this$originIp == null) {
                if (other$originIp == null) {
                    break Label_0745;
                }
            } else if (this$originIp.equals(other$originIp)) {
                break Label_0745;
            }
            return false;
        }
        final Object this$leaderMemberId = this.getLeaderMemberId();
        final Object other$leaderMemberId = other.getLeaderMemberId();
        Label_0782:
        {
            if (this$leaderMemberId == null) {
                if (other$leaderMemberId == null) {
                    break Label_0782;
                }
            } else if (this$leaderMemberId.equals(other$leaderMemberId)) {
                break Label_0782;
            }
            return false;
        }
        final Object this$origin = this.getOrigin();
        final Object other$origin = other.getOrigin();
        Label_0819:
        {
            if (this$origin == null) {
                if (other$origin == null) {
                    break Label_0819;
                }
            } else if (this$origin.equals(other$origin)) {
                break Label_0819;
            }
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0870:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0870;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0870;
            }
            return false;
        }
        if (this.getSequence() != other.getSequence()) {
            return false;
        }
        final Object this$orderClose = this.getOrderClose();
        final Object other$orderClose = other.getOrderClose();
        Label_0921:
        {
            if (this$orderClose == null) {
                if (other$orderClose == null) {
                    break Label_0921;
                }
            } else if (this$orderClose.equals(other$orderClose)) {
                break Label_0921;
            }
            return false;
        }
        final Object this$orderFee = this.getOrderFee();
        final Object other$orderFee = other.getOrderFee();
        Label_0958:
        {
            if (this$orderFee == null) {
                if (other$orderFee == null) {
                    break Label_0958;
                }
            } else if (this$orderFee.equals(other$orderFee)) {
                break Label_0958;
            }
            return false;
        }
        final Object this$orderCancel = this.getOrderCancel();
        final Object other$orderCancel = other.getOrderCancel();
        Label_0995:
        {
            if (this$orderCancel == null) {
                if (other$orderCancel == null) {
                    break Label_0995;
                }
            } else if (this$orderCancel.equals(other$orderCancel)) {
                break Label_0995;
            }
            return false;
        }
        final Object this$orderTigger = this.getOrderTigger();
        final Object other$orderTigger = other.getOrderTigger();
        Label_1032:
        {
            if (this$orderTigger == null) {
                if (other$orderTigger == null) {
                    break Label_1032;
                }
            } else if (this$orderTigger.equals(other$orderTigger)) {
                break Label_1032;
            }
            return false;
        }
        final Object this$symbolCurrentPrice = this.getSymbolCurrentPrice();
        final Object other$symbolCurrentPrice = other.getSymbolCurrentPrice();
        Label_1069:
        {
            if (this$symbolCurrentPrice == null) {
                if (other$symbolCurrentPrice == null) {
                    break Label_1069;
                }
            } else if (this$symbolCurrentPrice.equals(other$symbolCurrentPrice)) {
                break Label_1069;
            }
            return false;
        }
        final Object this$holdInterest = this.getHoldInterest();
        final Object other$holdInterest = other.getHoldInterest();
        Label_1106:
        {
            if (this$holdInterest == null) {
                if (other$holdInterest == null) {
                    break Label_1106;
                }
            } else if (this$holdInterest.equals(other$holdInterest)) {
                break Label_1106;
            }
            return false;
        }
        final Object this$fee = this.getFee();
        final Object other$fee = other.getFee();
        Label_1143:
        {
            if (this$fee == null) {
                if (other$fee == null) {
                    break Label_1143;
                }
            } else if (this$fee.equals(other$fee)) {
                break Label_1143;
            }
            return false;
        }
        final Object this$calculateProfit = this.getCalculateProfit();
        final Object other$calculateProfit = other.getCalculateProfit();
        Label_1180:
        {
            if (this$calculateProfit == null) {
                if (other$calculateProfit == null) {
                    break Label_1180;
                }
            } else if (this$calculateProfit.equals(other$calculateProfit)) {
                break Label_1180;
            }
            return false;
        }
        final Object this$nodeName = this.getNodeName();
        final Object other$nodeName = other.getNodeName();
        Label_1217:
        {
            if (this$nodeName == null) {
                if (other$nodeName == null) {
                    break Label_1217;
                }
            } else if (this$nodeName.equals(other$nodeName)) {
                break Label_1217;
            }
            return false;
        }
        final Object this$buyTime = this.getBuyTime();
        final Object other$buyTime = other.getBuyTime();
        Label_1254:
        {
            if (this$buyTime == null) {
                if (other$buyTime == null) {
                    break Label_1254;
                }
            } else if (this$buyTime.equals(other$buyTime)) {
                break Label_1254;
            }
            return false;
        }
        final Object this$contractType = this.getContractType();
        final Object other$contractType = other.getContractType();
        Label_1291:
        {
            if (this$contractType == null) {
                if (other$contractType == null) {
                    break Label_1291;
                }
            } else if (this$contractType.equals(other$contractType)) {
                break Label_1291;
            }
            return false;
        }
        final Object this$walletType = this.getWalletType();
        final Object other$walletType = other.getWalletType();
        Label_1328:
        {
            if (this$walletType == null) {
                if (other$walletType == null) {
                    break Label_1328;
                }
            } else if (this$walletType.equals(other$walletType)) {
                break Label_1328;
            }
            return false;
        }
        final Object this$isOneClickOrder = this.getIsOneClickOrder();
        final Object other$isOneClickOrder = other.getIsOneClickOrder();
        if (this$isOneClickOrder == null) {
            if (other$isOneClickOrder == null) {
                return true;
            }
        } else if (this$isOneClickOrder.equals(other$isOneClickOrder)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractExchangeOrder;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $contractMember = this.getContractMember();
        result = result * 59 + (($contractMember == null) ? 43 : $contractMember.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $side = this.getSide();
        result = result * 59 + (($side == null) ? 43 : $side.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $openPrice = this.getOpenPrice();
        result = result * 59 + (($openPrice == null) ? 43 : $openPrice.hashCode());
        final Object $price = this.getPrice();
        result = result * 59 + (($price == null) ? 43 : $price.hashCode());
        final Object $limitTriggerPrice = this.getLimitTriggerPrice();
        result = result * 59 + (($limitTriggerPrice == null) ? 43 : $limitTriggerPrice.hashCode());
        final Object $limitPrice = this.getLimitPrice();
        result = result * 59 + (($limitPrice == null) ? 43 : $limitPrice.hashCode());
        final Object $capitalAmount = this.getCapitalAmount();
        result = result * 59 + (($capitalAmount == null) ? 43 : $capitalAmount.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $coinId = this.getCoinId();
        result = result * 59 + (($coinId == null) ? 43 : $coinId.hashCode());
        final Object $leverMultiple = this.getLeverMultiple();
        result = result * 59 + (($leverMultiple == null) ? 43 : $leverMultiple.hashCode());
        final Object $ifStopLoss = this.getIfStopLoss();
        result = result * 59 + (($ifStopLoss == null) ? 43 : $ifStopLoss.hashCode());
        final Object $stopLossPrice = this.getStopLossPrice();
        result = result * 59 + (($stopLossPrice == null) ? 43 : $stopLossPrice.hashCode());
        final Object $ifStopProfit = this.getIfStopProfit();
        result = result * 59 + (($ifStopProfit == null) ? 43 : $ifStopProfit.hashCode());
        final Object $stopProfitPrice = this.getStopProfitPrice();
        result = result * 59 + (($stopProfitPrice == null) ? 43 : $stopProfitPrice.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $originIp = this.getOriginIp();
        result = result * 59 + (($originIp == null) ? 43 : $originIp.hashCode());
        final Object $leaderMemberId = this.getLeaderMemberId();
        result = result * 59 + (($leaderMemberId == null) ? 43 : $leaderMemberId.hashCode());
        final Object $origin = this.getOrigin();
        result = result * 59 + (($origin == null) ? 43 : $origin.hashCode());
        final long $version = this.getVersion();
        result = result * 59 + (int) ($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        final Object $orderClose = this.getOrderClose();
        result = result * 59 + (($orderClose == null) ? 43 : $orderClose.hashCode());
        final Object $orderFee = this.getOrderFee();
        result = result * 59 + (($orderFee == null) ? 43 : $orderFee.hashCode());
        final Object $orderCancel = this.getOrderCancel();
        result = result * 59 + (($orderCancel == null) ? 43 : $orderCancel.hashCode());
        final Object $orderTigger = this.getOrderTigger();
        result = result * 59 + (($orderTigger == null) ? 43 : $orderTigger.hashCode());
        final Object $symbolCurrentPrice = this.getSymbolCurrentPrice();
        result = result * 59 + (($symbolCurrentPrice == null) ? 43 : $symbolCurrentPrice.hashCode());
        final Object $holdInterest = this.getHoldInterest();
        result = result * 59 + (($holdInterest == null) ? 43 : $holdInterest.hashCode());
        final Object $fee = this.getFee();
        result = result * 59 + (($fee == null) ? 43 : $fee.hashCode());
        final Object $calculateProfit = this.getCalculateProfit();
        result = result * 59 + (($calculateProfit == null) ? 43 : $calculateProfit.hashCode());
        final Object $nodeName = this.getNodeName();
        result = result * 59 + (($nodeName == null) ? 43 : $nodeName.hashCode());
        final Object $buyTime = this.getBuyTime();
        result = result * 59 + (($buyTime == null) ? 43 : $buyTime.hashCode());
        final Object $contractType = this.getContractType();
        result = result * 59 + (($contractType == null) ? 43 : $contractType.hashCode());
        final Object $walletType = this.getWalletType();
        result = result * 59 + (($walletType == null) ? 43 : $walletType.hashCode());
        final Object $isOneClickOrder = this.getIsOneClickOrder();
        result = result * 59 + (($isOneClickOrder == null) ? 43 : $isOneClickOrder.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractExchangeOrder(id=" + this.getId() + ", member=" + this.getMember() + ", contractMember=" + this.getContractMember() + ", symbol=" + this.getSymbol() + ", side=" + this.getSide() + ", type=" + this.getType() + ", openPrice=" + this.getOpenPrice() + ", price=" + this.getPrice() + ", limitTriggerPrice=" + this.getLimitTriggerPrice() + ", limitPrice=" + this.getLimitPrice() + ", capitalAmount=" + this.getCapitalAmount() + ", amount=" + this.getAmount() + ", coinId=" + this.getCoinId() + ", leverMultiple=" + this.getLeverMultiple() + ", ifStopLoss=" + this.getIfStopLoss() + ", stopLossPrice=" + this.getStopLossPrice() + ", ifStopProfit=" + this.getIfStopProfit() + ", stopProfitPrice=" + this.getStopProfitPrice() + ", status=" + this.getStatus() + ", originIp=" + this.getOriginIp() + ", leaderMemberId=" + this.getLeaderMemberId() + ", origin=" + this.getOrigin() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ", orderClose=" + this.getOrderClose() + ", orderFee=" + this.getOrderFee() + ", orderCancel=" + this.getOrderCancel() + ", orderTigger=" + this.getOrderTigger() + ", symbolCurrentPrice=" + this.getSymbolCurrentPrice() + ", holdInterest=" + this.getHoldInterest() + ", fee=" + this.getFee() + ", calculateProfit=" + this.getCalculateProfit() + ", nodeName=" + this.getNodeName() + ", buyTime=" + this.getBuyTime() + ", contractType=" + this.getContractType() + ", walletType=" + this.getWalletType() + ", isOneClickOrder=" + this.getIsOneClickOrder() + ")";
    }
}

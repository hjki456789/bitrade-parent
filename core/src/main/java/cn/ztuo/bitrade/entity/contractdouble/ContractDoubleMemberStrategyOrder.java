package cn.ztuo.bitrade.entity.contractdouble;

import java.io.*;
import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import cn.ztuo.bitrade.entity.*;

@Entity
@Table
public class ContractDoubleMemberStrategyOrder implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long fromExchangeId;
    private Long contractOrderId;
    private Long apiId;
    private String apiKey;
    private String secretKey;
    private String fromExchangeOrderId;
    private String symbol;
    private BigDecimal price;
    private Long amount;
    private BigDecimal contractSize;
    private String direction;
    private String offset;
    private Integer leverRate;
    private String orderPriceType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private int status;
    private String remark;
    private int matchStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private BigDecimal marginFrozen;
    private BigDecimal profit;
    private String closeOrderId;
    private int closeMatchStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date closeTime;
    @Transient
    private String fromExchangeName;
    @Transient
    private Member member;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public Long getFromExchangeId() {
        return this.fromExchangeId;
    }
    
    public Long getContractOrderId() {
        return this.contractOrderId;
    }
    
    public Long getApiId() {
        return this.apiId;
    }
    
    public String getApiKey() {
        return this.apiKey;
    }
    
    public String getSecretKey() {
        return this.secretKey;
    }
    
    public String getFromExchangeOrderId() {
        return this.fromExchangeOrderId;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public Long getAmount() {
        return this.amount;
    }
    
    public BigDecimal getContractSize() {
        return this.contractSize;
    }
    
    public String getDirection() {
        return this.direction;
    }
    
    public String getOffset() {
        return this.offset;
    }
    
    public Integer getLeverRate() {
        return this.leverRate;
    }
    
    public String getOrderPriceType() {
        return this.orderPriceType;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public int getMatchStatus() {
        return this.matchStatus;
    }
    
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public BigDecimal getMarginFrozen() {
        return this.marginFrozen;
    }
    
    public BigDecimal getProfit() {
        return this.profit;
    }
    
    public String getCloseOrderId() {
        return this.closeOrderId;
    }
    
    public int getCloseMatchStatus() {
        return this.closeMatchStatus;
    }
    
    public Date getCloseTime() {
        return this.closeTime;
    }
    
    public String getFromExchangeName() {
        return this.fromExchangeName;
    }
    
    public Member getMember() {
        return this.member;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setFromExchangeId(final Long fromExchangeId) {
        this.fromExchangeId = fromExchangeId;
    }
    
    public void setContractOrderId(final Long contractOrderId) {
        this.contractOrderId = contractOrderId;
    }
    
    public void setApiId(final Long apiId) {
        this.apiId = apiId;
    }
    
    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }
    
    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }
    
    public void setFromExchangeOrderId(final String fromExchangeOrderId) {
        this.fromExchangeOrderId = fromExchangeOrderId;
    }
    
    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }
    
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
    
    public void setAmount(final Long amount) {
        this.amount = amount;
    }
    
    public void setContractSize(final BigDecimal contractSize) {
        this.contractSize = contractSize;
    }
    
    public void setDirection(final String direction) {
        this.direction = direction;
    }
    
    public void setOffset(final String offset) {
        this.offset = offset;
    }
    
    public void setLeverRate(final Integer leverRate) {
        this.leverRate = leverRate;
    }
    
    public void setOrderPriceType(final String orderPriceType) {
        this.orderPriceType = orderPriceType;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setMatchStatus(final int matchStatus) {
        this.matchStatus = matchStatus;
    }
    
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public void setMarginFrozen(final BigDecimal marginFrozen) {
        this.marginFrozen = marginFrozen;
    }
    
    public void setProfit(final BigDecimal profit) {
        this.profit = profit;
    }
    
    public void setCloseOrderId(final String closeOrderId) {
        this.closeOrderId = closeOrderId;
    }
    
    public void setCloseMatchStatus(final int closeMatchStatus) {
        this.closeMatchStatus = closeMatchStatus;
    }
    
    public void setCloseTime(final Date closeTime) {
        this.closeTime = closeTime;
    }
    
    public void setFromExchangeName(final String fromExchangeName) {
        this.fromExchangeName = fromExchangeName;
    }
    
    public void setMember(final Member member) {
        this.member = member;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractDoubleMemberStrategyOrder)) {
            return false;
        }
        final ContractDoubleMemberStrategyOrder other = (ContractDoubleMemberStrategyOrder)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0102: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0102;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$fromExchangeId = this.getFromExchangeId();
        final Object other$fromExchangeId = other.getFromExchangeId();
        Label_0139: {
            if (this$fromExchangeId == null) {
                if (other$fromExchangeId == null) {
                    break Label_0139;
                }
            }
            else if (this$fromExchangeId.equals(other$fromExchangeId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$contractOrderId = this.getContractOrderId();
        final Object other$contractOrderId = other.getContractOrderId();
        Label_0176: {
            if (this$contractOrderId == null) {
                if (other$contractOrderId == null) {
                    break Label_0176;
                }
            }
            else if (this$contractOrderId.equals(other$contractOrderId)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$apiId = this.getApiId();
        final Object other$apiId = other.getApiId();
        Label_0213: {
            if (this$apiId == null) {
                if (other$apiId == null) {
                    break Label_0213;
                }
            }
            else if (this$apiId.equals(other$apiId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$apiKey = this.getApiKey();
        final Object other$apiKey = other.getApiKey();
        Label_0250: {
            if (this$apiKey == null) {
                if (other$apiKey == null) {
                    break Label_0250;
                }
            }
            else if (this$apiKey.equals(other$apiKey)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$secretKey = this.getSecretKey();
        final Object other$secretKey = other.getSecretKey();
        Label_0287: {
            if (this$secretKey == null) {
                if (other$secretKey == null) {
                    break Label_0287;
                }
            }
            else if (this$secretKey.equals(other$secretKey)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$fromExchangeOrderId = this.getFromExchangeOrderId();
        final Object other$fromExchangeOrderId = other.getFromExchangeOrderId();
        Label_0324: {
            if (this$fromExchangeOrderId == null) {
                if (other$fromExchangeOrderId == null) {
                    break Label_0324;
                }
            }
            else if (this$fromExchangeOrderId.equals(other$fromExchangeOrderId)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0361: {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0361;
                }
            }
            else if (this$symbol.equals(other$symbol)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        Label_0398: {
            if (this$price == null) {
                if (other$price == null) {
                    break Label_0398;
                }
            }
            else if (this$price.equals(other$price)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0435: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0435;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$contractSize = this.getContractSize();
        final Object other$contractSize = other.getContractSize();
        Label_0472: {
            if (this$contractSize == null) {
                if (other$contractSize == null) {
                    break Label_0472;
                }
            }
            else if (this$contractSize.equals(other$contractSize)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$direction = this.getDirection();
        final Object other$direction = other.getDirection();
        Label_0509: {
            if (this$direction == null) {
                if (other$direction == null) {
                    break Label_0509;
                }
            }
            else if (this$direction.equals(other$direction)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$offset = this.getOffset();
        final Object other$offset = other.getOffset();
        Label_0546: {
            if (this$offset == null) {
                if (other$offset == null) {
                    break Label_0546;
                }
            }
            else if (this$offset.equals(other$offset)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$leverRate = this.getLeverRate();
        final Object other$leverRate = other.getLeverRate();
        Label_0583: {
            if (this$leverRate == null) {
                if (other$leverRate == null) {
                    break Label_0583;
                }
            }
            else if (this$leverRate.equals(other$leverRate)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$orderPriceType = this.getOrderPriceType();
        final Object other$orderPriceType = other.getOrderPriceType();
        Label_0620: {
            if (this$orderPriceType == null) {
                if (other$orderPriceType == null) {
                    break Label_0620;
                }
            }
            else if (this$orderPriceType.equals(other$orderPriceType)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0657: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0657;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0657;
            }
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0707: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0707;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0707;
            }
            return false;
        }
        if (this.getMatchStatus() != other.getMatchStatus()) {
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0757: {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0757;
                }
            }
            else if (this$updateTime.equals(other$updateTime)) {
                break Label_0757;
            }
            return false;
        }
        final Object this$marginFrozen = this.getMarginFrozen();
        final Object other$marginFrozen = other.getMarginFrozen();
        Label_0794: {
            if (this$marginFrozen == null) {
                if (other$marginFrozen == null) {
                    break Label_0794;
                }
            }
            else if (this$marginFrozen.equals(other$marginFrozen)) {
                break Label_0794;
            }
            return false;
        }
        final Object this$profit = this.getProfit();
        final Object other$profit = other.getProfit();
        Label_0831: {
            if (this$profit == null) {
                if (other$profit == null) {
                    break Label_0831;
                }
            }
            else if (this$profit.equals(other$profit)) {
                break Label_0831;
            }
            return false;
        }
        final Object this$closeOrderId = this.getCloseOrderId();
        final Object other$closeOrderId = other.getCloseOrderId();
        Label_0868: {
            if (this$closeOrderId == null) {
                if (other$closeOrderId == null) {
                    break Label_0868;
                }
            }
            else if (this$closeOrderId.equals(other$closeOrderId)) {
                break Label_0868;
            }
            return false;
        }
        if (this.getCloseMatchStatus() != other.getCloseMatchStatus()) {
            return false;
        }
        final Object this$closeTime = this.getCloseTime();
        final Object other$closeTime = other.getCloseTime();
        Label_0918: {
            if (this$closeTime == null) {
                if (other$closeTime == null) {
                    break Label_0918;
                }
            }
            else if (this$closeTime.equals(other$closeTime)) {
                break Label_0918;
            }
            return false;
        }
        final Object this$fromExchangeName = this.getFromExchangeName();
        final Object other$fromExchangeName = other.getFromExchangeName();
        Label_0955: {
            if (this$fromExchangeName == null) {
                if (other$fromExchangeName == null) {
                    break Label_0955;
                }
            }
            else if (this$fromExchangeName.equals(other$fromExchangeName)) {
                break Label_0955;
            }
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        if (this$member == null) {
            if (other$member == null) {
                return true;
            }
        }
        else if (this$member.equals(other$member)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractDoubleMemberStrategyOrder;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $fromExchangeId = this.getFromExchangeId();
        result = result * 59 + (($fromExchangeId == null) ? 43 : $fromExchangeId.hashCode());
        final Object $contractOrderId = this.getContractOrderId();
        result = result * 59 + (($contractOrderId == null) ? 43 : $contractOrderId.hashCode());
        final Object $apiId = this.getApiId();
        result = result * 59 + (($apiId == null) ? 43 : $apiId.hashCode());
        final Object $apiKey = this.getApiKey();
        result = result * 59 + (($apiKey == null) ? 43 : $apiKey.hashCode());
        final Object $secretKey = this.getSecretKey();
        result = result * 59 + (($secretKey == null) ? 43 : $secretKey.hashCode());
        final Object $fromExchangeOrderId = this.getFromExchangeOrderId();
        result = result * 59 + (($fromExchangeOrderId == null) ? 43 : $fromExchangeOrderId.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $price = this.getPrice();
        result = result * 59 + (($price == null) ? 43 : $price.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $contractSize = this.getContractSize();
        result = result * 59 + (($contractSize == null) ? 43 : $contractSize.hashCode());
        final Object $direction = this.getDirection();
        result = result * 59 + (($direction == null) ? 43 : $direction.hashCode());
        final Object $offset = this.getOffset();
        result = result * 59 + (($offset == null) ? 43 : $offset.hashCode());
        final Object $leverRate = this.getLeverRate();
        result = result * 59 + (($leverRate == null) ? 43 : $leverRate.hashCode());
        final Object $orderPriceType = this.getOrderPriceType();
        result = result * 59 + (($orderPriceType == null) ? 43 : $orderPriceType.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        result = result * 59 + this.getStatus();
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        result = result * 59 + this.getMatchStatus();
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $marginFrozen = this.getMarginFrozen();
        result = result * 59 + (($marginFrozen == null) ? 43 : $marginFrozen.hashCode());
        final Object $profit = this.getProfit();
        result = result * 59 + (($profit == null) ? 43 : $profit.hashCode());
        final Object $closeOrderId = this.getCloseOrderId();
        result = result * 59 + (($closeOrderId == null) ? 43 : $closeOrderId.hashCode());
        result = result * 59 + this.getCloseMatchStatus();
        final Object $closeTime = this.getCloseTime();
        result = result * 59 + (($closeTime == null) ? 43 : $closeTime.hashCode());
        final Object $fromExchangeName = this.getFromExchangeName();
        result = result * 59 + (($fromExchangeName == null) ? 43 : $fromExchangeName.hashCode());
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractDoubleMemberStrategyOrder(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", fromExchangeId=" + this.getFromExchangeId() + ", contractOrderId=" + this.getContractOrderId() + ", apiId=" + this.getApiId() + ", apiKey=" + this.getApiKey() + ", secretKey=" + this.getSecretKey() + ", fromExchangeOrderId=" + this.getFromExchangeOrderId() + ", symbol=" + this.getSymbol() + ", price=" + this.getPrice() + ", amount=" + this.getAmount() + ", contractSize=" + this.getContractSize() + ", direction=" + this.getDirection() + ", offset=" + this.getOffset() + ", leverRate=" + this.getLeverRate() + ", orderPriceType=" + this.getOrderPriceType() + ", createTime=" + this.getCreateTime() + ", status=" + this.getStatus() + ", remark=" + this.getRemark() + ", matchStatus=" + this.getMatchStatus() + ", updateTime=" + this.getUpdateTime() + ", marginFrozen=" + this.getMarginFrozen() + ", profit=" + this.getProfit() + ", closeOrderId=" + this.getCloseOrderId() + ", closeMatchStatus=" + this.getCloseMatchStatus() + ", closeTime=" + this.getCloseTime() + ", fromExchangeName=" + this.getFromExchangeName() + ", member=" + this.getMember() + ")";
    }
}

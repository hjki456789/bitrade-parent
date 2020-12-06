package cn.ztuo.bitrade.entity.contractstrategy;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

@Entity
@Table
public class ContractStrategyHedgingOrder implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long fromExchangeId;
    private String apiKey;
    private String fromExchangeOrderId;
    private String symbol;
    private BigDecimal price;
    private Long amount;
    private BigDecimal contractSize;
    private String direction;
    private String offset;
    private Integer leverRate;
    private String orderPriceType;
    private Date createTime;

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public Long getFromExchangeId() {
        return this.fromExchangeId;
    }

    public String getApiKey() {
        return this.apiKey;
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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setFromExchangeId(final Long fromExchangeId) {
        this.fromExchangeId = fromExchangeId;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
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

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractStrategyHedgingOrder)) {
            return false;
        }
        final ContractStrategyHedgingOrder other = (ContractStrategyHedgingOrder) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065:
        {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            } else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0102:
        {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0102;
                }
            } else if (this$memberId.equals(other$memberId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$fromExchangeId = this.getFromExchangeId();
        final Object other$fromExchangeId = other.getFromExchangeId();
        Label_0139:
        {
            if (this$fromExchangeId == null) {
                if (other$fromExchangeId == null) {
                    break Label_0139;
                }
            } else if (this$fromExchangeId.equals(other$fromExchangeId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$apiKey = this.getApiKey();
        final Object other$apiKey = other.getApiKey();
        Label_0176:
        {
            if (this$apiKey == null) {
                if (other$apiKey == null) {
                    break Label_0176;
                }
            } else if (this$apiKey.equals(other$apiKey)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$fromExchangeOrderId = this.getFromExchangeOrderId();
        final Object other$fromExchangeOrderId = other.getFromExchangeOrderId();
        Label_0213:
        {
            if (this$fromExchangeOrderId == null) {
                if (other$fromExchangeOrderId == null) {
                    break Label_0213;
                }
            } else if (this$fromExchangeOrderId.equals(other$fromExchangeOrderId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0250:
        {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0250;
                }
            } else if (this$symbol.equals(other$symbol)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        Label_0287:
        {
            if (this$price == null) {
                if (other$price == null) {
                    break Label_0287;
                }
            } else if (this$price.equals(other$price)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0324:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0324;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$contractSize = this.getContractSize();
        final Object other$contractSize = other.getContractSize();
        Label_0361:
        {
            if (this$contractSize == null) {
                if (other$contractSize == null) {
                    break Label_0361;
                }
            } else if (this$contractSize.equals(other$contractSize)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$direction = this.getDirection();
        final Object other$direction = other.getDirection();
        Label_0398:
        {
            if (this$direction == null) {
                if (other$direction == null) {
                    break Label_0398;
                }
            } else if (this$direction.equals(other$direction)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$offset = this.getOffset();
        final Object other$offset = other.getOffset();
        Label_0435:
        {
            if (this$offset == null) {
                if (other$offset == null) {
                    break Label_0435;
                }
            } else if (this$offset.equals(other$offset)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$leverRate = this.getLeverRate();
        final Object other$leverRate = other.getLeverRate();
        Label_0472:
        {
            if (this$leverRate == null) {
                if (other$leverRate == null) {
                    break Label_0472;
                }
            } else if (this$leverRate.equals(other$leverRate)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$orderPriceType = this.getOrderPriceType();
        final Object other$orderPriceType = other.getOrderPriceType();
        Label_0509:
        {
            if (this$orderPriceType == null) {
                if (other$orderPriceType == null) {
                    break Label_0509;
                }
            } else if (this$orderPriceType.equals(other$orderPriceType)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null) {
            if (other$createTime == null) {
                return true;
            }
        } else if (this$createTime.equals(other$createTime)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractStrategyHedgingOrder;
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
        final Object $apiKey = this.getApiKey();
        result = result * 59 + (($apiKey == null) ? 43 : $apiKey.hashCode());
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
        return result;
    }

    @Override
    public String toString() {
        return "ContractStrategyHedgingOrder(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", fromExchangeId=" + this.getFromExchangeId() + ", apiKey=" + this.getApiKey() + ", fromExchangeOrderId=" + this.getFromExchangeOrderId() + ", symbol=" + this.getSymbol() + ", price=" + this.getPrice() + ", amount=" + this.getAmount() + ", contractSize=" + this.getContractSize() + ", direction=" + this.getDirection() + ", offset=" + this.getOffset() + ", leverRate=" + this.getLeverRate() + ", orderPriceType=" + this.getOrderPriceType() + ", createTime=" + this.getCreateTime() + ")";
    }
}

package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;

import org.hibernate.validator.constraints.*;

import java.math.*;

@Entity
@Table
public class ContractCoin implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    @NotBlank(message = "\u8ba1\u4ef7\u5e01\u79cd\u4e0d\u5f97\u4e3a\u7a7a")
    private String baseSymbol;
    @NotBlank(message = "\u4ea4\u6613\u5e01\u79cd\u4e0d\u5f97\u4e3a\u7a7a")
    private String coinSymbol;
    @NotBlank(message = "\u4ea4\u6613\u5e01\u79cd\u540d\u79f0\u4e0d\u5f97\u4e3a\u7a7a")
    private String coinSymbolName;
    private String coinSymbolImageUrl;
    private Integer coinSymbolPrecision;
    private Integer enable;
    private Integer sort;
    private String symbol;
    @NotBlank(message = "\u6760\u6746\u500d\u6570\u4e0d\u5f97\u4e3a\u7a7a")
    private String leverMultiple;
    private BigDecimal openFee;
    private BigDecimal closeFee;
    private BigDecimal dayFeeRate;
    private BigDecimal minLimit;
    private BigDecimal maxLimit;
    private BigDecimal maxHoldLimit;
    private BigDecimal riseInterval;
    private BigDecimal fallInterval;
    private int contractType;
    private int canBuyUp;
    private int canBuyDown;

    public ContractCoin() {
        this.openFee = BigDecimal.ZERO;
        this.closeFee = BigDecimal.ZERO;
        this.dayFeeRate = BigDecimal.ZERO;
        this.minLimit = BigDecimal.ZERO;
        this.maxLimit = BigDecimal.ZERO;
        this.maxHoldLimit = BigDecimal.ZERO;
        this.riseInterval = BigDecimal.ZERO;
        this.fallInterval = BigDecimal.ZERO;
        this.contractType = 0;
        this.canBuyUp = 1;
        this.canBuyDown = 1;
    }

    public ContractCoin(final String id) {
        this.openFee = BigDecimal.ZERO;
        this.closeFee = BigDecimal.ZERO;
        this.dayFeeRate = BigDecimal.ZERO;
        this.minLimit = BigDecimal.ZERO;
        this.maxLimit = BigDecimal.ZERO;
        this.maxHoldLimit = BigDecimal.ZERO;
        this.riseInterval = BigDecimal.ZERO;
        this.fallInterval = BigDecimal.ZERO;
        this.contractType = 0;
        this.canBuyUp = 1;
        this.canBuyDown = 1;
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getBaseSymbol() {
        return this.baseSymbol;
    }

    public String getCoinSymbol() {
        return this.coinSymbol;
    }

    public String getCoinSymbolName() {
        return this.coinSymbolName;
    }

    public String getCoinSymbolImageUrl() {
        return this.coinSymbolImageUrl;
    }

    public Integer getCoinSymbolPrecision() {
        return this.coinSymbolPrecision;
    }

    public Integer getEnable() {
        return this.enable;
    }

    public Integer getSort() {
        return this.sort;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getLeverMultiple() {
        return this.leverMultiple;
    }

    public BigDecimal getOpenFee() {
        return this.openFee;
    }

    public BigDecimal getCloseFee() {
        return this.closeFee;
    }

    public BigDecimal getDayFeeRate() {
        return this.dayFeeRate;
    }

    public BigDecimal getMinLimit() {
        return this.minLimit;
    }

    public BigDecimal getMaxLimit() {
        return this.maxLimit;
    }

    public BigDecimal getMaxHoldLimit() {
        return this.maxHoldLimit;
    }

    public BigDecimal getRiseInterval() {
        return this.riseInterval;
    }

    public BigDecimal getFallInterval() {
        return this.fallInterval;
    }

    public int getContractType() {
        return this.contractType;
    }

    public int getCanBuyUp() {
        return this.canBuyUp;
    }

    public int getCanBuyDown() {
        return this.canBuyDown;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setBaseSymbol(final String baseSymbol) {
        this.baseSymbol = baseSymbol;
    }

    public void setCoinSymbol(final String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public void setCoinSymbolName(final String coinSymbolName) {
        this.coinSymbolName = coinSymbolName;
    }

    public void setCoinSymbolImageUrl(final String coinSymbolImageUrl) {
        this.coinSymbolImageUrl = coinSymbolImageUrl;
    }

    public void setCoinSymbolPrecision(final Integer coinSymbolPrecision) {
        this.coinSymbolPrecision = coinSymbolPrecision;
    }

    public void setEnable(final Integer enable) {
        this.enable = enable;
    }

    public void setSort(final Integer sort) {
        this.sort = sort;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setLeverMultiple(final String leverMultiple) {
        this.leverMultiple = leverMultiple;
    }

    public void setOpenFee(final BigDecimal openFee) {
        this.openFee = openFee;
    }

    public void setCloseFee(final BigDecimal closeFee) {
        this.closeFee = closeFee;
    }

    public void setDayFeeRate(final BigDecimal dayFeeRate) {
        this.dayFeeRate = dayFeeRate;
    }

    public void setMinLimit(final BigDecimal minLimit) {
        this.minLimit = minLimit;
    }

    public void setMaxLimit(final BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void setMaxHoldLimit(final BigDecimal maxHoldLimit) {
        this.maxHoldLimit = maxHoldLimit;
    }

    public void setRiseInterval(final BigDecimal riseInterval) {
        this.riseInterval = riseInterval;
    }

    public void setFallInterval(final BigDecimal fallInterval) {
        this.fallInterval = fallInterval;
    }

    public void setContractType(final int contractType) {
        this.contractType = contractType;
    }

    public void setCanBuyUp(final int canBuyUp) {
        this.canBuyUp = canBuyUp;
    }

    public void setCanBuyDown(final int canBuyDown) {
        this.canBuyDown = canBuyDown;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractCoin)) {
            return false;
        }
        final ContractCoin other = (ContractCoin) o;
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
        final Object this$baseSymbol = this.getBaseSymbol();
        final Object other$baseSymbol = other.getBaseSymbol();
        Label_0102:
        {
            if (this$baseSymbol == null) {
                if (other$baseSymbol == null) {
                    break Label_0102;
                }
            } else if (this$baseSymbol.equals(other$baseSymbol)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$coinSymbol = this.getCoinSymbol();
        final Object other$coinSymbol = other.getCoinSymbol();
        Label_0139:
        {
            if (this$coinSymbol == null) {
                if (other$coinSymbol == null) {
                    break Label_0139;
                }
            } else if (this$coinSymbol.equals(other$coinSymbol)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$coinSymbolName = this.getCoinSymbolName();
        final Object other$coinSymbolName = other.getCoinSymbolName();
        Label_0176:
        {
            if (this$coinSymbolName == null) {
                if (other$coinSymbolName == null) {
                    break Label_0176;
                }
            } else if (this$coinSymbolName.equals(other$coinSymbolName)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$coinSymbolImageUrl = this.getCoinSymbolImageUrl();
        final Object other$coinSymbolImageUrl = other.getCoinSymbolImageUrl();
        Label_0213:
        {
            if (this$coinSymbolImageUrl == null) {
                if (other$coinSymbolImageUrl == null) {
                    break Label_0213;
                }
            } else if (this$coinSymbolImageUrl.equals(other$coinSymbolImageUrl)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$coinSymbolPrecision = this.getCoinSymbolPrecision();
        final Object other$coinSymbolPrecision = other.getCoinSymbolPrecision();
        Label_0250:
        {
            if (this$coinSymbolPrecision == null) {
                if (other$coinSymbolPrecision == null) {
                    break Label_0250;
                }
            } else if (this$coinSymbolPrecision.equals(other$coinSymbolPrecision)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$enable = this.getEnable();
        final Object other$enable = other.getEnable();
        Label_0287:
        {
            if (this$enable == null) {
                if (other$enable == null) {
                    break Label_0287;
                }
            } else if (this$enable.equals(other$enable)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$sort = this.getSort();
        final Object other$sort = other.getSort();
        Label_0324:
        {
            if (this$sort == null) {
                if (other$sort == null) {
                    break Label_0324;
                }
            } else if (this$sort.equals(other$sort)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0361:
        {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0361;
                }
            } else if (this$symbol.equals(other$symbol)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$leverMultiple = this.getLeverMultiple();
        final Object other$leverMultiple = other.getLeverMultiple();
        Label_0398:
        {
            if (this$leverMultiple == null) {
                if (other$leverMultiple == null) {
                    break Label_0398;
                }
            } else if (this$leverMultiple.equals(other$leverMultiple)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$openFee = this.getOpenFee();
        final Object other$openFee = other.getOpenFee();
        Label_0435:
        {
            if (this$openFee == null) {
                if (other$openFee == null) {
                    break Label_0435;
                }
            } else if (this$openFee.equals(other$openFee)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$closeFee = this.getCloseFee();
        final Object other$closeFee = other.getCloseFee();
        Label_0472:
        {
            if (this$closeFee == null) {
                if (other$closeFee == null) {
                    break Label_0472;
                }
            } else if (this$closeFee.equals(other$closeFee)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$dayFeeRate = this.getDayFeeRate();
        final Object other$dayFeeRate = other.getDayFeeRate();
        Label_0509:
        {
            if (this$dayFeeRate == null) {
                if (other$dayFeeRate == null) {
                    break Label_0509;
                }
            } else if (this$dayFeeRate.equals(other$dayFeeRate)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$minLimit = this.getMinLimit();
        final Object other$minLimit = other.getMinLimit();
        Label_0546:
        {
            if (this$minLimit == null) {
                if (other$minLimit == null) {
                    break Label_0546;
                }
            } else if (this$minLimit.equals(other$minLimit)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$maxLimit = this.getMaxLimit();
        final Object other$maxLimit = other.getMaxLimit();
        Label_0583:
        {
            if (this$maxLimit == null) {
                if (other$maxLimit == null) {
                    break Label_0583;
                }
            } else if (this$maxLimit.equals(other$maxLimit)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$maxHoldLimit = this.getMaxHoldLimit();
        final Object other$maxHoldLimit = other.getMaxHoldLimit();
        Label_0620:
        {
            if (this$maxHoldLimit == null) {
                if (other$maxHoldLimit == null) {
                    break Label_0620;
                }
            } else if (this$maxHoldLimit.equals(other$maxHoldLimit)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$riseInterval = this.getRiseInterval();
        final Object other$riseInterval = other.getRiseInterval();
        Label_0657:
        {
            if (this$riseInterval == null) {
                if (other$riseInterval == null) {
                    break Label_0657;
                }
            } else if (this$riseInterval.equals(other$riseInterval)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$fallInterval = this.getFallInterval();
        final Object other$fallInterval = other.getFallInterval();
        if (this$fallInterval == null) {
            if (other$fallInterval == null) {
                return this.getContractType() == other.getContractType() && this.getCanBuyUp() == other.getCanBuyUp() && this.getCanBuyDown() == other.getCanBuyDown();
            }
        } else if (this$fallInterval.equals(other$fallInterval)) {
            return this.getContractType() == other.getContractType() && this.getCanBuyUp() == other.getCanBuyUp() && this.getCanBuyDown() == other.getCanBuyDown();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractCoin;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $baseSymbol = this.getBaseSymbol();
        result = result * 59 + (($baseSymbol == null) ? 43 : $baseSymbol.hashCode());
        final Object $coinSymbol = this.getCoinSymbol();
        result = result * 59 + (($coinSymbol == null) ? 43 : $coinSymbol.hashCode());
        final Object $coinSymbolName = this.getCoinSymbolName();
        result = result * 59 + (($coinSymbolName == null) ? 43 : $coinSymbolName.hashCode());
        final Object $coinSymbolImageUrl = this.getCoinSymbolImageUrl();
        result = result * 59 + (($coinSymbolImageUrl == null) ? 43 : $coinSymbolImageUrl.hashCode());
        final Object $coinSymbolPrecision = this.getCoinSymbolPrecision();
        result = result * 59 + (($coinSymbolPrecision == null) ? 43 : $coinSymbolPrecision.hashCode());
        final Object $enable = this.getEnable();
        result = result * 59 + (($enable == null) ? 43 : $enable.hashCode());
        final Object $sort = this.getSort();
        result = result * 59 + (($sort == null) ? 43 : $sort.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $leverMultiple = this.getLeverMultiple();
        result = result * 59 + (($leverMultiple == null) ? 43 : $leverMultiple.hashCode());
        final Object $openFee = this.getOpenFee();
        result = result * 59 + (($openFee == null) ? 43 : $openFee.hashCode());
        final Object $closeFee = this.getCloseFee();
        result = result * 59 + (($closeFee == null) ? 43 : $closeFee.hashCode());
        final Object $dayFeeRate = this.getDayFeeRate();
        result = result * 59 + (($dayFeeRate == null) ? 43 : $dayFeeRate.hashCode());
        final Object $minLimit = this.getMinLimit();
        result = result * 59 + (($minLimit == null) ? 43 : $minLimit.hashCode());
        final Object $maxLimit = this.getMaxLimit();
        result = result * 59 + (($maxLimit == null) ? 43 : $maxLimit.hashCode());
        final Object $maxHoldLimit = this.getMaxHoldLimit();
        result = result * 59 + (($maxHoldLimit == null) ? 43 : $maxHoldLimit.hashCode());
        final Object $riseInterval = this.getRiseInterval();
        result = result * 59 + (($riseInterval == null) ? 43 : $riseInterval.hashCode());
        final Object $fallInterval = this.getFallInterval();
        result = result * 59 + (($fallInterval == null) ? 43 : $fallInterval.hashCode());
        result = result * 59 + this.getContractType();
        result = result * 59 + this.getCanBuyUp();
        result = result * 59 + this.getCanBuyDown();
        return result;
    }

    @Override
    public String toString() {
        return "ContractCoin(id=" + this.getId() + ", baseSymbol=" + this.getBaseSymbol() + ", coinSymbol=" + this.getCoinSymbol() + ", coinSymbolName=" + this.getCoinSymbolName() + ", coinSymbolImageUrl=" + this.getCoinSymbolImageUrl() + ", coinSymbolPrecision=" + this.getCoinSymbolPrecision() + ", enable=" + this.getEnable() + ", sort=" + this.getSort() + ", symbol=" + this.getSymbol() + ", leverMultiple=" + this.getLeverMultiple() + ", openFee=" + this.getOpenFee() + ", closeFee=" + this.getCloseFee() + ", dayFeeRate=" + this.getDayFeeRate() + ", minLimit=" + this.getMinLimit() + ", maxLimit=" + this.getMaxLimit() + ", maxHoldLimit=" + this.getMaxHoldLimit() + ", riseInterval=" + this.getRiseInterval() + ", fallInterval=" + this.getFallInterval() + ", contractType=" + this.getContractType() + ", canBuyUp=" + this.getCanBuyUp() + ", canBuyDown=" + this.getCanBuyDown() + ")";
    }
}

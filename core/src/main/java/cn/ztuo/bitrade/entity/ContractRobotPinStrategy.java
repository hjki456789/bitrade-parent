package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.enums.*;

import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table
public class ContractRobotPinStrategy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String symbol;
    private ContractRobotPinStrategyStatus status;
    private ContractRobotPinStrategySide side;
    private BigDecimal priceRangeMin;
    private BigDecimal priceRangeMax;
    private BigDecimal amountRangeMin;
    private BigDecimal amountRangeMax;
    private BigDecimal targetPrice;
    private BigDecimal antiDirectionMultiple;
    private Integer frequency;
    private Long startEffectTime;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Date updateTime;
    @Transient
    private String startEffectTimeStr;

    public Long getId() {
        return this.id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public ContractRobotPinStrategyStatus getStatus() {
        return this.status;
    }

    public ContractRobotPinStrategySide getSide() {
        return this.side;
    }

    public BigDecimal getPriceRangeMin() {
        return this.priceRangeMin;
    }

    public BigDecimal getPriceRangeMax() {
        return this.priceRangeMax;
    }

    public BigDecimal getAmountRangeMin() {
        return this.amountRangeMin;
    }

    public BigDecimal getAmountRangeMax() {
        return this.amountRangeMax;
    }

    public BigDecimal getTargetPrice() {
        return this.targetPrice;
    }

    public BigDecimal getAntiDirectionMultiple() {
        return this.antiDirectionMultiple;
    }

    public Integer getFrequency() {
        return this.frequency;
    }

    public Long getStartEffectTime() {
        return this.startEffectTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getStartEffectTimeStr() {
        return this.startEffectTimeStr;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setStatus(final ContractRobotPinStrategyStatus status) {
        this.status = status;
    }

    public void setSide(final ContractRobotPinStrategySide side) {
        this.side = side;
    }

    public void setPriceRangeMin(final BigDecimal priceRangeMin) {
        this.priceRangeMin = priceRangeMin;
    }

    public void setPriceRangeMax(final BigDecimal priceRangeMax) {
        this.priceRangeMax = priceRangeMax;
    }

    public void setAmountRangeMin(final BigDecimal amountRangeMin) {
        this.amountRangeMin = amountRangeMin;
    }

    public void setAmountRangeMax(final BigDecimal amountRangeMax) {
        this.amountRangeMax = amountRangeMax;
    }

    public void setTargetPrice(final BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public void setAntiDirectionMultiple(final BigDecimal antiDirectionMultiple) {
        this.antiDirectionMultiple = antiDirectionMultiple;
    }

    public void setFrequency(final Integer frequency) {
        this.frequency = frequency;
    }

    public void setStartEffectTime(final Long startEffectTime) {
        this.startEffectTime = startEffectTime;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setStartEffectTimeStr(final String startEffectTimeStr) {
        this.startEffectTimeStr = startEffectTimeStr;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractRobotPinStrategy)) {
            return false;
        }
        final ContractRobotPinStrategy other = (ContractRobotPinStrategy) o;
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
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0102:
        {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0102;
                }
            } else if (this$symbol.equals(other$symbol)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0139:
        {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0139;
                }
            } else if (this$status.equals(other$status)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$side = this.getSide();
        final Object other$side = other.getSide();
        Label_0176:
        {
            if (this$side == null) {
                if (other$side == null) {
                    break Label_0176;
                }
            } else if (this$side.equals(other$side)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$priceRangeMin = this.getPriceRangeMin();
        final Object other$priceRangeMin = other.getPriceRangeMin();
        Label_0213:
        {
            if (this$priceRangeMin == null) {
                if (other$priceRangeMin == null) {
                    break Label_0213;
                }
            } else if (this$priceRangeMin.equals(other$priceRangeMin)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$priceRangeMax = this.getPriceRangeMax();
        final Object other$priceRangeMax = other.getPriceRangeMax();
        Label_0250:
        {
            if (this$priceRangeMax == null) {
                if (other$priceRangeMax == null) {
                    break Label_0250;
                }
            } else if (this$priceRangeMax.equals(other$priceRangeMax)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$amountRangeMin = this.getAmountRangeMin();
        final Object other$amountRangeMin = other.getAmountRangeMin();
        Label_0287:
        {
            if (this$amountRangeMin == null) {
                if (other$amountRangeMin == null) {
                    break Label_0287;
                }
            } else if (this$amountRangeMin.equals(other$amountRangeMin)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$amountRangeMax = this.getAmountRangeMax();
        final Object other$amountRangeMax = other.getAmountRangeMax();
        Label_0324:
        {
            if (this$amountRangeMax == null) {
                if (other$amountRangeMax == null) {
                    break Label_0324;
                }
            } else if (this$amountRangeMax.equals(other$amountRangeMax)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$targetPrice = this.getTargetPrice();
        final Object other$targetPrice = other.getTargetPrice();
        Label_0361:
        {
            if (this$targetPrice == null) {
                if (other$targetPrice == null) {
                    break Label_0361;
                }
            } else if (this$targetPrice.equals(other$targetPrice)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$antiDirectionMultiple = this.getAntiDirectionMultiple();
        final Object other$antiDirectionMultiple = other.getAntiDirectionMultiple();
        Label_0398:
        {
            if (this$antiDirectionMultiple == null) {
                if (other$antiDirectionMultiple == null) {
                    break Label_0398;
                }
            } else if (this$antiDirectionMultiple.equals(other$antiDirectionMultiple)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$frequency = this.getFrequency();
        final Object other$frequency = other.getFrequency();
        Label_0435:
        {
            if (this$frequency == null) {
                if (other$frequency == null) {
                    break Label_0435;
                }
            } else if (this$frequency.equals(other$frequency)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$startEffectTime = this.getStartEffectTime();
        final Object other$startEffectTime = other.getStartEffectTime();
        Label_0472:
        {
            if (this$startEffectTime == null) {
                if (other$startEffectTime == null) {
                    break Label_0472;
                }
            } else if (this$startEffectTime.equals(other$startEffectTime)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0509:
        {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0509;
                }
            } else if (this$remark.equals(other$remark)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0546:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0546;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0583:
        {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0583;
                }
            } else if (this$updateTime.equals(other$updateTime)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$startEffectTimeStr = this.getStartEffectTimeStr();
        final Object other$startEffectTimeStr = other.getStartEffectTimeStr();
        if (this$startEffectTimeStr == null) {
            if (other$startEffectTimeStr == null) {
                return true;
            }
        } else if (this$startEffectTimeStr.equals(other$startEffectTimeStr)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractRobotPinStrategy;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $side = this.getSide();
        result = result * 59 + (($side == null) ? 43 : $side.hashCode());
        final Object $priceRangeMin = this.getPriceRangeMin();
        result = result * 59 + (($priceRangeMin == null) ? 43 : $priceRangeMin.hashCode());
        final Object $priceRangeMax = this.getPriceRangeMax();
        result = result * 59 + (($priceRangeMax == null) ? 43 : $priceRangeMax.hashCode());
        final Object $amountRangeMin = this.getAmountRangeMin();
        result = result * 59 + (($amountRangeMin == null) ? 43 : $amountRangeMin.hashCode());
        final Object $amountRangeMax = this.getAmountRangeMax();
        result = result * 59 + (($amountRangeMax == null) ? 43 : $amountRangeMax.hashCode());
        final Object $targetPrice = this.getTargetPrice();
        result = result * 59 + (($targetPrice == null) ? 43 : $targetPrice.hashCode());
        final Object $antiDirectionMultiple = this.getAntiDirectionMultiple();
        result = result * 59 + (($antiDirectionMultiple == null) ? 43 : $antiDirectionMultiple.hashCode());
        final Object $frequency = this.getFrequency();
        result = result * 59 + (($frequency == null) ? 43 : $frequency.hashCode());
        final Object $startEffectTime = this.getStartEffectTime();
        result = result * 59 + (($startEffectTime == null) ? 43 : $startEffectTime.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $startEffectTimeStr = this.getStartEffectTimeStr();
        result = result * 59 + (($startEffectTimeStr == null) ? 43 : $startEffectTimeStr.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractRobotPinStrategy(id=" + this.getId() + ", symbol=" + this.getSymbol() + ", status=" + this.getStatus() + ", side=" + this.getSide() + ", priceRangeMin=" + this.getPriceRangeMin() + ", priceRangeMax=" + this.getPriceRangeMax() + ", amountRangeMin=" + this.getAmountRangeMin() + ", amountRangeMax=" + this.getAmountRangeMax() + ", targetPrice=" + this.getTargetPrice() + ", antiDirectionMultiple=" + this.getAntiDirectionMultiple() + ", frequency=" + this.getFrequency() + ", startEffectTime=" + this.getStartEffectTime() + ", remark=" + this.getRemark() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", startEffectTimeStr=" + this.getStartEffectTimeStr() + ")";
    }
}

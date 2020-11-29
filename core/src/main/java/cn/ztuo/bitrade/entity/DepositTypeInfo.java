package cn.ztuo.bitrade.entity;

import javax.persistence.*;
import java.math.*;

@Entity
@Table
public class DepositTypeInfo
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private int cycle;
    private BigDecimal dayProfitRate;
    private BigDecimal investmentMin;
    private BigDecimal investmentMax;
    private String remark;
    private int status;
    private String investCoin;
    private String profitCoin;
    private BigDecimal breakRate;
    
    public Long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getCycle() {
        return this.cycle;
    }
    
    public BigDecimal getDayProfitRate() {
        return this.dayProfitRate;
    }
    
    public BigDecimal getInvestmentMin() {
        return this.investmentMin;
    }
    
    public BigDecimal getInvestmentMax() {
        return this.investmentMax;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getInvestCoin() {
        return this.investCoin;
    }
    
    public String getProfitCoin() {
        return this.profitCoin;
    }
    
    public BigDecimal getBreakRate() {
        return this.breakRate;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setCycle(final int cycle) {
        this.cycle = cycle;
    }
    
    public void setDayProfitRate(final BigDecimal dayProfitRate) {
        this.dayProfitRate = dayProfitRate;
    }
    
    public void setInvestmentMin(final BigDecimal investmentMin) {
        this.investmentMin = investmentMin;
    }
    
    public void setInvestmentMax(final BigDecimal investmentMax) {
        this.investmentMax = investmentMax;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setInvestCoin(final String investCoin) {
        this.investCoin = investCoin;
    }
    
    public void setProfitCoin(final String profitCoin) {
        this.profitCoin = profitCoin;
    }
    
    public void setBreakRate(final BigDecimal breakRate) {
        this.breakRate = breakRate;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DepositTypeInfo)) {
            return false;
        }
        final DepositTypeInfo other = (DepositTypeInfo)o;
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
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0102: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0102;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0102;
            }
            return false;
        }
        if (this.getCycle() != other.getCycle()) {
            return false;
        }
        final Object this$dayProfitRate = this.getDayProfitRate();
        final Object other$dayProfitRate = other.getDayProfitRate();
        Label_0152: {
            if (this$dayProfitRate == null) {
                if (other$dayProfitRate == null) {
                    break Label_0152;
                }
            }
            else if (this$dayProfitRate.equals(other$dayProfitRate)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$investmentMin = this.getInvestmentMin();
        final Object other$investmentMin = other.getInvestmentMin();
        Label_0189: {
            if (this$investmentMin == null) {
                if (other$investmentMin == null) {
                    break Label_0189;
                }
            }
            else if (this$investmentMin.equals(other$investmentMin)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$investmentMax = this.getInvestmentMax();
        final Object other$investmentMax = other.getInvestmentMax();
        Label_0226: {
            if (this$investmentMax == null) {
                if (other$investmentMax == null) {
                    break Label_0226;
                }
            }
            else if (this$investmentMax.equals(other$investmentMax)) {
                break Label_0226;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0263: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0263;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0263;
            }
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$investCoin = this.getInvestCoin();
        final Object other$investCoin = other.getInvestCoin();
        Label_0313: {
            if (this$investCoin == null) {
                if (other$investCoin == null) {
                    break Label_0313;
                }
            }
            else if (this$investCoin.equals(other$investCoin)) {
                break Label_0313;
            }
            return false;
        }
        final Object this$profitCoin = this.getProfitCoin();
        final Object other$profitCoin = other.getProfitCoin();
        Label_0350: {
            if (this$profitCoin == null) {
                if (other$profitCoin == null) {
                    break Label_0350;
                }
            }
            else if (this$profitCoin.equals(other$profitCoin)) {
                break Label_0350;
            }
            return false;
        }
        final Object this$breakRate = this.getBreakRate();
        final Object other$breakRate = other.getBreakRate();
        if (this$breakRate == null) {
            if (other$breakRate == null) {
                return true;
            }
        }
        else if (this$breakRate.equals(other$breakRate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof DepositTypeInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        result = result * 59 + this.getCycle();
        final Object $dayProfitRate = this.getDayProfitRate();
        result = result * 59 + (($dayProfitRate == null) ? 43 : $dayProfitRate.hashCode());
        final Object $investmentMin = this.getInvestmentMin();
        result = result * 59 + (($investmentMin == null) ? 43 : $investmentMin.hashCode());
        final Object $investmentMax = this.getInvestmentMax();
        result = result * 59 + (($investmentMax == null) ? 43 : $investmentMax.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        result = result * 59 + this.getStatus();
        final Object $investCoin = this.getInvestCoin();
        result = result * 59 + (($investCoin == null) ? 43 : $investCoin.hashCode());
        final Object $profitCoin = this.getProfitCoin();
        result = result * 59 + (($profitCoin == null) ? 43 : $profitCoin.hashCode());
        final Object $breakRate = this.getBreakRate();
        result = result * 59 + (($breakRate == null) ? 43 : $breakRate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "DepositTypeInfo(id=" + this.getId() + ", name=" + this.getName() + ", cycle=" + this.getCycle() + ", dayProfitRate=" + this.getDayProfitRate() + ", investmentMin=" + this.getInvestmentMin() + ", investmentMax=" + this.getInvestmentMax() + ", remark=" + this.getRemark() + ", status=" + this.getStatus() + ", investCoin=" + this.getInvestCoin() + ", profitCoin=" + this.getProfitCoin() + ", breakRate=" + this.getBreakRate() + ")";
    }
}

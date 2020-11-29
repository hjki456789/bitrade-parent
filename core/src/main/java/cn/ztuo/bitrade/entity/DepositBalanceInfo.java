package cn.ztuo.bitrade.entity;

import java.math.*;

public class DepositBalanceInfo
{
    private BigDecimal balance;
    private BigDecimal totalProfit;
    private BigDecimal staticProfit;
    private BigDecimal recommendProfit;
    private BigDecimal depositAmount;
    
    public DepositBalanceInfo() {
        this.balance = BigDecimal.ZERO;
        this.totalProfit = BigDecimal.ZERO;
        this.staticProfit = BigDecimal.ZERO;
        this.recommendProfit = BigDecimal.ZERO;
        this.depositAmount = BigDecimal.ZERO;
    }
    
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public BigDecimal getTotalProfit() {
        return this.totalProfit;
    }
    
    public BigDecimal getStaticProfit() {
        return this.staticProfit;
    }
    
    public BigDecimal getRecommendProfit() {
        return this.recommendProfit;
    }
    
    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }
    
    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }
    
    public void setTotalProfit(final BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }
    
    public void setStaticProfit(final BigDecimal staticProfit) {
        this.staticProfit = staticProfit;
    }
    
    public void setRecommendProfit(final BigDecimal recommendProfit) {
        this.recommendProfit = recommendProfit;
    }
    
    public void setDepositAmount(final BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DepositBalanceInfo)) {
            return false;
        }
        final DepositBalanceInfo other = (DepositBalanceInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        Label_0065: {
            if (this$balance == null) {
                if (other$balance == null) {
                    break Label_0065;
                }
            }
            else if (this$balance.equals(other$balance)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$totalProfit = this.getTotalProfit();
        final Object other$totalProfit = other.getTotalProfit();
        Label_0102: {
            if (this$totalProfit == null) {
                if (other$totalProfit == null) {
                    break Label_0102;
                }
            }
            else if (this$totalProfit.equals(other$totalProfit)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$staticProfit = this.getStaticProfit();
        final Object other$staticProfit = other.getStaticProfit();
        Label_0139: {
            if (this$staticProfit == null) {
                if (other$staticProfit == null) {
                    break Label_0139;
                }
            }
            else if (this$staticProfit.equals(other$staticProfit)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$recommendProfit = this.getRecommendProfit();
        final Object other$recommendProfit = other.getRecommendProfit();
        Label_0176: {
            if (this$recommendProfit == null) {
                if (other$recommendProfit == null) {
                    break Label_0176;
                }
            }
            else if (this$recommendProfit.equals(other$recommendProfit)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$depositAmount = this.getDepositAmount();
        final Object other$depositAmount = other.getDepositAmount();
        if (this$depositAmount == null) {
            if (other$depositAmount == null) {
                return true;
            }
        }
        else if (this$depositAmount.equals(other$depositAmount)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof DepositBalanceInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $balance = this.getBalance();
        result = result * 59 + (($balance == null) ? 43 : $balance.hashCode());
        final Object $totalProfit = this.getTotalProfit();
        result = result * 59 + (($totalProfit == null) ? 43 : $totalProfit.hashCode());
        final Object $staticProfit = this.getStaticProfit();
        result = result * 59 + (($staticProfit == null) ? 43 : $staticProfit.hashCode());
        final Object $recommendProfit = this.getRecommendProfit();
        result = result * 59 + (($recommendProfit == null) ? 43 : $recommendProfit.hashCode());
        final Object $depositAmount = this.getDepositAmount();
        result = result * 59 + (($depositAmount == null) ? 43 : $depositAmount.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "DepositBalanceInfo(balance=" + this.getBalance() + ", totalProfit=" + this.getTotalProfit() + ", staticProfit=" + this.getStaticProfit() + ", recommendProfit=" + this.getRecommendProfit() + ", depositAmount=" + this.getDepositAmount() + ")";
    }
}

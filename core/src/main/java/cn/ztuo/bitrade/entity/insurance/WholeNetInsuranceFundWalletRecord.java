package cn.ztuo.bitrade.entity.insurance;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import cn.ztuo.bitrade.enums.*;
import java.util.*;

@Entity
@Table
public class WholeNetInsuranceFundWalletRecord implements Serializable
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private BigDecimal amount;
    private String coin;
    private WholeNetInsuranceFundWalletRecordType type;
    private String remark;
    private Date createTime;
    private Long sequence;
    
    public Long getId() {
        return this.id;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public String getCoin() {
        return this.coin;
    }
    
    public WholeNetInsuranceFundWalletRecordType getType() {
        return this.type;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public Long getSequence() {
        return this.sequence;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setCoin(final String coin) {
        this.coin = coin;
    }
    
    public void setType(final WholeNetInsuranceFundWalletRecordType type) {
        this.type = type;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WholeNetInsuranceFundWalletRecord)) {
            return false;
        }
        final WholeNetInsuranceFundWalletRecord other = (WholeNetInsuranceFundWalletRecord)o;
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
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0102: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0102;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0139: {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0139;
                }
            }
            else if (this$coin.equals(other$coin)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0176: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0176;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0213: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0213;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0250: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0250;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        if (this$sequence == null) {
            if (other$sequence == null) {
                return true;
            }
        }
        else if (this$sequence.equals(other$sequence)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof WholeNetInsuranceFundWalletRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "WholeNetInsuranceFundWalletRecord(id=" + this.getId() + ", amount=" + this.getAmount() + ", coin=" + this.getCoin() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}

package cn.ztuo.bitrade.entity;

import javax.persistence.*;
import java.math.*;

@Entity
public class WalletConfig
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Integer type;
    private String coinName;
    private String address;
    private BigDecimal balance;
    private BigDecimal collectRate;
    
    public Long getId() {
        return this.id;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public String getCoinName() {
        return this.coinName;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public BigDecimal getCollectRate() {
        return this.collectRate;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setType(final Integer type) {
        this.type = type;
    }
    
    public void setCoinName(final String coinName) {
        this.coinName = coinName;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }
    
    public void setCollectRate(final BigDecimal collectRate) {
        this.collectRate = collectRate;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WalletConfig)) {
            return false;
        }
        final WalletConfig other = (WalletConfig)o;
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
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0102: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0102;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$coinName = this.getCoinName();
        final Object other$coinName = other.getCoinName();
        Label_0139: {
            if (this$coinName == null) {
                if (other$coinName == null) {
                    break Label_0139;
                }
            }
            else if (this$coinName.equals(other$coinName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        Label_0176: {
            if (this$address == null) {
                if (other$address == null) {
                    break Label_0176;
                }
            }
            else if (this$address.equals(other$address)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        Label_0213: {
            if (this$balance == null) {
                if (other$balance == null) {
                    break Label_0213;
                }
            }
            else if (this$balance.equals(other$balance)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$collectRate = this.getCollectRate();
        final Object other$collectRate = other.getCollectRate();
        if (this$collectRate == null) {
            if (other$collectRate == null) {
                return true;
            }
        }
        else if (this$collectRate.equals(other$collectRate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof WalletConfig;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $coinName = this.getCoinName();
        result = result * 59 + (($coinName == null) ? 43 : $coinName.hashCode());
        final Object $address = this.getAddress();
        result = result * 59 + (($address == null) ? 43 : $address.hashCode());
        final Object $balance = this.getBalance();
        result = result * 59 + (($balance == null) ? 43 : $balance.hashCode());
        final Object $collectRate = this.getCollectRate();
        result = result * 59 + (($collectRate == null) ? 43 : $collectRate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "WalletConfig(id=" + this.getId() + ", type=" + this.getType() + ", coinName=" + this.getCoinName() + ", address=" + this.getAddress() + ", balance=" + this.getBalance() + ", collectRate=" + this.getCollectRate() + ")";
    }
}

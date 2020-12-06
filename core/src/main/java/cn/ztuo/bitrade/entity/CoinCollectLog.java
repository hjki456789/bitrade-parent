package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "coin_collect_log")
public class CoinCollectLog implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String coinName;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
    private String address;
    private String toAddress;
    private BigDecimal amount;
    private String txHash;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public Long getId() {
        return this.id;
    }

    public String getCoinName() {
        return this.coinName;
    }

    public Member getMember() {
        return this.member;
    }

    public String getAddress() {
        return this.address;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public String getTxHash() {
        return this.txHash;
    }

    public Date getTime() {
        return this.time;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCoinName(final String coinName) {
        this.coinName = coinName;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setToAddress(final String toAddress) {
        this.toAddress = toAddress;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setTxHash(final String txHash) {
        this.txHash = txHash;
    }

    public void setTime(final Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CoinCollectLog)) {
            return false;
        }
        final CoinCollectLog other = (CoinCollectLog) o;
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
        final Object this$coinName = this.getCoinName();
        final Object other$coinName = other.getCoinName();
        Label_0102:
        {
            if (this$coinName == null) {
                if (other$coinName == null) {
                    break Label_0102;
                }
            } else if (this$coinName.equals(other$coinName)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0139:
        {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0139;
                }
            } else if (this$member.equals(other$member)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        Label_0176:
        {
            if (this$address == null) {
                if (other$address == null) {
                    break Label_0176;
                }
            } else if (this$address.equals(other$address)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$toAddress = this.getToAddress();
        final Object other$toAddress = other.getToAddress();
        Label_0213:
        {
            if (this$toAddress == null) {
                if (other$toAddress == null) {
                    break Label_0213;
                }
            } else if (this$toAddress.equals(other$toAddress)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0250:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0250;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$txHash = this.getTxHash();
        final Object other$txHash = other.getTxHash();
        Label_0287:
        {
            if (this$txHash == null) {
                if (other$txHash == null) {
                    break Label_0287;
                }
            } else if (this$txHash.equals(other$txHash)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null) {
            if (other$time == null) {
                return true;
            }
        } else if (this$time.equals(other$time)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CoinCollectLog;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $coinName = this.getCoinName();
        result = result * 59 + (($coinName == null) ? 43 : $coinName.hashCode());
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $address = this.getAddress();
        result = result * 59 + (($address == null) ? 43 : $address.hashCode());
        final Object $toAddress = this.getToAddress();
        result = result * 59 + (($toAddress == null) ? 43 : $toAddress.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $txHash = this.getTxHash();
        result = result * 59 + (($txHash == null) ? 43 : $txHash.hashCode());
        final Object $time = this.getTime();
        result = result * 59 + (($time == null) ? 43 : $time.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CoinCollectLog(id=" + this.getId() + ", coinName=" + this.getCoinName() + ", member=" + this.getMember() + ", address=" + this.getAddress() + ", toAddress=" + this.getToAddress() + ", amount=" + this.getAmount() + ", txHash=" + this.getTxHash() + ", time=" + this.getTime() + ")";
    }
}

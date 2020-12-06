package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table
public class ContractWalletTransferRecord implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
    private BigDecimal amount;
    private int type;
    @JoinColumn(name = "coin_id")
    @ManyToOne
    private Coin coin;
    private long version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private long sequence;

    public ContractWalletTransferRecord() {
        this.amount = BigDecimal.ZERO;
    }

    public long getId() {
        return this.id;
    }

    public Member getMember() {
        return this.member;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public int getType() {
        return this.type;
    }

    public Coin getCoin() {
        return this.coin;
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

    public void setId(final long id) {
        this.id = id;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public void setCoin(final Coin coin) {
        this.coin = coin;
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

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractWalletTransferRecord)) {
            return false;
        }
        final ContractWalletTransferRecord other = (ContractWalletTransferRecord) o;
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
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0116:
        {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0116;
                }
            } else if (this$amount.equals(other$amount)) {
                break Label_0116;
            }
            return false;
        }
        if (this.getType() != other.getType()) {
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0166:
        {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0166;
                }
            } else if (this$coin.equals(other$coin)) {
                break Label_0166;
            }
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null) {
            if (other$createTime == null) {
                return this.getSequence() == other.getSequence();
            }
        } else if (this$createTime.equals(other$createTime)) {
            return this.getSequence() == other.getSequence();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractWalletTransferRecord;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int) ($id >>> 32 ^ $id);
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        result = result * 59 + this.getType();
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final long $version = this.getVersion();
        result = result * 59 + (int) ($version >>> 32 ^ $version);
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final long $sequence = this.getSequence();
        result = result * 59 + (int) ($sequence >>> 32 ^ $sequence);
        return result;
    }

    @Override
    public String toString() {
        return "ContractWalletTransferRecord(id=" + this.getId() + ", member=" + this.getMember() + ", amount=" + this.getAmount() + ", type=" + this.getType() + ", coin=" + this.getCoin() + ", version=" + this.getVersion() + ", createTime=" + this.getCreateTime() + ", sequence=" + this.getSequence() + ")";
    }
}

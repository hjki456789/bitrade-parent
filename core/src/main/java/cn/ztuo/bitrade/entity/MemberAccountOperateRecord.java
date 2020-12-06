package cn.ztuo.bitrade.entity;

import javax.persistence.*;
import java.math.*;

import cn.ztuo.bitrade.constant.*;

import java.util.*;

@Entity
public class MemberAccountOperateRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private BigDecimal changeAmount;
    private String coin;
    private BalanceTypeEnum balanceType;
    private AccountTypeEnum accountType;
    private String adminUserId;
    private Long sequence;
    private Date createTime;

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public BigDecimal getChangeAmount() {
        return this.changeAmount;
    }

    public String getCoin() {
        return this.coin;
    }

    public BalanceTypeEnum getBalanceType() {
        return this.balanceType;
    }

    public AccountTypeEnum getAccountType() {
        return this.accountType;
    }

    public String getAdminUserId() {
        return this.adminUserId;
    }

    public Long getSequence() {
        return this.sequence;
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

    public void setChangeAmount(final BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public void setCoin(final String coin) {
        this.coin = coin;
    }

    public void setBalanceType(final BalanceTypeEnum balanceType) {
        this.balanceType = balanceType;
    }

    public void setAccountType(final AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public void setAdminUserId(final String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MemberAccountOperateRecord)) {
            return false;
        }
        final MemberAccountOperateRecord other = (MemberAccountOperateRecord) o;
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
        final Object this$changeAmount = this.getChangeAmount();
        final Object other$changeAmount = other.getChangeAmount();
        Label_0139:
        {
            if (this$changeAmount == null) {
                if (other$changeAmount == null) {
                    break Label_0139;
                }
            } else if (this$changeAmount.equals(other$changeAmount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0176:
        {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0176;
                }
            } else if (this$coin.equals(other$coin)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$balanceType = this.getBalanceType();
        final Object other$balanceType = other.getBalanceType();
        Label_0213:
        {
            if (this$balanceType == null) {
                if (other$balanceType == null) {
                    break Label_0213;
                }
            } else if (this$balanceType.equals(other$balanceType)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$accountType = this.getAccountType();
        final Object other$accountType = other.getAccountType();
        Label_0250:
        {
            if (this$accountType == null) {
                if (other$accountType == null) {
                    break Label_0250;
                }
            } else if (this$accountType.equals(other$accountType)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$adminUserId = this.getAdminUserId();
        final Object other$adminUserId = other.getAdminUserId();
        Label_0287:
        {
            if (this$adminUserId == null) {
                if (other$adminUserId == null) {
                    break Label_0287;
                }
            } else if (this$adminUserId.equals(other$adminUserId)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        Label_0324:
        {
            if (this$sequence == null) {
                if (other$sequence == null) {
                    break Label_0324;
                }
            } else if (this$sequence.equals(other$sequence)) {
                break Label_0324;
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
        return other instanceof MemberAccountOperateRecord;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $changeAmount = this.getChangeAmount();
        result = result * 59 + (($changeAmount == null) ? 43 : $changeAmount.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $balanceType = this.getBalanceType();
        result = result * 59 + (($balanceType == null) ? 43 : $balanceType.hashCode());
        final Object $accountType = this.getAccountType();
        result = result * 59 + (($accountType == null) ? 43 : $accountType.hashCode());
        final Object $adminUserId = this.getAdminUserId();
        result = result * 59 + (($adminUserId == null) ? 43 : $adminUserId.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MemberAccountOperateRecord(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", changeAmount=" + this.getChangeAmount() + ", coin=" + this.getCoin() + ", balanceType=" + this.getBalanceType() + ", accountType=" + this.getAccountType() + ", adminUserId=" + this.getAdminUserId() + ", sequence=" + this.getSequence() + ", createTime=" + this.getCreateTime() + ")";
    }
}

package cn.ztuo.bitrade.entity;

import java.io.*;
import java.math.*;
import javax.persistence.*;

@Entity
@Table
public class ContractWallet implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private BigDecimal balance;
    private BigDecimal frozenBalance;
    private BigDecimal virtualRechargeFrozenBalance;
    private int is_lock;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
    private int status;
    private Long version;
    @JoinColumn(name = "coin_id")
    @ManyToOne
    private Coin coin;

    public ContractWallet() {
        this.balance = BigDecimal.ZERO;
        this.frozenBalance = BigDecimal.ZERO;
        this.virtualRechargeFrozenBalance = BigDecimal.ZERO;
        this.is_lock = 0;
        this.status = 0;
        this.version = 0L;
    }

    public Long getId() {
        return this.id;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public BigDecimal getFrozenBalance() {
        return this.frozenBalance;
    }

    public BigDecimal getVirtualRechargeFrozenBalance() {
        return this.virtualRechargeFrozenBalance;
    }

    public int getIs_lock() {
        return this.is_lock;
    }

    public Member getMember() {
        return this.member;
    }

    public int getStatus() {
        return this.status;
    }

    public Long getVersion() {
        return this.version;
    }

    public Coin getCoin() {
        return this.coin;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public void setFrozenBalance(final BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public void setVirtualRechargeFrozenBalance(final BigDecimal virtualRechargeFrozenBalance) {
        this.virtualRechargeFrozenBalance = virtualRechargeFrozenBalance;
    }

    public void setIs_lock(final int is_lock) {
        this.is_lock = is_lock;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public void setCoin(final Coin coin) {
        this.coin = coin;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractWallet)) {
            return false;
        }
        final ContractWallet other = (ContractWallet) o;
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
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        Label_0102:
        {
            if (this$balance == null) {
                if (other$balance == null) {
                    break Label_0102;
                }
            } else if (this$balance.equals(other$balance)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$frozenBalance = this.getFrozenBalance();
        final Object other$frozenBalance = other.getFrozenBalance();
        Label_0139:
        {
            if (this$frozenBalance == null) {
                if (other$frozenBalance == null) {
                    break Label_0139;
                }
            } else if (this$frozenBalance.equals(other$frozenBalance)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$virtualRechargeFrozenBalance = this.getVirtualRechargeFrozenBalance();
        final Object other$virtualRechargeFrozenBalance = other.getVirtualRechargeFrozenBalance();
        Label_0176:
        {
            if (this$virtualRechargeFrozenBalance == null) {
                if (other$virtualRechargeFrozenBalance == null) {
                    break Label_0176;
                }
            } else if (this$virtualRechargeFrozenBalance.equals(other$virtualRechargeFrozenBalance)) {
                break Label_0176;
            }
            return false;
        }
        if (this.getIs_lock() != other.getIs_lock()) {
            return false;
        }
        final Object this$member = this.getMember();
        final Object other$member = other.getMember();
        Label_0226:
        {
            if (this$member == null) {
                if (other$member == null) {
                    break Label_0226;
                }
            } else if (this$member.equals(other$member)) {
                break Label_0226;
            }
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        Label_0276:
        {
            if (this$version == null) {
                if (other$version == null) {
                    break Label_0276;
                }
            } else if (this$version.equals(other$version)) {
                break Label_0276;
            }
            return false;
        }
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        if (this$coin == null) {
            if (other$coin == null) {
                return true;
            }
        } else if (this$coin.equals(other$coin)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractWallet;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $balance = this.getBalance();
        result = result * 59 + (($balance == null) ? 43 : $balance.hashCode());
        final Object $frozenBalance = this.getFrozenBalance();
        result = result * 59 + (($frozenBalance == null) ? 43 : $frozenBalance.hashCode());
        final Object $virtualRechargeFrozenBalance = this.getVirtualRechargeFrozenBalance();
        result = result * 59 + (($virtualRechargeFrozenBalance == null) ? 43 : $virtualRechargeFrozenBalance.hashCode());
        result = result * 59 + this.getIs_lock();
        final Object $member = this.getMember();
        result = result * 59 + (($member == null) ? 43 : $member.hashCode());
        result = result * 59 + this.getStatus();
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractWallet(id=" + this.getId() + ", balance=" + this.getBalance() + ", frozenBalance=" + this.getFrozenBalance() + ", virtualRechargeFrozenBalance=" + this.getVirtualRechargeFrozenBalance() + ", is_lock=" + this.getIs_lock() + ", member=" + this.getMember() + ", status=" + this.getStatus() + ", version=" + this.getVersion() + ", coin=" + this.getCoin() + ")";
    }
}

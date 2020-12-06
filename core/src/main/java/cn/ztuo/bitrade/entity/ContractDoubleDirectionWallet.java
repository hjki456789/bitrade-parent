package cn.ztuo.bitrade.entity;

import java.math.*;

import com.fasterxml.jackson.annotation.*;
import cn.ztuo.bitrade.constant.*;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"memberId", "coin_id"})})
public class ContractDoubleDirectionWallet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
    @Column(columnDefinition = "decimal(18,8) comment '\u53ef\u7528\u4f59\u989d'")
    private BigDecimal balance;
    @Column(columnDefinition = "decimal(18,8) comment '\u51bb\u7ed3\u4f59\u989d'")
    private BigDecimal frozenBalance;
    @JsonIgnore
    @Version
    private int version;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "int default 0 comment '\u94b1\u5305\u4e0d\u662f\u9501\u5b9a'")
    private BooleanEnum isLock;
    @Transient
    private String username;
    @Transient
    private String mobilePhone;
    @Transient
    private String realName;
    @Transient
    private String email;

    public ContractDoubleDirectionWallet() {
        this.balance = BigDecimal.ZERO;
        this.frozenBalance = BigDecimal.ZERO;
        this.isLock = BooleanEnum.IS_FALSE;
    }

    public Long getId() {
        return this.id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public Coin getCoin() {
        return this.coin;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public BigDecimal getFrozenBalance() {
        return this.frozenBalance;
    }

    public int getVersion() {
        return this.version;
    }

    public BooleanEnum getIsLock() {
        return this.isLock;
    }

    public String getUsername() {
        return this.username;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public String getRealName() {
        return this.realName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setCoin(final Coin coin) {
        this.coin = coin;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public void setFrozenBalance(final BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public void setIsLock(final BooleanEnum isLock) {
        this.isLock = isLock;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setRealName(final String realName) {
        this.realName = realName;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractDoubleDirectionWallet)) {
            return false;
        }
        final ContractDoubleDirectionWallet other = (ContractDoubleDirectionWallet) o;
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
        final Object this$coin = this.getCoin();
        final Object other$coin = other.getCoin();
        Label_0139:
        {
            if (this$coin == null) {
                if (other$coin == null) {
                    break Label_0139;
                }
            } else if (this$coin.equals(other$coin)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$balance = this.getBalance();
        final Object other$balance = other.getBalance();
        Label_0176:
        {
            if (this$balance == null) {
                if (other$balance == null) {
                    break Label_0176;
                }
            } else if (this$balance.equals(other$balance)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$frozenBalance = this.getFrozenBalance();
        final Object other$frozenBalance = other.getFrozenBalance();
        Label_0213:
        {
            if (this$frozenBalance == null) {
                if (other$frozenBalance == null) {
                    break Label_0213;
                }
            } else if (this$frozenBalance.equals(other$frozenBalance)) {
                break Label_0213;
            }
            return false;
        }
        if (this.getVersion() != other.getVersion()) {
            return false;
        }
        final Object this$isLock = this.getIsLock();
        final Object other$isLock = other.getIsLock();
        Label_0263:
        {
            if (this$isLock == null) {
                if (other$isLock == null) {
                    break Label_0263;
                }
            } else if (this$isLock.equals(other$isLock)) {
                break Label_0263;
            }
            return false;
        }
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0300:
        {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0300;
                }
            } else if (this$username.equals(other$username)) {
                break Label_0300;
            }
            return false;
        }
        final Object this$mobilePhone = this.getMobilePhone();
        final Object other$mobilePhone = other.getMobilePhone();
        Label_0337:
        {
            if (this$mobilePhone == null) {
                if (other$mobilePhone == null) {
                    break Label_0337;
                }
            } else if (this$mobilePhone.equals(other$mobilePhone)) {
                break Label_0337;
            }
            return false;
        }
        final Object this$realName = this.getRealName();
        final Object other$realName = other.getRealName();
        Label_0374:
        {
            if (this$realName == null) {
                if (other$realName == null) {
                    break Label_0374;
                }
            } else if (this$realName.equals(other$realName)) {
                break Label_0374;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null) {
            if (other$email == null) {
                return true;
            }
        } else if (this$email.equals(other$email)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractDoubleDirectionWallet;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $coin = this.getCoin();
        result = result * 59 + (($coin == null) ? 43 : $coin.hashCode());
        final Object $balance = this.getBalance();
        result = result * 59 + (($balance == null) ? 43 : $balance.hashCode());
        final Object $frozenBalance = this.getFrozenBalance();
        result = result * 59 + (($frozenBalance == null) ? 43 : $frozenBalance.hashCode());
        result = result * 59 + this.getVersion();
        final Object $isLock = this.getIsLock();
        result = result * 59 + (($isLock == null) ? 43 : $isLock.hashCode());
        final Object $username = this.getUsername();
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $mobilePhone = this.getMobilePhone();
        result = result * 59 + (($mobilePhone == null) ? 43 : $mobilePhone.hashCode());
        final Object $realName = this.getRealName();
        result = result * 59 + (($realName == null) ? 43 : $realName.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractDoubleDirectionWallet(id=" + this.getId() + ", memberId=" + this.getMemberId() + ", coin=" + this.getCoin() + ", balance=" + this.getBalance() + ", frozenBalance=" + this.getFrozenBalance() + ", version=" + this.getVersion() + ", isLock=" + this.getIsLock() + ", username=" + this.getUsername() + ", mobilePhone=" + this.getMobilePhone() + ", realName=" + this.getRealName() + ", email=" + this.getEmail() + ")";
    }
}

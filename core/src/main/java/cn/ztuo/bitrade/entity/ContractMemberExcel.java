package cn.ztuo.bitrade.entity;

import cn.afterturn.easypoi.excel.annotation.*;

import java.math.*;

public class ContractMemberExcel {
    @Excel(name = "\u7528\u6237ID", width = 25.0)
    private Long id;
    @Excel(name = "\u7528\u6237\u540d", width = 25.0)
    private String username;
    @Excel(name = "\u624b\u673a\u53f7", width = 25.0)
    private String mobilePhone;
    @Excel(name = "\u90ae\u7bb1", width = 25.0)
    private String email;
    @Excel(name = "\u7528\u6237\u7c7b\u578b", width = 25.0)
    private String userTypeName;
    @Excel(name = "\u5408\u7ea6\u7528\u6237\u72b6\u6001", width = 25.0)
    private String ifNodeName;
    @Excel(name = "\u4e0a\u7ea7ID", width = 25.0)
    private Long proxyId;
    @Excel(name = "\u6ce8\u518c\u65f6\u95f4", width = 25.0)
    private String registrationTime;
    @Excel(name = "\u7528\u6237\u6628\u65e5\u76c8\u4e8f", width = 25.0)
    private BigDecimal memberYesterdayProfitLossAmount;
    @Excel(name = "\u7528\u6237\u603b\u4f53\u76c8\u4e8f", width = 25.0)
    private BigDecimal memberTotalProfitLossAmount;
    @Excel(name = "\u56e2\u961f\u6628\u65e5\u76c8\u4e8f", width = 25.0)
    private BigDecimal teamYesterdayProfitLossAmount;
    @Excel(name = "\u56e2\u961f\u603b\u4f53\u76c8\u4e8f", width = 25.0)
    private BigDecimal teamTotalProfitLossAmount;
    @Excel(name = "\u7528\u6237\u6628\u65e5\u624b\u7eed\u8d39", width = 25.0)
    private BigDecimal yesterdayFee;
    @Excel(name = "\u7528\u6237\u603b\u624b\u7eed\u8d39", width = 25.0)
    private BigDecimal totalFee;
    @Excel(name = "\u56e2\u961f\u6628\u65e5\u624b\u7eed\u8d39", width = 25.0)
    private BigDecimal teamYesterdayFee;
    @Excel(name = "\u56e2\u961f\u603b\u624b\u7eed\u8d39", width = 25.0)
    private BigDecimal teamTotalFee;

    public ContractMemberExcel() {
        this.memberYesterdayProfitLossAmount = BigDecimal.ZERO;
        this.memberTotalProfitLossAmount = BigDecimal.ZERO;
        this.teamYesterdayProfitLossAmount = BigDecimal.ZERO;
        this.teamTotalProfitLossAmount = BigDecimal.ZERO;
        this.yesterdayFee = BigDecimal.ZERO;
        this.totalFee = BigDecimal.ZERO;
        this.teamYesterdayFee = BigDecimal.ZERO;
        this.teamTotalFee = BigDecimal.ZERO;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserTypeName() {
        return this.userTypeName;
    }

    public String getIfNodeName() {
        return this.ifNodeName;
    }

    public Long getProxyId() {
        return this.proxyId;
    }

    public String getRegistrationTime() {
        return this.registrationTime;
    }

    public BigDecimal getMemberYesterdayProfitLossAmount() {
        return this.memberYesterdayProfitLossAmount;
    }

    public BigDecimal getMemberTotalProfitLossAmount() {
        return this.memberTotalProfitLossAmount;
    }

    public BigDecimal getTeamYesterdayProfitLossAmount() {
        return this.teamYesterdayProfitLossAmount;
    }

    public BigDecimal getTeamTotalProfitLossAmount() {
        return this.teamTotalProfitLossAmount;
    }

    public BigDecimal getYesterdayFee() {
        return this.yesterdayFee;
    }

    public BigDecimal getTotalFee() {
        return this.totalFee;
    }

    public BigDecimal getTeamYesterdayFee() {
        return this.teamYesterdayFee;
    }

    public BigDecimal getTeamTotalFee() {
        return this.teamTotalFee;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setUserTypeName(final String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public void setIfNodeName(final String ifNodeName) {
        this.ifNodeName = ifNodeName;
    }

    public void setProxyId(final Long proxyId) {
        this.proxyId = proxyId;
    }

    public void setRegistrationTime(final String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setMemberYesterdayProfitLossAmount(final BigDecimal memberYesterdayProfitLossAmount) {
        this.memberYesterdayProfitLossAmount = memberYesterdayProfitLossAmount;
    }

    public void setMemberTotalProfitLossAmount(final BigDecimal memberTotalProfitLossAmount) {
        this.memberTotalProfitLossAmount = memberTotalProfitLossAmount;
    }

    public void setTeamYesterdayProfitLossAmount(final BigDecimal teamYesterdayProfitLossAmount) {
        this.teamYesterdayProfitLossAmount = teamYesterdayProfitLossAmount;
    }

    public void setTeamTotalProfitLossAmount(final BigDecimal teamTotalProfitLossAmount) {
        this.teamTotalProfitLossAmount = teamTotalProfitLossAmount;
    }

    public void setYesterdayFee(final BigDecimal yesterdayFee) {
        this.yesterdayFee = yesterdayFee;
    }

    public void setTotalFee(final BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public void setTeamYesterdayFee(final BigDecimal teamYesterdayFee) {
        this.teamYesterdayFee = teamYesterdayFee;
    }

    public void setTeamTotalFee(final BigDecimal teamTotalFee) {
        this.teamTotalFee = teamTotalFee;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractMemberExcel)) {
            return false;
        }
        final ContractMemberExcel other = (ContractMemberExcel) o;
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
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0102:
        {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0102;
                }
            } else if (this$username.equals(other$username)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$mobilePhone = this.getMobilePhone();
        final Object other$mobilePhone = other.getMobilePhone();
        Label_0139:
        {
            if (this$mobilePhone == null) {
                if (other$mobilePhone == null) {
                    break Label_0139;
                }
            } else if (this$mobilePhone.equals(other$mobilePhone)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0176:
        {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0176;
                }
            } else if (this$email.equals(other$email)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$userTypeName = this.getUserTypeName();
        final Object other$userTypeName = other.getUserTypeName();
        Label_0213:
        {
            if (this$userTypeName == null) {
                if (other$userTypeName == null) {
                    break Label_0213;
                }
            } else if (this$userTypeName.equals(other$userTypeName)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$ifNodeName = this.getIfNodeName();
        final Object other$ifNodeName = other.getIfNodeName();
        Label_0250:
        {
            if (this$ifNodeName == null) {
                if (other$ifNodeName == null) {
                    break Label_0250;
                }
            } else if (this$ifNodeName.equals(other$ifNodeName)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$proxyId = this.getProxyId();
        final Object other$proxyId = other.getProxyId();
        Label_0287:
        {
            if (this$proxyId == null) {
                if (other$proxyId == null) {
                    break Label_0287;
                }
            } else if (this$proxyId.equals(other$proxyId)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$registrationTime = this.getRegistrationTime();
        final Object other$registrationTime = other.getRegistrationTime();
        Label_0324:
        {
            if (this$registrationTime == null) {
                if (other$registrationTime == null) {
                    break Label_0324;
                }
            } else if (this$registrationTime.equals(other$registrationTime)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$memberYesterdayProfitLossAmount = this.getMemberYesterdayProfitLossAmount();
        final Object other$memberYesterdayProfitLossAmount = other.getMemberYesterdayProfitLossAmount();
        Label_0361:
        {
            if (this$memberYesterdayProfitLossAmount == null) {
                if (other$memberYesterdayProfitLossAmount == null) {
                    break Label_0361;
                }
            } else if (this$memberYesterdayProfitLossAmount.equals(other$memberYesterdayProfitLossAmount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$memberTotalProfitLossAmount = this.getMemberTotalProfitLossAmount();
        final Object other$memberTotalProfitLossAmount = other.getMemberTotalProfitLossAmount();
        Label_0398:
        {
            if (this$memberTotalProfitLossAmount == null) {
                if (other$memberTotalProfitLossAmount == null) {
                    break Label_0398;
                }
            } else if (this$memberTotalProfitLossAmount.equals(other$memberTotalProfitLossAmount)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$teamYesterdayProfitLossAmount = this.getTeamYesterdayProfitLossAmount();
        final Object other$teamYesterdayProfitLossAmount = other.getTeamYesterdayProfitLossAmount();
        Label_0435:
        {
            if (this$teamYesterdayProfitLossAmount == null) {
                if (other$teamYesterdayProfitLossAmount == null) {
                    break Label_0435;
                }
            } else if (this$teamYesterdayProfitLossAmount.equals(other$teamYesterdayProfitLossAmount)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$teamTotalProfitLossAmount = this.getTeamTotalProfitLossAmount();
        final Object other$teamTotalProfitLossAmount = other.getTeamTotalProfitLossAmount();
        Label_0472:
        {
            if (this$teamTotalProfitLossAmount == null) {
                if (other$teamTotalProfitLossAmount == null) {
                    break Label_0472;
                }
            } else if (this$teamTotalProfitLossAmount.equals(other$teamTotalProfitLossAmount)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$yesterdayFee = this.getYesterdayFee();
        final Object other$yesterdayFee = other.getYesterdayFee();
        Label_0509:
        {
            if (this$yesterdayFee == null) {
                if (other$yesterdayFee == null) {
                    break Label_0509;
                }
            } else if (this$yesterdayFee.equals(other$yesterdayFee)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$totalFee = this.getTotalFee();
        final Object other$totalFee = other.getTotalFee();
        Label_0546:
        {
            if (this$totalFee == null) {
                if (other$totalFee == null) {
                    break Label_0546;
                }
            } else if (this$totalFee.equals(other$totalFee)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$teamYesterdayFee = this.getTeamYesterdayFee();
        final Object other$teamYesterdayFee = other.getTeamYesterdayFee();
        Label_0583:
        {
            if (this$teamYesterdayFee == null) {
                if (other$teamYesterdayFee == null) {
                    break Label_0583;
                }
            } else if (this$teamYesterdayFee.equals(other$teamYesterdayFee)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$teamTotalFee = this.getTeamTotalFee();
        final Object other$teamTotalFee = other.getTeamTotalFee();
        if (this$teamTotalFee == null) {
            if (other$teamTotalFee == null) {
                return true;
            }
        } else if (this$teamTotalFee.equals(other$teamTotalFee)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractMemberExcel;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $mobilePhone = this.getMobilePhone();
        result = result * 59 + (($mobilePhone == null) ? 43 : $mobilePhone.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $userTypeName = this.getUserTypeName();
        result = result * 59 + (($userTypeName == null) ? 43 : $userTypeName.hashCode());
        final Object $ifNodeName = this.getIfNodeName();
        result = result * 59 + (($ifNodeName == null) ? 43 : $ifNodeName.hashCode());
        final Object $proxyId = this.getProxyId();
        result = result * 59 + (($proxyId == null) ? 43 : $proxyId.hashCode());
        final Object $registrationTime = this.getRegistrationTime();
        result = result * 59 + (($registrationTime == null) ? 43 : $registrationTime.hashCode());
        final Object $memberYesterdayProfitLossAmount = this.getMemberYesterdayProfitLossAmount();
        result = result * 59 + (($memberYesterdayProfitLossAmount == null) ? 43 : $memberYesterdayProfitLossAmount.hashCode());
        final Object $memberTotalProfitLossAmount = this.getMemberTotalProfitLossAmount();
        result = result * 59 + (($memberTotalProfitLossAmount == null) ? 43 : $memberTotalProfitLossAmount.hashCode());
        final Object $teamYesterdayProfitLossAmount = this.getTeamYesterdayProfitLossAmount();
        result = result * 59 + (($teamYesterdayProfitLossAmount == null) ? 43 : $teamYesterdayProfitLossAmount.hashCode());
        final Object $teamTotalProfitLossAmount = this.getTeamTotalProfitLossAmount();
        result = result * 59 + (($teamTotalProfitLossAmount == null) ? 43 : $teamTotalProfitLossAmount.hashCode());
        final Object $yesterdayFee = this.getYesterdayFee();
        result = result * 59 + (($yesterdayFee == null) ? 43 : $yesterdayFee.hashCode());
        final Object $totalFee = this.getTotalFee();
        result = result * 59 + (($totalFee == null) ? 43 : $totalFee.hashCode());
        final Object $teamYesterdayFee = this.getTeamYesterdayFee();
        result = result * 59 + (($teamYesterdayFee == null) ? 43 : $teamYesterdayFee.hashCode());
        final Object $teamTotalFee = this.getTeamTotalFee();
        result = result * 59 + (($teamTotalFee == null) ? 43 : $teamTotalFee.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractMemberExcel(id=" + this.getId() + ", username=" + this.getUsername() + ", mobilePhone=" + this.getMobilePhone() + ", email=" + this.getEmail() + ", userTypeName=" + this.getUserTypeName() + ", ifNodeName=" + this.getIfNodeName() + ", proxyId=" + this.getProxyId() + ", registrationTime=" + this.getRegistrationTime() + ", memberYesterdayProfitLossAmount=" + this.getMemberYesterdayProfitLossAmount() + ", memberTotalProfitLossAmount=" + this.getMemberTotalProfitLossAmount() + ", teamYesterdayProfitLossAmount=" + this.getTeamYesterdayProfitLossAmount() + ", teamTotalProfitLossAmount=" + this.getTeamTotalProfitLossAmount() + ", yesterdayFee=" + this.getYesterdayFee() + ", totalFee=" + this.getTotalFee() + ", teamYesterdayFee=" + this.getTeamYesterdayFee() + ", teamTotalFee=" + this.getTeamTotalFee() + ")";
    }
}

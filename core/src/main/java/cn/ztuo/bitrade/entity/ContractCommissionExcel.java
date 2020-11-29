package cn.ztuo.bitrade.entity;

import java.io.*;
import cn.afterturn.easypoi.excel.annotation.*;
import java.math.*;

public class ContractCommissionExcel implements Serializable
{
    @Excel(name = "\u4f1a\u5458ID", orderNum = "1", width = 25.0)
    private Long memberId;
    @Excel(name = "\u7528\u6237\u540d", orderNum = "2", width = 25.0)
    private String username;
    @Excel(name = "\u90ae\u7bb1", orderNum = "3", width = 25.0)
    private String email;
    @Excel(name = "\u624b\u673a\u53f7", orderNum = "4", width = 25.0)
    private String mobilePhone;
    @Excel(name = "\u4ee3\u7406ID", orderNum = "5", width = 25.0)
    private Long proxyId;
    @Excel(name = "\u8282\u70b9ID", orderNum = "6", width = 25.0)
    private Long nodeId;
    @Excel(name = "\u8fd4\u4f63\u91d1\u989d", orderNum = "7", width = 25.0)
    private BigDecimal amount;
    @Excel(name = "\u8fd4\u4f63\u5e01\u79cd", orderNum = "8", width = 25.0)
    private String coinName;
    @Excel(name = "\u8fd4\u4f63\u72b6\u6001", orderNum = "9", width = 25.0)
    private String status;
    
    public Long getMemberId() {
        return this.memberId;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getMobilePhone() {
        return this.mobilePhone;
    }
    
    public Long getProxyId() {
        return this.proxyId;
    }
    
    public Long getNodeId() {
        return this.nodeId;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public String getCoinName() {
        return this.coinName;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public void setProxyId(final Long proxyId) {
        this.proxyId = proxyId;
    }
    
    public void setNodeId(final Long nodeId) {
        this.nodeId = nodeId;
    }
    
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setCoinName(final String coinName) {
        this.coinName = coinName;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractCommissionExcel)) {
            return false;
        }
        final ContractCommissionExcel other = (ContractCommissionExcel)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0065: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0065;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        Label_0102: {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0102;
                }
            }
            else if (this$username.equals(other$username)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0139: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0139;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$mobilePhone = this.getMobilePhone();
        final Object other$mobilePhone = other.getMobilePhone();
        Label_0176: {
            if (this$mobilePhone == null) {
                if (other$mobilePhone == null) {
                    break Label_0176;
                }
            }
            else if (this$mobilePhone.equals(other$mobilePhone)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$proxyId = this.getProxyId();
        final Object other$proxyId = other.getProxyId();
        Label_0213: {
            if (this$proxyId == null) {
                if (other$proxyId == null) {
                    break Label_0213;
                }
            }
            else if (this$proxyId.equals(other$proxyId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$nodeId = this.getNodeId();
        final Object other$nodeId = other.getNodeId();
        Label_0250: {
            if (this$nodeId == null) {
                if (other$nodeId == null) {
                    break Label_0250;
                }
            }
            else if (this$nodeId.equals(other$nodeId)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0287: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0287;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$coinName = this.getCoinName();
        final Object other$coinName = other.getCoinName();
        Label_0324: {
            if (this$coinName == null) {
                if (other$coinName == null) {
                    break Label_0324;
                }
            }
            else if (this$coinName.equals(other$coinName)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null) {
            if (other$status == null) {
                return true;
            }
        }
        else if (this$status.equals(other$status)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractCommissionExcel;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $username = this.getUsername();
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $mobilePhone = this.getMobilePhone();
        result = result * 59 + (($mobilePhone == null) ? 43 : $mobilePhone.hashCode());
        final Object $proxyId = this.getProxyId();
        result = result * 59 + (($proxyId == null) ? 43 : $proxyId.hashCode());
        final Object $nodeId = this.getNodeId();
        result = result * 59 + (($nodeId == null) ? 43 : $nodeId.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $coinName = this.getCoinName();
        result = result * 59 + (($coinName == null) ? 43 : $coinName.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractCommissionExcel(memberId=" + this.getMemberId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", mobilePhone=" + this.getMobilePhone() + ", proxyId=" + this.getProxyId() + ", nodeId=" + this.getNodeId() + ", amount=" + this.getAmount() + ", coinName=" + this.getCoinName() + ", status=" + this.getStatus() + ")";
    }
}

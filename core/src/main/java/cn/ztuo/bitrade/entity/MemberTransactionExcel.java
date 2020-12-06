package cn.ztuo.bitrade.entity;

import cn.afterturn.easypoi.excel.annotation.*;

public class MemberTransactionExcel {
    @Excel(name = "\u4f1a\u5458ID", orderNum = "1", width = 25.0)
    private Long memberId;
    @Excel(name = "\u4ea4\u6613\u7c7b\u578b", orderNum = "2", width = 25.0)
    private String typeName;
    @Excel(name = "\u4ea4\u6613\u91d1\u989d", orderNum = "3", width = 25.0)
    private String amountStr;
    @Excel(name = "\u4ea4\u6613\u624b\u7eed\u8d39", orderNum = "4", width = 25.0)
    private String feeStr;
    @Excel(name = "\u6536\u76ca\u6765\u6e90\u7528\u6237ID", orderNum = "5", width = 25.0)
    private Long fromMemberId;
    @Excel(name = "\u4ea4\u6613\u65f6\u95f4", orderNum = "6", width = 25.0)
    private String createTime;

    public Long getMemberId() {
        return this.memberId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getAmountStr() {
        return this.amountStr;
    }

    public String getFeeStr() {
        return this.feeStr;
    }

    public Long getFromMemberId() {
        return this.fromMemberId;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }

    public void setAmountStr(final String amountStr) {
        this.amountStr = amountStr;
    }

    public void setFeeStr(final String feeStr) {
        this.feeStr = feeStr;
    }

    public void setFromMemberId(final Long fromMemberId) {
        this.fromMemberId = fromMemberId;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MemberTransactionExcel)) {
            return false;
        }
        final MemberTransactionExcel other = (MemberTransactionExcel) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0065:
        {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0065;
                }
            } else if (this$memberId.equals(other$memberId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$typeName = this.getTypeName();
        final Object other$typeName = other.getTypeName();
        Label_0102:
        {
            if (this$typeName == null) {
                if (other$typeName == null) {
                    break Label_0102;
                }
            } else if (this$typeName.equals(other$typeName)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$amountStr = this.getAmountStr();
        final Object other$amountStr = other.getAmountStr();
        Label_0139:
        {
            if (this$amountStr == null) {
                if (other$amountStr == null) {
                    break Label_0139;
                }
            } else if (this$amountStr.equals(other$amountStr)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$feeStr = this.getFeeStr();
        final Object other$feeStr = other.getFeeStr();
        Label_0176:
        {
            if (this$feeStr == null) {
                if (other$feeStr == null) {
                    break Label_0176;
                }
            } else if (this$feeStr.equals(other$feeStr)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$fromMemberId = this.getFromMemberId();
        final Object other$fromMemberId = other.getFromMemberId();
        Label_0213:
        {
            if (this$fromMemberId == null) {
                if (other$fromMemberId == null) {
                    break Label_0213;
                }
            } else if (this$fromMemberId.equals(other$fromMemberId)) {
                break Label_0213;
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
        return other instanceof MemberTransactionExcel;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $typeName = this.getTypeName();
        result = result * 59 + (($typeName == null) ? 43 : $typeName.hashCode());
        final Object $amountStr = this.getAmountStr();
        result = result * 59 + (($amountStr == null) ? 43 : $amountStr.hashCode());
        final Object $feeStr = this.getFeeStr();
        result = result * 59 + (($feeStr == null) ? 43 : $feeStr.hashCode());
        final Object $fromMemberId = this.getFromMemberId();
        result = result * 59 + (($fromMemberId == null) ? 43 : $fromMemberId.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "MemberTransactionExcel(memberId=" + this.getMemberId() + ", typeName=" + this.getTypeName() + ", amountStr=" + this.getAmountStr() + ", feeStr=" + this.getFeeStr() + ", fromMemberId=" + this.getFromMemberId() + ", createTime=" + this.getCreateTime() + ")";
    }
}

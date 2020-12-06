package cn.ztuo.bitrade.entity.unblock;

import java.io.*;
import javax.persistence.*;

import cn.ztuo.bitrade.enums.*;

import java.util.*;

@Entity
@Table
public class UnblockLotteryIncreasedRecord implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long time;
    private Long memberId;
    private Long beforeCount;
    private Long afterCount;
    private Long addCount;
    private LotteryAddType lotteryAddType;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return this.id;
    }

    public Long getTime() {
        return this.time;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public Long getBeforeCount() {
        return this.beforeCount;
    }

    public Long getAfterCount() {
        return this.afterCount;
    }

    public Long getAddCount() {
        return this.addCount;
    }

    public LotteryAddType getLotteryAddType() {
        return this.lotteryAddType;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setTime(final Long time) {
        this.time = time;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setBeforeCount(final Long beforeCount) {
        this.beforeCount = beforeCount;
    }

    public void setAfterCount(final Long afterCount) {
        this.afterCount = afterCount;
    }

    public void setAddCount(final Long addCount) {
        this.addCount = addCount;
    }

    public void setLotteryAddType(final LotteryAddType lotteryAddType) {
        this.lotteryAddType = lotteryAddType;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UnblockLotteryIncreasedRecord)) {
            return false;
        }
        final UnblockLotteryIncreasedRecord other = (UnblockLotteryIncreasedRecord) o;
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
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        Label_0102:
        {
            if (this$time == null) {
                if (other$time == null) {
                    break Label_0102;
                }
            } else if (this$time.equals(other$time)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0139:
        {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0139;
                }
            } else if (this$memberId.equals(other$memberId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$beforeCount = this.getBeforeCount();
        final Object other$beforeCount = other.getBeforeCount();
        Label_0176:
        {
            if (this$beforeCount == null) {
                if (other$beforeCount == null) {
                    break Label_0176;
                }
            } else if (this$beforeCount.equals(other$beforeCount)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$afterCount = this.getAfterCount();
        final Object other$afterCount = other.getAfterCount();
        Label_0213:
        {
            if (this$afterCount == null) {
                if (other$afterCount == null) {
                    break Label_0213;
                }
            } else if (this$afterCount.equals(other$afterCount)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$addCount = this.getAddCount();
        final Object other$addCount = other.getAddCount();
        Label_0250:
        {
            if (this$addCount == null) {
                if (other$addCount == null) {
                    break Label_0250;
                }
            } else if (this$addCount.equals(other$addCount)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$lotteryAddType = this.getLotteryAddType();
        final Object other$lotteryAddType = other.getLotteryAddType();
        Label_0287:
        {
            if (this$lotteryAddType == null) {
                if (other$lotteryAddType == null) {
                    break Label_0287;
                }
            } else if (this$lotteryAddType.equals(other$lotteryAddType)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0324:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0324;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        if (this$updateTime == null) {
            if (other$updateTime == null) {
                return true;
            }
        } else if (this$updateTime.equals(other$updateTime)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnblockLotteryIncreasedRecord;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $time = this.getTime();
        result = result * 59 + (($time == null) ? 43 : $time.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $beforeCount = this.getBeforeCount();
        result = result * 59 + (($beforeCount == null) ? 43 : $beforeCount.hashCode());
        final Object $afterCount = this.getAfterCount();
        result = result * 59 + (($afterCount == null) ? 43 : $afterCount.hashCode());
        final Object $addCount = this.getAddCount();
        result = result * 59 + (($addCount == null) ? 43 : $addCount.hashCode());
        final Object $lotteryAddType = this.getLotteryAddType();
        result = result * 59 + (($lotteryAddType == null) ? 43 : $lotteryAddType.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UnblockLotteryIncreasedRecord(id=" + this.getId() + ", time=" + this.getTime() + ", memberId=" + this.getMemberId() + ", beforeCount=" + this.getBeforeCount() + ", afterCount=" + this.getAfterCount() + ", addCount=" + this.getAddCount() + ", lotteryAddType=" + this.getLotteryAddType() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ")";
    }
}

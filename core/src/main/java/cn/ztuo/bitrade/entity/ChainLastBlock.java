package cn.ztuo.bitrade.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import java.util.*;
import org.hibernate.annotations.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "chain_last_block")
public class ChainLastBlock
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long lastBlockNum;
    @NotBlank(message = "\u8d44\u4ea7\u540d\u4e0d\u4e3a\u7a7a")
    private String assetsName;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Long getId() {
        return this.id;
    }

    public Long getLastBlockNum() {
        return this.lastBlockNum;
    }

    public String getAssetsName() {
        return this.assetsName;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setLastBlockNum(final Long lastBlockNum) {
        this.lastBlockNum = lastBlockNum;
    }

    public void setAssetsName(final String assetsName) {
        this.assetsName = assetsName;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChainLastBlock)) {
            return false;
        }
        final ChainLastBlock other = (ChainLastBlock)o;
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
        final Object this$lastBlockNum = this.getLastBlockNum();
        final Object other$lastBlockNum = other.getLastBlockNum();
        Label_0102: {
            if (this$lastBlockNum == null) {
                if (other$lastBlockNum == null) {
                    break Label_0102;
                }
            }
            else if (this$lastBlockNum.equals(other$lastBlockNum)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$assetsName = this.getAssetsName();
        final Object other$assetsName = other.getAssetsName();
        Label_0139: {
            if (this$assetsName == null) {
                if (other$assetsName == null) {
                    break Label_0139;
                }
            }
            else if (this$assetsName.equals(other$assetsName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        if (this$updateTime == null) {
            if (other$updateTime == null) {
                return true;
            }
        }
        else if (this$updateTime.equals(other$updateTime)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ChainLastBlock;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $lastBlockNum = this.getLastBlockNum();
        result = result * 59 + (($lastBlockNum == null) ? 43 : $lastBlockNum.hashCode());
        final Object $assetsName = this.getAssetsName();
        result = result * 59 + (($assetsName == null) ? 43 : $assetsName.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ChainLastBlock(id=" + this.getId() + ", lastBlockNum=" + this.getLastBlockNum() + ", assetsName=" + this.getAssetsName() + ", updateTime=" + this.getUpdateTime() + ")";
    }
}

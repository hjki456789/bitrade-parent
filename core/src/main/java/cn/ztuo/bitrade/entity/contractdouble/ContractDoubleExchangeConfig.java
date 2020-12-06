package cn.ztuo.bitrade.entity.contractdouble;

import java.io.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class ContractDoubleExchangeConfig implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String cnName;
    private String apiUrl;
    private Date createTime;
    private String content;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCnName() {
        return this.cnName;
    }

    public String getApiUrl() {
        return this.apiUrl;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }

    public void setApiUrl(final String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractDoubleExchangeConfig)) {
            return false;
        }
        final ContractDoubleExchangeConfig other = (ContractDoubleExchangeConfig) o;
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
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0102:
        {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0102;
                }
            } else if (this$name.equals(other$name)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$cnName = this.getCnName();
        final Object other$cnName = other.getCnName();
        Label_0139:
        {
            if (this$cnName == null) {
                if (other$cnName == null) {
                    break Label_0139;
                }
            } else if (this$cnName.equals(other$cnName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$apiUrl = this.getApiUrl();
        final Object other$apiUrl = other.getApiUrl();
        Label_0176:
        {
            if (this$apiUrl == null) {
                if (other$apiUrl == null) {
                    break Label_0176;
                }
            } else if (this$apiUrl.equals(other$apiUrl)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0213:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0213;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null) {
            if (other$content == null) {
                return true;
            }
        } else if (this$content.equals(other$content)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractDoubleExchangeConfig;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $cnName = this.getCnName();
        result = result * 59 + (($cnName == null) ? 43 : $cnName.hashCode());
        final Object $apiUrl = this.getApiUrl();
        result = result * 59 + (($apiUrl == null) ? 43 : $apiUrl.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $content = this.getContent();
        result = result * 59 + (($content == null) ? 43 : $content.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractDoubleExchangeConfig(id=" + this.getId() + ", name=" + this.getName() + ", cnName=" + this.getCnName() + ", apiUrl=" + this.getApiUrl() + ", createTime=" + this.getCreateTime() + ", content=" + this.getContent() + ")";
    }
}

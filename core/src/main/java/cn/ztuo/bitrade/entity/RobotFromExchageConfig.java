package cn.ztuo.bitrade.entity;

import javax.persistence.*;

@Entity
@Table
public class RobotFromExchageConfig {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String nameCn;
    private String host;
    private String baseUrl;
    private String remark;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNameCn() {
        return this.nameCn;
    }

    public String getHost() {
        return this.host;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setNameCn(final String nameCn) {
        this.nameCn = nameCn;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RobotFromExchageConfig)) {
            return false;
        }
        final RobotFromExchageConfig other = (RobotFromExchageConfig) o;
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
        final Object this$nameCn = this.getNameCn();
        final Object other$nameCn = other.getNameCn();
        Label_0139:
        {
            if (this$nameCn == null) {
                if (other$nameCn == null) {
                    break Label_0139;
                }
            } else if (this$nameCn.equals(other$nameCn)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$host = this.getHost();
        final Object other$host = other.getHost();
        Label_0176:
        {
            if (this$host == null) {
                if (other$host == null) {
                    break Label_0176;
                }
            } else if (this$host.equals(other$host)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$baseUrl = this.getBaseUrl();
        final Object other$baseUrl = other.getBaseUrl();
        Label_0213:
        {
            if (this$baseUrl == null) {
                if (other$baseUrl == null) {
                    break Label_0213;
                }
            } else if (this$baseUrl.equals(other$baseUrl)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        if (this$remark == null) {
            if (other$remark == null) {
                return true;
            }
        } else if (this$remark.equals(other$remark)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RobotFromExchageConfig;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $nameCn = this.getNameCn();
        result = result * 59 + (($nameCn == null) ? 43 : $nameCn.hashCode());
        final Object $host = this.getHost();
        result = result * 59 + (($host == null) ? 43 : $host.hashCode());
        final Object $baseUrl = this.getBaseUrl();
        result = result * 59 + (($baseUrl == null) ? 43 : $baseUrl.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RobotFromExchageConfig(id=" + this.getId() + ", name=" + this.getName() + ", nameCn=" + this.getNameCn() + ", host=" + this.getHost() + ", baseUrl=" + this.getBaseUrl() + ", remark=" + this.getRemark() + ")";
    }
}

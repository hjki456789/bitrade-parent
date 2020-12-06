package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;

@Entity
@Table
public class ContractConfig implements Serializable {
    @Id
    private String configKey;
    private String configValue;
    private String description;

    public String getConfigKey() {
        return this.configKey;
    }

    public String getConfigValue() {
        return this.configValue;
    }

    public String getDescription() {
        return this.description;
    }

    public void setConfigKey(final String configKey) {
        this.configKey = configKey;
    }

    public void setConfigValue(final String configValue) {
        this.configValue = configValue;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractConfig)) {
            return false;
        }
        final ContractConfig other = (ContractConfig) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$configKey = this.getConfigKey();
        final Object other$configKey = other.getConfigKey();
        Label_0065:
        {
            if (this$configKey == null) {
                if (other$configKey == null) {
                    break Label_0065;
                }
            } else if (this$configKey.equals(other$configKey)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$configValue = this.getConfigValue();
        final Object other$configValue = other.getConfigValue();
        Label_0102:
        {
            if (this$configValue == null) {
                if (other$configValue == null) {
                    break Label_0102;
                }
            } else if (this$configValue.equals(other$configValue)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null) {
            if (other$description == null) {
                return true;
            }
        } else if (this$description.equals(other$description)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractConfig;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $configKey = this.getConfigKey();
        result = result * 59 + (($configKey == null) ? 43 : $configKey.hashCode());
        final Object $configValue = this.getConfigValue();
        result = result * 59 + (($configValue == null) ? 43 : $configValue.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractConfig(configKey=" + this.getConfigKey() + ", configValue=" + this.getConfigValue() + ", description=" + this.getDescription() + ")";
    }
}

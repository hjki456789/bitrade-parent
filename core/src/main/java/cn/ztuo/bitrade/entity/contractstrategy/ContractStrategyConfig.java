package cn.ztuo.bitrade.entity.contractstrategy;

import java.io.*;
import java.math.*;

import cn.ztuo.bitrade.constant.*;

import java.util.*;
import javax.persistence.*;

@Entity
@Table
public class ContractStrategyConfig implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long fromExchangeId;
    private String apiKey;
    private String secretKey;
    private String expireDate;
    private String remark;
    private Long adminId;
    private Long memberId;
    private BigDecimal priceRiseRate;
    private BigDecimal priceFallRate;
    private String symbol;
    private CommonStatus status;
    private Date createTime;
    private Date updateTime;
    @Transient
    private ContractStrategyExchangeConfig exchangeConfig;

    public Long getId() {
        return this.id;
    }

    public Long getFromExchangeId() {
        return this.fromExchangeId;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getExpireDate() {
        return this.expireDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public Long getAdminId() {
        return this.adminId;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public BigDecimal getPriceRiseRate() {
        return this.priceRiseRate;
    }

    public BigDecimal getPriceFallRate() {
        return this.priceFallRate;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public CommonStatus getStatus() {
        return this.status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public ContractStrategyExchangeConfig getExchangeConfig() {
        return this.exchangeConfig;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFromExchangeId(final Long fromExchangeId) {
        this.fromExchangeId = fromExchangeId;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    public void setExpireDate(final String expireDate) {
        this.expireDate = expireDate;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public void setAdminId(final Long adminId) {
        this.adminId = adminId;
    }

    public void setMemberId(final Long memberId) {
        this.memberId = memberId;
    }

    public void setPriceRiseRate(final BigDecimal priceRiseRate) {
        this.priceRiseRate = priceRiseRate;
    }

    public void setPriceFallRate(final BigDecimal priceFallRate) {
        this.priceFallRate = priceFallRate;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public void setStatus(final CommonStatus status) {
        this.status = status;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setExchangeConfig(final ContractStrategyExchangeConfig exchangeConfig) {
        this.exchangeConfig = exchangeConfig;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractStrategyConfig)) {
            return false;
        }
        final ContractStrategyConfig other = (ContractStrategyConfig) o;
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
        final Object this$fromExchangeId = this.getFromExchangeId();
        final Object other$fromExchangeId = other.getFromExchangeId();
        Label_0102:
        {
            if (this$fromExchangeId == null) {
                if (other$fromExchangeId == null) {
                    break Label_0102;
                }
            } else if (this$fromExchangeId.equals(other$fromExchangeId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$apiKey = this.getApiKey();
        final Object other$apiKey = other.getApiKey();
        Label_0139:
        {
            if (this$apiKey == null) {
                if (other$apiKey == null) {
                    break Label_0139;
                }
            } else if (this$apiKey.equals(other$apiKey)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$secretKey = this.getSecretKey();
        final Object other$secretKey = other.getSecretKey();
        Label_0176:
        {
            if (this$secretKey == null) {
                if (other$secretKey == null) {
                    break Label_0176;
                }
            } else if (this$secretKey.equals(other$secretKey)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$expireDate = this.getExpireDate();
        final Object other$expireDate = other.getExpireDate();
        Label_0213:
        {
            if (this$expireDate == null) {
                if (other$expireDate == null) {
                    break Label_0213;
                }
            } else if (this$expireDate.equals(other$expireDate)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0250:
        {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0250;
                }
            } else if (this$remark.equals(other$remark)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$adminId = this.getAdminId();
        final Object other$adminId = other.getAdminId();
        Label_0287:
        {
            if (this$adminId == null) {
                if (other$adminId == null) {
                    break Label_0287;
                }
            } else if (this$adminId.equals(other$adminId)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_0324:
        {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_0324;
                }
            } else if (this$memberId.equals(other$memberId)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$priceRiseRate = this.getPriceRiseRate();
        final Object other$priceRiseRate = other.getPriceRiseRate();
        Label_0361:
        {
            if (this$priceRiseRate == null) {
                if (other$priceRiseRate == null) {
                    break Label_0361;
                }
            } else if (this$priceRiseRate.equals(other$priceRiseRate)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$priceFallRate = this.getPriceFallRate();
        final Object other$priceFallRate = other.getPriceFallRate();
        Label_0398:
        {
            if (this$priceFallRate == null) {
                if (other$priceFallRate == null) {
                    break Label_0398;
                }
            } else if (this$priceFallRate.equals(other$priceFallRate)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        Label_0435:
        {
            if (this$symbol == null) {
                if (other$symbol == null) {
                    break Label_0435;
                }
            } else if (this$symbol.equals(other$symbol)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0472:
        {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0472;
                }
            } else if (this$status.equals(other$status)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0509:
        {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0509;
                }
            } else if (this$createTime.equals(other$createTime)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0546:
        {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0546;
                }
            } else if (this$updateTime.equals(other$updateTime)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$exchangeConfig = this.getExchangeConfig();
        final Object other$exchangeConfig = other.getExchangeConfig();
        if (this$exchangeConfig == null) {
            if (other$exchangeConfig == null) {
                return true;
            }
        } else if (this$exchangeConfig.equals(other$exchangeConfig)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractStrategyConfig;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $fromExchangeId = this.getFromExchangeId();
        result = result * 59 + (($fromExchangeId == null) ? 43 : $fromExchangeId.hashCode());
        final Object $apiKey = this.getApiKey();
        result = result * 59 + (($apiKey == null) ? 43 : $apiKey.hashCode());
        final Object $secretKey = this.getSecretKey();
        result = result * 59 + (($secretKey == null) ? 43 : $secretKey.hashCode());
        final Object $expireDate = this.getExpireDate();
        result = result * 59 + (($expireDate == null) ? 43 : $expireDate.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $adminId = this.getAdminId();
        result = result * 59 + (($adminId == null) ? 43 : $adminId.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $priceRiseRate = this.getPriceRiseRate();
        result = result * 59 + (($priceRiseRate == null) ? 43 : $priceRiseRate.hashCode());
        final Object $priceFallRate = this.getPriceFallRate();
        result = result * 59 + (($priceFallRate == null) ? 43 : $priceFallRate.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * 59 + (($symbol == null) ? 43 : $symbol.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $exchangeConfig = this.getExchangeConfig();
        result = result * 59 + (($exchangeConfig == null) ? 43 : $exchangeConfig.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractStrategyConfig(id=" + this.getId() + ", fromExchangeId=" + this.getFromExchangeId() + ", apiKey=" + this.getApiKey() + ", secretKey=" + this.getSecretKey() + ", expireDate=" + this.getExpireDate() + ", remark=" + this.getRemark() + ", adminId=" + this.getAdminId() + ", memberId=" + this.getMemberId() + ", priceRiseRate=" + this.getPriceRiseRate() + ", priceFallRate=" + this.getPriceFallRate() + ", symbol=" + this.getSymbol() + ", status=" + this.getStatus() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", exchangeConfig=" + this.getExchangeConfig() + ")";
    }
}

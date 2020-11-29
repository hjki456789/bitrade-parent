package cn.ztuo.bitrade.entity;

import cn.afterturn.easypoi.excel.annotation.*;
import org.hibernate.validator.constraints.*;
import java.math.*;
import javax.persistence.*;
import cn.ztuo.bitrade.constant.*;

@Entity
@Table(name = "contract_coin_info")
public class ContractCoinInfo
{
    @Excel(name = "\u5408\u7ea6\u5e01\u79cd\u7f16\u53f7", orderNum = "1", width = 20.0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Excel(name = "\u5408\u7ea6\u5e01\u79cd\u540d\u79f0", orderNum = "1", width = 20.0)
    @NotBlank(message = "\u5e01\u540d\u79f0\u4e0d\u5f97\u4e3a\u7a7a")
    private String name;
    @Excel(name = "\u5408\u7ea6\u5e01\u79cd\u4e2d\u6587\u540d\u79f0", orderNum = "1", width = 20.0)
    private String nameCn;
    @Excel(name = "\u5408\u7ea6\u5e01\u79cd\u5355\u4f4d", orderNum = "1", width = 20.0)
    private String unit;
    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
    @Column(columnDefinition = "decimal(18,6) comment '\u4ea4\u6613\u624b\u7eed\u8d39\u7387'")
    private BigDecimal jyRate;
    @Column(columnDefinition = "int(11) default 0 comment '\u5e8f\u53f7'")
    private int sort;
    @Enumerated(EnumType.ORDINAL)
    private BooleanEnum isPlatformCoin;
    @Column(columnDefinition = "int(11) default 8 comment '\u5e01\u79cd\u7cbe\u5ea6'")
    private Integer coinScale;
    @Column(columnDefinition = "int(11) default 0 comment '\u6700\u5927\u6302\u5355\u6570\u91cf\uff0c0\u8868\u793a\u4e0d\u9650\u5236'")
    private Integer maxVolume;
    
    public ContractCoinInfo() {
        this.status = CommonStatus.NORMAL;
        this.isPlatformCoin = BooleanEnum.IS_FALSE;
        this.coinScale = 8;
        this.maxVolume = 0;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNameCn() {
        return this.nameCn;
    }
    
    public String getUnit() {
        return this.unit;
    }
    
    public CommonStatus getStatus() {
        return this.status;
    }
    
    public BigDecimal getJyRate() {
        return this.jyRate;
    }
    
    public int getSort() {
        return this.sort;
    }
    
    public BooleanEnum getIsPlatformCoin() {
        return this.isPlatformCoin;
    }
    
    public Integer getCoinScale() {
        return this.coinScale;
    }
    
    public Integer getMaxVolume() {
        return this.maxVolume;
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
    
    public void setUnit(final String unit) {
        this.unit = unit;
    }
    
    public void setStatus(final CommonStatus status) {
        this.status = status;
    }
    
    public void setJyRate(final BigDecimal jyRate) {
        this.jyRate = jyRate;
    }
    
    public void setSort(final int sort) {
        this.sort = sort;
    }
    
    public void setIsPlatformCoin(final BooleanEnum isPlatformCoin) {
        this.isPlatformCoin = isPlatformCoin;
    }
    
    public void setCoinScale(final Integer coinScale) {
        this.coinScale = coinScale;
    }
    
    public void setMaxVolume(final Integer maxVolume) {
        this.maxVolume = maxVolume;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractCoinInfo)) {
            return false;
        }
        final ContractCoinInfo other = (ContractCoinInfo)o;
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
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0102: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0102;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$nameCn = this.getNameCn();
        final Object other$nameCn = other.getNameCn();
        Label_0139: {
            if (this$nameCn == null) {
                if (other$nameCn == null) {
                    break Label_0139;
                }
            }
            else if (this$nameCn.equals(other$nameCn)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$unit = this.getUnit();
        final Object other$unit = other.getUnit();
        Label_0176: {
            if (this$unit == null) {
                if (other$unit == null) {
                    break Label_0176;
                }
            }
            else if (this$unit.equals(other$unit)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0213: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0213;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$jyRate = this.getJyRate();
        final Object other$jyRate = other.getJyRate();
        Label_0250: {
            if (this$jyRate == null) {
                if (other$jyRate == null) {
                    break Label_0250;
                }
            }
            else if (this$jyRate.equals(other$jyRate)) {
                break Label_0250;
            }
            return false;
        }
        if (this.getSort() != other.getSort()) {
            return false;
        }
        final Object this$isPlatformCoin = this.getIsPlatformCoin();
        final Object other$isPlatformCoin = other.getIsPlatformCoin();
        Label_0300: {
            if (this$isPlatformCoin == null) {
                if (other$isPlatformCoin == null) {
                    break Label_0300;
                }
            }
            else if (this$isPlatformCoin.equals(other$isPlatformCoin)) {
                break Label_0300;
            }
            return false;
        }
        final Object this$coinScale = this.getCoinScale();
        final Object other$coinScale = other.getCoinScale();
        Label_0337: {
            if (this$coinScale == null) {
                if (other$coinScale == null) {
                    break Label_0337;
                }
            }
            else if (this$coinScale.equals(other$coinScale)) {
                break Label_0337;
            }
            return false;
        }
        final Object this$maxVolume = this.getMaxVolume();
        final Object other$maxVolume = other.getMaxVolume();
        if (this$maxVolume == null) {
            if (other$maxVolume == null) {
                return true;
            }
        }
        else if (this$maxVolume.equals(other$maxVolume)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractCoinInfo;
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
        final Object $unit = this.getUnit();
        result = result * 59 + (($unit == null) ? 43 : $unit.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $jyRate = this.getJyRate();
        result = result * 59 + (($jyRate == null) ? 43 : $jyRate.hashCode());
        result = result * 59 + this.getSort();
        final Object $isPlatformCoin = this.getIsPlatformCoin();
        result = result * 59 + (($isPlatformCoin == null) ? 43 : $isPlatformCoin.hashCode());
        final Object $coinScale = this.getCoinScale();
        result = result * 59 + (($coinScale == null) ? 43 : $coinScale.hashCode());
        final Object $maxVolume = this.getMaxVolume();
        result = result * 59 + (($maxVolume == null) ? 43 : $maxVolume.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ContractCoinInfo(id=" + this.getId() + ", name=" + this.getName() + ", nameCn=" + this.getNameCn() + ", unit=" + this.getUnit() + ", status=" + this.getStatus() + ", jyRate=" + this.getJyRate() + ", sort=" + this.getSort() + ", isPlatformCoin=" + this.getIsPlatformCoin() + ", coinScale=" + this.getCoinScale() + ", maxVolume=" + this.getMaxVolume() + ")";
    }
}

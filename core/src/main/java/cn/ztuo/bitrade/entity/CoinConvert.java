package cn.ztuo.bitrade.entity;

import org.hibernate.validator.constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "coin_convert")
public class CoinConvert {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "\u88ab\u8f6c\u6362\u5e01\u4e0d\u5f97\u4e3a\u7a7a")
    private String baseCoin;
    @NotBlank(message = "\u8f6c\u6362\u5e01\u4e0d\u5f97\u4e3a\u7a7a")
    private String convertCoin;
    @Column(columnDefinition = "double(10,8) default 0 comment '\u624b\u7eed\u8d39'")
    private Double fee;

    public Long getId() {
        return this.id;
    }

    public String getBaseCoin() {
        return this.baseCoin;
    }

    public String getConvertCoin() {
        return this.convertCoin;
    }

    public Double getFee() {
        return this.fee;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setBaseCoin(final String baseCoin) {
        this.baseCoin = baseCoin;
    }

    public void setConvertCoin(final String convertCoin) {
        this.convertCoin = convertCoin;
    }

    public void setFee(final Double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CoinConvert)) {
            return false;
        }
        final CoinConvert other = (CoinConvert) o;
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
        final Object this$baseCoin = this.getBaseCoin();
        final Object other$baseCoin = other.getBaseCoin();
        Label_0102:
        {
            if (this$baseCoin == null) {
                if (other$baseCoin == null) {
                    break Label_0102;
                }
            } else if (this$baseCoin.equals(other$baseCoin)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$convertCoin = this.getConvertCoin();
        final Object other$convertCoin = other.getConvertCoin();
        Label_0139:
        {
            if (this$convertCoin == null) {
                if (other$convertCoin == null) {
                    break Label_0139;
                }
            } else if (this$convertCoin.equals(other$convertCoin)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$fee = this.getFee();
        final Object other$fee = other.getFee();
        if (this$fee == null) {
            if (other$fee == null) {
                return true;
            }
        } else if (this$fee.equals(other$fee)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CoinConvert;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $baseCoin = this.getBaseCoin();
        result = result * 59 + (($baseCoin == null) ? 43 : $baseCoin.hashCode());
        final Object $convertCoin = this.getConvertCoin();
        result = result * 59 + (($convertCoin == null) ? 43 : $convertCoin.hashCode());
        final Object $fee = this.getFee();
        result = result * 59 + (($fee == null) ? 43 : $fee.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CoinConvert(id=" + this.getId() + ", baseCoin=" + this.getBaseCoin() + ", convertCoin=" + this.getConvertCoin() + ", fee=" + this.getFee() + ")";
    }
}

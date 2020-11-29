package cn.ztuo.bitrade.entity;

import javax.persistence.*;
import org.hibernate.validator.constraints.*;

@Entity
@Table(name = "hot_wallet_info")
public class HotWalletInfo
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "\u70ed\u94b1\u5305\u5730\u5740\u4e0d\u5f97\u4e3a\u7a7a")
    private String address;
    @NotBlank(message = "\u70ed\u94b1\u5305\u79c1\u94a5\u4e0d\u5f97\u4e3a\u7a7a")
    private String privateKey;
    private int chainType;
    
    public Long getId() {
        return this.id;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getPrivateKey() {
        return this.privateKey;
    }
    
    public int getChainType() {
        return this.chainType;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }
    
    public void setChainType(final int chainType) {
        this.chainType = chainType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HotWalletInfo)) {
            return false;
        }
        final HotWalletInfo other = (HotWalletInfo)o;
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
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        Label_0102: {
            if (this$address == null) {
                if (other$address == null) {
                    break Label_0102;
                }
            }
            else if (this$address.equals(other$address)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$privateKey = this.getPrivateKey();
        final Object other$privateKey = other.getPrivateKey();
        if (this$privateKey == null) {
            if (other$privateKey == null) {
                return this.getChainType() == other.getChainType();
            }
        }
        else if (this$privateKey.equals(other$privateKey)) {
            return this.getChainType() == other.getChainType();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HotWalletInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $address = this.getAddress();
        result = result * 59 + (($address == null) ? 43 : $address.hashCode());
        final Object $privateKey = this.getPrivateKey();
        result = result * 59 + (($privateKey == null) ? 43 : $privateKey.hashCode());
        result = result * 59 + this.getChainType();
        return result;
    }
    
    @Override
    public String toString() {
        return "HotWalletInfo(id=" + this.getId() + ", address=" + this.getAddress() + ", privateKey=" + this.getPrivateKey() + ", chainType=" + this.getChainType() + ")";
    }
}

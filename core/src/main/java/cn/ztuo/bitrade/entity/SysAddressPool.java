package cn.ztuo.bitrade.entity;

import javax.persistence.*;

import org.hibernate.validator.constraints.*;

@Entity
@Table(name = "sys_address_pool")
public class SysAddressPool {
    @Id
    @NotBlank(message = "\u5730\u5740\u4e0d\u5f97\u4e3a\u7a7a")
    private String address;
    private int type;
    private int status;

    public String getAddress() {
        return this.address;
    }

    public int getType() {
        return this.type;
    }

    public int getStatus() {
        return this.status;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SysAddressPool)) {
            return false;
        }
        final SysAddressPool other = (SysAddressPool) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null) {
            if (other$address == null) {
                return this.getType() == other.getType() && this.getStatus() == other.getStatus();
            }
        } else if (this$address.equals(other$address)) {
            return this.getType() == other.getType() && this.getStatus() == other.getStatus();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SysAddressPool;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $address = this.getAddress();
        result = result * 59 + (($address == null) ? 43 : $address.hashCode());
        result = result * 59 + this.getType();
        result = result * 59 + this.getStatus();
        return result;
    }

    @Override
    public String toString() {
        return "SysAddressPool(address=" + this.getAddress() + ", type=" + this.getType() + ", status=" + this.getStatus() + ")";
    }
}

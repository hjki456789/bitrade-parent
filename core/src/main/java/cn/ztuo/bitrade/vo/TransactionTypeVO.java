package cn.ztuo.bitrade.vo;

public class TransactionTypeVO
{
    private Integer code;
    private String name;
    
    public Integer getCode() {
        return this.code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setCode(final Integer code) {
        this.code = code;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TransactionTypeVO)) {
            return false;
        }
        final TransactionTypeVO other = (TransactionTypeVO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        Label_0065: {
            if (this$code == null) {
                if (other$code == null) {
                    break Label_0065;
                }
            }
            else if (this$code.equals(other$code)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null) {
            if (other$name == null) {
                return true;
            }
        }
        else if (this$name.equals(other$name)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof TransactionTypeVO;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $code = this.getCode();
        result = result * 59 + (($code == null) ? 43 : $code.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "TransactionTypeVO(code=" + this.getCode() + ", name=" + this.getName() + ")";
    }
}

package cn.ztuo.bitrade.exchances.impl.huobi.model;

public class HuobiTickResponse<T>
{
    private String status;
    private String ch;
    private Long ts;
    private T tick;
    
    public String getStatus() {
        return this.status;
    }
    
    public String getCh() {
        return this.ch;
    }
    
    public Long getTs() {
        return this.ts;
    }
    
    public T getTick() {
        return this.tick;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public void setCh(final String ch) {
        this.ch = ch;
    }
    
    public void setTs(final Long ts) {
        this.ts = ts;
    }
    
    public void setTick(final T tick) {
        this.tick = tick;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HuobiTickResponse)) {
            return false;
        }
        final HuobiTickResponse<?> other = (HuobiTickResponse<?>)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0065: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0065;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$ch = this.getCh();
        final Object other$ch = other.getCh();
        Label_0102: {
            if (this$ch == null) {
                if (other$ch == null) {
                    break Label_0102;
                }
            }
            else if (this$ch.equals(other$ch)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$ts = this.getTs();
        final Object other$ts = other.getTs();
        Label_0139: {
            if (this$ts == null) {
                if (other$ts == null) {
                    break Label_0139;
                }
            }
            else if (this$ts.equals(other$ts)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$tick = this.getTick();
        final Object other$tick = other.getTick();
        if (this$tick == null) {
            if (other$tick == null) {
                return true;
            }
        }
        else if (this$tick.equals(other$tick)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HuobiTickResponse;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $ch = this.getCh();
        result = result * 59 + (($ch == null) ? 43 : $ch.hashCode());
        final Object $ts = this.getTs();
        result = result * 59 + (($ts == null) ? 43 : $ts.hashCode());
        final Object $tick = this.getTick();
        result = result * 59 + (($tick == null) ? 43 : $tick.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HuobiTickResponse(status=" + this.getStatus() + ", ch=" + this.getCh() + ", ts=" + this.getTs() + ", tick=" + this.getTick() + ")";
    }
}

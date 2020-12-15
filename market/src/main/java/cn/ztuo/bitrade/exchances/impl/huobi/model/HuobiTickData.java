package cn.ztuo.bitrade.exchances.impl.huobi.model;

import java.util.*;

public class HuobiTickData<T>
{
    private String id;
    private String ts;
    private List<T> data;
    
    public String getId() {
        return this.id;
    }
    
    public String getTs() {
        return this.ts;
    }
    
    public List<T> getData() {
        return this.data;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setTs(final String ts) {
        this.ts = ts;
    }
    
    public void setData(final List<T> data) {
        this.data = data;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HuobiTickData)) {
            return false;
        }
        final HuobiTickData<?> other = (HuobiTickData<?>)o;
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
        final Object this$ts = this.getTs();
        final Object other$ts = other.getTs();
        Label_0102: {
            if (this$ts == null) {
                if (other$ts == null) {
                    break Label_0102;
                }
            }
            else if (this$ts.equals(other$ts)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null) {
            if (other$data == null) {
                return true;
            }
        }
        else if (this$data.equals(other$data)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HuobiTickData;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $ts = this.getTs();
        result = result * 59 + (($ts == null) ? 43 : $ts.hashCode());
        final Object $data = this.getData();
        result = result * 59 + (($data == null) ? 43 : $data.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HuobiTickData(id=" + this.getId() + ", ts=" + this.getTs() + ", data=" + this.getData() + ")";
    }
}

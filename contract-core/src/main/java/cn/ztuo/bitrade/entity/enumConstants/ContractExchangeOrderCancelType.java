package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum ContractExchangeOrderCancelType implements BaseEnum
{
    SELF_OPERATION("\u624b\u52a8\u64a4\u9500"), 
    SYSTEM_OPERATION("\u7cfb\u7edf\u64a4\u9500");
    
    private String cnName;
    
    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }
    
    @ConstructorProperties({ "cnName" })
    private ContractExchangeOrderCancelType(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

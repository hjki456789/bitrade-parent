package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum ContractExchangeOrderCloseType implements BaseEnum
{
    CLOSE_SELF("\u624b\u52a8\u5e73\u4ed3"), 
    CLOSE_AUTO("\u81ea\u52a8\u5e73\u4ed3"), 
    CLOSE_FOLLOW("\u8ddf\u968f\u5e73\u4ed3");
    
    private String cnName;
    
    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }
    
    @ConstructorProperties({ "cnName" })
    private ContractExchangeOrderCloseType(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

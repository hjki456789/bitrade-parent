package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum ContractOrderSide implements BaseEnum
{
    BUY_UP("\u4e70\u6da8"), 
    BUY_DOWN("\u4e70\u8dcc");
    
    private String cnName;
    
    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }
    
    @ConstructorProperties({ "cnName" })
    private ContractOrderSide(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}
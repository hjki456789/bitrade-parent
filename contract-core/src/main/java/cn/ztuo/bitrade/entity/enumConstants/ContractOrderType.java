package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum ContractOrderType implements BaseEnum
{
    MARKET_PRICE("\u5e02\u4ef7"), 
    LIMIT_PRICE("\u9650\u4ef7");
    
    private String cnName;
    
    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }
    
    @ConstructorProperties({ "cnName" })
    private ContractOrderType(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

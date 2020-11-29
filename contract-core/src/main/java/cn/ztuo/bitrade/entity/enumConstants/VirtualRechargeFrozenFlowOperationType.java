package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum VirtualRechargeFrozenFlowOperationType implements BaseEnum
{
    BE_NODE("\u6210\u4e3a\u8282\u70b9"), 
    ADD_DEPOSITAMOUNT("\u589e\u52a0\u4fdd\u8bc1\u91d1"), 
    PLAT_LOSS("\u5e73\u53f0\u4e8f\u635f\uff0c\u51cf\u5c11\u8282\u70b9\u7528\u6237\u7684\u865a\u5145\u51bb\u7ed3\u91d1\u989d");
    
    private String cnName;
    
    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }
    
    @ConstructorProperties({ "cnName" })
    private VirtualRechargeFrozenFlowOperationType(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}
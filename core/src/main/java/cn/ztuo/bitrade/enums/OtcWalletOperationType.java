package cn.ztuo.bitrade.enums;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum OtcWalletOperationType implements BaseEnum
{
    CHANGE_BALANCE("\u540e\u53f0\u8d26\u6237\u5145\u5e01");
    
    private String cnName;
    
    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
    
    @ConstructorProperties({ "cnName" })
    private OtcWalletOperationType(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

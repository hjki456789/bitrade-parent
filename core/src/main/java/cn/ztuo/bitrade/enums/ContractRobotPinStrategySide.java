package cn.ztuo.bitrade.enums;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum ContractRobotPinStrategySide implements BaseEnum
{
    UP("\u6da8"), 
    DOWN("\u8dcc");
    
    private String cnName;
    
    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
    
    public ContractRobotPinStrategySide[] getAll() {
        return values();
    }
    
    public static ContractRobotPinStrategySide getByValue(final int value) {
        for (final ContractRobotPinStrategySide credentialsType : values()) {
            if (credentialsType.getOrdinal() == value) {
                return credentialsType;
            }
        }
        return null;
    }
    
    @ConstructorProperties({ "cnName" })
    private ContractRobotPinStrategySide(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

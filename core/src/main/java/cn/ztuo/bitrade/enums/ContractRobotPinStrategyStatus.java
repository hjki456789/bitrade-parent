package cn.ztuo.bitrade.enums;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractRobotPinStrategyStatus implements BaseEnum {
    STOP("\u505c\u6b62\u8fd0\u884c"),
    RUNNING("\u6b63\u5728\u8fd0\u884c");

    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

    public ContractRobotPinStrategyStatus[] getAll() {
        return values();
    }

    public static ContractRobotPinStrategyStatus getByValue(final int value) {
        for (final ContractRobotPinStrategyStatus credentialsType : values()) {
            if (credentialsType.getOrdinal() == value) {
                return credentialsType;
            }
        }
        return null;
    }

    @ConstructorProperties({"cnName"})
    private ContractRobotPinStrategyStatus(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

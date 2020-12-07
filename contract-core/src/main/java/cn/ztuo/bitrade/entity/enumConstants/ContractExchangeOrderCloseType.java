package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractExchangeOrderCloseType implements BaseEnum {
    CLOSE_SELF("手动平仓"),
    CLOSE_AUTO("自动平仓"),
    CLOSE_FOLLOW("跟随平仓");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({"cnName"})
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

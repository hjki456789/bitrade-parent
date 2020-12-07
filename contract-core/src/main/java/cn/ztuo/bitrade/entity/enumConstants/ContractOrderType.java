package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractOrderType implements BaseEnum {
    MARKET_PRICE("市价"),
    LIMIT_PRICE("限价");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({"cnName"})
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

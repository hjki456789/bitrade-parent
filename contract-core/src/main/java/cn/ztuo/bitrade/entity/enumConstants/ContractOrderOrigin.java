package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractOrderOrigin implements BaseEnum {
    ORIGIN_PC("PC\u7aef"),
    ORIGIN_APP("\u79fb\u52a8\u7aef"),
    ORIGIN_FOLLOW("\u8ddf\u968f\u4e0b\u5355");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({"cnName"})
    private ContractOrderOrigin(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractOrderOrigin implements BaseEnum {
    ORIGIN_PC("PC端"),
    ORIGIN_APP("移动端"),
    ORIGIN_FOLLOW("跟随下单");

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

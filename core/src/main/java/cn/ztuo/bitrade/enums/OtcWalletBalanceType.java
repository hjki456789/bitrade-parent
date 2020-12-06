package cn.ztuo.bitrade.enums;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum OtcWalletBalanceType implements BaseEnum {
    AVAILABLE_BALANCE("\u53ef\u7528\u4f59\u989d"),
    FROZEN_BALANCE("\u51bb\u7ed3\u4f59\u989d"),
    RELEASE_BALANCE("\u5f85\u91ca\u653e\u4f59\u989d");

    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({"cnName"})
    private OtcWalletBalanceType(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

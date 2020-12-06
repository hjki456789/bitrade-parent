package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractOrderStatus implements BaseEnum {
    HANG_PROCESSING("\u59d4\u6258\u4e2d"),
    HOLD_PROCESSING("\u6301\u4ed3\u4e2d"),
    CANCEL("\u59d4\u6258\u53d6\u6d88"),
    SELF_CLOSE("\u624b\u52a8\u5e73\u4ed3"),
    FORCE_CLOSE("\u5f3a\u5236\u5e73\u4ed3"),
    EXPOLDE_WAREHOUSE("\u7206\u4ed3"),
    THROUGH_WAREHOUSE("\u7a7f\u4ed3"),
    STOP_PROFIT("\u6b62\u76c8"),
    STOP_LOSS("\u6b62\u635f"),
    FOLLOW_CLOSE("\u8ddf\u968f\u5e73\u4ed3"),
    ONE_CLICK_NO_CONFIRM("\u4e00\u952e\u4e0b\u5355\u5f85\u786e\u8ba4"),
    ONE_CLICK_CANCEL("\u4e00\u952e\u4e0b\u5355\u53d6\u6d88");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({"cnName"})
    private ContractOrderStatus(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

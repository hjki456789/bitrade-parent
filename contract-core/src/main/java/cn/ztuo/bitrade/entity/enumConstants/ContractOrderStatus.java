package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractOrderStatus implements BaseEnum {
    HANG_PROCESSING("委托中"),
    HOLD_PROCESSING("持仓中"),
    CANCEL("委托取消"),
    SELF_CLOSE("手动平仓"),
    FORCE_CLOSE("强制平仓"),
    EXPOLDE_WAREHOUSE("爆仓"),
    THROUGH_WAREHOUSE("穿仓"),
    STOP_PROFIT("止盈"),
    STOP_LOSS("止损"),
    FOLLOW_CLOSE("跟随平仓"),
    ONE_CLICK_NO_CONFIRM("一键下单待确认"),
    ONE_CLICK_CANCEL("一键下单取消");

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

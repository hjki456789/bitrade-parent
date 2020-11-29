package cn.ztuo.bitrade.constant;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.*;
@AllArgsConstructor
@Getter
public enum CommissionType implements BaseEnum
{
    PROFIT_LOSS("盈亏返佣"),
    FEE("手续费返佣");

    @Setter
    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
}

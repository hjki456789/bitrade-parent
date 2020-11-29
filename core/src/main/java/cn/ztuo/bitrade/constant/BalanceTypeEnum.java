package cn.ztuo.bitrade.constant;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.*;
@AllArgsConstructor
@Getter
public enum BalanceTypeEnum implements BaseEnum
{
    BALANCE("可用余额"),
    FROZEN_BALANCE("冻结余额"),
    RELAESE_BALANCE("可释放余额"),
    AUDIT_SUCCESS("虚充冻结金额");

    @Setter
    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
}

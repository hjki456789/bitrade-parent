package cn.ztuo.bitrade.constant;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.*;

@AllArgsConstructor
@Getter
public enum AccountTypeEnum implements BaseEnum {
    COIN("币币账户"),
    CONTRACT("合约账户"),
    OTC("法币账户"),
    OPTION("期权账户"),
    DOUBLE_DIRECTION("双仓合约账户"),
    DEPOSIT("托管账户");
    @Setter
    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
}

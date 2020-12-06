package cn.ztuo.bitrade.constant;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.*;

@Getter
@AllArgsConstructor
public enum UserType implements BaseEnum {
    COMMON("普通用户"),
    LEAD_ORDER("带单用户");

    @Setter
    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
}

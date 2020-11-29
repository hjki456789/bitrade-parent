package cn.ztuo.bitrade.constant;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.*;

@AllArgsConstructor
@Getter
public enum ProfitLossType implements BaseEnum
{
    MEMBER_WEEK("用户周盈亏"),
    TEAM_WEEK("团队周盈亏"),
    MEMBER_TOTAL("用户总体盈亏"),
    TEAM_TOTAL("团队总体盈亏"),
    MEMBER_MONTH("用户月盈亏"),
    TEAM_MONTH("团队月盈亏");

    @Setter
    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
}

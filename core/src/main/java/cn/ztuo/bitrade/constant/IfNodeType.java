package cn.ztuo.bitrade.constant;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.*;

@AllArgsConstructor
@Getter
public enum IfNodeType implements BaseEnum {
    COMMON("普通用户"),
    NODE("节点"),
    PROXY("代理"),
    MARKET("市场管理员");

    @Setter
    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

}

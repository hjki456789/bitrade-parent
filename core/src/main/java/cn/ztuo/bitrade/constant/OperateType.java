package cn.ztuo.bitrade.constant;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.beans.*;
@AllArgsConstructor
@Getter
public enum OperateType implements BaseEnum
{
    ADD_MARKET("设置市场管理员"),
    CANCEL_MARKET("取消市场管理员"),
    ADD_PROXY("设置代理"),
    CANCEL_PROXY("取消代理"),
    ADD_NODE("设置节点"),
    CANCEL_NODE("取消节点");

    @Setter
    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

}

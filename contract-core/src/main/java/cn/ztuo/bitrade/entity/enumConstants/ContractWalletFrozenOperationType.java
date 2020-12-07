package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractWalletFrozenOperationType implements BaseEnum {
    BE_NODE("成为节点"),
    ADD_DEPOSITAMOUNT("节点用户增加保证金"),
    PLAT_LOSS("平台亏损，减少节点用的冻结资产"),
    CANCEL_NODE("取消节点");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({"cnName"})
    private ContractWalletFrozenOperationType(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractWalletOperationType implements BaseEnum {
    TRANSFER_IN("划转入"),
    TRANSFER_OUT("划转出"),
    CONTRACT_PLACE_ORDER("合约交易下单"),
    CONTRACT_CLOSE_ORDER("合约订单平仓"),
    LIMIT_ORDER_TO_HOLD("计划订单进入持仓状态"),
    OPERATE_BALANCE("后台管理员修改"),
    BE_NODE("成为节点"),
    CHANGE_BALANCE("合约账户充币"),
    RETURN_COMMISSION("返佣"),
    SUPER_NODE_RETURN_COMMISSION("一级节点减少返佣金额"),
    ADD_DEPOSITAMOUNT("节点用户增加保证金"),
    CANCEL_NODE("取消节点"),
    CONTRACT_EXPLODE_ORDER("合约订单爆仓"),
    CONTRACT_WALLET_THROUGH("合约订单穿仓"),
    CONTRACT_LOSS_REWARD("合约订单亏损返回"),
    DOUBLE_CONTRACT_ORDER_FAIL_RETURN("双仓合约订单外部下单失败返还"),
    DEPOSIT_CONTRACT_ORDER_FAIL_RETURN("托管合约订单外部下单失败返还");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({"cnName"})
    private ContractWalletOperationType(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

package cn.ztuo.bitrade.entity.enumConstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum ContractWalletOperationType implements BaseEnum {
    TRANSFER_IN("\u5212\u8f6c\u5165"),
    TRANSFER_OUT("\u5212\u8f6c\u51fa"),
    CONTRACT_PLACE_ORDER("\u5408\u7ea6\u4ea4\u6613\u4e0b\u5355"),
    CONTRACT_CLOSE_ORDER("\u5408\u7ea6\u8ba2\u5355\u5e73\u4ed3"),
    LIMIT_ORDER_TO_HOLD("\u8ba1\u5212\u8ba2\u5355\u8fdb\u5165\u6301\u4ed3\u72b6\u6001"),
    OPERATE_BALANCE("\u540e\u53f0\u7ba1\u7406\u5458\u4fee\u6539"),
    BE_NODE("\u6210\u4e3a\u8282\u70b9"),
    CHANGE_BALANCE("\u5408\u7ea6\u8d26\u6237\u5145\u5e01"),
    RETURN_COMMISSION("\u8fd4\u4f63"),
    SUPER_NODE_RETURN_COMMISSION("\u4e00\u7ea7\u8282\u70b9\u51cf\u5c11\u8fd4\u4f63\u91d1\u989d"),
    ADD_DEPOSITAMOUNT("\u8282\u70b9\u7528\u6237\u589e\u52a0\u4fdd\u8bc1\u91d1"),
    CANCEL_NODE("\u53d6\u6d88\u8282\u70b9"),
    CONTRACT_EXPLODE_ORDER("\u5408\u7ea6\u8ba2\u5355\u7206\u4ed3"),
    CONTRACT_WALLET_THROUGH("\u5408\u7ea6\u8ba2\u5355\u7a7f\u4ed3"),
    CONTRACT_LOSS_REWARD("\u5408\u7ea6\u8ba2\u5355\u4e8f\u635f\u8fd4\u56de"),
    DOUBLE_CONTRACT_ORDER_FAIL_RETURN("\u53cc\u4ed3\u5408\u7ea6\u8ba2\u5355\u5916\u90e8\u4e0b\u5355\u5931\u8d25\u8fd4\u8fd8"),
    DEPOSIT_CONTRACT_ORDER_FAIL_RETURN("\u6258\u7ba1\u5408\u7ea6\u8ba2\u5355\u5916\u90e8\u4e0b\u5355\u5931\u8d25\u8fd4\u8fd8");

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

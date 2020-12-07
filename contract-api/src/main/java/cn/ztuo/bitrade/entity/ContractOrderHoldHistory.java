package cn.ztuo.bitrade.entity;

import java.math.*;

import cn.ztuo.bitrade.entity.enumConstants.*;

import java.util.*;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContractOrderHoldHistory {
    private long id;
    private String symbol;
    private ContractOrderSide side;
    private ContractOrderType type;
    private BigDecimal openPrice;
    private BigDecimal closePrice;
    private BigDecimal capitalAmount;
    private BigDecimal leverMultiple;
    private ContractOrderStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date closeTime;

    public ContractOrderHoldHistory() {
        this.openPrice = BigDecimal.ZERO;
        this.closePrice = BigDecimal.ZERO;
        this.capitalAmount = BigDecimal.ZERO;
        this.leverMultiple = BigDecimal.ZERO;
    }
}

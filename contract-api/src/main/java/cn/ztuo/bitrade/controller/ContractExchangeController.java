package cn.ztuo.bitrade.controller;

import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.transform.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;

import cn.ztuo.bitrade.constant.*;
import cn.ztuo.bitrade.entity.enumConstants.*;

import javax.servlet.http.*;

@RestController
@RequestMapping({"/order"})
public class ContractExchangeController extends BaseController {
    @Autowired
    private ContractExchangeService contractExchangeService;

    @PostMapping({"/getCurrentExplodeInfo"})
    public MessageResult getCurrentExplodeInfo(@SessionAttribute("API_MEMBER") final AuthMember authMember) {
        return this.contractExchangeService.getCurrentExplodeInfo(authMember.getId());
    }

    @PostMapping({"/add"})
    public MessageResult addContractOrder(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "symbol", required = true) final String symbol, @RequestParam(value = "side", required = true) final ContractOrderSide side, @RequestParam(value = "type", required = true) final ContractOrderType type, @RequestParam(value = "price", required = true) final BigDecimal price, @RequestParam(value = "limitTriggerPrice", required = false, defaultValue = "0") final BigDecimal limitTriggerPrice, @RequestParam(value = "limitPrice", required = false, defaultValue = "0") BigDecimal limitPrice, @RequestParam(value = "amount", required = true) final BigDecimal amount, @RequestParam(value = "capitalAmount", required = true) final BigDecimal capitalAmount, @RequestParam(value = "leverMultiple", required = true) final BigDecimal leverMultiple, @RequestParam(value = "ifStopLoss", required = true) final BooleanEnum ifStopLoss, @RequestParam(value = "stopLossPrice", required = false, defaultValue = "0") final BigDecimal stopLossPrice, @RequestParam(value = "ifStopProfit", required = true) final BooleanEnum ifStopProfit, @RequestParam(value = "stopProfitPrice", required = false, defaultValue = "0") final BigDecimal stopProfitPrice, @RequestParam(value = "origin", required = true) final ContractOrderOrigin origin, @RequestParam(value = "walletType", required = false, defaultValue = "0") final int walletType, @RequestParam(value = "isOneClickOrder", required = false, defaultValue = "0") final int isOneClickOrder, final HttpServletRequest request) {
        final String originIp = this.getRemoteIp(request);
        limitPrice = BigDecimal.ZERO;
        return this.contractExchangeService.addContractOrder(Long.valueOf(authMember.getId()), symbol, side, type, price, limitTriggerPrice, limitPrice, amount, capitalAmount, leverMultiple, ifStopLoss, stopLossPrice, ifStopProfit, stopProfitPrice, origin, originIp, walletType, isOneClickOrder);
    }

    @PostMapping({"/getHoldingOrders"})
    public MessageResult getHoldingOrders(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "symbol", required = false) final String symbol, @RequestParam(value = "contractType", required = false) final Integer contractType, @RequestParam(value = "walletType", required = false) final Integer walletType, final int pageNo, final int pageSize) {
        return this.contractExchangeService.getHoldingOrders(Long.valueOf(authMember.getId()), symbol, pageNo, pageSize, contractType, walletType);
    }

    @PostMapping({"/close"})
    public MessageResult closeContractOrder(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "orderId", required = true) final Long orderId, @RequestParam(value = "closePrice", required = true) final BigDecimal closePrice, final HttpServletRequest request) {
        return this.contractExchangeService.closeContractOrder(Long.valueOf(authMember.getId()), orderId, closePrice);
    }

    @PostMapping({"/updateStopCondition"})
    public MessageResult updateStopCondition(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "orderId", required = true) final Long orderId, @RequestParam(value = "ifStopLoss", required = true) final BooleanEnum ifStopLoss, @RequestParam(value = "stopLossPrice", required = false, defaultValue = "0") final BigDecimal stopLossPrice, @RequestParam(value = "ifStopProfit", required = true) final BooleanEnum ifStopProfit, @RequestParam(value = "stopProfitPrice", required = false, defaultValue = "0") final BigDecimal stopProfitPrice, final HttpServletRequest request) {
        return this.contractExchangeService.updateStopCondition(Long.valueOf(authMember.getId()), orderId, ifStopLoss, stopLossPrice, ifStopProfit, stopProfitPrice);
    }

    @PostMapping({"/getOrdersResultHistory"})
    public MessageResult getOrdersResultHistory(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "symbol", required = false) final String symbol, @RequestParam(value = "contractType", required = false) final Integer contractType, @RequestParam(value = "walletType", required = false) final Integer walletType, final int pageNo, final int pageSize) {
        return this.contractExchangeService.getOrdersResultHistory(Long.valueOf(authMember.getId()), symbol, pageNo, pageSize, contractType, walletType);
    }

    @PostMapping({"/getLimitOrders"})
    public MessageResult getLimitOrders(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "symbol", required = false) final String symbol, @RequestParam(value = "contractType", required = false) final Integer contractType, @RequestParam(value = "walletType", required = false) final Integer walletType, final int pageNo, final int pageSize) {
        return this.contractExchangeService.getLimitOrders(Long.valueOf(authMember.getId()), symbol, pageNo, pageSize, contractType, walletType);
    }

    @PostMapping({"/cancelLimitOrder"})
    public MessageResult cancelLimitOrder(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "orderId", required = true) final Long orderId) {
        return this.contractExchangeService.cancelLimitOrder(Long.valueOf(authMember.getId()), orderId);
    }

    @PostMapping({"/getLimitOrdersResultHistory"})
    public MessageResult getLimitOrdersResultHistory(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "symbol", required = false) final String symbol, @RequestParam(value = "contractType", required = false) final Integer contractType, @RequestParam(value = "walletType", required = false) final Integer walletType, final int pageNo, final int pageSize) {
        return this.contractExchangeService.getLimitOrdersResultHistory(Long.valueOf(authMember.getId()), symbol, pageNo, pageSize, contractType, walletType);
    }

    @PostMapping({"/getExternalExchangeOrders"})
    public MessageResult getExternalExchangeOrders(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "fromExchangeId", required = false) final Integer fromExchangeId, @RequestParam(value = "status", required = true) final Integer status, final int pageNo, final int pageSize) {
        return this.contractExchangeService.getExternalExchangeOrders(authMember.getId(), pageNo, pageSize, fromExchangeId, status);
    }

    @PostMapping({"/cancelExternalExchangeOrder"})
    public MessageResult cancelExternalExchangeOrder(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "id", required = true) final Long id) {
        return this.contractExchangeService.cancelExternalExchangeOrder(Long.valueOf(authMember.getId()), id);
    }

    @PostMapping({"/getOneClickOrders"})
    public MessageResult getOneClickOrders(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "symbol", required = false) final String symbol, @RequestParam(value = "contractType", required = false) final Integer contractType, @RequestParam(value = "walletType", required = false) final Integer walletType, final int pageNo, final int pageSize) {
        return this.contractExchangeService.getOneClickOrders(Long.valueOf(authMember.getId()), symbol, pageNo, pageSize, contractType, walletType);
    }

    @PostMapping({"/cancelOneClickOrder"})
    public MessageResult cancelOneClickOrder(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "orderId", required = true) final Long orderId) {
        return this.contractExchangeService.cancelOneClickOrder(Long.valueOf(authMember.getId()), orderId);
    }
}

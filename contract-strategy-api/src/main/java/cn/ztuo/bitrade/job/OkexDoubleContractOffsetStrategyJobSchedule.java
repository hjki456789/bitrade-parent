package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import cn.ztuo.bitrade.exchanges.okex.service.swap.impl.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.exchanges.okex.response_entity.*;
import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;

import java.lang.reflect.Type;
import java.util.*;

import cn.ztuo.bitrade.entity.contractdouble.*;
import org.springframework.scheduling.annotation.*;
import org.slf4j.*;

@Component
public class OkexDoubleContractOffsetStrategyJobSchedule {
    private static final Logger log;
    @Autowired
    private ContractDoubleMemberStrategyOrderService contractDoubleMemberStrategyOrderService;
    @Autowired
    private DoubleContractOffsetStrategyJobService doubleContractOffsetStrategyJobService;
    @Autowired
    private ContractDoubleExchangeConfigService contractDoubleExchangeConfigService;
    @Autowired
    private ContractOrderService contractOrderService;
    @Autowired
    private ContractDoubleMemberApiService contractDoubleMemberApiService;

    @Scheduled(fixedDelay = 1000L, initialDelay = 2000L)
    public void checkOrderStatus() {
        final List<ContractDoubleMemberStrategyOrder> orderList = this.contractDoubleMemberStrategyOrderService.findAllNeedCheckOrders(Integer.valueOf(2));
        OkexDoubleContractOffsetStrategyJobSchedule.log.info("checkOrderStatus COUNT==>{}", (Object) ((null != orderList) ? orderList.size() : 0));
        for (final ContractDoubleMemberStrategyOrder strategyOrder : orderList) {
            try {
                final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                final String OKEX_DM_API_URL = exchangeConfig.getApiUrl();
                final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(strategyOrder.getApiId());
                if (null == api) {
                    return;
                }
                final SwapUserAPIServiceImpl okexApi = new SwapUserAPIServiceImpl(OKEX_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey(), api.getPassphrase());
                final String result = okexApi.selectOrderByOrderId(strategyOrder.getSymbol(), strategyOrder.getFromExchangeOrderId());
                OkexDoubleContractOffsetStrategyJobSchedule.log.info("OKEX Result==>{}", (Object) result);
                if (!StringUtils.isNotEmpty((CharSequence) result)) {
                    continue;
                }
                final OkexSwapOrderDetailResponse orderIdResp = (OkexSwapOrderDetailResponse) JSON.parseObject(result, (Type) OkexDoubleContractOffsetStrategyJobSchedule.class, new Feature[0]);
                if (null == orderIdResp || !StringUtils.isNotEmpty((CharSequence) orderIdResp.getOrder_id()) || !orderIdResp.getOrder_id().equals(strategyOrder.getFromExchangeOrderId())) {
                    continue;
                }
                if (strategyOrder.getMatchStatus() == 1 && orderIdResp.getState().equals("0")) {
                    continue;
                }
                if (strategyOrder.getMatchStatus() == 1 && orderIdResp.getState().equals("2")) {
                    this.doubleContractOffsetStrategyJobService.markOrderComplete(strategyOrder, 6);
                } else {
                    if (strategyOrder.getMatchStatus() != 1 || !orderIdResp.getState().equals("-1")) {
                        continue;
                    }
                    this.doubleContractOffsetStrategyJobService.markOrderRetracement(strategyOrder, 7);
                }
            } catch (Exception e) {
                OkexDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0c\u5f02\u5e38\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) e);
            }
        }
    }

    @Scheduled(fixedDelay = 3000L, initialDelay = 2000L)
    public void checkOrderCloseStatus() {
        final List<ContractDoubleMemberStrategyOrder> orderList = (List<ContractDoubleMemberStrategyOrder>) this.contractDoubleMemberStrategyOrderService.findAllNeedCheckCloseOrders(Integer.valueOf(2));
        for (final ContractDoubleMemberStrategyOrder strategyOrder : orderList) {
            try {
                final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                final String OKEX_DM_API_URL = exchangeConfig.getApiUrl();
                final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(strategyOrder.getApiId());
                if (null == api) {
                    return;
                }
                final SwapUserAPIServiceImpl okexApi = new SwapUserAPIServiceImpl(OKEX_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey(), api.getPassphrase());
                final String result = okexApi.selectOrderByOrderId(strategyOrder.getSymbol(), strategyOrder.getFromExchangeOrderId());
                OkexDoubleContractOffsetStrategyJobSchedule.log.info("OKEX Result==>{}", (Object) result);
                if (!StringUtils.isNotEmpty((CharSequence) result)) {
                    continue;
                }
                final OkexSwapOrderDetailResponse orderIdResp = (OkexSwapOrderDetailResponse) JSON.parseObject(result, (Type) OkexDoubleContractOffsetStrategyJobSchedule.class, new Feature[0]);
                if (null == orderIdResp || !StringUtils.isNotEmpty((CharSequence) orderIdResp.getOrder_id()) || !orderIdResp.getOrder_id().equals(strategyOrder.getFromExchangeOrderId()) || strategyOrder.getMatchStatus() != 5 || !orderIdResp.getState().equals("2")) {
                    continue;
                }
                this.contractDoubleMemberStrategyOrderService.updateCloseMatchStatus(strategyOrder.getId(), 6);
            } catch (Exception e) {
                OkexDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0c\u5f02\u5e38\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) e);
            }
        }
    }

    static {
        log = LoggerFactory.getLogger((Class) OkexDoubleContractOffsetStrategyJobSchedule.class);
    }
}

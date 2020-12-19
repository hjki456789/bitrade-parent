package cn.ztuo.bitrade.job;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import cn.ztuo.bitrade.exchanges.huobi.common.api.*;
import org.apache.commons.lang3.*;
import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.huobi.common.entity.*;

import java.lang.reflect.Type;
import java.util.*;

import org.springframework.scheduling.annotation.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.enumConstants.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.exchanges.okex.service.swap.impl.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import org.slf4j.*;

@Component
public class HuoBiDoubleContractOffsetStrategyJobSchedule {
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
        final List<ContractDoubleMemberStrategyOrder> orderList = (List<ContractDoubleMemberStrategyOrder>) this.contractDoubleMemberStrategyOrderService.findAllNeedCheckOrders(Integer.valueOf(1));
        HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("checkOrderStatus COUNT==>{}", (Object) ((null != orderList) ? orderList.size() : 0));
        for (final ContractDoubleMemberStrategyOrder strategyOrder : orderList) {
            try {
                final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
                final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey());
                final String result = huobiApi.futureContractOrderDetail(strategyOrder.getSymbol(), strategyOrder.getFromExchangeOrderId(), "", "", "", "1");
                HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("HUOBI Result==>{}", (Object) result);
                if (!StringUtils.isNotEmpty((CharSequence) result)) {
                    continue;
                }
                final HuoBiCommonResponse<HuoBiOrderDetailResponse> orderIdResp =  JSON.parseObject(result, (Type)HuoBiDoubleContractOffsetStrategyJobSchedule.class, new Feature[0]);
                if (null == orderIdResp || !orderIdResp.getStatus().equals("ok")) {
                    continue;
                }
                final HuoBiOrderDetailResponse response = orderIdResp.getData();
                if (!response.getOrder_id_str().equals(strategyOrder.getFromExchangeOrderId())) {
                    continue;
                }
                if (strategyOrder.getMatchStatus() == response.getStatus()) {
                    continue;
                }
                if (response.getStatus() == 5 || response.getStatus() == 6) {
                    this.doubleContractOffsetStrategyJobService.markOrderComplete(strategyOrder, response.getStatus());
                } else if (response.getStatus() == 7) {
                    this.doubleContractOffsetStrategyJobService.markOrderRetracement(strategyOrder, response.getStatus());
                } else {
                    this.contractDoubleMemberStrategyOrderService.updateMatchStatus(strategyOrder.getId(), response.getStatus());
                }
            } catch (Exception e) {
                HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0c\u5f02\u5e38\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) e);
            }
        }
    }

    @Scheduled(fixedDelay = 3000L, initialDelay = 2000L)
    public void checkOrderCloseStatus() {
        final List<ContractDoubleMemberStrategyOrder> orderList = (List<ContractDoubleMemberStrategyOrder>) this.contractDoubleMemberStrategyOrderService.findAllNeedCheckCloseOrders(1);
        for (final ContractDoubleMemberStrategyOrder strategyOrder : orderList) {
            try {
                final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
                final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey());
                final String result = huobiApi.futureContractOrderDetail(strategyOrder.getSymbol(), strategyOrder.getCloseOrderId(), "", "", "", "1");
                if (!StringUtils.isNotEmpty((CharSequence) result)) {
                    continue;
                }
                final HuoBiCommonResponse<HuoBiOrderDetailResponse> orderIdResp =  JSON.parseObject(result, (Type)HuoBiDoubleContractOffsetStrategyJobSchedule.class, new Feature[0]);
                if (null == orderIdResp || !orderIdResp.getStatus().equals("ok")) {
                    continue;
                }
                final HuoBiOrderDetailResponse response = orderIdResp.getData();
                if (response.getStatus() != 5 && response.getStatus() != 6) {
                    continue;
                }
                this.contractDoubleMemberStrategyOrderService.updateCloseMatchStatus(strategyOrder.getId(), response.getStatus());
            } catch (Exception e) {
                HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0c\u5f02\u5e38\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) e);
            }
        }
    }

    @Scheduled(fixedDelay = 5000L, initialDelay = 2000L)
    public void getExternalOpenOrdersProfit() {
        while (true) {
            final Page<ContractDoubleMemberStrategyOrder> page = (Page<ContractDoubleMemberStrategyOrder>) this.contractDoubleMemberStrategyOrderService.getExternalOpenExchangeOrders((Long) null, 1, 100, Integer.valueOf(1));
            if (null == page || null == page.getContent() || page.getContent().size() <= 0) {
                break;
            }
            for (final ContractDoubleMemberStrategyOrder strategyOrder : page.getContent()) {
                try {
                    final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                    final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
                    final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey());
                    final String result = huobiApi.futureContractOrderDetail(strategyOrder.getSymbol(), strategyOrder.getCloseOrderId(), "", "", "", "1");
                    if (!StringUtils.isNotEmpty((CharSequence) result)) {
                        continue;
                    }
                    final HuoBiCommonResponse<HuoBiOrderDetailResponse> orderIdResp =  JSON.parseObject(result, (Type)HuoBiDoubleContractOffsetStrategyJobSchedule.class, new Feature[0]);
                    if (null == orderIdResp || !orderIdResp.getStatus().equals("ok")) {
                        continue;
                    }
                    final HuoBiOrderDetailResponse response = orderIdResp.getData();
                    this.contractDoubleMemberStrategyOrderService.updateBasicInfo(strategyOrder.getId(), response.getMargin_frozen(), response.getProfit());
                } catch (Exception e) {
                    HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0c\u5f02\u5e38\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) e);
                }
            }
        }
    }

    @Scheduled(fixedDelay = 600000L, initialDelay = 2000L)
    public void checkOrder() {
        final List<ContractExchangeOrder> list = (List<ContractExchangeOrder>) this.contractOrderService.findOneClickOrdersByStatus(ContractOrderStatus.ONE_CLICK_NO_CONFIRM);
        for (final ContractExchangeOrder order : list) {
            try {
                if (order.getSequence() + 600000L > System.currentTimeMillis()) {
                    continue;
                }
                final ContractDoubleMemberStrategyOrder strategyOrder = this.contractDoubleMemberStrategyOrderService.findByOrderId(order.getId());
                if (null == strategyOrder || null == strategyOrder.getContractOrderId()) {
                    this.doubleContractOffsetStrategyJobService.markOrderRetracement(Long.valueOf(order.getId()));
                    return;
                }
                if (order.getSequence() + 86400000L >= System.currentTimeMillis()) {
                    continue;
                }
                if (strategyOrder.getStatus() == 1) {
                    this.doubleContractOffsetStrategyJobService.markOrderRetracement(Long.valueOf(order.getId()));
                } else {
                    if (strategyOrder.getMatchStatus() != 1) {
                        continue;
                    }
                    if (strategyOrder.getFromExchangeId() == 1L) {
                        final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                        final String HUOBI_DM_API_URL = exchangeConfig.getApiUrl();
                        final HbdmswapRestApiV1 huobiApi = new HbdmswapRestApiV1(HUOBI_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey());
                        final String response = huobiApi.futureContractCancel(strategyOrder.getFromExchangeOrderId(), "", strategyOrder.getSymbol());
                        HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0c\u706b\u5e01\u64a4\u5355\u8fd4\u56de\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) response);
                    } else {
                        if (strategyOrder.getFromExchangeId() != 2L) {
                            continue;
                        }
                        final ContractDoubleExchangeConfig exchangeConfig = this.contractDoubleExchangeConfigService.get(strategyOrder.getFromExchangeId());
                        final String OKEX_DM_API_URL = exchangeConfig.getApiUrl();
                        final ContractDoubleMemberApi api = this.contractDoubleMemberApiService.get(strategyOrder.getApiId());
                        if (null == api) {
                            return;
                        }
                        final SwapTradeAPIServiceImpl okexApi = new SwapTradeAPIServiceImpl(OKEX_DM_API_URL, strategyOrder.getApiKey(), strategyOrder.getSecretKey(), api.getPassphrase());
                        final String response2 = okexApi.cancelOrderByOrderId(strategyOrder.getSymbol(), strategyOrder.getFromExchangeOrderId());
                        HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0cOKEX\u64a4\u5355\u8fd4\u56de\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) strategyOrder), (Object) response2);
                    }
                }
            } catch (Exception e) {
                HuoBiDoubleContractOffsetStrategyJobSchedule.log.info("\u6570\u636e\uff0c==>{}\uff0c\u5f02\u5e38\u4fe1\u606f==>{}", (Object) JSON.toJSONString((Object) order), (Object) e);
            }
        }
    }

    static {
        log = LoggerFactory.getLogger((Class) HuoBiDoubleContractOffsetStrategyJobSchedule.class);
    }
}

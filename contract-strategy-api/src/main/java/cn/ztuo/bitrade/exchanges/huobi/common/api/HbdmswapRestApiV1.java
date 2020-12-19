package cn.ztuo.bitrade.exchanges.huobi.common.api;

import org.apache.commons.lang.*;
import cn.ztuo.bitrade.exchanges.huobi.common.util.*;
import org.apache.http.*;

import java.io.*;
import java.util.*;

import cn.ztuo.bitrade.exchanges.huobi.common.request.*;

public class HbdmswapRestApiV1 implements IHbdmswapRestApi {
    private String secret_key;
    private String api_key;
    private String url_prex;
    public static final String HUOBI_FUTURE_TICKER = "/swap-ex/market/detail/merged";
    public static final String HUOBI_FUTURE_DEPTH = "/swap-ex/market/depth";
    public static final String HUOBI_FUTURE_KLINE = "/swap-ex/market/history/kline";
    public static final String HUOBI_FUTURE_TRADE = "/swap-ex/market/history/trade";
    public static final String HUOBI_FUTURE_CONTRACT_INFO = "/swap-api/v1/swap_contract_info";
    public static final String HUOBI_FUTURE_CONTRACT_INDEX = "/swap-api/v1/swap_index";
    public static final String HUOBI_FUTURE_CONTRACT_PRICE_LIMIT = "/swap-api/v1/swap_price_limit";
    public static final String HUOBI_FUTURE_CONTRACT_OPEN_INTEREST = "/swap-api/v1/swap_open_interest";
    public static final String HUOBI_FUTURE_CONTRACT_ORDER_DETAIL = "/swap-api/v1/swap_order_detail";
    public static final String HUOBI_FUTURE_CONTRACT_HISORDERS = "/swap-api/v1/swap_hisorders";
    public static final String HUOBI_FUTURE_CONTRACT_BATCHORDER = "/swap-api/v1/swap_batchorder";
    public static final String HUOBI_FUTURE_ACCOUNT_INFO = "/swap-api/v1/swap_account_info";
    public static final String HUOBI_FUTURE_POSITION_INFO = "/swap-api/v1/swap_position_info";
    public static final String HUOBI_FUTURE_ORDER = "/swap-api/v1/swap_order";
    public static final String HUOBI_FUTURE_ORDER_CANCEL = "/swap-api/v1/swap_cancel";
    public static final String HUOBI_FUTURE_ORDER_INFO = "/swap-api/v1/swap_order_info";
    public static final String HUOBI_FUTURE_ORDER_CANCEL_ALL = "/swap-api/v1/swap_cancelall";
    public static final String HUOBI_CONTRACE_CODE = "/swap-api/v1/swap_open_interest";
    public static final String HUOBI_CONTRACE_OPENORDERS = "/swap-api/v1/swap_openorders";

    public HbdmswapRestApiV1(final String url_prex, final String api_key, final String secret_key) {
        this.api_key = api_key;
        this.secret_key = secret_key;
        this.url_prex = url_prex;
    }

    public HbdmswapRestApiV1(final String url_prex) {
        this.url_prex = url_prex;
    }

    public String futureContractInfo(final String contractCode) {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String contractinfoRes = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-api/v1/swap_contract_info", (Map) params);
        return contractinfoRes;
    }

    public String futureContractIndex(final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String contractindexRes = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-api/v1/swap_index", (Map) params);
        return contractindexRes;
    }

    public String futurePriceLimit(final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String contractinfoRes = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-api/v1/swap_price_limit", (Map) params);
        return contractinfoRes;
    }

    public String futureOpenInterest(final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String contractinfoRes = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-api/v1/swap_open_interest", (Map) params);
        return contractinfoRes;
    }

    public String futureMarketDepth(final String contractCode, final String type) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(type)) {
            params.put("type", type);
        }
        final String contractinfoRes = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-ex/market/depth", (Map) params);
        return contractinfoRes;
    }

    public String futureMarketHistoryKline(final String contractCode, final String period, final String size) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(period)) {
            params.put("period", period);
        }
        if (!StringUtils.isEmpty(size)) {
            params.put("size", size);
        }
        final String res = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-ex/market/history/kline", (Map) params);
        return res;
    }

    public String futureMarketDetailMerged(final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String res = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-ex/market/detail/merged", (Map) params);
        return res;
    }

    public String futureMarketDetailTrade(final String contractCode, final String size) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(size)) {
            params.put("size", size);
        }
        final String res = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-ex/market/history/trade", (Map) params);
        return res;
    }

    public String futureMarketHistoryTrade(final String contractCode, final String size) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(size)) {
            params.put("size", size);
        }
        final String res = HbdmHttpClient.getInstance().doGet(this.url_prex + "/swap-ex/market/history/trade", (Map) params);
        return res;
    }

    public String futureContractAccountInfo(final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_account_info", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractPositionInfo(final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_position_info", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractOrder(final String contractCode, final String clientOrderId, final String price, final String volume, final String direction, final String offset, final String leverRate, final String orderPriceType) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(clientOrderId)) {
            params.put("client_order_id", clientOrderId);
        }
        if (!StringUtils.isEmpty(price)) {
            params.put("price", price);
        }
        if (!StringUtils.isEmpty(volume)) {
            params.put("volume", volume);
        }
        if (!StringUtils.isEmpty(direction)) {
            params.put("direction", direction);
        }
        if (!StringUtils.isEmpty(offset)) {
            params.put("offset", offset);
        }
        if (!StringUtils.isEmpty(leverRate)) {
            params.put("lever_rate", leverRate);
        }
        if (!StringUtils.isEmpty(orderPriceType)) {
            params.put("order_price_type", orderPriceType);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_order", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractBatchorder(final List<Order> orders) throws HttpException, IOException {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("orders_data", orders);
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_batchorder", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractCancel(final String orderId, final String clientOrderId, final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }
        if (!StringUtils.isEmpty(clientOrderId)) {
            params.put("client_order_id", clientOrderId);
        }
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_cancel", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractCancelall(final String contractCode) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_cancelall", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractOrderInfo(final String orderId, final String clientOrderId, final String contractCode, final String orderType) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }
        if (!StringUtils.isEmpty(clientOrderId)) {
            params.put("client_order_id", clientOrderId);
        }
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(orderType)) {
            params.put("order_type", orderType);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_order_info", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractOrderDetail(final String contractCode, final String orderId, final String pageIndex, final String pageSize, final String createdAt, final String orderType) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(orderId)) {
            params.put("order_id", orderId);
        }
        if (!StringUtils.isEmpty(pageIndex)) {
            params.put("page_index", pageIndex);
        }
        if (!StringUtils.isEmpty(pageSize)) {
            params.put("page_size", pageSize);
        }
        if (!StringUtils.isEmpty(createdAt)) {
            params.put("created_at", createdAt);
        }
        if (!StringUtils.isEmpty(orderType)) {
            params.put("order_type", orderType);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_order_detail", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractOpenorders(final String contractCode, final String pageIndex, final String pageSize) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(pageIndex)) {
            params.put("page_index", pageIndex);
        }
        if (!StringUtils.isEmpty(pageSize)) {
            params.put("page_size", pageSize);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_openorders", (Object) params, (Map) new HashMap());
        return res;
    }

    public String futureContractHisorders(final String contractCode, final String tradeType, final String type, final String status, final String createDate, final String pageIndex, final String pageSize) throws HttpException, IOException {
        final Map<String, String> params = new HashMap<String, String>();
        if (!StringUtils.isEmpty(contractCode)) {
            params.put("contract_code", contractCode);
        }
        if (!StringUtils.isEmpty(tradeType)) {
            params.put("trade_type", tradeType);
        }
        if (!StringUtils.isEmpty(type)) {
            params.put("type", type);
        }
        if (!StringUtils.isEmpty(createDate)) {
            params.put("create_date", createDate);
        }
        if (!StringUtils.isEmpty(status)) {
            params.put("status", status);
        }
        if (!StringUtils.isEmpty(pageIndex)) {
            params.put("page_index", pageIndex);
        }
        if (!StringUtils.isEmpty(pageSize)) {
            params.put("page_size", pageSize);
        }
        final String res = HbdmHttpClient.getInstance().call(this.api_key, this.secret_key, "POST", this.url_prex + "/swap-api/v1/swap_hisorders", (Object) params, (Map) new HashMap());
        return res;
    }
}

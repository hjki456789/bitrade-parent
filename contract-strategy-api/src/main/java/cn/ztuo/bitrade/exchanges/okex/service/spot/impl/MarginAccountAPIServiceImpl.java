package cn.ztuo.bitrade.exchanges.okex.service.spot.impl;

import cn.ztuo.bitrade.exchanges.okex.service.spot.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import com.alibaba.fastjson.*;

public class MarginAccountAPIServiceImpl implements MarginAccountAPIService {
    private final APIClient client;
    private final MarginAccountAPI api;

    public MarginAccountAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(MarginAccountAPI.class);
    }

    public List<Map<String, Object>> getAccounts() {
        return this.client.executeSync(this.api.getAccounts());
    }

    public Map<String, Object> getAccountsByProductId(final String instrument_id) {
        return this.client.executeSync(this.api.getAccountsByProductId(instrument_id));
    }

    public List<UserMarginBillDto> getLedger(final String instrument_id, final String type, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getLedger(instrument_id, type, before, after, limit));
    }

    public List<Map<String, Object>> getAvailability() {
        return this.client.executeSync(this.api.getAvailability());
    }

    public List<Map<String, Object>> getAvailabilityByProductId(final String instrument_id) {
        return this.client.executeSync(this.api.getAvailabilityByProductId(instrument_id));
    }

    public List<MarginBorrowOrderDto> getBorrowedAccounts(final String status, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getBorrowedAccounts(status, before, after, limit));
    }

    public List<MarginBorrowOrderDto> getBorrowedAccountsByProductId(final String instrument_id, final String before, final String after, final String limit, final String status) {
        return this.client.executeSync(this.api.getBorrowedAccountsByProductId(instrument_id, before, after, limit, status));
    }

    public BorrowResult borrow_1(final BorrowRequestDto order) {
        return this.client.executeSync(this.api.borrow_1(order));
    }

    public RepaymentResult repayment_1(final RepaymentRequestDto order) {
        return this.client.executeSync(this.api.repayment_1(order));
    }

    public JSONObject setLeverage(final String instrument_id, final MarginLeverage leverage) {
        return this.client.executeSync(this.api.setLeverage(instrument_id, leverage));
    }

    public JSONObject getLeverage(final String leverage) {
        return this.client.executeSync(this.api.getLeverage(leverage));
    }
}

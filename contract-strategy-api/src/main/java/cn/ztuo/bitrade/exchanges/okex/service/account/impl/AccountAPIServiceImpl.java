package cn.ztuo.bitrade.exchanges.okex.service.account.impl;

import cn.ztuo.bitrade.exchanges.okex.bean.account.result.Currency;
import cn.ztuo.bitrade.exchanges.okex.service.account.*;
import cn.ztuo.bitrade.exchanges.okex.client.*;
import cn.ztuo.bitrade.exchanges.okex.config.*;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.account.param.*;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.account.result.*;

import java.math.*;

public class AccountAPIServiceImpl implements AccountAPIService {
    private APIClient client;
    private AccountAPI api;

    public AccountAPIServiceImpl(final APIConfiguration config) {
        this.client = new APIClient(config);
        this.api = this.client.createService(AccountAPI.class);
    }

    @Override
    public List<Wallet> getWallet() {
        return this.client.executeSync(this.api.getWallet());
    }

    @Override
    public List<Wallet> getWallet(final String currency) {
        return this.client.executeSync(this.api.getWallet(currency));
    }

    @Override
    public JSONObject transfer(final Transfer transfer) {
        return this.client.executeSync(this.api.transfer(JSONObject.parseObject(JSON.toJSONString((Object) transfer))));
    }

    @Override
    public JSONObject withdraw(final Withdraw withdraw) {
        return this.client.executeSync(this.api.withdraw(JSONObject.parseObject(JSON.toJSONString((Object) withdraw))));
    }

    @Override
    public List<Currency> getCurrencies() {
        return this.client.executeSync(this.api.getCurrencies());
    }

    @Override
    public JSONArray getLedger(final String type, final String currency, final String before, final String after, final String limit) {
        return this.client.executeSync(this.api.getLedger(type, currency, before, after, limit));
    }

    @Override
    public JSONArray getDepositAddress(final String currency) {
        return this.client.executeSync(this.api.getDepositAddress(currency));
    }

    @Override
    public List<WithdrawFee> getWithdrawFee(final String currency) {
        return this.client.executeSync(this.api.getWithdrawFee(currency));
    }

    @Override
    public JSONArray getOnHold(final String currency) {
        return this.client.executeSync(this.api.getOnHold(currency));
    }

    @Override
    public JSONObject lock(final String currency, final BigDecimal amount) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("currency", (Object) currency);
        jsonObject.put("size", (Object) amount);
        return this.client.executeSync(this.api.lock(jsonObject));
    }

    @Override
    public JSONObject unlock(final String currency, final BigDecimal amount) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("currency", (Object) currency);
        jsonObject.put("size", (Object) amount);
        return this.client.executeSync(this.api.unlock(jsonObject));
    }

    @Override
    public JSONArray getDepositHistory() {
        return this.client.executeSync(this.api.getDepositHistory());
    }

    @Override
    public JSONArray getDepositHistory(final String currency) {
        return this.client.executeSync(this.api.getDepositHistory(currency));
    }

    @Override
    public JSONArray getWithdrawalHistory() {
        return this.client.executeSync(this.api.getWithdrawalHistory());
    }

    @Override
    public JSONArray getWithdrawalHistory(final String currency) {
        return this.client.executeSync(this.api.getWithdrawalHistory(currency));
    }

    @Override
    public JSONObject getSubAccount(final String sub_account) {
        return this.client.executeSync(this.api.getSubAccount(sub_account));
    }

    @Override
    public JSONObject getAllAccount(final String account_type, final String valuation_currency) {
        return this.client.executeSync(this.api.getAllAccount(account_type, valuation_currency));
    }
}

package cn.ztuo.bitrade.exchanges.okex.service.account;

import java.util.*;

import cn.ztuo.bitrade.exchanges.okex.bean.account.param.*;
import cn.ztuo.bitrade.exchanges.okex.bean.account.result.Currency;
import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.bean.account.result.*;

import java.math.*;

public interface AccountAPIService {
    List<Wallet> getWallet();

    List<Wallet> getWallet(final String p0);

    JSONObject transfer(final Transfer p0);

    JSONObject withdraw(final Withdraw p0);

    List<Currency> getCurrencies();

    JSONArray getLedger(final String p0, final String p1, final String p2, final String p3, final String p4);

    JSONArray getDepositAddress(final String p0);

    List<WithdrawFee> getWithdrawFee(final String p0);

    JSONArray getOnHold(final String p0);

    JSONObject lock(final String p0, final BigDecimal p1);

    JSONObject unlock(final String p0, final BigDecimal p1);

    JSONArray getDepositHistory();

    JSONArray getDepositHistory(final String p0);

    JSONArray getWithdrawalHistory();

    JSONArray getWithdrawalHistory(final String p0);

    JSONObject getSubAccount(final String p0);

    JSONObject getAllAccount(final String p0, final String p1);
}

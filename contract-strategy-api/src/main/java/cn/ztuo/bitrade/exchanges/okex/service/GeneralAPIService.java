package cn.ztuo.bitrade.exchanges.okex.service;

import cn.ztuo.bitrade.exchanges.okex.bean.futures.result.*;

public interface GeneralAPIService {
    ServerTime getServerTime();

    ExchangeRate getExchangeRate();
}

package cn.ztuo.bitrade.exchanges.huobi.common.api;

import org.apache.http.*;

import java.io.*;
import java.util.*;

import cn.ztuo.bitrade.exchanges.huobi.common.request.*;

public interface IHbdmswapRestApi {
    String futureContractInfo(final String p0) throws HttpException, IOException;

    String futureContractIndex(final String p0) throws HttpException, IOException;

    String futurePriceLimit(final String p0) throws HttpException, IOException;

    String futureOpenInterest(final String p0) throws HttpException, IOException;

    String futureMarketDepth(final String p0, final String p1) throws HttpException, IOException;

    String futureMarketHistoryKline(final String p0, final String p1, final String p2) throws HttpException, IOException;

    String futureMarketDetailMerged(final String p0) throws HttpException, IOException;

    String futureMarketDetailTrade(final String p0, final String p1) throws HttpException, IOException;

    String futureMarketHistoryTrade(final String p0, final String p1) throws HttpException, IOException;

    String futureContractAccountInfo(final String p0) throws HttpException, IOException;

    String futureContractPositionInfo(final String p0) throws HttpException, IOException;

    String futureContractOrder(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6, final String p7) throws HttpException, IOException;

    String futureContractBatchorder(final List<Order> p0) throws HttpException, IOException;

    String futureContractCancel(final String p0, final String p1, final String p2) throws HttpException, IOException;

    String futureContractCancelall(final String p0) throws HttpException, IOException;

    String futureContractOrderInfo(final String p0, final String p1, final String p2, final String p3) throws HttpException, IOException;

    String futureContractOrderDetail(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5) throws HttpException, IOException;

    String futureContractOpenorders(final String p0, final String p1, final String p2) throws HttpException, IOException;

    String futureContractHisorders(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6) throws HttpException, IOException;
}

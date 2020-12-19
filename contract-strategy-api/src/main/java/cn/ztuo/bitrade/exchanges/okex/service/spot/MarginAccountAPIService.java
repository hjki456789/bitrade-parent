package cn.ztuo.bitrade.exchanges.okex.service.spot;

import java.util.*;

import retrofit2.http.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.result.*;
import cn.ztuo.bitrade.exchanges.okex.bean.spot.param.*;
import com.alibaba.fastjson.*;

public interface MarginAccountAPIService {
    List<Map<String, Object>> getAccounts();

    Map<String, Object> getAccountsByProductId(@Path("instrument_id") final String p0);

    List<UserMarginBillDto> getLedger(@Path("instrument_id") final String p0, @Query("type") final String p1, @Query("before") final String p2, @Query("after") final String p3, @Query("limit") final String p4);

    List<Map<String, Object>> getAvailability();

    List<Map<String, Object>> getAvailabilityByProductId(@Path("instrument_id") final String p0);

    List<MarginBorrowOrderDto> getBorrowedAccounts(@Query("status") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3);

    List<MarginBorrowOrderDto> getBorrowedAccountsByProductId(@Path("instrument_id") final String p0, @Query("before") final String p1, @Query("after") final String p2, @Query("limit") final String p3, @Query("status") final String p4);

    BorrowResult borrow_1(final BorrowRequestDto p0);

    RepaymentResult repayment_1(final RepaymentRequestDto p0);

    JSONObject setLeverage(final String p0, final MarginLeverage p1);

    JSONObject getLeverage(final String p0);
}

package cn.ztuo.bitrade.dao;

import cn.ztuo.bitrade.entity.ExchangeOrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExchangeOrderDetailRepository extends MongoRepository<ExchangeOrderDetail, String> {
    List<ExchangeOrderDetail> findAllByOrderId(String orderId);

    @Query("{ $and: [ { \"orderId\": ?0 }, { \"time\": { $gte: ?1 } }, { \"time\": { $lt: ?2 } } ] }")
    List<ExchangeOrderDetail> findByOrderIdAndTimeGreaterThanEqualAndTimeLessThan(final String p0, final Long p1, final Long p2);
}

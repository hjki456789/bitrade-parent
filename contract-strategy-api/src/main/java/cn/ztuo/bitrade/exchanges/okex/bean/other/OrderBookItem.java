package cn.ztuo.bitrade.exchanges.okex.bean.other;

public interface OrderBookItem<T> {
    String getPrice();

    T getSize();
}

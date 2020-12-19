package cn.ztuo.bitrade.exchanges.okex.websocket;

public interface WebSocket {
    void connect();

    void close();

    void login(final String p0, final String p1, final String p2);

    void subscribe(final String... p0);

    void unSubscribe(final String... p0);

    void sendPing();

    boolean checkSum(final String p0);
}

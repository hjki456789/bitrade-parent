package cn.ztuo.bitrade.exchanges.okex.websocket;

public interface WebSocketListener {
    void onTextMessage(final WebSocket p0, final String p1) throws Exception;

    void onWebsocketOpen(final WebSocket p0);

    void handleCallbackError(final WebSocket p0, final Throwable p1);

    void onWebsocketClose(final WebSocket p0, final int p1);

    void onWebsocketPong(final WebSocket p0);
}

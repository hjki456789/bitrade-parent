package cn.ztuo.bitrade.exchanges.huobi.common.exception;

public class HttpRequestException extends RuntimeException {
    public HttpRequestException() {
    }

    public HttpRequestException(final String message) {
        super(message);
    }

    public HttpRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public HttpRequestException(final Throwable cause) {
        super(cause);
    }

    public HttpRequestException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

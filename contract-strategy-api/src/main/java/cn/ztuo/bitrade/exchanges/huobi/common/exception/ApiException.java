package cn.ztuo.bitrade.exchanges.huobi.common.exception;

public class ApiException extends RuntimeException {
    final String errCode;

    public ApiException(final String errCode, final String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public ApiException(final Exception e) {
        super(e);
        this.errCode = e.getClass().getName();
    }

    public String getErrCode() {
        return this.errCode;
    }
}

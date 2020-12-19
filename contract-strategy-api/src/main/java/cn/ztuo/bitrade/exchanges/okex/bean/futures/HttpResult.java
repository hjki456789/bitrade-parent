package cn.ztuo.bitrade.exchanges.okex.bean.futures;

public class HttpResult {
    private int code;
    private String message;
    private int errorCode;
    private String errorMessage;
    private String order_id;
    private Boolean result;

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(final String order_id) {
        this.order_id = order_id;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(final Boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "\t\tResponse Body:{code=" + this.code + ", message='" + this.message + '\'' + ", errorCode=" + this.errorCode + ", errorMessage='" + this.errorMessage + '\'' + ", order_id='" + this.order_id + '\'' + ", result=" + this.result + '}';
    }
}

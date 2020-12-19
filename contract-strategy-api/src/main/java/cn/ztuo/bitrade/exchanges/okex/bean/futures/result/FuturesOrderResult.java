package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class FuturesOrderResult {
    private String result;
    private String instrument_id;
    private String order_type;
    private String algo_id;
    private String error_code;
    private String error_message;

    public String getResult() {
        return this.result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(final String order_type) {
        this.order_type = order_type;
    }

    public String getAlgo_id() {
        return this.algo_id;
    }

    public void setAlgo_id(final String algo_id) {
        this.algo_id = algo_id;
    }

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(final String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(final String error_message) {
        this.error_message = error_message;
    }
}

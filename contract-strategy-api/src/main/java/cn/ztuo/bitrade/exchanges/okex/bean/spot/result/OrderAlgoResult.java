package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class OrderAlgoResult {
    private String result;
    private String algo_id;
    private String error_code;
    private String error_message;
    private String[] algo_ids;

    public String[] getAlgo_ids() {
        return this.algo_ids;
    }

    public void setAlgo_ids(final String[] algo_ids) {
        this.algo_ids = algo_ids;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(final String result) {
        this.result = result;
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

package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class RepaymentResult {
    private boolean result;
    private String repayment_id;
    private String client_oid;

    public String getClient_oid() {
        return this.client_oid;
    }

    public void setClient_oid(final String client_oid) {
        this.client_oid = client_oid;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(final boolean result) {
        this.result = result;
    }

    public String getRepayment_id() {
        return this.repayment_id;
    }

    public void setRepayment_id(final String repayment_id) {
        this.repayment_id = repayment_id;
    }
}

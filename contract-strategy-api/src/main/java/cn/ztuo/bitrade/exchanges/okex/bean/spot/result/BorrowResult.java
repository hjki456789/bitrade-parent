package cn.ztuo.bitrade.exchanges.okex.bean.spot.result;

public class BorrowResult {
    private boolean result;
    private String borrow_id;
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

    public String getBorrow_id() {
        return this.borrow_id;
    }

    public void setBorrow_id(final String borrow_id) {
        this.borrow_id = borrow_id;
    }
}

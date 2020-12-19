package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class Liquidation {
    private String instrument_id;
    private String price;
    private String size;
    private String type;
    private String loss;
    private String created_at;

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getLoss() {
        return this.loss;
    }

    public void setLoss(final String loss) {
        this.loss = loss;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(final String created_at) {
        this.created_at = created_at;
    }
}

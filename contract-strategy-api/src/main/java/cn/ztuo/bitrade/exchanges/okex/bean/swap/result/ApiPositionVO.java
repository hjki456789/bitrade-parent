package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

public class ApiPositionVO {
    private String liquidation_price;
    private String position;
    private String avail_position;
    private String avg_cost;
    private String settlement_price;
    private String instrument_id;
    private String leverage;
    private String realized_pnl;
    private String side;
    private String timestamp;

    public ApiPositionVO() {
    }

    public ApiPositionVO(final String liquidation_price, final String position, final String avail_position, final String avg_cost, final String settlement_price, final String instrument_id, final String leverage, final String realized_pnl, final String side, final String timestamp) {
        this.liquidation_price = liquidation_price;
        this.position = position;
        this.avail_position = avail_position;
        this.avg_cost = avg_cost;
        this.settlement_price = settlement_price;
        this.instrument_id = instrument_id;
        this.leverage = leverage;
        this.realized_pnl = realized_pnl;
        this.side = side;
        this.timestamp = timestamp;
    }

    public String getLiquidation_price() {
        return this.liquidation_price;
    }

    public void setLiquidation_price(final String liquidation_price) {
        this.liquidation_price = liquidation_price;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getAvail_position() {
        return this.avail_position;
    }

    public void setAvail_position(final String avail_position) {
        this.avail_position = avail_position;
    }

    public String getAvg_cost() {
        return this.avg_cost;
    }

    public void setAvg_cost(final String avg_cost) {
        this.avg_cost = avg_cost;
    }

    public String getSettlement_price() {
        return this.settlement_price;
    }

    public void setSettlement_price(final String settlement_price) {
        this.settlement_price = settlement_price;
    }

    public String getInstrument_id() {
        return this.instrument_id;
    }

    public void setInstrument_id(final String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getLeverage() {
        return this.leverage;
    }

    public void setLeverage(final String leverage) {
        this.leverage = leverage;
    }

    public String getRealized_pnl() {
        return this.realized_pnl;
    }

    public void setRealized_pnl(final String realized_pnl) {
        this.realized_pnl = realized_pnl;
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
}

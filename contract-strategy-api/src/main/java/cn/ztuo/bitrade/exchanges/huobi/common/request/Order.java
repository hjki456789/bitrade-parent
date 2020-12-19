package cn.ztuo.bitrade.exchanges.huobi.common.request;

import com.alibaba.fastjson.annotation.*;

public class Order {
    @JSONField(name = "client_order_id")
    public String clientOrderId;
    @JSONField(name = "contract_code")
    public String contractCode;
    public String price;
    public String volume;
    public String direction;
    public String offset;
    @JSONField(name = "lever_rate")
    public String leverRate;
    @JSONField(name = "order_price_type")
    public String orderPriceType;

    public Order(final String contractCode, final String clientOrderId, final String price, final String volume, final String direction, final String offset, final String leverRate, final String orderPriceType) {
        this.contractCode = contractCode;
        this.clientOrderId = clientOrderId;
        this.price = price;
        this.volume = volume;
        this.direction = direction;
        this.offset = offset;
        this.leverRate = leverRate;
        this.orderPriceType = orderPriceType;
    }

    public Order() {
    }

    public String getContractCode() {
        return this.contractCode;
    }

    public void setContractCode(final String contractCode) {
        this.contractCode = contractCode;
    }

    public String getClientOrderId() {
        return this.clientOrderId;
    }

    public void setClientOrderId(final String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(final String volume) {
        this.volume = volume;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(final String direction) {
        this.direction = direction;
    }

    public String getOffset() {
        return this.offset;
    }

    public void setOffset(final String offset) {
        this.offset = offset;
    }

    public String getLeverRate() {
        return this.leverRate;
    }

    public void setLeverRate(final String leverRate) {
        this.leverRate = leverRate;
    }

    public String getOrderPriceType() {
        return this.orderPriceType;
    }

    public void setOrderPriceType(final String orderPriceType) {
        this.orderPriceType = orderPriceType;
    }
}

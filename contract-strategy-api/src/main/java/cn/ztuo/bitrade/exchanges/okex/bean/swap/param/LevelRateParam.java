package cn.ztuo.bitrade.exchanges.okex.bean.swap.param;

import com.alibaba.fastjson.*;

public class LevelRateParam extends JSONObject {
    private String side;
    private String leverage;

    public LevelRateParam(final String side, final String levelRate) {
        this.side = side;
        this.leverage = levelRate;
    }

    public LevelRateParam() {
    }

    public String getSide() {
        return this.side;
    }

    public void setSide(final String side) {
        this.side = side;
    }

    public String getLeverage() {
        return this.leverage;
    }

    public void setLeverage(final String leverage) {
        this.leverage = leverage;
    }
}

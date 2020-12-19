package cn.ztuo.bitrade.exchanges.okex.enums;

public enum FuturesDirectionEnum {
    LONG("long"),
    SHORT("short");

    private String direction;

    private FuturesDirectionEnum(final String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }
}

package cn.ztuo.bitrade.exchanges.okex.enums;

public enum FuturesCurrenciesEnum {
    BTC(0),
    LTC(1),
    ETH(2),
    ETC(4),
    XRP(15),
    EOS(20),
    BCH(301),
    BSV(302);

    private int symbol;

    private FuturesCurrenciesEnum(final int symbol) {
        this.symbol = symbol;
    }

    public int getSymbol() {
        return this.symbol;
    }
}

package cn.ztuo.bitrade.exchanges.okex.enums;

public enum CharsetEnum {
    UTF_8("UTF-8"),
    ISO_8859_1("ISO-8859-1");

    private String charset;

    private CharsetEnum(final String charset) {
        this.charset = charset;
    }

    public String charset() {
        return this.charset;
    }
}
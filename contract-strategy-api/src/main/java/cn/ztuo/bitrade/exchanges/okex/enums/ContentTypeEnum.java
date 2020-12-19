package cn.ztuo.bitrade.exchanges.okex.enums;

public enum ContentTypeEnum {
    APPLICATION_JSON("application/json"),
    APPLICATION_JSON_UTF8("application/json; charset=UTF-8"),
    APPLICATION_FORM("application/x-www-form-urlencoded; charset=UTF-8");

    private String contentType;

    private ContentTypeEnum(final String contentType) {
        this.contentType = contentType;
    }

    public String contentType() {
        return this.contentType;
    }
}

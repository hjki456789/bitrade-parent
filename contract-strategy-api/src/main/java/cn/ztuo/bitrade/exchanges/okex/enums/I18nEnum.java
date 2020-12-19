package cn.ztuo.bitrade.exchanges.okex.enums;

public enum I18nEnum {
    ENGLISH("en_US"),
    SIMPLIFIED_CHINESE("zh_CN"),
    TRADITIONAL_CHINESE("zh_HK");

    private String i18n;

    private I18nEnum(final String i18n) {
        this.i18n = i18n;
    }

    public String i18n() {
        return this.i18n;
    }
}

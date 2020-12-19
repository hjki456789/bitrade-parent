package cn.ztuo.bitrade.exchanges.okex.config;

import cn.ztuo.bitrade.exchanges.okex.enums.*;

public class APIConfiguration {
    private String apiKey;
    private String secretKey;
    private String passphrase;
    private String endpoint;
    private long connectTimeout;
    private long readTimeout;
    private long writeTimeout;
    private boolean retryOnConnectionFailure;
    private boolean print;
    private I18nEnum i18n;

    public APIConfiguration() {
        this(null);
    }

    public APIConfiguration(final String endpoint) {
        this.apiKey = null;
        this.secretKey = null;
        this.passphrase = null;
        this.endpoint = endpoint;
        this.connectTimeout = 30000L;
        this.readTimeout = 30000L;
        this.writeTimeout = 30000L;
        this.retryOnConnectionFailure = true;
        this.print = false;
        this.i18n = I18nEnum.ENGLISH;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPassphrase() {
        return this.passphrase;
    }

    public void setPassphrase(final String passphrase) {
        this.passphrase = passphrase;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public long getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(final long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public long getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(final long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public long getWriteTimeout() {
        return this.writeTimeout;
    }

    public void setWriteTimeout(final long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public boolean isRetryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    public void setRetryOnConnectionFailure(final boolean retryOnConnectionFailure) {
        this.retryOnConnectionFailure = retryOnConnectionFailure;
    }

    public boolean isPrint() {
        return this.print;
    }

    public void setPrint(final boolean print) {
        this.print = print;
    }

    public I18nEnum getI18n() {
        return this.i18n;
    }

    public void setI18n(final I18nEnum i18n) {
        this.i18n = i18n;
    }
}

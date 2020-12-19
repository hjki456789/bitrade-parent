package cn.ztuo.bitrade.exchanges.okex.client;

import cn.ztuo.bitrade.exchanges.okex.config.*;

public class APICredentials {
    private String apiKey;
    private String secretKey;
    private String passphrase;

    public APICredentials(final APIConfiguration config) {
        this.apiKey = config.getApiKey();
        this.secretKey = config.getSecretKey();
        this.passphrase = config.getPassphrase();
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
}

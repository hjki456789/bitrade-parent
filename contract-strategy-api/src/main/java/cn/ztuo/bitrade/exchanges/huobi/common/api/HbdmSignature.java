package cn.ztuo.bitrade.exchanges.huobi.common.api;

public class HbdmSignature {
    private String accessKey;
    private String secretKey;

    public HbdmSignature() {
    }

    public HbdmSignature(final String accessKey, final String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public void setAccessKey(final String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }
}

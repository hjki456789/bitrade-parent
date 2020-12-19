package cn.ztuo.bitrade.entity;

public class GaMemberAccount
{
    private String id;
    private String memberId;
    private Integer type;
    private String address;
    private String qcCodeUrl;
    private String privateKey;
    private String password;
    private String secretWord;
    private Double balance;
    private Double chainBalance;
    private Long version;
    
    public GaMemberAccount() {
        this.type = 1;
        this.balance = 0.0;
        this.chainBalance = 0.0;
        this.version = 0L;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getMemberId() {
        return this.memberId;
    }
    
    public void setMemberId(final String memberId) {
        this.memberId = memberId;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public String getQcCodeUrl() {
        return this.qcCodeUrl;
    }
    
    public void setQcCodeUrl(final String qcCodeUrl) {
        this.qcCodeUrl = qcCodeUrl;
    }
    
    public String getPrivateKey() {
        return this.privateKey;
    }
    
    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getSecretWord() {
        return this.secretWord;
    }
    
    public void setSecretWord(final String secretWord) {
        this.secretWord = secretWord;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(final Integer type) {
        this.type = type;
    }
    
    public Double getBalance() {
        return this.balance;
    }
    
    public void setBalance(final Double balance) {
        this.balance = balance;
    }
    
    public Double getChainBalance() {
        return this.chainBalance;
    }
    
    public void setChainBalance(final Double chainBalance) {
        this.chainBalance = chainBalance;
    }
    
    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(final Long version) {
        this.version = version;
    }
    
    @Override
    public String toString() {
        return "GaMemberAccount [id=" + this.id + ", memberId=" + this.memberId + ", type=" + this.type + ", address=" + this.address + ", qcCodeUrl=" + this.qcCodeUrl + ", privateKey=" + this.privateKey + ", password=" + this.password + ", secretWord=" + this.secretWord + ", balance=" + this.balance + ", chainBalance=" + this.chainBalance + ", version=" + this.version + "]";
    }
}

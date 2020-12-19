package cn.ztuo.bitrade.entity;

import java.io.*;

public class EthTranscation implements Serializable
{
    private static final long serialVersionUID = 2120869894112984147L;
    private String txHash;
    private String toAddress;
    private Double ethNum;
    
    public String getTxHash() {
        return this.txHash;
    }
    
    public void setTxHash(final String txHash) {
        this.txHash = txHash;
    }
    
    public String getToAddress() {
        return this.toAddress;
    }
    
    public void setToAddress(final String toAddress) {
        this.toAddress = toAddress;
    }
    
    public Double getEthNum() {
        return this.ethNum;
    }
    
    public void setEthNum(final Double ethNum) {
        this.ethNum = ethNum;
    }
}

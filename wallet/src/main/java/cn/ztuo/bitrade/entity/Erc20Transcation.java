package cn.ztuo.bitrade.entity;

import java.io.*;

public class Erc20Transcation implements Serializable
{
    private static final long serialVersionUID = 2120869894112984147L;
    private String blockNumber;
    private String timestamp;
    private String hash;
    private String nonce;
    private String blockHash;
    private String from;
    private String contractAddress;
    private String to;
    private String value;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private String transactionIndex;
    private String gas;
    private String gasPrice;
    private String gasUsed;
    private String cumulativeGasUsed;
    private String input;
    private String confirmations;
    
    public String getBlockNumber() {
        return this.blockNumber;
    }
    
    public void setBlockNumber(final String blockNumber) {
        this.blockNumber = blockNumber;
    }
    
    public String getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(final String hash) {
        this.hash = hash;
    }
    
    public String getNonce() {
        return this.nonce;
    }
    
    public void setNonce(final String nonce) {
        this.nonce = nonce;
    }
    
    public String getBlockHash() {
        return this.blockHash;
    }
    
    public void setBlockHash(final String blockHash) {
        this.blockHash = blockHash;
    }
    
    public String getFrom() {
        return this.from;
    }
    
    public void setFrom(final String from) {
        this.from = from;
    }
    
    public String getContractAddress() {
        return this.contractAddress;
    }
    
    public void setContractAddress(final String contractAddress) {
        this.contractAddress = contractAddress;
    }
    
    public String getTo() {
        return this.to;
    }
    
    public void setTo(final String to) {
        this.to = to;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public String getTokenName() {
        return this.tokenName;
    }
    
    public void setTokenName(final String tokenName) {
        this.tokenName = tokenName;
    }
    
    public String getTokenSymbol() {
        return this.tokenSymbol;
    }
    
    public void setTokenSymbol(final String tokenSymbol) {
        this.tokenSymbol = tokenSymbol;
    }
    
    public String getTokenDecimal() {
        return this.tokenDecimal;
    }
    
    public void setTokenDecimal(final String tokenDecimal) {
        this.tokenDecimal = tokenDecimal;
    }
    
    public String getTransactionIndex() {
        return this.transactionIndex;
    }
    
    public void setTransactionIndex(final String transactionIndex) {
        this.transactionIndex = transactionIndex;
    }
    
    public String getGas() {
        return this.gas;
    }
    
    public void setGas(final String gas) {
        this.gas = gas;
    }
    
    public String getGasPrice() {
        return this.gasPrice;
    }
    
    public void setGasPrice(final String gasPrice) {
        this.gasPrice = gasPrice;
    }
    
    public String getGasUsed() {
        return this.gasUsed;
    }
    
    public void setGasUsed(final String gasUsed) {
        this.gasUsed = gasUsed;
    }
    
    public String getCumulativeGasUsed() {
        return this.cumulativeGasUsed;
    }
    
    public void setCumulativeGasUsed(final String cumulativeGasUsed) {
        this.cumulativeGasUsed = cumulativeGasUsed;
    }
    
    public String getInput() {
        return this.input;
    }
    
    public void setInput(final String input) {
        this.input = input;
    }
    
    public String getConfirmations() {
        return this.confirmations;
    }
    
    public void setConfirmations(final String confirmations) {
        this.confirmations = confirmations;
    }
}

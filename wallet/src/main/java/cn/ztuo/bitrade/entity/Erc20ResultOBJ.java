package cn.ztuo.bitrade.entity;

import java.util.*;

public class Erc20ResultOBJ
{
    private Integer status;
    private String message;
    private List<Erc20Transcation> result;
    
    public Erc20ResultOBJ() {
        this.result = new ArrayList<Erc20Transcation>();
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public List<Erc20Transcation> getResult() {
        return this.result;
    }
    
    public void setResult(final List<Erc20Transcation> result) {
        this.result = result;
    }
}

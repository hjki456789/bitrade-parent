package cn.ztuo.bitrade.enums;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum LotteryAddType implements BaseEnum
{
    TRANSADD("\u6bcf\u6ee110\u6b21\u4ea4\u6613\u589e\u52a0"), 
    WEBADD("\u540e\u53f0\u624b\u52a8\u589e\u52a0"), 
    BUYADD("\u8d2d\u4e70\u589e\u52a0");
    
    private String cnName;
    
    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
    
    public LotteryAddType[] getAll() {
        return values();
    }
    
    public static LotteryAddType getByValue(final int value) {
        for (final LotteryAddType lotteryAddType : values()) {
            if (lotteryAddType.getOrdinal() == value) {
                return lotteryAddType;
            }
        }
        return null;
    }
    
    @ConstructorProperties({ "cnName" })
    private LotteryAddType(final String cnName) {
        this.cnName = cnName;
    }
    
    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

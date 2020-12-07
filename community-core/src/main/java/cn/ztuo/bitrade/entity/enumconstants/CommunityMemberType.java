package cn.ztuo.bitrade.entity.enumconstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum CommunityMemberType implements BaseEnum
{
    NORMAL("普通用户"),
    TRADER("交易员");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({ "cnName" })
    private CommunityMemberType(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

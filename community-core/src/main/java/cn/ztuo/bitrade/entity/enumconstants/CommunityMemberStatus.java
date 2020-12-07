package cn.ztuo.bitrade.entity.enumconstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum CommunityMemberStatus implements BaseEnum
{
    CLOSE_LEAD("关闭带单功能"),
    OPEN_LEAD("开启带单功能");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({ "cnName" })
    private CommunityMemberStatus(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

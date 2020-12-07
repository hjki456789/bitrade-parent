package cn.ztuo.bitrade.entity.enumconstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum CommunityMemberFollowInfoStatus implements BaseEnum
{
    FOLLOWING("跟单中"),
    STOP_FOLLOW("关闭跟单"),
    CANCEL_FOLLOW("取消跟随");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({ "cnName" })
    private CommunityMemberFollowInfoStatus(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

package cn.ztuo.bitrade.entity.enumconstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum CommunityMemberFollowInfoRecordType implements BaseEnum
{
    NEW("新建"),
    AGAIN_FOLLOW("再次跟随"),
    UPDATE_INFO("修改内容"),
    FOLLOW_OPEN("跟单开启"),
    FOLLOW_STOP("跟单关闭"),
    CANCEL("取消跟随");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({ "cnName" })
    private CommunityMemberFollowInfoRecordType(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

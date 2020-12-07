package cn.ztuo.bitrade.entity.enumconstants;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;
import java.beans.*;

public enum CommunityMemberSex implements BaseEnum
{
    MALE("男"),
    FEMALE("女"),
    UNKONWN("未知");

    private String cnName;

    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({ "cnName" })
    private CommunityMemberSex(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

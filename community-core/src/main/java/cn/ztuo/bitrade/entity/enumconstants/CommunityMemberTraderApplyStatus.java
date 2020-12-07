package cn.ztuo.bitrade.entity.enumconstants;

import cn.ztuo.bitrade.core.*;
import java.beans.*;

public enum CommunityMemberTraderApplyStatus implements BaseEnum
{
    IN_APPLYING("申请中"),
    AGREE_APPLY("申请通过"),
    REFUSE_APPLY("拒绝不通过");

    private String cnName;

    public int getOrdinal() {
        return this.ordinal();
    }

    @ConstructorProperties({ "cnName" })
    private CommunityMemberTraderApplyStatus(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}

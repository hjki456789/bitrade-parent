package cn.ztuo.bitrade.exchanges.okex.bean.swap.result;

import java.util.*;

public class ApiAccountsVO {
    private List<ApiAccountVO> info;

    public List<ApiAccountVO> getInfo() {
        return this.info;
    }

    public void setInfo(final List<ApiAccountVO> info) {
        this.info = info;
    }

    public ApiAccountsVO() {
    }

    public ApiAccountsVO(final List<ApiAccountVO> info) {
        this.info = info;
    }
}

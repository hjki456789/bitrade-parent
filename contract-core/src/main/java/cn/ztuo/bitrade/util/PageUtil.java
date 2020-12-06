package cn.ztuo.bitrade.util;

import org.springframework.data.domain.*;
import cn.ztuo.bitrade.pagenation.*;

public class PageUtil {
    public static EntityPage page(final Page page, final int pageNo, final int pageSize) {
        final EntityPage entityPage = new EntityPage();
        entityPage.setCount(page.getTotalElements());
        entityPage.setTotalPage(page.getTotalPages());
        entityPage.setList(page.getContent());
        entityPage.setCurrentPage(pageNo);
        entityPage.setPageSize(pageSize);
        return entityPage;
    }
}

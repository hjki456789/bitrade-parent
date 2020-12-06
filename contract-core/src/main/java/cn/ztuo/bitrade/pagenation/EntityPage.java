package cn.ztuo.bitrade.pagenation;

import java.io.*;
import java.util.*;

public class EntityPage<T extends Serializable> implements Serializable {
    private int currentPage;
    private int pageSize;
    private long totalPage;
    private long count;
    private List<T> list;

    public EntityPage() {
        this.currentPage = 1;
        this.pageSize = 20;
        this.totalPage = 0L;
        this.count = 0L;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotalPage() {
        return this.totalPage;
    }

    public long getCount() {
        return this.count;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(final long totalPage) {
        this.totalPage = totalPage;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EntityPage)) {
            return false;
        }
        final EntityPage<?> other = (EntityPage<?>) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getCurrentPage() != other.getCurrentPage()) {
            return false;
        }
        if (this.getPageSize() != other.getPageSize()) {
            return false;
        }
        if (this.getTotalPage() != other.getTotalPage()) {
            return false;
        }
        if (this.getCount() != other.getCount()) {
            return false;
        }
        final Object this$list = this.getList();
        final Object other$list = other.getList();
        if (this$list == null) {
            if (other$list == null) {
                return true;
            }
        } else if (this$list.equals(other$list)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EntityPage;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getCurrentPage();
        result = result * 59 + this.getPageSize();
        final long $totalPage = this.getTotalPage();
        result = result * 59 + (int) ($totalPage >>> 32 ^ $totalPage);
        final long $count = this.getCount();
        result = result * 59 + (int) ($count >>> 32 ^ $count);
        final Object $list = this.getList();
        result = result * 59 + (($list == null) ? 43 : $list.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "EntityPage(currentPage=" + this.getCurrentPage() + ", pageSize=" + this.getPageSize() + ", totalPage=" + this.getTotalPage() + ", count=" + this.getCount() + ", list=" + this.getList() + ")";
    }
}

package cn.ztuo.bitrade.pagenation;

import java.io.*;
import java.util.*;
import org.springframework.data.domain.*;

public class CommunityPage<T extends Serializable> implements Serializable
{
    private long totalElements;
    private int totalPages;
    private int size;
    private List<T> content;
    private boolean last;
    private boolean first;
    
    public CommunityPage(final Page<?> page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.size = page.getSize();
        this.last = page.isLast();
        this.first = page.isFirst();
    }
    
    public long getTotalElements() {
        return this.totalElements;
    }
    
    public int getTotalPages() {
        return this.totalPages;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public List<T> getContent() {
        return this.content;
    }
    
    public boolean isLast() {
        return this.last;
    }
    
    public boolean isFirst() {
        return this.first;
    }
    
    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }
    
    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }
    
    public void setSize(final int size) {
        this.size = size;
    }
    
    public void setContent(final List<T> content) {
        this.content = content;
    }
    
    public void setLast(final boolean last) {
        this.last = last;
    }
    
    public void setFirst(final boolean first) {
        this.first = first;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommunityPage)) {
            return false;
        }
        final CommunityPage<?> other = (CommunityPage<?>)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getTotalElements() != other.getTotalElements()) {
            return false;
        }
        if (this.getTotalPages() != other.getTotalPages()) {
            return false;
        }
        if (this.getSize() != other.getSize()) {
            return false;
        }
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null) {
            if (other$content == null) {
                return this.isLast() == other.isLast() && this.isFirst() == other.isFirst();
            }
        }
        else if (this$content.equals(other$content)) {
            return this.isLast() == other.isLast() && this.isFirst() == other.isFirst();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommunityPage;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $totalElements = this.getTotalElements();
        result = result * 59 + (int)($totalElements >>> 32 ^ $totalElements);
        result = result * 59 + this.getTotalPages();
        result = result * 59 + this.getSize();
        final Object $content = this.getContent();
        result = result * 59 + (($content == null) ? 43 : $content.hashCode());
        result = result * 59 + (this.isLast() ? 79 : 97);
        result = result * 59 + (this.isFirst() ? 79 : 97);
        return result;
    }
    
    @Override
    public String toString() {
        return "CommunityPage(totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ", size=" + this.getSize() + ", content=" + this.getContent() + ", last=" + this.isLast() + ", first=" + this.isFirst() + ")";
    }
}

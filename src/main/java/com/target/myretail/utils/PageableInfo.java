package com.target.myretail.utils;

import java.io.Serializable;

public class PageableInfo implements Serializable {
    private static final long serialVersionUID = 1088088485298669810L;
    private int page;
    private int maxSize;
    private int count;

    public PageableInfo() {
    }

    public int getPage() {
        return this.page;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getCount() {
        return this.count;
    }

    public PageableInfo setPage(int page) {
        this.page = page;
        return this;
    }

    public PageableInfo setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public PageableInfo setCount(int count) {
        this.count = count;
        return this;
    }
}
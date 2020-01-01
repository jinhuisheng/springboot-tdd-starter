package com.test.common.ddd;

import java.io.Serializable;

public class PageParams implements Serializable {

    private Integer page;

    private Integer pageSize;

    public Integer getPage() {
        if(page == null || page < 1){
            return 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        if(pageSize == null || pageSize < 1){
            return 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

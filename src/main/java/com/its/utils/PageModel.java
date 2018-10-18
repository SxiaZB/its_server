package com.its.utils;

import java.util.List;

/**
 * 分页查询参数及数据分封装
 * @author zhangzhi
 */
public class PageModel<T> {
    private int pageSize;//每页显示数据条数
    private int nowPage;//当前页数
    private T model;//参数
    private int offset;//偏移量
    private int totalCount;//总条数
    private int totalPage;//总页数
    private List<?> list;//结果集

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize==0){
            pageSize=10;
        }
        this.pageSize = pageSize;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    /**
     * 根据当前页和每页条数计算偏移量
     */
    public void setOffset() {
        this.pageSize = pageSize <= 0 ? 20 : pageSize;
        this.offset = (nowPage <= 0 ? 1 : nowPage - 1) * pageSize;
    }

    public int getOffset() {
        this.setOffset();
        return offset;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.setTotalPage();
    }

    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 根据总条数和每页条数计算页数
     */
    public void setTotalPage() {
        int tp = 0;
        if(pageSize > 0 )
            tp = this.totalCount % pageSize == 0 ? this.totalCount / pageSize : this.totalCount / pageSize + 1;
        this.totalPage = tp;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}

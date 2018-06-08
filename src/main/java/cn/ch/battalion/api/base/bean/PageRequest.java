package cn.ch.battalion.api.base.bean;


import cn.ch.battalion.api.base.Global;

/**
 * 接收分页数据
 */
public class PageRequest<T> {

    private int currentPage = 1;
    private int pageSize = Global.PAGE_SIZE;
    private int start = 0;
    private T data;

    public PageRequest() {
        this.pageSize = Global.PAGE_SIZE;
        this.currentPage = 1;
        this.setStartValue();
    }

    public PageRequest(int currentPage, int pageSize, T data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.setStartValue();
        this.data = data;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        setStartValue();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        setStartValue();
    }

    private void setStartValue() {
        int start = (this.currentPage - 1) * this.pageSize;
        this.setStart(start);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

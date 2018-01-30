package com.kee.base.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kee on 2017/6/21.
 */
public class PageDataBean<T> {
    private int currentPage;
    private int totalPage;
    private List<T> datas;


    public void addItem() {
        if (datas == null) {
            datas = new ArrayList<>();
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public PageDataBean setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public PageDataBean setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public List<T> getDatas() {
        return datas;
    }

    public PageDataBean setDatas(List<T> datas) {
        this.datas = datas;
        return this;
    }
}

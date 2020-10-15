package com.lizhao.my.shop.commons.dto;

import com.lizhao.my.shop.commons.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T extends BaseEntity> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getDraw() {
        return draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}

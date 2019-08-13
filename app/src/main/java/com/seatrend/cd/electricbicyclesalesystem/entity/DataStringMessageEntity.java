package com.seatrend.cd.electricbicyclesalesystem.entity;

/**
 * Created by seatrend on 2018/11/28.
 */

public class DataStringMessageEntity extends BaseEntity {


    /**
     * data : http://11.121.35.209:8088/application/printApplication?cjxxbh=cjbh8338207901616640
     * total : 0
     */

    private String data;
    private int total;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

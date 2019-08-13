package com.seatrend.cd.electricbicyclesalesystem.entity;

/**
 * Created by seatrend on 2019/1/7.
 */

public class InsuranceQrMessageEntity extends BaseEntity {


    /**
     * data : {"车架号":"117321800000001","保险开始日期":"2018-12-22","身份证明号码":"511000000000000000","保险公司名称":"人保财险","联系电话":"18600000000","保费":"0","牌照号":"川ACA4326","保单号":"PZDB201851010000000097","保险产品代码":"ZDB5100020","校验位":"25F2F12FA26779648E5BCE7B4EBD9EA5","所有人":"张三","保险结束日期":"2019-12-21"}
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

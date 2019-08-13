package com.seatrend.cd.electricbicyclesalesystem.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seatrend on 2019/1/10.
 */

public class VertiyEntity extends BaseEntity {


    /**
     * data : {"code":0,"description":"人口信息验证","message":"未获取到户籍登记信息"}
     * total : 0
     */

    private DataBean data;
    private int total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        /**
         * code : 0
         * description : 人口信息验证
         * message : 未获取到户籍登记信息
         */

        @SerializedName("code")
        private int codeX;
        private String description;
        @SerializedName("message")
        private String messageX;

        public int getCodeX() {
            return codeX;
        }

        public void setCodeX(int codeX) {
            this.codeX = codeX;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMessageX() {
            return messageX;
        }

        public void setMessageX(String messageX) {
            this.messageX = messageX;
        }
    }
}

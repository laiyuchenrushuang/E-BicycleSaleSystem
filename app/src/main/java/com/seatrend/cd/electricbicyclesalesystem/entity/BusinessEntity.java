package com.seatrend.cd.electricbicyclesalesystem.entity;

/**
 * Created by seatrend on 2019/1/8.
 */

public class BusinessEntity extends BaseEntity{


    /**
     * data : {"fail":0,"total":0,"pass":0}
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
         * fail : 0
         * total : 0
         * pass : 0
         */

        private int fail;
        private int total;
        private int pass;

        public int getFail() {
            return fail;
        }

        public void setFail(int fail) {
            this.fail = fail;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPass() {
            return pass;
        }

        public void setPass(int pass) {
            this.pass = pass;
        }
    }
}

package com.seatrend.cd.electricbicyclesalesystem.entity;

/**
 * Created by seatrend on 2019/1/7.
 */

public class InsuranceDetailsEntity extends BaseEntity {


    /**
     * data : {"ddjh":"XX001","bdh":"PZDB201851010000000097","zcbm":"174821311260018","syr":null,"pzh":"A000022"}
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
         * ddjh : XX001
         * bdh : PZDB201851010000000097
         * zcbm : 174821311260018
         * syr : null
         * pzh : A000022
         */

        private String ddjh;
        private String bdh;
        private String zcbm;
        private String syr;
        private String pzh;

        public String getDdjh() {
            return ddjh;
        }

        public void setDdjh(String ddjh) {
            this.ddjh = ddjh;
        }

        public String getBdh() {
            return bdh;
        }

        public void setBdh(String bdh) {
            this.bdh = bdh;
        }

        public String getZcbm() {
            return zcbm;
        }

        public void setZcbm(String zcbm) {
            this.zcbm = zcbm;
        }

        public String getSyr() {
            return syr;
        }

        public void setSyr(String syr) {
            this.syr = syr;
        }

        public String getPzh() {
            return pzh;
        }

        public void setPzh(String pzh) {
            this.pzh = pzh;
        }
    }
}

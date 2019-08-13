package com.seatrend.cd.electricbicyclesalesystem.entity;

/**
 * Created by seatrend on 2018/12/28.
 */

public class LoginEntity extends BaseEntity {


    /**
     * data : {"yhdh":"071316","xm":"孙波","glbm":"510104000400","qxms":"2","ipstart":"1.1.1.1","ipend":"255.255.255.0","sfzmhm":"510105197711123015","pwd":"73504470639906268905297996627116","zt":null,"ssxt":"P","ssbm":null,"klyzms":"1","yxqz":"2019-01-08 09:17:45","yhlx":"0","xsddm":null,"xsdmc":null}
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
         * yhdh : 071316
         * xm : 孙波
         * glbm : 510104000400
         * qxms : 2
         * ipstart : 1.1.1.1
         * ipend : 255.255.255.0
         * sfzmhm : 510105197711123015
         * pwd : 73504470639906268905297996627116
         * zt : null
         * ssxt : P
         * ssbm : null
         * klyzms : 1
         * yxqz : 2019-01-08 09:17:45
         * yhlx : 0
         * xsddm : null
         * xsdmc : null
         */

        private String yhdh;
        private String xm;
        private String glbm;
        private String qxms;
        private String ipstart;
        private String ipend;
        private String sfzmhm;
        private String pwd;
        private String zt;
        private String ssxt;
        private String ssbm;
        private String klyzms;
        private String yxqz;
        private String yhlx;
        private String xsddm;
        private String xsdmc;

        public String getYhdh() {
            return yhdh;
        }

        public void setYhdh(String yhdh) {
            this.yhdh = yhdh;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getGlbm() {
            return glbm;
        }

        public void setGlbm(String glbm) {
            this.glbm = glbm;
        }

        public String getQxms() {
            return qxms;
        }

        public void setQxms(String qxms) {
            this.qxms = qxms;
        }

        public String getIpstart() {
            return ipstart;
        }

        public void setIpstart(String ipstart) {
            this.ipstart = ipstart;
        }

        public String getIpend() {
            return ipend;
        }

        public void setIpend(String ipend) {
            this.ipend = ipend;
        }

        public String getSfzmhm() {
            return sfzmhm;
        }

        public void setSfzmhm(String sfzmhm) {
            this.sfzmhm = sfzmhm;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getSsxt() {
            return ssxt;
        }

        public void setSsxt(String ssxt) {
            this.ssxt = ssxt;
        }

        public String getSsbm() {
            return ssbm;
        }

        public void setSsbm(String ssbm) {
            this.ssbm = ssbm;
        }

        public String getKlyzms() {
            return klyzms;
        }

        public void setKlyzms(String klyzms) {
            this.klyzms = klyzms;
        }

        public String getYxqz() {
            return yxqz;
        }

        public void setYxqz(String yxqz) {
            this.yxqz = yxqz;
        }

        public String getYhlx() {
            return yhlx;
        }

        public void setYhlx(String yhlx) {
            this.yhlx = yhlx;
        }

        public String getXsddm() {
            return xsddm;
        }

        public void setXsddm(String xsddm) {
            this.xsddm = xsddm;
        }

        public String getXsdmc() {
            return xsdmc;
        }

        public void setXsdmc(String xsdmc) {
            this.xsdmc = xsdmc;
        }
    }
}

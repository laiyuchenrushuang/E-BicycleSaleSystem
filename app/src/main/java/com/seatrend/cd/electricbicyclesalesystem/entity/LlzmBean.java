package com.seatrend.cd.electricbicyclesalesystem.entity;

import java.util.List;

/**
 * Created by ly on 2019/8/8 19:34
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class LlzmBean {
    private boolean status;
    private int code;
    private String message;
    private List<Data> data;
    private int total;
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return status;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public class Data {

        private String ywxh;
        private String xmxh;
        private String pzlx;
        private String sfbt;
        private String zmmc;
        private String zplx;
        public void setYwxh(String ywxh) {
            this.ywxh = ywxh;
        }
        public String getYwxh() {
            return ywxh;
        }

        public void setXmxh(String xmxh) {
            this.xmxh = xmxh;
        }
        public String getXmxh() {
            return xmxh;
        }

        public void setPzlx(String pzlx) {
            this.pzlx = pzlx;
        }
        public String getPzlx() {
            return pzlx;
        }

        public void setSfbt(String sfbt) {
            this.sfbt = sfbt;
        }
        public String getSfbt() {
            return sfbt;
        }

        public void setZmmc(String zmmc) {
            this.zmmc = zmmc;
        }
        public String getZmmc() {
            return zmmc;
        }

        public void setZplx(String zplx) {
            this.zplx = zplx;
        }
        public String getZplx() {
            return zplx;
        }

    }
}

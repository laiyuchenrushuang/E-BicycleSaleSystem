package com.seatrend.cd.electricbicyclesalesystem.entity;

import java.util.List;

/**
 * Created by ly on 2019/8/9 10:32
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */

public class YwSearchBean {

    private boolean status;
    private int code;
    private String message;
    private Data data;
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

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }


    public class Data {

        private List<ZlList> zlList;
        private VehicleTemp vehicleTemp;
        private String vehInsurance;
        private VehFlowMain vehFlowMain;
        public void setZlList(List<ZlList> zlList) {
            this.zlList = zlList;
        }
        public List<ZlList> getZlList() {
            return zlList;
        }

        public void setVehicleTemp(VehicleTemp vehicleTemp) {
            this.vehicleTemp = vehicleTemp;
        }
        public VehicleTemp getVehicleTemp() {
            return vehicleTemp;
        }

        public void setVehInsurance(String vehInsurance) {
            this.vehInsurance = vehInsurance;
        }
        public String getVehInsurance() {
            return vehInsurance;
        }

        public void setVehFlowMain(VehFlowMain vehFlowMain) {
            this.vehFlowMain = vehFlowMain;
        }
        public VehFlowMain getVehFlowMain() {
            return vehFlowMain;
        }


        public class VehicleTemp {

            private String cllx;  //车辆类型
            private String xss;
            private String xsdmc;
            private String xsddz;
            private String lczt;
            private String ywyy;
            private String rlsbjg;
            private String tmh;
            private long jysj;
            private String jydz;
            private String clqymc;
            private String zcbm; //整车编码
            private String lsh;
            private String xh;
            private String clpp;
            private String hpzl;
            private String clxh;
            private String clsbdm;
            private String hphm; //号牌号码
            private String zbzl;
            private String sczbzl;
            private String zczlbj;
            private String csys1;  //车身颜色1
            private String csys2;  //车身颜色2
            private String csysbj;
            private String zgss;
            private String hdzk;
            private String hdzz;
            private String wkc;
            private String wkk;
            private String wkg;
            private String ckgbj;
            private String djxs;
            private String xhlc;
            private String ltkd;
            private String glbh;
            private String edzs;
            private String edzj;
            private String lxgl;
            private String cdfs;
            private String qhzj;
            private String qhzxljbj;
            private String qlzj;
            private String hlzj;
            private String dcrl;
            private String bcdy;
            private String dclx;
            private String eddy;
            private String qybh;
            private String jyr;
            private String jydw;
            private String jydwmc;
            private String jyzt;
            private String sccx;
            private String djh;
            private String sjss;
            private String zgssbj;
            private String qxgn;
            private String jtxsbj;
            private String hdrq;  //获得日期
            private String djrq;
            private String lp;
            private String hdfs;  //获得方式
            private String syxz;  //使用性质
            private String dlrxm;
            private String dlrzjhm;
            private String dlrzjlx;
            private String dlrzsdz;
            private String dlrzssheng;
            private String dlrzsshi;
            private String dlrzsqu;
            private String dlrsjhm;
            private String czyjsheng;
            private String czyjshi;
            private String czyjqu;
            private String czyjdz;
            private String czxm;
            private String czfrqc;
            private String czzjlx;
            private String czzjhm;
            private String czlxdh;
            private String czzzdz;
            private String czzsdz;
            private String czzsqh;
            private String czzzqh;
            private String jzzhm;
            private String clyt;   //车辆用途
            private String cphgzbh;  //合格证编号
            private String cccbh;
            private String clzzs;
            private String xgzl;
            private String djr;
            private String djdw;
            private String llzm;  //来历证明
            private String xsqy;  //行驶区域
            private String czgj;
            private String czzssheng;
            private String czzsshi;
            private String czzsqu;
            private String czsjhm;
            private String czzsyzbm;
            private String xczxm;
            private String xczgj;
            private String xczzjlx;
            private String xczzjhm;
            private String xczzssheng;
            private String xczzsshi;
            private String xczzsqu;
            private String xczzsdz;
            private String xczlxdh;
            private String xczsjhm;
            private String zlList;
            private String vehFlowMain;
            private String vehInsurance;

            public void setCllx(String cllx) {
                this.cllx = cllx;
            }

            public String getCllx() {
                return cllx;
            }

            public void setXss(String xss) {
                this.xss = xss;
            }

            public String getXss() {
                return xss;
            }

            public void setXsdmc(String xsdmc) {
                this.xsdmc = xsdmc;
            }

            public String getXsdmc() {
                return xsdmc;
            }

            public void setXsddz(String xsddz) {
                this.xsddz = xsddz;
            }

            public String getXsddz() {
                return xsddz;
            }

            public void setLczt(String lczt) {
                this.lczt = lczt;
            }

            public String getLczt() {
                return lczt;
            }

            public void setYwyy(String ywyy) {
                this.ywyy = ywyy;
            }

            public String getYwyy() {
                return ywyy;
            }

            public void setRlsbjg(String rlsbjg) {
                this.rlsbjg = rlsbjg;
            }

            public String getRlsbjg() {
                return rlsbjg;
            }

            public void setTmh(String tmh) {
                this.tmh = tmh;
            }

            public String getTmh() {
                return tmh;
            }

            public void setJysj(long jysj) {
                this.jysj = jysj;
            }

            public long getJysj() {
                return jysj;
            }

            public void setJydz(String jydz) {
                this.jydz = jydz;
            }

            public String getJydz() {
                return jydz;
            }

            public void setClqymc(String clqymc) {
                this.clqymc = clqymc;
            }

            public String getClqymc() {
                return clqymc;
            }

            public void setZcbm(String zcbm) {
                this.zcbm = zcbm;
            }

            public String getZcbm() {
                return zcbm;
            }

            public void setLsh(String lsh) {
                this.lsh = lsh;
            }

            public String getLsh() {
                return lsh;
            }

            public void setXh(String xh) {
                this.xh = xh;
            }

            public String getXh() {
                return xh;
            }

            public void setClpp(String clpp) {
                this.clpp = clpp;
            }

            public String getClpp() {
                return clpp;
            }

            public void setHpzl(String hpzl) {
                this.hpzl = hpzl;
            }

            public String getHpzl() {
                return hpzl;
            }

            public void setClxh(String clxh) {
                this.clxh = clxh;
            }

            public String getClxh() {
                return clxh;
            }

            public void setClsbdm(String clsbdm) {
                this.clsbdm = clsbdm;
            }

            public String getClsbdm() {
                return clsbdm;
            }

            public void setHphm(String hphm) {
                this.hphm = hphm;
            }

            public String getHphm() {
                return hphm;
            }

            public void setZbzl(String zbzl) {
                this.zbzl = zbzl;
            }

            public String getZbzl() {
                return zbzl;
            }

            public void setSczbzl(String sczbzl) {
                this.sczbzl = sczbzl;
            }

            public String getSczbzl() {
                return sczbzl;
            }

            public void setZczlbj(String zczlbj) {
                this.zczlbj = zczlbj;
            }

            public String getZczlbj() {
                return zczlbj;
            }

            public void setCsys1(String csys1) {
                this.csys1 = csys1;
            }

            public String getCsys1() {
                return csys1;
            }

            public void setCsys2(String csys2) {
                this.csys2 = csys2;
            }

            public String getCsys2() {
                return csys2;
            }

            public void setCsysbj(String csysbj) {
                this.csysbj = csysbj;
            }

            public String getCsysbj() {
                return csysbj;
            }

            public void setZgss(String zgss) {
                this.zgss = zgss;
            }

            public String getZgss() {
                return zgss;
            }

            public void setHdzk(String hdzk) {
                this.hdzk = hdzk;
            }

            public String getHdzk() {
                return hdzk;
            }

            public void setHdzz(String hdzz) {
                this.hdzz = hdzz;
            }

            public String getHdzz() {
                return hdzz;
            }

            public void setWkc(String wkc) {
                this.wkc = wkc;
            }

            public String getWkc() {
                return wkc;
            }

            public void setWkk(String wkk) {
                this.wkk = wkk;
            }

            public String getWkk() {
                return wkk;
            }

            public void setWkg(String wkg) {
                this.wkg = wkg;
            }

            public String getWkg() {
                return wkg;
            }

            public void setCkgbj(String ckgbj) {
                this.ckgbj = ckgbj;
            }

            public String getCkgbj() {
                return ckgbj;
            }

            public void setDjxs(String djxs) {
                this.djxs = djxs;
            }

            public String getDjxs() {
                return djxs;
            }

            public void setXhlc(String xhlc) {
                this.xhlc = xhlc;
            }

            public String getXhlc() {
                return xhlc;
            }

            public void setLtkd(String ltkd) {
                this.ltkd = ltkd;
            }

            public String getLtkd() {
                return ltkd;
            }

            public void setGlbh(String glbh) {
                this.glbh = glbh;
            }

            public String getGlbh() {
                return glbh;
            }

            public void setEdzs(String edzs) {
                this.edzs = edzs;
            }

            public String getEdzs() {
                return edzs;
            }

            public void setEdzj(String edzj) {
                this.edzj = edzj;
            }

            public String getEdzj() {
                return edzj;
            }

            public void setLxgl(String lxgl) {
                this.lxgl = lxgl;
            }

            public String getLxgl() {
                return lxgl;
            }

            public void setCdfs(String cdfs) {
                this.cdfs = cdfs;
            }

            public String getCdfs() {
                return cdfs;
            }

            public void setQhzj(String qhzj) {
                this.qhzj = qhzj;
            }

            public String getQhzj() {
                return qhzj;
            }

            public void setQhzxljbj(String qhzxljbj) {
                this.qhzxljbj = qhzxljbj;
            }

            public String getQhzxljbj() {
                return qhzxljbj;
            }

            public void setQlzj(String qlzj) {
                this.qlzj = qlzj;
            }

            public String getQlzj() {
                return qlzj;
            }

            public void setHlzj(String hlzj) {
                this.hlzj = hlzj;
            }

            public String getHlzj() {
                return hlzj;
            }

            public void setDcrl(String dcrl) {
                this.dcrl = dcrl;
            }

            public String getDcrl() {
                return dcrl;
            }

            public void setBcdy(String bcdy) {
                this.bcdy = bcdy;
            }

            public String getBcdy() {
                return bcdy;
            }

            public void setDclx(String dclx) {
                this.dclx = dclx;
            }

            public String getDclx() {
                return dclx;
            }

            public void setEddy(String eddy) {
                this.eddy = eddy;
            }

            public String getEddy() {
                return eddy;
            }

            public void setQybh(String qybh) {
                this.qybh = qybh;
            }

            public String getQybh() {
                return qybh;
            }

            public void setJyr(String jyr) {
                this.jyr = jyr;
            }

            public String getJyr() {
                return jyr;
            }

            public void setJydw(String jydw) {
                this.jydw = jydw;
            }

            public String getJydw() {
                return jydw;
            }

            public void setJydwmc(String jydwmc) {
                this.jydwmc = jydwmc;
            }

            public String getJydwmc() {
                return jydwmc;
            }

            public void setJyzt(String jyzt) {
                this.jyzt = jyzt;
            }

            public String getJyzt() {
                return jyzt;
            }

            public void setSccx(String sccx) {
                this.sccx = sccx;
            }

            public String getSccx() {
                return sccx;
            }

            public void setDjh(String djh) {
                this.djh = djh;
            }

            public String getDjh() {
                return djh;
            }

            public void setSjss(String sjss) {
                this.sjss = sjss;
            }

            public String getSjss() {
                return sjss;
            }

            public void setZgssbj(String zgssbj) {
                this.zgssbj = zgssbj;
            }

            public String getZgssbj() {
                return zgssbj;
            }

            public void setQxgn(String qxgn) {
                this.qxgn = qxgn;
            }

            public String getQxgn() {
                return qxgn;
            }

            public void setJtxsbj(String jtxsbj) {
                this.jtxsbj = jtxsbj;
            }

            public String getJtxsbj() {
                return jtxsbj;
            }

            public void setHdrq(String hdrq) {
                this.hdrq = hdrq;
            }

            public String getHdrq() {
                return hdrq;
            }

            public void setDjrq(String djrq) {
                this.djrq = djrq;
            }

            public String getDjrq() {
                return djrq;
            }

            public void setLp(String lp) {
                this.lp = lp;
            }

            public String getLp() {
                return lp;
            }

            public void setHdfs(String hdfs) {
                this.hdfs = hdfs;
            }

            public String getHdfs() {
                return hdfs;
            }

            public void setSyxz(String syxz) {
                this.syxz = syxz;
            }

            public String getSyxz() {
                return syxz;
            }

            public void setDlrxm(String dlrxm) {
                this.dlrxm = dlrxm;
            }

            public String getDlrxm() {
                return dlrxm;
            }

            public void setDlrzjhm(String dlrzjhm) {
                this.dlrzjhm = dlrzjhm;
            }

            public String getDlrzjhm() {
                return dlrzjhm;
            }

            public void setDlrzjlx(String dlrzjlx) {
                this.dlrzjlx = dlrzjlx;
            }

            public String getDlrzjlx() {
                return dlrzjlx;
            }

            public void setDlrzsdz(String dlrzsdz) {
                this.dlrzsdz = dlrzsdz;
            }

            public String getDlrzsdz() {
                return dlrzsdz;
            }

            public void setDlrzssheng(String dlrzssheng) {
                this.dlrzssheng = dlrzssheng;
            }

            public String getDlrzssheng() {
                return dlrzssheng;
            }

            public void setDlrzsshi(String dlrzsshi) {
                this.dlrzsshi = dlrzsshi;
            }

            public String getDlrzsshi() {
                return dlrzsshi;
            }

            public void setDlrzsqu(String dlrzsqu) {
                this.dlrzsqu = dlrzsqu;
            }

            public String getDlrzsqu() {
                return dlrzsqu;
            }

            public void setDlrsjhm(String dlrsjhm) {
                this.dlrsjhm = dlrsjhm;
            }

            public String getDlrsjhm() {
                return dlrsjhm;
            }

            public void setCzyjsheng(String czyjsheng) {
                this.czyjsheng = czyjsheng;
            }

            public String getCzyjsheng() {
                return czyjsheng;
            }

            public void setCzyjshi(String czyjshi) {
                this.czyjshi = czyjshi;
            }

            public String getCzyjshi() {
                return czyjshi;
            }

            public void setCzyjqu(String czyjqu) {
                this.czyjqu = czyjqu;
            }

            public String getCzyjqu() {
                return czyjqu;
            }

            public void setCzyjdz(String czyjdz) {
                this.czyjdz = czyjdz;
            }

            public String getCzyjdz() {
                return czyjdz;
            }

            public void setCzxm(String czxm) {
                this.czxm = czxm;
            }

            public String getCzxm() {
                return czxm;
            }

            public void setCzfrqc(String czfrqc) {
                this.czfrqc = czfrqc;
            }

            public String getCzfrqc() {
                return czfrqc;
            }

            public void setCzzjlx(String czzjlx) {
                this.czzjlx = czzjlx;
            }

            public String getCzzjlx() {
                return czzjlx;
            }

            public void setCzzjhm(String czzjhm) {
                this.czzjhm = czzjhm;
            }

            public String getCzzjhm() {
                return czzjhm;
            }

            public void setCzlxdh(String czlxdh) {
                this.czlxdh = czlxdh;
            }

            public String getCzlxdh() {
                return czlxdh;
            }

            public void setCzzzdz(String czzzdz) {
                this.czzzdz = czzzdz;
            }

            public String getCzzzdz() {
                return czzzdz;
            }

            public void setCzzsdz(String czzsdz) {
                this.czzsdz = czzsdz;
            }

            public String getCzzsdz() {
                return czzsdz;
            }

            public void setCzzsqh(String czzsqh) {
                this.czzsqh = czzsqh;
            }

            public String getCzzsqh() {
                return czzsqh;
            }

            public void setCzzzqh(String czzzqh) {
                this.czzzqh = czzzqh;
            }

            public String getCzzzqh() {
                return czzzqh;
            }

            public void setJzzhm(String jzzhm) {
                this.jzzhm = jzzhm;
            }

            public String getJzzhm() {
                return jzzhm;
            }

            public void setClyt(String clyt) {
                this.clyt = clyt;
            }

            public String getClyt() {
                return clyt;
            }

            public void setCphgzbh(String cphgzbh) {
                this.cphgzbh = cphgzbh;
            }

            public String getCphgzbh() {
                return cphgzbh;
            }

            public void setCccbh(String cccbh) {
                this.cccbh = cccbh;
            }

            public String getCccbh() {
                return cccbh;
            }

            public void setClzzs(String clzzs) {
                this.clzzs = clzzs;
            }

            public String getClzzs() {
                return clzzs;
            }

            public void setXgzl(String xgzl) {
                this.xgzl = xgzl;
            }

            public String getXgzl() {
                return xgzl;
            }

            public void setDjr(String djr) {
                this.djr = djr;
            }

            public String getDjr() {
                return djr;
            }

            public void setDjdw(String djdw) {
                this.djdw = djdw;
            }

            public String getDjdw() {
                return djdw;
            }

            public void setLlzm(String llzm) {
                this.llzm = llzm;
            }

            public String getLlzm() {
                return llzm;
            }

            public void setXsqy(String xsqy) {
                this.xsqy = xsqy;
            }

            public String getXsqy() {
                return xsqy;
            }

            public void setCzgj(String czgj) {
                this.czgj = czgj;
            }

            public String getCzgj() {
                return czgj;
            }

            public void setCzzssheng(String czzssheng) {
                this.czzssheng = czzssheng;
            }

            public String getCzzssheng() {
                return czzssheng;
            }

            public void setCzzsshi(String czzsshi) {
                this.czzsshi = czzsshi;
            }

            public String getCzzsshi() {
                return czzsshi;
            }

            public void setCzzsqu(String czzsqu) {
                this.czzsqu = czzsqu;
            }

            public String getCzzsqu() {
                return czzsqu;
            }

            public void setCzsjhm(String czsjhm) {
                this.czsjhm = czsjhm;
            }

            public String getCzsjhm() {
                return czsjhm;
            }

            public void setCzzsyzbm(String czzsyzbm) {
                this.czzsyzbm = czzsyzbm;
            }

            public String getCzzsyzbm() {
                return czzsyzbm;
            }

            public void setXczxm(String xczxm) {
                this.xczxm = xczxm;
            }

            public String getXczxm() {
                return xczxm;
            }

            public void setXczgj(String xczgj) {
                this.xczgj = xczgj;
            }

            public String getXczgj() {
                return xczgj;
            }

            public void setXczzjlx(String xczzjlx) {
                this.xczzjlx = xczzjlx;
            }

            public String getXczzjlx() {
                return xczzjlx;
            }

            public void setXczzjhm(String xczzjhm) {
                this.xczzjhm = xczzjhm;
            }

            public String getXczzjhm() {
                return xczzjhm;
            }

            public void setXczzssheng(String xczzssheng) {
                this.xczzssheng = xczzssheng;
            }

            public String getXczzssheng() {
                return xczzssheng;
            }

            public void setXczzsshi(String xczzsshi) {
                this.xczzsshi = xczzsshi;
            }

            public String getXczzsshi() {
                return xczzsshi;
            }

            public void setXczzsqu(String xczzsqu) {
                this.xczzsqu = xczzsqu;
            }

            public String getXczzsqu() {
                return xczzsqu;
            }

            public void setXczzsdz(String xczzsdz) {
                this.xczzsdz = xczzsdz;
            }

            public String getXczzsdz() {
                return xczzsdz;
            }

            public void setXczlxdh(String xczlxdh) {
                this.xczlxdh = xczlxdh;
            }

            public String getXczlxdh() {
                return xczlxdh;
            }

            public void setXczsjhm(String xczsjhm) {
                this.xczsjhm = xczsjhm;
            }

            public String getXczsjhm() {
                return xczsjhm;
            }

            public void setZlList(String zlList) {
                this.zlList = zlList;
            }

            public String getZlList() {
                return zlList;
            }

            public void setVehFlowMain(String vehFlowMain) {
                this.vehFlowMain = vehFlowMain;
            }

            public String getVehFlowMain() {
                return vehFlowMain;
            }

            public void setVehInsurance(String vehInsurance) {
                this.vehInsurance = vehInsurance;
            }

            public String getVehInsurance() {
                return vehInsurance;
            }

        }

        public class VehFlowMain {

            private String lsh;
            private String xh;
            private String yhpzl;
            private String yhphm;
            private String ywlx;  //业务类型
            private String djjy;
            private String lczt;
            private String gqbz;
            private String zxbj;
            private long slrq;
            private String bjrq;
            private String dlbj;
            private String xgzl;
            private String xhbj;
            private String xhdm;
            private String syr;
            private String xhhpzl;
            private String glbm;
            private String scbm;
            private String jccs;
            private String cybj;
            private String jybj;
            private String clsbdh;
            private String yxqz;
            private String ywyy;
            private String wjbj;
            private String zdbj;
            private String fzjg;
            private String shbj;
            private String zlsfqq;
            private String sfzmhm;
            private String cyzcxh;
            private String jgbj;
            private String zdsh;
            private String cshbsd;
            private String qxgn;
            private String zcbm;
            private String tmh;
            private String shbz;
            private String cllx;
            private String glbmmc;
            private String shrq;
            private String shry;
            private String jgry;
            private String jgrq;
            private String jgbz;
            private String hpbf;
            private String beginTime;
            private String endTime;
            private String vehInsurance;
            private String vehicleTemp;

            public void setLsh(String lsh) {
                this.lsh = lsh;
            }

            public String getLsh() {
                return lsh;
            }

            public void setXh(String xh) {
                this.xh = xh;
            }

            public String getXh() {
                return xh;
            }

            public void setYhpzl(String yhpzl) {
                this.yhpzl = yhpzl;
            }

            public String getYhpzl() {
                return yhpzl;
            }

            public void setYhphm(String yhphm) {
                this.yhphm = yhphm;
            }

            public String getYhphm() {
                return yhphm;
            }

            public void setYwlx(String ywlx) {
                this.ywlx = ywlx;
            }

            public String getYwlx() {
                return ywlx;
            }

            public void setDjjy(String djjy) {
                this.djjy = djjy;
            }

            public String getDjjy() {
                return djjy;
            }

            public void setLczt(String lczt) {
                this.lczt = lczt;
            }

            public String getLczt() {
                return lczt;
            }

            public void setGqbz(String gqbz) {
                this.gqbz = gqbz;
            }

            public String getGqbz() {
                return gqbz;
            }

            public void setZxbj(String zxbj) {
                this.zxbj = zxbj;
            }

            public String getZxbj() {
                return zxbj;
            }

            public void setSlrq(long slrq) {
                this.slrq = slrq;
            }

            public long getSlrq() {
                return slrq;
            }

            public void setBjrq(String bjrq) {
                this.bjrq = bjrq;
            }

            public String getBjrq() {
                return bjrq;
            }

            public void setDlbj(String dlbj) {
                this.dlbj = dlbj;
            }

            public String getDlbj() {
                return dlbj;
            }

            public void setXgzl(String xgzl) {
                this.xgzl = xgzl;
            }

            public String getXgzl() {
                return xgzl;
            }

            public void setXhbj(String xhbj) {
                this.xhbj = xhbj;
            }

            public String getXhbj() {
                return xhbj;
            }

            public void setXhdm(String xhdm) {
                this.xhdm = xhdm;
            }

            public String getXhdm() {
                return xhdm;
            }

            public void setSyr(String syr) {
                this.syr = syr;
            }

            public String getSyr() {
                return syr;
            }

            public void setXhhpzl(String xhhpzl) {
                this.xhhpzl = xhhpzl;
            }

            public String getXhhpzl() {
                return xhhpzl;
            }

            public void setGlbm(String glbm) {
                this.glbm = glbm;
            }

            public String getGlbm() {
                return glbm;
            }

            public void setScbm(String scbm) {
                this.scbm = scbm;
            }

            public String getScbm() {
                return scbm;
            }

            public void setJccs(String jccs) {
                this.jccs = jccs;
            }

            public String getJccs() {
                return jccs;
            }

            public void setCybj(String cybj) {
                this.cybj = cybj;
            }

            public String getCybj() {
                return cybj;
            }

            public void setJybj(String jybj) {
                this.jybj = jybj;
            }

            public String getJybj() {
                return jybj;
            }

            public void setClsbdh(String clsbdh) {
                this.clsbdh = clsbdh;
            }

            public String getClsbdh() {
                return clsbdh;
            }

            public void setYxqz(String yxqz) {
                this.yxqz = yxqz;
            }

            public String getYxqz() {
                return yxqz;
            }

            public void setYwyy(String ywyy) {
                this.ywyy = ywyy;
            }

            public String getYwyy() {
                return ywyy;
            }

            public void setWjbj(String wjbj) {
                this.wjbj = wjbj;
            }

            public String getWjbj() {
                return wjbj;
            }

            public void setZdbj(String zdbj) {
                this.zdbj = zdbj;
            }

            public String getZdbj() {
                return zdbj;
            }

            public void setFzjg(String fzjg) {
                this.fzjg = fzjg;
            }

            public String getFzjg() {
                return fzjg;
            }

            public void setShbj(String shbj) {
                this.shbj = shbj;
            }

            public String getShbj() {
                return shbj;
            }

            public void setZlsfqq(String zlsfqq) {
                this.zlsfqq = zlsfqq;
            }

            public String getZlsfqq() {
                return zlsfqq;
            }

            public void setSfzmhm(String sfzmhm) {
                this.sfzmhm = sfzmhm;
            }

            public String getSfzmhm() {
                return sfzmhm;
            }

            public void setCyzcxh(String cyzcxh) {
                this.cyzcxh = cyzcxh;
            }

            public String getCyzcxh() {
                return cyzcxh;
            }

            public void setJgbj(String jgbj) {
                this.jgbj = jgbj;
            }

            public String getJgbj() {
                return jgbj;
            }

            public void setZdsh(String zdsh) {
                this.zdsh = zdsh;
            }

            public String getZdsh() {
                return zdsh;
            }

            public void setCshbsd(String cshbsd) {
                this.cshbsd = cshbsd;
            }

            public String getCshbsd() {
                return cshbsd;
            }

            public void setQxgn(String qxgn) {
                this.qxgn = qxgn;
            }

            public String getQxgn() {
                return qxgn;
            }

            public void setZcbm(String zcbm) {
                this.zcbm = zcbm;
            }

            public String getZcbm() {
                return zcbm;
            }

            public void setTmh(String tmh) {
                this.tmh = tmh;
            }

            public String getTmh() {
                return tmh;
            }

            public void setShbz(String shbz) {
                this.shbz = shbz;
            }

            public String getShbz() {
                return shbz;
            }

            public void setCllx(String cllx) {
                this.cllx = cllx;
            }

            public String getCllx() {
                return cllx;
            }

            public void setGlbmmc(String glbmmc) {
                this.glbmmc = glbmmc;
            }

            public String getGlbmmc() {
                return glbmmc;
            }

            public void setShrq(String shrq) {
                this.shrq = shrq;
            }

            public String getShrq() {
                return shrq;
            }

            public void setShry(String shry) {
                this.shry = shry;
            }

            public String getShry() {
                return shry;
            }

            public void setJgry(String jgry) {
                this.jgry = jgry;
            }

            public String getJgry() {
                return jgry;
            }

            public void setJgrq(String jgrq) {
                this.jgrq = jgrq;
            }

            public String getJgrq() {
                return jgrq;
            }

            public void setJgbz(String jgbz) {
                this.jgbz = jgbz;
            }

            public String getJgbz() {
                return jgbz;
            }

            public void setHpbf(String hpbf) {
                this.hpbf = hpbf;
            }

            public String getHpbf() {
                return hpbf;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setVehInsurance(String vehInsurance) {
                this.vehInsurance = vehInsurance;
            }

            public String getVehInsurance() {
                return vehInsurance;
            }

            public void setVehicleTemp(String vehicleTemp) {
                this.vehicleTemp = vehicleTemp;
            }

            public String getVehicleTemp() {
                return vehicleTemp;
            }

        }

        public class VehInsurance {

            private String bxd;
            private String bxgsmc;
            private String bxksrq;
            private String bxjsrq;
            private String bf;
            private String bxcpbm;
            private String lsh;
            private String xh;
            private String hphm;
            private String clsbdh;
            private String beginTime;
            private String endTime;
            private String seaDepartment;
            private String vehicleTemp;

            public void setBxd(String bxd) {
                this.bxd = bxd;
            }

            public String getBxd() {
                return bxd;
            }

            public void setBxgsmc(String bxgsmc) {
                this.bxgsmc = bxgsmc;
            }

            public String getBxgsmc() {
                return bxgsmc;
            }

            public void setBxksrq(String bxksrq) {
                this.bxksrq = bxksrq;
            }

            public String getBxksrq() {
                return bxksrq;
            }

            public void setBxjsrq(String bxjsrq) {
                this.bxjsrq = bxjsrq;
            }

            public String getBxjsrq() {
                return bxjsrq;
            }

            public void setBf(String bf) {
                this.bf = bf;
            }

            public String getBf() {
                return bf;
            }

            public void setBxcpbm(String bxcpbm) {
                this.bxcpbm = bxcpbm;
            }

            public String getBxcpbm() {
                return bxcpbm;
            }

            public void setLsh(String lsh) {
                this.lsh = lsh;
            }

            public String getLsh() {
                return lsh;
            }

            public void setXh(String xh) {
                this.xh = xh;
            }

            public String getXh() {
                return xh;
            }

            public void setHphm(String hphm) {
                this.hphm = hphm;
            }

            public String getHphm() {
                return hphm;
            }

            public void setClsbdh(String clsbdh) {
                this.clsbdh = clsbdh;
            }

            public String getClsbdh() {
                return clsbdh;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setSeaDepartment(String seaDepartment) {
                this.seaDepartment = seaDepartment;
            }

            public String getSeaDepartment() {
                return seaDepartment;
            }

            public void setVehicleTemp(String vehicleTemp) {
                this.vehicleTemp = vehicleTemp;
            }

            public String getVehicleTemp() {
                return vehicleTemp;
            }

        }

        public class ZlList {

            private String xh;
            private String lsh;
            private String clsbdm;
            private String zp;
            private String zpStr;
            private String czsj;
            private String csys1;
            private String csys2;
            private String glbm;
            private String zt;
            private String zdbj;
            private String zplx;
            private String zpmc;
            private String zlfl;
            public void setXh(String xh) {
                this.xh = xh;
            }
            public String getXh() {
                return xh;
            }

            public void setLsh(String lsh) {
                this.lsh = lsh;
            }
            public String getLsh() {
                return lsh;
            }

            public void setClsbdm(String clsbdm) {
                this.clsbdm = clsbdm;
            }
            public String getClsbdm() {
                return clsbdm;
            }

            public void setZp(String zp) {
                this.zp = zp;
            }
            public String getZp() {
                return zp;
            }

            public void setZpStr(String zpStr) {
                this.zpStr = zpStr;
            }
            public String getZpStr() {
                return zpStr;
            }

            public void setCzsj(String czsj) {
                this.czsj = czsj;
            }
            public String getCzsj() {
                return czsj;
            }

            public void setCsys1(String csys1) {
                this.csys1 = csys1;
            }
            public String getCsys1() {
                return csys1;
            }

            public void setCsys2(String csys2) {
                this.csys2 = csys2;
            }
            public String getCsys2() {
                return csys2;
            }

            public void setGlbm(String glbm) {
                this.glbm = glbm;
            }
            public String getGlbm() {
                return glbm;
            }

            public void setZt(String zt) {
                this.zt = zt;
            }
            public String getZt() {
                return zt;
            }

            public void setZdbj(String zdbj) {
                this.zdbj = zdbj;
            }
            public String getZdbj() {
                return zdbj;
            }

            public void setZplx(String zplx) {
                this.zplx = zplx;
            }
            public String getZplx() {
                return zplx;
            }

            public void setZpmc(String zpmc) {
                this.zpmc = zpmc;
            }
            public String getZpmc() {
                return zpmc;
            }

            public void setZlfl(String zlfl) {
                this.zlfl = zlfl;
            }
            public String getZlfl() {
                return zlfl;
            }

        }
    }
}

package com.seatrend.cd.electricbicyclesalesystem.entity;

import java.util.List;

/**
 * Created by seatrend on 2019/1/8.
 */

public class WarningMessageEntity extends BaseEntity {


    /**
     * data : [{"lsh":"5102150101029249","xh":"51019000019689","yhpzl":"B","yhphm":"A144248","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420073445000,"bjrq":1420073728000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"姚佳","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"510104197612052622","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029250","xh":"51019000019703","yhpzl":"B","yhphm":"A144249","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420073518000,"bjrq":1420073962000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"王剑","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"512535197805092119","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029251","xh":"51019000019716","yhpzl":"B","yhphm":"A144258","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420073578000,"bjrq":1420075280000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"向远明","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"510113197412237418","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029252","xh":"51019000019742","yhpzl":"B","yhphm":"A144252","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420073696000,"bjrq":1420074467000,"dlbj":"0","xgzl":"71,72,73,","xhbj":null,"xhdm":null,"syr":"黄沛明","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"511081198204235211","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029246","xh":"51019000019652","yhpzl":"B","yhphm":"A144250","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420073053000,"bjrq":1420074056000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"陈启贵","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"511023197211112799","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029247","xh":"51019000019659","yhpzl":"B","yhphm":"A144247","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420073258000,"bjrq":1420073516000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"焦玛","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"510102195612278451","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029248","xh":"51019000019674","yhpzl":"B","yhphm":"A144251","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420073400000,"bjrq":1420074332000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"侯志军","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"510123196707022519","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029258","xh":"51019000020353","yhpzl":"B","yhphm":"A144256","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420074641000,"bjrq":1420075000000,"dlbj":"0","xgzl":"71,72,73,71,72,","xhbj":null,"xhdm":null,"syr":"潘小平","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"512530196802221695","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029261","xh":"51019000020593","yhpzl":"B","yhphm":"A144270","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420074856000,"bjrq":1420076769000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"叶成全","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"51012219630918177X","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null},{"lsh":"5102150101029259","xh":"51019000020422","yhpzl":"B","yhphm":"A144259","ywlx":"A","djjy":"超标电动车注册登记","lczt":"E","gqbz":null,"zxbj":null,"slrq":1420074685000,"bjrq":1420075375000,"dlbj":"0","xgzl":"71,72,","xhbj":null,"xhdm":null,"syr":"杨永久","xhhpzl":null,"glbm":"510101000405","jccs":null,"cybj":"1","jybj":null,"clsbdh":null,"yxqz":null,"ywyy":null,"wjbj":null,"zdbj":"1","fzjg":"川A","shbj":"2","zlsfqq":"0","sfzmhm":"511023196311291515","cyzcxh":"2","jgbj":"0","zdsh":null,"cshbsd":null,"qxgn":null,"zcbm":null,"tmh":null}]
     * total : 10
     */

    private int total;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lsh : 5102150101029249
         * xh : 51019000019689
         * yhpzl : B
         * yhphm : A144248
         * ywlx : A
         * djjy : 超标电动车注册登记
         * lczt : E
         * gqbz : null
         * zxbj : null
         * slrq : 1420073445000
         * bjrq : 1420073728000
         * dlbj : 0
         * xgzl : 71,72,
         * xhbj : null
         * xhdm : null
         * syr : 姚佳
         * xhhpzl : null
         * glbm : 510101000405
         * jccs : null
         * cybj : 1
         * jybj : null
         * clsbdh : null
         * yxqz : null
         * ywyy : null
         * wjbj : null
         * zdbj : 1
         * fzjg : 川A
         * shbj : 2
         * zlsfqq : 0
         * sfzmhm : 510104197612052622
         * cyzcxh : 2
         * jgbj : 0
         * zdsh : null
         * cshbsd : null
         * qxgn : null
         * zcbm : null
         * tmh : null
         */

        private String lsh;
        private String xh;
        private String yhpzl;
        private String yhphm;
        private String ywlx;
        private String djjy;
        private String lczt;
        private String gqbz;
        private String zxbj;
        private long slrq;
        private long bjrq;
        private String dlbj;
        private String xgzl;
        private String xhbj;
        private String xhdm;
        private String syr;
        private String xhhpzl;
        private String glbm;
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

        public String getLsh() {
            return lsh;
        }

        public void setLsh(String lsh) {
            this.lsh = lsh;
        }

        public String getXh() {
            return xh;
        }

        public void setXh(String xh) {
            this.xh = xh;
        }

        public String getYhpzl() {
            return yhpzl;
        }

        public void setYhpzl(String yhpzl) {
            this.yhpzl = yhpzl;
        }

        public String getYhphm() {
            return yhphm;
        }

        public void setYhphm(String yhphm) {
            this.yhphm = yhphm;
        }

        public String getYwlx() {
            return ywlx;
        }

        public void setYwlx(String ywlx) {
            this.ywlx = ywlx;
        }

        public String getDjjy() {
            return djjy;
        }

        public void setDjjy(String djjy) {
            this.djjy = djjy;
        }

        public String getLczt() {
            return lczt;
        }

        public void setLczt(String lczt) {
            this.lczt = lczt;
        }

        public String getGqbz() {
            return gqbz;
        }

        public void setGqbz(String gqbz) {
            this.gqbz = gqbz;
        }

        public String getZxbj() {
            return zxbj;
        }

        public void setZxbj(String zxbj) {
            this.zxbj = zxbj;
        }

        public long getSlrq() {
            return slrq;
        }

        public void setSlrq(long slrq) {
            this.slrq = slrq;
        }

        public long getBjrq() {
            return bjrq;
        }

        public void setBjrq(long bjrq) {
            this.bjrq = bjrq;
        }

        public String getDlbj() {
            return dlbj;
        }

        public void setDlbj(String dlbj) {
            this.dlbj = dlbj;
        }

        public String getXgzl() {
            return xgzl;
        }

        public void setXgzl(String xgzl) {
            this.xgzl = xgzl;
        }

        public String getXhbj() {
            return xhbj;
        }

        public void setXhbj(String xhbj) {
            this.xhbj = xhbj;
        }

        public String getXhdm() {
            return xhdm;
        }

        public void setXhdm(String xhdm) {
            this.xhdm = xhdm;
        }

        public String getSyr() {
            return syr;
        }

        public void setSyr(String syr) {
            this.syr = syr;
        }

        public String getXhhpzl() {
            return xhhpzl;
        }

        public void setXhhpzl(String xhhpzl) {
            this.xhhpzl = xhhpzl;
        }

        public String getGlbm() {
            return glbm;
        }

        public void setGlbm(String glbm) {
            this.glbm = glbm;
        }

        public String getJccs() {
            return jccs;
        }

        public void setJccs(String jccs) {
            this.jccs = jccs;
        }

        public String getCybj() {
            return cybj;
        }

        public void setCybj(String cybj) {
            this.cybj = cybj;
        }

        public String getJybj() {
            return jybj;
        }

        public void setJybj(String jybj) {
            this.jybj = jybj;
        }

        public String getClsbdh() {
            return clsbdh;
        }

        public void setClsbdh(String clsbdh) {
            this.clsbdh = clsbdh;
        }

        public String getYxqz() {
            return yxqz;
        }

        public void setYxqz(String yxqz) {
            this.yxqz = yxqz;
        }

        public String getYwyy() {
            return ywyy;
        }

        public void setYwyy(String ywyy) {
            this.ywyy = ywyy;
        }

        public String getWjbj() {
            return wjbj;
        }

        public void setWjbj(String wjbj) {
            this.wjbj = wjbj;
        }

        public String getZdbj() {
            return zdbj;
        }

        public void setZdbj(String zdbj) {
            this.zdbj = zdbj;
        }

        public String getFzjg() {
            return fzjg;
        }

        public void setFzjg(String fzjg) {
            this.fzjg = fzjg;
        }

        public String getShbj() {
            return shbj;
        }

        public void setShbj(String shbj) {
            this.shbj = shbj;
        }

        public String getZlsfqq() {
            return zlsfqq;
        }

        public void setZlsfqq(String zlsfqq) {
            this.zlsfqq = zlsfqq;
        }

        public String getSfzmhm() {
            return sfzmhm;
        }

        public void setSfzmhm(String sfzmhm) {
            this.sfzmhm = sfzmhm;
        }

        public String getCyzcxh() {
            return cyzcxh;
        }

        public void setCyzcxh(String cyzcxh) {
            this.cyzcxh = cyzcxh;
        }

        public String getJgbj() {
            return jgbj;
        }

        public void setJgbj(String jgbj) {
            this.jgbj = jgbj;
        }

        public String getZdsh() {
            return zdsh;
        }

        public void setZdsh(String zdsh) {
            this.zdsh = zdsh;
        }

        public String getCshbsd() {
            return cshbsd;
        }

        public void setCshbsd(String cshbsd) {
            this.cshbsd = cshbsd;
        }

        public String getQxgn() {
            return qxgn;
        }

        public void setQxgn(String qxgn) {
            this.qxgn = qxgn;
        }

        public String getZcbm() {
            return zcbm;
        }

        public void setZcbm(String zcbm) {
            this.zcbm = zcbm;
        }

        public String getTmh() {
            return tmh;
        }

        public void setTmh(String tmh) {
            this.tmh = tmh;
        }
    }
}

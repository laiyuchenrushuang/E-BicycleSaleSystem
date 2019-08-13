package com.seatrend.cd.electricbicyclesalesystem.entity;

import java.util.List;

/**
 * Created by seatrend on 2019/1/7.
 */

public class CompanyNameEntity extends BaseEntity {


    /**
     * data : {"InsuranceCompanys":["人寿保险","天安保险"]}
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
        private List<String> InsuranceCompanys;

        public List<String> getInsuranceCompanys() {
            return InsuranceCompanys;
        }

        public void setInsuranceCompanys(List<String> InsuranceCompanys) {
            this.InsuranceCompanys = InsuranceCompanys;
        }
    }
}

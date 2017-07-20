package com.zoesap.borrowclient.data.bean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/19.
 */

public class ChooseLoanTypeBean {

    /**
     * code : 10000
     * info : success
     * data : {"loantype":[{"skey":1,"sval":"信用贷"},{"skey":2,"sval":"抵押贷"},{"skey":3,"sval":"担保贷"}],"career":[{"skey":1,"sval":"个体工商户"},{"skey":2,"sval":"上班族"},{"skey":3,"sval":"无固定职业"},{"skey":4,"sval":"企业主"},{"skey":5,"sval":"学生"}],"credit":[{"skey":1,"sval":"有信用记录(有信用卡或房贷等)"},{"skey":2,"sval":"无信用记录"}],"house":[{"skey":1,"sval":"有房产"},{"skey":2,"sval":"无房产"}]}
     * location :
     */

    private int code;
    private String info;
    private DataBean data;
    private String location;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class DataBean {
        private List<LoantypeBean> loantype;
        private List<CareerBean> career;
        private List<CreditBean> credit;
        private List<HouseBean> house;

        public List<LoantypeBean> getLoantype() {
            return loantype;
        }

        public void setLoantype(List<LoantypeBean> loantype) {
            this.loantype = loantype;
        }

        public List<CareerBean> getCareer() {
            return career;
        }

        public void setCareer(List<CareerBean> career) {
            this.career = career;
        }

        public List<CreditBean> getCredit() {
            return credit;
        }

        public void setCredit(List<CreditBean> credit) {
            this.credit = credit;
        }

        public List<HouseBean> getHouse() {
            return house;
        }

        public void setHouse(List<HouseBean> house) {
            this.house = house;
        }

        public static class LoantypeBean {
            /**
             * skey : 1
             * sval : 信用贷
             */

            private int skey;
            private String sval;

            public int getSkey() {
                return skey;
            }

            public void setSkey(int skey) {
                this.skey = skey;
            }

            public String getSval() {
                return sval;
            }

            public void setSval(String sval) {
                this.sval = sval;
            }
        }

        public static class CareerBean {
            /**
             * skey : 1
             * sval : 个体工商户
             */

            private int skey;
            private String sval;

            public int getSkey() {
                return skey;
            }

            public void setSkey(int skey) {
                this.skey = skey;
            }

            public String getSval() {
                return sval;
            }

            public void setSval(String sval) {
                this.sval = sval;
            }
        }

        public static class CreditBean {
            /**
             * skey : 1
             * sval : 有信用记录(有信用卡或房贷等)
             */

            private int skey;
            private String sval;

            public int getSkey() {
                return skey;
            }

            public void setSkey(int skey) {
                this.skey = skey;
            }

            public String getSval() {
                return sval;
            }

            public void setSval(String sval) {
                this.sval = sval;
            }
        }

        public static class HouseBean {
            /**
             * skey : 1
             * sval : 有房产
             */

            private int skey;
            private String sval;

            public int getSkey() {
                return skey;
            }

            public void setSkey(int skey) {
                this.skey = skey;
            }

            public String getSval() {
                return sval;
            }

            public void setSval(String sval) {
                this.sval = sval;
            }
        }
    }
}

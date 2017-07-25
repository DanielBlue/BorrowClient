package com.zoesap.borrowclient.data.bean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/25.
 */

public class MyRecommendBean {

    /**
     * code : 10000
     * data : {"datas":[{"apply_for":"快速贷款","apply_time":"2017-03-01 13:58","money":"1000","state":"0"}],"salt":"839941"}
     * info : success
     * location :
     */

    private int code;
    private DataBean data;
    private String info;
    private String location;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class DataBean {
        /**
         * datas : [{"apply_for":"快速贷款","apply_time":"2017-03-01 13:58","money":"1000","state":"0"}]
         * salt : 839941
         */

        private String salt;
        private List<DatasBean> datas;

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            public DatasBean(String apply_for, String apply_time, String money, String state) {
                this.apply_for = apply_for;
                this.apply_time = apply_time;
                this.money = money;
                this.state = state;
            }

            /**
             * apply_for : 快速贷款
             * apply_time : 2017-03-01 13:58
             * money : 1000
             * state : 0
             */



            private String apply_for;
            private String apply_time;
            private String money;
            private String state;

            public String getApply_for() {
                return apply_for;
            }

            public void setApply_for(String apply_for) {
                this.apply_for = apply_for;
            }

            public String getApply_time() {
                return apply_time;
            }

            public void setApply_time(String apply_time) {
                this.apply_time = apply_time;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}

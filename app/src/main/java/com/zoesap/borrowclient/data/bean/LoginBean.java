package com.zoesap.borrowclient.data.bean;

/**
 * Created by maoqi on 2017/7/20.
 */

public class LoginBean {


    /**
     * code : 10000
     * info : success
     * data : {"info":{"uid":"AD5WNwI1VWdaNV1pUWpdOQ0O000O00","fund":"0.00","frzbal":"0.00","avlbal":"0.00"}}
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
        /**
         * info : {"uid":"AD5WNwI1VWdaNV1pUWpdOQ0O000O00","fund":"0.00","frzbal":"0.00","avlbal":"0.00"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * uid : AD5WNwI1VWdaNV1pUWpdOQ0O000O00
             * fund : 0.00
             * frzbal : 0.00
             * avlbal : 0.00
             */

            private String uid;
            private String fund;
            private String frzbal;
            private String avlbal;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getFund() {
                return fund;
            }

            public void setFund(String fund) {
                this.fund = fund;
            }

            public String getFrzbal() {
                return frzbal;
            }

            public void setFrzbal(String frzbal) {
                this.frzbal = frzbal;
            }

            public String getAvlbal() {
                return avlbal;
            }

            public void setAvlbal(String avlbal) {
                this.avlbal = avlbal;
            }
        }
    }
}

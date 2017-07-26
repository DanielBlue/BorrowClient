package com.zoesap.borrowclient.data.bean;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyInfoBean {

    /**
     * code : 10000
     * data : {"id":274}
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
         * id : 274
         */

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

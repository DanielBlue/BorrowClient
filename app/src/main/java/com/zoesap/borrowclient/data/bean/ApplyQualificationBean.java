package com.zoesap.borrowclient.data.bean;

/**
 * Created by maoqi on 2017/7/26.
 */

public class ApplyQualificationBean {

    /**
     * code : 10000
     * data : {"id":294,"loan_house":1,"loan_income":"3000~5000元","loan_status":"无固定职业"}
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
         * id : 294
         * loan_house : 1
         * loan_income : 3000~5000元
         * loan_status : 无固定职业
         */

        private int id;
        private int loan_house;
        private String loan_income;
        private String loan_status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLoan_house() {
            return loan_house;
        }

        public void setLoan_house(int loan_house) {
            this.loan_house = loan_house;
        }

        public String getLoan_income() {
            return loan_income;
        }

        public void setLoan_income(String loan_income) {
            this.loan_income = loan_income;
        }

        public String getLoan_status() {
            return loan_status;
        }

        public void setLoan_status(String loan_status) {
            this.loan_status = loan_status;
        }
    }
}

package com.zoesap.borrowclient.data.bean;

/**
 * Created by maoqi on 2017/7/20.
 */

public class LoanDetailBean {

    /**
     * code : 10000
     * info : success
     * data : {"id":"4","loan_title":"赎楼贷","loan_alimit":"1000","loan_ilimit":"1","loan_ftime":"3天","loan_adeadline":"3","loan_ideadline":"1","loan_for":"<p>\r\n\t<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">已在门店签订房产交易合同；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">房产无纠纷、无查封；客户资信良好、无被诉讼执行；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">资金用途指定代为偿还银行按揭或其他抵押贷款、还款来源指定为买方购房款或房产再按揭抵押贷款。<\/span> \r\n<\/p>","loan_data":"<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">1.借款人夫妻双方身份证、户口薄、婚姻证明<\/span><br />\r\n<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">2.买卖双方征信良好<\/span><br />\r\n<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">3.房屋买卖合同<\/span><br />\r\n<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">4.房产两证（或不动产证）<\/span><br />\r\n<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">5.房屋产权调查表<\/span><br />\r\n<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">6.卖方全套委托公证，证件相符公证，如无法办理的收原件<\/span><br />\r\n<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">7.买方公证，买方按揭批复<\/span><br />\r\n<span style=\"font-family:'Microsoft YaHei';font-size:14px;\">8.已缴纳首付款或定金<\/span><br />\r\n<div>\r\n\t<br />\r\n<\/div>","loan_apr":"2.00","loan_type":"抵押贷","company":"好借贷","count":"246","type_id":"2"}
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
         * id : 4
         * loan_title : 赎楼贷
         * loan_alimit : 1000
         * loan_ilimit : 1
         * loan_ftime : 3天
         * loan_adeadline : 3
         * loan_ideadline : 1
         * loan_for : <p>
         <span style="font-family:'Microsoft YaHei';font-size:14px;">已在门店签订房产交易合同；</span>
         </p>
         <p>
         <span style="font-family:'Microsoft YaHei';font-size:14px;">房产无纠纷、无查封；客户资信良好、无被诉讼执行；</span>
         </p>
         <p>
         <span style="font-family:'Microsoft YaHei';font-size:14px;">资金用途指定代为偿还银行按揭或其他抵押贷款、还款来源指定为买方购房款或房产再按揭抵押贷款。</span>
         </p>
         * loan_data : <span style="font-family:'Microsoft YaHei';font-size:14px;">1.借款人夫妻双方身份证、户口薄、婚姻证明</span><br />
         <span style="font-family:'Microsoft YaHei';font-size:14px;">2.买卖双方征信良好</span><br />
         <span style="font-family:'Microsoft YaHei';font-size:14px;">3.房屋买卖合同</span><br />
         <span style="font-family:'Microsoft YaHei';font-size:14px;">4.房产两证（或不动产证）</span><br />
         <span style="font-family:'Microsoft YaHei';font-size:14px;">5.房屋产权调查表</span><br />
         <span style="font-family:'Microsoft YaHei';font-size:14px;">6.卖方全套委托公证，证件相符公证，如无法办理的收原件</span><br />
         <span style="font-family:'Microsoft YaHei';font-size:14px;">7.买方公证，买方按揭批复</span><br />
         <span style="font-family:'Microsoft YaHei';font-size:14px;">8.已缴纳首付款或定金</span><br />
         <div>
         <br />
         </div>
         * loan_apr : 2.00
         * loan_type : 抵押贷
         * company : 好借贷
         * count : 246
         * type_id : 2
         */

        private String id;
        private String loan_title;
        private String loan_alimit;
        private String loan_ilimit;
        private String loan_ftime;
        private String loan_adeadline;
        private String loan_ideadline;
        private String loan_for;
        private String loan_data;
        private String loan_apr;
        private String loan_type;
        private String company;
        private String count;
        private String type_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoan_title() {
            return loan_title;
        }

        public void setLoan_title(String loan_title) {
            this.loan_title = loan_title;
        }

        public String getLoan_alimit() {
            return loan_alimit;
        }

        public void setLoan_alimit(String loan_alimit) {
            this.loan_alimit = loan_alimit;
        }

        public String getLoan_ilimit() {
            return loan_ilimit;
        }

        public void setLoan_ilimit(String loan_ilimit) {
            this.loan_ilimit = loan_ilimit;
        }

        public String getLoan_ftime() {
            return loan_ftime;
        }

        public void setLoan_ftime(String loan_ftime) {
            this.loan_ftime = loan_ftime;
        }

        public String getLoan_adeadline() {
            return loan_adeadline;
        }

        public void setLoan_adeadline(String loan_adeadline) {
            this.loan_adeadline = loan_adeadline;
        }

        public String getLoan_ideadline() {
            return loan_ideadline;
        }

        public void setLoan_ideadline(String loan_ideadline) {
            this.loan_ideadline = loan_ideadline;
        }

        public String getLoan_for() {
            return loan_for;
        }

        public void setLoan_for(String loan_for) {
            this.loan_for = loan_for;
        }

        public String getLoan_data() {
            return loan_data;
        }

        public void setLoan_data(String loan_data) {
            this.loan_data = loan_data;
        }

        public String getLoan_apr() {
            return loan_apr;
        }

        public void setLoan_apr(String loan_apr) {
            this.loan_apr = loan_apr;
        }

        public String getLoan_type() {
            return loan_type;
        }

        public void setLoan_type(String loan_type) {
            this.loan_type = loan_type;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }
    }
}

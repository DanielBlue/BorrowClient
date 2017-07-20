package com.zoesap.borrowclient.data.bean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/19.
 */

public class LoanListItemBean {

    /**
     * code : 10000
     * info : success
     * data : {"list":[{"count":"246","organ_short":"好借贷","ico":"/Update/image/20170310/20170310104645_89303.png","id":"ADs0O00","loan_type":"抵押贷","loan_title":"赎楼贷","loan_alimit":"1000","loan_ilimit":"1","loan_ftime":"3天","loan_ideadline":"1","loan_adeadline":"3","loan_apr":"2.00","loan_mageapr":"0.00","company":"好借贷","type_id":"2"},{"count":"120","organ_short":"好借贷","ico":"/Update/image/20170310/20170310104645_89303.png","id":"ADo0O00","loan_type":"抵押贷","loan_title":"尾款贷","loan_alimit":"1000","loan_ilimit":"1","loan_ftime":"3天","loan_ideadline":"0","loan_adeadline":"1","loan_apr":"2.00","loan_mageapr":"0.00","company":"好借贷","type_id":"2"},{"count":"40","organ_short":"好借贷","ico":"/Update/image/20170310/20170310104645_89303.png","id":"ADk0O00","loan_type":"抵押贷","loan_title":"房易贷","loan_alimit":"1000","loan_ilimit":"1","loan_ftime":"3天","loan_ideadline":"1","loan_adeadline":"6","loan_apr":"1.30","loan_mageapr":"0.00","company":"好借贷","type_id":"2"},{"count":"15","organ_short":"银行机构","ico":"","id":"ADg0O00","loan_type":"抵押贷","loan_title":"抵押贷A","loan_alimit":"300","loan_ilimit":"0","loan_ftime":"20天","loan_ideadline":"12","loan_adeadline":"127","loan_apr":"0.47","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"0","organ_short":"银行机构","ico":"","id":"ADc0O00","loan_type":"抵押贷","loan_title":"抵押贷B","loan_alimit":"300","loan_ilimit":"1","loan_ftime":"30天","loan_ideadline":"1","loan_adeadline":"127","loan_apr":"0.53","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"2","organ_short":"银行机构","ico":"","id":"ADY0O00","loan_type":"抵押贷","loan_title":"抵押贷C","loan_alimit":"300","loan_ilimit":"1","loan_ftime":"20天","loan_ideadline":"1","loan_adeadline":"127","loan_apr":"0.51","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"1","organ_short":"银行机构","ico":"","id":"AD5WNA0O000O00","loan_type":"抵押贷","loan_title":"抵押贷D","loan_alimit":"500","loan_ilimit":"1","loan_ftime":"7天","loan_ideadline":"1","loan_adeadline":"36","loan_apr":"0.81","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"56","organ_short":"银行机构","ico":"","id":"AD5WNQ0O000O00","loan_type":"信用贷","loan_title":"信用消费贷A","loan_alimit":"20","loan_ilimit":"5","loan_ftime":"7天","loan_ideadline":"6","loan_adeadline":"24","loan_apr":"0.90","loan_mageapr":"0.00","company":"银行机构","type_id":"1"},{"count":"36","organ_short":"银行机构","ico":"","id":"AD5WNg0O000O00","loan_type":"信用贷","loan_title":"信用消费贷B","loan_alimit":"50","loan_ilimit":"5","loan_ftime":"1天","loan_ideadline":"12","loan_adeadline":"48","loan_apr":"1.89","loan_mageapr":"0.00","company":"银行机构","type_id":"1"},{"count":"7","organ_short":"银行机构","ico":"","id":"AD5WNw0O000O00","loan_type":"信用贷","loan_title":"信用消费贷C","loan_alimit":"100","loan_ilimit":"1","loan_ftime":"7天","loan_ideadline":"24","loan_adeadline":"60","loan_apr":"1.80","loan_mageapr":"0.00","company":"银行机构","type_id":"1"}],"pagetotal":2}
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
         * list : [{"count":"246","organ_short":"好借贷","ico":"/Update/image/20170310/20170310104645_89303.png","id":"ADs0O00","loan_type":"抵押贷","loan_title":"赎楼贷","loan_alimit":"1000","loan_ilimit":"1","loan_ftime":"3天","loan_ideadline":"1","loan_adeadline":"3","loan_apr":"2.00","loan_mageapr":"0.00","company":"好借贷","type_id":"2"},{"count":"120","organ_short":"好借贷","ico":"/Update/image/20170310/20170310104645_89303.png","id":"ADo0O00","loan_type":"抵押贷","loan_title":"尾款贷","loan_alimit":"1000","loan_ilimit":"1","loan_ftime":"3天","loan_ideadline":"0","loan_adeadline":"1","loan_apr":"2.00","loan_mageapr":"0.00","company":"好借贷","type_id":"2"},{"count":"40","organ_short":"好借贷","ico":"/Update/image/20170310/20170310104645_89303.png","id":"ADk0O00","loan_type":"抵押贷","loan_title":"房易贷","loan_alimit":"1000","loan_ilimit":"1","loan_ftime":"3天","loan_ideadline":"1","loan_adeadline":"6","loan_apr":"1.30","loan_mageapr":"0.00","company":"好借贷","type_id":"2"},{"count":"15","organ_short":"银行机构","ico":"","id":"ADg0O00","loan_type":"抵押贷","loan_title":"抵押贷A","loan_alimit":"300","loan_ilimit":"0","loan_ftime":"20天","loan_ideadline":"12","loan_adeadline":"127","loan_apr":"0.47","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"0","organ_short":"银行机构","ico":"","id":"ADc0O00","loan_type":"抵押贷","loan_title":"抵押贷B","loan_alimit":"300","loan_ilimit":"1","loan_ftime":"30天","loan_ideadline":"1","loan_adeadline":"127","loan_apr":"0.53","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"2","organ_short":"银行机构","ico":"","id":"ADY0O00","loan_type":"抵押贷","loan_title":"抵押贷C","loan_alimit":"300","loan_ilimit":"1","loan_ftime":"20天","loan_ideadline":"1","loan_adeadline":"127","loan_apr":"0.51","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"1","organ_short":"银行机构","ico":"","id":"AD5WNA0O000O00","loan_type":"抵押贷","loan_title":"抵押贷D","loan_alimit":"500","loan_ilimit":"1","loan_ftime":"7天","loan_ideadline":"1","loan_adeadline":"36","loan_apr":"0.81","loan_mageapr":"0.00","company":"银行机构","type_id":"2"},{"count":"56","organ_short":"银行机构","ico":"","id":"AD5WNQ0O000O00","loan_type":"信用贷","loan_title":"信用消费贷A","loan_alimit":"20","loan_ilimit":"5","loan_ftime":"7天","loan_ideadline":"6","loan_adeadline":"24","loan_apr":"0.90","loan_mageapr":"0.00","company":"银行机构","type_id":"1"},{"count":"36","organ_short":"银行机构","ico":"","id":"AD5WNg0O000O00","loan_type":"信用贷","loan_title":"信用消费贷B","loan_alimit":"50","loan_ilimit":"5","loan_ftime":"1天","loan_ideadline":"12","loan_adeadline":"48","loan_apr":"1.89","loan_mageapr":"0.00","company":"银行机构","type_id":"1"},{"count":"7","organ_short":"银行机构","ico":"","id":"AD5WNw0O000O00","loan_type":"信用贷","loan_title":"信用消费贷C","loan_alimit":"100","loan_ilimit":"1","loan_ftime":"7天","loan_ideadline":"24","loan_adeadline":"60","loan_apr":"1.80","loan_mageapr":"0.00","company":"银行机构","type_id":"1"}]
         * pagetotal : 2
         */

        private int pagetotal;
        private List<ListBean> list;

        public int getPagetotal() {
            return pagetotal;
        }

        public void setPagetotal(int pagetotal) {
            this.pagetotal = pagetotal;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * count : 246
             * organ_short : 好借贷
             * ico : /Update/image/20170310/20170310104645_89303.png
             * id : ADs0O00
             * loan_type : 抵押贷
             * loan_title : 赎楼贷
             * loan_alimit : 1000
             * loan_ilimit : 1
             * loan_ftime : 3天
             * loan_ideadline : 1
             * loan_adeadline : 3
             * loan_apr : 2.00
             * loan_mageapr : 0.00
             * company : 好借贷
             * type_id : 2
             */

            private String count;
            private String organ_short;
            private String ico;
            private String id;
            private String loan_type;
            private String loan_title;
            private String loan_alimit;
            private String loan_ilimit;
            private String loan_ftime;
            private String loan_ideadline;
            private String loan_adeadline;
            private String loan_apr;
            private String loan_mageapr;
            private String company;
            private String type_id;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getOrgan_short() {
                return organ_short;
            }

            public void setOrgan_short(String organ_short) {
                this.organ_short = organ_short;
            }

            public String getIco() {
                return ico;
            }

            public void setIco(String ico) {
                this.ico = ico;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLoan_type() {
                return loan_type;
            }

            public void setLoan_type(String loan_type) {
                this.loan_type = loan_type;
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

            public String getLoan_ideadline() {
                return loan_ideadline;
            }

            public void setLoan_ideadline(String loan_ideadline) {
                this.loan_ideadline = loan_ideadline;
            }

            public String getLoan_adeadline() {
                return loan_adeadline;
            }

            public void setLoan_adeadline(String loan_adeadline) {
                this.loan_adeadline = loan_adeadline;
            }

            public String getLoan_apr() {
                return loan_apr;
            }

            public void setLoan_apr(String loan_apr) {
                this.loan_apr = loan_apr;
            }

            public String getLoan_mageapr() {
                return loan_mageapr;
            }

            public void setLoan_mageapr(String loan_mageapr) {
                this.loan_mageapr = loan_mageapr;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }
        }
    }
}

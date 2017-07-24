package com.zoesap.borrowclient.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.adapter.AdapterContract;

import java.util.List;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanBean {

    /**
     * code : 10000
     * data : {"list":[{"apply_money":"5","apply_state":"0","apply_time":"2017-05-44 16:02:44","company":"建设银行","ico":"/Update/image/20161122/20161122151425_39568.png","id":"AD1WMgI8","loan_apr":"5.90","loan_id":"19","loan_title":"揭贷款","organ_short":"建设银行","state":"0","tel":"400-186-9018"},{"apply_money":"5","apply_state":"0","apply_time":"2017-05-22 16:00:22","company":"建设银行","ico":"/Update/image/20161122/20161122151425_39568.png","id":"AD1WMgI9","loan_apr":"5.90","loan_id":"19","loan_title":"揭贷款","organ_short":"建设银行","state":"0","tel":"400-186-9018"},{"apply_money":"5","apply_state":"0","apply_time":"2017-05-05 15:55:05","company":"建设银行","ico":"/Update/image/20161122/20161122151425_39568.png","id":"AD1WMgIy","loan_apr":"5.90","loan_id":"19","loan_title":"揭贷款","organ_short":"建设银行","state":"0","tel":"400-186-9018"},{"apply_money":"5","apply_state":"0","apply_time":"2017-05-31 15:54:31","company":"建设银行","ico":"/Update/image/20161122/20161122151425_39568.png","id":"AD1WMgIz","loan_apr":"5.90","loan_id":"19","loan_title":"揭贷款","organ_short":"建设银行","state":"0","tel":"400-186-9018"},{"apply_money":"5","apply_state":"0","apply_time":"2017-04-43 16:49:43","company":"好借贷","ico":"/Update/image/20161130/20161130031130_35241.png","id":"AD1WMQI2","loan_apr":"3.00","loan_id":"16","loan_title":"三个","organ_short":"好借贷","state":"0","tel":"400-186-9018"},{"apply_money":"5","apply_state":"0","apply_time":"2017-04-22 16:14:22","company":"建设银行","ico":"/Update/image/20161122/20161122151425_39568.png","id":"AD1WMQI3","loan_apr":"5.90","loan_id":"19","loan_title":"揭贷款","organ_short":"建设银行","state":"0","tel":"400-186-9018"}],"recommend":[{"company":"建设银行","ico":"/Update/image/20161122/20161122151425_39568.png","id":"AD5WPQ0O000O00","loan_adeadline":"127","loan_alimit":"1000","loan_apr":"5.90","loan_ftime":"5个工作日","loan_ideadline":"12","loan_ilimit":"1","loan_mageapr":"0.00","loan_title":"揭贷款","loan_type":"购房贷款","organ_short":"建设银行","type_id":"2"},{"company":"建设银行","ico":"/Update/image/20161122/20161122151425_39568.png","id":"AD1WNA0O000O00","loan_adeadline":"36","loan_alimit":"100","loan_apr":"0.50","loan_ftime":"5个工作日","loan_ideadline":"12","loan_ilimit":"5","loan_mageapr":"0.44","loan_title":"购车贷款","loan_type":"购房贷款","organ_short":"建设银行","type_id":"2"},{"company":"好借贷","ico":"/Update/image/20161130/20161130031130_35241.png","id":"AD1WNQ0O000O00","loan_adeadline":"36","loan_alimit":"20","loan_apr":"2.48","loan_ftime":"3个工作日","loan_ideadline":"24","loan_ilimit":"2","loan_mageapr":"0.00","loan_title":"业主贷","loan_type":"快速贷款","organ_short":"好借贷","type_id":"1"}]}
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
        private List<ListBean> list;
        private List<RecommendBean> recommend;
        private EmptyBean emptyBean;
        private RecommendHeaderBean recommendHeaderBean;

        public RecommendHeaderBean getRecommendHeaderBean() {
            return recommendHeaderBean;
        }

        public void setRecommendHeaderBean(RecommendHeaderBean recommendHeaderBean) {
            this.recommendHeaderBean = recommendHeaderBean;
        }

        public EmptyBean getEmptyBean() {
            return emptyBean;
        }

        public void setEmptyBean(EmptyBean emptyBean) {
            this.emptyBean = emptyBean;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public static class ListBean implements MultiItemEntity{
            /**
             * apply_money : 5
             * apply_state : 0
             * apply_time : 2017-05-44 16:02:44
             * company : 建设银行
             * ico : /Update/image/20161122/20161122151425_39568.png
             * id : AD1WMgI8
             * loan_apr : 5.90
             * loan_id : 19
             * loan_title : 揭贷款
             * organ_short : 建设银行
             * state : 0
             * tel : 400-186-9018
             */

            private String apply_money;
            private String apply_state;
            private String apply_time;
            private String company;
            private String ico;
            private String id;
            private String loan_apr;
            private String loan_id;
            private String loan_title;
            private String organ_short;
            private String state;
            private String tel;

            public String getApply_money() {
                return apply_money;
            }

            public void setApply_money(String apply_money) {
                this.apply_money = apply_money;
            }

            public String getApply_state() {
                return apply_state;
            }

            public void setApply_state(String apply_state) {
                this.apply_state = apply_state;
            }

            public String getApply_time() {
                return apply_time;
            }

            public void setApply_time(String apply_time) {
                this.apply_time = apply_time;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
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

            public String getLoan_apr() {
                return loan_apr;
            }

            public void setLoan_apr(String loan_apr) {
                this.loan_apr = loan_apr;
            }

            public String getLoan_id() {
                return loan_id;
            }

            public void setLoan_id(String loan_id) {
                this.loan_id = loan_id;
            }

            public String getLoan_title() {
                return loan_title;
            }

            public void setLoan_title(String loan_title) {
                this.loan_title = loan_title;
            }

            public String getOrgan_short() {
                return organ_short;
            }

            public void setOrgan_short(String organ_short) {
                this.organ_short = organ_short;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            @Override
            public int getItemType() {
                return AdapterContract.MyLoanMultiItem.LOAN_LIST;
            }
        }

        public static class EmptyBean implements MultiItemEntity{

            @Override
            public int getItemType() {
                return AdapterContract.MyLoanMultiItem.LOAN_LIST_EMPTY;
            }
        }

        public static class RecommendHeaderBean implements MultiItemEntity{
            @Override
            public int getItemType() {
                return AdapterContract.MyLoanMultiItem.RECOMMEND_LOAN_LIST_HEADER;
            }
        }

        public static class RecommendBean implements MultiItemEntity{
            public RecommendBean(String loan_adeadline, String loan_alimit, String loan_apr, String loan_ftime, String loan_ideadline, String loan_ilimit, String loan_title) {
                this.loan_adeadline = loan_adeadline;
                this.loan_alimit = loan_alimit;
                this.loan_apr = loan_apr;
                this.loan_ftime = loan_ftime;
                this.loan_ideadline = loan_ideadline;
                this.loan_ilimit = loan_ilimit;
                this.loan_apr = loan_apr;
                this.loan_title = loan_title;
            }

            /**
             * company : 建设银行
             * ico : /Update/image/20161122/20161122151425_39568.png
             * id : AD5WPQ0O000O00
             * loan_adeadline : 127
             * loan_alimit : 1000
             * loan_apr : 5.90
             * loan_ftime : 5个工作日
             * loan_ideadline : 12
             * loan_ilimit : 1
             * loan_mageapr : 0.00
             * loan_title : 揭贷款
             * loan_type : 购房贷款
             * organ_short : 建设银行
             * type_id : 2
             */



            private String company;
            private String ico;
            private String id;
            private String loan_adeadline;
            private String loan_alimit;
            private String loan_apr;
            private String loan_ftime;
            private String loan_ideadline;
            private String loan_ilimit;
            private String loan_mageapr;
            private String loan_title;
            private String loan_type;
            private String organ_short;
            private String type_id;

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
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

            public String getLoan_adeadline() {
                return loan_adeadline;
            }

            public void setLoan_adeadline(String loan_adeadline) {
                this.loan_adeadline = loan_adeadline;
            }

            public String getLoan_alimit() {
                return loan_alimit;
            }

            public void setLoan_alimit(String loan_alimit) {
                this.loan_alimit = loan_alimit;
            }

            public String getLoan_apr() {
                return loan_apr;
            }

            public void setLoan_apr(String loan_apr) {
                this.loan_apr = loan_apr;
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

            public String getLoan_ilimit() {
                return loan_ilimit;
            }

            public void setLoan_ilimit(String loan_ilimit) {
                this.loan_ilimit = loan_ilimit;
            }

            public String getLoan_mageapr() {
                return loan_mageapr;
            }

            public void setLoan_mageapr(String loan_mageapr) {
                this.loan_mageapr = loan_mageapr;
            }

            public String getLoan_title() {
                return loan_title;
            }

            public void setLoan_title(String loan_title) {
                this.loan_title = loan_title;
            }

            public String getLoan_type() {
                return loan_type;
            }

            public void setLoan_type(String loan_type) {
                this.loan_type = loan_type;
            }

            public String getOrgan_short() {
                return organ_short;
            }

            public void setOrgan_short(String organ_short) {
                this.organ_short = organ_short;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            @Override
            public int getItemType() {
                return AdapterContract.MyLoanMultiItem.RECOMMEND_LOAN_LIST;
            }
        }
    }
}

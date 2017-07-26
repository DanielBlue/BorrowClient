package com.zoesap.borrowclient.data.bean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/24.
 */

public class BaseBeanWrapper<T> {


    /**
     * code : 10000
     * data : []
     * info : 取消成功
     * location :
     */

    private int code;
    private String info;
    private String location;
    private List<T> data;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

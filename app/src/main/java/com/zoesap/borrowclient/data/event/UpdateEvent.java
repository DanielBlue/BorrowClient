package com.zoesap.borrowclient.data.event;

/**
 * Created by maoqi on 2017/8/4.
 */

public class UpdateEvent {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UpdateEvent(String url) {
        this.url = url;
    }
}

package com.example.nasa.domain.model;

import com.google.gson.annotations.SerializedName;

public class APOD {

    @SerializedName("date")
    private String date;
    @SerializedName("explanation")
    private String hdurl;
    @SerializedName("service_version")
    private String serviceVersion;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

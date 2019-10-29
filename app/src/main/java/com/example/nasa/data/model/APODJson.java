package com.example.nasa.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class APODJson {

    @PrimaryKey
    @ColumnInfo(name = "date")
    @SerializedName("date")
    @NonNull
    private String mDate;

    @ColumnInfo(name = "explanation")
    @SerializedName("explanation")
    @NonNull
    private String mExplanation;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @NonNull
    private String mTitle;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @NonNull
    private String mUrl;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getExplanation() {
        return mExplanation;
    }

    public void setExplanation(String explanation) {
        mExplanation = explanation;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
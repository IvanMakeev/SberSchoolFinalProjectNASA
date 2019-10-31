package com.example.nasa.data.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class APODJson {

    @SerializedName("date")
    @NonNull
    private String mDate;

    @SerializedName("explanation")
    @NonNull
    private String mExplanation;

    @SerializedName("title")
    @NonNull
    private String mTitle;

    @SerializedName("url")
    @NonNull
    private String mUrl;


    public APODJson(@NonNull String date, @NonNull String explanation, @NonNull String title, @NonNull String url) {
        mDate = date;
        mExplanation = explanation;
        mTitle = title;
        mUrl = url;
    }

    @NotNull
    public String getDate() {
        return mDate;
    }

    @NotNull
    public String getExplanation() {
        return mExplanation;
    }

    @NotNull
    public String getTitle() {
        return mTitle;
    }

    @NotNull
    public String getUrl() {
        return mUrl;
    }
}
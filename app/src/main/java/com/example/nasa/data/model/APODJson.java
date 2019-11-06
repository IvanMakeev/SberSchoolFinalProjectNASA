package com.example.nasa.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public final class APODJson {

    @SerializedName("date")
    @NonNull
    private final String mDate;

    @SerializedName("explanation")
    @NonNull
    private final String mExplanation;

    @SerializedName("title")
    @NonNull
    private final String mTitle;

    @SerializedName("url")
    @NonNull
    private final String mUrl;

    @SerializedName("copyright")
    @NonNull
    private final String mCopyright;


    public APODJson(@NonNull String date, @NonNull String explanation, @NonNull String title, @NonNull String url, @Nullable String copyright) {
        mDate = date;
        mExplanation = explanation;
        mTitle = title;
        mUrl = url;
        mCopyright = copyright != null ? copyright : "";
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

    @NonNull
    public String getCopyright() {
        return mCopyright;
    }
}
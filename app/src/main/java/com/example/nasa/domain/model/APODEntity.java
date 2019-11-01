package com.example.nasa.domain.model;


import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class APODEntity {

    @NonNull
    private final String mDate;
    @NonNull
    private final String mExplanation;
    @NonNull
    private final String mTitle;
    @NonNull
    private final String mUrl;
    @NonNull
    private final String mCopyright;

    public APODEntity(@NotNull String date, @NotNull String explanation, @NotNull String title, @NotNull String url, @NonNull String copyright) {
        mDate = date;
        mExplanation = explanation;
        mTitle = title;
        mUrl = url;
        mCopyright = copyright;
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

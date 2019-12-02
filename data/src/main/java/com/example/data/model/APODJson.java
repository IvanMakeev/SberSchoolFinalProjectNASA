package com.example.data.model;


import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Pojo class для работы с Json
 */
public final class APODJson {

    @SerializedName("date")
    @NotNull
    private final String mDate;

    @SerializedName("explanation")
    @NotNull
    private final String mExplanation;

    @SerializedName("title")
    @NotNull
    private final String mTitle;

    @SerializedName("url")
    @NotNull
    private final String mUrl;

    @SerializedName("copyright")
    @NotNull
    private final String mCopyright;


    public APODJson(@NotNull String date,
                    @NotNull String explanation,
                    @NotNull String title,
                    @NotNull String url,
                    @Nullable String copyright) {
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

    @NotNull
    public String getCopyright() {
        return mCopyright;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APODJson apodJson = (APODJson) o;
        return mDate.equals(apodJson.mDate) &&
                mExplanation.equals(apodJson.mExplanation) &&
                mTitle.equals(apodJson.mTitle) &&
                mUrl.equals(apodJson.mUrl) &&
                mCopyright.equals(apodJson.mCopyright);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDate, mExplanation, mTitle, mUrl, mCopyright);
    }
}
package com.example.domain.model;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class APODEntity {

    @NotNull
    private final String mDate;
    @NotNull
    private final String mExplanation;
    @NotNull
    private final String mTitle;
    @NotNull
    private final String mUrl;
    @NotNull
    private final String mCopyright;

    public APODEntity(@NotNull String date, @NotNull String explanation, @NotNull String title, @NotNull String url, @NotNull String copyright) {
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

    @NotNull
    public String getCopyright() {
        return mCopyright;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APODEntity that = (APODEntity) o;
        return mDate.equals(that.mDate) &&
                mExplanation.equals(that.mExplanation) &&
                mTitle.equals(that.mTitle) &&
                mUrl.equals(that.mUrl) &&
                mCopyright.equals(that.mCopyright);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDate, mExplanation, mTitle, mUrl, mCopyright);
    }
}

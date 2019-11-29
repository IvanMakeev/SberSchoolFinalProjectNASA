package com.example.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Entity
public final class APODRoom {

    @PrimaryKey
    @ColumnInfo(name = "date")
    @NotNull
    private final String mDate;

    @ColumnInfo(name = "explanation")
    @NotNull
    private final String mExplanation;

    @ColumnInfo(name = "title")
    @NotNull
    private final String mTitle;

    @ColumnInfo(name = "url")
    @NotNull
    private final String mUrl;

    @ColumnInfo(name = "copyright")
    @NotNull
    private final String mCopyright;


    public APODRoom(@NotNull String date,
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
        APODRoom apodRoom = (APODRoom) o;
        return mDate.equals(apodRoom.mDate) &&
                mExplanation.equals(apodRoom.mExplanation) &&
                mTitle.equals(apodRoom.mTitle) &&
                mUrl.equals(apodRoom.mUrl) &&
                mCopyright.equals(apodRoom.mCopyright);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDate, mExplanation, mTitle, mUrl, mCopyright);
    }
}
package com.example.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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


    public APODRoom(@NotNull String date, @NotNull String explanation, @NotNull String title, @NotNull String url, @Nullable String copyright) {
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
}
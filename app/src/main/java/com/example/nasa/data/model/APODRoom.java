package com.example.nasa.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public final class APODRoom {

    @PrimaryKey
    @ColumnInfo(name = "date")
    @NonNull
    private final String mDate;

    @ColumnInfo(name = "explanation")
    @NonNull
    private final String mExplanation;

    @ColumnInfo(name = "title")
    @NonNull
    private final String mTitle;

    @ColumnInfo(name = "url")
    @NonNull
    private final String mUrl;

    @ColumnInfo(name = "copyright")
    @NonNull
    private final String mCopyright;


    public APODRoom(@NonNull String date, @NonNull String explanation, @NonNull String title, @NonNull String url, @Nullable String copyright) {
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
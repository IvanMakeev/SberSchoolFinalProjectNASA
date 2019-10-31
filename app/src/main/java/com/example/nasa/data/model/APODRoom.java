package com.example.nasa.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class APODRoom {

    @PrimaryKey
    @ColumnInfo(name = "date")
    @NonNull
    private String mDate;

    @ColumnInfo(name = "explanation")
    @NonNull
    private String mExplanation;

    @ColumnInfo(name = "title")
    @NonNull
    private String mTitle;

    @ColumnInfo(name = "url")
    @NonNull
    private String mUrl;


    public APODRoom(@NonNull String date, @NonNull String explanation, @NonNull String title, @NonNull String url) {
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
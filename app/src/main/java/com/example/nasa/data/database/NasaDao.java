package com.example.nasa.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.nasa.data.model.APODRoom;

@Dao
public interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAstronomyPicture(APODRoom apodJson);

    @Query("select * from APODRoom where date = :date")
    APODRoom getAstronomyPicture(String date);
}

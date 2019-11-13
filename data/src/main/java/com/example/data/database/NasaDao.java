package com.example.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.data.model.APODRoom;

import java.util.List;

@Dao
public interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAstronomyPicture(APODRoom apodJson);

    @Query("select * from APODRoom where date = :date")
    APODRoom getAstronomyPicture(String date);

    @Query("select * from APODRoom")
    List<APODRoom> getAll();
}

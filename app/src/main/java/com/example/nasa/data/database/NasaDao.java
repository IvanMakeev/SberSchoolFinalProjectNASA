package com.example.nasa.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.nasa.data.model.APODJson;

import java.util.List;

@Dao
public interface NasaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAstronomyPicture(APODJson apodJson);

    @Query("select * from APODJson where date = :date")
    public APODJson getAstronomyPicture(String date);

//    @get:Query("select * from user")
//    val users: List<User>
}

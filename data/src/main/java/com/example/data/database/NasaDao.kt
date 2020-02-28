package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.APODRoom

@Dao
interface NasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstronomyPicture(apodRoom: APODRoom)

    @Query("select * from APODRoom where date = :date")
    suspend fun getAstronomyPicture(date: String): APODRoom?

    @Query("select * from APODRoom")
    suspend fun getAll(): List<APODRoom>
}
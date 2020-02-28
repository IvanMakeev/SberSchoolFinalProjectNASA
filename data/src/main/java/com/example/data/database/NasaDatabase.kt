package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.APODRoom

@Database(entities = [APODRoom::class], version = 1)
abstract class NasaDatabase : RoomDatabase() {
    abstract val nasaDao: NasaDao
}
package com.example.nasa.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.nasa.data.model.APODRoom;

@Database(entities = {APODRoom.class}, version = 1)
public abstract class NasaDatabase extends RoomDatabase {

    abstract public NasaDao getNasaDao();
}

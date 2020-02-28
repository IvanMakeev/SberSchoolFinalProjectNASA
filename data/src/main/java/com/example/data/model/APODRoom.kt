package com.example.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Pojo class для работы с Room
 */
@Entity
data class APODRoom(
        @field:ColumnInfo(name = "date") @field:PrimaryKey val date: String,
        @field:ColumnInfo(name = "explanation") val explanation: String,
        @field:ColumnInfo(name = "title") val title: String,
        @field:ColumnInfo(name = "url") val url: String,
        @field:ColumnInfo(name = "copyright") var copyright: String?) {
    init {
        this.copyright = copyright ?: ""
    }
}
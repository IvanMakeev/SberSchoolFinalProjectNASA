package com.example.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Pojo class для работы с Json
 */
data class APODJson(
        @field:SerializedName("date") val date: String,
        @field:SerializedName("explanation") val explanation: String,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("url") val url: String,
        @field:SerializedName("copyright") var copyright: String?) {
    init {
        this.copyright = copyright ?: ""
    }
}
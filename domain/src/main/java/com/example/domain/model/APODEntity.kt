package com.example.domain.model

/**
 * Астрономическая картинка дня
 */
data class APODEntity(
        val date: String,
        val explanation: String,
        val title: String,
        val url: String ,
        val copyright: String)
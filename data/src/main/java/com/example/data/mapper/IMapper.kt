package com.example.data.mapper

/**
 * Интерфейс для маппинга данных
 * @param <E>   маппинг в entity
 * @param <D>   маппинг в data
 */
interface IMapper<E, D> {
    companion object {
        const val JSON = "json"
        const val ROOM = "room"
    }

    fun mapFromEntity(type: E): D
    fun mapToEntity(type: D): E
}
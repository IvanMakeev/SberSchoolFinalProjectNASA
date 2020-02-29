package com.example.data.mapper

import com.example.data.model.APODRoom
import com.example.domain.model.APODEntity
import javax.inject.Inject

/**
 * Реализация маппинга из room в entity и наоборот
 */
open class RoomMapper @Inject constructor() : IMapper<APODEntity, APODRoom> {
    override fun mapFromEntity(type: APODEntity): APODRoom {
        return APODRoom(
                type.date,
                type.explanation,
                type.title,
                type.url,
                type.copyright)
    }

    override fun mapToEntity(type: APODRoom): APODEntity {
        return APODEntity(
                type.date,
                type.explanation,
                type.title,
                type.url,
                type.copyright ?: "")

    }
}
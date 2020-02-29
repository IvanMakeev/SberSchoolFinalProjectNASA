package com.example.data.mapper

import com.example.data.model.APODJson
import com.example.domain.model.APODEntity
import javax.inject.Inject

/**
 * Реализация маппинга из json в entity и наоборот
 */
open class JsonMapper @Inject constructor() : IMapper<APODEntity, APODJson> {
    override fun mapFromEntity(type: APODEntity): APODJson {
        return APODJson(
                type.date,
                type.explanation,
                type.title,
                type.url,
                type.copyright)
    }

    override fun mapToEntity(type: APODJson): APODEntity {
        return APODEntity(
                type.date,
                type.explanation,
                type.title,
                type.url,
                type.copyright ?: "")

    }
}
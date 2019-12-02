package com.example.data.mapper;

import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для маппинга данных
 * @param <E>   маппинг в entity
 * @param <D>   маппинг в data
 */
public interface IMapper<E, D> {

    String JSON = "json";
    String ROOM = "room";

    @NotNull
    D mapFromEntity(@NotNull E type);

    @NotNull
    E mapToEntity(@NotNull D type);
}

package com.example.data.mapper;

import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

/**
 * Реализация маппинга из room в entity и наоборот
 */

public class RoomMapper implements IMapper<APODEntity, APODRoom> {

    @Inject
    public RoomMapper() {
    }

    @NotNull
    @Override
    public APODRoom mapFromEntity(@NotNull APODEntity type) {
        return new APODRoom(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl(),
                type.getCopyright());
    }

    @NotNull
    @Override
    public APODEntity mapToEntity(@Nullable APODRoom type) {
        APODEntity apodEntity;
        if (type != null) {
            apodEntity = new APODEntity(
                    type.getDate(),
                    type.getExplanation(),
                    type.getTitle(),
                    type.getUrl(),
                    type.getCopyright());
        } else {
            apodEntity = new APODEntity(
                    "",
                    "",
                    "",
                    "",
                    "");
        }
        return apodEntity;
    }
}

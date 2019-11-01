package com.example.nasa.data.mapper;

import com.example.nasa.data.model.APODRoom;
import com.example.nasa.domain.model.APODEntity;

import javax.inject.Inject;

public class RoomMapper implements IMapper<APODEntity, APODRoom> {

    @Inject
    RoomMapper() {
    }

    @Override
    public APODRoom mapFromEntity(APODEntity type) {
        return new APODRoom(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl(),
                type.getCopyright());
    }

    @Override
    public APODEntity mapToEntity(APODRoom type) {
        return new APODEntity(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl(),
                type.getCopyright());
    }
}

package com.example.data.mapper;

import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;

import javax.inject.Inject;

public final class RoomMapper implements IMapper<APODEntity, APODRoom> {

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

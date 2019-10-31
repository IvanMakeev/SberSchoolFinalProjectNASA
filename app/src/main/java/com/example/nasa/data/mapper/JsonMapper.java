package com.example.nasa.data.mapper;

import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.model.APODEntity;

import javax.inject.Inject;

public class JsonMapper implements IMapper<APODEntity, APODJson> {

    @Inject
    JsonMapper() {
    }

    @Override
    public APODJson mapFromEntity(APODEntity type) {
        return new APODJson(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl()
        );
    }

    @Override
    public APODEntity mapToEntity(APODJson type) {
        return new APODEntity(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl()
        );
    }
}

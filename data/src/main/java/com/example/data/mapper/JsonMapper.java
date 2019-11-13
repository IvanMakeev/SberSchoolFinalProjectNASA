package com.example.data.mapper;

import com.example.data.model.APODJson;
import com.example.domain.model.APODEntity;

import javax.inject.Inject;

public class JsonMapper implements IMapper<APODEntity, APODJson> {

    @Inject
    public JsonMapper() {
    }

    @Override
    public APODJson mapFromEntity(APODEntity type) {
        return new APODJson(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl(),
                type.getCopyright());
    }

    @Override
    public APODEntity mapToEntity(APODJson type) {
        return new APODEntity(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl(),
                type.getCopyright());
    }
}

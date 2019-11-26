package com.example.data.mapper;

import com.example.data.model.APODJson;
import com.example.domain.model.APODEntity;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class JsonMapper implements IMapper<APODEntity, APODJson> {

    @Inject
    public JsonMapper() {
    }

    @NotNull
    @Override
    public APODJson mapFromEntity(@NotNull APODEntity type) {
        return new APODJson(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl(),
                type.getCopyright());
    }

    @NotNull
    @Override
    public APODEntity mapToEntity(@NotNull APODJson type) {
        return new APODEntity(
                type.getDate(),
                type.getExplanation(),
                type.getTitle(),
                type.getUrl(),
                type.getCopyright());
    }
}

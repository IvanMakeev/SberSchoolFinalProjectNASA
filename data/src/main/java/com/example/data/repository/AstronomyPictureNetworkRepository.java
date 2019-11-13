package com.example.data.repository;

import com.example.data.api.INasaApi;
import com.example.data.mapper.IMapper;
import com.example.data.model.APODJson;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public class AstronomyPictureNetworkRepository implements IAstronomyPictureRepository {

    private final INasaApi mApi;
    private final IMapper<APODEntity, APODJson> mMapper;

    public AstronomyPictureNetworkRepository(@NotNull INasaApi api, @NotNull IMapper<APODEntity, APODJson> mapper) {
        mApi = api;
        mMapper = mapper;
    }

    @NotNull
    @Override
    public Single<APODEntity> getAstronomyPicture(@NotNull String date) {
        return mApi.getAstronomyPicture(date)
                .map(mMapper::mapToEntity);
    }

    @Override
    public void insertAstronomyPicture(APODEntity apod) {
        //do nothing
    }
}

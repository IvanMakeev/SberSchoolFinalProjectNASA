package com.example.nasa.data.repository;

import com.example.nasa.data.api.INasaApi;
import com.example.nasa.data.mapper.IMapper;
import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import io.reactivex.Single;

public final class AstronomyPictureServerRepository implements IAstronomyPictureRepository {

    private final INasaApi mApi;
    private final IMapper<APODEntity, APODJson> mMapper;

    public AstronomyPictureServerRepository(INasaApi api, IMapper<APODEntity, APODJson> mapper) {
        mApi = api;
        mMapper = mapper;
    }

    @Override
    public Single<APODEntity> getAstronomyPicture(String date) {
        return mApi.getAstronomyPicture(date)
                .map(mMapper::mapToEntity);
    }

    @Override
    public void insertAstronomyPicture(APODEntity apod) {
        //do nothing
    }
}

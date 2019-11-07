package com.example.data.repository;

import com.example.data.api.INasaApi;
import com.example.data.mapper.IMapper;
import com.example.data.model.APODJson;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

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

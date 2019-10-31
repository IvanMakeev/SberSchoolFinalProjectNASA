package com.example.nasa.data.repository;

import com.example.nasa.data.api.NasaApi;
import com.example.nasa.data.mapper.IMapper;
import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class AstronomyPictureServerRepository implements IAstronomyPictureRepository {

    @Inject
    NasaApi mApi;

    @Inject
    @Named(IMapper.JSON)
    IMapper<APODEntity, APODJson> mMapper;


    @Inject
    AstronomyPictureServerRepository() {
    }

    @Override
    public Single<APODEntity> getAstronomyPicture(String date) {
        return mApi.getAstronomyPicture(date)
                .map(apodJson -> mMapper.mapToEntity(apodJson));
    }

    @Override
    public void insertAstronomyPicture(APODEntity apod) {
        //do nothing
    }
}

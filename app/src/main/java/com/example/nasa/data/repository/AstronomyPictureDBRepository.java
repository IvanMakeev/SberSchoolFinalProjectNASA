package com.example.nasa.data.repository;

import com.example.nasa.data.database.NasaDao;
import com.example.nasa.data.mapper.IMapper;
import com.example.nasa.data.model.APODRoom;
import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class AstronomyPictureDBRepository implements IAstronomyPictureRepository {

    @Inject
    NasaDao mDao;

    @Inject
    @Named(IMapper.ROOM)
    IMapper<APODEntity, APODRoom> mMapper;

    @Inject
    AstronomyPictureDBRepository() {
    }

    @Override
    public Single<APODEntity> getAstronomyPicture(final String date) {
        return Single.fromCallable(() -> mMapper.mapToEntity(mDao.getAstronomyPicture(date)));
    }

    @Override
    public void insertAstronomyPicture(APODEntity apod) {
        mDao.insertAstronomyPicture(mMapper.mapFromEntity(apod));
    }
}

package com.example.nasa.data.repository;

import com.example.nasa.data.database.NasaDao;
import com.example.nasa.data.mapper.IMapper;
import com.example.nasa.data.model.APODRoom;
import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import io.reactivex.Single;

public final class AstronomyPictureDBRepository implements IAstronomyPictureRepository {

    private final NasaDao mDao;
    private final IMapper<APODEntity, APODRoom> mMapper;

    public AstronomyPictureDBRepository(NasaDao dao, IMapper<APODEntity, APODRoom> mapper) {
        mDao = dao;
        mMapper = mapper;
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

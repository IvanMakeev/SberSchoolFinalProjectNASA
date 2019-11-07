package com.example.data.repository;

import com.example.data.mapper.IMapper;
import com.example.data.model.APODRoom;
import com.example.data.database.NasaDao;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

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

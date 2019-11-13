package com.example.data.repository;

import com.example.data.mapper.IMapper;
import com.example.data.model.APODRoom;
import com.example.data.database.NasaDao;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public class AstronomyPictureDBRepository implements IAstronomyPictureRepository {

    private final NasaDao mDao;
    private final IMapper<APODEntity, APODRoom> mMapper;

    public AstronomyPictureDBRepository(@NotNull NasaDao dao, @NotNull IMapper<APODEntity, APODRoom> mapper) {
        mDao = dao;
        mMapper = mapper;
    }

    @NotNull
    @Override
    public Single<APODEntity> getAstronomyPicture(@NotNull final String date) {
        return Single.fromCallable(() -> mMapper.mapToEntity(mDao.getAstronomyPicture(date)));
    }

    @Override
    public void insertAstronomyPicture(@NotNull APODEntity apod) {
        mDao.insertAstronomyPicture(mMapper.mapFromEntity(apod));
    }
}

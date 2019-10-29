package com.example.nasa.data.repository;

import com.example.nasa.data.database.NasaDao;
import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class AstronomyPictureDBRepository implements IAstronomyPictureRepository {

    @Inject
    NasaDao mDao;

    @Inject
    public AstronomyPictureDBRepository() {
    }

    @Override
    public Single<APODJson> getAstronomyPicture(final String date) {
        return Single.fromCallable(new Callable<APODJson>() {
            @Override
            public APODJson call() throws Exception {
                return mDao.getAstronomyPicture(date);
            }
        });
    }

    @Override
    public void insertAstronomyPicture(APODJson apod) {
        mDao.insertAstronomyPicture(apod);
    }
}

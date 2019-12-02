package com.example.data.repository;

import com.example.data.api.NasaApi;
import com.example.data.database.NasaDao;
import com.example.data.mapper.IMapper;
import com.example.data.model.APODJson;
import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Реализация репозитория для получения и сохранения данных
 */
public class AstronomyPictureRepository implements IAstronomyPictureRepository {

    @NotNull
    private final NasaApi mApi;
    @NotNull
    private final NasaDao mDao;
    @NotNull
    private final IMapper<APODEntity, APODJson> mJsonMapper;
    @NotNull
    private final IMapper<APODEntity, APODRoom> mRoomMapper;

    /**
     * @param api        используется для работы web api
     * @param dao        используется для работы c room api
     * @param jsonMapper используется для маппинга данных из json в entity и наоборот
     * @param roomMapper используется для маппинга данных из room в entity и наоборот
     */
    public AstronomyPictureRepository(
            @NotNull NasaApi api,
            @NotNull NasaDao dao,
            @NotNull IMapper<APODEntity, APODJson> jsonMapper,
            @NotNull IMapper<APODEntity, APODRoom> roomMapper) {
        mApi = api;
        mDao = dao;
        mJsonMapper = jsonMapper;
        mRoomMapper = roomMapper;
    }

    /**
     * Получение данных
     *
     * @param date дата для которой необходимо получить данные
     * @return возвращает Single с данными для отображения пользователю
     */
    @NotNull
    @Override
    public Single<APODEntity> getAstronomyPicture(@NotNull String date) {
        return Single.fromCallable(() -> mRoomMapper.mapToEntity(mDao.getAstronomyPicture(date)))
                .flatMap(getSource(date));
    }

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    @Override
    public void insertAstronomyPicture(@NotNull APODEntity apod) {
        mDao.insertAstronomyPicture(mRoomMapper.mapFromEntity(apod));
    }

    @NotNull
    private Function<APODEntity, SingleSource<APODEntity>> getSource(@NotNull String date) {
        return apodEntity -> {
            if (isDataExist(apodEntity)) {
                return fetchFromDatabase(apodEntity);
            } else {
                return fetchFromNetwork(date);
            }
        };
    }

    private boolean isDataExist(@NotNull APODEntity apodEntity) {
        return !apodEntity.getDate().equals("");
    }

    @NotNull
    private SingleSource<APODEntity> fetchFromDatabase(@NotNull APODEntity apodEntity) {
        return Single.fromCallable(() -> apodEntity);
    }

    @NotNull
    private SingleSource<APODEntity> fetchFromNetwork(@NotNull String date) {
        return mApi.getAstronomyPicture(date)
                .map(mJsonMapper::mapToEntity)
                .doOnSuccess(apod ->
                        mDao.insertAstronomyPicture(mRoomMapper.mapFromEntity(apod)));
    }
}

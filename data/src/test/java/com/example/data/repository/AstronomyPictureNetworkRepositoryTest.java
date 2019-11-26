package com.example.data.repository;

import com.example.data.api.NasaApi;
import com.example.data.database.NasaDao;
import com.example.data.mapper.JsonMapper;
import com.example.data.mapper.RoomMapper;
import com.example.data.model.APODJson;
import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AstronomyPictureNetworkRepositoryTest {

    private static final String TEST_DATE = "2019-10-10";

    private AstronomyPictureRepository mRepository;
    private APODEntity mEnteredEntity;
    private APODJson mEnteredJson;
    private APODRoom mEnteredRoom;

    @Before
    public void setUp() {
        mEnteredEntity = new APODEntity(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        mEnteredJson = new APODJson(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        mEnteredRoom = new APODRoom(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );


        NasaApi api = mock(NasaApi.class);
        when(api.getAstronomyPicture(TEST_DATE)).thenReturn(Single.fromCallable(() ->
                mEnteredJson
        ));

        NasaDao dao = mock(NasaDao.class);
        when(dao.getAstronomyPicture(TEST_DATE)).thenReturn(mEnteredRoom);

        JsonMapper jsonMapper = mock(JsonMapper.class);
        when(jsonMapper.mapToEntity(mEnteredJson)).thenReturn(mEnteredEntity);

        RoomMapper roomMapper = mock(RoomMapper.class);
        when(roomMapper.mapToEntity(mEnteredRoom)).thenReturn(mEnteredEntity);

        mRepository = new AstronomyPictureRepository(api, dao, jsonMapper, roomMapper);
    }

    @Test
    public void testGetAstronomyPicture() {
        TestObserver<APODEntity> observer = mRepository.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
    }
}
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AstronomyPictureRepositoryTest {

    private static final String TEST_DATE = "2019-10-10";

    private APODEntity mEnteredEntity;
    private APODEntity mEnteredEmptyEntity;
    private APODJson mEnteredJson;
    private APODRoom mEnteredRoom;
    private NasaApi mApi;
    private NasaDao mDao;
    private JsonMapper mJsonMapper;
    private RoomMapper mRoomMapper;

    @Before
    public void setUp() {
        mEnteredEntity = new APODEntity(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        mEnteredEmptyEntity = new APODEntity(
                "",
                "",
                "",
                "",
                ""
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


        mApi = mock(NasaApi.class);
        mDao = mock(NasaDao.class);
        mJsonMapper = mock(JsonMapper.class);
        mRoomMapper = mock(RoomMapper.class);

    }

    @Test
    public void testGetAstronomyPictureFetchDao() {
        when(mDao.getAstronomyPicture(TEST_DATE)).thenReturn(mEnteredRoom);
        when(mRoomMapper.mapToEntity(mEnteredRoom)).thenReturn(mEnteredEntity);
        AstronomyPictureRepository repository = new AstronomyPictureRepository(mApi, mDao, mJsonMapper, mRoomMapper);

        TestObserver<APODEntity> observer = repository.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
        observer.dispose();

        verify(mDao).getAstronomyPicture(TEST_DATE);
        verifyZeroInteractions(mDao);
        verifyZeroInteractions(mApi);

    }

    @Test
    public void testGetAstronomyPictureFetchNetworkApi() {
        when(mDao.getAstronomyPicture(TEST_DATE)).thenReturn(mEnteredRoom);
        when(mApi.getAstronomyPicture(TEST_DATE)).thenReturn(Single.fromCallable(() -> mEnteredJson));
        when(mRoomMapper.mapToEntity(mEnteredRoom)).thenReturn(mEnteredEmptyEntity);
        when(mJsonMapper.mapToEntity(mEnteredJson)).thenReturn(mEnteredEntity);
        when(mRoomMapper.mapFromEntity(mEnteredEntity)).thenReturn(mEnteredRoom);
        AstronomyPictureRepository repository = new AstronomyPictureRepository(mApi, mDao, mJsonMapper, mRoomMapper);

        TestObserver<APODEntity> observer = repository.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
        observer.dispose();

        verify(mDao).getAstronomyPicture(TEST_DATE);
        verify(mApi).getAstronomyPicture(TEST_DATE);
        verify(mDao).insertAstronomyPicture(mEnteredRoom);

        verifyZeroInteractions(mDao);
        verifyZeroInteractions(mApi);
    }

    @Test
    public void testInsertAstronomyPictureDao(){
        when(mRoomMapper.mapFromEntity(mEnteredEntity)).thenReturn(mEnteredRoom);
        AstronomyPictureRepository repository = new AstronomyPictureRepository(mApi, mDao, mJsonMapper, mRoomMapper);

        repository.insertAstronomyPicture(mEnteredEntity);
        verify(mDao).insertAstronomyPicture(mEnteredRoom);

        verifyZeroInteractions(mDao);
    }
}
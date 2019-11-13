package com.example.data.repository;

import com.example.data.database.NasaDao;
import com.example.data.mapper.RoomMapper;
import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AstronomyPictureDBRepositoryTest {

    private static final String TEST_DATE = "2019-10-10";

    private AstronomyPictureDBRepository mDbRepository;
    private APODEntity mEnteredEntity;
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

        mEnteredRoom = new APODRoom(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        NasaDao dao = mock(NasaDao.class);
        when(dao.getAstronomyPicture(TEST_DATE)).thenReturn(mEnteredRoom);

        RoomMapper mapper = mock(RoomMapper.class);
        when(mapper.mapToEntity(mEnteredRoom)).thenReturn(mEnteredEntity);

        mDbRepository = new AstronomyPictureDBRepository(dao, mapper);
    }


    @Test
    public void testGetAstronomyPicture() {
        TestObserver<APODEntity> observer = mDbRepository.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
    }
}
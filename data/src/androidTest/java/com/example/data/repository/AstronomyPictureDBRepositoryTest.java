package com.example.data.repository;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.data.database.NasaDatabase;
import com.example.data.mapper.RoomMapper;
import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class AstronomyPictureDBRepositoryTest {

    private static final String TEST_DATE = "2019-10-10";
    private static final int SIZE_DATABASE = 1;

    private NasaDatabase db;
    private APODEntity enteredEntity;
    private AstronomyPictureDBRepository mDBRepository;
    private APODRoom enteredRoom;


    @Before
    public void setUp() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getContext(),
                NasaDatabase.class)
                .build();

        mDBRepository = new AstronomyPictureDBRepository(db.getNasaDao(), new RoomMapper());
        enteredEntity = new APODEntity(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );
        enteredRoom = new APODRoom(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );
    }


    @Test
    public void testGetAstronomyPicture() {
        mDBRepository.insertAstronomyPicture(enteredEntity);
        TestObserver<APODEntity> observer = mDBRepository.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(enteredEntity);
    }

    @Test
    public void testInsertAstronomyPicture(){
        mDBRepository.insertAstronomyPicture(enteredEntity);
        List<APODRoom> roomObjectsList = db.getNasaDao().getAll();
        APODRoom expected = roomObjectsList.get(roomObjectsList.size() - 1);

        Assert.assertEquals(SIZE_DATABASE, roomObjectsList.size());
        assertThat(enteredRoom, is(expected));
    }

    @After
    public void tearDown() {
        db.close();
    }
}
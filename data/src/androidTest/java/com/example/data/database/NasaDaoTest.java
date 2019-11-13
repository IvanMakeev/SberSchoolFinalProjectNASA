package com.example.data.database;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.data.model.APODRoom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class NasaDaoTest {

    private static final String TEST_DATE = "2019-10-10";
    private static final int SIZE_DATABASE = 1;

    private NasaDatabase db;
    private APODRoom entered;

    @Before
    public void setUp() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getContext(),
                NasaDatabase.class)
                .build();

        entered = new APODRoom(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );
    }

    @Test
    public void testInsertAstronomyPicture() {
        db.getNasaDao().insertAstronomyPicture(entered);
        List<APODRoom> roomObjectsList = db.getNasaDao().getAll();
        APODRoom expected = roomObjectsList.get(roomObjectsList.size() - 1);

        Assert.assertEquals(SIZE_DATABASE, roomObjectsList.size());
        assertThat(entered, is(expected));
    }

    @Test
    public void testGetAstronomyPicture() {
        db.getNasaDao().insertAstronomyPicture(entered);
        APODRoom expected = db.getNasaDao().getAstronomyPicture(TEST_DATE);
        assertThat(entered, is(expected));

    }

    @After
    public void tearDown() {
        db.close();
    }
}
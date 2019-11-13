package com.example.data.mapper;

import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoomMapperTest {


    private RoomMapper mMapper;
    private APODRoom mEnteredRoom;
    private APODEntity mEnteredEntity;
    private APODEntity mEnteredEmptyEntity;

    @Before
    public void setUp() {
        mMapper = new RoomMapper();
        mEnteredRoom = new APODRoom(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

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

    }

    @Test
    public void testMapFromEntity() {
        APODRoom expected = mMapper.mapFromEntity(mEnteredEntity);
        assertThat(mEnteredRoom, is(expected));
    }

    @Test
    public void testMapToEntity() {
        APODEntity expected = mMapper.mapToEntity(mEnteredRoom);
        assertThat(mEnteredEntity, is(expected));
    }

    @Test
    public void testEmptyMapToEntity() {
        APODEntity expected = mMapper.mapToEntity(null);
        assertThat(mEnteredEmptyEntity, is(expected));
    }


}
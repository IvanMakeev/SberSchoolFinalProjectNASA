package com.example.data.mapper;

import com.example.data.model.APODJson;
import com.example.domain.model.APODEntity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonMapperTest {

    private JsonMapper mMapper;
    private APODJson mEnteredJson;
    private APODEntity mEnteredEntity;

    @Before
    public void setUp() {
        mMapper = new JsonMapper();
        mEnteredJson = new APODJson(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        mEnteredEntity = new APODEntity(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );
    }

    @Test
    public void testMapFromEntity() {
        APODJson expected = mMapper.mapFromEntity(mEnteredEntity);
        assertThat(mEnteredJson, is(expected));
    }

    @Test
    public void testMapToEntity() {
        APODEntity expected = mMapper.mapToEntity(mEnteredJson);
        assertThat(mEnteredEntity, is(expected));
    }
}
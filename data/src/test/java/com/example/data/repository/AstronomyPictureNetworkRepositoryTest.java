package com.example.data.repository;

import com.example.data.api.INasaApi;
import com.example.data.mapper.JsonMapper;
import com.example.data.model.APODJson;
import com.example.domain.model.APODEntity;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AstronomyPictureNetworkRepositoryTest {

    private static final String TEST_DATE = "2019-10-10";

    private AstronomyPictureNetworkRepository mNetworkRepository;
    private APODEntity mEnteredEntity;
    private APODJson mEnteredJson;

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


        INasaApi api = mock(INasaApi.class);
        when(api.getAstronomyPicture(TEST_DATE)).thenReturn(Single.fromCallable(() ->
                mEnteredJson
        ));
        JsonMapper mapper = mock(JsonMapper.class);
        when(mapper.mapToEntity(mEnteredJson)).thenReturn(mEnteredEntity);

        mNetworkRepository = new AstronomyPictureNetworkRepository(api, mapper);
    }

    @Test
    public void testGetAstronomyPicture() {
        TestObserver<APODEntity> observer = mNetworkRepository.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
    }
}
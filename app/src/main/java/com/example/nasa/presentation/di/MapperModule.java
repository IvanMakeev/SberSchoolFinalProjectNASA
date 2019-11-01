package com.example.nasa.presentation.di;

import com.example.nasa.data.mapper.IMapper;
import com.example.nasa.data.mapper.JsonMapper;
import com.example.nasa.data.mapper.RoomMapper;
import com.example.nasa.data.model.APODJson;
import com.example.nasa.data.model.APODRoom;
import com.example.nasa.domain.model.APODEntity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class MapperModule {

    @Provides
    @Singleton
    @Named(IMapper.JSON)
    IMapper<APODEntity, APODJson> provideJsonMapper(JsonMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    @Named(IMapper.ROOM)
    IMapper<APODEntity, APODRoom> provideRoomMapper(RoomMapper mapper) {
        return mapper;
    }
}

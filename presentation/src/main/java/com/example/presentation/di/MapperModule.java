package com.example.presentation.di;


import com.example.data.mapper.IMapper;
import com.example.data.mapper.JsonMapper;
import com.example.data.mapper.RoomMapper;
import com.example.data.model.APODJson;
import com.example.data.model.APODRoom;
import com.example.domain.model.APODEntity;

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

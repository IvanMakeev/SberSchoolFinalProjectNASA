package com.example.data.mapper;

public interface IMapper<E, D> {

    String JSON = "json";
    String ROOM = "room";

    D mapFromEntity(E type);

    E mapToEntity(D type);
}

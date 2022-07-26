package com.AA.HotelAndSpa.service;

import com.AA.HotelAndSpa.model.room.Room;

import java.util.List;

public interface RoomService {
    Room save(Room room);

    List<Room> findAll();

    Room update(long id, Room updatedRoom);

    Room findById(long id);

    void deleteById(long id);
}

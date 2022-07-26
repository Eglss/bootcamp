package com.AA.HotelAndSpa.service;

import com.AA.HotelAndSpa.model.reservation.RoomReservation;
import com.AA.HotelAndSpa.model.room.Room;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    RoomReservation findById(long id);

    RoomReservation findByUserIdAndReservationId(long uid, long rid);

    Set<RoomReservation> findAllByUserId(long id);

    Set<RoomReservation> findAll();

    int calculateDays(Instant startDate, Instant endDate);

    void deleteById(long id);

    void roomReservationIdMatch(long roomId, long roomReservationId);

    RoomReservation update(long id, long rid, RoomReservation updatedRoomReservation);

    List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people);
}

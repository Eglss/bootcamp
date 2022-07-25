package com.AA.HotelAndSpa.service;

import com.AA.HotelAndSpa.model.reservation.RoomReservation;
import com.AA.HotelAndSpa.model.room.Room;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface RoomReservationService {

    RoomReservation save(RoomReservation roomReservation);

    RoomReservation findById(Long id);

    RoomReservation findByUserIdAndReservationId(Long uid, Long rid);

    Set<RoomReservation> findAllByUserId(Long id);

    Set<RoomReservation> findAll();

    int calculateDays(Instant startDate, Instant endDate);

    void deleteById(Long id);

    void roomReservationIdMatch(Long roomId, Long roomReservationId);

    RoomReservation update(Long id, Long rid, RoomReservation updatedRoomReservation);

    List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people);
}
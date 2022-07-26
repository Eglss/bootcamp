package com.AA.HotelAndSpa.repository;

import com.AA.HotelAndSpa.model.reservation.RoomReservation;
import com.AA.HotelAndSpa.model.room.Room;
import com.AA.HotelAndSpa.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

    Set<RoomReservation> findAllByUser(User user);

    @Query("SELECT r, COUNT(*) FROM Room r " +
            "WHERE r.people >= :people " +
            "AND r.id NOT IN (SELECT rr.room FROM RoomReservation rr) " +
            "OR r.id IN (SELECT rrIn.room FROM RoomReservation rrIn " +
            "WHERE rrIn.startDate NOT BETWEEN :start_date AND :end_date " +
            "AND rrIn.endDate NOT BETWEEN :start_date AND :end_date) " +
            "GROUP BY r HAVING COUNT(*) <= r.count")
    List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people);
}
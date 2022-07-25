package com.AA.HotelAndSpa.repository;

import com.AA.HotelAndSpa.model.room.Room;
import com.AA.HotelAndSpa.model.room.RoomTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByTitle(RoomTitle roomTitle);
}

package com.AA.HotelAndSpa.dto.roomReservation;

import com.AA.HotelAndSpa.dto.room.RoomResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomReservationResponseV1 {
    private long id;
    private String start_date;
    private String end_date;
    private int days;
    private int adults;
    private int kids;
    private float price;
    private RoomResponse room;
}

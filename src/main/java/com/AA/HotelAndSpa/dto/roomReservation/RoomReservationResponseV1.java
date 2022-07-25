package com.AA.HotelAndSpa.dto.roomReservation;

import com.AA.HotelAndSpa.dto.room.RoomResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomReservationResponseV1 {
    private Long id;
    private String start_date;
    private String end_date;
    private Integer days;
    private Integer adults;
    private Integer kids;
    private Float price;
    private RoomResponse room;
}

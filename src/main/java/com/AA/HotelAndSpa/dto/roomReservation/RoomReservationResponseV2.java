package com.AA.HotelAndSpa.dto.roomReservation;

import com.AA.HotelAndSpa.dto.room.RoomResponse;
import com.AA.HotelAndSpa.dto.user.UserResponse;
import com.AA.HotelAndSpa.model.room.RoomBedType;
import com.AA.HotelAndSpa.model.room.RoomView;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomReservationResponseV2 {
    private long id;
    private String start_date;
    private String end_date;
    private int days;
    private int adults;
    private int kids;
    private RoomBedType type_bed;
    private RoomView view;
    private String date;
    private float price;
    private RoomResponse room;
    private UserResponse user;
}

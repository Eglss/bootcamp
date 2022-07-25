package com.AA.HotelAndSpa.dto.roomReservation;

import com.AA.HotelAndSpa.model.room.RoomBedType;
import com.AA.HotelAndSpa.model.room.RoomView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoomReservationUpdateRequest {
    private Long userId;
    private String start_date;
    private String end_date;
    private Integer adults;
    private Integer kids;
    private RoomBedType type_bed;
    private RoomView view;
    private Float price;
}
package com.AA.HotelAndSpa.dto.roomReservation;

import com.AA.HotelAndSpa.model.room.RoomBedType;
import com.AA.HotelAndSpa.model.room.RoomView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationSaveRequest {
    private Long user;
    private String startDate;
    private String endDate;
    private Integer adults;
    private Integer kids;
    private RoomBedType type_bed;
    private RoomView view;
}

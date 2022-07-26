package com.AA.HotelAndSpa.dto.room;

import com.AA.HotelAndSpa.model.room.RoomTitle;
import com.AA.HotelAndSpa.model.room.RoomView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomSaveRequest {

    @NotNull
    private RoomTitle title;

    @NotNull
    private String image;

    private Set<String> images;

    @NotNull
    private String description;

    @NotNull
    private String facilities;

    @NotNull
    private int area;

    @NotNull
    private RoomView view;

    @NotNull
    private int people;

    @NotNull
    private float price;

    @NotNull
    private int count;
}

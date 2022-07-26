package com.AA.HotelAndSpa.dto.room;

import com.AA.HotelAndSpa.model.room.RoomTitle;
import com.AA.HotelAndSpa.model.room.RoomView;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class RoomResponse {
    private long id;
    private RoomTitle title;
    private String image;
    private Set<String> images;
    private String description;
    private String facilities;
    private int area;
    private RoomView view;
    private int people;
    private float price;
}

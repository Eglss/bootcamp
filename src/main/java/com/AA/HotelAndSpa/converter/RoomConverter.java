package com.AA.HotelAndSpa.converter;

import com.AA.HotelAndSpa.dto.room.RoomResponse;
import com.AA.HotelAndSpa.dto.room.RoomSaveRequest;
import com.AA.HotelAndSpa.dto.room.RoomUpdateRequest;
import com.AA.HotelAndSpa.model.room.Room;
import com.AA.HotelAndSpa.model.room.RoomImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoomConverter {

    public Room convert(RoomSaveRequest roomSaveRequest) {
        return Room.builder()
                .title(roomSaveRequest.getTitle())
                .image(roomSaveRequest.getImage())
                .images(roomSaveRequest.getImages().stream()
                        .map(image -> RoomImage.builder().image(image).build())
                        .collect(Collectors.toSet()))
                .description(roomSaveRequest.getDescription())
                .facilities(roomSaveRequest.getFacilities())
                .area(roomSaveRequest.getArea())
                .people(roomSaveRequest.getPeople())
                .view(roomSaveRequest.getView())
                .price(roomSaveRequest.getPrice())
                .count(roomSaveRequest.getCount())
                .build();
    }

    public RoomResponse convert(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .title(room.getTitle())
                .image(room.getImage())
                .images(room.getImages().stream()
                        .map(RoomImage::getImage)
                        .collect(Collectors.toSet()))
                .description(room.getDescription())
                .facilities(room.getFacilities())
                .view(room.getView())
                .area(room.getArea())
                .people(room.getPeople())
                .price(room.getPrice())
                .build();
    }

    public Room convert(RoomUpdateRequest roomUpdateRequest) {
        return Room.builder()
                .title(roomUpdateRequest.getTitle())
                .image(roomUpdateRequest.getImage())
                .images(roomUpdateRequest.getImages().stream()
                        .map(image -> RoomImage.builder().image(image).build())
                        .collect(Collectors.toSet()))
                .description(roomUpdateRequest.getDescription())
                .facilities(roomUpdateRequest.getFacilities())
                .area(roomUpdateRequest.getArea())
                .price(roomUpdateRequest.getPrice())
                .people(roomUpdateRequest.getPeople())
                .price(roomUpdateRequest.getPrice())
                .view(roomUpdateRequest.getView())
                .count(roomUpdateRequest.getCount())
                .build();
    }
}
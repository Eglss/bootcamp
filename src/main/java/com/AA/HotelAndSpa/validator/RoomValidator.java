package com.AA.HotelAndSpa.validator;

import com.AA.HotelAndSpa.exception.RecordBadRequestException;
import com.AA.HotelAndSpa.model.room.RoomTitle;
import com.AA.HotelAndSpa.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoomValidator {

    private final RoomRepository roomRepository;

    public void existById(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RecordBadRequestException(String.format("Room with id:%s, don't exists.", id));
        }
    }

    public void existsByTitle(RoomTitle roomTitle) {
        if (!roomRepository.existsByTitle(roomTitle)) {
            throw new RecordBadRequestException(String.format("Room with Title:%s, don't exists", roomTitle));
        }
    }
}

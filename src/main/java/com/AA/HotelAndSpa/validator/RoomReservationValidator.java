package com.AA.HotelAndSpa.validator;

import com.AA.HotelAndSpa.exception.RecordBadRequestException;
import com.AA.HotelAndSpa.model.reservation.RoomReservation;
import com.AA.HotelAndSpa.repository.RoomReservationRepository;
import com.AA.HotelAndSpa.service.RoomReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class RoomReservationValidator {

    private final RoomReservationRepository roomReservationRepository;
    private final RoomReservationService roomReservationService;

    public void existsById(Long id) {
        if (!roomReservationRepository.existsById(id)) {
            throw new RecordBadRequestException(String.format("Room reservation with id:%s, not exists.", id));
        }
    }

    public void validDates(Instant startDate, Instant endDate) {
        if (endDate.isBefore(startDate) || startDate.isBefore(Instant.now())) {
            throw new RecordBadRequestException("Incorrect dates");
        }
    }

    public void validGuestNumber(Integer roomPeople, Integer roomReservePeople) {
        if (roomPeople < roomReservePeople) {
            throw new RecordBadRequestException(String.format("This room is for %s people!", roomPeople));
        }
    }

    public void roomReservationIdMatchWithUserId(Long userid, Long roomReservationId) {
        RoomReservation foundReservation = roomReservationService.findById(roomReservationId);
        if (!foundReservation.getUser().getId().equals(userid)) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }
    }
}

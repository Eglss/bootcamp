package com.AA.HotelAndSpa.service.implement;

import com.AA.HotelAndSpa.exception.RecordBadRequestException;
import com.AA.HotelAndSpa.exception.RecordNotFoundException;
import com.AA.HotelAndSpa.model.reservation.RoomReservation;
import com.AA.HotelAndSpa.model.room.Room;
import com.AA.HotelAndSpa.model.user.User;
import com.AA.HotelAndSpa.repository.RoomReservationRepository;
import com.AA.HotelAndSpa.service.RoomReservationService;
import com.AA.HotelAndSpa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoomReservationServiceImpl implements RoomReservationService {

    private final UserService userService;
    private final RoomReservationRepository roomReservationRepository;

    public RoomReservation save(RoomReservation roomReservation) {
        Objects.requireNonNull(roomReservation);
        return roomReservationRepository.save(roomReservation);
    }

    public RoomReservation findByUserIdAndReservationId(long uid, long rid) {
        Objects.requireNonNull(uid);
        Objects.requireNonNull(rid);

        User foundUser = userService.findById(uid);
        RoomReservation foundReservation = findById(rid);
        if (foundReservation.getUser().getId() != foundUser.getId()) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the User ID.");
        }
        return foundReservation;
    }

    public Set<RoomReservation> findAllByUserId(long id) {
        Objects.requireNonNull(id);
        User userById = userService.findById(id);
        return roomReservationRepository.findAllByUser(userById);
    }

    public Set<RoomReservation> findAll() {

        return new HashSet<>(roomReservationRepository.findAll());
    }

    @Override
    public int calculateDays(Instant startDate, Instant endDate) {

        long daysLong = Duration.between(startDate, endDate).toDays();
        if (daysLong <= 0) {
            throw new RecordBadRequestException("Days should be more than 0");
        }
        return (int) daysLong;
    }

    @Override
    public RoomReservation findById(long id) {
        return roomReservationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Room reservation with id:%s, not found", id)));
    }

    @Override
    public void deleteById(long id) {
        roomReservationRepository.deleteById(id);
    }

    @Override
    public void roomReservationIdMatch(long roomId, long roomReservationId) {
        RoomReservation roomReservation = findById(roomReservationId);
        if (roomReservation.getRoom().getId() != roomId) {
            throw new RecordBadRequestException("Reservation ID doesn't match with the room ID.");
        }
    }

    @Override
    @Transactional
    public RoomReservation update(long id, long rid, RoomReservation updatedRoomReservation) {
        Objects.requireNonNull(rid);
        RoomReservation roomReservation = findById(rid);
        roomReservation.setStartDate(updatedRoomReservation.getStartDate());
        roomReservation.setEndDate(updatedRoomReservation.getEndDate());
        roomReservation.setAdults(updatedRoomReservation.getAdults());
        roomReservation.setKids(updatedRoomReservation.getKids());
        roomReservation.setRoomBedType(updatedRoomReservation.getRoomBedType());
        roomReservation.setRoomView(updatedRoomReservation.getRoomView());
        roomReservation.setPrice(updatedRoomReservation.getPrice());
        return roomReservation;
    }

    @Override
    public List<Room> findAllAvailableRooms(Instant start_date, Instant end_date, int people) {
        return roomReservationRepository.findAllAvailableRooms(start_date, end_date ,people);
    }
}

package com.AA.HotelAndSpa.service.implement;

import com.AA.HotelAndSpa.exception.RecordNotFoundException;
import com.AA.HotelAndSpa.model.room.Room;
import com.AA.HotelAndSpa.repository.RoomRepository;
import com.AA.HotelAndSpa.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room save(Room room) {
        Objects.requireNonNull(room);
        return roomRepository.save(room);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room update(long id, Room updatedRoom) {
        Objects.requireNonNull(id);

        Room room = findById(id);
        room.setTitle(updatedRoom.getTitle());
        room.setImage(updatedRoom.getImage());
        room.setImages(updatedRoom.getImages());
        room.setDescription(updatedRoom.getDescription());
        room.setFacilities(updatedRoom.getFacilities());
        room.setArea(updatedRoom.getArea());
        room.setView(updatedRoom.getView());
        room.setPeople(updatedRoom.getPeople());
        room.setPrice(updatedRoom.getPrice());
        room.setCount(updatedRoom.getCount());
        return room;
    }

    @Override
    public Room findById(long id) {
        Objects.requireNonNull(id);
        return roomRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                String.format("Room with id:%s, not found", id)));
    }

    @Override
    public void deleteById(long id) {
        try {
            roomRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    String.format("Room with id:%s, not found.", id));
        }
    }
}

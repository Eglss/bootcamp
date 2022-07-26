package com.AA.HotelAndSpa.service;

import com.AA.HotelAndSpa.exception.RecordBadRequestException;
import com.AA.HotelAndSpa.model.reservation.RoomReservation;
import com.AA.HotelAndSpa.model.user.Role;
import com.AA.HotelAndSpa.model.user.User;
import com.AA.HotelAndSpa.repository.RoomReservationRepository;
import com.AA.HotelAndSpa.repository.UserRepository;
import com.AA.HotelAndSpa.service.implement.RoomReservationServiceImpl;
import com.AA.HotelAndSpa.service.implement.UserServiceImpl;
import com.AA.HotelAndSpa.validator.RoomReservationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RoomReservationServiceImplTest {

    private RoomReservationServiceImpl roomReservationService;

    @Mock
    private RoomReservationRepository roomReservationRepository;

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private RoomReservationValidator roomReservationValidator;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
        roomReservationService = new RoomReservationServiceImpl(userService, roomReservationRepository);
    }

    @Test
    public void verifySave() {
        RoomReservation roomReservation = RoomReservation.builder().build();
        roomReservationService.save(roomReservation);
        verify(roomReservationRepository, times(1)).save(roomReservation);
    }

    @Test
    public void verifyFindAllByUserId() {
        User user = User.builder()
                .id(1L)
                .name("Georgi")
                .surname("Georgiev")
                .phone("0888888888")
                .email("email@gmail.com")
                .password("12345678")
                .roles(Set.of(Role.builder().build()))
                .created(Instant.now())
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        roomReservationService.findAllByUserId(user.getId());
        verify(roomReservationRepository, times(1)).findAllByUser(user);
    }

    @Test
    public void verifyFindAll() {
        when(roomReservationRepository.findAll()).thenReturn(List.of(RoomReservation.builder().build()));
        roomReservationService.findAll();
        verify(roomReservationRepository, times(1)).findAll();
    }

    @Test
    public void verifyCalculateDays() {
        String startDateString  = "2022-07-15T18:35:24.00Z";
        String endDateString = "2022-08-01T18:35:24.00Z";
        Instant startDate = Instant.parse(startDateString);
        Instant endDate = Instant.parse(endDateString);
        roomReservationService.calculateDays(startDate, endDate);
        boolean isTrue = startDate.isBefore(endDate);
        assertTrue(isTrue);
    }

    @Test
    public void verifyCalculateDaysThrowsException() {
        String endDateString  = "2022-07-15T18:35:24.00Z";
        String startDateString = "2022-08-01T18:35:24.00Z";
        Instant startDate = Instant.parse(startDateString);
        Instant endDate = Instant.parse(endDateString);
        String message = "Days should be more than 0";
        RecordBadRequestException recordNotFoundException = assertThrows(RecordBadRequestException.class, () -> {
            roomReservationService.calculateDays(startDate, endDate);
        });
        assertEquals(message, recordNotFoundException.getMessage());
    }
}
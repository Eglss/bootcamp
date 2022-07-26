package com.AA.HotelAndSpa.model.reservation;

import com.AA.HotelAndSpa.model.room.Room;
import com.AA.HotelAndSpa.model.room.RoomBedType;
import com.AA.HotelAndSpa.model.room.RoomView;
import com.AA.HotelAndSpa.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_reservations")
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "start_date")
    private Instant startDate;

    @NotNull
    @Column(name = "end_date")
    private Instant endDate;

    @NotNull
    private int days;

    @NotNull
    private int adults;

    @NotNull
    private int kids;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomBedType roomBedType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomView roomView;

    @NotNull
    private float price;

    @CreationTimestamp
    private Instant created;

//    @NotNull
//    @Column(name = "status")
//    private String status;

    @NotNull
    @ManyToOne
    private Room room;

    @NotNull
    @ManyToOne
    private User user;
}

package org.fasttrackit.hotel.management.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue
    private int id;

    private String number;
    private int floor;
    private String hotelName;

    @OneToMany
    private List<CleanOperations>  cleanups;

    @OneToOne
    private RoomFacilities facilities;
}

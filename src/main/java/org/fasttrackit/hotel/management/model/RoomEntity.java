package org.fasttrackit.hotel.management.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Setter
@Getter
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomEntity {
    @Id
    @GeneratedValue
    private int id;

    private String number;
    private int floor;
    private String hotelName;

//    @OneToMany(cascade = ALL)
//    private List<CleanOperationsEntity>  cleanups;

    @OneToOne(cascade = ALL)
    private RoomFacilityEntity facilities;
}

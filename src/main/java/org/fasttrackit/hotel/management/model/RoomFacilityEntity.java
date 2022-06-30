package org.fasttrackit.hotel.management.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@With
@AllArgsConstructor
@NoArgsConstructor
public class RoomFacilityEntity {
    @Id
    @GeneratedValue
    private int id;

    private boolean hasTV;
    private boolean hasDoubleBed;

}

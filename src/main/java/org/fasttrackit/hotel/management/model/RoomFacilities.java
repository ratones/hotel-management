package org.fasttrackit.hotel.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class RoomFacilities {
    @Id
    @GeneratedValue
    private int id;

    private boolean hasTV;
    private boolean hasDoubleBed;

}

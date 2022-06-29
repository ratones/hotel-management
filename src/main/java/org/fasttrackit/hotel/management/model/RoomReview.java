package org.fasttrackit.hotel.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class RoomReview {
    @Id
    @GeneratedValue
    private int id;

    private String message;
    private int rating;
    private String guest;

    @ManyToOne
    private Room room;
}

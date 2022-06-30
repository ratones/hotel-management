package org.fasttrackit.hotel.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomReviewEntity {
    @Id
    @GeneratedValue
    private int id;

    private String message;
    private int rating;
    private String guest;

    @ManyToOne
    private RoomEntity room;

    public RoomReviewEntity(String message, int rating, String guest, RoomEntity room){
        this.message = message;
        this.rating = rating;
        this.guest = guest;
        this.room = room;
    }
}

package org.fasttrackit.hotel.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class CleaningProcedure {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int outcome;
}

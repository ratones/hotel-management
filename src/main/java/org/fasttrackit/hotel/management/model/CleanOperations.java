package org.fasttrackit.hotel.management.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class CleanOperations {
    @Id
    @GeneratedValue
    private int id;

    private LocalDate date;

    private List<CleaningProcedure> procedures;
}

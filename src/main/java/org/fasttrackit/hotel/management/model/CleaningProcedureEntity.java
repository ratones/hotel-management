package org.fasttrackit.hotel.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CleaningProcedureEntity {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private ProcedureOutcome outcome;
}

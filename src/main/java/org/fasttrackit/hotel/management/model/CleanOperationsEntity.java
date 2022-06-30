package org.fasttrackit.hotel.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CleanOperationsEntity {
    @Id
    @GeneratedValue
    private int id;

    private LocalDate date;
    @ManyToMany
    private List<CleaningProcedureEntity> procedures;

    @ManyToOne RoomEntity room;

    public CleanOperationsEntity(LocalDate date, List<CleaningProcedureEntity> procedures, RoomEntity room){
        this.date = date;
        this.procedures = procedures;
        this.room = room;
    }
}

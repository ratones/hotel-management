package org.fasttrackit.hotel.management.repository;

import org.fasttrackit.hotel.management.model.CleanOperationsEntity;
import org.fasttrackit.hotel.management.model.RoomReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CleanOperationsRepository extends JpaRepository<CleanOperationsEntity, Integer> {
    List<CleanOperationsEntity> getByRoomId(int id);
}

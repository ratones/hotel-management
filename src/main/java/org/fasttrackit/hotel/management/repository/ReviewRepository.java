package org.fasttrackit.hotel.management.repository;

import org.fasttrackit.hotel.management.model.RoomReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<RoomReviewEntity, Integer> {
    List<RoomReviewEntity> getByRoomId(int id);
}

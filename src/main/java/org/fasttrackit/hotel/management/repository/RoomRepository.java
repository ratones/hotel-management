package org.fasttrackit.hotel.management.repository;

import org.fasttrackit.hotel.management.model.RoomEntity;
import org.fasttrackit.hotel.management.model.RoomFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

}

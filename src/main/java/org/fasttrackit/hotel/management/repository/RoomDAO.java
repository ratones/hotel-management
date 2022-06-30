package org.fasttrackit.hotel.management.repository;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.model.RoomEntity;
import org.fasttrackit.hotel.management.model.RoomFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class RoomDAO {
    private final EntityManager entityManager;

    public List<RoomEntity> findAllWithFilter(RoomFilter filter) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoomEntity> criteria = criteriaBuilder.createQuery(RoomEntity.class);
        Root<RoomEntity> root = criteria.from(RoomEntity.class);
        CriteriaQuery<RoomEntity> query = criteria.select(root);
        List<Predicate> whereCoditions = filterToConditions(criteriaBuilder, root, filter);
        query.where(whereCoditions.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    private List<Predicate> filterToConditions(CriteriaBuilder criteriaBuilder, Root<RoomEntity> root, RoomFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        ofNullable(filter.name())
                .map(name -> criteriaBuilder.like(root.get("number"), "%" + name + "%"))
                .ifPresent(predicates::add);
        ofNullable(filter.floor())
                .map(floor -> criteriaBuilder.equal(root.get("floor"), floor))
                .ifPresent(predicates::add);
        ofNullable(filter.hotel())
                .map(hotel -> criteriaBuilder.like(root.get("hotelName"),"%"+hotel+"%"))
                .ifPresent(predicates::add);
        ofNullable(filter.tv())
                .map(hasTV -> criteriaBuilder.equal(root.get("facilities").get("hasTV"),hasTV))
                .ifPresent(predicates::add);
        ofNullable(filter.doubleBed())
                .map(hasDoubleBed -> criteriaBuilder.equal(root.get("facilities").get("hasDoubleBed"),hasDoubleBed))
                .ifPresent(predicates::add);
        return predicates;
    }
}

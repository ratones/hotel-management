package org.fasttrackit.hotel.management.service;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.model.RoomReviewEntity;
import org.fasttrackit.hotel.management.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    public List<RoomReviewEntity> getRoomReviews(int roomId) {
        return repository.getByRoomId(roomId);
    }
}

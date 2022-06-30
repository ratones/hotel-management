package org.fasttrackit.hotel.management.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.model.RoomReviewEntity;
import org.fasttrackit.hotel.management.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;

    @GetMapping("{id}")
    List<RoomReviewEntity> getRoomReviews(@PathVariable int id){
        return service.getRoomReviews(id);
    }
}

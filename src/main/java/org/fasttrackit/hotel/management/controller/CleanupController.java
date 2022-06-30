package org.fasttrackit.hotel.management.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.model.CleanOperationsEntity;
import org.fasttrackit.hotel.management.service.CleanupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cleanups")
@RequiredArgsConstructor
public class CleanupController {
    private final CleanupService service;

    @GetMapping("{id}")
    List<CleanOperationsEntity> getRoomCleanup(@PathVariable int id){
        return service.getRoomCleanup(id);
    }
}

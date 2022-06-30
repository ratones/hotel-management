package org.fasttrackit.hotel.management.service;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.model.CleanOperationsEntity;
import org.fasttrackit.hotel.management.repository.CleanOperationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanOperationsRepository repository;

    public List<CleanOperationsEntity> getRoomCleanup(int roomId) {
        return repository.getByRoomId(roomId);
    }
}

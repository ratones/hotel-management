package org.fasttrackit.hotel.management.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.exceptions.ResourceNotFoundException;
import org.fasttrackit.hotel.management.model.RoomEntity;
import org.fasttrackit.hotel.management.model.RoomFacilityEntity;
import org.fasttrackit.hotel.management.model.RoomFilter;
import org.fasttrackit.hotel.management.repository.RoomDAO;
import org.fasttrackit.hotel.management.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;
    private final RoomDAO dao;
    public List<RoomEntity> getRooms(RoomFilter filter) {
        System.out.println(filter.toString());
        return dao.findAllWithFilter(filter);
    }

    public Optional<RoomEntity> getRoom(Integer id) {
        return repository.findById(id);
    }

    public Optional<RoomEntity> deleteRoom(Integer id) {
        var room = repository.findById(id);
        room.ifPresent(repository::delete);
        return room;
    }

    public RoomEntity updateRoom(Integer id, JsonPatch jsonPatch) {
        return repository.findById(id)
                .map(room -> applyPatch(room, jsonPatch))
                .map(room -> applyChanges(id, room))
                .orElseThrow(() -> new ResourceNotFoundException("Could not find room with id %s".formatted(id)));
    }

    private RoomEntity applyChanges(Integer id, RoomEntity room) {
        RoomEntity roomEntity = getRoom(id).orElseThrow();
        RoomFacilityEntity facilities = roomEntity.getFacilities();
        return repository.save(roomEntity
                .withFloor(room.getFloor())
                .withHotelName(room.getHotelName())
                .withNumber(room.getNumber())
                .withFacilities(facilities
                        .withHasTV(room.getFacilities().isHasTV())
                        .withHasDoubleBed(room.getFacilities().isHasDoubleBed())
                )
        );
    }

    private RoomEntity applyPatch(RoomEntity dbEntity, JsonPatch jsonPatch) {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode jsonNode = jsonMapper.convertValue(dbEntity, JsonNode.class);
            JsonNode patchedJson = jsonPatch.apply(jsonNode);
            return jsonMapper.treeToValue(patchedJson, RoomEntity.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

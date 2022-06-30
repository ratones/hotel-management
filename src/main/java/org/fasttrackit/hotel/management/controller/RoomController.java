package org.fasttrackit.hotel.management.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.exceptions.ResourceNotFoundException;
import org.fasttrackit.hotel.management.model.RoomEntity;
import org.fasttrackit.hotel.management.model.RoomFilter;
import org.fasttrackit.hotel.management.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping
    List<RoomEntity> getRooms(RoomFilter filter) {
        return service.getRooms(filter);
    }

    @GetMapping("{id}")
    RoomEntity getById(@PathVariable Integer id){
        return service.getRoom(id)
                .orElseThrow(() ->new ResourceNotFoundException("Could not find room with id " + id));
    }

    @DeleteMapping("{id}")
    RoomEntity deleteRoom(@PathVariable Integer id){
        return service.deleteRoom(id)
                .orElseThrow(() ->new ResourceNotFoundException("Could not find room with id " + id));
    }

    @PatchMapping("{id}")
    RoomEntity updateRoom(@PathVariable Integer id, @RequestBody JsonPatch jsonPatch){
        return service.updateRoom(id, jsonPatch);
    }

}

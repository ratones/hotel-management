package org.fasttrackit.hotel.management.loader;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.hotel.management.config.AppConfiguration;
import org.fasttrackit.hotel.management.model.*;
import org.fasttrackit.hotel.management.repository.CleanOperationsRepository;
import org.fasttrackit.hotel.management.repository.CleaningProcedureRepository;
import org.fasttrackit.hotel.management.repository.ReviewRepository;
import org.fasttrackit.hotel.management.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final Map<String, Integer> messages;
    private final RoomRepository roomRepo;
    private final CleaningProcedureRepository cleaningProcedureRepository;
    private final CleanOperationsRepository cleanOperationsRepository;
    private final ReviewRepository reviewRepository;

    private final AppConfiguration config;

    private List<CleaningProcedureEntity> procedures;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(config.getHotelName());
        this.messages.put("Clean room", 5);
        this.messages.put("Great view", 5);
        this.messages.put("Very noisy", 3);
        this.messages.put("No fridge", 2);

        this.procedures = createCleanProcedures();
        // create rooms
        IntStream.range(0, config.getNoOfFloors()).forEach(this::createRooms);
    }

    private List<CleaningProcedureEntity> createCleanProcedures() {
        return cleaningProcedureRepository.saveAll(List.of(
                new CleaningProcedureEntity(0, "Clean Bathroom", ProcedureOutcome.DONE),
                new CleaningProcedureEntity(0, "Clean Bedroom", ProcedureOutcome.DONE),
                new CleaningProcedureEntity(0, "Change sheets", ProcedureOutcome.DONE),
                new CleaningProcedureEntity(0, "Change towels", ProcedureOutcome.DONE),
                new CleaningProcedureEntity(0, "Clean Bathroom", ProcedureOutcome.STARTED),
                new CleaningProcedureEntity(0, "Clean Bedroom", ProcedureOutcome.STARTED),
                new CleaningProcedureEntity(0, "Change sheets", ProcedureOutcome.STARTED),
                new CleaningProcedureEntity(0, "Change towels", ProcedureOutcome.STARTED),
                new CleaningProcedureEntity(0, "Clean Bathroom", ProcedureOutcome.INPROGRESS),
                new CleaningProcedureEntity(0, "Clean Bedroom", ProcedureOutcome.INPROGRESS),
                new CleaningProcedureEntity(0, "Change sheets", ProcedureOutcome.INPROGRESS),
                new CleaningProcedureEntity(0, "Change towels", ProcedureOutcome.INPROGRESS)
        ));
    }

    private void createRooms(int i) {
        var rooms = roomRepo.saveAll(IntStream.range(1, 10)
                .mapToObj(index -> RoomEntity
                        .builder()
                        .facilities(new RoomFacilityEntity(0, index % 2 == 0, index % 4 == 0))
                        .floor(i+1)
                        .hotelName(config.getHotelName())
                        .number("Room no. %s0%s".formatted(i+1, index))
                        .build())
                .toList());
        rooms.forEach(room -> {
            generateReviews(room);
            generateCleanupOperations(room);
        });
    }

    private void generateReviews(RoomEntity roomEntity) {
        int randSize = new Random().nextInt(1, 5);
        IntStream.range(1, randSize).forEach(
                index -> {
                    var reviewData = messages.entrySet().stream().toList().get(new Random().nextInt(0, messages.size() - 1));
                    reviewRepository.save(new RoomReviewEntity(reviewData.getKey(), reviewData.getValue(), "Guest %s".formatted(index), roomEntity));
                }
        );
    }

    //TODO: Refactor more randon procedures
    private void generateCleanupOperations(RoomEntity room) {
        int startSublist = new Random().nextInt(0, procedures.size() - 1);
        int endSublist = procedures.size() - 1;
        cleanOperationsRepository.saveAll(IntStream.range(1, startSublist).mapToObj(
                (index -> new CleanOperationsEntity(LocalDate.now()
                        .minusDays(new Random().nextInt(365)),
                        procedures.subList(new Random().nextInt(0, procedures.size() - 1), endSublist), room))
        ).toList());
    }
}

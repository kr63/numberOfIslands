package com.example.numberofislands.solverservice.listener;

import com.example.numberofislands.solverservice.model.Islands;
import com.example.numberofislands.solverservice.service.SolverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class KafkaConsumer {

    private final SolverService solverService;

    @Autowired
    public KafkaConsumer(SolverService solverService) {
        this.solverService = solverService;
    }

    @KafkaListener(topics = "Kafka_Example", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Islands islands) {
        log.info("Consume islands: {}", islands);

        log.info("Receive map:");
        for (int[] line : islands.getData()) {
            log.info(Arrays.toString(line));
        }
        log.info("----------------------------------------");

        int solution = solverService.solve(islands.getData());
        log.info("Number of islands: {}", solution);
    }
}

package com.example.numberofislands.generatorservice.controller;

import com.example.numberofislands.generatorservice.entity.Islands;
import com.example.numberofislands.generatorservice.exception.IslandsNotFoundException;
import com.example.numberofislands.generatorservice.repository.IslandsRepository;
import com.example.numberofislands.generatorservice.service.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api")
public class Controller {

    private static final String TOPIC = "Kafka_Example";

    private final GeneratorService generatorService;
    private final IslandsRepository islandsRepository;
    private final KafkaTemplate<String, Islands> kafkaTemplate;

    @Autowired
    public Controller(GeneratorService generatorService,
                      IslandsRepository islandsRepository,
                      KafkaTemplate<String, Islands> kafkaTemplate) {
        this.generatorService = generatorService;
        this.islandsRepository = islandsRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("islands/{id}")
    public ResponseEntity<Islands> getIslands(@PathVariable Long id) {
        return generatorService.getIslandsById(id)
                .map(islands -> new ResponseEntity<>(islands, HttpStatus.OK))
                .orElseThrow(() -> new IslandsNotFoundException("Not found islands with id: " + id));
    }

    @PostMapping("islands/generate")
    public ResponseEntity<Islands> addIslands(
            @RequestParam(value = "rows", defaultValue = "3") Integer rows,
            @RequestParam(value = "cols", defaultValue = "3") Integer cols) {

        Islands islands = new Islands();
        islands.setId(null);
        islands.setData(generatorService.generateIslands(rows, cols));
        islandsRepository.save(islands);

        kafkaTemplate.send(TOPIC, islands);
        log.info("Send to kafka topic: {}", islands);

        return new ResponseEntity<>(islands, HttpStatus.CREATED);
    }

    @PostMapping("islands")
    public ResponseEntity<Islands> postIsland(@RequestBody Islands islands) {
        islands.setId(null);
        islandsRepository.save(islands);

        kafkaTemplate.send(TOPIC, islands);
        log.info("Send to kafka topic: {}", islands);
        return new ResponseEntity<>(islands, HttpStatus.CREATED);
    }
}

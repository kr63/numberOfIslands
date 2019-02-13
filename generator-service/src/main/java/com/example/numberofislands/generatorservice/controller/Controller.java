package com.example.numberofislands.generatorservice.controller;

import com.example.numberofislands.generatorservice.entity.Islands;
import com.example.numberofislands.generatorservice.exception.IslandsNotFoundException;
import com.example.numberofislands.generatorservice.repository.IslandsRepository;
import com.example.numberofislands.generatorservice.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class Controller {

    private final GeneratorService generatorService;
    private final IslandsRepository islandsRepository;

    @Autowired
    public Controller(GeneratorService generatorService,
                      IslandsRepository islandsRepository) {
        this.generatorService = generatorService;
        this.islandsRepository = islandsRepository;
    }

    @GetMapping("islands/{id}")
    public ResponseEntity<Islands> getIslands(@PathVariable Long id) {
        return generatorService.getIslandsById(id)
                .map(islands -> new ResponseEntity<>(islands, HttpStatus.OK))
                .orElseThrow(() -> new IslandsNotFoundException("Not found islands with id: " + id));
    }

    @PostMapping("islands")
    public ResponseEntity<Islands> addIslands(
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "cols", required = false) Integer cols) {
        if (rows == null) rows = 3;
        if (cols == null) cols = 3;

        Islands islands = new Islands();
        islands.setId(null);
        islands.setData(generatorService.generateIslands(rows, cols));
        islandsRepository.save(islands);
        return new ResponseEntity<>(islands, HttpStatus.CREATED);
    }

}

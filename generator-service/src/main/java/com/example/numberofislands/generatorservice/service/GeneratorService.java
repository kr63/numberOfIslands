package com.example.numberofislands.generatorservice.service;

import com.example.numberofislands.generatorservice.entity.Islands;

import java.util.Optional;

public interface GeneratorService {
    int[][] generateIslands(int rows, int cols);

    Optional<Islands> getIslandsById(Long id);
}

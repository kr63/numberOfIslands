package com.example.numberofislands.generatorservice.service;

import com.example.numberofislands.generatorservice.entity.Islands;
import com.example.numberofislands.generatorservice.repository.IslandsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {

    private final IslandsRepository islandsRepository;

    public GeneratorServiceImpl(IslandsRepository islandsRepository) {
        this.islandsRepository = islandsRepository;
    }

    @Override
    public int[][] generateIslands(int rowNum, int colNum) {
        int[][] islands = new int[rowNum][colNum];

        List<Integer> rows = getRandomList(0, rowNum, getRandomNumber(0, rowNum));
        List<Integer> cols;
        Integer randomSize;

        for (Integer row : rows) {
            randomSize = getRandomNumber(0, colNum);
            cols = getRandomList(0, colNum, randomSize);
            log.info("Row: {}; Cols: {}", row, cols);
            cols.forEach(col -> islands[row][col] = 1);
        }
        log.info("Generated map:");
        for (int[] line : islands) {
            log.info(Arrays.toString(line));
        }
        log.info("----------------------------------------");

        return islands;
    }

    @Override
    public Optional<Islands> getIslandsById(Long id) {
        return islandsRepository.findById(id);
    }

    private List<Integer> getRandomList(int from, int to, int size) {
        IntStream rows = IntStream.range(from, to);
        List<Integer> list = rows.boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        List<Integer> integers = list.subList(0, size);
        Collections.sort(integers);
        return integers;
    }

    private Integer getRandomNumber(int from, int to) {
        return from + (int) (Math.random() * (to - from) + 1);
    }
}

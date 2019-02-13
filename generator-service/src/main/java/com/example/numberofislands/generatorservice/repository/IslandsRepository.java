package com.example.numberofislands.generatorservice.repository;

import com.example.numberofislands.generatorservice.entity.Islands;
import org.springframework.data.repository.CrudRepository;

public interface IslandsRepository extends CrudRepository<Islands, Long> {
}

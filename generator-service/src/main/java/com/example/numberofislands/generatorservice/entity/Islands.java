package com.example.numberofislands.generatorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Islands {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private int[][] data;
}

package com.example.numberofislands.generatorservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Islands {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private int[][] data;
}

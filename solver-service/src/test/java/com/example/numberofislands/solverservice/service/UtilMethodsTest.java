package com.example.numberofislands.solverservice.service;

import com.example.numberofislands.solverservice.model.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilMethodsTest {

    private UtilMethods methods;
    private int[][] ints;
    private int cols;

    @Before
    public void setUp() {
        methods = new UtilMethods();
        ints = new int[][]{
                {11, 12, 13, 14},
                {21, 22, 23, 24},
                {31, 32, 33, 34}};
        cols = ints[0].length;
    }

    @Test
    public void getKey_ShouldReturnValidValues() {
        assertThat(methods.getKey(0, 0, cols)).isEqualTo(0);
        assertThat(methods.getKey(0, 1, cols)).isEqualTo(1);
        assertThat(methods.getKey(1, 1, cols)).isEqualTo(5);
        assertThat(methods.getKey(1, 3, cols)).isEqualTo(7);
        assertThat(methods.getKey(3, 2, cols)).isEqualTo(14);
    }

    @Test
    public void getNeighbors_ShouldReturnValidValuesForCornerNode1() {
        // given
        Node node = new Node();
        node.key = 0;
        node.row = 0;
        node.col = 0;
        // when
        List<Integer> neighbors = methods.getNeighbors(node, ints);
        // than
        assertThat(neighbors).isEqualTo(Arrays.asList(1, 4, 5));
    }

    @Test
    public void getNeighbors_ShouldReturnValidValuesForCornerNode2() {
        // given
        Node node = new Node();
        node.key = 11;
        node.row = 2;
        node.col = 3;
        // when
        List<Integer> neighbors = methods.getNeighbors(node, ints);
        // than
        assertThat(neighbors).isEqualTo(Arrays.asList(6, 7, 10));
    }

    @Test
    public void getNeighbors_ShouldReturnFiveCells() {
        // given
        Node node = new Node();
        node.key = 2;
        node.row = 0;
        node.col = 2;
        // when
        List<Integer> neighbors = methods.getNeighbors(node, ints);
        // than
        assertThat(neighbors).isEqualTo(Arrays.asList(1, 3, 5, 6, 7));
    }

    @Test
    public void getNeighbors_ShouldReturnEightCells() {
        // given
        Node node = new Node();
        node.key = 5;
        node.row = 1;
        node.col = 1;
        // when
        List<Integer> neighbors = methods.getNeighbors(node, ints);
        // than
        assertThat(neighbors).isEqualTo(Arrays.asList(0, 1, 2, 4, 6, 8, 9, 10));
    }
}
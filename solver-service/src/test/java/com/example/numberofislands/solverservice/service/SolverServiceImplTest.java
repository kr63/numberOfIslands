package com.example.numberofislands.solverservice.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SolverServiceImplTest {

    private int[][] ints;

    @Test
    public void solver_ShouldReturnOne1() {
        // given
        ints = new int[][]{{1, 0}, {0, 0}};
        // when
        int islands = new SolverServiceImpl().solve(ints);
        // than
        assertThat(islands).isEqualTo(1);
    }

    @Test
    public void solver_ShouldReturnOne2() {
        // given
        ints = new int[][]{{1, 1}, {0, 1}};
        // when
        int islands = new SolverServiceImpl().solve(ints);
        // than
        assertThat(islands).isEqualTo(1);
    }

    @Test
    public void solver_ShouldReturnFour() {
        // given
        ints = new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        // when
        int islands = new SolverServiceImpl().solve(ints);
        // than
        assertThat(islands).isEqualTo(4);
    }

    @Test
    public void solver_ShouldReturnOneGridSmall() {
        // given
        ints = new int[][]{
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0}};

        // when
        int islands = new SolverServiceImpl().solve(ints);
        // than
        assertThat(islands).isEqualTo(1);
    }

    @Test
    public void solver_ShouldReturnOneGridBig() {
        // given
        ints = new int[][]{
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1, 0, 1, 1, 0, 1}};

        // when
        int islands = new SolverServiceImpl().solve(ints);
        // than
        assertThat(islands).isEqualTo(1);
    }

    @Test
    public void solver_ShouldReturnOneLong() {
        ints = new int[][]{
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}};
        // when
        int islands = new SolverServiceImpl().solve(ints);
        // than
        assertThat(islands).isEqualTo(1);
    }

    @Test
    public void solver_ShouldReturnEight() {
        // given
        ints = new int[][]{
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 1, 1, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0}};
        // when
        int islands = new SolverServiceImpl().solve(ints);
        // than
        assertThat(islands).isEqualTo(8);
    }
}
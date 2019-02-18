package com.example.numberofislands.solverservice.service;

import com.example.numberofislands.solverservice.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UtilMethods {
    /**
     * @param node input node
     * @param ints input matrix
     * @return keys of node neighbors in input matrix
     */
    List<Integer> getNeighbors(Node node, int[][] ints) {
        int[] deltaRow = new int[]{0, 0, 1, -1, 1, -1, 1, -1};
        int[] deltaCol = new int[]{1, -1, 0, 0, 1, 1, -1, -1};
        int row = node.row;
        int col = node.col;
        int cols = ints[0].length;
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < deltaRow.length; i++) {
            try {
                if (ints[row + deltaRow[i]][col + deltaCol[i]] >= 0) {
                    int key = getKey(row + deltaRow[i], col + deltaCol[i], cols);
                    output.add(key);
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        Collections.sort(output);
        return output;
    }

    /**
     * @param row  coordinate of the cell;
     * @param col  coordinate of the cell;
     * @param cols max number of cells in row;
     * @return the key of cell(row, col).
     */
    int getKey(int row, int col, int cols) {
        return cols * row + col;
    }
}

package com.example.numberofislands.solverservice.service;

import com.example.numberofislands.solverservice.model.DisjointSet;
import com.example.numberofislands.solverservice.model.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SolverServiceImpl implements SolverService {

    private final static UtilMethods METHODS = new UtilMethods();

    @Override
    public int solve(int[][] ints) {

        DisjointSet dsj = new DisjointSet();
        int cols = ints[0].length;
        int rows = ints.length;

        // make nodes from int[i][j] == 1
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (ints[i][j] == 1) {
                    int key = METHODS.getKey(i, j, cols);
                    dsj.make(key, i, j);
                }
            }
        }

        // union neighbors
        Map<Integer, Node> map = dsj.getMap();
        for (Integer key : map.keySet()) {
            Node node = map.get(key);
            List<Integer> keys = METHODS.getNeighbors(node, ints);

            keys.forEach(neighborKey -> {
                Optional<Node> opt = Optional.ofNullable(map.get(neighborKey));
                opt.ifPresent(neighborNode -> dsj.union(neighborNode.key, key));
            });
        }

        // calculate roots
        Set<Node> roots = new HashSet<>();
        for (Integer key : map.keySet()) {
            Node node = map.get(key);
            Node rootNode = dsj.find(node);
            roots.add(rootNode.root);
        }

        return roots.size();
    }

}

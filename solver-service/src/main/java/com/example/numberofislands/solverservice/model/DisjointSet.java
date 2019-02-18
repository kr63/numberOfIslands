package com.example.numberofislands.solverservice.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class DisjointSet {

    private Map<Integer, Node> map = new HashMap<>();

    /**
     * Create set from the node defined by key
     *
     * @param key key of the Node
     */
    public void make(int key, int x, int y) {
        Node node = new Node();
        node.key = key;
        node.root = node;
        node.rank = 0;
        node.row = x;
        node.col = y;
        map.put(key, node);
    }

    /**
     * Union two nodes with keys 1 & 2
     *
     * @param key1 the first node key
     * @param key2 the second node key
     */
    public void union(int key1, int key2) {
        Node node1 = map.get(key1);
        Node node2 = map.get(key2);

        Node root1 = find(node1);
        Node root2 = find(node2);

        if (root1.key == root2.key) return;

        if (root1.rank >= root2.rank) {
            root1.rank = (root1.rank == root2.rank) ? ++root1.rank : root1.rank;
            root2.root = root1;
        } else {
            root1.root = root2;
        }
    }

    /**
     * Return the root of the specific node
     *
     * @param node find root for this node
     * @return root of the specific node
     */
    public Node find(Node node) {
        Node root = node.root;
        if (root == node) return root;
        node.root = find(node.root);
        return node.root;
    }
}

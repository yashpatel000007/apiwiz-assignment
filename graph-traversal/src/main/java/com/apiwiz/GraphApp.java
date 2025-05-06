package com.apiwiz;

public class GraphApp {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Sample input
        graph.addNode(1, "A");
        graph.addNode(2, "B");
        graph.addNode(3, "C");
        graph.addNode(4, "D");
        graph.addNode(5, "E");

        graph.addEdge(1, 2); // A -> B
        graph.addEdge(1, 3); // A -> C
        graph.addEdge(2, 4); // B -> D
        graph.addEdge(3, 4); // C -> D
        graph.addEdge(4, 5); // D -> E

        System.out.println("Starting graph execution:");
        graph.execute();
        System.out.println("Execution complete.");
    }
}

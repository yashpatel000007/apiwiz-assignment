package com.apiwiz;

import java.util.*;

public class GraphApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        // Read number of vertices
        int numVertices = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < numVertices; i++) {
            String[] parts = scanner.nextLine().trim().split(":");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            graph.addNode(id, name);
        }

        // Read number of edges
        int numEdges = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < numEdges; i++) {
            String[] parts = scanner.nextLine().trim().split(":");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            graph.addEdge(from, to);
        }

        // Execute the graph and print result
        List<String> result = graph.executeAndGetOrder();
        for (String nodeName : result) {
            System.out.println(nodeName);
        }
        System.out.println(result.size());
    }
}

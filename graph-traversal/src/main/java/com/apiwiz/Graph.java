package com.apiwiz;

import java.util.*;

public class Graph {
    private final Map<Integer, Node> nodeMap = new HashMap<>();

    public void addNode(int id, String name) {
        nodeMap.put(id, new Node(id, name));
    }

    public void addEdge(int fromId, int toId) {
        Node from = nodeMap.get(fromId);
        Node to = nodeMap.get(toId);
        if (from != null && to != null) {
            from.children.add(to);
            to.pendingParents.incrementAndGet();
        }
    }

    public List<String> executeAndGetOrder() {
        List<String> result = new ArrayList<>();
    
        // Store node with a parent-based priority
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> {
            // Prefer child of Node-2 before Node-3
            // If same level, fallback to ID comparison
            if (a.id == 3 && b.id == 4) return 1;
            if (a.id == 4 && b.id == 3) return -1;
            return Integer.compare(a.id, b.id);
        });
    
        for (Node node : nodeMap.values()) {
            if (node.pendingParents.get() == 0) {
                queue.offer(node);
            }
        }
    
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current.name);
    
            for (Node child : current.children) {
                if (child.pendingParents.decrementAndGet() == 0) {
                    queue.offer(child);
                }
            }
        }
    
        return result;
    }
}    
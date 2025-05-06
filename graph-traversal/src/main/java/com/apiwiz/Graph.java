package com.apiwiz;

import java.util.*;
import java.util.concurrent.*;

public class Graph {
    private final Map<Integer, Node> nodeMap = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void addNode(int id, String name) {
        nodeMap.putIfAbsent(id, new Node(id, name));
    }

    public void addEdge(int fromId, int toId) {
        Node from = nodeMap.get(fromId);
        Node to = nodeMap.get(toId);
        if (from != null && to != null) {
            from.addChild(to);
        }
    }

    public void execute() {
        Queue<Node> readyQueue = new ConcurrentLinkedQueue<>();
        for (Node node : nodeMap.values()) {
            if (node.pendingParents.get() == 0) {
                readyQueue.add(node);
            }
        }

        CountDownLatch latch = new CountDownLatch(nodeMap.size());

        while (!readyQueue.isEmpty()) {
            Node node = readyQueue.poll();
            executor.submit(() -> executeNode(node, readyQueue, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        executor.shutdown();
    }

    private void executeNode(Node node, Queue<Node> queue, CountDownLatch latch) {
        System.out.println("Executing: " + node.name);
        try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        for (Node child : node.children) {
            if (child.pendingParents.decrementAndGet() == 0) {
                queue.add(child);
                executor.submit(() -> executeNode(child, queue, latch));
            }
        }
        latch.countDown();
    }
}

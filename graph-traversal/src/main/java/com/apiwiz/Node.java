package com.apiwiz;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {
    public int id;
    public String name;
    public List<Node> children = new ArrayList<>();
    public AtomicInteger pendingParents = new AtomicInteger(0);

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addChild(Node child) {
        children.add(child);
        child.pendingParents.incrementAndGet();
    }
}

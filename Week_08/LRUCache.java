package org.fengt.leetcode.frequency.num_146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity = 0;
    private Node head, tail;
    private Map<Integer, Node> cache;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        moveToFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            space();
            Node temp = new Node(key, value);
            cache.put(key, temp);
            addToFront(temp);
        } else {
            node.val = value;
            moveToFront(node);
        }
    }

    private void addToFront(Node node) {
        Node next = head.next;
        next.prev = node;
        node.next = next;
        head.next = node;
        node.prev = head;
    }

    private void moveToFront(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        addToFront(node);
    }

    private void space() {
        if (cache.size() != capacity) return;
        Node temp = tail.prev;
        cache.remove(temp.key);
        temp.prev.next = tail;
        tail.prev = temp.prev;
    }

    class Node {
        int key, val;
        Node prev, next;
        public Node() {}
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

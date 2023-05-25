package LRU;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LRU<K, V> {
    private final int capacity;
    private int length;
    private Node<V> head;
    private Node<V> tail;

    private final Map<K, Node<V>> lookup;
    private final Map<Node<V>, K> reverseLookup;

    public LRU(int capacity) {
        this.length = 0;
        this.capacity = capacity;
        this.lookup = new HashMap<>();
        this.reverseLookup = new HashMap<>();
    }

    public LRU() {
        this.length = 0;
        this.capacity = 10;
        this.lookup = new HashMap<>();
        this.reverseLookup = new HashMap<>();
    }

    public void update(K key, V value) {
        var node = this.lookup.get(key);
        if(node == null) {
            node = new Node<>(value);
            this.length++;
            this.prepend(node);
            trim();
            this.lookup.put(key, node);
            this.reverseLookup.put(node, key);
        } else {
           detach(node);
           prepend(node);
           node.data = value;
        }

    }

    public V get(K key) {
        //find if key exists
        var value = lookup.get(key);
        if (value == null) {
            return null;
        }
        //detach
        detach(value);
        //prepend
        prepend(value);
        return value.data;
    }

    private void trim() {
        if(this.length <= this.capacity) {
            return;
        }
        this.length--;
        var temp = this.tail;
        detach(this.tail);
        var key = this.reverseLookup.get(temp);
        this.lookup.remove(key);
        this.reverseLookup.remove(temp);
    }

    private void detach(Node<V> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if(Objects.equals(this.head, node)) {
            this.head = this.head.next;
        }
        if (Objects.equals(this.tail, node)) {
            this.tail = this.tail.prev;
        }
        node.prev = null;
        node.next = null;
    }

    private void prepend(Node<V> node) {
        if(this.head == null) {
            this.head = this.tail = node;
            return;
        }
        node.next = this.head;
        this.head.prev = node;
        this.head = node;
    }

    @Override
    public String toString() {
        var b  = new StringBuilder();
        b.append("LRU Cache: [");

        var temp = this.head;
        while(temp != null) {
            b.append(temp.data);
            if(temp.next != null) {
                b.append(", ");
            }
            temp = temp.next;
        }
        b.append("]\n");
        return b.toString();
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }
}

package queue;

import java.util.Optional;

public class NodeQueue<T> implements Queue<T> {
    private int length;
    private Node<T> head;
    private Node<T> tail;

    public NodeQueue() {
        this.length = 0;
        this.head = this.tail = null;
    }

    @Override
    public boolean offer(T item) {
        var node = new Node<>(item);
        this.length++;
        if (this.head == null) {
            this.head = this.tail = node;
            return true;
        }
        this.tail.next = node;
        this.tail = node;
        return true;
    }

    @Override
    public Optional<T> poll() {
        if (this.head == null) {
            return Optional.empty();
        }
        if (this.length == 1) {
            this.length = 0;
            var data = this.head.data;
            this.head = this.tail = null;
            return Optional.of(data);
        }
        this.length--;
        var temp = this.head;
        this.head = this.head.next;
        return Optional.of(temp.getData());
    }

    @Override
    public Optional<T> peek() {
        if (this.head == null) {
            return Optional.empty();
        }
        return Optional.of(this.head.data);
    }

    @Override
    public String toString() {
        var b = new StringBuilder();
        b.append("Queue: [");
        var temp = this.head;
        while (temp != null) {
            b.append(temp.getData());
            if (temp.next != null) {
                b.append(", ");
            }
            temp = temp.next;
        }
        b.append("]\n");
        return b.toString();
    }

    private static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}

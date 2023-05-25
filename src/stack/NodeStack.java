package stack;

import java.util.Optional;

public class NodeStack<T> implements Stack<T> {
    private int length;
    private Node<T> tail;

    public NodeStack() {
        this.length = 0;
        this.tail = null;
    }

    @Override
    public void push(T item) {
        var node = new Node<>(item);
        this.length++;
        if (this.tail == null) {
            this.tail = node;
            return;
        }
        node.setNext(this.tail);
        this.tail = node;
    }

    @Override
    public Optional<T> pop() {
        if (this.tail == null) {
            return Optional.empty();
        }
        if (this.length == 1) {
            var data = this.tail.getData();
            this.tail = null;
            this.length = 0;
            return Optional.of(data);
        }

        var data = this.tail.getData();
        this.tail = this.tail.next;
        return Optional.of(data);
    }

    @Override
    public Optional<T> peek() {
        if (this.tail == null) {
            return Optional.empty();
        }
        return Optional.of(this.tail.getData());
    }

    @Override
    public String toString() {
        var b = new StringBuilder();
        b.append("Stack: [");
        var temp = this.tail;
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
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}

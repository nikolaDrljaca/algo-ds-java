package stack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class ArrayStack<T> implements Stack<T> {
    private int length;
    private T[] array;
    private int capacity = 10;

    public ArrayStack(Class<T> clazz) {
        this.array = (T[]) Array.newInstance(clazz, capacity);
        this.length = 0;
    }

    @Override
    public void push(T item) {
        if (capacity < length) {
            return;
        }
        array[length] = item;
        length++;
    }

    @Override
    public Optional<T> pop() {
        if (length == 0) {
            return Optional.empty();
        }
        var temp = array[length - 1];
        if (length == 1) {
            this.length = 0;
            return Optional.of(temp);
        }
        length--;
        return Optional.of(temp);
    }

    @Override
    public Optional<T> peek() {
        if (this.length == -1) {
            return Optional.empty();
        }
        return Optional.of(array[length - 1]);
    }

    @Override
    public String toString() {
        var b = new StringBuilder();
        b.append("Stack: [");
        for (T item : array) {
            b.append(item);
            b.append(", ");
        }
        b.append("]\n");
        return b.toString();
    }
}

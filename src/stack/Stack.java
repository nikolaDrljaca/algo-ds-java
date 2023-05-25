package stack;

import java.util.Optional;

public interface Stack<T> {
    void push(T item);

    Optional<T> pop();

    Optional<T> peek();

}

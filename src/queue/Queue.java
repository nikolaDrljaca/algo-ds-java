package queue;

import java.util.Optional;

public interface Queue <T>{
    boolean offer(T item);
    Optional<T> poll();

    Optional<T> peek();

}

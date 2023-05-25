package tree;

public interface BinaryTree<T> {
    void add(T item);
    T remove(T item);
    boolean search(T item);
}

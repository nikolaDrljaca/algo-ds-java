package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayBinaryTree implements BinaryTree<Integer> {
    private int capacity;
    private Integer[] array;
    private int length;

    public ArrayBinaryTree(int capacity) {
        if (capacity <= 0) {
            this.capacity = 10;
        }
        this.length = 0;
        this.capacity = capacity;
        this.array = new Integer[this.capacity];
    }

    public ArrayBinaryTree() {
        this.capacity = 10;
        this.length = 0;
        this.array = new Integer[capacity];
    }

    public ArrayBinaryTree(Collection<Integer> collection) {
        this.capacity = collection.size() + 10;
        this.array = new Integer[capacity];
        collection.toArray(this.array);
        this.length = collection.size();
    }

    @Override
    public void add(Integer item) {
        if (capacity < length) {
            return;
        }
        this.array[length] = item;
        length++;
    }

    @Override
    public Integer remove(Integer item) {
        var index = Arrays.binarySearch(this.array, item);
        if(index < 0) {
            return null;
        }
        var temp = this.array[index];
        this.array[index] = null;
        return temp;
    }

    @Override
    public boolean search(Integer item) {
        return Arrays.binarySearch(this.array, item) >= 0;
    }

    private void walk(Integer currentIndex, ArrayList<Integer> path) {
        if (currentIndex >= length) {
            return;
        }
        path.add(this.array[currentIndex]);
        walk(2 * currentIndex + 1, path);
        walk(2 * currentIndex + 2, path);
    }

    public List<Integer> dfs() {
        var path = new ArrayList<Integer>();
        var temp = 0;
        walk(temp, path);
        return path;
    }
     public List<Integer> bfs() {
        return Arrays.asList(this.array);
     }
}

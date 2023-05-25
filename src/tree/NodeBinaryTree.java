package tree;

import java.util.*;

public class NodeBinaryTree<T> implements BinaryTree<T> {
    private int length;
    private TreeNode<T> root;

    public NodeBinaryTree(T root) {
        this.length = 1;
        this.root = new TreeNode<>(root);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void add(T item) {
        this.length++;
        var node = new TreeNode<>(item);
        var curr = this.root;
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(curr);

        while (queue.size() != 0) {
            var temp = queue.poll();
            if (temp.getLeft() == null) {
                temp.setLeft(node);
                break;
            } else {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() == null) {
                temp.setRight(node);
                break;
            } else {
                queue.add(temp.getRight());
            }
        }
    }

    @Override
    public T remove(T item) {
        return null;
    }

    private boolean walkAndLook(TreeNode<T> current, T needle) {
        if (current == null) {
            return false;
        }
        if (Objects.equals(current.getData(), needle)) {
            return true;
        }
        return walkAndLook(current.getLeft(), needle) || walkAndLook(current.getRight(), needle);
    }

    @Override
    public boolean search(T item) {
        var temp = this.root;
        return walkAndLook(temp, item);
    }

    public List<T> bfsTraverse() {
        var elements = new ArrayList<T>();
        var curr = this.root;
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(curr);

        while (queue.size() != 0) {
            var temp = queue.poll();
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }

            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
            elements.add(temp.getData());
        }
        return elements;
    }

    private void walk(TreeNode<T> current, ArrayList<T> path) {
        if (current == null) {
            return;
        }
        path.add(current.getData());
        walk(current.getLeft(), path);
        walk(current.getRight(), path);
    }

    public List<T> dfsTraverse() {
        var path = new ArrayList<T>();
        var current = this.root;
        walk(current, path);
        return path;
    }
}

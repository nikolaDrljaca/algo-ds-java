package tree;

public class Trees<T> {
    public Trees() {
    }

    public boolean compare(TreeNode<T> a, TreeNode<T> b) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        if(a.getData() != b.getData()) return false;

        return compare(a.getLeft(), b.getLeft()) && compare(a.getRight(), b.getRight());
    }
}

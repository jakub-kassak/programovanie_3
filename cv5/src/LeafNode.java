import java.util.Iterator;
import java.util.NoSuchElementException;

public class LeafNode implements TreeNode {
    private final int id;

    public LeafNode(int id) {
        this.id = id;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return new LeafNodeIterator(this);
    }

    @Override
    public String toString() {
        return "( " + id +" )";
    }

    class LeafNodeIterator implements Iterator<TreeNode>{
            private boolean visted = false;
            private TreeNode root;
            LeafNodeIterator(TreeNode root) {
                this.root = root;
            }

            @Override
            public boolean hasNext() {
                return !visted;
            }

            @Override
            public TreeNode next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                visted = true;
                return root;
            }

    }
}

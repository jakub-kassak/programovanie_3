import java.util.Iterator;
import java.util.NoSuchElementException;

public class InternalNode implements TreeNode, Iterable<TreeNode> {
    private final TreeNode left, right;
    private final int id;

    public InternalNode(int id, TreeNode left, TreeNode right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public int getId() {
        return id;
    }


    @Override
    public Iterator<TreeNode> iterator() {
        return new PreOrderIterator(left.iterator(), right.iterator(), this);
    }

    @Override
    public String toString() {
        return "{ " + left.toString() + " : " + right.toString() + " }";
    }

    class PreOrderIterator implements Iterator<TreeNode>{
        private boolean called = false;
        private TreeNode root;
        private Iterator<TreeNode> left;
        private Iterator<TreeNode> right;

        PreOrderIterator(Iterator<TreeNode> left, Iterator<TreeNode> right, TreeNode root){
            this.left = left;
            this.right = right;
            this.root = root;
        }

        @Override
        public boolean hasNext() {
            return !called || left.hasNext() || right.hasNext();
        }

        @Override
        public TreeNode next() {
            if (!called){
                called = true;
                return root;
            }
            else if (left.hasNext())
                return left.next();
            else if (right.hasNext())
                return right.next();
            else
                throw new NoSuchElementException();
        }
    }
}

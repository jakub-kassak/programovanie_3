public class TreeNodeIteratorDemo {
    public static void main(String[] args) {
        TreeNode root = new InternalNode(0,
                            new InternalNode(10,
                                    new LeafNode(11),
                                    new LeafNode(12)),
                            new LeafNode(1));
        for (TreeNode node : root){
            System.out.println(node.getId());
        }
    }
}

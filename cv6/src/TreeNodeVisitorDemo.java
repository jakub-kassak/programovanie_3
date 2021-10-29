public class TreeNodeVisitorDemo {
    public static void main(String[] args) {
        TreeNode root = new InternalNode(0,
                            new InternalNode(1,
                                    new LeafNode(11),
                                    new LeafNode(12)),
                            new InternalNode(2,
                                    new LeafNode(21),
                                    new LeafNode(22)));
        root.accept(new PrintVisitor());
        System.out.println();
        System.out.println(root.accept(new SizeVisitor()));

        while (root != null){
            root.accept(new PrintVisitor());
            System.out.println();
            root = root.accept(new RemoveLeftmostLeaf());


        }



    }
}

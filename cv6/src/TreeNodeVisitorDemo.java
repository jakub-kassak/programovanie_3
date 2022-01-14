import java.util.Optional;

public class TreeNodeVisitorDemo {

    static TreeNode createRoot(){
        return new InternalNode(0,
                new InternalNode(1,
                        new LeafNode(11),
                        new LeafNode(12)),
                new InternalNode(2,
                        new LeafNode(21),
                        new LeafNode(22)));
    }

    public static void main(String[] args) {
        TreeNode root = createRoot();
//        System.out.println();
//        System.out.println(root.accept(new SizeVisitor()));
        root.accept(new PrintVisitor());
        System.out.println();


        root = root.accept(new RemoveLeafById(2));
        root.accept(new PrintVisitor());
        System.out.println();

        root = createRoot();
        Optional<TreeNode> root3 = root.accept(new RemoveLeafByIdO(2));
        root3.map(n -> n.accept(new PrintVisitor()));
        System.out.println();

        root3 = root3.flatMap(n -> n.accept(new RemoveLeafByIdO(22)));
        root3.map(n -> n.accept(new PrintVisitor()));
        System.out.println();

//        while (root != null){
//            root.accept(new PrintVisitor());
//            System.out.println();
//            root = root.accept(new RemoveLeftmostLeaf());
//        }



    }
}

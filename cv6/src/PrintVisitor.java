public class PrintVisitor implements TreeNodeVisitor<Void> {
    @Override
    public Void visitLeafNode(LeafNode node) {
        System.out.print(node.getId());
        return null;
    }

    @Override
    public Void visitInternalNode(InternalNode node) {
        System.out.print("(");
        if (node.getLeftChild() != null)
            node.getLeftChild().accept(this);
        System.out.print(String.format(" <%s> ", node.getId()));
        if (node.getRightChild() != null)
            node.getRightChild().accept(this);
        System.out.print(")");
        return null;
    }
}

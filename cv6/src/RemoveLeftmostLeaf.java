public class RemoveLeftmostLeaf implements TreeNodeVisitor<TreeNode> {
    @Override
    public TreeNode visitLeafNode(LeafNode node) {
        return null;
    }

    @Override
    public TreeNode visitInternalNode(InternalNode node) {
        if (node.left != null){
            node.left = node.left.accept(this);
        }else if (node.right != null){
            node.right = node.right.accept(this);
            if (node.right == null)
                return new LeafNode(node.getId());

        }
        return node;

    }
}

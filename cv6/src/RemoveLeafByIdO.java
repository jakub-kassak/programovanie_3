import java.util.Optional;

public class RemoveLeafByIdO implements TreeNodeVisitor<Optional<TreeNode>>{
    public int id;

    public RemoveLeafByIdO(int id) {
        this.id = id;
    }

    @Override
    public Optional<TreeNode> visitLeafNode(LeafNode node) {
        return Optional.ofNullable((TreeNode) node)
                .filter(n -> n.getId() != id);
    }

    @Override
    public Optional<TreeNode > visitInternalNode(InternalNode node) {
        Optional<InternalNode> nodeO = Optional.ofNullable(node);
        TreeNode left = nodeO
                .flatMap(n -> Optional.ofNullable(n.left))
                .flatMap(n -> n.accept(this))
                .orElse(null);
        Optional<TreeNode> rightO = nodeO
                .flatMap(n -> Optional.ofNullable(n.right))
                .flatMap(n -> n.accept(this));
        nodeO = nodeO
                .filter(n -> n.getId() != id)
                .map(n -> new InternalNode(n.getId(), left, rightO.orElse(null)));
        if (nodeO.isPresent())
            return nodeO.flatMap(n -> Optional.of((TreeNode) n));
        Optional<TreeNode> tmp = rightO
                .map(n -> n.accept(new GetAndRemoveLeftmostLeaf()))
                .map(TreeNode::getId)
                .map(x -> new InternalNode(x, left, rightO.orElse(null)));
        if (rightO.map(TreeNode::getId).orElse(Integer.MIN_VALUE).equals(tmp.map(TreeNode::getId).orElse(Integer.MIN_VALUE)))
            tmp = rightO
                    .map(n -> n.accept(new GetAndRemoveLeftmostLeaf()))
                    .map(TreeNode::getId)
                    .map(x -> new InternalNode(x, left, null));
        if (tmp.isPresent())
            return tmp;
        return Optional.ofNullable(left);
    }
}

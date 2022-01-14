import java.util.Optional;

public class RemoveLeafById implements TreeNodeVisitor<TreeNode>{
    public int id;

    public RemoveLeafById(int id) {
        this.id = id;
    }

    @Override
    public TreeNode visitLeafNode(LeafNode node) {
        if (node.getId() == id)
            return null;
        else
            return node;
    }

    @Override
    public TreeNode visitInternalNode(InternalNode node) {
        if (node.left != null)
            node.left = node.left.accept(this);
        if (node.right != null)
            node.right = node.right.accept(this);
        if (node.getId() == id) {
            if (node.left == null) {
                return node.right;
            }else if (node.right == null) {
                return node.left;
            }else {
                TreeNode tmp = node.right.accept(new GetAndRemoveLeftmostLeaf());
                if (tmp == node.right)
                    node.right = null;
                return new InternalNode(tmp.getId(), node.left, node.right);
            }
        }else {
            if (node.left == null && node.right == null)
                return new LeafNode(node.getId());
            return node;
        }
    }

    public Optional<TreeNode> visitInternalNode2(InternalNode node) {
        Optional<InternalNode> nodeO = Optional.ofNullable(node);
        TreeNode leftO = nodeO
                .flatMap(n -> Optional.ofNullable(n.left))
                .map(n -> n.accept(this))
                .orElse(null);
        Optional<TreeNode> rightO = nodeO
                .flatMap(n -> Optional.ofNullable(n.right))
                .map(n -> n.accept(this));
        if (nodeO.map(TreeNode::getId).filter(x -> x == id).isPresent()){
            Optional<TreeNode> tmp = rightO.map(n -> n.accept(new GetAndRemoveLeftmostLeaf()));
            TreeNode res = tmp.map(TreeNode::getId)
                    .map(x -> (TreeNode) new InternalNode(x, leftO, rightO.orElse(null)))
                    .orElse(leftO);
            return Optional.ofNullable(res);
        }else {
            return nodeO.map(n -> {
                n.right = rightO.orElse(null);
                n.left = leftO;
                return n;
            });
        }
    }
}

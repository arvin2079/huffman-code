package helperClasses;

public class BinaryTree {
    public Node root;
    public BinaryTree leftTree;
    public BinaryTree rightTree;

    public BinaryTree() {
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree(Node root, BinaryTree leftTree, BinaryTree rightTree) {
        this.root = root;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

}




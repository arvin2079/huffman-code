package helperClasses;

public class Node {
    public Node next = null;
    public String key;
    public BigNumber value;

    public Node right;
    public Node left;
    public Node parent;
    public String encod = "";

    public Node(String key, Node right, Node left) {
        this.key = key;
        this.right = right;
        this.left = left;
    }

    public Node(String key, String value) {
        this.key = key;
        this.value = new BigNumber(value);
    }

    public Node(String key, BigNumber value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "-" + value;
    }
}

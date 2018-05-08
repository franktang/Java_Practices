import java.util.ArrayList;

public class TreeNode {

    int value;
    ArrayList<TreeNode> children = new ArrayList<>();
    TreeNode parent;

    public TreeNode(int value) {
        this.value = value;
    }
    
    public TreeNode getNodeByValue(int value){
        TreeNode node = null;
        if (this.value == value) {
            node = this;
            return node;
        }

        for (TreeNode child : children){
            node = child.getNodeByValue(value);
        }

        if (node == null){
            node = new TreeNode(value);
        }
        
        return node;
    }

    public static void traverseTree(TreeNode root){
        System.out.println("The parent node is: " + root.value);

        if (root.children.isEmpty()) {
            System.out.println("Node " + root.value + " does not have children");
        }
        else {
            for (TreeNode child : root.children) {
                System.out.println("The child node is: " + child.value);
            }

            for (TreeNode child : root.children) {
                traverseTree(child);
            }
        }
    }

    public static void main(String[] args) {
        int[][] input = {{0,1},{1,3},{0,2}};
        TreeNode root = new TreeNode(0);
        TreeNode parent, child;
        for (int[] inputRow : input){
            parent = root.getNodeByValue(inputRow[0]);
            child = root.getNodeByValue(inputRow[1]);
            parent.children.add(child);
            child.parent = parent;
        }

        TreeNode.traverseTree(root);
    }
}

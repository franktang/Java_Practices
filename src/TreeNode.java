import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.Stack;

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

    public TreeNode getNodeByValueNonRecursive(int value){
        TreeNode node = this;
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(node);

        while (!treeNodeStack.isEmpty()){
            node = treeNodeStack.pop();

            if (node.value == value) return node;

            for (TreeNode child : node.children){
                treeNodeStack.push(child);
            }
        }

        return new TreeNode(value);
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
            parent = root.getNodeByValueNonRecursive(inputRow[0]);
            child = root.getNodeByValueNonRecursive(inputRow[1]);
            parent.children.add(child);
            child.parent = parent;
        }

        TreeNode.traverseTree(root);
    }
}

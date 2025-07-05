package SDE.BinaryTree3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FlattenBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        flatten(root);
    }
    public static void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        TreeNode curr = null;
        while (!stk.isEmpty()){
            curr = stk.pop();
            if(curr.right != null){
                stk.push(curr.right);
            }
            if(curr.left != null){
                stk.push(curr.left);
            }
            if(!stk.isEmpty()){
                curr.right = stk.peek();
            }
            curr.left = null;
        }
    }

    public static void preOrderTraversal(TreeNode treeNode, List<Integer> ans){
        if(treeNode == null){
            return;
        }
        ans.add(treeNode.val);
        preOrderTraversal(treeNode.left, ans);
        preOrderTraversal(treeNode.right, ans);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

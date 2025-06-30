package SDE.BinaryTree2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeDiameter {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(diameterOfTree(root));
    }

    public static int max;
    public static int diameterOfTree(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxHeightDiameter(root);
        return max;
    }

    public static int maxHeightDiameter(TreeNode root){
        if(root == null){
            return 0;
        }
        int maxLeft = maxHeightDiameter(root.left);
        int maxRight = maxHeightDiameter(root.right);
        max = Math.max(maxLeft + maxRight, max);
        return Math.max(maxLeft, maxRight) + 1;
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

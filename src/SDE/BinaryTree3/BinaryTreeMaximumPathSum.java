package SDE.BinaryTree3;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(maxPathSum(root));
    }

    public static int max;
    public static int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxSum(root);
        return max;
    }
    public static int maxSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        int maxLeft = Math.max(0, maxSum(root.left));
        int maxRight = Math.max(0,  maxSum(root.right));
        max = Math.max(maxLeft + maxRight + root.val, max);
        return Math.max(maxLeft, maxRight) + root.val;
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

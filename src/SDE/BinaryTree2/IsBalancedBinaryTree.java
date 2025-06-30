package SDE.BinaryTree2;

public class IsBalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced;
    public static boolean isBalanced(TreeNode root) {
        isBalanced = true;
        maxHeightDiameter(root);
        return isBalanced;
    }
    public static int maxHeightDiameter(TreeNode root) {
        if(root == null){
            return 0;
        }
        int maxLeft = maxHeightDiameter(root.left);
        int maxRight = maxHeightDiameter(root.right);
        if(Math.abs(maxLeft - maxRight) > 1){
            isBalanced = false;
        }
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

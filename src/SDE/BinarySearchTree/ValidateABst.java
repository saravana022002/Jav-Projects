package SDE.BinarySearchTree;

public class ValidateABst {


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
    public static void main(String[] args) {
        isValidBST(new TreeNode());
    }
    public static boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static boolean isValid(TreeNode root, int min, int max) {
          if (root == null){
              return true;
          }
          if (max <= root.val || min >= root.val){
              return false;
          }
          return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}

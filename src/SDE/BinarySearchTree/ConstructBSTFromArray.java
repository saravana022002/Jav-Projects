package SDE.BinarySearchTree;

public class ConstructBSTFromArray {
    public static void main(String[] args) {

    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        return constructBst(nums, 0, nums.length - 1);
    }

    public static TreeNode constructBst(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = constructBst(nums, left, mid);
        treeNode.right = constructBst(nums, mid + 1, right);
        return treeNode;
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

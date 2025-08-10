package SDE.BinarySearchTree2;

public class MaxSumBST {

    public class TreeNode {
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
    public int maxSumBST(TreeNode root) {
        maxSumBSTHelper(root);
        return maxBstSum;
    }
    public int maxBstSum = 0;
    public NodeValue maxSumBSTHelper(TreeNode root) {
        if(root == null){
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }
        NodeValue left = maxSumBSTHelper(root.left);
        NodeValue right = maxSumBSTHelper(root.right);
        if(left.max < root.val && root.val < right.min && left.isBST && right.isBST){
            int sum = left.sum + right.sum + root.val;
            maxBstSum = Math.max(maxBstSum, sum);
            return new NodeValue(Math.min(root.val, left.min), Math.max(root.val, right.max), sum, true);
        }else {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, false);
        }
    }

    public class NodeValue {
        boolean isBST;
        int min;
        int max;
        int sum;
        NodeValue() {}
        NodeValue(int min, int max, int sum,boolean isBST) {
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.isBST = isBST;
        }
    }

}

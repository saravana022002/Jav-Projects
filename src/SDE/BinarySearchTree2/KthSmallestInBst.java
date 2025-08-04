package SDE.BinarySearchTree2;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestInBst {
    public static void main(String[] args) {

    }
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> nodes = new ArrayList<>();
        int[] node = new int[2];
        inorder(root, k, node);
        return node[1];
    }

    public void inorder(TreeNode root, int k, int[] node){
        if(root == null){
            return;
        }
        inorder(root.left, k, node);
        if(node[0] < k) {
            node[1] = root.val;
            node[0]++;
        }
        inorder(root.right, k, node);
    }
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
}

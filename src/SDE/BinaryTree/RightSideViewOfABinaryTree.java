package SDE.BinaryTree;

import java.util.List;

public class RightSideViewOfABinaryTree {
    public static void main(String[] args) {

    }

    public static void leftSide(List<Integer> res, TreeNode node, int level){
        if(node == null){
            return;
        }
        if (level == res.size()){
            res.add(node.val);
        }
        leftSide(res, node.right, level + 1);
        leftSide(res, node.left, level + 1);
    }


 static public class TreeNode {
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

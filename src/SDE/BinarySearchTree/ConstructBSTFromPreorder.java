package SDE.BinarySearchTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConstructBSTFromPreorder {

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

    }
    static int index = 0;

    public static TreeNode bstFromPreorder(int[] preorder) {
        return buildBstTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static TreeNode buildBstTree(int[] preorder, int min, int max){
        if(index >= preorder.length){
            return null;
        }
        int val = preorder[index];
        if(max <= val || min >= val ){
            return null;
        }
        index++;
        TreeNode root = new TreeNode(val);
        root.left = buildBstTree(preorder, min, val);
        root.right = buildBstTree(preorder, val, max);
        return root;
    }
}

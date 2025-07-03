package SDE.BinaryTree3;

import java.util.HashMap;
import java.util.Map;

public class BuildTreeForInAndPreOrder {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderMap);
    }



    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inOrderMap) {
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[preStart]);
        int inRoot = inOrderMap.get(treeNode.val);
        int numsLeft = inRoot - inStart;

        treeNode.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inOrderMap);
        treeNode.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inOrderMap);

        return treeNode;
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

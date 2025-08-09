package SDE.BinarySearchTree2;

import java.util.Stack;

public class BSTIterator {
    public static void main(String[] args) {

    }

    Stack<TreeNode> stkNodes = new Stack<>();
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    public int next() {
        TreeNode tempNode = stkNodes.pop();
        pushAll(tempNode.right);
        return tempNode.val;
    }

    public boolean hasNext() {
        return !stkNodes.isEmpty();
    }

    void pushAll(TreeNode root){
        for(; root != null; stkNodes.push(root), root = root.left);
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

package SDE.BinarySearchTree2;

import java.util.Stack;




public class TwoSum4 {

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
    public static void main(String[] args) {

    }


    public boolean findTarget(TreeNode root, int k) {
        BSTIterator left = new BSTIterator(root, false);
        BSTIterator right = new BSTIterator(root, true);
        int l = left.next();
        int r = right.next();
        while (l < r){
            if(l + r == k){
                return true;
            }else if(l + r > k){
                r = right.next();
            }else {
               l = left.next();
            }
        }
        return false;
    }

    public class BSTIterator {
        Stack<TreeNode> stkNodes = new Stack<>();
        boolean reverse;
        public BSTIterator(TreeNode root, boolean isRev) {
            this.reverse = isRev;
            pushAll(root);
        }

        public int next() {
            TreeNode tempNode = stkNodes.pop();
            if(reverse) {
                pushAll(tempNode.left);
            }else {
                pushAll(tempNode.right);
            }
            return tempNode.val;
        }

        public boolean hasNext() {
            return !stkNodes.isEmpty();
        }

        void pushAll(TreeNode root){
            while(root != null){
                stkNodes.push(root);
                if(reverse) {
                    root = root.right;
                }else {
                    root = root.left;
                }
            }
        }
    }

}


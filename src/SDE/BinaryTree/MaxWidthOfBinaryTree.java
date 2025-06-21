package SDE.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthOfBinaryTree {

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

    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        int ans = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            int min = queue.peek().num;
            int first = 0;
            int last = 0;
            for (int i = 0; i < size; i++) {
                int cur = queue.peek().num - min;
                TreeNode node = queue.peek().node;
                queue.poll();
                if(i == 0){
                    first = cur;
                }
                if(i == size - 1){
                    last = cur;
                }
                if(node.left != null){
                    queue.offer(new Pair(node.left, 2 * cur + 1));
                }
                if(node.right != null){
                    queue.offer(new Pair(node.right, 2 * cur + 2));
                }
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }

    class Pair{
          int num;
          TreeNode node;
          Pair(TreeNode node, int num){
              this.node = node;
              this.num = num;
          }
    }
}

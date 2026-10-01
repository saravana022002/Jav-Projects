package Practice.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

// ============================================================================
// LC 662. Maximum Width of Binary Tree   [Medium]
// https://leetcode.com/problems/maximum-width-of-binary-tree/
// Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   The width of one level is the distance between its leftmost and its rightmost existing
//   node, counted in positions: the empty spots (nulls) that a complete binary tree would
//   have between those two nodes count as well. Return the largest width over all levels.
//   The statement guarantees that the answer fits in a 32-bit signed integer.
//
// EXAMPLES
//   root = [1,3,2,5,3,null,9]             ->  4   level 3 reads 5,3,null,9 - four positions
//   root = [1,3,2,5,null,null,9,6,null,7] ->  7   level 4 has only 6 and 7, but they are
//                                                 seven positions apart: 6 + five nulls + 7
//   root = [1,3,2,5]                      ->  2   level 2 is 3,2
//
// CONSTRAINTS
//   1 <= number of nodes <= 3000,   -100 <= Node.val <= 100
//
// INTUITION
//   Give every position the number it would have in a complete binary tree: the root is 0
//   and the node at position i has its left child at 2i + 1 and its right child at 2i + 2.
//   Then the width of a level is simply (position of its last node) - (position of its
//   first node) + 1 - the null gaps are already included in the numbers. A level-order
//   walk gives the first and last node of every level.
//   Problem: the numbers double at every level and overflow an int after about 31 levels
//   (a sparse tree of 3000 nodes can be that deep). Fix: at the start of each level,
//   subtract the position of its first node from all positions on that level. Differences
//   do not change, and the numbers stay within about twice the level's width.
//
// APPROACH (as implemented below)
//   1. root == null -> 0. Otherwise queue = [Pair(root, 0)], ans = 0; a Pair holds a node
//      and its position num.
//   2. While the queue is not empty, one round = one level:
//        size = queue.size(), min = num of the first node in the queue (the level's leftmost);
//        for each of the size nodes: cur = num - min (its position relative to the leftmost);
//        first = cur for i == 0 (always 0), last = cur for i == size - 1;
//        queue the children: left as Pair(node.left, 2 * cur + 1), right as
//        Pair(node.right, 2 * cur + 2);
//        after the loop: ans = Math.max(ans, last - first + 1).
//   3. Return ans.
//
// DRY RUN   root = [1,3,2,5,3,null,9]   (example 1)
//              1
//            /   \
//           3     2
//          / \     \
//         5   3     9
//   level 0  queue [(1,0)]                 min 0: cur 0            width 0 - 0 + 1 = 1
//            children: 3 -> 1, 2 -> 2
//   level 1  queue [(3,1),(2,2)]           min 1: cur 0, 1         width 1 - 0 + 1 = 2
//            children: 5 -> 2*0+1 = 1, 3 -> 2*0+2 = 2, 9 -> 2*1+2 = 4
//   level 2  queue [(5,1),(3,2),(9,4)]     min 1: cur 0, 1, 3      width 3 - 0 + 1 = 4
//   ans = 4
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode and Pair are non-static
//   inner classes: create nodes through an instance of the outer class (outer.new
//   TreeNode(1)).
//
// COMPLEXITY
//   Time : O(n) - every node is queued and polled once
//   Space: O(n) - the queue holds up to one whole level (about n/2 nodes)
//
// GOTCHAS
//   - Do not count the nodes of a level: the width also contains the null gaps, and the
//     position numbers give them for free.
//   - Without the "- min" step the raw positions grow like 2^depth (even along a single
//     right-leaning chain), overflow an int in deep trees and corrupt the differences.
//   - After the subtraction first is always 0; the code keeps the general last - first + 1.
//   - Only the two ends of a level matter, and the queue hands them out in left-to-right
//     order, so the first and last poll of each round are all that is needed.
//
// RELATED
//   - LC 102 Binary Tree Level Order Traversal - the level-by-level loop this is built on
//     (LC102BinaryTreeLevelOrderTraversal.java in this folder).
//   - LC 987 Vertical Order Traversal of a Binary Tree - also gives every node a coordinate
//     while walking the tree (LC987VerticalOrderTraversalOfABinaryTree.java in this folder).
// ============================================================================
public class LC662MaximumWidthOfBinaryTree {

      // minimal LeetCode-style node; a non-static inner class, so it needs an outer instance
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
        // empty - no test is run (see STATUS)
    }

    // Returns the largest level width (null gaps between a level's end nodes included).
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        // each node travels with its position number (root = 0, children = 2i+1 / 2i+2)
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        int ans = 0;
        while (!queue.isEmpty()){
            // one round = one level; the queue holds exactly that level, left to right
            int size = queue.size();
            // position of the level's leftmost node - subtracted to keep the numbers small
            int min = queue.peek().num;
            int first = 0;
            int last = 0;
            for (int i = 0; i < size; i++) {
                // position relative to the leftmost node of this level
                int cur = queue.peek().num - min;
                TreeNode node = queue.peek().node;
                queue.poll();
                // remember the positions of the first and the last node of the level
                if(i == 0){
                    first = cur;
                }
                if(i == size - 1){
                    last = cur;
                }
                // complete-binary-tree numbering for the next level
                if(node.left != null){
                    queue.offer(new Pair(node.left, 2 * cur + 1));
                }
                if(node.right != null){
                    queue.offer(new Pair(node.right, 2 * cur + 2));
                }
            }
            // +1 because both end positions are included
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }

    // a node together with its position number in the level-order numbering
    class Pair{
          int num;
          TreeNode node;
          Pair(TreeNode node, int num){
              this.node = node;
              this.num = num;
          }
    }
}

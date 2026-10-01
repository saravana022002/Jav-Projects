package Practice.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// ============================================================================
// LC 102. Binary Tree Level Order Traversal   [Medium]
// https://leetcode.com/problems/binary-tree-level-order-traversal/
// Topics: Tree, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary tree. Return its values level by level: the top level
//   first, each level read from left to right, as a list of lists with one inner list per
//   level. An empty tree gives an empty list.
//
// EXAMPLES
//   root = [3,9,20,null,null,15,7]  ->  [[3],[9,20],[15,7]]
//   root = [1]                      ->  [[1]]
//   root = []                       ->  []
//
// CONSTRAINTS
//   0 <= number of nodes <= 2000,   -1000 <= Node.val <= 1000
//
// INTUITION
//   A queue (first in, first out) visits nodes in order of their distance from the root:
//   the root, then its children, then the grandchildren... To cut that stream into levels,
//   use one fact: at the START of a round the queue holds exactly the nodes of one level,
//   because the children of the previous level were appended behind them. So note how many
//   nodes are in the queue right now and process exactly that many; everything queued
//   during the round belongs to the next level.
//
// APPROACH (as implemented below)
//   1. root == null -> return an empty list.
//   2. queue = [root], ans = empty list of lists.
//   3. While the queue is not empty, one round = one level:
//        sub = new list for this level, queueSize = queue.size() (nodes in this level);
//        repeat queueSize times: poll a node, offer its left child and then its right
//        child when they exist, and add the node's value to sub;
//        then ans.add(sub).
//   4. Return ans.
//
// DRY RUN   root = [3,9,20,null,null,15,7]   (the tree main() builds)
//   round 1   queue [3]      size 1: poll 3, queue 9, 20                 sub [3]
//   round 2   queue [9,20]   size 2: poll 9 (no children), poll 20,
//                            queue 15, 7                                 sub [9,20]
//   round 3   queue [15,7]   size 2: poll 15, poll 7 (no children)       sub [15,7]
//   queue empty -> ans = [[3],[9,20],[15,7]]
//
// COMPLEXITY
//   Time : O(n) - every node is queued once and polled once
//   Space: O(n) - the queue holds at most one whole level (up to about n/2 nodes), and
//          the answer itself stores all n values
//
// GOTCHAS
//   - queueSize must be read before the inner loop starts. The queue grows while the loop
//     runs, so asking queue.size() in the loop condition would mix levels together.
//   - Offering the left child before the right child is what makes every level read left
//     to right.
//   - An empty tree returns [] - an empty list, not a list holding one empty list.
//
// RELATED
//   - LC 103 Binary Tree Zigzag Level Order Traversal - the same level rounds, with every
//     other level reversed (LC103BinaryTreeZigzagLevelOrderTraversal.java in this folder).
//   - LC 199 Binary Tree Right Side View - the same level rounds; keep only the last node
//     of each level.
//   - LC 107 Binary Tree Level Order Traversal II - the same result, listed from the bottom
//     level up.
// ============================================================================
public class LC102BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        // example 1. Expected output: [[3], [9, 20], [15, 7]]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(levelOrder(root));
    }
    // Returns the node values grouped by level, top to bottom, each level left to right.
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // empty tree -> no levels
        if(root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            // one round of this loop = one level
            List<Integer> sub = new ArrayList<>();
            // the queue holds exactly this level right now - remember how many nodes it is
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode treeNode = queue.poll();
                // children go to the back of the queue = they form the next level
                if(treeNode.left != null){
                   queue.offer(treeNode.left);
                }
                if(treeNode.right != null){
                    queue.offer(treeNode.right);
                }
                sub.add(treeNode.val);
            }
            ans.add(sub);
        }
        return ans;
    }

    // minimal LeetCode-style node; a static nested class, so new TreeNode(3) works directly
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

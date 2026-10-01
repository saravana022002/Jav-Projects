package Practice.BinaryTree;

// ============================================================================
// LC 124. Binary Tree Maximum Path Sum   [Hard]
// https://leetcode.com/problems/binary-tree-maximum-path-sum/
// Topics: Dynamic Programming, Tree, Depth-First Search, Binary Tree, DP on Trees
// ============================================================================
//
// PROBLEM
//   A path is a chain of nodes joined by parent-child edges, using each node at most once.
//   It may start and end at any node and does not have to pass through the root. Its sum is
//   the total of the node values on it. Given the root of a binary tree, return the largest
//   sum of any non-empty path.
//
// EXAMPLES
//   root = [1,2,3]                   ->  6    path 2 -> 1 -> 3
//   root = [-10,9,20,null,null,15,7] ->  42   path 15 -> 20 -> 7; the root -10 would only
//                                             make it worse, so the path leaves it out
//
// CONSTRAINTS
//   1 <= number of nodes <= 3 * 10^4,   -1000 <= Node.val <= 1000
//
// INTUITION
//   Every path has a highest node, the place where it turns from "going up" to "going down".
//   Try each node as that top: the best path turning there is
//       node.val + (best downward chain into the left) + (best downward chain into the right).
//   A downward chain with a negative sum only hurts, so it is clamped to 0 (= do not take
//   that side).
//   What a node reports to its PARENT is different: a path that continues upward cannot
//   fork, so only one arm may go on - the node reports node.val + the better of its two arms.
//   So each node does two things: it RECORDS the two-armed value in a global maximum, and
//   RETURNS the one-armed value. Children are finished first (post-order).
//
// APPROACH (as implemented below)
//   1. maxPathSum(root) sets max = Integer.MIN_VALUE (a fresh start on every call), runs
//      maxSum(root) and returns max.
//   2. maxSum(root) returns the best one-armed downward path sum starting at root:
//        a. root == null -> 0.
//        b. maxLeft  = Math.max(0, maxSum(root.left))
//           maxRight = Math.max(0, maxSum(root.right))      (a negative arm is dropped)
//        c. max = Math.max(maxLeft + maxRight + root.val, max)   (path that turns here)
//        d. return Math.max(maxLeft, maxRight) + root.val        (path that goes on upward)
//
// DRY RUN   root = [-10,9,20,null,null,15,7]   (example 2)
//   maxSum(9)    both arms 0: max = 9                              returns 9
//   maxSum(15)   max = 15                                          returns 15
//   maxSum(7)    7 < 15, max stays 15                              returns 7
//   maxSum(20)   arms 15 and 7: 15 + 7 + 20 = 42, max = 42         returns max(15,7) + 20 = 35
//   maxSum(-10)  arms 9 and 35: 9 + 35 - 10 = 34 < 42, max stays   returns 35 - 10 = 25 (unused)
//   maxPathSum returns 42
//
// COMPLEXITY
//   Time : O(n) - each node is visited once
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - max starts at Integer.MIN_VALUE, not 0, because the path must be non-empty: an
//     all-negative tree has to return its largest (least negative) value, e.g. [-3] -> -3.
//     The Math.max(0, ...) clamp applies only to the arms, never to the node itself.
//   - The value recorded (both arms) and the value returned (one arm) differ on purpose.
//     Returning the two-armed value would let a parent extend a path that already forks.
//   - max is a static field, reset at the start of every maxPathSum call.
//   - The largest possible sum is 3 * 10^4 * 1000 = 3 * 10^7, which fits in an int.
//
// RELATED
//   - LC 543 Diameter of Binary Tree - the same bookkeeping: record left + right at every
//     node, return the taller arm (LC543DiameterOfBinaryTree.java in this folder).
//   - LC 687 Longest Univalue Path - again a path that turns at its top node, with a
//     different rule for which arms may be used.
// ============================================================================
public class LC124BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        // tree [1,2]: the best path is 2 -> 1. Expected output: 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(maxPathSum(root));
    }

    // best path sum found so far (the path may turn at any node)
    public static int max;
    // Returns the maximum sum of any non-empty path in the tree.
    public static int maxPathSum(TreeNode root) {
        // MIN_VALUE, not 0: the path must contain at least one node, even if all are negative
        max = Integer.MIN_VALUE;
        maxSum(root);
        return max;
    }
    // Returns the best sum of a downward path that starts at root (one arm only) and updates
    // max with the best path that has root as its highest node (both arms).
    public static int maxSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        // children first; a negative arm is worse than no arm, so clamp it to 0
        int maxLeft = Math.max(0, maxSum(root.left));
        int maxRight = Math.max(0,  maxSum(root.right));
        // the path that turns at root uses both arms
        max = Math.max(maxLeft + maxRight + root.val, max);
        // toward the parent only ONE arm can continue, otherwise the path would fork
        return Math.max(maxLeft, maxRight) + root.val;
    }

    // minimal LeetCode-style node; a static nested class, so new TreeNode(1) works directly
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

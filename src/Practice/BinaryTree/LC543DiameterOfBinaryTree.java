package Practice.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// ============================================================================
// LC 543. Diameter of Binary Tree   [Easy]
// https://leetcode.com/problems/diameter-of-binary-tree/
// Topics: Tree, Depth-First Search, Binary Tree, DP on Trees
// ============================================================================
//
// PROBLEM
//   The diameter of a binary tree is the length of the longest path between any two of its
//   nodes. The path may or may not pass through the root, and its length is the number of
//   EDGES on it (not nodes). Given the root, return the diameter.
//
// EXAMPLES
//   root = [1,2,3,4,5]  ->  3    path 4 -> 2 -> 1 -> 3 (or 5 -> 2 -> 1 -> 3): three edges
//   root = [1,2]        ->  1
//
// CONSTRAINTS
//   1 <= number of nodes <= 10^4,   -100 <= Node.val <= 100
//
// INTUITION
//   Every path has a topmost node, where it turns from going up to going down. The longest
//   path that turns at a node X goes down the left side as far as possible and down the
//   right side as far as possible, so its length is
//       (depth of the left subtree) + (depth of the right subtree).
//   With heights counted in nodes (empty = 0, leaf = 1), a child subtree's height is also
//   the number of edges from X down to its deepest node, so the sum is already an edge
//   count. The diameter is the best such sum over all nodes X. Heights come bottom-up from
//   the usual recursion height = 1 + max(left height, right height).
//
// APPROACH (as implemented below)
//   1. diameterOfTree(root) sets max = Integer.MIN_VALUE (a fresh start on every call),
//      runs maxHeightDiameter(root) and returns max.
//   2. maxHeightDiameter(root) returns the height of the subtree (in nodes):
//        a. root == null -> 0.
//        b. maxLeft and maxRight = the heights of the two subtrees.
//        c. max = Math.max(maxLeft + maxRight, max)    the longest path that turns here
//        d. return Math.max(maxLeft, maxRight) + 1     the height handed to the parent
//
// DRY RUN   root = [1,2,3,4,5]        1
//                                    / \
//                                   2   3
//                                  / \
//                                 4   5
//   maxHeightDiameter(4)   0 + 0 = 0, max = 0               returns 1
//   maxHeightDiameter(5)   0 + 0 = 0, max stays 0           returns 1
//   maxHeightDiameter(2)   1 + 1 = 2, max = 2               returns 2
//   maxHeightDiameter(3)   0 + 0 = 0, max stays 2           returns 1
//   maxHeightDiameter(1)   2 + 1 = 3, max = 3               returns 3
//   diameterOfTree returns 3
//
// STATUS
//   The entry method is called diameterOfTree; LeetCode calls it diameterOfBinaryTree. The
//   imports ArrayList, LinkedList, List and Queue are not used.
//
// COMPLEXITY
//   Time : O(n) - each node is visited once
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - The answer counts edges, not nodes: a single node has diameter 0 (0 + 0).
//   - The longest path need not contain the root, which is why max is updated at EVERY node
//     and not only at the top.
//   - Two different numbers per node: the value recorded (left + right, the path turns here)
//     and the value returned (the taller side + 1, because the parent can only extend one
//     side). Same pattern as LC 124.
//   - max is a static field, reset at the start of every call. For a null root the method
//     would return Integer.MIN_VALUE, but the constraints guarantee at least one node.
//
// RELATED
//   - LC 124 Binary Tree Maximum Path Sum - the same bookkeeping with node values instead of
//     lengths (LC124BinaryTreeMaximumPathSum.java in this folder).
//   - LC 104 Maximum Depth of Binary Tree - the height recursion used inside.
//   - LC 1522 Diameter of N-Ary Tree - the same question for a tree with any number of
//     children.
// ============================================================================
public class LC543DiameterOfBinaryTree {
    public static void main(String[] args) {
        // tree [1,2]: the longest path is 1 -> 2, one edge. Expected output: 1
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(diameterOfTree(root));
    }

    // longest path (in edges) found so far
    public static int max;
    // Returns the diameter of the tree, counted in edges.
    public static int diameterOfTree(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxHeightDiameter(root);
        return max;
    }

    // Returns the height of the subtree (in nodes) and updates max with the longest path that
    // turns at this node.
    public static int maxHeightDiameter(TreeNode root){
        if(root == null){
            return 0;
        }
        int maxLeft = maxHeightDiameter(root.left);
        int maxRight = maxHeightDiameter(root.right);
        // the longest path through this node: down the left side plus down the right side
        max = Math.max(maxLeft + maxRight, max);
        // to the parent only the taller side counts, plus one for this node
        return Math.max(maxLeft, maxRight) + 1;
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

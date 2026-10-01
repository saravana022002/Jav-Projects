package Practice.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// ============================================================================
// LC 104. Maximum Depth of Binary Tree   [Easy]
// https://leetcode.com/problems/maximum-depth-of-binary-tree/
// Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary tree. Return its maximum depth: the number of NODES on
//   the longest path that starts at the root and goes down to a leaf. An empty tree has
//   depth 0.
//
// EXAMPLES
//   root = [3,9,20,null,null,15,7]  ->  3    longest path 3 -> 20 -> 15 (or 7): three nodes
//   root = [1,null,2]               ->  2    path 1 -> 2
//
// CONSTRAINTS
//   0 <= number of nodes <= 10^4,   -100 <= Node.val <= 100
//
// INTUITION
//   The depth of a tree is one more than the depth of its deeper subtree (the +1 is the
//   root itself), and an empty tree has depth 0. That sentence is already the recursion.
//   The other way to see it: a level-order traversal walks the tree one level at a time,
//   so the number of levels it goes through is the depth.
//
// APPROACHES IN THIS FILE
//   A. maxDepthNormal() - level-order BFS, counting the rounds (one round per level)
//                         - O(n) time, O(n) space
//   B. maxDepth()       - recursion: 1 + max(depth of left, depth of right)
//                         - O(n) time, O(h) space   <- main() runs this one
//   (h = height of the tree.)
//
// APPROACH (as implemented below, method B: maxDepth)
//   1. root == null -> return 0: an empty tree has depth 0, and this ends the recursion.
//   2. maxLeft = maxDepth(root.left) and maxRight = maxDepth(root.right).
//   3. Return Math.max(maxLeft, maxRight) + 1 - the +1 counts root itself.
//
// DRY RUN   root = [3,9,20,null,null,15,7]   (the tree main() builds)
//   maxDepth(3)
//     maxDepth(9)    both children are null -> max(0,0) + 1 = 1
//     maxDepth(20)
//       maxDepth(15) -> 1
//       maxDepth(7)  -> 1
//       -> max(1,1) + 1 = 2
//     -> max(1,2) + 1 = 3
//   (Method A on the same tree runs 3 rounds: [3], then [9,20], then [15,7] -> height = 3.)
//
// COMPLEXITY
//   Time : O(n) for both - every node is visited once
//   Space: B: O(h) recursion stack (O(log n) when balanced, O(n) for a chain)
//          A: O(n) - the queue holds up to one whole level, and the unused ans lists
//             store all n values
//
// GOTCHAS
//   - Depth counts nodes, not edges: a single node has depth 1, an empty tree 0.
//   - In method A the lists ans and sub are built but never read; only height++ matters.
//     Without them the extra memory would shrink to the widest level.
//   - A chain of 10^4 nodes makes method B recurse 10^4 levels deep; the BFS of method A has
//     no recursion at all.
//
// RELATED
//   - LC 110 Balanced Binary Tree - needs this same height at every node.
//   - LC 543 Diameter of Binary Tree - the same height recursion, combining the left and
//     right heights at each node.
//   - LC 102 Binary Tree Level Order Traversal - method A is that level loop plus a counter.
// ============================================================================
public class LC104MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        // example 1. Expected output: 3
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxDepth(root));
    }
    // Approach A: BFS, one round per level; the number of rounds is the depth.
    // O(n) time, O(n) space.
    public static int maxDepthNormal(TreeNode root) {
        if(root == null){
            return 0;
        }
        // levels finished so far
        int height = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // ans and sub are never read - only the number of rounds (height) is used
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            List<Integer> sub = new ArrayList<>();
            // the queue holds exactly one level right now
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode treeNode = queue.poll();
                if(treeNode.left != null){
                    queue.offer(treeNode.left);
                }
                if(treeNode.right != null){
                    queue.offer(treeNode.right);
                }
                sub.add(treeNode.val);
            }
            ans.add(sub);
            // one more level done
            height++;
        }
        return height;
    }

    // Approach B: recursion - depth = 1 + the deeper of the two subtrees. O(n) time, O(h) space.
    public static int maxDepth(TreeNode root) {
        // empty tree: depth 0 (also the base case of the recursion)
        if(root == null){
            return 0;
        }
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        // +1 for the root itself
        return Math.max(maxLeft, maxRight) + 1;
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

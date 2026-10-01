package Practice.BinaryTree;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 100. Same Tree   [Easy]
// https://leetcode.com/problems/same-tree/
// Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the roots of two binary trees p and q (either may be empty). Return true if
//   the trees are identical: the same shape, and the same value at every matching position.
//
// EXAMPLES
//   p = [1,2,3], q = [1,2,3]     ->  true
//   p = [1,2],   q = [1,null,2]  ->  false   same values, but 2 hangs on the left in p and
//                                            on the right in q - the shapes differ
//   p = [1,2,1], q = [1,1,2]     ->  false   same shape, but the left children hold 2 and 1
//
// CONSTRAINTS
//   0 <= number of nodes in each tree <= 100,   -10^4 <= Node.val <= 10^4
//
// INTUITION
//   Two trees are the same exactly when their roots match, their left subtrees are the same
//   and their right subtrees are the same - the definition already is a recursion. So walk
//   both trees in lockstep, always standing at the same position in each, and fail at the
//   first position where they disagree.
//
// APPROACH (as implemented below)
//   isSameTree(p, q) looks at one pair of positions:
//   1. Both null  -> true: two empty spots match.
//   2. Only one is null -> false: the shapes differ.
//   3. p.val != q.val   -> false: the values differ.
//   4. Otherwise both nodes match, so the answer depends on the children:
//      isSameTree(p.left, q.left) && isSameTree(p.right, q.right).
//
// DRY RUN   p = [1,2], q = [1,null,2]   (example 2)
//   isSameTree(1, 1)        both exist, 1 == 1          -> compare the left children
//   isSameTree(2, null)     only one is null            -> false
//   && stops here: the right children are never compared -> false
//
// STATUS
//   main() is empty, so running the file prints nothing. The imports ArrayList and List
//   are not used.
//
// COMPLEXITY
//   Time : O(n) - n = nodes of the smaller tree: each pair of positions is compared at
//          most once, and the first mismatch ends the walk
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - The two null checks must come before p.val / q.val are read; after them both nodes
//     are known to exist.
//   - Comparing only the values in some traversal order can miss shape differences:
//     [1,2] and [1,null,2] both read 1, 2 in pre-order.
//   - && short-circuits: once the left subtrees differ, the right ones are skipped.
//
// RELATED
//   - LC 101 Symmetric Tree - the same lockstep comparison, but it compares the left side
//     of one tree with the mirrored right side.
//   - LC 572 Subtree of Another Tree - uses this same-tree check at every node as a
//     building block.
// ============================================================================
public class LC100SameTree {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }
    // Returns true when both trees have the same shape and the same values.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // both positions empty: they match
        if(p == null && q == null) return true;
        // exactly one is empty: the shapes differ
        if(p == null || q == null) return false;
        // same position, different value
        if(p.val != q.val) return false;
        // this pair matches; both subtrees must match too
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    // minimal LeetCode-style node; a static nested class, so new TreeNode(1) works directly
    static public class TreeNode {
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

package Practice.BinaryTree;

// ============================================================================
// LC 101. Symmetric Tree   [Easy]
// https://leetcode.com/problems/symmetric-tree/
// Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary tree. Return true if the tree is a mirror image of
//   itself, i.e. symmetric around a vertical line through the root: the left half, flipped,
//   looks exactly like the right half (shape and values).
//   Follow-up in the statement: solve it both recursively and iteratively.
//
// EXAMPLES
//   root = [1,2,2,3,4,4,3]       ->  true    below the two 2s: 3,4 on the left and 4,3 on the
//                                            right, which are mirror images
//   root = [1,2,2,null,3,null,3] ->  false   both 2s have only a RIGHT child 3; a mirror
//                                            would need it on the left for one of them
//
// CONSTRAINTS
//   1 <= number of nodes <= 1000,   -100 <= Node.val <= 100
//
// INTUITION
//   A tree is symmetric when its left subtree is the mirror image of its right subtree.
//   Two subtrees are mirror images when their roots hold the same value AND the outer
//   children mirror each other (left.left against right.right) AND the inner children
//   mirror each other (left.right against right.left). That is the lockstep comparison of
//   LC 100 Same Tree with one change: the children are crossed instead of matched straight.
//
// APPROACH (as implemented below)
//   1. isSymmetric(root) compares the two halves: isSameTree(root.left, root.right).
//   2. isSameTree(p, q) - despite its name it is a MIRROR check of the pair (p, q):
//        both null    -> true  (two empty spots match)
//        only one null -> false (the shapes differ)
//        p.val != q.val -> false
//        otherwise p.left must mirror q.right AND p.right must mirror q.left:
//        isSameTree(p.left, q.right) && isSameTree(p.right, q.left).
//
// DRY RUN   root = [1,2,2,3,4,4,3]   (example 1)
//                  1
//                /   \
//               2     2
//              / \   / \
//             3   4 4   3
//   isSymmetric -> isSameTree(left 2, right 2)
//     2 == 2, then the crossed pairs:
//       isSameTree(3, 3)  outer: equal, all children null -> true
//       isSameTree(4, 4)  inner: equal, all children null -> true
//     -> true
//   Example 2, root = [1,2,2,null,3,null,3]: isSameTree(2, 2) is equal, then the outer pair
//   is isSameTree(null, 3): only one is null -> false, and && skips the rest -> false.
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode is a non-static inner
//   class: create nodes through an instance, e.g. new LC101SymmetricTree().new TreeNode(1).
//   isSymmetric(null) would throw a NullPointerException (LeetCode guarantees at least
//   one node). Only the recursive solution is here, not the iterative follow-up.
//
// COMPLEXITY
//   Time : O(n) - every node of the left half is paired with exactly one node of the right
//          half, and each pair is looked at once
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - The pairs are crossed: left.left goes with right.right and left.right with right.left.
//     Pairing left with left would be the LC 100 same-tree check.
//   - A single node is symmetric: both children are null and the first rule returns true.
//   - The root itself never needs a comparison; only its two subtrees are compared.
//   - Iterative version of the same idea: keep a queue (or stack) of node pairs, start with
//     (root.left, root.right), and for each pair apply the same null/value checks and add
//     (p.left, q.right) and (p.right, q.left).
//
// RELATED
//   - LC 100 Same Tree - the same lockstep walk, but the children are matched straight.
//   - LC 226 Invert Binary Tree - a tree is symmetric when inverting its left subtree
//     gives its right subtree.
// ============================================================================
public class LC101SymmetricTree {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }

    // Returns true if the left half of the tree mirrors the right half.
    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    // Mirror check of the pair (p, q) - the name comes from the LC 100 same-tree check.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // both empty: they mirror each other
        if(p == null && q == null) return true;
        // exactly one empty: the shapes differ
        if(p == null || q == null) return false;
        // mirrored positions must hold equal values
        if(p.val != q.val) return false;
        // crossed pairs: outer (p.left, q.right) and inner (p.right, q.left)
        return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }

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
}

package Practice.BinaryTree;

// ============================================================================
// LC 236. Lowest Common Ancestor of a Binary Tree   [Medium]
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
// Topics: Tree, Depth-First Search, Binary Tree, Binary Lifting, Lowest Common Ancestor
// ============================================================================
//
// PROBLEM
//   You get a binary tree (no ordering of the values) and two different nodes p and q that
//   are both in it. Return their lowest common ancestor (LCA): the deepest node that has
//   both p and q somewhere in its subtree. A node counts as its own descendant, so when p
//   sits above q the answer is p itself.
//
// EXAMPLES
//   root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1  ->  3   5 is in the left subtree of
//                                                              3, 1 in the right one
//   same tree, p = 5, q = 4                              ->  5   4 is below 5, and a node
//                                                              is its own ancestor
//   root = [1,2], p = 1, q = 2                           ->  1
//
// CONSTRAINTS
//   2 <= number of nodes <= 10^5,   -10^9 <= Node.val <= 10^9,   all values unique,
//   p != q,   p and q are both in the tree
//
// INTUITION
//   Ask every subtree: "do you contain p or q?". Each call answers with
//     null            - neither is in this subtree,
//     a node          - the first of p / q it ran into, or the LCA if both are inside.
//   At a node:
//     - If the node IS p or q, answer with it straight away. If the other target is below,
//       this node is the LCA; if not, the other one is elsewhere and this answer just tells
//       the parent "one target is here".
//     - Otherwise ask both children. If BOTH answer with a node, p is on one side and q on
//       the other, so this node is the lowest one with both below it: it is the LCA.
//     - If only one child answers, pass that answer up unchanged (it is the LCA found
//       deeper, or a single target still waiting for its partner).
//
// APPROACH (as implemented below)
//   1. root == null, root == p or root == q -> return root.
//   2. left = lowestCommonAncestor(root.left, p, q), right = ... (root.right, p, q).
//   3. left != null and right != null -> return root (the targets are on different sides).
//   4. Otherwise return whichever is not null (null when both are null).
//
// DRY RUN   root = [3,5,1,6,2,0,8,null,null,7,4]
//                3
//              /   \
//             5     1
//            / \   / \
//           6   2 0   8
//              / \
//             7   4
//   p = 5, q = 1   lca(3): not p or q -> ask both children
//                    lca(5) = 5   (it is p, returned at once)
//                    lca(1) = 1   (it is q)
//                  both non-null -> return 3
//   p = 5, q = 4   lca(3): not p or q -> ask both children
//                    lca(5) = 5   (it is p; 4 below it is never even looked at)
//                    lca(1): its whole subtree has neither target -> null
//                  only left is non-null -> return 5
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode is a static nested class
//   with a single constructor, TreeNode(int x).
//
// COMPLEXITY
//   Time : O(n) - each node is visited at most once
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - Nodes are compared by reference (root == p), not by value.
//   - Stopping at p or q without looking below is only safe because both targets are
//     guaranteed to exist. If q could be missing, finding p would still return p as the
//     "LCA" (that is LC 1644, where this code would need an extra check).
//   - return left != null ? left : right covers three cases at once: only left found,
//     only right found, and nothing found (right is null).
//   - Unlike a BST there is no value order to steer by, so both subtrees may have to be
//     searched (compare LC 235).
//
// RELATED
//   - LC 235 Lowest Common Ancestor of a Binary Search Tree - the same question on a BST,
//     where comparing values lets a single root-to-node path decide.
//   - LC 1644 Lowest Common Ancestor of a Binary Tree II - p or q may be missing from the tree.
// ============================================================================
public class LC236LowestCommonAncestorOfABinaryTree {

    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }

    // Returns the lowest node that has both p and q in its subtree (p and q must be in the tree).
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // nothing here, or this node is one of the targets: report it (null = "neither found")
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // one target on each side: this node is where their paths meet
        if(right != null && left != null){
            return root;
        }
        // at most one side found something: pass it up
        return left != null ? left : right;
    }


      // minimal LeetCode-style node; a static nested class, so new TreeNode(3) works directly
      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }
}

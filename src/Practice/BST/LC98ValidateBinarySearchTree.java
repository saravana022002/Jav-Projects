package Practice.BST;

// ============================================================================
// LC 98. Validate Binary Search Tree   [Medium]
// https://leetcode.com/problems/validate-binary-search-tree/
// Topics: Tree, Depth-First Search, Binary Search Tree, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary tree. Say whether it is a valid binary search tree:
//     - every key in a node's LEFT subtree is strictly smaller than the node's key,
//     - every key in its RIGHT subtree is strictly larger,
//     - and both subtrees are valid BSTs themselves.
//   "Strictly" means duplicates make a tree invalid.
//
// EXAMPLES
//   root = [2,1,3]                ->  true
//   root = [5,1,4,null,null,3,6]  ->  false   the right child 4 is smaller than the root 5
//
// CONSTRAINTS
//   1 <= number of nodes <= 10^4,   -2^31 <= Node.val <= 2^31 - 1
//
// INTUITION
//   Checking only "left child < node < right child" is not enough: a node must beat EVERY
//   key in its left subtree and be beaten by every key in its right subtree. Every turn
//   on the way down leaves a limit that all deeper nodes must respect:
//     go left  at value v -> everything below must be  < v   (v becomes the new upper bound)
//     go right at value v -> everything below must be  > v   (v becomes the new lower bound)
//   So each node is handed an open interval (min, max) and its key must lie strictly
//   inside it. If every node passes, the whole tree is a valid BST.
//
// APPROACH (as implemented below)
//   1. isValidBST(root) starts the check with the widest interval the code knows:
//      isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE).
//   2. isValid(root, min, max):
//        a. root == null -> true (an empty tree is fine).
//        b. root.val must satisfy min < root.val < max; if max <= root.val or
//           min >= root.val -> false.
//        c. The left child must fit in (min, root.val), the right child in (root.val, max):
//           return isValid(root.left, min, root.val) && isValid(root.right, root.val, max).
//
// DRY RUN   root = [5,1,4,null,null,3,6]   (example 2)
//   isValid(5, MIN, MAX)   MIN < 5 < MAX ok    -> check left (MIN, 5), then right (5, MAX)
//   isValid(1, MIN, 5)     MIN < 1 < 5 ok      -> children are null -> true
//   isValid(4, 5, MAX)     min = 5 >= 4        -> false
//   true && false -> false     (the 3 and 6 below the 4 are never reached)
//
// STATUS
//   Known issue: the bounds are ints and the comparisons are strict, so a node whose key
//   equals Integer.MAX_VALUE (2147483647) or Integer.MIN_VALUE (-2147483648) is rejected
//   even though the constraints allow those keys. root = [2147483647] returns false, the
//   correct answer is true; so does [0,null,2147483647]. A fix would use long bounds (or
//   "no bound yet" markers) instead of the int limits.
//   The code is left unchanged on purpose.
//   Also: main() calls isValidBST(new TreeNode()) - a single node with key 0 - but does not
//   print the result (it returns true).
//
// COMPLEXITY
//   Time : O(n) - every node is checked once (and the walk stops at the first violation)
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - Comparing each node only with its parent accepts [5,4,6,null,null,3,7]: 6 is fine
//     next to 3 and 7, but 3 sits in the right subtree of 5 and is smaller than 5. With
//     bounds, isValid(3, 5, 6) fails at once because min = 5 >= 3.
//   - The comparisons must be strict (< and >), which is what rejects duplicates such as
//     [2,2,2].
//   - Sentinel bounds must lie outside the range of legal keys - int limits do not (STATUS).
//
// RELATED
//   - LC 94 Binary Tree Inorder Traversal - a tree is a valid BST exactly when its in-order
//     walk is strictly increasing: another way to solve this problem.
//   - LC 1373 Maximum Sum BST in Binary Tree - also has to decide, for every subtree,
//     whether it is a valid BST.
// ============================================================================
public class LC98ValidateBinarySearchTree {


      // minimal LeetCode-style node; a static nested class, so new TreeNode(2) works directly
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
    public static void main(String[] args) {
        // a single node with key 0: the call returns true, but the result is not printed
        isValidBST(new TreeNode());
    }
    // Returns true if the tree is a valid BST: start with the widest interval the int type offers.
    public static boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    // Does every key in this subtree lie strictly between min and max, at every level?
    public static boolean isValid(TreeNode root, int min, int max) {
          // an empty subtree has nothing that could break the rules
          if (root == null){
              return true;
          }
          // the key must be strictly inside (min, max); equal keys are invalid too
          // (this is also where a key equal to an int limit gets rejected - see STATUS)
          if (max <= root.val || min >= root.val){
              return false;
          }
          // going left, the node's key becomes the new upper bound; going right, the lower bound
          return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}

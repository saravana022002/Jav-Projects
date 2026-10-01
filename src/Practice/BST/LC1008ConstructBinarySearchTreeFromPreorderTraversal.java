package Practice.BST;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 1008. Construct Binary Search Tree from Preorder Traversal   [Medium]
// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
// Topics: Array, Stack, Tree, Binary Search Tree, Monotonic Stack, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the preorder listing of a binary search tree (each node is listed before its
//   left subtree, which is listed before its right subtree). Rebuild the tree and return
//   its root. In this BST everything in a node's left subtree is strictly smaller than
//   the node and everything in its right subtree is strictly bigger. All values are
//   distinct, and the input is always a valid BST preorder.
//
// EXAMPLES
//   preorder = [8,5,1,7,10,12]  ->  [8,5,10,1,7,null,12]
//       8 is the root; 5,1,7 are smaller so they form its left subtree, 10,12 its right
//   preorder = [1,3]            ->  [1,null,3]     3 > 1, so it goes to the right of 1
//
// CONSTRAINTS
//   1 <= preorder.length <= 100,   1 <= preorder[i] <= 1000,   all values are unique
//
// INTUITION
//   Preorder names a node before anything below it, so the tree can be rebuilt by reading
//   the array once, left to right. The only question for the next value is: "does it
//   belong to the subtree I am building right now?" Every subtree owns an open range
//   (min, max) of allowed values. Going left from a node v narrows the range to (min, v),
//   going right narrows it to (v, max). If the next value is outside the current range,
//   it belongs to the right side of some ancestor, so this subtree is complete: return
//   null and leave the value for the caller that can use it.
//
// APPROACH (as implemented below)
//   1. bstFromPreorder starts buildBstTree with the widest range
//      (Integer.MIN_VALUE, Integer.MAX_VALUE). The static index is the read position.
//   2. buildBstTree(preorder, min, max):
//        a. index >= preorder.length -> null (every value is placed).
//        b. val = preorder[index]; if val is not strictly inside (min, max) -> null.
//        c. Otherwise consume it (index++) and create the node root.
//        d. root.left  = buildBstTree(preorder, min, val)
//           root.right = buildBstTree(preorder, val, max)
//           (left first, because preorder lists the left subtree first)
//
// DRY RUN   preorder = [8,5,1,7,10,12]   (-inf / +inf stand for MIN_VALUE / MAX_VALUE)
//   range (-inf,+inf) reads 8  -> node 8,  go left
//   range (-inf,8)    reads 5  -> node 5,  go left
//   range (-inf,5)    reads 1  -> node 1;  its ranges (-inf,1) and (1,5) both see 7 -> null
//   range (5,8)       reads 7  -> node 7 = right of 5; (5,7) and (7,8) both see 10 -> null
//   range (8,+inf)    reads 10 -> node 10 = right of 8; its left (8,10) sees 12 -> null
//   range (10,+inf)   reads 12 -> node 12 = right of 10; index = 6 = length -> nulls
//   result [8,5,10,1,7,null,12]
//
// STATUS
//   Known issue: index is static and never reset. The first call is correct, but a later
//   call in the same JVM starts from the old position: after example 1, a call with [1,3]
//   returns null (an empty tree). LeetCode runs many test cases in one JVM, so as written
//   every test after the first would get a wrong tree. Setting index = 0 at the start of
//   bstFromPreorder (or using an instance field) would fix it.
//   main() is empty, so running the file prints nothing.
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   Time : O(n) - each created node makes exactly 2 child calls, so there are 2n + 1
//          calls in total and each does O(1) work
//   Space: O(h) - recursion depth is the tree height; O(n) when the input is sorted
//          (the tree becomes a chain)
//
// GOTCHAS
//   - The range is exclusive (max <= val || min >= val -> null). That is right because
//     values are unique, and MIN_VALUE / MAX_VALUE can serve as "infinity" only because
//     real values are 1..1000.
//   - A value outside the range must NOT be consumed - it is someone else's node.
//
// RELATED
//   - LC 105 Construct Binary Tree from Preorder and Inorder Traversal - the general
//     (non-BST) version, where the inorder array tells where the left subtree ends.
//   - LC 98 Validate Binary Search Tree - the same (min, max) range carried per subtree.
// ============================================================================
public class LC1008ConstructBinarySearchTreeFromPreorderTraversal {

    // LeetCode's tree node, nested here so the file is self-contained
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
        // empty - no test is run (see STATUS)
    }
    // read position in preorder, shared by every recursive call; never reset - see STATUS
    static int index = 0;

    // Returns the root of the rebuilt BST. The root may hold any value -> widest range.
    public static TreeNode bstFromPreorder(int[] preorder) {
        return buildBstTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Builds the subtree whose values must lie strictly inside (min, max) from the values
    // starting at index. Returns null when the next value does not belong here.
    public static TreeNode buildBstTree(int[] preorder, int min, int max){
        // every value has been placed
        if(index >= preorder.length){
            return null;
        }
        int val = preorder[index];
        // outside this subtree's range -> it belongs to an ancestor; do not consume it
        if(max <= val || min >= val ){
            return null;
        }
        // the value belongs here: consume it and make it this subtree's root
        index++;
        TreeNode root = new TreeNode(val);
        // smaller values (up to val) go left, bigger ones (from val) go right;
        // left is built first because preorder lists the left subtree first
        root.left = buildBstTree(preorder, min, val);
        root.right = buildBstTree(preorder, val, max);
        return root;
    }
}

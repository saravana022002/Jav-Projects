package Practice.BST;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 230. Kth Smallest Element in a BST   [Medium]
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// Topics: Tree, Depth-First Search, Binary Search Tree, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary search tree and a number k. Return the k-th smallest
//   value stored in the tree, counting from 1 (k = 1 is the minimum).
//
// EXAMPLES
//   root = [3,1,4,null,2],         k = 1  ->  1    values in order: 1,2,3,4
//   root = [5,3,6,2,4,null,null,1], k = 3  ->  3    values in order: 1,2,3,4,5,6
//
// CONSTRAINTS
//   the tree has n nodes,   1 <= k <= n <= 10^4,   0 <= Node.val <= 10^4
//
// INTUITION
//   An in-order walk (left subtree, node, right subtree) of a BST visits the values in
//   ascending order. So the k-th node the walk reaches is the k-th smallest value -
//   just count nodes as they are visited.
//
// APPROACH (as implemented below)
//   1. kthSmallest creates node = new int[2]:
//        node[0] = how many nodes have been counted, node[1] = value of the last one.
//      (An array, so every recursive call updates the same two numbers.)
//   2. inorder(root, k, node): walk left, then if node[0] < k store root.val in node[1]
//      and increment node[0], then walk right.
//   3. When the walk ends, node[1] holds the value that was counted k-th -> return it.
//   (The list nodes is created but never used.)
//
// DRY RUN   root = [5,3,6,2,4,null,null,1], k = 3   (example 2)
//   visit 1 -> node = [1, 1]
//   visit 2 -> node = [2, 2]
//   visit 3 -> node = [3, 3]     count reached k
//   visit 4, 5, 6 -> node[0] == k, nothing changes
//   return node[1] = 3
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode is a non-static inner
//   class: create nodes through an instance, e.g. new LC230KthSmallestElementInABST().new
//   TreeNode(5).
//
// COMPLEXITY
//   Time : O(n) - there is no early exit: after the k-th node the walk still visits the
//          rest of the tree, it just stops recording
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - A plain int counter passed as a parameter would not work: Java copies it, so each
//     call would count on its own copy. The shared int[] (or a field) fixes that.
//   - Stopping as soon as node[0] == k would make it O(h + k).
//   - Follow-up in the statement (tree changes often, many queries): store each node's
//     subtree size; compare k with the left subtree size to go left, stop or go right,
//     giving O(h) per query.
//
// RELATED
//   - LC 94 Binary Tree Inorder Traversal - the traversal this solution is built on.
// ============================================================================
public class LC230KthSmallestElementInABST {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }
    // Returns the k-th smallest value in the BST (k is 1-based).
    public int kthSmallest(TreeNode root, int k) {
        // unused
        List<Integer> nodes = new ArrayList<>();
        // node[0] = nodes counted so far, node[1] = value of the last counted node
        int[] node = new int[2];
        inorder(root, k, node);
        return node[1];
    }

    // In-order walk (left, node, right) = ascending order; counts the first k values.
    public void inorder(TreeNode root, int k, int[] node){
        if(root == null){
            return;
        }
        inorder(root.left, k, node);
        // all smaller values are counted already, so root is the next smallest;
        // once k values are counted the answer is fixed (the walk still goes on)
        if(node[0] < k) {
            node[1] = root.val;
            node[0]++;
        }
        inorder(root.right, k, node);
    }
     // LeetCode's tree node (non-static inner class - needs an outer instance to create)
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

package Practice.BinaryTree;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 94. Binary Tree Inorder Traversal   [Easy]
// https://leetcode.com/problems/binary-tree-inorder-traversal/
// Topics: Stack, Tree, Depth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   Return the values of a binary tree in in-order: for every node, first everything in its
//   left subtree, then the node's own value, then everything in its right subtree.
//   Follow-up in the statement: the recursive solution is easy - can you do it iteratively?
//
// EXAMPLES
//   root = [1,null,2,3]                        ->  [1,3,2]
//       1 has no left child; its right subtree is 2, and 2's left child 3 comes before 2
//   root = [1,2,3,4,5,null,8,null,null,6,7,9]  ->  [4,2,6,5,7,1,3,9,8]
//   root = []                                  ->  []
//
// CONSTRAINTS
//   0 <= number of nodes <= 100,   -100 <= Node.val <= 100
//
// INTUITION
//   The definition already is the algorithm: traverse the left subtree, record the node,
//   traverse the right subtree. A recursive method does exactly that, and a list collects the
//   values in the order they are recorded. The position of the "record" step is what makes
//   it in-order: before the two calls it would be pre-order, after them post-order.
//   (For a binary SEARCH tree, in-order is the sorted order - the reason it shows up in
//   many BST problems.)
//
// APPROACH (as implemented below)
//   1. inorderTraversal(root) creates the empty result list, calls inOrder(root, list) and
//      returns the list.
//   2. inOrder(node, list):
//        a. node == null -> return (nothing to record, this ends the recursion).
//        b. inOrder(node.left, list)   the whole left subtree first
//        c. list.add(node.val)         then this node
//        d. inOrder(node.right, list)  then the whole right subtree
//
// DRY RUN   root = [1,null,2,3]        1
//                                       \
//                                        2
//                                       /
//                                      3
//   inOrder(1)
//     left is null, nothing to do
//     add 1                          list [1]
//     inOrder(2)
//       inOrder(3): left null, add 3, right null     list [1,3]
//       add 2                                        list [1,3,2]
//       right is null
//   return [1,3,2]
//
// STATUS
//   main() is empty, so running the file prints nothing. Only the recursive solution is
//   here; the iterative follow-up (and Morris traversal) is not.
//
// COMPLEXITY
//   Time : O(n) - every node is visited once
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain), plus O(n) for the
//          returned list itself
//
// GOTCHAS
//   - Where list.add sits decides the order: between the two recursive calls = in-order,
//     before them = pre-order (LC 144), after them = post-order (LC 145).
//   - The list is shared by all calls (passed by reference), so the recursion needs no
//     return value.
//   - Iterative idea for the follow-up: keep a stack; go left as far as possible pushing
//     nodes, pop one and record it, then continue from its right child.
//
// RELATED
//   - LC 98 Validate Binary Search Tree - the in-order walk of a valid BST is strictly
//     increasing (LC98ValidateBinarySearchTree.java in the BST folder).
//   - LC 230 Kth Smallest Element in a BST - counts nodes in this order
//     (LC230KthSmallestElementInABST.java in the BST folder).
//   - LC 173 Binary Search Tree Iterator - the iterative, stack-based version, paused
//     between values (LC173BinarySearchTreeIterator.java in the Design folder).
// ============================================================================
public class LC94BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }

    // Returns the node values in in-order (left subtree, node, right subtree).
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    // Appends the values of the subtree under node to list, in in-order.
    public static void inOrder(TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        // 1) the whole left subtree  2) this node  3) the whole right subtree
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
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

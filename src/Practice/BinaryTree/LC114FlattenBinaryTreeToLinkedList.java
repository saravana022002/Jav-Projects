package Practice.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// ============================================================================
// LC 114. Flatten Binary Tree to Linked List   [Medium]
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// Topics: Linked List, Stack, Tree, Depth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   Rearrange a binary tree IN PLACE into a "linked list" that reuses the same TreeNode
//   objects: every node's right pointer must point to the next node, every left pointer
//   must be null, and the nodes must be in pre-order (node, left subtree, right subtree).
//   The method returns nothing - it modifies the tree it is given.
//   Follow-up in the statement: do it with O(1) extra space.
//
// EXAMPLES
//   root = [1,2,5,3,4,null,6]  ->  [1,null,2,null,3,null,4,null,5,null,6]
//       pre-order is 1,2,3,4,5,6, laid out as a chain that goes only to the right
//   root = []                  ->  []
//   root = [0]                 ->  [0]
//
// CONSTRAINTS
//   0 <= number of nodes <= 2000,   -100 <= Node.val <= 100
//
// INTUITION
//   The finished chain is just the pre-order sequence, so produce pre-order and relink the
//   nodes in that order. Iterative pre-order uses a stack: pop a node, push its RIGHT child,
//   then its LEFT child - the left one is on top, so it is visited next. After those pushes
//   the top of the stack is exactly the node that follows the current one in pre-order
//   (its left child; else its right child; else a pending right child of some ancestor).
//   So: curr.right = that top, curr.left = null. The original right child is not lost,
//   because it was pushed onto the stack before curr.right is overwritten.
//
// APPROACH (as implemented below)
//   1. root == null -> return. Otherwise stk = [root].
//   2. While the stack is not empty:
//        curr = stk.pop()
//        push curr.right if it exists, then push curr.left if it exists
//        if the stack is not empty: curr.right = stk.peek()   (the next node in pre-order)
//        curr.left = null
//   3. The last node in pre-order has no children and nothing is left on the stack, so its
//      right pointer is already null and the chain ends.
//
// DRY RUN   root = [1,2,5,3,4,null,6]   (the tree main() builds)
//            1
//           / \
//          2   5
//         / \   \
//        3   4   6
//   stack (top on the right)            action
//   [1]                                 pop 1, push 5 then 2 -> [5,2];   1.right = 2
//   [5,2]                               pop 2, push 4 then 3 -> [5,4,3]; 2.right = 3
//   [5,4,3]                             pop 3, no children   -> [5,4];   3.right = 4
//   [5,4]                               pop 4, no children   -> [5];     4.right = 5
//   [5]                                 pop 5, push 6        -> [6];     5.right = 6
//   [6]                                 pop 6, nothing left  -> [];      6.right stays null
//   (every curr.left is set to null on the way)   result: 1 -> 2 -> 3 -> 4 -> 5 -> 6
//
// STATUS
//   main() builds example 1 and calls flatten(root) but prints nothing (the chain is
//   1, 2, 3, 4, 5, 6 with every left pointer null - checked). The recursive
//   preOrderTraversal helper is never called; it is a leftover for checking the result.
//   The O(1)-space follow-up is not implemented.
//
// COMPLEXITY
//   Time : O(n) - every node is pushed and popped once
//   Space: O(h) - the stack holds at most one pending right child per level plus the
//          current left child
//
// GOTCHAS
//   - Push the RIGHT child first and the LEFT child second: a stack pops the last item
//     pushed, and pre-order needs the left subtree before the right one.
//   - The right child must be pushed before curr.right is overwritten, or the whole right
//     subtree would be lost.
//   - Every left pointer must end up null, so curr.left = null runs on every pop (it is
//     harmless for nodes that had no left child).
//   - Follow-up idea (O(1) extra space): for each node with a left child, find the rightmost
//     node of that left subtree, hang the node's right subtree there, then move the left
//     subtree to the right side.
//
// RELATED
//   - LC 144 Binary Tree Preorder Traversal - the traversal whose order this flattening
//     follows.
//   - LC 430 Flatten a Multilevel Doubly Linked List - another "flatten in pre-order" task,
//     on a linked structure with child pointers.
// ============================================================================
public class LC114FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        // example 1 - the result is not printed; the chain becomes 1 -> 2 -> 3 -> 4 -> 5 -> 6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        flatten(root);
    }
    // Flattens the tree in place into a right-going chain in pre-order.
    public static void flatten(TreeNode root) {
        if (root == null) return;
        // iterative pre-order: the top of the stack is always the next node to visit
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        TreeNode curr = null;
        while (!stk.isEmpty()){
            curr = stk.pop();
            // right first, left second -> the left child is popped first
            if(curr.right != null){
                stk.push(curr.right);
            }
            if(curr.left != null){
                stk.push(curr.left);
            }
            // link to the next node in pre-order (the old right child is safe on the stack)
            if(!stk.isEmpty()){
                curr.right = stk.peek();
            }
            curr.left = null;
        }
    }

    // plain recursive pre-order into a list - not used by flatten (handy for checking it)
    public static void preOrderTraversal(TreeNode treeNode, List<Integer> ans){
        if(treeNode == null){
            return;
        }
        ans.add(treeNode.val);
        preOrderTraversal(treeNode.left, ans);
        preOrderTraversal(treeNode.right, ans);
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

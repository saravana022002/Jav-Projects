package Practice.Design;

import java.util.Stack;

// ============================================================================
// LC 173. Binary Search Tree Iterator   [Medium]
// https://leetcode.com/problems/binary-search-tree-iterator/
// Topics: Stack, Tree, Design, Binary Search Tree, Binary Tree, Iterator
// ============================================================================
//
// PROBLEM
//   Build an iterator that hands out the values of a binary search tree in in-order
//   (= ascending) order, one value per call:
//     - constructor(root)  set up the iterator; nothing is returned yet
//     - next()             return the smallest value not handed out so far
//                          (so the first call returns the minimum of the tree)
//     - hasNext()          true while at least one value is still left
//   next() is only called when a value is left.
//   Follow-up: next() and hasNext() in average O(1) time using O(h) memory, h = height.
//
// EXAMPLES
//   root = [7,3,15,null,null,9,20]          7
//                                          / \
//                                         3   15
//                                            /  \
//                                           9    20
//   next -> 3, next -> 7, hasNext -> true, next -> 9, hasNext -> true,
//   next -> 15, hasNext -> true, next -> 20, hasNext -> false
//   Output: [null, 3, 7, true, 9, true, 15, true, 20, false]
//
// CONSTRAINTS
//   The number of nodes in the tree is in the range [1, 10^5].
//   0 <= Node.val <= 10^6,   at most 10^5 calls will be made to hasNext and next
//
// INTUITION
//   The iterative in-order traversal uses a stack: walk left as far as possible pushing
//   every node, pop one (that is the next value), then repeat the "walk left" from its
//   right child. This iterator runs exactly that traversal, but lazily: one pop per
//   next(), paused between calls.
//   Why the top is always the next value: everything smaller has already been popped,
//   and the popped node's right subtree (all larger) is only loaded after it.
//   The stack holds at most one waiting node per level of the tree, so memory is O(h).
//
// APPROACH (as implemented below)
//   1. Constructor: pushAll(root) pushes root, root.left, root.left.left, ... (the left
//      spine), so the minimum is on top.
//   2. next(): pop tempNode - it is the answer. pushAll(tempNode.right) loads the left
//      spine of its right subtree, whose bottom is the next value in order. Return
//      tempNode.val.
//   3. hasNext(): values remain exactly while stkNodes is not empty.
//   4. pushAll(root): a for-loop with an EMPTY body - push root, step to root.left, repeat
//      until null.
//
// DRY RUN   the LC example   (stack written bottom -> top)
//   constructor  pushAll(7): push 7, 3             stack [7, 3]
//   next         pop 3,  pushAll(null)             stack [7]          -> 3
//   next         pop 7,  pushAll(15): push 15, 9   stack [15, 9]      -> 7
//   hasNext      stack not empty                                      -> true
//   next         pop 9,  pushAll(null)             stack [15]         -> 9
//   next         pop 15, pushAll(20): push 20      stack [20]         -> 15
//   next         pop 20, pushAll(null)             stack []           -> 20
//   hasNext      stack empty                                          -> false
//
// STATUS
//   main() is empty, so running the file prints nothing. Replaying the LC example above
//   gives the expected output (checked).
//   LeetCode names the class BSTIterator: rename the class and constructor to submit.
//   TreeNode is a non-static inner class here, so test code needs an iterator instance to
//   create nodes (it.new TreeNode(7)).
//
// COMPLEXITY
//   Time : next() O(1) amortized - over a full iteration every node is pushed once and
//          popped once, so n calls cost O(n) in total, even though a single next() may
//          push up to h nodes.  hasNext() O(1).
//   Space: O(h) - one waiting node per level at most (h = n for a completely skewed tree)
//
// GOTCHAS
//   - pushAll's loop body is empty on purpose: the update part "stkNodes.push(root),
//     root = root.left" does the work, push first and then step left.
//   - Copying the whole in-order sequence into a list in the constructor also works, but
//     costs O(n) memory instead of the O(h) the follow-up asks for.
//
// RELATED
//   - LC 94 Binary Tree Inorder Traversal - the same stack-based in-order walk, run all
//     at once instead of one step per call.
//   - LC 1586 Binary Search Tree Iterator II - adds hasPrev()/prev() (premium).
// ============================================================================
public class LC173BinarySearchTreeIterator {
    public static void main(String[] args) {
        // Empty: nothing is printed. See STATUS for how the class was checked.

    }

    // Left spines of the parts of the tree not visited yet; the top is the next value.
    Stack<TreeNode> stkNodes = new Stack<>();
    public LC173BinarySearchTreeIterator(TreeNode root) {
        // load the left spine of the whole tree -> the minimum ends up on top
        pushAll(root);
    }

    // Returns the next value in ascending order.
    public int next() {
        TreeNode tempNode = stkNodes.pop();
        // the values right after tempNode live in its right subtree: load its left spine
        pushAll(tempNode.right);
        return tempNode.val;
    }

    // Every unvisited node is either on the stack or below the right child of a stacked
    // node, so an empty stack means the traversal is over.
    public boolean hasNext() {
        return !stkNodes.isEmpty();
    }

    // Pushes root and then every left child below it (empty loop body: the update part
    // pushes the node and steps left).
    void pushAll(TreeNode root){
        for(; root != null; stkNodes.push(root), root = root.left);
    }


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

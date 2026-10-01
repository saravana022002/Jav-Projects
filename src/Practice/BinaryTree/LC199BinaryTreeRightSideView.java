package Practice.BinaryTree;

import java.util.List;

// ============================================================================
// LC 199. Binary Tree Right Side View   [Medium]
// https://leetcode.com/problems/binary-tree-right-side-view/
// Topics: Tree, Depth-First Search, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   Stand on the right side of a binary tree and look at it. Return the values of the nodes
//   you can see, from top to bottom. In practice that is the rightmost node of every level -
//   but a left node also shows when it lies deeper than anything on the right.
//
// EXAMPLES
//   root = [1,2,3,null,5,null,4]      ->  [1,3,4]
//   root = [1,2,3,4,null,null,null,5] ->  [1,3,4,5]   levels 2 and 3 exist only on the left,
//                                                     so 4 and 5 peek out below the 3
//   root = [1,null,3]                 ->  [1,3]
//   root = []                         ->  []
//
// CONSTRAINTS
//   0 <= number of nodes <= 100,   -100 <= Node.val <= 100
//
// INTUITION
//   The answer has one value per level: the rightmost node of that level. A depth-first walk
//   that goes into the RIGHT child before the LEFT child reaches the rightmost node of each
//   level before any other node of that level. So record a node only when its level has no
//   entry yet. The result list gets one value per new level, in order, so "level ==
//   res.size()" says exactly "this is the first node seen on a new level".
//
// APPROACH (as implemented below)
//   The caller creates an empty list res and calls leftSide(res, root, 0).
//   1. leftSide(res, node, level): node == null -> return.
//   2. If level == res.size(), no node of this level has been recorded yet; this one is the
//      first visited, hence the rightmost -> res.add(node.val).
//   3. Recurse into the RIGHT child first, then the LEFT child, both with level + 1.
//
// DRY RUN   root = [1,2,3,4,null,null,null,5]   (example 2)
//            1
//           / \
//          2   3
//         /
//        4
//       /
//      5
//   leftSide(1, level 0)   res.size() = 0 == 0    -> add 1        res [1]
//     right: leftSide(3, level 1)   1 == 1        -> add 3        res [1,3]
//     left:  leftSide(2, level 1)   2 != 1        -> skip (level 1 already has the 3)
//       left: leftSide(4, level 2)  2 == 2        -> add 4        res [1,3,4]
//         left: leftSide(5, level 3)  3 == 3      -> add 5        res [1,3,4,5]
//   result [1,3,4,5]
//
// STATUS
//   Unfinished: there is no rightSideView(TreeNode root) method - the entry point LeetCode
//   expects, which would create the list and start the recursion - and main() is empty, so
//   the file cannot be submitted as it is. Only the recursive helper exists. It works: with
//   List<Integer> res = new ArrayList<>(), leftSide(res, root, 0) fills res with the right
//   side view (checked on all four examples).
//   The helper is named leftSide although it goes right first.
//
// COMPLEXITY
//   Time : O(n) - every node is visited once
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain), not counting the
//          output list
//
// GOTCHAS
//   - The order matters: right child first gives the right side view; left first would give
//     the LEFT side view (which the method name suggests).
//   - A left node can be visible: wherever the left branch reaches a level that the right
//     branch does not (the 4 and the 5 in example 2).
//   - level == res.size() replaces a "seen levels" array: when a node at level L is reached,
//     levels 0 .. L-1 already have an entry, so the size is at least L.
//
// RELATED
//   - LC 102 Binary Tree Level Order Traversal - the BFS alternative: take the last node of
//     each level (LC102BinaryTreeLevelOrderTraversal.java in this folder).
//   - LC 116 Populating Next Right Pointers in Each Node - another level-by-level task
//     (LC116PopulatingNextRightPointersInEachNode.java in this folder).
// ============================================================================
public class LC199BinaryTreeRightSideView {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }

    // Fills res with the right side view. Call it with an empty list and level = 0:
    // leftSide(res, root, 0).
    public static void leftSide(List<Integer> res, TreeNode node, int level){
        if(node == null){
            return;
        }
        // first node reached on this level = the rightmost one (right is explored first)
        if (level == res.size()){
            res.add(node.val);
        }
        // right before left, so the rightmost node of every level is met first
        leftSide(res, node.right, level + 1);
        leftSide(res, node.left, level + 1);
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

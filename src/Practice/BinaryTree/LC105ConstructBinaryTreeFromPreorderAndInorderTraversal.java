package Practice.BinaryTree;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 105. Construct Binary Tree from Preorder and Inorder Traversal   [Medium]
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// Topics: Array, Hash Table, Divide and Conquer, Tree, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get two arrays that describe the same binary tree with all-distinct values:
//   preorder (node, then left subtree, then right subtree) and inorder (left subtree,
//   then node, then right subtree). Rebuild the tree and return its root.
//
// EXAMPLES
//   preorder = [3,9,20,15,7],  inorder = [9,3,15,20,7]  ->  [3,9,20,null,null,15,7]
//   preorder = [-1],           inorder = [-1]           ->  [-1]
//
// CONSTRAINTS
//   1 <= preorder.length <= 3000,   inorder.length == preorder.length,
//   -3000 <= preorder[i], inorder[i] <= 3000,   all values are unique,
//   both arrays are valid traversals of the same tree
//
// INTUITION
//   - The first element of a preorder range is always the root of that (sub)tree.
//   - In inorder the root splits the range: everything to its left is the left subtree,
//     everything to its right is the right subtree.
//   So find the root inside inorder: its position tells how many nodes (numsLeft) the left
//   subtree has. In preorder the root is followed by exactly numsLeft left-subtree nodes,
//   and the rest is the right subtree. Both halves are the same problem on smaller ranges,
//   so recurse. Values are unique, so the root's place in inorder is unambiguous, and a
//   HashMap (value -> inorder index) finds it in O(1) instead of scanning.
//
// APPROACH (as implemented below)
//   1. buildTree(preorder, inorder) fills inOrderMap: value -> its index in inorder, then
//      calls the helper on the whole arrays (both ranges 0 .. n-1).
//   2. Helper buildTree(preorder, preStart, preEnd, inorder, inStart, inEnd, inOrderMap):
//        a. empty range (preStart > preEnd || inStart > inEnd) -> null.
//        b. treeNode = new node holding preorder[preStart] - the root of this range.
//        c. inRoot = inOrderMap.get(treeNode.val)   where the root sits in inorder
//           numsLeft = inRoot - inStart              size of the left subtree
//        d. left  = build(preorder[preStart+1 .. preStart+numsLeft], inorder[inStart .. inRoot-1])
//        e. right = build(preorder[preStart+numsLeft+1 .. preEnd],   inorder[inRoot+1 .. inEnd])
//        f. return treeNode.
//
// DRY RUN   preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]   (example 1)
//   map {9:0, 3:1, 15:2, 20:3, 7:4}
//   build(pre 0..4, in 0..4)  root = pre[0] = 3, inRoot = 1, numsLeft = 1 - 0 = 1
//     left  = build(pre 1..1, in 0..0)   root 9, inRoot = 0, numsLeft = 0
//               both children get an empty range -> null                   => node 9
//     right = build(pre 2..4, in 2..4)   root = pre[2] = 20, inRoot = 3, numsLeft = 3 - 2 = 1
//               left  = build(pre 3..3, in 2..2)  => node 15
//               right = build(pre 4..4, in 4..4)  => node 7
//   result: 3 with left 9 and right 20(15, 7) = [3,9,20,null,null,15,7]
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode is a non-static inner
//   class: create nodes through an instance of the outer class (outer.new TreeNode(3)).
//
// COMPLEXITY
//   Time : O(n) - the map is built in one pass, and each of the n nodes is created once
//          with an O(1) lookup
//   Space: O(n) - the map holds n entries; the recursion is as deep as the tree (up to n
//          for a one-sided tree)
//
// GOTCHAS
//   - The left subtree's SIZE comes from inorder (inRoot - inStart) and is then used to cut
//     the PREORDER range. Mixing the two arrays up is the classic mistake.
//   - preStart + numsLeft is the last preorder index of the left subtree, so the right
//     subtree starts at preStart + numsLeft + 1.
//   - Unique values are essential: with duplicates the map would keep only one index and the
//     root's position would be ambiguous.
//   - Without the map, searching inorder for the root at every node costs O(n) each, O(n^2)
//     in total for a one-sided tree.
//   - The helper's inorder parameter is passed along but never read - only the map is used.
//
// RELATED
//   - LC 106 Construct Binary Tree from Inorder and Postorder Traversal - the mirror
//     problem: the root is the LAST element of postorder
//     (LC106ConstructBinaryTreeFromInorderAndPostorderTraversal.java in this folder).
//   - LC 94 Binary Tree Inorder Traversal - the traversal whose split property is used here.
// ============================================================================
public class LC105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }

    // Rebuilds the tree from its preorder and inorder arrays (values must be distinct).
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // value -> index in inorder, so a root can be located in O(1)
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderMap);
    }



    // Builds the subtree described by preorder[preStart..preEnd] and inorder[inStart..inEnd].
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inOrderMap) {
        // empty range -> no node here
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        // the first preorder element of the range is the root
        TreeNode treeNode = new TreeNode(preorder[preStart]);
        // where the root sits in inorder, and how many nodes lie to its left (= left subtree size)
        int inRoot = inOrderMap.get(treeNode.val);
        int numsLeft = inRoot - inStart;

        // left subtree: the next numsLeft preorder elements / the inorder part before the root
        treeNode.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inOrderMap);
        // right subtree: the remaining preorder elements / the inorder part after the root
        treeNode.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inOrderMap);

        return treeNode;
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

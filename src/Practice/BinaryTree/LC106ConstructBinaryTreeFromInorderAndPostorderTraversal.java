package Practice.BinaryTree;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 106. Construct Binary Tree from Inorder and Postorder Traversal   [Medium]
// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// Topics: Array, Hash Table, Divide and Conquer, Tree, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get two arrays that describe the same binary tree with all-distinct values:
//   inorder (left subtree, then node, then right subtree) and postorder (left subtree,
//   then right subtree, then node). Rebuild the tree and return its root.
//
// EXAMPLES
//   inorder = [9,3,15,20,7],  postorder = [9,15,7,20,3]  ->  [3,9,20,null,null,15,7]
//   inorder = [-1],           postorder = [-1]           ->  [-1]
//
// CONSTRAINTS
//   1 <= inorder.length <= 3000,   postorder.length == inorder.length,
//   -3000 <= inorder[i], postorder[i] <= 3000,   all values are unique,
//   both arrays are valid traversals of the same tree
//
// INTUITION
//   - Postorder visits a node AFTER both of its subtrees, so the LAST element of a
//     postorder range is the root of that (sub)tree.
//   - In inorder the root splits the range: everything to its left is the left subtree,
//     everything to its right is the right subtree.
//   Find the root inside inorder: its position tells how many nodes (numsLeft) the left
//   subtree has. The postorder range without its last element then starts with exactly
//   numsLeft left-subtree nodes, followed by the right subtree's nodes. Both halves are the
//   same problem on smaller ranges, so recurse. Values are unique, so the root's place in
//   inorder is unambiguous, and a HashMap (value -> inorder index) finds it in O(1).
//   This is the mirror image of LC 105, where the root is the FIRST element of preorder.
//
// APPROACH (as implemented below)
//   1. buildTree(postOrder, inorder) fills inOrderMap: value -> its index in inorder, then
//      calls the helper on the whole arrays (both ranges 0 .. n-1).
//   2. Helper buildTree(postorder, postStart, postEnd, inStart, inEnd, inOrderMap):
//        a. empty range (postStart > postEnd || inStart > inEnd) -> null.
//        b. treeNode = new node holding postorder[postEnd] - the LAST element is the root.
//        c. inRoot = inOrderMap.get(treeNode.val)   where the root sits in inorder
//           numsLeft = inRoot - inStart              size of the left subtree
//        d. left  = build(postorder[postStart .. postStart+numsLeft-1],
//                         inorder[inStart .. inRoot-1])
//        e. right = build(postorder[postStart+numsLeft .. postEnd-1],
//                         inorder[inRoot+1 .. inEnd])
//        f. return treeNode.
//      (The helper needs no inorder array: the map answers every question about it.)
//
// DRY RUN   inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]   (example 1)
//   map {9:0, 3:1, 15:2, 20:3, 7:4}
//   build(post 0..4, in 0..4)  root = post[4] = 3, inRoot = 1, numsLeft = 1 - 0 = 1
//     left  = build(post 0..0, in 0..0)   root = post[0] = 9, numsLeft = 0
//               both children get an empty range -> null                   => node 9
//     right = build(post 1..3, in 2..4)   root = post[3] = 20, inRoot = 3, numsLeft = 3 - 2 = 1
//               left  = build(post 1..1, in 2..2)  => node 15
//               right = build(post 2..2, in 4..4)  => node 7
//   result: 3 with left 9 and right 20(15, 7) = [3,9,20,null,null,15,7]
//
// STATUS
//   Signature differs from LeetCode: buildTree takes (postOrder, inorder), while LeetCode
//   passes (inorder, postorder). Called in LeetCode's order it overflows the stack (example 1
//   gives a StackOverflowError), so swap the two parameters before submitting there. As
//   written, with the postorder array first, it is correct.
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
//     the POSTORDER range: the left part is postorder[postStart .. postStart+numsLeft-1],
//     the right part follows it and stops before postEnd (the root).
//   - Postorder is read from its END (the root comes last); in LC 105 preorder is read from
//     its front.
//   - Unique values are essential: with duplicates the map would keep only one index and the
//     root's position would be ambiguous.
//
// RELATED
//   - LC 105 Construct Binary Tree from Preorder and Inorder Traversal - the same idea with
//     the root first (LC105ConstructBinaryTreeFromPreorderAndInorderTraversal.java in this
//     folder).
//   - LC 94 Binary Tree Inorder Traversal - the traversal whose split property is used here.
// ============================================================================
public class LC106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
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

    // Rebuilds the tree from its postorder and inorder arrays (values must be distinct).
    // NOTE the parameter order: postorder first, then inorder (LeetCode uses the opposite).
    public TreeNode buildTree(int[] postOrder, int[] inorder) {
        // value -> index in inorder, so a root can be located in O(1)
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return buildTree(postOrder, 0, postOrder.length - 1, 0, inorder.length - 1, inOrderMap);
    }



    // Builds the subtree described by postorder[postStart..postEnd] and inorder[inStart..inEnd].
    public TreeNode buildTree(int[] postorder, int postStart, int postEnd, int inStart, int inEnd, Map<Integer, Integer> inOrderMap) {
        // empty range -> no node here
        if(postStart > postEnd || inStart > inEnd){
            return null;
        }
        // the last postorder element of the range is the root
        TreeNode treeNode = new TreeNode(postorder[postEnd]);
        // where the root sits in inorder, and how many nodes lie to its left (= left subtree size)
        int inRoot = inOrderMap.get(treeNode.val);
        int numsLeft = inRoot - inStart;

        // left subtree: the first numsLeft postorder elements / the inorder part before the root
        treeNode.left = buildTree(postorder, postStart, postStart + numsLeft - 1 , inStart, inRoot - 1, inOrderMap);
        // right subtree: the postorder elements after those, up to (not including) the root /
        // the inorder part after the root
        treeNode.right = buildTree(postorder, postStart + numsLeft, postEnd - 1, inRoot + 1, inEnd, inOrderMap);

        return treeNode;
    }
}

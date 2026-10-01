package Practice.BinaryTree;

// ============================================================================
// LC 110. Balanced Binary Tree   [Easy]
// https://leetcode.com/problems/balanced-binary-tree/
// Topics: Tree, Depth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   Decide whether a binary tree is height-balanced: at EVERY node, the heights of the
//   left and right subtrees may differ by at most 1. Return true if the tree is balanced,
//   false otherwise. An empty tree is balanced.
//
// EXAMPLES
//   root = [3,9,20,null,null,15,7]       ->  true    the root's sides have heights 1 and 2,
//                                                    the 20's sides 1 and 1
//   root = [1,2,2,3,3,null,null,4,4]     ->  false   the root's left side has height 3 (via
//                                                    2 -> 3 -> 4), its right side only 1
//   root = []                            ->  true
//
// CONSTRAINTS
//   0 <= number of nodes <= 5000,   -10^4 <= Node.val <= 10^4
//
// INTUITION
//   Checking a node needs the heights of both its subtrees, and heights are computed
//   bottom-up anyway (LC 104: height = 1 + max(left, right)). So ride on that recursion:
//   once a node has received its two child heights, compare them, and if they differ by more
//   than 1 record "unbalanced" in a flag. One post-order pass (children before the node)
//   does all the checks, so the work is O(n) - recomputing the heights from scratch at every
//   node would cost O(n^2) on a long chain.
//
// APPROACH (as implemented below)
//   1. isBalanced(root) sets the static flag isBalanced = true, runs the helper on the root
//      and returns the flag.
//   2. maxHeightDiameter(root) returns the height of the subtree and checks balance on the
//      way:
//        a. root == null -> height 0.
//        b. maxLeft and maxRight = the heights of the two subtrees (computed first).
//        c. if Math.abs(maxLeft - maxRight) > 1 -> isBalanced = false. Nothing ever sets the
//           flag back to true during a call, so one bad node is enough.
//        d. return Math.max(maxLeft, maxRight) + 1.
//
// DRY RUN   root = [1,2,2,3,3,null,null,4,4]   (example 2)
//                  1
//                 / \
//                2   2
//               / \
//              3   3
//             / \
//            4   4
//   heights come back bottom-up:
//     the two 4s        -> 1 each
//     left 3            -> maxLeft 1, maxRight 1, diff 0, returns 2
//     right 3           -> 1
//     left 2            -> maxLeft 2, maxRight 1, diff 1 (ok), returns 3
//     right 2           -> 1
//     root 1            -> maxLeft 3, maxRight 1, diff 2 > 1 -> isBalanced = false
//   isBalanced(root) returns false.
//
// COMPLEXITY
//   Time : O(n) - every node is visited once; there is no early exit, so the whole tree is
//          walked even after the first violation
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - Balance must hold at every node. Looking at the root only is not enough: a deeper node
//     can be lopsided while the root's two sides happen to have equal heights.
//   - The flag is static and reset at the start of every isBalanced(root) call, so repeated
//     calls are fine; the shared state would be a problem only for concurrent use.
//   - The static field isBalanced and the method isBalanced() share a name. Java allows it
//     (fields and methods live in different namespaces), it is just easy to misread.
//   - The helper is called maxHeightDiameter but it returns the height, not a diameter (the
//     name comes from the diameter solution it was copied from).
//   - A common improvement: return -1 from the helper as an "unbalanced" signal and stop as
//     soon as it appears, avoiding the extra flag and the wasted work.
//
// RELATED
//   - LC 104 Maximum Depth of Binary Tree - the height recursion that is reused here.
//   - LC 543 Diameter of Binary Tree - the same post-order pattern: compute child heights
//     and update a global answer on the way up.
// ============================================================================
public class LC110BalancedBinaryTree {
    public static void main(String[] args) {
        // example 1. Expected output: true
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(isBalanced(root));
    }

    // set to false by the helper as soon as some node has sides differing by more than 1
    public static boolean isBalanced;
    // Returns true if the tree is height-balanced.
    public static boolean isBalanced(TreeNode root) {
        // reset, so that an earlier call cannot leave a stale false behind
        isBalanced = true;
        maxHeightDiameter(root);
        return isBalanced;
    }
    // Returns the height of the subtree (in nodes) and flags unbalanced nodes on the way up.
    public static int maxHeightDiameter(TreeNode root) {
        // empty subtree: height 0
        if(root == null){
            return 0;
        }
        // children first (post-order): their heights are needed to judge this node
        int maxLeft = maxHeightDiameter(root.left);
        int maxRight = maxHeightDiameter(root.right);
        // the balance rule for this node
        if(Math.abs(maxLeft - maxRight) > 1){
            isBalanced = false;
        }
        return Math.max(maxLeft, maxRight) + 1;
    }

    // minimal LeetCode-style node; a static nested class, so new TreeNode(3) works directly
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

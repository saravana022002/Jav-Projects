package Practice.BST;

// ============================================================================
// LC 108. Convert Sorted Array to Binary Search Tree   [Easy]
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// Topics: Array, Divide and Conquer, Tree, Binary Search Tree, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get an int array nums sorted in strictly increasing order. Build a binary search
//   tree holding exactly these values that is height-balanced (at every node the heights
//   of the two subtrees differ by at most 1) and return its root. When several balanced
//   BSTs exist, any of them is accepted.
//
// EXAMPLES
//   nums = [-10,-3,0,5,9]  ->  [0,-3,9,-10,null,5]
//       [0,-10,5,null,-3,null,9] is accepted too - it is what the fixed code builds
//   nums = [1,3]           ->  [3,1]     ([1,null,3] is accepted too)
//
// CONSTRAINTS
//   1 <= nums.length <= 10^4,   -10^4 <= nums[i] <= 10^4,   nums is strictly increasing
//
// INTUITION
//   An inorder walk of a BST visits the values in sorted order, so nums already is the
//   inorder sequence of the tree we want. Picking the middle element as the root puts the
//   smaller half of the values on the left and the bigger half on the right, so the two
//   sides differ in size by at most one. Repeating that for each half keeps every node
//   balanced, and the BST order holds automatically.
//
// APPROACH (as implemented below)
//   1. sortedArrayToBST calls constructBst on the whole index range [0, nums.length - 1].
//   2. constructBst(nums, left, right):
//        a. left > right -> empty range -> null.
//        b. mid = (left + right) / 2; nums[mid] becomes the node treeNode.
//        c. treeNode.left  = constructBst(nums, left, mid)       <- bug: should be mid - 1
//           treeNode.right = constructBst(nums, mid + 1, right)
//
// DRY RUN   nums = [-10,-3,0,5,9]
//   As written:
//     (0,4) mid=2 -> node 0,   left call (0,2)
//     (0,2) mid=1 -> node -3,  left call (0,1)
//     (0,1) mid=0 -> node -10, left call (0,0)
//     (0,0) mid=0 -> node -10, left call (0,0) again ... forever -> StackOverflowError
//   With the fix (left call on (left, mid - 1)):
//     (0,4) mid=2 -> 0;  left  (0,1) mid=0 -> -10, whose right (1,1) -> -3
//                        right (3,4) mid=3 -> 5,   whose right (4,4) -> 9
//     result [0,-10,5,null,-3,null,9]   (the accepted alternative of example 1)
//
// STATUS
//   Known issue: the left recursion uses (left, mid) instead of (left, mid - 1). Once a
//   range is a single element (left == right == mid) it calls itself with the same range,
//   so the recursion never ends: every input, including both examples, throws
//   StackOverflowError. The correct left call is constructBst(nums, left, mid - 1).
//   main() is empty, so running the file prints nothing.
//   The code is left unchanged on purpose.
//
// COMPLEXITY   (of the intended version with mid - 1; as written it never finishes)
//   Time : O(n) - every index becomes a node exactly once, with O(1) work per call
//   Space: O(log n) - recursion depth is the height of the balanced tree
//          (the n new nodes are the output itself)
//
// GOTCHAS
//   - mid must be left out of BOTH halves: [left, mid - 1] and [mid + 1, right].
//     Keeping it in either half repeats a one-element range forever.
//   - (left + right) / 2 cannot overflow here (indexes < 10^4);
//     left + (right - left) / 2 is the habit that is always safe.
//
// RELATED
//   - LC 109 Convert Sorted List to Binary Search Tree - the same idea on a linked list,
//     where finding the middle is no longer O(1).
// ============================================================================
public class LC108ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }
    // Returns the root of a height-balanced BST built from the whole array.
    public static TreeNode sortedArrayToBST(int[] nums) {
        return constructBst(nums, 0, nums.length - 1);
    }

    // Builds a balanced BST from nums[left..right] (both inclusive); null for an empty range.
    public static TreeNode constructBst(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        // the middle value is the root, so each side gets half of the remaining values
        int mid = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        // BUG: should be (left, mid - 1); with mid kept, the range (i, i) repeats forever
        treeNode.left = constructBst(nums, left, mid);
        // values bigger than nums[mid] form the right subtree
        treeNode.right = constructBst(nums, mid + 1, right);
        return treeNode;
    }

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
}

package Practice.BST;

// ============================================================================
// LC 1373. Maximum Sum BST in Binary Tree   [Hard]
// https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
// Topics: Dynamic Programming, Tree, Depth-First Search, Binary Search Tree, Binary Tree,
//         DP on Trees
// ============================================================================
//
// PROBLEM
//   You get an ordinary binary tree. Look at every subtree (a node plus everything below
//   it) that happens to be a valid BST: all keys on the left strictly smaller than the
//   node, all keys on the right strictly bigger, and both sides BSTs themselves. Return
//   the largest key sum among those subtrees. The empty tree counts as a BST with sum 0,
//   so the answer is never below 0.
//
// EXAMPLES
//   root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]  ->  20
//       the subtree rooted at 3 is a BST: 3 + 2 + 5 + 4 + 6 = 20
//   root = [4,3,null,1,2]  ->  2    3 has the smaller 2 as its RIGHT child, so 3 and 4 are
//                                   not BSTs; the best BST is the leaf 2
//   root = [-4,-2,-5]      ->  0    every key is negative, so the empty BST wins
//
// CONSTRAINTS
//   1 <= number of nodes <= 4 * 10^4,   -4 * 10^4 <= Node.val <= 4 * 10^4
//
// INTUITION
//   A subtree is a BST exactly when both child subtrees are BSTs AND the node sits
//   between them: (largest key on the left) < node < (smallest key on the right).
//   Comparing with the direct children is not enough - the whole side must fit.
//   So each subtree reports four facts to its parent: its min, its max, its sum and
//   whether it is a BST. Computing children first (post-order) lets every node decide in
//   O(1), and every valid BST met on the way is a candidate for the answer.
//
// APPROACH (as implemented below)
//   1. maxSumBST runs maxSumBSTHelper(root) and returns the field maxBstSum (starts at 0,
//      the empty BST).
//   2. maxSumBSTHelper(root) returns a NodeValue(min, max, sum, isBST):
//        a. root == null -> (MAX_VALUE, MIN_VALUE, 0, true): an empty BST whose min/max
//           let any parent pass the comparison.
//        b. Get left and right first.
//        c. If both are BSTs and left.max < root.val < right.min: sum = left.sum +
//           right.sum + root.val, update maxBstSum, and return
//           (min(root.val, left.min), max(root.val, right.max), sum, true).
//        d. Otherwise return isBST = false - every ancestor then fails too.
//
// DRY RUN   root = [4,3,null,1,2]   (example 2)
//   leaf 1: both children empty -> BST, sum 1  -> maxBstSum = 1, returns (1,1,1,true)
//   leaf 2: both children empty -> BST, sum 2  -> maxBstSum = 2, returns (2,2,2,true)
//   node 3: left.max 1 < 3 ok, but 3 < right.min 2 fails -> isBST = false
//   node 4: left is not a BST -> isBST = false
//   return maxBstSum = 2
//
// STATUS
//   There is no main(), so running the file fails with "Main method not found".
//   TreeNode and NodeValue are non-static inner classes: build nodes through an instance,
//   e.g. new LC1373MaximumSumBSTInBinaryTree().new TreeNode(5).
//
// COMPLEXITY
//   Time : O(n) - every node is visited once and does O(1) work
//   Space: O(h) - recursion depth is the tree height (O(n) for a chain)
//
// GOTCHAS
//   - The answer starts at 0, not MIN_VALUE, because the empty BST is allowed (example 3).
//   - The sentinels MAX_VALUE / MIN_VALUE never clash with real keys (|val| <= 4 * 10^4),
//     and the largest possible sum, 4 * 10^4 * 4 * 10^4 = 1.6 * 10^9, still fits an int.
//   - maxBstSum is never reset, so use a new object per tree. Reusing one object carries
//     the old best: example 1 and then example 3 on the same object prints 20 twice.
//
// RELATED
//   - LC 98 Validate Binary Search Tree - the same "the whole side must fit" rule for one
//     tree; here it is checked bottom-up for every subtree.
//   - LC 333 Largest BST Subtree - the same post-order (min, max, isBST) info, but it
//     maximises the node count instead of the sum.
// ============================================================================
public class LC1373MaximumSumBSTInBinaryTree {

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
    // Returns the largest key sum of any BST subtree (0 if none beats the empty tree).
    public int maxSumBST(TreeNode root) {
        maxSumBSTHelper(root);
        return maxBstSum;
    }
    // best BST sum seen so far; 0 = the empty BST. Not reset between calls.
    public int maxBstSum = 0;
    // Post-order: returns min, max, sum and isBST of the subtree rooted at root,
    // and records the sum in maxBstSum whenever that subtree is a BST.
    public NodeValue maxSumBSTHelper(TreeNode root) {
        // empty subtree: a BST with sum 0; min = +inf and max = -inf so that any parent
        // value is "bigger than everything on the left" and "smaller than everything on
        // the right"
        if(root == null){
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }
        NodeValue left = maxSumBSTHelper(root.left);
        NodeValue right = maxSumBSTHelper(root.right);
        // BST test: both sides are BSTs and root lies strictly between them
        if(left.max < root.val && root.val < right.min && left.isBST && right.isBST){
            int sum = left.sum + right.sum + root.val;
            maxBstSum = Math.max(maxBstSum, sum);
            // min/max with root.val handle an empty side (its sentinel loses to root.val)
            return new NodeValue(Math.min(root.val, left.min), Math.max(root.val, right.max), sum, true);
        }else {
            // not a BST -> no ancestor can be one either; the other fields are ignored
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, false);
        }
    }

    // what a subtree reports to its parent
    public class NodeValue {
        boolean isBST;
        int min;
        int max;
        int sum;
        NodeValue() {}
        NodeValue(int min, int max, int sum,boolean isBST) {
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.isBST = isBST;
        }
    }

}

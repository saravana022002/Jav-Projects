package Practice.BST;

// ============================================================================
// LC 700. Search in a Binary Search Tree   [Easy]
// https://leetcode.com/problems/search-in-a-binary-search-tree/
// Topics: Tree, Binary Search Tree, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary search tree and a number val. Find the node whose value
//   equals val and return that node - which means the whole subtree hanging under it,
//   not just the number. If no node holds val, return null.
//
// EXAMPLES
//   root = [4,2,7,1,3],  val = 2  ->  [2,1,3]   the node 2 together with its children 1, 3
//   root = [4,2,7,1,3],  val = 5  ->  []         no node holds 5, so the answer is null
//
// CONSTRAINTS
//   1 <= number of nodes <= 5000,   1 <= Node.val <= 10^7,
//   root is a BST,   1 <= val <= 10^7
//
// INTUITION
//   In a BST everything left of a node is smaller and everything right of it is larger.
//   So one comparison of val with root.val says which side val must be on, and the whole
//   other side can be ignored. It is binary search on a tree: each step discards one
//   subtree, so only a single root-to-node path is ever visited.
//
// APPROACH (as implemented below)
//   1. root == null   -> we fell off the tree, val is not there: return root (= null).
//      root.val == val -> found it: return root (its subtree comes along for free).
//   2. root.val > val  -> val can only be in the left subtree:  searchBST(root.left, val).
//   3. otherwise (root.val < val) -> only the right subtree:     searchBST(root.right, val).
//
// DRY RUN   root = [4,2,7,1,3]
//   val = 2   searchBST(4): 4 != 2 and 4 > 2  -> go left
//             searchBST(2): root.val == val   -> return the node 2 (children 1 and 3 included)
//   val = 5   searchBST(4): 4 < 5             -> go right
//             searchBST(7): 7 > 5             -> go left
//             searchBST(null)                 -> return null
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode is a non-static inner
//   class: create nodes through an instance, e.g. new LC700SearchInABinarySearchTree().new
//   TreeNode(4).
//
// COMPLEXITY
//   Time : O(h) - one root-to-node path, h = tree height (O(log n) when balanced,
//          O(n) for a chain-shaped BST)
//   Space: O(h) - the recursion stack is as deep as the path (a loop would need only O(1))
//
// GOTCHAS
//   - The null test has to come first: in "root == null || root.val == val" the || skips
//     root.val when root is null; reversed, it would throw a NullPointerException.
//   - The returned node is the original object in the tree, not a copy.
//   - No equality case is needed in the direction test: BST values are distinct, and an
//     equal value was already returned in step 1.
//
// RELATED
//   - LC 701 Insert into a Binary Search Tree - the same left/right walk, but it attaches
//     a new node at the spot where this search would fall off the tree.
//   - LC 235 Lowest Common Ancestor of a Binary Search Tree - also walks down by
//     comparing values.
// ============================================================================
public class LC700SearchInABinarySearchTree {
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }
    // Returns the node whose value is val (with its whole subtree), or null if there is none.
    public TreeNode searchBST(TreeNode root, int val) {
        // empty spot -> not found (returns null);  matching node -> found
        if(root == null || root.val == val){
            return root;
        }
        // BST rule: a smaller value is on the left, a larger one on the right
        if(root.val > val){
            return searchBST(root.left, val);
        }else {
            return searchBST(root.right, val);
        }
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

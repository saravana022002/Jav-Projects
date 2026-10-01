package Practice.BST;

import java.util.Stack;

// ============================================================================
// LC 653. Two Sum IV - Input is a BST   [Easy]
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
// Topics: Hash Table, Two Pointers, Tree, Depth-First Search, Breadth-First Search,
//         Binary Search Tree, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary search tree and a number k. Decide whether the tree
//   holds two different nodes whose values add up to k: true if it does, false if not.
//
// EXAMPLES
//   root = [5,3,6,2,4,null,7],  k = 9   ->  true     2 + 7 = 9  (3 + 6 also works)
//   root = [5,3,6,2,4,null,7],  k = 28  ->  false    even the two largest values give
//                                                    only 6 + 7 = 13
//
// CONSTRAINTS
//   1 <= number of nodes <= 10^4,   -10^4 <= Node.val <= 10^4,
//   root is a valid BST,   -10^5 <= k <= 10^5
//
// INTUITION
//   Reading a BST in-order gives its values in ascending order - a sorted array. On a
//   sorted array, "find two numbers that add up to k" is the classic two-pointer job:
//   one pointer on the smallest value, one on the largest.
//     sum too small -> move the low pointer up;   sum too big -> move the high pointer down.
//   Each move throws away a value that can no longer be part of an answer, so the
//   pointers only ever move inward and the scan is linear.
//   To avoid copying the tree into an array (O(n) extra memory), each "pointer" is a
//   stack-based BST iterator that produces its next value on demand: one walks the tree
//   in ascending order, the other in descending order (the mirror image). Each keeps just
//   one root-to-node path on its stack.
//
// APPROACH (as implemented below)
//   1. Build two BSTIterator objects over the same tree:
//        left  = BSTIterator(root, false)   ascending  (smallest value first)
//        right = BSTIterator(root, true)    descending (largest value first)
//   2. l = left.next() (the minimum), r = right.next() (the maximum).
//   3. While l < r:
//        l + r == k  -> return true
//        l + r >  k  -> sum too big,   r = right.next()   (next smaller value)
//        l + r <  k  -> sum too small, l = left.next()    (next larger value)
//   4. The pointers met or crossed without a match -> return false.
//   How the iterator works: pushAll(node) pushes node and then keeps following the left
//   child (the right child when reverse), so the next value in its order is on top.
//   next() pops that node, then pushAll()s the child on the other side (right child when
//   ascending, left child when reverse) - the values that come right after it.
//
// DRY RUN   root = [5,3,6,2,4,null,7]   (stacks are written bottom -> top)
//                   5
//                  / \
//                 3   6
//                / \   \
//               2   4   7
//   start   left stack [5,3,2],  right stack [5,6,7]
//           l = left.next()  pops 2  -> l = 2      r = right.next() pops 7  -> r = 7
//   k = 9   2 + 7 = 9 == k                               -> true
//   k = 28  2 + 7 =  9 < 28  -> l = 3  (pops 3, pushes its right child 4)
//           3 + 7 = 10 < 28  -> l = 4
//           4 + 7 = 11 < 28  -> l = 5  (pops 5, pushes its right child 6)
//           5 + 7 = 12 < 28  -> l = 6  (pops 6, pushes its right child 7)
//           6 + 7 = 13 < 28  -> l = 7  (pops 7)
//           l = 7, r = 7: l < r is false, loop ends          -> false
//
// STATUS
//   main() is empty, so running the file prints nothing. TreeNode and BSTIterator are
//   non-static inner classes: create nodes through an instance, e.g.
//   new LC653TwoSumIVInputIsABST().new TreeNode(5).
//
// COMPLEXITY
//   Time : O(n) - each iterator pops a node at most once and every loop step moves one
//          pointer inward, so there are at most about n steps in total
//   Space: O(h) - each stack holds one root-to-node path, h = tree height
//          (O(log n) for a balanced tree, O(n) for a chain)
//
// GOTCHAS
//   - The loop condition is l < r, not l <= r. BST values are unique, so l == r means
//     both pointers sit on the same node - and a node must not be paired with itself
//     (root = [1], k = 2 has to give false).
//   - next() never meets an empty stack: it is only called while l < r, which guarantees
//     at least one more value on that side.
//   - hasNext() is never called.
//   - A HashSet of visited values (or an in-order list plus two indexes) is shorter to
//     write but costs O(n) extra memory; the two iterators need only O(h).
//
// RELATED
//   - LC 167 Two Sum II - Input Array Is Sorted - the same two-pointer idea on an array.
//   - LC 173 Binary Search Tree Iterator - the stack iterator that is used twice here.
//   - LC 1 Two Sum - the hash-based version for an unsorted array.
// ============================================================================
public class LC653TwoSumIVInputIsABST {

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
    public static void main(String[] args) {
        // empty - no test is run (see STATUS)
    }


    // Returns true if two different nodes of the BST add up to k.
    // Two pointers over the sorted order: l climbs up from the minimum, r down from the maximum.
    public boolean findTarget(TreeNode root, int k) {
        // left hands out values in ascending order, right in descending order
        BSTIterator left = new BSTIterator(root, false);
        BSTIterator right = new BSTIterator(root, true);
        // start at the two extremes: smallest and largest value
        int l = left.next();
        int r = right.next();
        // l < r: stop when the pointers meet or cross - a node must not pair with itself
        while (l < r){
            if(l + r == k){
                return true;
            }else if(l + r > k){
                // sum too big -> drop the current largest, take the next smaller value
                r = right.next();
            }else {
               // sum too small -> drop the current smallest, take the next larger value
               l = left.next();
            }
        }
        return false;
    }

    // Stack-based BST iterator: reverse == false gives the values in ascending order (in-order),
    // reverse == true gives them in descending order (mirror-image in-order).
    // The stack only ever holds one path of the tree, so it needs O(h) memory.
    public class BSTIterator {
        Stack<TreeNode> stkNodes = new Stack<>();
        boolean reverse;
        public BSTIterator(TreeNode root, boolean isRev) {
            this.reverse = isRev;
            // load the first spine; the node on top is the first value in this order
            pushAll(root);
        }

        // Pops and returns the next value; must not be called when the stack is empty.
        public int next() {
            TreeNode tempNode = stkNodes.pop();
            // load the spine of the subtree that comes right after tempNode in this order
            if(reverse) {
                pushAll(tempNode.left);
            }else {
                pushAll(tempNode.right);
            }
            return tempNode.val;
        }

        // not used by findTarget
        public boolean hasNext() {
            return !stkNodes.isEmpty();
        }

        // Pushes root and then keeps going left (right when reverse) until there is no child;
        // the last node pushed, now on top, is the next value in order.
        void pushAll(TreeNode root){
            while(root != null){
                stkNodes.push(root);
                if(reverse) {
                    root = root.right;
                }else {
                    root = root.left;
                }
            }
        }
    }

}


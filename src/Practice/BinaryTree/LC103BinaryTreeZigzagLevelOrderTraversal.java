package Practice.BinaryTree;

import java.util.*;

// ============================================================================
// LC 103. Binary Tree Zigzag Level Order Traversal   [Medium]
// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
// Topics: Tree, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get the root of a binary tree. Return its values level by level as a list of
//   lists, but read the levels in alternating directions: the root level left to right,
//   the next level right to left, the next left to right again, and so on.
//   An empty tree gives an empty list.
//
// EXAMPLES
//   root = [3,9,20,null,null,15,7]  ->  [[3],[20,9],[15,7]]    level 2 (9, 20) is reversed
//   root = [1]                      ->  [[1]]
//   root = []                       ->  []
//
// CONSTRAINTS
//   0 <= number of nodes <= 2000,   -100 <= Node.val <= 100
//
// INTUITION
//   A normal level-order traversal (see LC 102) always reads a level from left to right.
//   For a right-to-left level there is no need to queue the children differently or to
//   reverse the list afterwards: just build that level's list by inserting each value at
//   the FRONT instead of the back. A LinkedList can insert at either end in O(1), and a
//   boolean that flips after every level says which end to use.
//
// APPROACH (as implemented below)
//   1. root == null -> return an empty list.
//   2. queue = [root], ans = empty list of lists, zig = true (the first level goes left
//      to right).
//   3. While the queue is not empty, one round = one level:
//        sub = new LinkedList for this level, queueSize = queue.size() (nodes in the level);
//        repeat queueSize times: poll a node; if zig, sub.addLast(val) (keeps the reading
//        order), else sub.addFirst(val) (reverses it); then offer its left and right child
//        - always left first, the queue order never changes.
//   4. After the level: ans.add(sub) and zig = !zig. Return ans when the queue is empty.
//
// DRY RUN   root = [3,9,20,null,null,15,7]   (the tree main() builds)
//   round 1  zig=true    queue [3]     poll 3: addLast  -> sub [3]        queue [9,20]
//   round 2  zig=false   queue [9,20]  poll 9: addFirst -> sub [9]
//                                      poll 20: addFirst -> sub [20,9]    queue [15,7]
//   round 3  zig=true    queue [15,7]  poll 15: addLast -> sub [15]
//                                      poll 7: addLast  -> sub [15,7]
//   ans = [[3],[20,9],[15,7]]
//
// STATUS
//   The method is still called levelOrder (copied from the LC 102 file) although it does the
//   zigzag traversal; LeetCode calls it zigzagLevelOrder. main() prints
//   [[3], [20, 9], [15, 7]], which is the expected answer for example 1.
//
// COMPLEXITY
//   Time : O(n) - every node is queued and polled once and inserted at an end of a
//          LinkedList in O(1)
//   Space: O(n) - the queue holds up to one whole level, and the answer stores all n values
//
// GOTCHAS
//   - The reversal comes from the side each VALUE is inserted on; the children are still
//     queued left, then right, so the queue order is the same as in plain level order.
//   - sub is declared as LinkedList<Integer> (not List) because addFirst / addLast belong to
//     LinkedList; it is added to ans as a plain List<Integer>.
//   - zig flips once per LEVEL (after ans.add), not once per node.
//   - Alternative: fill an ArrayList normally and Collections.reverse() it on the
//     right-to-left levels - same O(n) in total.
//
// RELATED
//   - LC 102 Binary Tree Level Order Traversal - the plain version this one is built on
//     (LC102BinaryTreeLevelOrderTraversal.java in this folder).
//   - LC 199 Binary Tree Right Side View - also works level by level.
// ============================================================================
public class LC103BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        // example 1. Expected output: [[3], [20, 9], [15, 7]]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(levelOrder(root));
    }
    // Zigzag level order: level 0 left to right, level 1 right to left, and so on.
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // empty tree -> no levels
        if(root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        // true = this level is read left to right, false = right to left
        boolean zig = true;
        while (!queue.isEmpty()){
            // one round of this loop = one level
            LinkedList<Integer> sub = new LinkedList<>();
            // the queue holds exactly this level right now - remember how many nodes it is
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode treeNode = queue.poll();
                // append for a left-to-right level, put in front for a right-to-left level
                if(zig) {
                    sub.addLast(treeNode.val);
                }else {
                    sub.addFirst(treeNode.val);
                }
                // children are always queued left, then right
                if(treeNode.left != null){
                    queue.offer(treeNode.left);
                }
                if(treeNode.right != null){
                    queue.offer(treeNode.right);
                }
            }
            ans.add(sub);
            // the next level is read in the opposite direction
            zig = !zig;
        }
        return ans;
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

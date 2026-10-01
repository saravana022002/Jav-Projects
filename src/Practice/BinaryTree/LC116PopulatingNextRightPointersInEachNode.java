package Practice.BinaryTree;

import Practice.BinaryTree.LC102BinaryTreeLevelOrderTraversal;

import java.util.*;

// ============================================================================
// LC 116. Populating Next Right Pointers in Each Node   [Medium]
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
// Topics: Linked List, Tree, Depth-First Search, Breadth-First Search, Binary Tree
// ============================================================================
//
// PROBLEM
//   You get a PERFECT binary tree (every parent has two children, all leaves are on the
//   same level). Each node has val, left, right and an extra pointer next, which starts out
//   null. Set every next to the node immediately to its right on the same level; the
//   rightmost node of a level keeps null. Return the root.
//   Follow-up in the statement: use only constant extra space (the recursion stack does not
//   count).
//
// EXAMPLES
//   root = [1,2,3,4,5,6,7]  ->  [1,#,2,3,#,4,5,6,7,#]   each level becomes a chain:
//                               1 -> null;  2 -> 3 -> null;  4 -> 5 -> 6 -> 7 -> null
//                               (# in the output marks the end of a level)
//   root = []               ->  []
//
// CONSTRAINTS
//   0 <= number of nodes <= 2^12 - 1,   -1000 <= Node.val <= 1000
//
// INTUITION
//   Level-order BFS (as in LC 102) has the whole level in the queue, left to right, at the
//   start of each round. So inside a round the node polled next is exactly the right
//   neighbour of the node polled before it: remember the previous node and set
//   prev.next = current. The last node of a round never gets linked, so it keeps null.
//
// APPROACHES IN THIS FILE
//   A. connect()                  - level-order BFS, link each node to its predecessor in the
//                                   same level - O(n) time, O(n) space for the queue; works
//                                   for ANY binary tree    <- main() runs this one
//   B. connectitcut() -> dfs()    - recursion that re-uses the next pointers of the level
//                                   above: left.next = right, right.next = parent.next.left
//                                   - O(n) time, O(h) stack, no queue; PERFECT trees only
//   (h = height of the tree.)
//
// APPROACH (as implemented below, method A)
//   1. queue = [root].
//   2. While the queue is not empty, one round = one level:
//        queueSize = queue.size() (nodes in this level), prev = null;
//        repeat queueSize times: nude = queue.poll(); if prev != null then prev.next = nude;
//        prev = nude; offer nude.left and nude.right when they exist.
//   3. Return root.
//
// DRY RUN   root = [1,2,3,4,5,6,7]   (the tree main() builds)
//   round 1  queue [1]        poll 1 (prev is null, nothing to link)          queue [2,3]
//   round 2  queue [2,3]      poll 2 (prev null); poll 3: 2.next = 3          queue [4,5,6,7]
//   round 3  queue [4,5,6,7]  poll 4; poll 5: 4.next = 5; poll 6: 5.next = 6;
//                             poll 7: 6.next = 7
//   1, 3 and 7 were the last nodes of their rounds, so their next stays null.
//
// STATUS
//   Known issue: connect(null) - the empty tree, the statement's example 2 (root = []) - throws
//   a NullPointerException: queue.offer(null) puts a null into the queue, and nude.left
//   dereferences it. It should return null. connectitcut(null) handles the empty tree
//   correctly.
//   The code is left unchanged on purpose.
//   Also: main() builds the 7-node tree and calls connect(root) but prints nothing. The
//   import of LC102BinaryTreeLevelOrderTraversal is not used.
//
// COMPLEXITY
//   Time : O(n) for both methods - every node is handled once
//   Space: A: O(n) - the queue holds up to one whole level (about n/2 nodes)
//          B: O(h) = O(log n) for the recursion stack of a perfect tree, nothing else
//
// GOTCHAS
//   - prev is reset to null at the start of every round, so the last node of a level is
//     never linked to the first node of the next level.
//   - Method B needs the tree to be perfect: it assumes that if root.next exists then
//     root.next.left exists too, and it stops at the first node that lacks a child. On other
//     trees (LC 117) it leaves wrong or missing links; method A handles them unchanged.
//   - Method B sets the children's pointers BEFORE recursing, so when dfs reaches a node,
//     that node's own next has already been filled in by its parent's call. That is what
//     lets it reach across to the neighbouring subtree with root.next.left.
//   - dfs's return value is never used. Node is a package-private static nested class
//     with public fields.
//
// RELATED
//   - LC 117 Populating Next Right Pointers in Each Node II - the same task on any binary
//     tree; method A works there as it is.
//   - LC 199 Binary Tree Right Side View - also a level-by-level walk.
//   - LC 102 Binary Tree Level Order Traversal - the level-rounds loop used in method A.
// ============================================================================
public class LC116PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        // perfect tree 1 / (2: 4, 5) / (3: 6, 7); connect() links each level, nothing is printed
        // (afterwards 2.next = 3, 4.next = 5, 5.next = 6, 6.next = 7, all others null)
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n2 = new Node(2, n4, n5, null);
        Node n3 = new Node(3, n6, n7, null);
        Node root = new Node(1, n2, n3, null);
        connect(root);
    }

    // Approach A: level-order BFS - link every node to the previous node of its level.
    // O(n) time, O(n) space. Throws a NullPointerException for root == null (see STATUS).
    public static Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            // the queue holds exactly one level right now
            int queueSize = queue.size();
            // previous node of THIS level (null again at the start of every level)
            Node prev = null;
            for (int i = 0; i < queueSize; i++) {
                Node nude = queue.poll();
                // the node to my left points to me
                if (prev != null) {
                    prev.next = nude;
                }
                prev = nude;
                // children join the back of the queue = they form the next level
                if(nude.left != null){
                    queue.offer(nude.left);
                }
                if(nude.right != null){
                    queue.offer(nude.right);
                }

            }
        }
        return root;
    }


    // Approach B entry point (LeetCode's name is connect): handles the empty tree, then dfs.
    public Node connectitcut(Node root) {
        if (root == null) return null;
        dfs(root);
        return root;
    }

    // Approach B: perfect trees only - no queue, O(h) recursion stack.
    // Uses the next pointers of the level above, which are already set when we get here.
    public static Node dfs(Node root) {
        // a leaf (in a perfect tree both children are missing together): nothing to link
        if(root == null || root.left == null || root.right == null){
            return null;
        }
        // siblings: the left child points to the right child
        root.left.next = root.right;
        // cousins: my right child points to the left child of my right neighbour (if any)
        if(root.next != null){
            root.right.next = root.next.left;
        }
        dfs(root.left);
        dfs(root.right);
        return root;
    }


    // tree node with the extra next pointer; constructor arguments: (val, left, right, next)
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

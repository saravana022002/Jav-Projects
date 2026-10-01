package Practice.BinaryTree;

import java.util.*;

// ============================================================================
// GFG - Top View of Binary Tree   [Medium]
// https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
// Topics: Binary Tree, Breadth-First Search (level order), TreeMap, Horizontal Distance
// ============================================================================
//
// PROBLEM
//   Picture the tree seen from above: in every vertical column you see only the node
//   closest to the root. A column is the set of nodes with the same horizontal distance
//   (hd) from the root: the root has hd 0, a left child has its parent's hd - 1, a right
//   child has its parent's hd + 1. Return the values of the visible nodes from the
//   leftmost column to the rightmost one.
//
// EXAMPLES
//   root = [1,2,3]                      ->  [2,1,3]
//   root = [10,20,30,40,60,90,100]      ->  [40,20,10,30,100]
//       60 and 90 also sit in column 0 (below the root 10), but the root is closer to the
//       top, so it is the one that shows
//
// CONSTRAINTS
//   1 <= number of nodes <= 10^5,   1 <= node data <= 10^5
//
// INTUITION
//   Give every node its horizontal distance hd; nodes with equal hd are in the same
//   column. Visit the nodes level by level, top to bottom: the FIRST node met in a column
//   is then the topmost one. So keep a map hd -> value and write a column's entry only
//   if it is still empty; later (lower) nodes of that column never overwrite it. A
//   TreeMap keeps its keys sorted, so its values already come out from the leftmost
//   column to the rightmost one.
//
// APPROACH (as implemented below)
//   1. horVsNode is a TreeMap from hd to a node value, queue is the BFS queue. Node has
//      no hd field here, so the queue holds Pair(node, hd) objects. Put the root (hd 0)
//      into the map and the queue.
//   2. While the queue is not empty, take it one level at a time (size = nodes in the level):
//        poll a Pair; if horVsNode does not contain pair.hd yet, store pair.node.data for it
//        - the first visit of a column is its topmost node;
//        a left child is queued with hd - 1, a right child with hd + 1.
//   3. Return new ArrayList<>(horVsNode.values()) - ascending hd, leftmost column first.
//
// DRY RUN   root = [2,3,4,N,6]   (the tree main() builds)
//                2            hd  0
//               / \
//              3   4          hd -1 and +1
//               \
//                6            hd  0   (right child of 3: -1 + 1)
//   start        map {0=2}, queue [(2,0)]
//   poll (2,0)   column 0 already has a value -> skip;  queue [(3,-1),(4,1)]
//   poll (3,-1)  column -1 is empty -> put(-1,3);       queue [(4,1),(6,0)]
//   poll (4,1)   column 1 is empty  -> put(1,4)
//   poll (6,0)   column 0 already has a value (2) -> skip: the root stays on top
//   map {-1=3, 0=2, 1=4}  ->  values in key order: [3, 2, 4]
//
// COMPLEXITY
//   Time : O(n log n) - every node does a TreeMap lookup (and maybe a put), each O(log c)
//          for c distinct columns (c <= n); the BFS itself is O(n)
//   Space: O(n) - the queue holds up to one whole level, the map one entry per column
//
// GOTCHAS
//   - Only the FIRST visit of a column writes (containsKey check) - the opposite of the
//     bottom view (GFGBottomViewOfBinaryTree.java in this folder), where every visit
//     overwrites.
//   - Two nodes can share both the column and the level (e.g. the nodes reached from the
//     root by left,right,right,right and by right,left,right,right: both are 4 levels down
//     in column 2). The statement does not say which one shows; this code keeps the one
//     met first in level order, i.e. the left one.
//   - root == null throws a NullPointerException at root.data - there is no empty check.
//   - The root is put into the map before the loop, so when the loop polls it the column
//     is already filled - harmless.
//   - The inner loop over "size" is not needed for correctness: the nodes are polled in
//     the same order without it.
//
// RELATED
//   - GFGBottomViewOfBinaryTree.java - the same horizontal-distance idea, but it keeps the
//     last node of each column instead of the first.
//   - LC 987 Vertical Order Traversal of a Binary Tree - the same columns, but it lists
//     every node of each column, not just one.
//   - LC 199 Binary Tree Right Side View - another "what is visible from one side"
//     problem, also solved with a level-order traversal.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGTopViewOfBinaryTree {
    public static void main(String[] args) {
        // tree [2,3,4,N,6]: the 6 is hidden behind the root in column 0. Expected output: [3, 2, 4]
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.right = new Node(6);
        System.out.println(topView(root));
    }
    // Returns the values visible from above, leftmost column first.
    public static ArrayList<Integer> topView(Node root) {
        // horizontal distance -> value of the topmost node seen in that column
        // (TreeMap = keys in ascending order = columns left to right)
        TreeMap<Integer, Integer> horVsNode = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        // the root is column 0
        horVsNode.put(0, root.data);
        queue.add(new Pair(root, 0));
        // level-order traversal: upper levels first, and left to right within a level
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                // first visit = topmost node of this column; never overwrite it afterwards
                if(!horVsNode.containsKey(pair.hd)) {
                    horVsNode.put(pair.hd, pair.node.data);
                }
                // going left shifts the column by -1, going right by +1
                if(pair.node.left != null){
                    queue.offer(new Pair(pair.node.left, pair.hd - 1));
                }
                if(pair.node.right != null){
                    queue.offer(new Pair(pair.node.right, pair.hd + 1));
                }
            }
        }
        // values in ascending order of hd = left to right
        return new ArrayList<>(horVsNode.values());
    }
    // a node together with its horizontal distance from the root
    static class Pair{
        int hd;
        Node node;
        Pair(Node node, int hd)
        {
            this.node = node;
            this.hd = hd;
        }
    }
    // GeeksforGeeks' node template (no hd field - see Pair)
    public static class Node {
        int data;
        Node left, right;
        public Node(int key) {
            data = key;
            left = right = null;
        }
    }
}

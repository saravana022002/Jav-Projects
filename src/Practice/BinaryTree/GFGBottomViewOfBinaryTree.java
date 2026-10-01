package Practice.BinaryTree;

import java.util.*;

// ============================================================================
// GFG - Bottom View of Binary Tree   [Medium]
// https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
// Topics: Binary Tree, Breadth-First Search (level order), TreeMap, Horizontal Distance
// ============================================================================
//
// PROBLEM
//   Picture the tree seen from below: in every vertical column you see only its lowest
//   node. A column is the set of nodes with the same horizontal distance (hd) from the
//   root: the root has hd 0, a left child has its parent's hd - 1, a right child has
//   its parent's hd + 1. Return the values of the visible nodes from the leftmost column
//   to the rightmost one. If several nodes are equally low in one column, the one that
//   comes LATER in level order (the right one on that level) is the visible one.
//
// EXAMPLES
//   root = [1,2,3,4,5,N,6]                         ->  [4,2,5,3,6]
//       columns -2..2 show 4, 2, 5 (5 is lower than the root 1, which shares its column),
//       3 and 6
//   root = [20,8,22,5,3,4,25,N,N,10,14,N,N,28,N]  ->  [5,10,4,28,25]
//       ties go to the later node in level order: in column 0, the nodes 3 and 4 are on
//       the same level and 4 comes later; in column 1, 14 and 28 are on the same level and
//       28 comes later
//
// INTUITION
//   Give every node its horizontal distance hd (left = parent's hd - 1, right = parent's
//   hd + 1); nodes with equal hd are in the same column. Visit the nodes level by level,
//   top to bottom and left to right: the LAST node met in a column is then the lowest
//   one, and on a tie the one furthest right - exactly the node the problem wants. So keep
//   a map hd -> value and let every visit overwrite the entry of its column. A TreeMap
//   keeps its keys sorted, so its values already come out from the leftmost column to the
//   rightmost one.
//
// APPROACH (as implemented below)
//   1. horVsNode is a TreeMap from hd to a node value, queue is the BFS queue. Set
//      root.hd = 0, put the root into the map and offer it to the queue.
//   2. While the queue is not empty, take it one level at a time (size = nodes in the level):
//        poll a node and horVsNode.put(node.hd, node.data) - this overwrites whatever a
//        higher node stored for the same column;
//        a left child gets hd = node.hd - 1 and is offered to the queue;
//        a right child gets hd = node.hd + 1 and is offered to the queue.
//   3. Return new ArrayList<>(horVsNode.values()) - the TreeMap iterates in ascending hd,
//      so the leftmost column comes first.
//
// DRY RUN   root = [2,3,4,N,6]   (the tree main() builds)
//                2            hd  0
//               / \
//              3   4          hd -1 and +1
//               \
//                6            hd  0   (right child of 3: -1 + 1)
//   start    root.hd = 0, map {0=2}, queue [2]
//   poll 2   put(0,2);   3.hd = -1, 4.hd = 1, queue [3,4]
//   poll 3   put(-1,3);  6.hd = 0, queue [4,6]
//   poll 4   put(1,4)
//   poll 6   put(0,6)    overwrites the 2: column 0 now shows 6, which is lower
//   map {-1=3, 0=6, 1=4}  ->  values in key order: [3, 6, 4]
//
// COMPLEXITY
//   Time : O(n log n) - every node does one TreeMap.put, which costs O(log c) for c distinct
//          columns (c <= n); the BFS itself is O(n)
//   Space: O(n) - the queue holds up to one whole level, the map one entry per column
//
// GOTCHAS
//   - put() overwrites, so the LAST node of a column survives - the opposite of the top
//     view (GFGTopViewOfBinaryTree.java in this folder), which keeps the FIRST one.
//   - The level-order traversal is what makes "last write wins" mean "lowest wins, and on a
//     tie the one further right". A plain DFS would have to store the depth as well.
//   - Node.hd starts as Integer.MAX_VALUE only as a placeholder; it is always set before
//     the node is used (root.hd = 0 here, a child's hd when it is queued).
//   - root == null throws a NullPointerException at root.hd = 0 - there is no empty check.
//   - The inner loop over "size" is not needed for correctness: the nodes are polled in
//     the same order without it.
//
// RELATED
//   - GFGTopViewOfBinaryTree.java - the same horizontal-distance idea, but it keeps the
//     first node of each column instead of the last.
//   - LC 987 Vertical Order Traversal of a Binary Tree - the same columns, but it lists
//     every node of each column, not just one.
//   - LC 199 Binary Tree Right Side View - another "what is visible from one side"
//     problem, also solved with a level-order traversal.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGBottomViewOfBinaryTree {
    public static void main(String[] args) {
        // tree [2,3,4,N,6]: the 6 hides the root in column 0. Expected output: [3, 6, 4]
        Node root = new Node(2);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.right = new Node(6);
        System.out.println(bottomView(root));
    }

    // Returns the values visible from below, leftmost column first.
    public static ArrayList<Integer> bottomView(Node root) {
        // horizontal distance -> value of the lowest node seen so far in that column
        // (TreeMap = keys in ascending order = columns left to right)
        TreeMap<Integer, Integer> horVsNode = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        // the root is column 0
        root.hd = 0;
        horVsNode.put(root.hd, root.data);
        queue.add(root);
        // level-order traversal: upper levels first, and left to right within a level
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                // overwrite: a later visit is lower (or equally low and further right)
                horVsNode.put(node.hd, node.data);
                // going left shifts the column by -1, going right by +1
                if(node.left != null){
                    node.left.hd = node.hd - 1;
                    queue.offer(node.left);
                }
                if(node.right != null){
                    node.right.hd = node.hd + 1;
                    queue.offer(node.right);
                }
            }
        }
        // values in ascending order of hd = left to right
        return new ArrayList<>(horVsNode.values());
    }
    // GeeksforGeeks' node template; hd (horizontal distance) is filled in during the traversal
    public static class Node {
        int data;
        int hd;
        Node left, right;
        public Node(int key)
        {
            data = key;
            hd = Integer.MAX_VALUE;
            left = right = null;
        }
    }
}

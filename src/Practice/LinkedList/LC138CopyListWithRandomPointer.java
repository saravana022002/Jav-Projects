package Practice.LinkedList;

// ============================================================================
// LC 138. Copy List with Random Pointer   [Medium]
// https://leetcode.com/problems/copy-list-with-random-pointer/
// Topics: Hash Table, Linked List
// ============================================================================
//
// PROBLEM
//   A singly linked list has n nodes. Besides next, every node carries a random pointer
//   that points at any node of the same list, or at null. Build a deep copy: exactly n
//   brand-new nodes with the same values, where the new next and random pointers lead only
//   to new nodes and have the same shape as the originals. Nothing in the copy may point
//   into the original list. Return the head of the copy. You are given only the head.
//
// EXAMPLES
//   LeetCode writes a list as [val, random_index] pairs: random_index is the position of the
//   random target, or null.
//   head = [[7,null],[13,0],[11,4],[10,2],[1,0]]  ->  same list, built from new nodes
//       13 points at the node holding 7, 11 at the node holding 1, 10 at the node holding
//       11, and 1 at the node holding 7
//   head = [[1,1],[2,1]]                          ->  [[1,1],[2,1]]
//       both random pointers end at the second node
//   head = [[3,null],[3,0],[3,null]]              ->  [[3,null],[3,0],[3,null]]
//       three nodes with the same value; only the middle one has a random pointer
//
// CONSTRAINTS
//   0 <= n <= 1000
//   -10^4 <= Node.val <= 10^4
//   Node.random is null or is pointing to some node in the linked list
//
// INTUITION
//   Copying the next chain is easy. The random pointers are the hard part: when a copy is
//   being wired up, the node it must point to may not have been copied yet, or may have
//   been copied long ago. So you need a quick way to get from ANY original node to its
//   copy. The official hints give two ways:
//   1. A map old node -> new node (HashMap). Pass 1 creates a copy of every node and stores
//      it in the map. Pass 2 sets copy.next = map.get(old.next) and
//      copy.random = map.get(old.random) for every node. O(n) extra space.
//   2. Interweave: put each copy right behind its original (A, A', B, B', ...). Then the
//      copy of X.random is simply X.random.next, so the random pointers are set in one
//      walk, and a last walk pulls the two lists apart again. O(1) extra space.
//
// APPROACH (as implemented below)
//   Nothing is implemented yet - this file is a stub.
//   1. main() builds the five nodes of example 1 (values 7, 13, 11, 10, 1), links
//      next node0 -> node1 -> node2 -> node3 -> node4, sets random
//      node1 -> node0, node2 -> node4, node3 -> node2, node4 -> node0 (node0.random stays
//      null) and keeps node0 as head.
//   2. copyRandomList(head) simply returns head: the ORIGINAL list, nothing is copied.
//
// DRY RUN   the list main() builds (LC example 1)
//   node    val   next    random
//   node0     7   node1   null
//   node1    13   node2   node0
//   node2    11   node3   node4
//   node3    10   node4   node2
//   node4     1   null    node0
//   A correct copy has 5 NEW nodes; for example the copy of node1 (13) must have its random
//   pointing at the copy of node0, never at node0. The stub hands back node0 itself.
//
// STATUS
//   Unfinished: copyRandomList returns head, so it gives back the ORIGINAL list instead of
//   a deep copy, and LeetCode would reject it. Neither approach above is written yet.
//   main() only builds LC example 1: it never calls copyRandomList and prints nothing.
//   copyRandomList is an instance method (as on LeetCode) while main() is static.
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   As written: O(1) time and O(1) space - it returns at once.
//   Once finished: map version O(n) time, O(n) space; interweaving O(n) time, O(1) extra.
//
// GOTCHAS
//   - A value cannot identify a node (example 3 has three 3s), so map nodes themselves.
//   - random may be null, point backwards, forwards or at the node itself; a null random
//     must stay null in the copy.
//   - Copying only the next chain (or returning head) leaves pointers into the original
//     list, which is exactly what the problem forbids.
//
// RELATED
//   - LC 133 Clone Graph - the same old node -> new node map on a graph
//     (Practice/Graph/LC133CloneGraph.java in this repo).
//   - LC 1485 Clone Binary Tree With Random Pointer - the same task on a binary tree
//     (premium).
// ============================================================================
public class LC138CopyListWithRandomPointer {
    public static void main(String[] args) {

        // Builds LC example 1: [[7,null],[13,0],[11,4],[10,2],[1,0]] (see DRY RUN above).
        Node node0 = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        // Linking next pointers
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // Linking random pointers
        node1.random = node0;
        node2.random = node4;
        node3.random = node2;
        node4.random = node0;

        // head of the list
        Node head = node0;
        // The list is only built: copyRandomList is never called and nothing is printed.

    }

    // STUB: returns the original head, not a deep copy (see STATUS).
    public Node copyRandomList(Node head) {
        return head;
    }

    // List node: val, the usual next, and random = any node of the list or null.
    public static class  Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

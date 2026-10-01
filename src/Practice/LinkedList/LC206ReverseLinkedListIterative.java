package Practice.LinkedList;

// ============================================================================
// LC 206. Reverse Linked List   [Easy]
// https://leetcode.com/problems/reverse-linked-list/
// Topics: Linked List, Recursion
// ============================================================================
//
// PROBLEM
//   Given the head of a singly linked list, reverse it and return the head of the reversed
//   list. The old last node becomes the head and the old head becomes the last node.
//   An empty list stays empty.
//
// EXAMPLES
//   head = [1,2,3,4,5]  ->  [5,4,3,2,1]
//   head = [1,2]        ->  [2,1]
//   head = []           ->  []
//
// CONSTRAINTS
//   The number of nodes in the list is in the range [0, 5000]
//   -5000 <= Node.val <= 5000
//   Follow-up: reverse it iteratively or recursively - can you do both?
//
// INTUITION
//   Reversing a list just means turning every next arrow around, so that each node points
//   at the node that used to be BEFORE it. Walk the list once and flip one arrow per node.
//   Three pointers are enough:
//     prev     - the first node of the part that is already reversed
//     curr     - the node whose arrow is being flipped right now
//     nextNode - the rest of the original list, saved before curr.next is overwritten
//                (without that copy the rest of the list would be lost)
//
// APPROACH (as implemented below)
//   1. prev = null (nothing reversed yet), curr = node (the old head).
//   2. While curr != null:
//        nextNode = curr.next     remember the rest of the list
//        curr.next = prev         flip the arrow so curr points backwards
//        prev = curr              curr is now the first node of the reversed part
//        curr = nextNode          move on to the rest
//   3. curr is null, so prev is the last node visited, the old tail: the new head.
//      Return prev.
//
// DRY RUN   head = 1 -> 2 -> 3
//   start    prev = null,  curr = 1
//   round 1  nextNode = 2;  1.next = null;  prev = 1;  curr = 2     reversed: 1
//   round 2  nextNode = 3;  2.next = 1;     prev = 2;  curr = 3     reversed: 2 -> 1
//   round 3  nextNode = null;  3.next = 2;  prev = 3;  curr = null  reversed: 3 -> 2 -> 1
//   curr is null: return prev = 3, the list 3 -> 2 -> 1
//
// STATUS
//   main() builds one default node (value 0) and reverses it, which returns that same node.
//   The result is discarded, so nothing is printed.
//
// COMPLEXITY
//   Time : O(n) - each node is visited once
//   Space: O(1) - three pointers; the nodes are re-linked in place, none are created
//
// GOTCHAS
//   - Save curr.next BEFORE overwriting it, otherwise the rest of the list is lost.
//   - The first arrow flipped points to null (prev starts as null), so the old head ends
//     up as a proper tail and the list has no cycle.
//   - An empty list returns null and a one-node list returns itself, with no special case.
//
// SEE ALSO
//   LC206ReverseLinkedListRecursive.java - the recursive version, plus this iterative one
//   again and a stack-based brute force
//
// RELATED
//   - LC 92 Reverse Linked List II - reverse only a part of the list; the same loop, run
//     on a sub-range.
//   - LC 234 Palindrome Linked List - reverses the second half and compares it with the
//     first (LC234PalindromeLinkedList.java in this folder).
//   - LC 25 Reverse Nodes in k-Group - reverses the list in blocks of k
//     (LC25ReverseNodesInKGroup.java in this folder).
// ============================================================================
public class LC206ReverseLinkedListIterative {
    public static void main(String[] args) {
        // a single node: reversing it gives the same node back (the result is ignored)
        ListNode node = new ListNode();
        reverseList(node);
    }

    // Singly linked list node.
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // Reverses the list in place by flipping every next pointer; returns the new head.
    public static ListNode reverseList(ListNode node) {
        // prev = head of the reversed part, curr = node being flipped
        ListNode prev = null;
        ListNode curr = node;
        ListNode nextNode = null;
        while (curr != null){
            // keep the rest of the list before its link is overwritten
            nextNode = curr.next;
            // flip the arrow: curr now points at the previous node
            curr.next = prev;
            // the reversed part grows by one node, then move on
            prev = curr;
            curr = nextNode;
        }
        // curr ran past the old tail, so prev is the old tail = the new head
        return prev;
    }
}

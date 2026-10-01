package Practice.LinkedList;

// ============================================================================
// LC 237. Delete Node in a Linked List   [Medium]
// https://leetcode.com/problems/delete-node-in-a-linked-list/
// Topics: Linked List
// ============================================================================
//
// PROBLEM
//   You are handed ONE node of a singly linked list - not the head - and have to delete
//   that node from its list. You cannot reach the node before it, so the usual unlinking
//   is impossible. The node is never the tail, and all values in the list are unique.
//   "Deleted" means: afterwards the node's value is no longer in the list, the list has one
//   node fewer, and all the other values are still in their old order.
//
// EXAMPLES
//   head = [4,5,1,9], node = 5  ->  [4,1,9]
//   head = [4,5,1,9], node = 1  ->  [4,5,9]
//
// CONSTRAINTS
//   The number of nodes in the list is in the range [2, 1000]
//   -1000 <= Node.val <= 1000
//   The value of each node in the list is unique
//   The node to be deleted is in the list and is not a tail node
//
// INTUITION
//   Deleting a node normally means making its PREDECESSOR skip over it, but the predecessor
//   is out of reach. So do not remove the given node - remove the one AFTER it. Copy the
//   next node's value into the given node, then make the given node skip over that next
//   node. The list now shows exactly the values it should, one node shorter; the node that
//   physically leaves the chain is the successor, not the one you were given.
//   That is also why the node may not be the tail: a tail has no next node to copy from.
//
// APPROACH (as implemented below)
//   1. If node.next != null:
//        node.val = node.next.val        take over the successor's value
//        node.next = node.next.next      skip (unlink) the successor
//   2. Otherwise (a tail node, which the problem rules out): node = null only changes the
//      local variable, so the list stays as it is.
//   3. Return node.
//
// DRY RUN   main()'s list 4 -> 3 -> 5 -> 1, given node = the node holding 5  (shown as [5])
//   start                            4 -> 3 -> [5] -> 1
//   node.val = node.next.val (1)     4 -> 3 -> [1] -> 1
//   node.next = node.next.next       4 -> 3 -> [1] -> null     (the old last node is skipped)
//   the list reads 4, 3, 1: the value 5 is gone
//
// STATUS
//   LeetCode's method is void; here deleteNode returns a ListNode (the given node, which
//   now holds its successor's value, or null in the tail case). The return value is not
//   needed.
//   main() builds 4 -> 3 -> 5 -> 1, deletes the node holding 5 and ignores the result, so
//   nothing is printed (the list becomes 4 -> 3 -> 1).
//   LC160IntersectionOfTwoLinkedLists.java has an unused import of this class, so fix that
//   import if this class is ever renamed or moved.
//
// COMPLEXITY
//   Time : O(1) - a couple of assignments
//   Space: O(1)
//
// GOTCHAS
//   - A tail node cannot be deleted this way (no next to copy from). LeetCode never passes
//     one; if it happened, this code would leave the list untouched and return null.
//   - node = null in the else branch does nothing for the caller: Java passes the
//     reference by value.
//   - The given node object stays in the list with a new value; the successor object is the
//     one that is unlinked, so any other reference to it no longer points into the list.
//
// RELATED
//   - LC 203 Remove Linked List Elements - deleting nodes the normal way, with the head
//     (and so the predecessors) available.
//   - LC 19 Remove Nth Node From End of List - also deletes a node, but by finding its
//     predecessor (LC19RemoveNthNodeFromEndOfListV1.java in this folder).
// ============================================================================
public class LC237DeleteNodeInALinkedList {
    public static void main(String[] args) {

        // the list 4 -> 3 -> 5 -> 1
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(1);

        // the node holding 5 is the one to delete; afterwards the list is 4 -> 3 -> 1
        // (the result is ignored and nothing is printed)
        ListNode deleteNode = l1.next.next;
        deleteNode(deleteNode);
    }

    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    // "Deletes" node by turning it into a copy of its successor and unlinking the successor.
    public static ListNode deleteNode(ListNode node) {
        if(node.next != null) {
            // copy the successor's value here, then skip the successor
            node.val = node.next.val;
            node.next = node.next.next;
        }else {
            // tail node: cannot happen per the problem; this only nulls the local variable
            node = null;
        }
        return node;
    }
}

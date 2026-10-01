package Practice.LinkedList;

// ============================================================================
// LC 876. Middle of the Linked List   [Easy]
// https://leetcode.com/problems/middle-of-the-linked-list/
// Topics: Linked List, Two Pointers
// ============================================================================
//
// PROBLEM
//   Given the head of a singly linked list, return its middle node (the rest of the list
//   after it comes along, since it is the same chain). With two middle nodes - an even
//   number of nodes - return the second one.
//
// EXAMPLES
//   head = [1,2,3,4,5]     ->  [3,4,5]     the middle node is the one holding 3
//   head = [1,2,3,4,5,6]   ->  [4,5,6]     the middles hold 3 and 4; the second one is returned
//
// CONSTRAINTS
//   The number of nodes in the list is in the range [1, 100]
//   1 <= Node.val <= 100
//
// INTUITION
//   Counting the nodes and walking half of them works, but it needs two passes. Two pointers
//   with different speeds do it in one: the tortoise moves 1 node per step and the hare
//   moves 2. When the hare has reached the end, the tortoise has covered half the distance,
//   so it stands on the middle. For an even length the hare ends up past the last node
//   (null) and the tortoise is on the second middle node - exactly the tie-break the
//   problem asks for.
//
// APPROACH (as implemented below)
//   1. tortoise and hare both start at head.
//   2. While hare != null:
//        - hare.next == null: the hare is on the LAST node (odd length), so the tortoise
//          is on the exact middle: return it.
//        - otherwise tortoise = tortoise.next (1 node) and hare = hare.next.next (2 nodes).
//   3. The hare fell off the end (even length): return tortoise, the second middle node.
//
// DRY RUN   head = [1,2,3,4,5]  (what main() runs)
//   start    tortoise = 1, hare = 1
//   step 1   hare.next exists:  tortoise = 2, hare = 3
//   step 2   hare.next exists:  tortoise = 3, hare = 5
//   step 3   hare = 5 and hare.next == null: return tortoise = the node 3
//   Even length, head = [1,2,3,4,5,6]:
//   (t, h)   (1,1) -> (2,3) -> (3,5) -> hare.next is 6, so tortoise = 4 and hare = null
//            loop ends: return tortoise = the node 4, the second middle
//
// STATUS
//   main() builds 1 -> 2 -> 3 -> 4 -> 5 and calls middleNode, but throws the result away,
//   so nothing is printed (the call returns the node holding 3).
//
// COMPLEXITY
//   Time : O(n) - the hare walks the list once, the tortoise covers half of it
//   Space: O(1) - two pointers
//
// GOTCHAS
//   - Look at hare.next before hare.next.next: the hare.next == null test (odd length) and
//     the hare != null condition (even length) are what prevent a NullPointerException.
//   - For an even length the answer is the SECOND middle, which is what this loop returns
//     without extra code. Stopping one step earlier would give the first one.
//   - The result is a node of the original list, not a copy: the chain from it to the
//     end is the answer.
//
// RELATED
//   - LC 234 Palindrome Linked List - uses this same fast/slow walk to find the middle
//     (LC234PalindromeLinkedList.java in this folder).
//   - LC 2095 Delete the Middle Node of a Linked List - the middle has to be found, and the
//     node before it is needed for the deletion.
//   - LC 2130 Maximum Twin Sum of a Linked List - again needs the middle of a list.
// ============================================================================
public class LC876MiddleOfTheLinkedList {
    public static void main(String[] args) {
        // the list 1 -> 2 -> 3 -> 4 -> 5; the middle is the node holding 3
        // (the result is ignored, nothing is printed)
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        middleNode(node1);
    }
    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    // Returns the middle node (the second one when there are two).
    public static ListNode middleNode(ListNode head) {
        // the hare moves twice as fast as the tortoise
        ListNode hare = head;
        ListNode tortoise = head;
        while(hare != null){
            if(hare.next == null){
                // hare is on the last node: odd length, tortoise is on the middle
                return tortoise;
            } else {
                tortoise = tortoise.next;
                hare = hare.next.next;
            }
        }
        // hare ran past the end: even length, tortoise is on the second middle
        return tortoise;
    }
}

package Practice.LinkedList;

// ============================================================================
// LC 19. Remove Nth Node From End of List   [Medium]
// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// Topics: Linked List, Two Pointers
// ============================================================================
//
// PROBLEM
//   Given the head of a singly linked list and a number n, delete the n-th node counting
//   from the END of the list (n = 1 is the last node) and return the head of the list
//   that is left.
//
// EXAMPLES
//   head = [1,2,3,4,5], n = 2  ->  [1,2,3,5]    the 2nd node from the end is 4
//   head = [1],          n = 1  ->  []           the only node is also the last one
//   head = [1,2],        n = 1  ->  [1]
//
// CONSTRAINTS
//   The number of nodes sz satisfies 1 <= sz <= 30
//   0 <= Node.val <= 100
//   1 <= n <= sz
//   Follow-up: can you do it in one pass?   (this solution does)
//
// INTUITION
//   The n-th node from the end sits at position sz - n + 1 from the front, but sz is not
//   known without a counting pass. Two pointers give it for free: keep them a fixed
//   number of nodes apart and move them together. When the front pointer (hare) has run
//   off the end, the back pointer (tortoise) is exactly that fixed distance from the end.
//   Choose the gap n + 1 and the tortoise stops on the node just BEFORE the one to delete,
//   which is what unlinking a node in a singly linked list needs.
//   A dummy node in front of head gives even the real head a predecessor, so deleting
//   the head needs no special case.
//
// APPROACH (as implemented below, removeNthFromEnd)
//   1. dummy = new ListNode(0) with dummy.next = head. hare and tortoise both start there.
//   2. Move hare forward n + 1 times (while cnt <= n), so it is n + 1 nodes ahead.
//   3. Move hare and tortoise together, one node per step, until hare is null. The
//      tortoise is now the node just before the n-th node from the end.
//   4. tortoise.next = tortoise.next.next unlinks that node.
//   5. Return dummy.next. It is the old head unless the head itself was removed.
//
// DRY RUN   what main() runs: head = a single node holding 5, n = 1
//   start    dummy -> 5 -> null               hare = tortoise = dummy
//   step 2   hare moves 2 times: dummy -> 5 -> null     hare = null, tortoise = dummy
//   step 3   hare is already null, so nobody moves
//   step 4   tortoise is dummy: dummy.next = 5.next = null
//   return dummy.next = null, the empty list (LC example 2)
//   A longer one, head = [1,2,3,4,5], n = 2:
//   step 2   hare moves 3 times: dummy -> 1 -> 2 -> 3       hare = 3, tortoise = dummy
//   step 3   hare = 4, tortoise = 1;  hare = 5, tortoise = 2;  hare = null, tortoise = 3
//   step 4   tortoise is node 3: 3.next = 4.next = 5         result 1 -> 2 -> 3 -> 5
//
// STATUS
//   The solution is complete: the two-pointer removeNthFromEnd is correct.
//   Leftover: bruteRemoveNthFromEnd is an empty placeholder - it returns null and nothing
//   calls it. Its name suggests a brute-force version (count the nodes, then walk
//   sz - n steps to the node before the target), which was never written.
//   main() builds a one-node list, calls removeNthFromEnd(node5, 1) and throws the result
//   away, so nothing is printed (the call returns null). The lines that would build
//   [1,2,3,4,5] are commented out.
//
// COMPLEXITY
//   Time : O(sz) - one pass: hare makes n + 1 moves in step 2 and sz - n moves in step 3
//   Space: O(1) - the dummy node and two pointers
//
// GOTCHAS
//   - Advance hare n + 1 times, not n: the tortoise has to stop BEFORE the target, because
//     a singly linked node cannot unlink itself.
//   - n = sz (delete the head) works without a special case: hare ends on null after
//     step 2, the tortoise stays on dummy, and dummy.next becomes head.next.
//   - It relies on 1 <= n <= sz. With n > sz the hare walks off the list in step 2 and
//     hare.next throws a NullPointerException.
//
// SEE ALSO
//   LC19RemoveNthNodeFromEndOfListV1.java - the same two-pointer solution as an instance
//   method (LeetCode's form), without a main()
//
// RELATED
//   - LC 2095 Delete the Middle Node of a Linked List - also deletes a node found with
//     two pointers, so it needs the node before it as well.
//   - LC 1721 Swapping Nodes in a Linked List - also finds the k-th node from the end
//     with a fixed gap between two pointers.
//   - LC 876 Middle of the Linked List - the other classic two-pointer walk (in this folder).
// ============================================================================
public class LC19RemoveNthNodeFromEndOfListV2 {
    public static void main(String[] args) {
        // LC example 2: a single node and n = 1. The result is discarded and nothing is
        // printed; the call returns null, the empty list.
        ListNode node5 = new ListNode(5);
//        ListNode node4 = new ListNode(4, node5);
//        ListNode node3 = new ListNode(3, node4);
//        ListNode node2 = new ListNode(2, node3);
//        ListNode node1 = new ListNode(1, node2);
        removeNthFromEnd(node5, 1);
    }

    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // Two pointers with a gap of n + 1 nodes, one pass. Removes the n-th node from the end
    // and returns the (possibly new) head.
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // the dummy gives the real head a predecessor, like every other node
        ListNode dummy = new ListNode(0); // Dummy node before head
        dummy.next = head;

        ListNode hare = dummy;
        ListNode tortoise = dummy;
        int cnt = 0;
        // give the hare a head start of n + 1 nodes
        while(cnt <= n){
            hare = hare.next;
            cnt++;
        }

        // move both until the hare is past the last node; the tortoise then stands on the
        // node just before the one to delete
        while (hare != null){
            hare = hare.next;
            tortoise = tortoise.next;
        }
        // unlink the target
        tortoise.next = tortoise.next.next;

        return dummy.next;
    }

    // Placeholder for a brute-force version: not written yet, returns null, never called.
    public static ListNode bruteRemoveNthFromEnd(ListNode head, int n) {
      return null;
    }


}

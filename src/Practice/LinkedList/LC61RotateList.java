package Practice.LinkedList;

// ============================================================================
// LC 61. Rotate List   [Medium]
// https://leetcode.com/problems/rotate-list/
// Topics: Linked List, Two Pointers
// ============================================================================
//
// PROBLEM
//   Given the head of a linked list, rotate the list to the right by k places and return
//   the new head. One place means the last node jumps to the front. The list may be
//   empty, and k can be far larger than the length of the list.
//
// EXAMPLES
//   head = [1,2,3,4,5], k = 2  ->  [4,5,1,2,3]    the last two nodes move to the front
//   head = [0,1,2],     k = 4  ->  [2,0,1]         4 places = 1 place, since 3 places
//                                                  bring a 3-node list back to itself
//
// CONSTRAINTS
//   The number of nodes in the list is in the range [0, 500]
//   -100 <= Node.val <= 100
//   0 <= k <= 2 * 10^9
//
// INTUITION
//   Rotating right by k takes the last k nodes and moves them, as one block in the same
//   order, in front of the rest. So find the node that becomes the new tail - it is number
//   n - k counted from the head - make the node after it the new head, and join the old
//   tail to the old head. Closing the list into a ring does the joining: link the tail to
//   the head, walk to the new tail, and cut the ring right after it.
//   Rotating by n places gives the same list back, so only k mod n matters; that also
//   takes care of k larger than n.
//
// APPROACH (as implemented below)
//   1. head == null, a single node or k == 0: return head, nothing to rotate.
//   2. Walk fast to the tail, counting the nodes: cnt = length.
//   3. fast.next = head closes the list into a ring.
//   4. k = k % cnt. If k == 0 return head (but see STATUS: the ring is still closed).
//   5. stepsToBeMoved = cnt - k. Start pointer at head and move it stepsToBeMoved - 1
//      times, so it stands on the new tail.
//   6. newHead = pointer.next; pointer.next = null cuts the ring. Return newHead.
//
// DRY RUN   head = [0,1,2], k = 4  (what main() runs)
//   cnt = 3, fast stops on the node 2; fast.next = head    ring: 0 -> 1 -> 2 -> 0
//   k = 4 % 3 = 1;  stepsToBeMoved = 3 - 1 = 2
//   pointer starts on 0 and moves once (i = 1)             pointer = the node 1, the new tail
//   newHead = pointer.next = the node 2;  pointer.next = null
//   result 2 -> 0 -> 1, that is [2,0,1]
//
// STATUS
//   Known issue: when k is a non-zero multiple of the list length and the list has at least
//   two nodes (for example head = [1,2,3] with k = 3 or k = 6), rotateRight returns a
//   CIRCULAR list. The ring (fast.next = head) is closed before the "if (k == 0) return
//   head" check and is never cut on that path. Expected [1,2,3]; any loop over the returned
//   list never ends. The code is left unchanged on purpose.
//   main() uses k = 4 on a 3-node list, which does not trigger it. It prints the returned
//   node object (ListNode has no toString), so the output looks like
//   Practice.LinkedList.LC61RotateList$ListNode@<hash>, not [2,0,1].
//
// COMPLEXITY
//   Time : O(n) - one walk to the tail and at most one more walk to the new tail
//   Space: O(1) - a few pointers; the list is re-linked in place
//
// GOTCHAS
//   - k can be as large as 2 * 10^9, which still fits in an int; reduce it with k % cnt
//     instead of rotating step by step.
//   - The ring must be cut on every path before returning, see STATUS.
//   - pointer stops on the (cnt - k)-th node, hence the loop from i = 1 that moves only
//     stepsToBeMoved - 1 times.
//
// RELATED
//   - LC 189 Rotate Array - the same rotation on an array.
//   - LC 725 Split Linked List in Parts - also cuts a list into pieces, which needs the node
//     in front of each cut point.
// ============================================================================
public class LC61RotateList {
    public static void main(String[] args) {
        // the list 0 -> 1 -> 2 rotated right by 4 (= 1 place)
        ListNode node2 = new ListNode(2);
        ListNode node1 = new ListNode(1, node2);
        ListNode head = new ListNode(0, node1);
        int k = 4;
        // prints the node object (no toString), not the values; the list is 2 -> 0 -> 1
        System.out.println(rotateRight(head, k));
    }
    // Rotates the list right by k places and returns the new head.
    public static ListNode rotateRight(ListNode head, int k) {
        // nothing to rotate: empty list, one node, or k == 0
        if(head == null || head.next == null || k == 0){
            return head;
        }

        // walk to the last node and count the nodes
        ListNode fast = head;
        int cnt = 1;
        while (fast.next != null){
            fast = fast.next;
            cnt ++;
        }
        // last node should point to head
        fast.next = head;

        // rotating by the length is a full turn: only k mod cnt matters
        // (known issue: this path returns while the ring is still closed)
        k = k % cnt;
        if (k == 0) return head;

        // the new tail is node number cnt - k counted from the head
        int stepsToBeMoved = cnt - k;

        ListNode pointer = head;
        for (int i = 1; i < stepsToBeMoved; i++) {
            pointer = pointer.next;
        }

        // the node after the new tail becomes the head; cut the ring after the new tail
        ListNode newHead = pointer.next;
        pointer.next = null;
        return newHead;
    }
    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}

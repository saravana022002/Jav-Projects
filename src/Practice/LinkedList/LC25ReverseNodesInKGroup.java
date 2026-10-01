package Practice.LinkedList;


// ============================================================================
// LC 25. Reverse Nodes in k-Group   [Hard]
// https://leetcode.com/problems/reverse-nodes-in-k-group/
// Topics: Linked List, Recursion
// ============================================================================
//
// PROBLEM
//   Given the head of a linked list and a number k, cut the list into consecutive blocks
//   of k nodes and reverse each block. If the nodes at the very end are fewer than k, leave
//   them exactly as they are. Only the links between nodes may be changed, never the
//   values stored in them. Return the head of the resulting list.
//
// EXAMPLES
//   head = [1,2,3,4,5], k = 2  ->  [2,1,4,3,5]    blocks [1,2] and [3,4] flip; 5 is left over
//   head = [1,2,3,4,5], k = 3  ->  [3,2,1,4,5]    block [1,2,3] flips; [4,5] is too short
//
// CONSTRAINTS
//   The list has n nodes, 1 <= k <= n <= 5000
//   0 <= Node.val <= 1000
//   Follow-up: can you do it with O(1) extra memory?   (this solution does)
//
// INTUITION
//   Handle one full block at a time. Reversing a block is the plain list reversal of LC 206
//   on just k nodes, but the result has to be stitched back in: the node in front of the
//   block must point to the block's NEW first node (its old last node), and the block's NEW
//   last node (its old first node) must point to whatever followed the block.
//   Two details make the stitching small:
//     - start the reversal with prev = the node AFTER the block (not null), so the old
//       first node ends up pointing at the rest of the list without extra work;
//     - keep a pointer groupPrev to the node just before the current block (a dummy head
//       before the first block), and move it to the block's new last node afterwards.
//   A running counter cnt tells where a block ends: whenever cnt is a multiple of k, the
//   scanning pointer tail sits on the k-th node of the current block. A leftover block of
//   fewer than k nodes never reaches a multiple of k, so it stays untouched.
//
// APPROACH (as implemented below, reverseKGroup)
//   1. head == null or k == 1: return head (nothing to reverse).
//   2. dummy -> head. groupPrev = dummy (node before the current block), tail = head
//      (the scanner), cnt = 0.
//   3. While tail != null: cnt++.
//        cnt % k == 0, so tail is the last node of a full block:
//          a. groupNext = tail.next (first node after the block), prev = groupNext,
//             curr = groupPrev.next (first node of the block).
//          b. while curr != groupNext: temp = curr.next; curr.next = prev; prev = curr;
//             curr = temp. The block is now reversed and its old first node points to
//             groupNext.
//          c. temp = groupPrev.next (the old first node, now the block's last);
//             groupPrev.next = tail (the old last node is now the first);
//             groupPrev = temp (the node before the next block);
//             tail = groupPrev.next (= groupNext: resume scanning after the block).
//        otherwise: tail = tail.next.
//   4. Return dummy.next.
//
// DRY RUN   head = [1,2,3,4,5], k = 2  (what main() runs)
//   tail scans   cnt   action                                               list afterwards
//   1            1     not a multiple of 2                                  1 2 3 4 5
//   2            2     block [1,2]: reverse; dummy -> 2, 1 -> 3,            2 1 3 4 5
//                      groupPrev = 1, tail resumes at 3
//   3            3     not a multiple                                       2 1 3 4 5
//   4            4     block [3,4]: reverse; 1 -> 4, 3 -> 5,                2 1 4 3 5
//                      groupPrev = 3, tail resumes at 5
//   5            5     not a multiple; tail becomes null, the loop ends     2 1 4 3 5
//   return dummy.next = 2, the list [2,1,4,3,5]
//
// STATUS
//   The solution is complete: reverseKGroup is correct (checked against a reference for
//   every n up to 20 and every k up to n).
//   Leftover: the private helper getReverse is unfinished dead code - it reverses a
//   stretch of the list but ends with return null, and nothing calls it.
//   myReverseKGroup is the same algorithm without the first-line guard and gives
//   identical results: k == 1 just reverses blocks of one node, and a null head never
//   enters the loop.
//   main() runs reverseKGroup on [1,2,3,4,5] with k = 2 and discards the result, so nothing
//   is printed.
//
// COMPLEXITY
//   Time : O(n) - each node is scanned once by tail and re-linked once inside its block
//   Space: O(1) - a handful of pointers plus the dummy node
//
// GOTCHAS
//   - Start prev at groupNext, not null. With null, the block's new last node would end the
//     list and everything after the block would be lost.
//   - groupPrev has to move to the OLD first node of the block (the new last one), because
//     that is the node in front of the next block.
//   - After a block is reversed, tail is reset to the first node after it (groupNext).
//     cnt keeps counting, so cnt % k == 0 marks the end of every following block.
//   - A trailing block shorter than k is not reversed: the loop ends before cnt reaches the
//     next multiple of k.
//
// RELATED
//   - LC 206 Reverse Linked List - the reversal used inside each block
//     (LC206ReverseLinkedListIterative.java in this folder).
//   - LC 24 Swap Nodes in Pairs - the same problem with k = 2.
// ============================================================================
public class LC25ReverseNodesInKGroup {
    public static void main(String[] args) {
        // the list 1 -> 2 -> 3 -> 4 -> 5
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
        // k = 2 gives 2 -> 1 -> 4 -> 3 -> 5; the result is ignored, nothing is printed
        reverseKGroup(head, 2);
    }

    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    // Reverses every full block of k nodes in place and returns the new head.
    public static ListNode reverseKGroup(ListNode head, int k) {
        // empty list or k == 1: there is nothing to change
        if (head == null || k == 1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // groupPrev = the node just before the current block; tail = the scanner
        ListNode groupPrev = dummy;
        ListNode tail = head;
        int cnt = 0;
        while(tail != null){
            cnt++;
            if(cnt % k == 0) {
                // tail is the k-th node of the block groupPrev.next .. tail
                ListNode groupNext = tail.next;
                // prev starts at groupNext so the old first node will link to the rest
                ListNode prev = groupNext;
                ListNode curr = groupPrev.next;
                // reverse the k nodes of the block
                while (curr != groupNext){
                    ListNode temp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = temp;
                }
                // stitch: the node before the block now points at the old last node; the old
                // first node is the block's new last node, so it is the next groupPrev
                ListNode temp = groupPrev.next;
                groupPrev.next = tail;
                groupPrev = temp;
                // resume scanning right after the block
                tail = groupPrev.next;
            } else {
                tail = tail.next;
            }
        }
        return dummy.next;
    }


    // Same algorithm as reverseKGroup, just without the head == null / k == 1 guard.
    public static ListNode myReverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        ListNode tail = groupPrev.next;
        int cnt = 0;
        while(tail != null){
            cnt++;
            if(cnt % k == 0){
                ListNode groupNext = tail.next;
                ListNode prev = groupNext;
                ListNode curr = groupPrev.next;
                while (curr != groupNext){
                    ListNode temp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = temp;
                }


                ListNode temp = groupPrev.next;
                groupPrev.next = tail;

                groupPrev = temp;
                tail = groupPrev.next;

            }else {
                tail = tail.next;
            }
        }
        return dummy.next;
    }

    // Unfinished and unused: reverses front .. (the node before tail) but returns null
    // instead of the new head (prev).
    private static ListNode getReverse(ListNode front, ListNode tail) {
        ListNode temp = front;
        ListNode prev = null;
        ListNode fronter = null;
        while (temp != tail){
            fronter = temp.next;
            temp.next = prev;
            prev = temp;
            temp = fronter;
        }
        return null;
    }
}

package Practice.LinkedList;

// ============================================================================
// LC 141. Linked List Cycle   [Easy]
// https://leetcode.com/problems/linked-list-cycle/
// Topics: Hash Table, Linked List, Two Pointers, Floyd's Cycle Finding Algorithm
// ============================================================================
//
// PROBLEM
//   Given the head of a singly linked list, say whether the list has a cycle: a node that
//   you can reach again by following next pointers. Return true if it does, false if the
//   list just ends at null. LeetCode builds the cycle itself (pos = the index the tail
//   links back to); pos is not passed to your function.
//
// EXAMPLES
//   head = [3,2,0,-4], pos = 1   ->  true    the tail (-4) links back to the node holding 2
//   head = [1,2],      pos = 0   ->  true    the tail links back to the head
//   head = [1],        pos = -1  ->  false   one node that points nowhere
//
// CONSTRAINTS
//   The number of nodes is in the range [0, 10^4]
//   -10^5 <= Node.val <= 10^5
//   pos is -1 or a valid index in the linked list
//   Follow-up: can you do it with O(1) extra memory?   (this solution does)
//
// INTUITION
//   Picture two runners on the list. If the list ends, the faster runner reaches null first
//   and we can stop. If the list loops, both end up circling inside the loop and the faster
//   one keeps closing in on the slower one: the gap shrinks by exactly 1 node per round
//   (hare moves 2, tortoise moves 1), so it reaches 0 and they stand on the same node.
//   That can only happen in a cycle. No set of visited nodes is needed, hence O(1) memory.
//   This is Floyd's tortoise and hare.
//
// APPROACH (as implemented below)
//   1. head == null or head.next == null -> fewer than two nodes: return false. (A single
//      node that points at itself has head.next != null, so it is not caught here.)
//   2. tortosie = head.next (1 node in) and hare = head.next.next (2 nodes in).
//      "tortosie" is the code's spelling of tortoise. Starting them on different nodes lets
//      the loop test hare != tortosie before the first move.
//   3. While both are non-null and have not met:
//        - hare.next == null -> the hare is on the last node, the list ends: return false
//        - tortosie moves 1 node, hare moves 2 (hare = hare.next.next)
//   4. return hare == tortosie: true if they met, false if the hare ran off the end (null).
//
// DRY RUN   head = [3,2,0,-4] with the tail linked back to the node holding 2 (pos = 1)
//   Name the nodes A=3, B=2, C=0, D=-4, with D.next = B.
//   start    tortosie = B, hare = C              (head.next, head.next.next)
//   round 1  hare != tortosie, hare.next = D     tortosie = C, hare = C -> D -> B = B
//   round 2  hare != tortosie, hare.next = C     tortosie = D, hare = B -> C -> D = D
//   now hare == tortosie: the loop stops and the method returns true.
//   A list without a cycle, [1,2,3]: tortosie = 2, hare = 3, hare.next == null -> false.
//
// STATUS
//   main() builds a 28-node list that has no cycle, calls hasCycle and throws the result
//   away, so nothing is printed (hasCycle would return false for that list).
//
// COMPLEXITY
//   Time : O(n) - with no cycle the hare walks the list once; with a cycle the two meet
//          within about n rounds
//   Space: O(1) - just two pointers
//
// GOTCHAS
//   - Check hare.next before using hare.next.next: the "if(hare.next == null) return false"
//     line avoids a NullPointerException on lists of odd length.
//   - Compare nodes with == (identity), never their values: values repeat (main()'s list
//     has two -21s).
//   - After the loop, hare == tortosie is true only for a real meeting; when the list ends
//     the hare is null and the tortoise is not.
//
// RELATED
//   - LC 142 Linked List Cycle II - the same two pointers, but it must return the node
//     where the cycle begins (LC142LinkedListCycleII.java in this folder).
//   - LC 202 Happy Number - the digit-square sequence behaves like a linked list that may
//     loop, so the same tortoise and hare idea detects it.
// ============================================================================
public class LC141LinkedListCycle {
    public static void main(String[] args) {

        // Step 1: Create nodes
        // (28 nodes, values -21, 10, 17, ..., 5; the last next stays null, so no cycle)
        ListNode head = new ListNode(-21);
        head.next = new ListNode(10);
        head.next.next = new ListNode(17);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(26);
        head.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next = new ListNode(35);
        head.next.next.next.next.next.next.next.next = new ListNode(33);
        head.next.next.next.next.next.next.next.next.next = new ListNode(-7);
        head.next.next.next.next.next.next.next.next.next.next = new ListNode(-16);
        head.next.next.next.next.next.next.next.next.next.next.next = new ListNode(27);
        head.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-12);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(29);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-12);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(20);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(14);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(14);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(13);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-24);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(21);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(23);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-21);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(5);

        // the result is ignored and nothing is printed (it would be false for this list)
        hasCycle(head);
    }

    // Floyd's tortoise and hare: true when the fast pointer catches the slow one in a cycle.
    public static boolean hasCycle(ListNode head) {
        // empty list, or one node with no next: there is nothing to loop back to
        if (head == null || head.next == null) return false;
        // slow pointer starts 1 node in, fast pointer 2 nodes in
        ListNode tortosie = head.next;
        ListNode hare = head.next.next;
        // go on until they meet or the hare runs off the end
        while(tortosie != null && hare != null && hare != tortosie){
            // the hare is on the last node and cannot take 2 steps: the list ends
            if(hare.next == null) return false;
            tortosie = tortosie.next;
            hare = hare.next.next;
        }
        // met -> cycle; hare == null (fell off the end) -> no cycle
        return hare == tortosie;
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

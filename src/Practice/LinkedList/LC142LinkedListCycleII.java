package Practice.LinkedList;

// ============================================================================
// LC 142. Linked List Cycle II   [Medium]
// https://leetcode.com/problems/linked-list-cycle-ii/
// Topics: Hash Table, Linked List, Two Pointers, Floyd's Cycle Finding Algorithm
// ============================================================================
//
// PROBLEM
//   Given the head of a singly linked list, return the node where its cycle begins - the
//   first node of the loop you walk into from the head - or null if there is no cycle.
//   LeetCode builds the cycle itself (pos = index the tail links back to) and does not
//   pass pos to you. The list must not be modified.
//
// EXAMPLES
//   head = [3,2,0,-4], pos = 1   ->  the node holding 2   (the tail links back to index 1)
//   head = [1,2],      pos = 0   ->  the node holding 1   (the head itself starts the cycle)
//   head = [1],        pos = -1  ->  null                 (no cycle)
//
// CONSTRAINTS
//   The number of nodes is in the range [0, 10^4]
//   -10^5 <= Node.val <= 10^5
//   pos is -1 or a valid index in the linked list
//   Follow-up: can you do it with O(1) extra memory?   (this solution does)
//
// INTUITION
//   Phase 1 is LC 141: a slow pointer (1 node per round) and a fast one (2 nodes per round)
//   meet if and only if the list has a cycle. The meeting node is somewhere inside the loop,
//   usually not at its start, so a second phase is needed.
//   Let a = steps from the head to the cycle start and c = the cycle length. At the meeting
//   the slow pointer has walked s steps and the fast one 2s, so the extra s steps are whole
//   laps: s is a multiple of c. Walk a MORE steps from the meeting node and you are "a
//   steps past a whole number of laps", which is exactly the cycle start. A new pointer
//   from the head also needs a steps to reach the cycle start. So move both one node at a
//   time and they collide on the cycle start.
//
// APPROACH (as implemented below)
//   1. head == null or head.next == null -> return null. (A single node that points at
//      itself has head.next != null, so it is handled by the loop below.)
//   2. Phase 1: tortosie = head.next, hare = head.next.next ("tortosie" is the code's
//      spelling of tortoise). Counted from the head they start 1 and 2 steps in, so the
//      hare has always walked exactly twice as far as the tortoise, as in the textbook
//      version that starts both at the head.
//      While hare != null && hare.next != null: move tortosie 1 node and hare 2 nodes.
//      If hare == tortosie they have met inside a cycle -> phase 2.
//   3. Phase 2: hare = head (the variable is reused as a plain one-step walker), then while
//      tortosie != hare move both ONE node. They stop on the cycle start: return hare.
//   4. The phase 1 loop ended because the hare (or hare.next) became null: no cycle,
//      return null.
//
// DRY RUN   head = [3,2,0,-4] with the tail linked back to the node holding 2 (pos = 1)
//   Name the nodes A=3, B=2, C=0, D=-4 with D.next = B. Here a = 1 and c = 3.
//   phase 1   start     tortosie = B, hare = C
//             round 1   tortosie = C, hare = C -> D -> B = B      not equal yet
//             round 2   tortosie = D, hare = B -> C -> D = D      equal: met at D
//   phase 2   hare = A (head), tortosie = D
//             D != A    tortosie = D.next = B, hare = A.next = B
//             equal     return B, the node holding 2
//   Note the meeting node D (-4) is not the answer, which is why phase 2 exists.
//
// STATUS
//   The method is named hasCycle (copied from LC 141) although it returns the ListNode
//   where the cycle starts, or null. LeetCode's name for this problem is detectCycle.
//   main() builds the list 1 -> 2 with no cycle, calls hasCycle and throws the result
//   away, so nothing is printed (the call returns null).
//
// COMPLEXITY
//   Time : O(n) - phase 1 takes at most about n rounds, phase 2 at most a <= n steps
//   Space: O(1) - two pointers; nothing is marked or stored, so the list stays unmodified
//
// GOTCHAS
//   - Do not return at the first meeting: that node is inside the loop but is usually not
//     where it starts (example 1 meets at -4, the answer is 2).
//   - Phase 2 moves BOTH pointers one node at a time. Moving the hare two nodes would
//     break the argument above.
//   - The loop test hare != null && hare.next != null is what makes hare.next.next safe.
//
// RELATED
//   - LC 141 Linked List Cycle - phase 1 only: does a cycle exist (LC141LinkedListCycle.java
//     in this folder).
//   - LC 287 Find the Duplicate Number - treat nums[i] as "next" and the duplicate value is
//     the cycle start, so the same two phases apply.
// ============================================================================
public class LC142LinkedListCycleII {
    public static void main(String[] args) {

        // Step 1: Create nodes
        // the list 1 -> 2 with no cycle, so the method returns null (the result is ignored)
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        hasCycle(head);
    }

    // Floyd's algorithm: returns the first node of the cycle, or null when there is none.
    public static ListNode hasCycle(ListNode head) {
        // empty list, or one node with no next: no cycle possible
        if (head == null || head.next == null) return null;
        // phase 1: slow starts 1 node in, fast 2 nodes in
        ListNode tortosie = head.next;
        ListNode hare = head.next.next;
        // the fast pointer reaching the end means there is no cycle
        while(hare != null && hare.next != null){
            tortosie = tortosie.next;
            hare = hare.next.next;
            if(hare == tortosie){
                // phase 2: restart one walker at the head; both now move one node at a time
                hare = head;
                while (tortosie != hare){
                    tortosie = tortosie.next;
                    hare = hare.next;
                }
                // they meet on the first node of the cycle
                return hare;
            }
        }
        return null;
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

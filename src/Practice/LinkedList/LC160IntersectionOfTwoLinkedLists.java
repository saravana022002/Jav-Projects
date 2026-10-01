package Practice.LinkedList;

import Practice.LinkedList.LC237DeleteNodeInALinkedList;

// ============================================================================
// LC 160. Intersection of Two Linked Lists   [Easy]
// https://leetcode.com/problems/intersection-of-two-linked-lists/
// Topics: Hash Table, Linked List, Two Pointers
// ============================================================================
//
// PROBLEM
//   You get the heads of two singly linked lists, headA and headB. They may merge into
//   one shared tail at some node. Return that first shared node, or null when the lists
//   never meet. "Shared" means the very same node object, not just an equal value. There
//   are no cycles, and both lists must be left exactly as they were.
//
// EXAMPLES
//   listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]  ->  the node holding 8
//       A reaches it after 2 nodes, B after 3. The nodes holding 1 look alike but are
//       different nodes, so they do not count.
//   listA = [1,9,1,2,4], listB = [3,2,4]         ->  the node holding 2
//   listA = [2,6,4],     listB = [1,5]           ->  null (no intersection)
//
// CONSTRAINTS
//   1 <= m, n <= 3 * 10^4   (m = nodes in listA, n = nodes in listB)
//   1 <= Node.val <= 10^5
//   Follow-up: O(m + n) time and O(1) memory
//
// INTUITION
//   Once two lists share a node, they share everything after it. So if both lists had the
//   same length, walking them from their heads in step would land on the shared node at
//   the same moment. The only obstacle is that the lengths differ.
//   The pointer-switch trick cancels the difference without measuring it: when a pointer
//   falls off the end of its own list it restarts at the head of the OTHER list. Both
//   pointers then walk the same total distance (length of A + length of B), so the longer
//   list's head start disappears and they reach the first shared node on the same step.
//   With no shared node they both hit null on the same step; the loop ends because
//   null == null and the answer is null.
//
// APPROACHES IN THIS FILE
//   A. getIntersectionNode        - count both lists, advance the longer one by the length
//                                   difference, then step both together until they are the
//                                   same node - O(m + n) time, O(1) space
//   B. getIntersectionNodeOptimal - pointer switch (described above and below)
//                                   - O(m + n) time, O(1) space   <- main() runs this one
//
// APPROACH (as implemented below, method B)
//   1. If either head is null, return null.
//   2. a = headA, b = headB.
//   3. While a != b: a = (a == null) ? headB : a.next;  b = (b == null) ? headA : b.next.
//      A pointer steps onto null first and only jumps to the other head on the next move.
//   4. return a: the first shared node, or null if they never shared one.
//
// DRY RUN   what main() builds: A = 1 -> 2 -> 6 -> 7 -> 8,   B = 3 -> 4 -> 5 -> 6 -> 7 -> 8
//   (the nodes 6, 7, 8 are shared; the numbers are the node values)
//   moves   a       b
//   0       1       3
//   1       2       4
//   2       6       5
//   3       7       6
//   4       8       7
//   5       null    8       a fell off A; next move it goes to headB
//   6       3       null    b fell off B; next move it goes to headA
//   7       4       1
//   8       5       2
//   9       6       6       a == b: the node holding 6 is returned
//   Both pointers made 9 moves: 2 (A's own nodes) + 3 (B's own nodes) + 3 (shared nodes)
//   + 1 (the step onto null), in a different order for each - that is why they line up.
//
// STATUS
//   main() builds the lists above and calls getIntersectionNodeOptimal(a1, b1) but throws
//   the result away, so nothing is printed (it returns the node holding 6). The call to
//   method A is commented out in main(). The import of LC237DeleteNodeInALinkedList is
//   unused (it is in the same package anyway).
//
// COMPLEXITY
//   Time : O(m + n) - method B: each pointer moves at most m + n + 1 times. Method A:
//          two counting passes plus at most max(m, n) steps.
//   Space: O(1) - a few pointers and counters
//
// GOTCHAS
//   - Compare nodes with == (identity), never the values: equal values in different
//     nodes (the two 1s in example 1) are not an intersection.
//   - In method B the pointer goes onto null before it switches lists. That one extra
//     step is what makes two non-intersecting lists end with a == b == null instead of
//     looping forever.
//   - The lists are never modified, as the problem demands.
//
// RELATED
//   - LC 142 Linked List Cycle II - another linked-list trick where two pointers line up
//     because of how far each has walked (LC142LinkedListCycleII.java in this folder).
//   - LC 599 Minimum Index Sum of Two Lists - LeetCode lists it as similar: find what two
//     lists have in common, there with a hash map. A HashSet of A's nodes, checked while
//     walking B, is the O(m)-space way to solve this problem.
// ============================================================================
public class LC160IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {

        // Shared intersection part: c1 -> c2 -> c3
        ListNode c3 = new ListNode(8);
        ListNode c2 = new ListNode(7, c3);
        ListNode c1 = new ListNode(6, c2);

        // List A: a1 -> a2 -> c1 -> c2 -> c3
        ListNode a2 = new ListNode(2, c1);
        ListNode a1 = new ListNode(1, a2);

        // List B: b1 -> b2 -> b3 -> c1 -> c2 -> c3
        ListNode b3 = new ListNode(5, c1);
        ListNode b2 = new ListNode(4, b3);
        ListNode b1 = new ListNode(3, b2);
//        getIntersectionNode(a1, b1);
        // method B returns c1 (the node holding 6); the result is ignored, nothing is printed
        getIntersectionNodeOptimal(a1, b1);
    }

    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    // Approach A: measure both lists, advance the longer one by the difference, then walk
    // both together. O(m + n) time, O(1) space
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;
        ListNode s = null;
        ListNode sq = null;

        int size1 = 0;
        int size2 = 0;
        int diff = 0;

        // count the nodes of list A
        while(t1 != null){
            size1 ++;
            t1 = t1.next;
        }

        // count the nodes of list B
        while(t2 != null){
            size2 ++;
            t2 = t2.next;
        }

        // s = head of the longer list, sq = head of the shorter one
        if(size1 >= size2){
            diff = size1 - size2;
            s = headA;
            sq = headB;
        }else {
            diff = size2 - size1;
            s = headB;
            sq = headA;
        }

        // give the longer list its head start: skip the extra nodes
        while (s != null && diff > 0){
            s = s.next;
            diff --;
        }

        // the remaining parts are equally long: the first identical node is the answer
        while (s != null && sq != null){
            if(s == sq){
                return s;
            }
            s = s.next;
            sq = sq.next;
        }
        return null;
    }

     // Approach B: pointer switch, see INTUITION. O(m + n) time, O(1) space
     public static ListNode getIntersectionNodeOptimal(ListNode headA, ListNode headB) {
         // an empty list cannot intersect anything
         if (headA == null || headB == null) return null;
         ListNode a = headA;
         ListNode b = headB;
         // each pointer, after falling off its list (null), restarts on the other list
         while (a != b) {
             a = (a == null) ? headB : a.next;
             b = (b == null) ? headA : b.next;
         }
         // the first shared node, or null when both ran off the end together
         return a;
    }


}

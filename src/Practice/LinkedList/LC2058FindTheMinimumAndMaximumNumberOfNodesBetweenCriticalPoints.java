package Practice.LinkedList;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points   [Medium]
// https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
// Topics: Linked List
// ============================================================================
//
// PROBLEM
//   A node of a linked list is a "critical point" if it is strictly greater than both of
//   its neighbours (local maximum) or strictly smaller than both (local minimum). The
//   first and the last node have only one neighbour, so they can never be critical.
//   Return [minDistance, maxDistance]: the smallest and the largest distance (difference
//   of positions, counted in nodes) between two different critical points. With fewer
//   than two critical points the answer is [-1, -1].
//
// EXAMPLES
//   head = [3,1]                  ->  [-1,-1]   no node has both neighbours
//   head = [5,3,1,2,5,1,2]        ->  [1,3]
//       critical: 3rd node (1, min), 5th node (5, max), 6th node (1, min)
//       smallest gap 6 - 5 = 1, largest gap 6 - 3 = 3
//   head = [1,3,2,2,3,2,2,2,7]    ->  [3,3]
//       critical: 2nd node (3) and 5th node (3), both maxima; 5 - 2 = 3 for both answers
//
// CONSTRAINTS
//   The number of nodes is in the range [2, 10^5]
//   1 <= Node.val <= 10^5
//
// INTUITION
//   Collect the positions of all critical points in one left-to-right scan. They come out
//   in increasing order, which gives both answers directly:
//     - the largest distance is between the first and the last critical point;
//     - the smallest distance is between two critical points that are NEIGHBOURS in that
//       list (any pair with another critical point between them is farther apart than
//       the pairs formed with it).
//
// APPROACH (as implemented below)
//   1. Walk with dummy (a plain moving pointer, not a dummy head) and index = its position.
//      The node being tested is dummy.next: its previous node is dummy and its next node
//      is dummy.next.next.
//   2. If both of those exist and dummy.next.val is strictly below both neighbours or
//      strictly above both, add index to the list indexes. The value stored is the
//      position of the node BEFORE the critical one, a constant shift of 1 that cancels
//      out in every difference.
//   3. result = [-1, -1]. If indexes holds at least two entries:
//        max = last entry - first entry,
//        min = the smallest difference between consecutive entries.
//   4. Return result.
//
// DRY RUN   head = [5,3,1,2,5,1,2]  (example 2)
//   dummy at   tested node   neighbours   critical?        indexes
//   0 (5)      3             5 and 1      no               []
//   1 (3)      1             3 and 2      yes, minimum     [1]
//   2 (1)      2             1 and 5      no               [1]
//   3 (2)      5             2 and 1      yes, maximum     [1, 3]
//   4 (5)      1             5 and 2      yes, minimum     [1, 3, 4]
//   5 (1)      2 is last     no next      no               [1, 3, 4]
//   6 (2)      no next node               no               [1, 3, 4]
//   max = 4 - 1 = 3,  min = min(3 - 1, 4 - 3) = 1   ->  [1, 3]
//
// STATUS
//   main() builds [2,2,1,3] and throws the result away, so nothing is printed. Only the
//   node 1 is critical there (a minimum), so the call returns [-1, -1].
//
// COMPLEXITY
//   Time : O(n) - one scan of the list plus one pass over the critical positions
//   Space: O(c) - the list of critical positions, c <= n - 2 (O(n) in the worst case).
//          Keeping just the first position, the previous one and the smallest gap would
//          make it O(1).
//
// GOTCHAS
//   - Both comparisons are strict: equal neighbours never make a critical point (the two
//     2s in example 3 do not).
//   - The dummy.next != null && dummy.next.next != null test is what keeps the first and
//     last node from being critical and avoids a NullPointerException.
//   - The indexes list is sorted by construction, so first/last give the maximum distance.
//
// RELATED
//   - LC 162 Find Peak Element - the same "greater than both neighbours" test, on an array.
// ============================================================================
public class LC2058FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {
    public static void main(String[] args) {
        // the list 2 -> 2 -> 1 -> 3: only the 1 is critical, so the result is [-1, -1]
        // (it is discarded, nothing is printed)
        ListNode head = new ListNode(2);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        nodesBetweenCriticalPoints(head);
    }

    // Returns [minDistance, maxDistance] between critical points, or [-1, -1].
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        // positions (shifted by 1, see APPROACH) of the critical points, in increasing order
        List<Integer> indexes = new ArrayList<>();
        ListNode dummy;
        dummy = head;
        int index = 0;
        // dummy is the node BEFORE the one being tested (dummy.next)
        while (dummy != null){
            // dummy.next must have a previous node (dummy) and a next node (dummy.next.next);
            // then test: strict local minimum OR strict local maximum
            if(dummy.next != null && dummy.next.next != null && ((dummy.next.val < dummy.next.next.val && dummy.next.val < dummy.val) || (dummy.next.val > dummy.next.next.val && dummy.next.val > dummy.val))){
                indexes.add(index);
            }
            dummy = dummy.next;
            index++;
        }
        // default answer when there are fewer than two critical points
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (!indexes.isEmpty() && indexes.size() > 1) {
            int min = Integer.MAX_VALUE;
            // farthest apart: the first and the last critical point
            int max = indexes.get(indexes.size() - 1) - indexes.get(0);
            // closest: the smallest gap between two consecutive critical points
            for (int i = 1; i < indexes.size(); i++) {
                min = Math.min(min, indexes.get(i) - indexes.get(i - 1));
            }
            result[0] = min;
            result[1] = max;
        }
        return result;
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

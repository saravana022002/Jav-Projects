package Practice.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

// ============================================================================
// LC 1046. Last Stone Weight   [Easy]
// https://leetcode.com/problems/last-stone-weight/
// Topics: Array, Heap (Priority Queue)
// ============================================================================
//
// PROBLEM
//   stones[i] is the weight of stone i. Each turn you take the two heaviest stones and smash
//   them together. If they weigh the same, both disappear; otherwise the lighter one
//   disappears and the heavier one keeps the difference (y - x). Play until at most one stone
//   is left and return its weight, or 0 if nothing is left.
//
// EXAMPLES
//   stones = [2,7,4,1,8,1]  ->  1     8 and 7 leave 1; 4 and 2 leave 2; 2 and 1 leave 1;
//                                     1 and 1 vanish; one stone of weight 1 remains
//   stones = [1]            ->  1     nothing to smash
//
// CONSTRAINTS
//   1 <= stones.length <= 30,   1 <= stones[i] <= 1000
//
// INTUITION
//   The rule always asks for "the two heaviest stones right now", and every smash may put a
//   new (smaller) stone back in the pile. Repeatedly taking the maximum from a changing
//   collection is exactly what a max-heap is for: poll and offer both cost O(log n).
//
// APPROACH (as implemented below)
//   1. Offer every stone into maxHeap (a PriorityQueue with Collections.reverseOrder()).
//   2. While at least two stones are left: left = poll() (heaviest), right = poll() (second
//      heaviest), diff = left - right. If diff > 0 offer it back; if diff == 0 both stones
//      are gone.
//   3. Return the remaining stone (peek) or 0 if the heap is empty.
//
// DRY RUN   stones = [2,7,4,1,8,1]   (main() runs this; the answer 1 is not printed)
//   heap (largest first)  8 7 4 2 1 1
//   poll 8, 7  -> diff 1, offer     heap 4 2 1 1 1
//   poll 4, 2  -> diff 2, offer     heap 2 1 1 1
//   poll 2, 1  -> diff 1, offer     heap 1 1 1
//   poll 1, 1  -> diff 0, nothing   heap 1
//   size is 1, loop ends -> peek = 1
//
// COMPLEXITY
//   Time : O(n log n) - n offers to build the heap, then at most n - 1 smashes, each doing
//          two polls and at most one offer (O(log n) each).
//   Space: O(n) - the heap.
//
// GOTCHAS
//   - left comes out of a max-heap first, so left - right is never negative.
//   - A zero difference is not pushed back: equal stones destroy each other.
//   - `!maxHeap.isEmpty() &&` in the loop condition is redundant (size() > 1 already implies
//     it), and the final ternary handles the "everything destroyed" case by returning 0.
//
// STATUS
//   The code is correct. main() calls lastStoneWeight() but does not print the result
//   (it is 1 for its data).
//
// RELATED
//   - LC 1049 Last Stone Weight II - a different question despite the similar name: you may
//     smash ANY two stones and want the smallest leftover, which needs dynamic programming.
// ============================================================================
public class LC1046LastStoneWeight {
    public static void main(String[] args) {
        // Example 1: the result 1 is returned but not printed
        int[] stones = {2,7,4,1,8,1};
        lastStoneWeight(stones);
    }
    // Returns the weight of the last stone, or 0 if all stones were destroyed.
    public static int lastStoneWeight(int[] stones) {
        // max-heap: the heaviest stone is always at the front
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        int left = 0;
        int right = 0;
        int diff = 0;
        // smash the two heaviest stones until at most one is left
        while (!maxHeap.isEmpty() && maxHeap.size() > 1){
            left = maxHeap.poll();
            right = maxHeap.poll();
            diff = left - right;
            // equal stones vanish; otherwise the difference goes back in the pile
            if(diff > 0){
                maxHeap.offer(diff);
            }
        }
        return !maxHeap.isEmpty() ? maxHeap.peek() : 0;
    }
}

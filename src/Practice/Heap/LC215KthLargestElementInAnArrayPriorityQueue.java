package Practice.Heap;

import java.util.*;

// ============================================================================
// LC 215. Kth Largest Element in an Array   [Medium]
// https://leetcode.com/problems/kth-largest-element-in-an-array/
// Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Quickselect
// ============================================================================
//
// PROBLEM
//   Given an integer array nums and an integer k, return the k-th largest element of the
//   array in sorted order. Duplicates count separately - it is NOT the k-th distinct value.
//   (LeetCode also asks whether you can do it without sorting.)
//
// EXAMPLES
//   nums = [3,2,1,5,6,4], k = 2            ->  5     sorted high to low: 6 5 4 3 2 1
//   nums = [3,2,3,1,2,4,5,5,6], k = 4      ->  4     6 5 5 4 ... - the two 5s count twice
//
// CONSTRAINTS
//   1 <= k <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
//
// INTUITION
//   A max-heap always hands out its largest element first. Put all the numbers in one, throw
//   away the k - 1 largest, and the one now at the top is the k-th largest. Java's
//   PriorityQueue is a min-heap by default, so Comparator.reverseOrder() flips it.
//
// APPROACH (as implemented below)
//   1. Copy the numbers into the list `elements` (an extra step - see GOTCHAS).
//   2. maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); maxHeap.addAll(elements).
//   3. poll() k - 1 times (the loop runs for i = k .. 2), discarding the k - 1 largest.
//   4. peek() is now the k-th largest; return it.
//
// DRY RUN   nums = [3,2,1,5,6,4], k = 2   (main() prints 5)
//   maxHeap holds 6 5 4 3 2 1 (largest first)
//   loop i = 2: poll() removes 6           (k - 1 = 1 poll in total)
//   peek() -> 5
//
// COMPLEXITY
//   Time : O(n log n) - addAll offers the elements one by one (O(log n) each), then k - 1
//          polls of O(log n) each; k <= n.
//   Space: O(n) - the list and the heap each hold all n numbers.
//
// GOTCHAS
//   - The ArrayList `elements` is not needed; the numbers could be offered straight from the
//     array into the heap.
//   - k = 1: the loop does not run and peek() returns the maximum.
//   - The input array is not modified.
//   - Other ways to do it: a min-heap capped at size k (O(n log k) time, O(k) space - the root
//     is the answer) or Quickselect (O(n) on average).
//
// RELATED
//   - LC 703 Kth Largest Element in a Stream - the capped size-k heap idea, on a stream.
//   - LC 973 K Closest Points to Origin - another "best k items" problem solved with a heap.
//
// SEE ALSO
//   LC215KthLargestElementInAnArrayManualHeap.java - the same problem with a hand-written
//   heap stored inside the int[] instead of java.util.PriorityQueue.
// ============================================================================
public class LC215KthLargestElementInAnArrayPriorityQueue {
    public static void main(String[] args) {
        // Example 1 - expected output: 5
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println(findKthLargest(nums1, k1));
    }

    // Returns the k-th largest value: drop the k - 1 largest from a max-heap, read the top.
    public static int findKthLargest(int[] nums, int k) {
        List<Integer> elements = new ArrayList<>();
        for (int num : nums) {
            elements.add(num);
        }
        // max-heap: reverseOrder() makes the largest number come out first
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(elements);
        // discard the k - 1 largest numbers
        for(int i = k; i > 1; i--){
            maxHeap.poll();
        }
        // what is on top now is the k-th largest
        return maxHeap.peek();
    }
}

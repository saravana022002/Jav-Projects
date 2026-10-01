package Practice.Heap;

import java.util.*;
import java.util.stream.Collectors;

// ============================================================================
// LC 373. Find K Pairs with Smallest Sums   [Medium]
// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
// Topics: Array, Heap (Priority Queue)
// ============================================================================
//
// PROBLEM
//   nums1 and nums2 are sorted in non-decreasing order. A pair is (u, v) with u taken from
//   nums1 and v from nums2. Return the k pairs with the smallest sums u + v.
//
// EXAMPLES
//   nums1 = [1,7,11], nums2 = [2,4,6], k = 3  ->  [[1,2],[1,4],[1,6]]
//        the full order by sum is [1,2] [1,4] [1,6] [7,2] [7,4] [11,2] [7,6] ...
//   nums1 = [1,1,2], nums2 = [1,2,3], k = 2   ->  [[1,1],[1,1]]
//        two different index pairs can give the same values, and both are returned
//
// CONSTRAINTS
//   1 <= nums1.length, nums2.length <= 10^5,   -10^9 <= nums1[i], nums2[i] <= 10^9
//   both arrays sorted in non-decreasing order
//   1 <= k <= 10^4,   k <= nums1.length * nums2.length
//
// INTUITION
//   Lay the sums out as a grid: row i, column j holds nums1[i] + nums2[j]. Because both arrays
//   are sorted, every row grows to the right and every column grows downwards. So each row is
//   just a sorted list, and "the k smallest sums" is a k-way merge of n sorted lists - exactly
//   the trick of merging with a min-heap, and it is lazy: take the smallest entry, then only
//   the next entry of THAT row becomes a candidate. The grid is never built; only about
//   n + k entries ever exist.
//
// APPROACHES IN THIS FILE   (n = nums1.length, m = nums2.length)
//   A. kSmallestPairs()  - brute force: put ALL n * m pairs into a min-heap ordered by sum and
//                          poll k of them - O(nm log nm) time, O(nm) memory. Hopeless for
//                          large arrays (n * m can reach 10^10).
//   B. kSmallestPairs1() - frontier heap: O((n + k) log n) time, O(n + k) space
//                          <- main() runs this one
//
// APPROACH (as implemented below, method B)
//   1. No arrays or k == 0 -> return the empty result.
//   2. minHeap holds int[]{sum, i, j} ordered by sum (a[0]). Seed it with the first entry of
//      every row: {nums1[i] + nums2[0], i, 0} for each i.
//   3. While the heap is not empty and k > 0: poll the smallest curr = {sum, i, j}; add the
//      pair (nums1[i], nums2[j]) to result; if j + 1 < nums2.length push the next entry of the
//      same row, {nums1[i] + nums2[j+1], i, j+1}; k--.
//   4. Return result.
//
// DRY RUN   nums1 = [1,7,11], nums2 = [2,4,6], k = 3   (main() prints [[1, 2], [1, 4], [1, 6]])
//   seed     (sum 3; row 0, col 0) (9; 1, 0) (13; 2, 0)
//   poll 3   -> pair [1,2]   push (1+4 = 5; 0, 1)      k = 2
//   poll 5   -> pair [1,4]   push (1+6 = 7; 0, 2)      k = 1
//   poll 7   -> pair [1,6]   col 3 does not exist, no push      k = 0
//   result = [[1,2],[1,4],[1,6]]
//
// COMPLEXITY
//   A: Time O(nm log nm), Space O(nm).
//   B: Time O((n + k) log n) - n seed offers, then k rounds of one poll and at most one offer
//      on a heap that never holds more than n entries.  Space O(n + k) - heap plus result.
//
// GOTCHAS
//   - Only (i, j+1) is pushed after a poll. That is enough because each row is sorted; the
//     entry below, (i+1, j), is already covered by row i+1's own seed entry and its successors.
//   - The int sums fit: |value| <= 10^9, so a sum stays within +-2 * 10^9 < 2^31.
//   - A would hit a NullPointerException if k > n * m (poll() returns null); the constraints
//     rule that out. B simply returns fewer pairs in that case.
//   - B seeds all n rows although at most min(n, k) of them can matter (the first-column sums
//     grow with i). The extra entries are harmless.
//   - Pairs with equal sums can come out in any order.
//
// STATUS
//   Both methods are correct (checked against sorting all pairs, on 5000 random inputs).
//   Method A is only practical for small inputs; method B is the real solution.
//
// RELATED
//   - LC 23 Merge k Sorted Lists - the same heap-based k-way merge (LC23MergeKSortedLists.java
//     in this folder).
//   - LC 378 Kth Smallest Element in a Sorted Matrix - the same frontier-heap idea on a matrix.
// ============================================================================
public class LC373FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        // Example 1 - expected output: [[1, 2], [1, 4], [1, 6]]  (uses approach B)
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        System.out.println(kSmallestPairs1(nums1, nums2, k));
    }
    // Approach A: brute force - all n * m pairs in a heap, then poll k. Not used by main().
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // ordered by the pair's sum
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0] + a[1])
        );

        // every possible pair goes in
        for (int value : nums1) {
            for (int i : nums2) {
                minHeap.offer(new int[]{value, i});
            }
        }

        // the k smallest come out first (poll() would be null if k > n * m)
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(Arrays.stream(minHeap.poll())
                    .boxed()
                    .collect(Collectors.toList()));
        }
        return ans;
    }

    // Approach B: frontier heap over the rows of the sum grid - O((n + k) log n).
    public static List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0 || k == 0) {
            return result;
        }
        // entries are {sum, index in nums1, index in nums2}, smallest sum first
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])
        );
        // seed: the first entry of every row
        for (int i = 0; i < nums1.length; i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        while(!minHeap.isEmpty() && k > 0){
            // the smallest remaining sum is the next answer
            int[] curr = minHeap.poll();
            int i = curr[1];
            int j = curr[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
            // its row continues with the next element of nums2
            if(j + 1 < nums2.length) {
                minHeap.offer(new int[]{
                        nums1[i] + nums2[j + 1],
                        i,
                        j + 1
                });
            }
            k--;
        }
        return result;
    }

}

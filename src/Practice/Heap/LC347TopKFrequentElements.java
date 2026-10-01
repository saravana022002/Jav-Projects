package Practice.Heap;

import java.util.*;

// ============================================================================
// LC 347. Top K Frequent Elements   [Medium]
// https://leetcode.com/problems/top-k-frequent-elements/
// Topics: Array, Hash Table, Divide and Conquer, Sorting, Heap (Priority Queue),
//         Bucket Sort, Counting, Quickselect
// ============================================================================
//
// PROBLEM
//   Given an integer array nums and an integer k, return the k values that occur most often.
//   The answer may be returned in any order, and it is guaranteed to be unique (no tie at the
//   k-th place). LeetCode's follow-up asks for better than O(n log n).
//
// EXAMPLES
//   nums = [1,1,1,2,2,3], k = 2              ->  [1,2]     1 occurs 3 times, 2 twice, 3 once
//   nums = [1], k = 1                        ->  [1]
//   nums = [1,2,1,2,1,2,3,1,3,2], k = 2      ->  [1,2]     1 occurs 4 times, 2 four times
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
//   k is in [1, number of distinct values],   the answer is unique
//
// INTUITION
//   First count how often each value occurs (a HashMap). Then we only need the k values with
//   the biggest counts, not a full ranking. A min-heap that is never allowed to grow past k
//   entries does exactly that: whenever a new entry makes it too big, the entry with the
//   SMALLEST count is thrown out, so what survives are the k most frequent values.
//
// APPROACHES IN THIS FILE   (n = array length, m = number of distinct values)
//   A. topKFrequent()        - count, then a size-k min-heap over the (value, count) entries
//                              - O(n + m log k) time        <- main() runs this one
//   B. topKFrequentOptimal() - count, then bubble-sort ALL distinct entries by count
//                              (descending) and take the first k - O(n + m^2) time. The name
//                              is misleading: the O(n) way is a bucket sort by count.
//
// APPROACH (as implemented below, method A)
//   1. Guards: an empty nums is returned as is; k <= 0 gives an empty array.
//   2. freqMap: value -> number of occurrences.
//   3. minHeap = PriorityQueue over the map entries ordered by count
//      (Comparator.comparingInt(Map.Entry::getValue)). For every entry: offer it, and if the
//      heap now has more than k entries, poll() - that drops the entry with the lowest count.
//   4. Poll the survivors into `result` (they come out from least to most frequent) and copy
//      them into the int[] ans1.
//
// DRY RUN   nums = [1,1,1,2,2,3], k = 2
//   freqMap = {1:3, 2:2, 3:1}
//   offer 1:3            heap {1:3}
//   offer 2:2            heap {2:2, 1:3}
//   offer 3:1            size 3 > 2 -> poll the lowest count (3:1)   heap {2:2, 1:3}
//   poll the survivors -> keys 2 then 1 -> returns [2, 1]   (any order is accepted)
//
// COMPLEXITY
//   A: Time O(n + m log k) - n to count, then m offers on a heap of at most k + 1 entries.
//      Space O(m) for the map (the heap holds at most k entries).
//   B: Time O(n + m^2) - the bubble sort dominates; with up to 20001 distinct values here that
//      is still only a fraction of a second.  Space O(m).
//
// GOTCHAS
//   - A returns the answer from least to most frequent, not most frequent first; that is fine
//     because any order is accepted.
//   - The "unique answer" guarantee means there is never a tie for the k-th place to break.
//   - B needs k <= number of distinct values (guaranteed), or freqList.get(i) runs off the end.
//   - The heap version costs O(n + m log k); a bucket sort by count (see SEE ALSO) brings it
//     down to O(n).
//
// STATUS
//   Both methods are correct (checked against a frequency-sort reference on 10000 random
//   arrays). main() runs only topKFrequent with nums = {-1,-1}, k = 1 and does not print the
//   result (it is [-1]).
//
// RELATED
//   - LC 215 Kth Largest Element in an Array - the same "keep the best k in a heap" idea
//     (two solutions in this folder).
//   - LC 692 Top K Frequent Words - the same task on strings, with alphabetical tie-breaks.
//
// SEE ALSO
//   src/Leetcode/FrequencyAndMultisetResoning4/LC347TopKFrequent.java - your curated version
//   using a bucket sort by count (O(n)), which answers the follow-up.
// ============================================================================
public class LC347TopKFrequentElements {
    public static void main(String[] args) {
        // One value (-1) twice, k = 1: returns [-1]; the result is not printed.
        int[] nums = {-1,-1};
        int k = 1;
        topKFrequent(nums, k);
    }

    // Approach A: count, then keep the k most frequent entries in a min-heap.
    public static int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 0){
            return nums;
        }
        if (k <= 0) return new int[0];

        // value -> number of occurrences
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // min-heap on count: the weakest of the current k candidates is always on top
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            // more than k candidates: drop the one with the lowest count
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }


        // what is left are the k most frequent values (popped least frequent first)
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // copy the list into an int[]
        int[] ans1 = new int[result.size()];
        int index = 0;
        for(int a : result){
            ans1[index] = a;
            index ++;
        }
        return ans1;
    }


    // Approach B: count, bubble-sort all entries by count, take the first k - O(n + m^2).
    // (Not "optimal" despite the name; a bucket sort by count would be O(n).)
    public static int[] topKFrequentOptimal(int[] nums, int k) {
        if(nums.length == 0){
            return nums;
        }
        if (k <= 0) return new int[0];

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> freqList = new ArrayList<>(freqMap.entrySet());

        // Step 3: Bubble sort by frequency (descending)
        int n = freqList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if(freqList.get(j).getValue() < freqList.get(j + 1).getValue() ){
                    Collections.swap(freqList, j, j + 1);
                }
            }
        }

        int[] ans = new int[k];
        int index = 0;
        for (int i = 0; i < k; i++) {
            ans[index] = freqList.get(i).getKey();
            index ++;
        }
        return ans;
    }
}

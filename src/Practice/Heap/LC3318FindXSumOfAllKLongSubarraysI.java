package Practice.Heap;

import java.util.*;

// ============================================================================
// LC 3318. Find X-Sum of All K-Long Subarrays I   [Easy]
// https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/
// Topics: Array, Hash Table, Sliding Window, Heap (Priority Queue)
// ============================================================================
//
// PROBLEM
//   The x-sum of an array is computed like this: count how often each value occurs, keep only
//   the occurrences of the x most frequent values (if two values occur equally often, the
//   BIGGER value counts as more frequent), and add up what is kept. With fewer than x
//   distinct values nothing is dropped, so the x-sum is just the plain sum.
//   Given nums, k and x, return an array answer of length n - k + 1 where answer[i] is the
//   x-sum of the window nums[i .. i + k - 1].
//
// EXAMPLES
//   nums = [1,1,2,2,3,4,2,3], k = 6, x = 2  ->  [6, 10, 12]
//        window [1,2,2,3,4,2]: 2 occurs 3 times; 1, 3 and 4 tie with one occurrence and the
//        biggest, 4, wins -> 2+2+2 + 4 = 10
//   nums = [3,8,7,8,7,5], k = 2, x = 2      ->  [11, 15, 15, 15, 12]
//        k == x, so nothing is ever dropped: these are the plain window sums
//
// CONSTRAINTS
//   1 <= n == nums.length <= 50,   1 <= nums[i] <= 50,   1 <= x <= k <= nums.length
//
// INTUITION
//   The limits are tiny (n <= 50), so no clever sliding update is needed: for every window
//   just do what the definition says - count the values, rank them by (frequency, value) and
//   add up the best x. A max-priority-queue gives the ranking order directly.
//
// APPROACH (as implemented below)
//   1. findXSum makes answer of length n - k + 1 and sets answer[i] = getTopK(nums, i, i+k-1, x).
//   2. getTopK counts the values of nums[start..end] in the HashMap integerMap.
//   3. Every distinct value becomes a FrequencyMap(frequency, value) in a PriorityQueue `list`
//      whose comparator puts the higher frequency first and, on equal frequency, the bigger
//      value first.
//   4. Poll at most x entries (stop early if the queue runs out) and add
//      frequency * value for each - a kept value contributes ALL its occurrences.
//   5. Return that sum.
//
// DRY RUN   nums = [1,1,2,2,3,4,2,3], k = 6, x = 2, window i = 1 = [1,2,2,3,4,2]
//   integerMap = {1:1, 2:3, 3:1, 4:1}
//   queue order: (freq 3, value 2), (1, 4), (1, 3), (1, 1)
//   poll (3,2): 3*2 = 6;   poll (1,4): 1*4 = 4;   x is now 0 -> sum = 10
//   main(): nums = [9,2,2], k = 3, x = 3 -> one window, map {9:1, 2:2}; only 2 distinct values,
//   so both are taken: 2*2 + 9 = 13 -> prints [13]
//
// COMPLEXITY
//   Time : O((n-k+1) * (k + d log d)) with d = distinct values in a window (d <= k) - each
//          window is counted (k), queued (d log d) and polled (x log d); at most O(n*k log k).
//   Space: O(k) - the map and queue of one window at a time.
//
// GOTCHAS
//   - Ties on frequency go to the BIGGER value (the comparator's second branch).
//   - If a window has fewer than x distinct values the `!list.isEmpty()` guard makes the loop
//     stop and the x-sum is the whole window's sum.
//   - Each window is rebuilt from scratch; there is no incremental sliding update. That is
//     fine for n <= 50 but too slow for the big inputs of part II.
//   - getTopK uses its parameter x as the countdown.
//   - The sum is at most 50 * 50, so int is plenty.
//
// RELATED
//   - LC 3321 Find X-Sum of All K-Long Subarrays II - the same task with large limits, where
//     windows must be updated incrementally (LC3321FindXSumOfAllKLongSubarraysII.java in this
//     folder).
//   - LC 347 Top K Frequent Elements - top-k by frequency (LC347TopKFrequentElements.java in
//     this folder).
// ============================================================================
public class LC3318FindXSumOfAllKLongSubarraysI {

    public static void main(String[] args) {

        // One window [9,2,2] with x = 3 > 2 distinct values, so the whole window counts.
        // Expected output: [13]
        int[] nums = {9,2,2};
        int k = 3;
        int x = 3;
        System.out.println(Arrays.toString(findXSum(nums, k, x)));
    }
    // Returns the x-sum of every window of length k.
    public static int[] findXSum(int[] nums, int k, int x) {
        int[] answer = new int[nums.length - k + 1];

        for(int i = 0; i < answer.length; i++){
            answer[i] = getTopK(nums, i, i + k - 1, x);
        }
        return answer;
    }


    // x-sum of nums[start..end]: rank the values by (frequency, value) and add the top x.
    public static int getTopK(int[] nums, int start, int end, int x){
        // value -> how often it occurs in the window
        Map<Integer, Integer> integerMap = new HashMap<>();

        for (int i = start; i <= end; i++) {
            integerMap.put(nums[i], integerMap.getOrDefault(nums[i], 0) + 1);
        }
        // best first: higher frequency, and on a tie the bigger value
        Queue<FrequencyMap> list = new PriorityQueue<>((a, b) ->
        {
          if(a.frequency != b.frequency ){
              return Integer.compare(b.frequency, a.frequency);
          }else {
              return Integer.compare(b.value, a.value);
          }
        });

        for (Map.Entry<Integer, Integer> entry : integerMap.entrySet()) {
            list.add(new FrequencyMap(entry.getValue(), entry.getKey()));
        }

        // take up to x entries; each contributes all its occurrences (frequency * value)
        int answer = 0;
        FrequencyMap frequencyMap = null;
        while (!list.isEmpty() && x > 0 ){
            frequencyMap = list.poll();
            answer = answer + frequencyMap.frequency * frequencyMap.value;
            x--;
        }
        return answer;
    }

    // a distinct value together with how many times it occurs in the window
    static class FrequencyMap{
       int value;
       int frequency;
       FrequencyMap(int frequency, int value){
           this.frequency = frequency;
           this.value = value;
       }
    }

}

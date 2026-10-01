package Practice.Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// ============================================================================
// LC 3321. Find X-Sum of All K-Long Subarrays II   [Hard]
// https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/
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
//   (Same task as LC 3318, but with much larger limits.)
//
// EXAMPLES
//   nums = [1,1,2,2,3,4,2,3], k = 6, x = 2  ->  [6, 10, 12]
//        window [1,2,2,3,4,2]: 2 occurs 3 times; 1, 3 and 4 tie with one occurrence and the
//        biggest, 4, wins -> 2+2+2 + 4 = 10
//   nums = [3,8,7,8,7,5], k = 2, x = 2      ->  [11, 15, 15, 15, 12]
//        k == x, so nothing is ever dropped: these are the plain window sums
//
// CONSTRAINTS
//   1 <= n == nums.length <= 10^5,   1 <= nums[i] <= 10^9,   1 <= x <= k <= nums.length
//
// INTUITION   (what this code does, and what the big limits really need)
//   The code applies the definition to every window from scratch - count the values, rank them
//   by (frequency, value), add up the best x - just like part I (LC 3318). That is correct,
//   but each window costs O(k + d log d) and there are n - k + 1 windows, which is hopeless
//   for n = 10^5. The intended solution slides the window: only two values change frequency
//   per step (the one leaving and the one entering), so keep the (frequency, value) pairs in
//   two ordered sets - the x best, and the rest - plus a running sum of the x best, and fix
//   the boundary between them after each slide.
//
// APPROACH (as implemented below - the brute-force version)
//   1. findXSum makes a long[] answer of length n - k + 1 and sets
//      answer[i] = getTopK(nums, i, i + k - 1, x).
//   2. getTopK counts the values of nums[start..end] in the HashMap integerMap.
//   3. Every distinct value becomes a FrequencyMap(frequency, value) in a PriorityQueue `list`
//      ordered by higher frequency first and, on a tie, bigger value first.
//   4. Poll at most x entries (stop early if the queue runs out) and add
//      (long) frequency * value for each; return that sum.
//
// DRY RUN   nums = [3,8,7,8,7,5], k = 2, x = 2
//   window [3,8]: integerMap {3:1, 8:1}; queue order (1,8) then (1,3) (tie -> bigger value
//                 first); both are taken -> 8 + 3 = 11
//   window [8,7]: {8:1, 7:1} -> 8 + 7 = 15     window [7,8]: 15     window [8,7]: 15
//   window [7,5]: {7:1, 5:1} -> 12
//   answer = [11, 15, 15, 15, 12]
//
// COMPLEXITY
//   Time : O((n-k+1) * (k + d log d)) with d = distinct values in a window (d <= k); at worst
//          O(n * k log k). Measured for this note: n = 20000, k = 10000 already takes several
//          seconds, and n = 10^5 with k = 5 * 10^4 is about 25 times more work.
//   Space: O(k) - the map and queue of one window at a time.
//
// GOTCHAS
//   - (long) frequency * value: the cast comes BEFORE the multiplication, because sums go up
//     to about 10^5 * 10^9 = 10^14, far beyond int. The result array is long[] for the same reason.
//   - Ties on frequency go to the BIGGER value (the comparator's second branch).
//   - With fewer than x distinct values the `!list.isEmpty()` guard stops the loop and the
//     x-sum is the whole window's sum.
//   - getTopK uses its parameter x as the countdown.
//
// STATUS
//   Known issue (performance): the results are correct - both examples match and so does a
//   reference on random data, including values near 10^9 - but every window is recomputed from
//   scratch, so the code is far too slow for n up to 10^5 and would exceed the time limit on
//   LeetCode. A sliding-window solution with two ordered sets is needed (see INTUITION).
//   The file also has no main(). The code is left unchanged on purpose.
//
// RELATED
//   - LC 3318 Find X-Sum of All K-Long Subarrays I - the easy version (n <= 50) where this
//     per-window rebuild is enough (LC3318FindXSumOfAllKLongSubarraysI.java in this folder).
//   - LC 480 Sliding Window Median - also keeps two ordered halves while the window slides.
// ============================================================================
public class LC3321FindXSumOfAllKLongSubarraysII {
    // Returns the x-sum of every window of length k (brute force: each window rebuilt).
    public static long[] findXSum(int[] nums, int k, int x) {
        long[] answer = new long[nums.length - k + 1];

        for(int i = 0; i < answer.length; i++){
            answer[i] = getTopK(nums, i, i + k - 1, x);
        }
        return answer;
    }


    // x-sum of nums[start..end]: rank the values by (frequency, value) and add the top x.
    public static long getTopK(int[] nums, int start, int end, int x){
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
        long answer = 0;
        FrequencyMap frequencyMap = null;
        while (!list.isEmpty() && x > 0 ){
            frequencyMap = list.poll();
            answer += (long) frequencyMap.frequency * frequencyMap.value;
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

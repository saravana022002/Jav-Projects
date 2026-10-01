package Practice.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 3795. Minimum Subarray Length With Distinct Sum At Least K   [Medium]
// https://leetcode.com/problems/minimum-subarray-length-with-distinct-sum-at-least-k/
// Topics: Array, Hash Table, Sliding Window
// ============================================================================
//
// PROBLEM
//   For a subarray, add up the DISTINCT values it contains - a value that shows up several
//   times is counted only once. Find the shortest subarray whose distinct sum is at least k
//   and return its length, or -1 if no subarray gets there.
//
// EXAMPLES
//   nums = [2,2,3,1], k = 4  ->  2     [2,3] has distinct sum 2 + 3 = 5
//   nums = [3,2,3,4], k = 5  ->  2     [3,2] has distinct sum 5
//   nums = [5,5,4],   k = 5  ->  1     [5] alone is enough
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^5,   1 <= k <= 10^9
//
// INTUITION
//   Every value is positive, so making a window longer can only keep or raise its distinct
//   sum, and making it shorter can only keep or lower it. That is exactly what a two-pointer
//   window needs: for each right end r, push the left end l forward while the distinct sum
//   is still >= k; the last window that was still valid is the shortest one ending at r.
//   A frequency map says when a value enters the window for the first time (add it to the
//   sum) and when its last copy leaves (take it out of the sum).
//
// APPROACHES IN THIS FILE
//   A. minLength()  - first attempt: restarts the window when a value repeats and never
//                     shrinks it - WRONG (see STATUS)                <- main() calls this one
//   B. minLength1() - sliding window + frequency map - O(n)          <- the correct solution
//
// APPROACH (as implemented below, minLength1)
//   For every r:
//   1. val = nums[r]; freq[val]++. If freq[val] is now 1, val is new: distinctSum += val.
//   2. While distinctSum >= k:
//        minLen = min(minLen, r - l + 1);
//        leftVal = nums[l]; freq[leftVal]--; if it hit 0, distinctSum -= leftVal; l++.
//   3. At the end return minLen, or -1 if it was never set.
//
// DRY RUN   minLength1, nums = [2,2,3,1], k = 4
//   r=0 val=2  freq{2:1}        distinctSum = 2
//   r=1 val=2  freq{2:2}        distinctSum = 2  (2 already counted)
//   r=2 val=3  freq{2:2,3:1}    distinctSum = 5 >= 4
//        window [2,2,3] -> minLen = 3; drop a 2, one copy remains -> sum stays 5, l=1
//        window [2,3]   -> minLen = 2; drop the last 2          -> sum = 3,      l=2
//   r=3 val=1  distinctSum = 4 >= 4
//        window [3,1]   -> minLen = 2; drop 3                   -> sum = 1,      l=3
//   return 2
//
// STATUS
//   Known issue: main() calls minLength (the first attempt) and does not print the result.
//   minLength never moves l forward except to restart at a repeated value (and it never
//   clears its map), so it misses short windows: [3,1,7], k = 8 returns 3, but the correct
//   answer is 2 ([1,7]). minLength1 is correct. The code is left unchanged on purpose.
//
// COMPLEXITY   (minLength1)
//   Time : O(n) - r and l each move forward at most n times, each step is O(1) map work
//   Space: O(n) - the frequency map can hold every distinct value of nums
//
// GOTCHAS
//   - The window idea needs positive values: with negatives, adding an element could lower
//     the distinct sum and the shrink rule would no longer be safe.
//   - Add a value only on its FIRST copy and subtract it only when its LAST copy leaves.
//   - No int overflow: before each new value distinctSum is < k <= 10^9, and the value
//     added is <= 10^5.
//   - drelanvixo is an unused alias of nums (LeetCode contest problems ask you to store the
//     input midway in a variable with such a name). It does not change the result.
//
// RELATED
//   - LC 209 Minimum Size Subarray Sum - the same shrink-while-valid window with a plain sum.
// ============================================================================
public class LC3795MinimumSubarrayLengthWithDistinctSumAtLeastK {
    public static void main(String[] args) {
        // calls the first attempt: it returns 3 here, the correct answer is 2 ([1,7]).
        // The result is not printed - see STATUS. minLength1(nums, k) returns 2.
        int[] nums = {3,1,7};
        int k = 8;
        minLength(nums, k);
    }
    // Approach A (first attempt, WRONG - see STATUS): keeps a running sum, restarts the
    // window at r when a value repeats, and never shrinks it from the left.
    public static int minLength(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < nums.length){
            // one element that already reaches k is a window of length 1
            if(nums[r] >= k){
                minLength = Math.min(minLength, 1);
            }
            // repeated value: restart the window at r (the old values stay in the map)
            if(map.containsKey(nums[r])){
                l = r;
                sum = 0;
            }
            sum = sum + nums[r];
            if(sum >= k){
                minLength = Math.min(minLength, r - l + 1);
            }
            map.put(nums[r], r);
            r++;
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    // Approach B: sliding window over nums[l..r] with a frequency map. Returns the shortest
    // length whose distinct sum is >= k, or -1. O(n)
    public static int minLength1(int[] nums, int k) {
        int l = 0;
        int distinctSum = 0;
        int minLen = Integer.MAX_VALUE;

        // how many copies of each value are inside the window
        Map<Integer, Integer> freq = new HashMap<>();

        // unused alias of nums - the contest asks for a variable with this name
        int[] drelanvixo = nums;

        for (int r = 0; r < nums.length; r++) {
            int val = nums[r];
            freq.put(val, freq.getOrDefault(val, 0) + 1);

            // step 1: first copy of val in the window -> it joins the distinct sum
            if (freq.get(val) == 1) {
                distinctSum += val;
            }

            // step 2: while the window is valid, record it and try a shorter one
            while (distinctSum >= k) {
                minLen = Math.min(minLen, r - l + 1);

                int leftVal = nums[l];
                freq.put(leftVal, freq.get(leftVal) - 1);
                // the last copy of leftVal just left -> it no longer counts
                if (freq.get(leftVal) == 0) {
                    distinctSum -= leftVal;
                }
                l++;
            }
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }


}

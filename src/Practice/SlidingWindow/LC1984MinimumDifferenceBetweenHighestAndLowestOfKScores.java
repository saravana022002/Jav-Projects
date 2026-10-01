package Practice.SlidingWindow;

import java.util.Arrays;

// ============================================================================
// LC 1984. Minimum Difference Between Highest and Lowest of K Scores   [Easy]
// https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
// Topics: Array, Sliding Window, Sorting
// ============================================================================
//
// PROBLEM
//   nums[i] is the score of student i. Choose any k students so that the gap between the
//   highest and the lowest chosen score is as small as possible. Return that smallest gap.
//
// EXAMPLES
//   nums = [90], k = 1        ->  0     a single score: highest and lowest are the same
//   nums = [9,4,1,7], k = 2   ->  2     choose 7 and 9
//
// CONSTRAINTS
//   1 <= k <= nums.length <= 1000,   0 <= nums[i] <= 10^5
//
// INTUITION
//   Only the lowest and the highest chosen score matter. Sort the scores and take any group
//   of k with lowest L and highest H: the k sorted values that start at L all lie between
//   L and H (the group itself shows there are k values in that range), so that block of
//   k NEIGHBOURS is at least as good. Hence only blocks of k consecutive sorted values need
//   checking, and a block's gap is simply (its last value - its first value).
//
// APPROACH (as implemented below)
//   1. Arrays.sort(nums).
//   2. First window nums[0..k-1]: min = nums[0], max = nums[k-1], minDiff = max - min.
//   3. For i = k .. n-1 the window ending at i is nums[i-k+1 .. i]:
//      min = nums[i-k+1], max = nums[i], diff = max - min; keep the smaller in minDiff.
//   4. Return minDiff.
//
// DRY RUN   nums = [9,4,1,7], k = 2
//   sorted          [1,4,7,9]
//   first window    [1,4]   minDiff = 4 - 1 = 3
//   i=2             [4,7]   diff = 3               minDiff = 3
//   i=3             [7,9]   diff = 2 < 3           minDiff = 2
//   return 2
//
// STATUS
//   main() calls minimumDifference but does not print the result (it returns 2).
//
// COMPLEXITY
//   Time : O(n log n) - the sort dominates; the window scan is O(n)
//   Space: O(1) extra - Arrays.sort on an int[] works in place (only O(log n) stack)
//
// GOTCHAS
//   - Arrays.sort reorders the caller's array: after the call main's nums is [1,4,7,9].
//   - k = 1 correctly gives 0: the first window is nums[0] - nums[0].
//   - Sorting is what makes the window valid - on the unsorted array neighbours mean nothing.
//
// RELATED
//   - LC 561 Array Partition - same first move: sort, then the best partners are neighbours.
// ============================================================================
public class LC1984MinimumDifferenceBetweenHighestAndLowestOfKScores {
    // returns the smallest (highest - lowest) over every choice of k scores
    public static int minimumDifference(int[] nums, int k) {
        // after sorting, the best k scores are always k neighbours
        Arrays.sort(nums);
        // first window: nums[0..k-1]
        int min = nums[0];
        int max = nums[k - 1];
        int minDiff = max - min;
        int diff;
        // slide the window one step at a time: it now covers nums[i-k+1 .. i]
        for (int i = k; i < nums.length; i++) {
            min = nums[i - k + 1];
            max = nums[i];
            diff = max - min;
            if(minDiff > diff){
                minDiff = diff;
            }
        }
        return minDiff;
    }
    public static void main(String[] args) {
        // Example 2 - the returned value would be 2 (scores 7 and 9), but it is not printed
        int[] nums = {9,4,1,7};
        int k = 2;
        minimumDifference(nums, k);
    }

}

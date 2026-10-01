package Practice.Arrays;

// ============================================================================
// LC 2016. Maximum Difference Between Increasing Elements   [Easy]
// https://leetcode.com/problems/maximum-difference-between-increasing-elements/
// Topics: Array
// ============================================================================
//
// PROBLEM
//   Among all pairs of positions i < j with nums[i] < nums[j] (the later value must be
//   strictly bigger), find the largest gap nums[j] - nums[i]. If no such pair exists,
//   return -1.
//
// EXAMPLES
//   nums = [7,1,5,4]   ->  4    i=1, j=2: 5 - 1. (7 - 1 = 6 does not count: 7 is before 1.)
//   nums = [9,4,3,2]   -> -1    the array only goes down, so no pair qualifies
//   nums = [1,5,2,10]  ->  9    i=0, j=3: 10 - 1
//
// CONSTRAINTS
//   n == nums.length,   2 <= n <= 1000,   1 <= nums[i] <= 10^9
//
// INTUITION
//   For a fixed j, the best partner on its left is the SMALLEST value before it. So walk
//   left to right, remember the smallest value seen so far, and at each new element ask
//   "how far above that minimum am I?". The best answer over all j is the answer. This is
//   the same one-pass idea as the best time to buy and sell stock.
//
// APPROACH (as implemented below)
//   1. min = nums[0] (smallest value in the prefix), maxDiff = -1 (nothing found yet).
//   2. For i = 0 .. n-2:
//        min = Math.min(min, nums[i])            <- smallest of nums[0..i]
//        maxDiff = Math.max(maxDiff, nums[i+1] - min)   <- nums[i+1] plays the role of j
//   3. Return -1 if maxDiff is 0, otherwise maxDiff.
//
// DRY RUN   nums = [7,1,5,4]
//   start  min = 7, maxDiff = -1
//   i=0    min = 7;  candidate 1 - 7 = -6  -> maxDiff stays -1
//   i=1    min = 1;  candidate 5 - 1 = 4   -> maxDiff = 4
//   i=2    min = 1;  candidate 4 - 1 = 3   -> maxDiff stays 4
//   return 4
//
// COMPLEXITY
//   Time : O(n) - one pass
//   Space: O(1) - two integers
//
// GOTCHAS
//   - Equal values: nums[i+1] == min gives a difference of 0, which is NOT a valid pair
//     (the rule is nums[i] < nums[j]). That is why a final maxDiff of 0 is turned into -1,
//     e.g. [5,5] -> -1.
//   - A negative candidate (nums[i+1] below the min) can never beat the starting -1, so it
//     is ignored automatically.
//   - Differences stay below 10^9, so int is big enough.
//
// RELATED
//   - LC 121 Best Time to Buy and Sell Stock - the same running-minimum idea, but it
//     returns 0 instead of -1 when no profit is possible
//     (LC121BestTimeToBuyAndSellStock.java in this folder).
//   - LC 2078 Two Furthest Houses With Different Colors - also picks a pair of positions
//     under a condition (different colors) to maximise a value (the distance).
// ============================================================================
public class LC2016MaximumDifferenceBetweenIncreasingElements {
    public static void main(String[] args) {
        // 100 values that never increase (with repeats such as 907,907 and 824,824,824).
        // No valid pair exists, so this prints -1. The equal neighbours produce
        // differences of 0, which the final "maxDiff == 0" check turns into -1.
        int[] nums = {999,997,980,976,948,940,938,928,924,917,907,907,881,878,864,862,859,857,848,840,824,824,824,805,802,798,788,777,775,766,755,748,735,732,727,705,700,697,693,679,676,644,634,624,599,596,588,583,562,558,553,539,537,536,509,491,485,483,454,449,438,425,403,368,345,327,287,285,270,263,255,248,235,234,224,221,201,189,187,183,179,168,155,153,150,144,107,102,102,87,80,57,55,49,48,45,26,26,23,15};
        System.out.println(maximumDifference(nums));
    }

    // Returns the largest nums[j] - nums[i] with i < j and nums[i] < nums[j], or -1 if none.
    public static int maximumDifference(int[] nums) {
        int n = nums.length;
        // min = smallest value seen so far; maxDiff = best valid gap so far (-1 = none yet)
        int min = nums[0];
        int maxDiff = -1;
        for (int i = 0; i < n - 1; i++) {
            // include nums[i] in the prefix minimum, then treat nums[i+1] as the later element
            min = Math.min(min, nums[i]);
            maxDiff = Math.max(maxDiff, nums[i+1] - min);
        }
        // a gap of 0 only comes from equal values, which is not a valid pair
        return maxDiff == 0 ? -1 : maxDiff;
    }
}

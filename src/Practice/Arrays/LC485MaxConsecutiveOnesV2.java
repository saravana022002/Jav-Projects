package Practice.Arrays;

// ============================================================================
// LC 485. Max Consecutive Ones   [Easy]
// https://leetcode.com/problems/max-consecutive-ones/
// Topics: Array
// ============================================================================
//
// PROBLEM
//   nums contains only 0s and 1s. Return the length of the longest run of 1s that sit
//   next to each other.
//
// EXAMPLES
//   nums = [1,1,0,1,1,1]  ->  3    the last three 1s (the first run has only two)
//   nums = [1,0,1,1,0,1]  ->  2    the middle run
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   nums[i] is 0 or 1
//
// INTUITION
//   Only a 0 can break a run of 1s. So sweep once from left to right while keeping
//   currentMax, the length of the run that ends at the current element: a 1 makes it one
//   longer, a 0 sends it back to 0. Every time a run gets longer, compare it with the best
//   run seen so far (max). Because the comparison happens right when the run grows, a run
//   that ends at the end of the array is counted too - no extra step is needed afterwards.
//
// APPROACH (as implemented below)
//   1. max = 0 (longest run so far), currentMax = 0 (run ending at the current element).
//   2. For each num: if num != 1 (a zero), currentMax = 0 - the run is broken.
//   3. Otherwise currentMax++ and max = Math.max(max, currentMax).
//   4. Return max.
//
// DRY RUN   nums = [1,1,0,1,1,1]   (what main() runs)
//   num   currentMax   max
//    1        1         1
//    1        2         2
//    0        0         2       <- the run of two is over, max keeps it
//    1        1         2
//    1        2         2
//    1        3         3       -> returns 3
//
// COMPLEXITY
//   Time : O(n) - one pass
//   Space: O(1) - two integers
//
// GOTCHAS
//   - max is updated only in the branch where a run grows, so an all-zero array never
//     touches it and the answer stays 0.
//   - The test is num != 1 (not num == 0): any value other than 1 breaks the run. For this
//     problem the two are the same, since nums holds only 0s and 1s.
//
// RELATED
//   - LC 487 Max Consecutive Ones II - the same, but you may flip one 0 to a 1.
//   - LC 1004 Max Consecutive Ones III - the same, but you may flip up to k zeros (sliding
//     window).
//
// SEE ALSO
//   LC485MaxConsecutiveOnesV1.java - the same sweep, but it stores the best value at the
//   start of each iteration (count = -1 then count++ for a zero) and needs a final update
//   after the loop
//
// STATUS
//   Correct, but main() calls findMaxConsecutiveOnes without printing the result
//   (it returns 3 for the example in main()).
// ============================================================================
public class LC485MaxConsecutiveOnesV2 {
    public static void main(String[] args) {
        // Example 1 - the result is not printed; it is 3
        int[] nums = {1,1,0,1,1,1};
        findMaxConsecutiveOnes(nums);
    }

    // Returns the length of the longest run of consecutive 1s.
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        // length of the run of 1s that ends at the current element
        int currentMax = 0;
        for (int num : nums) {
            if (num != 1) {
                // a zero breaks the run
                currentMax = 0;
            } else {
                // the run grew by one: check whether it is the best so far
                currentMax++;
                max = Math.max(max, currentMax);
            }
        }
        return max;
    }
}

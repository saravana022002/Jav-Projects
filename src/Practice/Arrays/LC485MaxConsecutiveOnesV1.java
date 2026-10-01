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
//   Only a 0 can break a run of 1s. So sweep once from left to right while keeping count,
//   the length of the run that is going on; a 0 sends it back to 0. The longest run is the
//   largest value count ever reaches.
//   This version records the best value at the TOP of each iteration, i.e. before looking
//   at nums[i], so what it records is the run that ended at index i - 1.
//
// APPROACH (as implemented below)
//   1. count = 0 (length of the current run), max = Integer.MIN_VALUE (nothing recorded
//      yet), i = 0.
//   2. For each index i: first max = Math.max(count, max) - this stores the run that has
//      just ended (or is still going) before nums[i] is looked at.
//   3. If nums[i] == 0, set count = -1. Then count++ runs in every case: a 1 makes the run
//      one longer, a 0 turns the -1 into 0 (a fresh start). Then i++.
//   4. After the loop: max = Math.max(count, max) once more, because a run that reaches the
//      end of the array was never stored inside the loop. Return max.
//
// DRY RUN   nums = [1,1,0,1,1,1]   (what main() runs)
//   i   nums[i]   max after the update at the top   count after the iteration
//   0      1                 0                              1
//   1      1                 1                              2
//   2      0                 2                              -1 -> 0
//   3      1                 2                              1
//   4      1                 2                              2
//   5      1                 2                              3
//   after the loop: max = Math.max(3, 2) = 3, so it returns 3.
//
// COMPLEXITY
//   Time : O(n) - one pass
//   Space: O(1) - three integers
//
// GOTCHAS
//   - The update after the loop is essential. Inside the loop a run is stored only when the
//     NEXT iteration starts, so a run ending at the last index would be lost: [1,1,1] would
//     return 2 instead of 3.
//   - count = -1 followed by count++ is just a roundabout way of writing count = 0 for a zero.
//   - max starts at Integer.MIN_VALUE, which is harmless: count >= 0 replaces it on the first
//     iteration. An array of only zeros returns 0.
//
// RELATED
//   - LC 487 Max Consecutive Ones II - the same, but you may flip one 0 to a 1.
//   - LC 1004 Max Consecutive Ones III - the same, but you may flip up to k zeros (sliding
//     window).
//
// SEE ALSO
//   LC485MaxConsecutiveOnesV2.java - the same sweep, with a plain reset to 0 and the maximum
//   updated only when a 1 is seen
// ============================================================================
public class LC485MaxConsecutiveOnesV1 {
    public static void main(String[] args) {
        // Example 1 - prints 3
        int[] nums = {1,1,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }
    // Returns the length of the longest run of consecutive 1s.
    public static int findMaxConsecutiveOnes(int[] nums) {
        int i = 0;
        // count = length of the run of 1s ending at the last index looked at
        int count = 0;
        int max = Integer.MIN_VALUE;
        while (i < nums.length){
            // store the run that ended just before nums[i]
            max = Math.max(count, max);
            if(nums[i] == 0){
                // a zero breaks the run: -1 here, then count++ below makes it 0
                count = -1;
            }
            count++;
            i++;
        }
        // the last run is never stored inside the loop, so store it now
        max = Math.max(count, max);
        return max;
    }
}

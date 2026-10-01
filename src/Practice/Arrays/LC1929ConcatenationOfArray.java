package Practice.Arrays;

import java.util.Arrays;

// ============================================================================
// LC 1929. Concatenation of Array   [Easy]
// https://leetcode.com/problems/concatenation-of-array/
// Topics: Array, Simulation
// ============================================================================
//
// PROBLEM
//   Given an array nums of length n, build a new array ans of length 2n that is nums written
//   twice in a row: ans[i] and ans[i + n] both equal nums[i], for every i from 0 to n-1.
//
// EXAMPLES
//   nums = [1,2,1]    ->  [1,2,1,1,2,1]
//   nums = [1,3,2,1]  ->  [1,3,2,1,1,3,2,1]     the whole array, then the whole array again
//
// CONSTRAINTS
//   n == nums.length,   1 <= n <= 1000,   1 <= nums[i] <= 1000
//
// INTUITION
//   The second copy of nums starts right after the first one, n slots later. So every value
//   nums[i] has exactly two homes in the answer: slot i and slot i + n. One loop over i
//   can fill both homes at once.
//
// APPROACH (as implemented below)
//   1. length = nums.length; ans = new int[2 * length] (all zeros at the start).
//   2. For i = 0 .. length-1: ans[i] = nums[i] and ans[length + i] = nums[i].
//   3. Return ans.
//
// DRY RUN   nums = [1,2,1], length = 3
//   start  ans = [0,0,0,0,0,0]
//   i=0    ans[0] = 1, ans[3] = 1   => [1,0,0,1,0,0]
//   i=1    ans[1] = 2, ans[4] = 2   => [1,2,0,1,2,0]
//   i=2    ans[2] = 1, ans[5] = 1   => [1,2,1,1,2,1]
//
// COMPLEXITY
//   Time : O(n) - one loop, two writes per iteration
//   Space: O(n) - the 2n-long answer array, which is the output itself; nothing else is used
//
// GOTCHAS
//   - The result must be a NEW array of length 2n - an int[] cannot be grown in place.
//   - The offset of the second copy is n (= nums.length), not 2n.
//
// RELATED
//   - LC 1470 Shuffle the Array - also builds a 2n-long array by mapping indices i and i + n
//     (LC1470ShuffleTheArray.java in this folder).
// ============================================================================
public class LC1929ConcatenationOfArray {
    public static void main(String[] args) {
        // Example 1 - prints [1, 2, 1, 1, 2, 1]
        int[] nums = {1,2,1};
        System.out.println(Arrays.toString(getConcatenation(nums)));
    }


    // Returns nums followed by a second copy of nums.
    public static int[] getConcatenation(int[] nums) {
        int length = nums.length;
        // the answer has room for both copies
        int[] ans = new int[2 * length];
        for (int i = 0; i < length; i++) {
            // nums[i] goes into the first copy (slot i) and the second copy (slot length + i)
            ans[i] = nums[i];
            ans[length + i] = nums[i];
        }
        return ans;
    }
}

package Practice.HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ============================================================================
// LC 3471. Find the Largest Almost Missing Integer   [Easy]
// https://leetcode.com/problems/find-the-largest-almost-missing-integer/
// Topics: Array, Hash Table
// ============================================================================
//
// PROBLEM
//   nums is an int array and k a window size; a "window" is a contiguous stretch of k
//   elements. Call a value ALMOST MISSING when exactly one window contains it. Find the
//   biggest such value, or answer -1 if no value qualifies.
//
// EXAMPLES
//   nums = [3,9,2,1,7],   k = 3  ->  7    windows [3,9,2] [9,2,1] [2,1,7]; 3 and 7 lie in one
//                                         window each, 7 is the larger
//   nums = [3,9,7,2,1,7], k = 4  ->  3    only 3 sits in a single window (7 is in three)
//   nums = [0,0],         k = 1  ->  -1   windows [0] and [0]: 0 is in two windows
//
// CONSTRAINTS
//   1 <= nums.length <= 50,   0 <= nums[i] <= 50,   1 <= k <= nums.length
//
// INTUITION
//   Count how many windows can contain a position, then split on k (n = nums.length):
//   - k == n : there is one window, the whole array, so EVERY value is in exactly one window.
//              The answer is the maximum of nums.
//   - k == 1 : a window is a single element, so x is in exactly one window iff x occurs
//              exactly once in nums. The answer is the largest value that occurs once.
//   - 1 < k < n : every position except the two ends lies in at least two windows. Only
//              nums[0] (window 0 only) and nums[n-1] (last window only) lie in just one. So x
//              qualifies only if it occurs exactly once in nums AND that spot is an end.
//              The answer is the larger qualifying end, or -1.
//
// APPROACH (as implemented below)
//   1. map: value -> how many times it occurs in nums.
//   2. freq1: the set of values whose count is exactly 1.
//   3. k == nums.length -> return the maximum element of nums.
//   4. k == 1 -> return the largest value in freq1, or -1 when freq1 is empty.
//   5. Otherwise look only at the two ends: both in freq1 -> the larger of them; exactly one
//      in freq1 -> that one; neither -> -1.
//
// DRY RUN   nums = [11,12,5,4,2,8,11,8,11,7], k = 4   (what main() runs)
//   map   = {11=3, 12=1, 5=1, 4=1, 2=1, 8=2, 7=1}
//   freq1 = {12, 5, 4, 2, 7}
//   k = 4 is neither 10 (= n) nor 1 -> end case
//   nums[0] = 11 is not in freq1 (it occurs 3 times); nums[9] = 7 is in freq1 -> return 7
//
// COMPLEXITY
//   Time : O(n) - counting pass, one scan of the map, and at most one more scan in the
//          k == n / k == 1 cases (n <= 50 anyway)
//   Space: O(n) - the map and the set hold at most n values
//
// GOTCHAS
//   - k == n must be tested first: then duplicates do not matter ([5,5], k = 2 gives 5).
//   - An end value that also occurs elsewhere is NOT almost missing: 11 is at index 0 but
//     also at 6 and 8, so it lies in several windows.
//   - When both ends are in freq1 they are different numbers (a number that occurs once cannot
//     be at both ends), so the nums[0] != nums[n-1] check always passes and its
//     "return -1" is never reached.
//   - i.getValue().equals(1) compares Integer objects by value; == would be unreliable.
//
// STATUS
//   Correct (checked against a brute force over all windows on random inputs).
// ============================================================================
public class LC3471FindTheLargestAlmostMissingInteger {
    public static void main(String[] args) {
        // neither k == n nor k == 1, so only the two ends can qualify. Expected output: 7
        int k = 4;
        int[] nums = {11,12,5,4,2,8,11,8,11,7};
        System.out.println(largestInteger(nums, k));

    }
    // Case split on k: whole array / single elements / only the two ends can lie in one window.
    public static int largestInteger(int[] nums, int k) {
        // freq1: values that occur exactly once in nums
        Set<Integer> freq1 = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i  : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> i : map.entrySet()){
            if(i.getValue().equals(1)){
                freq1.add(i.getKey());
            }
        }
        if(k == nums.length){
            // k == n: the single window is the whole array, so the largest element wins
            int res = Integer.MIN_VALUE;
            for (Integer freq : nums) {
                res = Math.max(res, freq);
            }
            return res;
        }else if(k == 1) {
            // k == 1: windows are single elements, so the largest value that occurs once wins
            if(!freq1.isEmpty()) {
                int c = Integer.MIN_VALUE;
                for (Integer freq : freq1) {
                   c = Math.max(c, freq);
                }
                return c;
            }else {
               return -1;
            }
        }else {
            // 1 < k < n: only nums[0] and nums[n-1] lie in a single window, and only if unique
            if(freq1.contains(nums[0]) && freq1.contains(nums[nums.length - 1])){
                // both ends qualify (and differ, so the else below is never reached)
                if(nums[0] != nums[nums.length - 1]){
                    return Math.max(nums[0], nums[nums.length - 1]);
                }else {
                    return -1;
                }
            }else {
                if(freq1.contains(nums[0])){
                    return nums[0];
                }else if (freq1.contains(nums[nums.length - 1])){
                    return nums[nums.length - 1];
                }else {
                    return -1;
                }
            }

        }
    }
}

package Practice.PrefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 560. Subarray Sum Equals K   [Medium]
// https://leetcode.com/problems/subarray-sum-equals-k/
// Topics: Array, Hash Table, Prefix Sum
// ============================================================================
//
// PROBLEM
//   Given an int array nums and an int k, return how many subarrays (contiguous, non-empty)
//   have elements that add up to exactly k. Subarrays are counted by position, so equal
//   looking ones at different places each count. Values may be negative.
//
// EXAMPLES
//   nums = [1,1,1], k = 2  ->  2    [1,1] at indices 0-1 and again at indices 1-2
//   nums = [1,2,3], k = 3  ->  2    [1,2] and [3]
//
// CONSTRAINTS
//   1 <= nums.length <= 2 * 10^4,   -1000 <= nums[i] <= 1000,   -10^7 <= k <= 10^7
//
// INTUITION
//   Let P[i] be the sum of the first i elements (P[0] = 0, the empty prefix). The subarray
//   that ends at position i-1 and starts at position j sums to P[i] - P[j], so it equals k
//   exactly when P[j] == P[i] - k. Walk left to right; for every new prefix sum count how many
//   EARLIER prefix sums equal (current - k) - each one marks the start of a matching subarray
//   ending here. A HashMap of prefix sum -> how often it occurred answers that in O(1).
//   Negative values rule out a two-pointer window (the sum is no longer monotone), but
//   prefix sums do not care about signs.
//
// APPROACH (as implemented below)
//   1. prefixSumFreq: prefix sum -> how many times it occurred; seeded with {0=1} for the
//      empty prefix.
//   2. For each i: sum = sum + nums[0]. This is where the code goes wrong: the prefix sum
//      must add nums[i]; adding nums[0] every time only works when all elements are equal.
//   3. count += prefixSumFreq.getOrDefault(sum - k, 0): earlier prefixes equal to sum - k.
//   4. prefixSumFreq.put(sum, prefixSumFreq.getOrDefault(sum, 0) + 1).
//   5. Return count.
//
// DRY RUN   (main() runs nums = [1,1,1], k = 2)
//   All elements are equal, so nums[0] == nums[i] and the slip stays hidden:
//   start  freq={0=1}  sum=0  count=0
//   i=0: sum=1, look up -1 -> 0 hits         freq={0=1, 1=1}
//   i=1: sum=2, look up 0  -> 1 hit          count=1  freq={0=1, 1=1, 2=1}
//   i=2: sum=3, look up 1  -> 1 hit          count=2
//   return 2 (correct, by coincidence)
//   Example 2, nums = [1,2,3], k = 3: the code's "prefix sums" are 1, 2, 3 instead of
//   1, 3, 6; only i=2 finds a hit (sum 3 - 3 = 0) -> returns 1, but the answer is 2.
//
// COMPLEXITY
//   Time : O(n) - one pass, O(1) average per HashMap operation
//   Space: O(n) - up to n + 1 distinct prefix sums
//
// GOTCHAS
//   - The seed {0=1} is the empty prefix, so subarrays starting at index 0 are counted.
//   - Look up BEFORE storing the current prefix; otherwise, for k = 0, a prefix would match
//     itself and an empty subarray would be counted.
//   - The map stores frequencies, not just "seen": negatives and zeros make the same prefix
//     sum occur many times, and each occurrence is a different subarray start.
//
// STATUS
//   Known issue: "sum = sum + nums[0]" adds the FIRST element on every step instead of
//   nums[i], so the prefix sums are wrong unless all elements are equal. Example 2
//   ([1,2,3], k = 3) returns 1 instead of 2; on random small arrays most answers were wrong.
//   main()'s [1,1,1] input hides the bug. The code is left unchanged on purpose.
//   The import java.util.ArrayList is unused.
//
// RELATED
//   - LC 1 Two Sum - the same "look up what you need in a map" pattern
//     (solved in LC1TwoSumHashMap.java in the HashMap folder).
//   - LC 974 Subarray Sums Divisible by K - counting equal prefix remainders
//     (src/Leetcode/PrefixSum2/LC974SubArraysSumDivByK.java).
//
// SEE ALSO
//   LC560SubarraySumEqualsKV1.java - the correct version (adds every element, long keys)
// ============================================================================
public class LC560SubarraySumEqualsKV2 {
    public static void main(String[] args) {
        // Example 1 - prints 2 (correct, but only because all elements are equal; see STATUS)
        int[] nums = {1,1,1};
        int k = 2;
        System.out.println(optimalSolution(nums, k));
    }

    // Intended: count subarrays with sum k via prefix sums + frequency map.
    // KNOWN ISSUE: the running sum adds nums[0] instead of nums[i] - see STATUS.
    public static int optimalSolution(int[] nums, int k){
        // prefix sum -> how many times it has occurred; the empty prefix occurred once
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        prefixSumFreq.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // BUG: should be nums[i]
            sum = sum + nums[0];
            // each earlier prefix equal to sum - k starts a subarray ending at i
            count += prefixSumFreq.getOrDefault(sum - k, 0);
            prefixSumFreq.put(sum, prefixSumFreq.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

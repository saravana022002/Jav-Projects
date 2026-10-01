package Practice.PrefixSum;

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
//   1. freqMap: prefix sum -> how many times it occurred; seeded with {0=1} for the empty
//      prefix.
//   2. For each num: count += num. (count is the running prefix sum here, and res is the
//      answer - the names are easy to mix up.)
//   3. res += freqMap.getOrDefault(count - k, 0): earlier prefixes that start a subarray
//      with sum k ending at this element.
//   4. freqMap.put(count, freqMap.getOrDefault(count, 0) + 1), then continue.
//   5. Return res.
//
// DRY RUN   nums = [1,-1,0], k = 0   (what main() runs)
//   start     freqMap={0=1}  prefix=0  res=0
//   num=1 :   prefix=1, look up 1 - 0 = 1 -> 0 hits          freqMap={0=1, 1=1}
//   num=-1:   prefix=0, look up 0 -> 1 hit  ([1,-1])         res=1  freqMap={0=2, 1=1}
//   num=0 :   prefix=0, look up 0 -> 2 hits ([1,-1,0], [0])  res=3  freqMap={0=3, 1=1}
//   return 3
//
// COMPLEXITY
//   Time : O(n) - one pass, O(1) average per HashMap operation
//   Space: O(n) - up to n + 1 distinct prefix sums
//
// GOTCHAS
//   - The seed {0=1} is the empty prefix. Without it, subarrays that start at index 0
//     (prefix sum equal to k) would be missed.
//   - Look up BEFORE storing the current prefix. Otherwise, for k = 0, a prefix would match
//     itself and an empty subarray would be counted.
//   - The map stores FREQUENCIES, not just "seen": with negatives and zeros the same prefix
//     sum can occur many times, and every occurrence is a different subarray start.
//   - The running sum is a long (Long keys). With these limits |sum| <= 2 * 10^7, so an int
//     would also fit, and the answer is at most n(n+1)/2 = 200010000, which fits an int.
//
// STATUS
//   Correct (checked against a brute force on random arrays).
//
// RELATED
//   - LC 1 Two Sum - the same "look up what you need in a map" pattern
//     (solved in LC1TwoSumHashMap.java in the HashMap folder).
//   - LC 523 Continuous Subarray Sum - prefix sums + map, with a modulo twist
//     (src/Leetcode/PrefixSum2/LC523ContinuousSubarraySum.java).
//   - LC 974 Subarray Sums Divisible by K - counting equal prefix remainders
//     (src/Leetcode/PrefixSum2/LC974SubArraysSumDivByK.java).
//
// SEE ALSO
//   LC560SubarraySumEqualsKV2.java - the same idea with int keys (that file has a bug in
//   its running sum, see its STATUS)
// ============================================================================
public class LC560SubarraySumEqualsKV1 {
    public static void main(String[] args) {
        // Three subarrays sum to 0: [1,-1], [0] and [1,-1,0]. Expected output: 3
        int[] nums = {1,-1,0};
        int k = 0;
        System.out.println(subarraySum(nums, k));
    }
    // Counts subarrays with sum k: for every prefix, add the earlier prefixes equal to prefix - k.
    public static int subarraySum(int[] nums, int k) {
        // prefix sum -> how many times it has occurred; the empty prefix (sum 0) occurred once
        Map<Long, Integer> freqMap = new HashMap<>();
        freqMap.put(0L, 1);
        // running prefix sum (despite the name)
        long count = 0;
        // number of subarrays found so far (the answer)
        int res = 0;
        for (int num : nums) {
            count += num;
            // each earlier prefix equal to count - k starts a subarray ending at this element
            res += freqMap.getOrDefault(count - k, 0);
            freqMap.put(count, freqMap.getOrDefault(count, 0) + 1);
        }
        return res;
    }


}

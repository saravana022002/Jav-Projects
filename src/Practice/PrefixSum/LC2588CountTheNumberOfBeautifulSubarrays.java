package Practice.PrefixSum;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 2588. Count the Number of Beautiful Subarrays   [Medium]
// https://leetcode.com/problems/count-the-number-of-beautiful-subarrays/
// Topics: Array, Hash Table, Bit Manipulation, Prefix Sum
// ============================================================================
//
// PROBLEM
//   One operation: choose two distinct positions and a bit k that is set in both of their
//   numbers, then subtract 2^k from each (which clears bit k in both). A subarray is
//   BEAUTIFUL if some sequence of operations (possibly none) turns all its elements into 0;
//   a subarray that is already all zeros counts. Return how many non-empty subarrays of nums
//   are beautiful.
//
// EXAMPLES
//   nums = [4,3,1,2,4]  ->  2    [3,1,2] and the whole array (each has XOR 0)
//   nums = [1,10,4]     ->  0    no subarray has XOR 0
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^6
//
// INTUITION
//   An operation clears the SAME bit in two numbers, so for every bit the number of elements
//   that have it changes by 0 or 2 - its parity never changes. To end at all zeros every bit
//   must therefore occur an even number of times, which is exactly "the XOR of the subarray is
//   0". Conversely, if every bit occurs an even number of times, the holders of each bit can
//   be paired off and cleared two at a time. (This reduction is the official hint.)
//   So: count the subarrays whose XOR is 0. With prefix XORs P (P[0] = 0 for the empty
//   prefix), the XOR of elements j..i-1 is P[i] ^ P[j], which is 0 exactly when P[i] == P[j].
//   Hence for each new prefix, add the number of EARLIER prefixes with the same value.
//
// APPROACH (as implemented below)
//   1. k = 0: the XOR value we look for. (It is the general "subarrays with XOR k" template
//      with k fixed at 0; sum ^ k is just sum.)
//   2. prefixXORFreq: prefix XOR -> how many prefixes have had it. Start with {0=1}, the
//      empty prefix.
//   3. For each nums[i]: sum = sum ^ nums[i] (running prefix XOR);
//      count += prefixXORFreq.getOrDefault(sum ^ k, 0); then prefixXORFreq[sum] += 1.
//   4. Return count.
//
// DRY RUN   nums = [4,3,1,2,4]   (what main() runs)
//   start          sum=0  freq={0=1}  count=0
//   i=0 (4): sum = 4     no earlier 4 -> +0   freq={0=1, 4=1}
//   i=1 (3): sum = 7     +0                   freq={0=1, 4=1, 7=1}
//   i=2 (1): sum = 6     +0                   freq={0=1, 4=1, 7=1, 6=1}
//   i=3 (2): sum = 4     one earlier 4 -> +1  count=1   ([3,1,2])   freq[4]=2
//   i=4 (4): sum = 0     one earlier 0 -> +1  count=2   (whole array)
//   return 2
//
// COMPLEXITY
//   Time : O(n) - one pass, O(1) average per HashMap operation
//   Space: O(n) - up to n + 1 distinct prefix XORs
//
// GOTCHAS
//   - The seed {0=1} is the empty prefix. Without it, subarrays that start at index 0
//     (the whole array above) would never be counted.
//   - count is a long: for n = 10^5 zeros every subarray is beautiful, n(n+1)/2 = 5000050000,
//     which does not fit in an int.
//   - Read the map BEFORE storing the current prefix, otherwise a prefix would match itself
//     (an empty subarray).
//
// STATUS
//   Correct (also checked against a brute force that really applies the operations). The
//   method is named optimalSolution; LeetCode's own name is beautifulSubarrays.
//
// RELATED
//   - LC 560 Subarray Sum Equals K - the same "count earlier equal prefixes" idea with sums
//     instead of XOR (solved in LC560SubarraySumEqualsKV1.java and V2 in this folder).
//   - LC 1829 Maximum XOR for Each Query - another problem built on prefix XOR.
// ============================================================================
public class LC2588CountTheNumberOfBeautifulSubarrays {
    public static void main(String[] args) {
        // Example 1. Expected output: 2
        int[] nums = {4,3,1,2,4};
        System.out.println(optimalSolution(nums));
    }
    // Counts subarrays whose XOR is 0 (= beautiful subarrays) using prefix XORs.
    public static long optimalSolution(int[] nums){
        // target XOR; 0 here. sum ^ k below is therefore just sum
        long k = 0;
        // prefix XOR -> number of prefixes seen so far with that value
        Map<Long, Long> prefixXORFreq = new HashMap<>();
        // the empty prefix has XOR 0
        prefixXORFreq.put(0L, 1L);
        // running prefix XOR (named sum, although it is an XOR)
        long sum = 0;
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum ^ nums[i];
            // every earlier prefix equal to this one closes a zero-XOR subarray
            count += prefixXORFreq.getOrDefault(sum ^ k, 0L);
            prefixXORFreq.put(sum, prefixXORFreq.getOrDefault(sum, 0L) + 1L);
        }
        return count;
    }
}

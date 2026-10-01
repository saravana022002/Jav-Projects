package Practice.HashMap;

import java.util.*;

// ============================================================================
// LC 594. Longest Harmonious Subsequence   [Easy]
// https://leetcode.com/problems/longest-harmonious-subsequence/
// Topics: Array, Hash Table, Sliding Window, Sorting, Counting
// ============================================================================
//
// PROBLEM
//   An array is harmonious when its largest value minus its smallest value is exactly 1.
//   Given nums, return the length of the longest harmonious SUBSEQUENCE (keep any elements,
//   in their original order, drop the rest). Return 0 if no harmonious subsequence exists.
//
// EXAMPLES
//   nums = [1,3,2,2,5,2,3,7]  ->  5    [3,2,2,2,3]: three 2s and two 3s
//   nums = [1,2,3,4]          ->  2    [1,2], [2,3] or [3,4]
//   nums = [1,1,1,1]          ->  0    max - min is 0, never 1
//
// CONSTRAINTS
//   1 <= nums.length <= 2 * 10^4,   -10^9 <= nums[i] <= 10^9
//
// INTUITION
//   max - min == 1 means the subsequence contains exactly two distinct values, v and v + 1,
//   and uses both. A subsequence may skip anything, so the positions are irrelevant: for a
//   chosen v we can take EVERY copy of v and of v + 1. The best for that v is therefore
//   count(v) + count(v + 1), valid only if both values occur. The answer is the maximum over
//   all v (0 if no v qualifies). The tags also list Sorting and Sliding Window: after sorting,
//   the same two neighbouring values sit side by side - this version uses counts instead.
//
// APPROACH (as implemented below)
//   1. freqMap: value -> number of occurrences in nums.
//   2. longest = 0.
//   3. For every key num of freqMap: if freqMap also contains num + 1,
//      longest = max(longest, freqMap.get(num) + freqMap.get(num + 1)).
//   4. Return longest.
//
// DRY RUN   nums = [1,2,3,4]   (what main() runs)
//   freqMap = {1=1, 2=1, 3=1, 4=1}
//   num=1: 2 present -> 1 + 1 = 2    longest = 2
//   num=2: 3 present -> 1 + 1 = 2    longest = 2
//   num=3: 4 present -> 1 + 1 = 2    longest = 2
//   num=4: 5 absent  -> skipped
//   return 2
//   (Example 1, [1,3,2,2,5,2,3,7]: freq {1=1, 2=3, 3=2, 5=1, 7=1}; 1 pairs with 2 for 4,
//   2 pairs with 3 for 5, the rest have no neighbour -> 5.)
//
// COMPLEXITY
//   Time : O(n) - one counting pass and one pass over at most n distinct keys, each with
//          O(1) average HashMap work
//   Space: O(n) - the map holds one entry per distinct value
//
// GOTCHAS
//   - Both v and v + 1 must be present. Equal values alone ([1,1,1,1]) have difference 0,
//     so the containsKey check is what produces the 0 there.
//   - Only num + 1 is checked, never num - 1: every adjacent pair is reached once, from its
//     smaller value.
//   - num + 1 cannot overflow because |nums[i]| <= 10^9.
//
// STATUS
//   Correct (checked against an all-subsets brute force on random inputs).
//
// RELATED
//   - LC 128 Longest Consecutive Sequence - the same "is value + 1 present?" hash lookup
//     (solved in LC128LongestConsecutiveSequenceV1.java and V2).
// ============================================================================
public class LC594LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        // Example 2 - the best pair of neighbouring values has length 2. Expected output: 2
        int[] nums = {1,2,3,4};
        System.out.println(findLHS(nums));
    }
    // Best count(v) + count(v + 1) over all values v for which both occur; 0 if none.
    public static int findLHS(int[] nums) {
        // value -> how many times it occurs
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : freqMap.keySet()){
            // num and num + 1 both occur: every copy of each can go into one subsequence
            if(freqMap.containsKey(num + 1)){
                longest = Math.max(longest, freqMap.get(num) + freqMap.get(num + 1));
            }
        }
        return longest;

    }
}

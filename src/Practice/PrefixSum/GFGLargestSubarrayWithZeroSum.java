package Practice.PrefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// ============================================================================
// GFG - Largest Subarray with 0 Sum   [Medium]
// https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1
// Topics: Prefix Sum, Hash Map
// ============================================================================
//
// PROBLEM
//   arr holds positive and negative integers. Among all subarrays (runs of consecutive
//   elements) whose elements add up to 0, return the size of the biggest one, or 0 if no
//   subarray adds up to 0.
//
// EXAMPLES
//   arr = [15,-2,2,-8,1,7,10,23]  ->  5    [-2,2,-8,1,7] sums to 0
//   arr = [2,10,4]                ->  0    no subarray sums to 0
//   arr = [1,0,-4,3,1,0]          ->  5    [0,-4,3,1,0] sums to 0
//
// INTUITION
//   Let P[i] be the prefix sum arr[0] + ... + arr[i]. The elements after position j up to i
//   add to P[i] - P[j], which is 0 exactly when P[i] == P[j]. So the answer is the widest gap
//   between two equal prefix sums. Remember only the FIRST index where each prefix sum
//   occurred: when the same sum shows up again at i, the gap i - first index is the longest
//   zero-sum subarray ending at i. A prefix sum that is itself 0 means arr[0..i] sums to 0.
//
// APPROACH (as implemented below - optimalSolution(int[]), the one main() runs)
//   1. sum = 0, maxLength = 0, sumArr = empty map: prefix sum -> first index where it occurred.
//   2. For each i: sum = sum + nums[i], then
//        - sum == 0            -> nums[0..i] adds to 0: maxLength = i + 1
//        - sum already in map  -> with j = sumArr.get(sum), nums[j+1..i] adds to 0:
//                                 maxLength = max(maxLength, i - j); the stored j is kept
//        - otherwise           -> sumArr.put(sum, i)
//   3. Return maxLength.
//
// DRY RUN   arr = [15,-2,2,-8,1,7,10,23]   (example 1)
//   i  nums[i]  sum   what happens                          maxLength
//   0    15      15   first time, store 15 -> 0                 0
//   1    -2      13   first time, store 13 -> 1                 0
//   2     2      15   seen at 0: gap 2 - 0 = 2                  2
//   3    -8       7   first time, store 7 -> 3                  2
//   4     1       8   first time, store 8 -> 4                  2
//   5     7      15   seen at 0: gap 5 - 0 = 5                  5
//   6    10      25   first time, store                         5
//   7    23      48   first time, store                         5
//   return 5 (indices 1..5 = -2,2,-8,1,7)
//   main() runs a 12-element array whose prefix sum is 0 at i = 3, 5, 10 and 11, so the last
//   hit gives maxLength = 12 (the whole array) and it prints 12.
//
// COMPLEXITY
//   Time : O(n) - one pass, O(1) average per HashMap operation
//   Space: O(n) - up to n distinct prefix sums are stored
//
// GOTCHAS
//   - Keep the EARLIEST index of a sum: put() runs only when the sum is absent. Overwriting
//     it would shorten every later gap.
//   - A prefix sum of 0 is never stored; the sum == 0 branch covers it (as if 0 were stored
//     at index -1).
//   - A 0 element is itself a zero-sum subarray: it leaves the prefix sum unchanged, so the
//     same sum repeats one index later (gap 1).
//   - The stored index can be 0, which is why the test is "!= null" and not "> 0".
//   - sum is an int: a running total beyond about 2.1 * 10^9 would overflow (use long then).
//
// STATUS
//   Correct (checked against a brute force on random arrays). The file has two overloads with
//   the same algorithm: optimalSolution(int[]), used by main(), and
//   optimalSolution(ArrayList<Integer>).
//
// RELATED
//   - LC 560 Subarray Sum Equals K - counts the subarrays with a given sum, with the same
//     prefix-sum + map idea (solved in LC560SubarraySumEqualsKV1.java and V2 in this folder).
//   - LC 525 Contiguous Array - this problem after mapping each 0 to -1.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet (Arrays Day 4, Largest subarray with
//   0 sum)).
//   Closest LeetCode problem: LC 325 Maximum Size Subarray Sum Equals k (premium) - the same
//   task with k = 0.
// ============================================================================
public class GFGLargestSubarrayWithZeroSum {
    public static void main(String[] args) {
        // The whole array sums to 0, so the answer is its length. Expected output: 12
        int[] nums = {16 ,1 ,13 ,-30 ,18 ,-18 ,14 ,11 ,6 ,3 ,-34 ,0};
        System.out.println(optimalSolution(nums));
    }

    // Longest zero-sum subarray via prefix sums and a map of each sum's first index.
    public static int optimalSolution(int[] nums){
        int sizeArr = nums.length;
        // prefix sum -> first index where it occurred
        Map<Integer, Integer> sumArr = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < sizeArr; i++) {
            sum = sum + nums[i];
            if(sum == 0){
                // nums[0..i] sums to 0: the longest subarray ending at i
                maxLength = i + 1;
            }else {
                if (sumArr.get(sum) != null) {
                    // same prefix sum as at an earlier index: the elements in between sum to 0
                    maxLength = Math.max(maxLength, i - sumArr.get(sum));
                } else {
                    // first time: remember the earliest index only
                    sumArr.put(sum, i);
                }
            }
        }
        return maxLength;
    }

    // Same algorithm for the input given as an ArrayList<Integer>.
    public static int optimalSolution(ArrayList<Integer> arr){
        int sizeArr = arr.size();
        Map<Integer, Integer> sumArr = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < sizeArr; i++) {
            sum = sum + arr.get(i);
            if(sum == 0){
                maxLength = i + 1;
            }else {
                if (sumArr.get(sum) != null) {
                    maxLength = Math.max(maxLength, i - sumArr.get(sum));
                } else {
                    sumArr.put(sum, i);
                }
            }
        }
        return maxLength;
    }
}

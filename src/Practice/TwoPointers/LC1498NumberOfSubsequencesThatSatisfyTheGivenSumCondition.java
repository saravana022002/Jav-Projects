package Practice.TwoPointers;

import java.util.Arrays;

// ============================================================================
// LC 1498. Number of Subsequences That Satisfy the Given Sum Condition   [Medium]
// https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
// Topics: Array, Two Pointers, Binary Search, Sorting
// ============================================================================
//
// PROBLEM
//   Count the non-empty subsequences of nums (any choice of elements, identified by their
//   positions, so equal values at different positions count separately) in which
//   smallest element + largest element <= target. Return the count modulo 10^9 + 7.
//
// EXAMPLES
//   nums = [3,5,6,7], target = 9       ->  4    [3], [3,5], [3,6], [3,5,6]
//   nums = [3,3,6,8], target = 10      ->  6    [3], [3], [3,3], [3,6], [3,6], [3,3,6]
//   nums = [2,3,3,4,6,7], target = 12  ->  61   63 non-empty subsequences in all; only [6,7]
//                                               and [7] break the limit
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^6,   1 <= target <= 10^6
//
// INTUITION
//   Only the smallest and the largest element of a subsequence matter, never the order, so
//   sort nums first. In the sorted array a subsequence's minimum is its leftmost element.
//   Fix the minimum at index left, and let right be the farthest index with
//   nums[left] + nums[right] <= target. Then nums[left] together with ANY subset of the
//   indices left+1 .. right is valid (its maximum is at most nums[right]), and nothing beyond
//   right can be used. Each of those right - left elements is either in or out, so that is
//   2^(right - left) subsequences. Every valid subsequence is counted exactly once, under
//   its leftmost element. When left moves right, nums[left] only grows, so the farthest
//   usable right can only move left: two pointers that never back up.
//
// APPROACH (as implemented below)
//   1. mod = 10^9 + 7; sort nums.
//   2. pow[i] = 2^i mod mod for i = 0 .. n-1 (pow[0] = 1, pow[i] = pow[i-1] * 2 % mod).
//   3. left = 0, right = n - 1, ans = 0 (a long).
//   4. While left <= right, sum = nums[left] + nums[right]:
//      - sum <= target : nums[left] can start any subset of (left, right] ->
//                        ans += pow[right - left], then left++
//      - otherwise     : nums[right] is too big for this left and for every later one
//                        (they are larger), so right--
//   5. Return (int) (ans % mod).
//
// DRY RUN   nums = [3,5,6,7], target = 9   (sorted already)   pow = [1,2,4,8]
//   left=0 right=3  3+7 = 10 > 9   -> right = 2
//   left=0 right=2  3+6 =  9 <= 9  -> ans += pow[2] = 4  (the subsets of {5,6} added to 3)
//                                     ans = 4, left = 1
//   left=1 right=2  5+6 = 11 > 9   -> right = 1
//   left=1 right=1  5+5 = 10 > 9   -> right = 0
//   left=1 > right=0 -> stop.   return 4
//
// COMPLEXITY
//   Time : O(n log n) - the sort dominates; the power table and the two-pointer loop are
//          O(n) (every step moves left or right, never back)
//   Space: O(n) - the pow table; the sort is in place, so nums itself gets reordered
//
// GOTCHAS
//   - Subsequences are counted by position: in [3,3,6,8] the two 3s give 6 valid
//     subsequences, not 4.
//   - The left element is forced into the subsequence; 2^(right - left) only counts the
//     choices among the elements AFTER left.
//   - When left == right the term is pow[0] = 1, the one-element subsequence, and it only gets
//     added if 2 * nums[left] <= target.
//   - Overflow: pow[i-1] < mod, so pow[i-1] * 2 is below 2.1 * 10^9 and still fits an int;
//     ans is a long so the running total (up to n terms below mod) cannot overflow before
//     the final % mod.
//
// STATUS
//   The method is correct (checked against a brute force on random small arrays and the three
//   official examples). Arrays.sort reorders the caller's array. The method is called
//   numSubSeq here; LeetCode's template names it numSubseq.
//
// RELATED
//   - LC 167 Two Sum II - Input Array Is Sorted - the same sorted array with two pointers
//     that move inward by comparing a sum with the target.
//   - LC 15 3Sum - sort first, then two pointers (solved in this folder).
// ============================================================================
public class LC1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    public static void main(String[] args) {
        // Example 1. Expected output: 4
        int[] nums = {3,5,6,7};
        int target = 9;
        System.out.println(numSubSeq(nums, target));
    }
    // Counts (mod 10^9 + 7) the subsequences whose min + max <= target
    public static int numSubSeq(int[] nums, int target) {
        int mod = (int) (Math.pow(10, 9) + 7);
        // only min and max matter, so the original order is irrelevant: sort
        Arrays.sort(nums);

        // pow[i] = 2^i mod mod, the number of subsets of i free elements
        int n = nums.length;
        int[] pow = new int[n];
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }

        int left = 0;
        int right = nums.length - 1;
        long ans = 0;
        while (left <= right){
            int sum = nums[left] + nums[right];
            if(sum <= target){
                // nums[left] is the minimum; any subset of (left, right] may join it
                ans = ans + pow[right - left];
                left ++;
            }else {
                // nums[right] is too large for this left and for every larger left
                right --;
            }
        }
        return (int) (ans % mod);
    }
}

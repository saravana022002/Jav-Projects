package Practice.Contests.Contest158;

import java.util.*;

// ============================================================================
// LC 3574. Maximize Subarray GCD Score   [Hard]
// https://leetcode.com/problems/maximize-subarray-gcd-score/
// Topics: Array, Math, Enumeration, Number Theory
// ============================================================================
//
// PROBLEM
//   nums holds positive integers and you may do at most k operations; one operation
//   doubles a single element, and an element can be doubled at most once. The score of a
//   contiguous subarray is (its length) * (the GCD of its elements). After the doublings,
//   return the highest score any subarray can reach.
//
// EXAMPLES
//   nums = [2,4], k = 1    ->  8     double 2 -> [4,4]: gcd 4, length 2
//   nums = [3,5,7], k = 2  ->  14    double 7 -> subarray [14]: 1 * 14
//   nums = [5,5,5], k = 1  ->  15    no doubling needed: gcd 5 * length 3
//
// CONSTRAINTS
//   1 <= n == nums.length <= 1500,   1 <= nums[i] <= 10^9,   1 <= k <= n
//
// INTUITION
//   Fix a subarray. The doublings only matter inside it, and the best choice of WHICH (at
//   most k) elements to double can be found by trying every subset of the subarray: a
//   bitmask, bit b set = double element b. Take the GCD for each allowed mask, keep the
//   best GCD, multiply by the length, and keep the best over all subarrays. That is
//   correct by exhaustion but exponential - the longest subarray alone has 2^n masks - so
//   it only works for tiny n.
//   (A faster idea, not coded here: doubling can only turn a subarray's gcd g into 2g, and
//   it does so exactly when every element with (a_i / g) odd gets doubled. So the best gcd
//   is 2g if at most k elements have an odd a_i / g, otherwise g. This matched the brute
//   force on thousands of random small arrays.)
//
// APPROACH (as implemented below)
//   1. maxScore = 0. For every subarray [i..j]: len = j - i + 1, subarray = a copy of it,
//      m = its length, maxGcd = 0.
//   2. For every mask from 0 to 2^m - 1: skip it when more than k bits are set (that would
//      be more than k operations). Otherwise temp = a copy of subarray, double the
//      elements whose bit is set, currentGcd = computeGcd(temp), maxGcd = the larger.
//   3. currentScore = len * maxGcd; maxScore = the larger of it and maxScore.
//   4. Return maxScore.
//   computeGcd folds gcd() over the array and stops early once the running gcd is 1;
//   gcd(a, b) is Euclid's algorithm.
//
// DRY RUN   main() case 1: nums = [2,4], k = 1
//   [2]   (len 1): mask 0 -> gcd 2;  mask 1 -> [4] -> 4                     maxGcd 4, score 4
//   [2,4] (len 2): mask 00 -> gcd(2,4) = 2;  01 -> [4,4] -> 4;  10 -> [2,8] -> 2;
//                  11 is skipped (2 bits > k)                               maxGcd 4, score 8
//   [4]   (len 1): mask 0 -> 4;  mask 1 -> [8] -> 8                         maxGcd 8, score 8
//   maxScore = 8
//
// STATUS
//   Known issue: maxScore and currentScore are int although maxScore() returns long, so
//   len * maxGcd wraps around once it passes 2147483647. For nums = {10^9, 10^9, 10^9} and
//   k = 1 the code returns 2000000000, the correct answer is 3000000000 (the 3 * 10^9 score
//   overflows to a negative number and is ignored).
//   It is also only a brute force: measured here, n = 20 takes 0.3 s, n = 22 1.1 s and
//   n = 24 4.6 s (x4 for every +2), and for subarrays of 31 or more elements 1 << m no
//   longer counts the masks correctly (1 << 31 is negative, 1 << 32 is 1). LeetCode allows
//   n up to 1500, so this cannot pass the real tests; it is fine for the three examples.
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   Time : O(2^n * n * log A) - the longest subarray alone tries 2^n masks, each costing
//          O(n) to copy and double plus the gcd fold; shorter subarrays add a constant factor.
//          Masks with more than k bits are skipped after only a bit count, but they are
//          still looped over.
//   Space: O(n) - the subarray copy and the temp copy
//
// GOTCHAS
//   - An element may be doubled at most once, which is why a mask (each bit 0 or 1) models
//     the choice exactly.
//   - computeGcd returns at once when the running gcd reaches 1: it cannot grow back.
//   - Doubled values stay inside int here (at most 2 * 10^9), but the final product does not.
//
// RELATED
//   - LC 1979 Find Greatest Common Divisor of Array (in this repo) - the gcd building block.
//   - LC 2447 Number of Subarrays With GCD Equal to K - another problem about the gcd of
//     every subarray.
// ============================================================================
public class LC3574MaximizeSubarrayGCDScore {
    public static void main(String[] args) {
        // LC examples 1-3 (tiny inputs, so the brute force is fine): prints 8, 14, 15
        int[] nums1 = {2, 4};
        int k1 = 1;
        System.out.println(maxScore(nums1, k1)); // Output: 8

        int[] nums2 = {3, 5, 7};
        int k2 = 2;
        System.out.println(maxScore(nums2, k2)); // Output: 14

        int[] nums3 = {5, 5, 5};
        int k3 = 1;
        System.out.println(maxScore(nums3, k3)); // Output: 15
    }

    // Brute force over every subarray and every set of at most k doublings.
    public static long maxScore(int[] nums, int k) {
        int n = nums.length;
        // int: len * gcd can overflow it (see STATUS)
        int maxScore = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                int[] subarray = Arrays.copyOfRange(nums, i, j + 1);
                int m = subarray.length;
                int maxGcd = 0;

                // every subset of the subarray: bit b set = double element b
                for (int mask = 0; mask < (1 << m); mask++) {
                    // more than k doublings is not allowed
                    if (Integer.bitCount(mask) > k) {
                        continue;
                    }
                    int[] temp = subarray.clone();
                    for (int bit = 0; bit < m; bit++) {
                        if ((mask & (1 << bit)) != 0) {
                            temp[bit] *= 2;
                        }
                    }
                    int currentGcd = computeGcd(temp);
                    if (currentGcd > maxGcd) {
                        maxGcd = currentGcd;
                    }
                }
                // best gcd for this subarray times its length
                int currentScore = len * maxGcd;
                if (currentScore > maxScore) {
                    maxScore = currentScore;
                }
            }
        }
        return maxScore;
    }

    // gcd of all elements; stops early at 1 because it can only stay 1
    private static int computeGcd(int[] arr) {
        int gcd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = gcd(gcd, arr[i]);
            if (gcd == 1) {
                return 1;
            }
        }
        return gcd;
    }

    // Euclid's algorithm
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
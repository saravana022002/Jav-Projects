package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 560. Subarray Sum Equals K   [Medium]
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * Topics: Array, Hash Table, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #5
 *       also: ch. 3 Hashing as a search-to-lookup transform (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return how many non-empty contiguous subarrays of nums add up to exactly k. Subarrays at
 *   different positions are counted separately, even when they hold the same values. nums
 *   may contain negative numbers and zeros.
 *
 * EXAMPLES
 *   nums = [1,1,1], k = 2  ->  2     the two subarrays [1,1]
 *   nums = [1,2,3], k = 3  ->  2     [1,2] and [3]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 2 * 10^4,   -1000 <= nums[i] <= 1000,   -10^7 <= k <= 10^7
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/PrefixSum/LC560SubarraySumEqualsKV1.java
 *   src/Practice/PrefixSum/LC560SubarraySumEqualsKV2.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC560SubarraySumEqualsK {

    /**
     * Variant A - brute force: for every start and end, add up that subarray from scratch
     * <br>Target: time O(n^3), space O(1)
     */
    public static int subarraySumBruteForce(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - build the prefix-sum array, then compare every pair of prefix positions
     * <br>Target: time O(n^2), space O(n)
     */
    public static int subarraySumPrefixPairs(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: prefix sums plus a hash map of earlier prefix values
     * <br>Target: time O(n), space O(n)
     */
    public static int subarraySum(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC560SubarraySumEqualsK::subarraySumBruteForce);
        runAll("B", LC560SubarraySumEqualsK::subarraySumPrefixPairs);
        runAll("C", LC560SubarraySumEqualsK::subarraySum);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 560. Subarray Sum Equals K)
    // https://leetcode.com/problems/subarray-sum-equals-k/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[]{1, 1, 1}, 2));
        check(v + " example 2", 2, () -> s.solve(new int[]{1, 2, 3}, 3));
        // edge: a single negative element equal to k
        check(v + " single element", 1, () -> s.solve(new int[]{-3}, -3));
        // edge: all zeros, every subarray counts (3 + 2 + 1)
        check(v + " zeros", 6, () -> s.solve(new int[]{0, 0, 0}, 0));
        // edge: negatives break any window that only grows or shrinks by sign
        check(v + " negatives", 4, () -> s.solve(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

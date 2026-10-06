package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 713. Subarray Product Less Than K   [Medium]
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * Topics: Array, Binary Search, Sliding Window, Prefix Sum
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #9
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Count the contiguous subarrays of nums whose product is strictly less than k.
 *
 * EXAMPLES
 *   nums = [10,5,2,6], k = 100  ->  8
 *       the 8 subarrays: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]
 *       [10,5,2] is not counted: its product is exactly 100, not less than 100
 *   nums = [1,2,3],     k = 0   ->  0
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 3 * 10^4,   1 <= nums[i] <= 1000,   0 <= k <= 10^6
 * </pre>
 */
public class LC713SubarrayProductLessThanK {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int numSubarrayProductLessThanKA(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     *     + ch. 10 Binary search on a sorted array (later chapter)
     */
    public static int numSubarrayProductLessThanKB(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 9 Sliding window, chasing (this chapter)
     */
    public static int numSubarrayProductLessThanKC(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC713SubarrayProductLessThanK::numSubarrayProductLessThanKA);
        runAll("B", LC713SubarrayProductLessThanK::numSubarrayProductLessThanKB);
        runAll("C", LC713SubarrayProductLessThanK::numSubarrayProductLessThanKC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 713. Subarray Product Less Than K)
    // https://leetcode.com/problems/subarray-product-less-than-k/
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
        check(v + " example 1", 8, () -> s.solve(new int[]{10, 5, 2, 6}, 100));
        check(v + " example 2", 0, () -> s.solve(new int[]{1, 2, 3}, 0));
        // edge: k = 1, even a product of 1 is not strictly less than 1
        check(v + " k = 1", 0, () -> s.solve(new int[]{1, 1, 1}, 1));
        // edge: every subarray qualifies, the count is n * (n + 1) / 2
        check(v + " all ones", 10, () -> s.solve(new int[]{1, 1, 1, 1}, 2));
        // edge: a larger mixed input (one of LeetCode's sample cases)
        check(v + " larger input", 48, () -> s.solve(new int[]{686, 28, 455, 675, 605, 29, 942,
                48, 502, 889, 854, 206, 231, 796, 272, 565, 887, 969, 558, 13, 22, 455, 145, 804,
                15}, 515854));
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

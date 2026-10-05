package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 918. Maximum Sum Circular Subarray   [Medium]
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 * Topics: Array, Divide and Conquer, Dynamic Programming, Queue, Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #9
 *       also: ch. 21 Kadane and the running optimum (#9)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is a circular array: the element after the last one is the first one again.
 *   Return the largest sum of a non-empty subarray of this circle. A subarray is a run of
 *   consecutive positions that may wrap around the end of the array, but it may use every
 *   position at most once, so it has at most nums.length elements.
 *
 * EXAMPLES
 *   nums = [1,-2,3,-2]  ->  3     the subarray [3]
 *   nums = [5,-3,5]     ->  10    the subarray [5,5], wrapping around the end
 *   nums = [-3,-2,-3]   ->  -2    the subarray [-2]
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 3 * 10^4,   -3 * 10^4 <= nums[i] <= 3 * 10^4
 * </pre>
 */
public class LC918MaximumSumCircularSubarray {

    /**
     * Variant A - brute force: every start position and every length up to n, wrapping around
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int maxSubarraySumCircularBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - Kadane: best plain subarray versus the total minus the worst subarray
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 21 Kadane and the running optimum (later chapter)
     */
    public static int maxSubarraySumCircularKadane(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: prefix sums of the doubled array, monotonic deque
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     *     + ch. 17 The monotonic deque (this chapter)
     */
    public static int maxSubarraySumCircular(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC918MaximumSumCircularSubarray::maxSubarraySumCircularBruteForce);
        runAll("B", LC918MaximumSumCircularSubarray::maxSubarraySumCircularKadane);
        runAll("C", LC918MaximumSumCircularSubarray::maxSubarraySumCircular);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 918. Maximum Sum Circular Subarray)
    // https://leetcode.com/problems/maximum-sum-circular-subarray/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were checked against a brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{1, -2, 3, -2}));
        check(v + " example 2", 10, () -> s.solve(new int[]{5, -3, 5}));
        check(v + " example 3", -2, () -> s.solve(new int[]{-3, -2, -3}));
        // edge: a single negative element
        check(v + " single negative", -7, () -> s.solve(new int[]{-7}));
        // edge: all positive, the answer is the whole circle (counted once, not twice)
        check(v + " all positive", 15, () -> s.solve(new int[]{3, 1, 3, 2, 6}));
        // edge: the best run wraps around the end and leaves out a negative in the middle
        check(v + " wraps over a dip", 25, () -> s.solve(new int[]{8, -1, -3, 7, 9, -6, 5}));
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

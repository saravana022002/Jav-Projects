package Leetcode.Ch09SlidingWindowChasing;

import java.util.function.Supplier;

/**
 * <pre>
 * LC 643. Maximum Average Subarray I   [Easy]
 * https://leetcode.com/problems/maximum-average-subarray-i/
 * Topics: Array, Sliding Window
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds n integers and k is a window length. Look at every run of exactly k
 *   neighbouring elements, find the run whose average is the largest and return that
 *   average as a double. Answers within 10^-5 of the true value are accepted.
 *
 * EXAMPLES
 *   nums = [1,12,-5,-6,50,3], k = 4  ->  12.75000    (12 - 5 - 6 + 50) / 4 = 51 / 4
 *   nums = [5],               k = 1  ->  5.00000
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= k <= n <= 10^5,   -10^4 <= nums[i] <= 10^4
 * </pre>
 */
public class LC643MaximumAverageSubarrayI {

    /**
     * Variant A
     * <br>Target: time O(n * k), space O(1)
     */
    public static double findMaxAverageA(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     */
    public static double findMaxAverageB(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     */
    public static double findMaxAverageC(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC643MaximumAverageSubarrayI::findMaxAverageA);
        runAll("B", LC643MaximumAverageSubarrayI::findMaxAverageB);
        runAll("C", LC643MaximumAverageSubarrayI::findMaxAverageC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 643. Maximum Average Subarray I)
    // https://leetcode.com/problems/maximum-average-subarray-i/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    // Doubles are compared with a tolerance of 1e-5.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        double solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 12.75, () -> s.solve(new int[]{1, 12, -5, -6, 50, 3}, 4));
        check(v + " example 2", 5.0, () -> s.solve(new int[]{5}, 1));
        // edge: every window average is negative (a running best that starts at 0 is wrong)
        check(v + " all negative", -3.5, () -> s.solve(new int[]{-5, -2, -9, -4}, 2));
        // edge: the only window is the whole array
        check(v + " k equals n", 2.5, () -> s.solve(new int[]{1, 2, 3, 4}, 4));
        // edge: the best window is the last one
        check(v + " best at the end", 4.0, () -> s.solve(new int[]{1, 2, 3, 4, 5}, 3));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, double expected, Supplier<Double> actual) {
        total++;
        boolean ok = false;
        String got;
        try {
            double d = actual.get();
            ok = Math.abs(d - expected) < 1e-5;
            got = String.valueOf(d);
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

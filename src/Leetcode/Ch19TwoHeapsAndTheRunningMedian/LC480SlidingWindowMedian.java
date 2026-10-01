package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 480. Sliding Window Median   [Hard]
 * https://leetcode.com/problems/sliding-window-median/
 * Topics: Array, Hash Table, Sliding Window, Heap (Priority Queue), Treap
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #4
 *       also: ch. 39 Fenwick trees (#12), ch. 41 Ordered maps in practice (#10)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A window of k consecutive numbers slides over nums from the far left to the far right,
 *   one position at a time. For every window position compute the median of the k numbers
 *   inside it: the middle value of the sorted window when k is odd, the mean of the two middle
 *   values when k is even. Return all n - k + 1 medians in order, as doubles (an error up to
 *   10^-5 is accepted).
 *
 * EXAMPLES
 *   nums = [1,3,-1,-3,5,3,6,7],  k = 3  ->  [1,-1,-1,3,5,6]
 *       windows: [1 3 -1] -> 1,  [3 -1 -3] -> -1,  [-1 -3 5] -> -1,  [-3 5 3] -> 3, ...
 *   nums = [1,2,3,4,2,3,1,4,2],  k = 3  ->  [2,3,3,3,2,3,2]
 *
 * CONSTRAINTS
 *   1 <= k <= nums.length <= 10^5,   -2^31 <= nums[i] <= 2^31 - 1
 * </pre>
 */
public class LC480SlidingWindowMedian {

    /**
     * Variant A
     * <br>Target: time O(n * k log k), space O(k)
     */
    public static double[] medianSlidingWindowA(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n * k), space O(k)
     */
    public static double[] medianSlidingWindowB(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     */
    public static double[] medianSlidingWindowC(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC480SlidingWindowMedian::medianSlidingWindowA);
        runAll("B", LC480SlidingWindowMedian::medianSlidingWindowB);
        runAll("C", LC480SlidingWindowMedian::medianSlidingWindowC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 480. Sliding Window Median)
    // https://leetcode.com/problems/sliding-window-median/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a sort-every-window reference. The medians
    // are compared with a tolerance of 1e-5.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        double[] solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new double[]{1, -1, -1, 3, 5, 6},
                () -> s.solve(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        check(v + " example 2", new double[]{2, 3, 3, 3, 2, 3, 2},
                () -> s.solve(new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2}, 3));
        // edge: k = 1, every number is its own median
        check(v + " k is 1", new double[]{5, 2, 2, 7}, () -> s.solve(new int[]{5, 2, 2, 7}, 1));
        // edge: the window is the whole array (even size)
        check(v + " k is n", new double[]{2.5}, () -> s.solve(new int[]{1, 4, 2, 3}, 4));
        // edge: values at the int limits (adding two of them overflows an int)
        check(v + " int limits", new double[]{2147483647.0, -0.5, -2147483648.0},
                () -> s.solve(new int[]{2147483647, 2147483647, -2147483648, -2147483648}, 2));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, double[] expected, Supplier<double[]> actual) {
        total++;
        double[] got = null;
        String err = null;
        try {
            got = actual.get();
        } catch (Throwable t) {
            err = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = got != null && got.length == expected.length;
        for (int i = 0; ok && i < expected.length; i++) {
            ok = Math.abs(got[i] - expected[i]) <= 1e-5;
        }
        if (ok) passed++;
        String shown = err != null ? err : Arrays.toString(got);
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + Arrays.toString(expected) + ", got " + shown));
    }

    // ============================== END TESTS ===============================
}

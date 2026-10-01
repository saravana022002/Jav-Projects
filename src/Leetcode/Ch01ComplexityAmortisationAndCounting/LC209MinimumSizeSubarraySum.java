package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 209. Minimum Size Subarray Sum   [Medium]
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * Topics: Array, Binary Search, Sliding Window, Prefix Sum
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #6
 *       also: ch. 9 Sliding window, chasing (#7), ch. 83 What n tells you (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums contains only positive integers. Find the length of the shortest contiguous
 *   subarray whose elements add up to at least target. If even the whole array adds up to
 *   less than target, return 0.
 *   Follow-up: once you have an O(n) solution, also write one that runs in O(n log n).
 *
 * EXAMPLES
 *   target = 7,  nums = [2,3,1,2,4,3]       ->  2     [4,3] adds up to 7
 *   target = 4,  nums = [1,4,4]             ->  1     [4] alone is enough
 *   target = 11, nums = [1,1,1,1,1,1,1,1]   ->  0     the whole array only adds up to 8
 *
 * CONSTRAINTS
 *   1 <= target <= 10^9,   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^4
 * </pre>
 */
public class LC209MinimumSizeSubarraySum {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int minSubArrayLenA(int target, int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     */
    public static int minSubArrayLenB(int target, int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     */
    public static int minSubArrayLenC(int target, int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC209MinimumSizeSubarraySum::minSubArrayLenA);
        runAll("B", LC209MinimumSizeSubarraySum::minSubArrayLenB);
        runAll("C", LC209MinimumSizeSubarraySum::minSubArrayLenC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 209. Minimum Size Subarray Sum)
    // https://leetcode.com/problems/minimum-size-subarray-sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int target, int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(7, new int[]{2, 3, 1, 2, 4, 3}));
        check(v + " example 2", 1, () -> s.solve(4, new int[]{1, 4, 4}));
        check(v + " example 3", 0, () -> s.solve(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
        // edge: a single element that is exactly enough
        check(v + " single element", 1, () -> s.solve(5, new int[]{5}));
        // edge: only the whole array reaches the target
        check(v + " whole array", 5, () -> s.solve(15, new int[]{1, 2, 3, 4, 5}));
        // edge: a long window collapses to one big element at the end
        check(v + " collapses to one", 1, () -> s.solve(100, new int[]{1, 1, 1, 1, 1, 1, 100}));
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

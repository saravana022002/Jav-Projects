package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2104. Sum of Subarray Ranges   [Medium]
 * https://leetcode.com/problems/sum-of-subarray-ranges/
 * Topics: Array, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   The range of a subarray is its largest element minus its smallest element. For the integer
 *   array nums, add up the ranges of all its non-empty contiguous subarrays and return the sum.
 *   The result is returned as a long.
 *
 * EXAMPLES
 *   nums = [1,2,3]        ->  4    the six ranges are 0, 0, 0, 1, 1, 2
 *   nums = [1,3,3]        ->  4    the six ranges are 0, 0, 0, 2, 0, 2
 *   nums = [4,-2,-3,4,1]  ->  59
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 1000,   -10^9 <= nums[i] <= 10^9
 *   Follow-up: can you do it in O(n) time?
 * </pre>
 */
public class LC2104SumOfSubarrayRanges {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static long subArrayRangesA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static long subArrayRangesB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2104SumOfSubarrayRanges::subArrayRangesA);
        runAll("B", LC2104SumOfSubarrayRanges::subArrayRangesB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2104. Sum of Subarray Ranges)
    // https://leetcode.com/problems/sum-of-subarray-ranges/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent brute-force reference.
    // The methods return long, so the expected values are long too.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        long solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4L, () -> s.solve(new int[]{1, 2, 3}));
        check(v + " example 2", 4L, () -> s.solve(new int[]{1, 3, 3}));
        check(v + " example 3", 59L, () -> s.solve(new int[]{4, -2, -3, 4, 1}));
        // edge: a single element, its only subarray has range 0
        check(v + " single element", 0L, () -> s.solve(new int[]{5}));
        // edge: all values equal, every range is 0
        check(v + " all equal", 0L, () -> s.solve(new int[]{2, 2, 2, 2}));
        // edge: 1000 values alternating between 10^9 and -10^9; the answer needs a long
        check(v + " huge ranges", 999000000000000L, () -> s.solve(alternating()));
    }

    // 1000000000, -1000000000, 1000000000, ... (1000 values)
    private static int[] alternating() {
        int[] a = new int[1000];
        for (int i = 0; i < a.length; i++) {
            a[i] = i % 2 == 0 ? 1_000_000_000 : -1_000_000_000;
        }
        return a;
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

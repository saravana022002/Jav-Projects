package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 611. Valid Triangle Number   [Medium]
 * https://leetcode.com/problems/valid-triangle-number/
 * Topics: Array, Two Pointers, Binary Search, Greedy, Sorting
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Count the triplets of positions in nums whose three values can be the side lengths of a
 *   triangle: the sum of any two sides must be strictly greater than the third one. Triplets
 *   are counted by position, so equal values at different positions count separately.
 *
 * EXAMPLES
 *   nums = [2,2,3,4]  ->  3     (2,3,4) with either 2, and (2,2,3)
 *   nums = [4,2,3,4]  ->  4
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 1000,   0 <= nums[i] <= 1000
 * </pre>
 */
public class LC611ValidTriangleNumber {

    /**
     * Variant A
     * <br>Target: time O(n^3), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int triangleNumberA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n^2 log n), space O(1)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 10 Binary search on a sorted array (later chapter)
     */
    public static int triangleNumberB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 8 Two pointers, converging (this chapter)
     */
    public static int triangleNumberC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC611ValidTriangleNumber::triangleNumberA);
        runAll("B", LC611ValidTriangleNumber::triangleNumberB);
        runAll("C", LC611ValidTriangleNumber::triangleNumberC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 611. Valid Triangle Number)
    // https://leetcode.com/problems/valid-triangle-number/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a try-every-triple reference. Every case passes
    // a fresh array, so a variant that sorts its input in place is fine.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{2, 2, 3, 4}));
        check(v + " example 2", 4, () -> s.solve(new int[]{4, 2, 3, 4}));
        // edge: zero-length sides never make a triangle
        check(v + " zeros", 0, () -> s.solve(new int[]{0, 0, 0, 1, 1}));
        // edge: two short sides that only just reach the long one (1 + 2 = 3) are not enough
        check(v + " degenerate", 0, () -> s.solve(new int[]{1, 2, 3}));
        // edge: all values equal, every triple of positions counts (C(5,3) = 10)
        check(v + " all equal", 10, () -> s.solve(new int[]{3, 3, 3, 3, 3}));
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

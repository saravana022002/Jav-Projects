package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2163. Minimum Difference in Sums After Removal of Elements   [Hard]
 * https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/
 * Topics: Array, Dynamic Programming, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #8
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums has exactly 3 * n elements. Delete exactly n of them (any positions you like). The
 *   2 * n elements that remain, in their original order, form two halves: the first n and the
 *   last n. Return the smallest possible value of (sum of the first half) - (sum of the
 *   second half), as a long.
 *
 * EXAMPLES
 *   nums = [3,1,2]        ->  -1    n = 1: delete 3, left [1,2], difference 1 - 2 = -1
 *   nums = [7,9,5,8,1,3]  ->  1     n = 2: delete 9 and 1, left [7,5,8,3], (7+5) - (8+3) = 1
 *
 * CONSTRAINTS
 *   nums.length == 3 * n,   1 <= n <= 10^5,   1 <= nums[i] <= 10^5
 * </pre>
 */
public class LC2163MinimumDifferenceInSumsAfterRemovalOfElements {

    /**
     * Variant A - brute force: for every split point, sort both sides to pick the best n of each
     * <br>Target: time O(n^2 log n), space O(n)
     */
    public static long minimumDifferenceBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: one heap sweeping from each end of the array
     * <br>Target: time O(n log n), space O(n)
     */
    public static long minimumDifference(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A",
                LC2163MinimumDifferenceInSumsAfterRemovalOfElements::minimumDifferenceBruteForce);
        runAll("B", LC2163MinimumDifferenceInSumsAfterRemovalOfElements::minimumDifference);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 2163. Minimum Difference in Sums After Removal of Elements)
    // https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an enumerate-all-removals reference.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        long solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", -1L, () -> s.solve(new int[]{3, 1, 2}));
        check(v + " example 2", 1L, () -> s.solve(new int[]{7, 9, 5, 8, 1, 3}));
        // edge: all values equal
        check(v + " all equal", 0L, () -> s.solve(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}));
        // edge: strictly decreasing, the answer is positive
        check(v + " decreasing", 9L, () -> s.solve(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}));
        // edge: mixed values with n = 4
        check(v + " mixed", -19L, () -> s.solve(new int[]{2, 7, 1, 8, 2, 8, 1, 8, 2, 8, 4, 5}));
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
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

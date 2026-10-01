package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 327. Count of Range Sum   [Hard]
 * https://leetcode.com/problems/count-of-range-sum/
 * Topics: Array, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree,
 *         Merge Sort, Ordered Set, Treap
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #12
 *       also: ch. 39 Fenwick trees (#7), ch. 40 Segment trees (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   The range sum S(i, j) of the integer array nums is the sum of nums[i..j], both ends
 *   included, with i <= j. Return how many range sums lie in the closed interval
 *   [lower, upper]. Different (i, j) pairs count separately, even when their sums are equal.
 *
 * EXAMPLES
 *   nums = [-2,5,-1],  lower = -2,  upper = 2  ->  3
 *       the ranges [0,0], [2,2] and [0,2] have sums -2, -1 and 2
 *   nums = [0],        lower = 0,   upper = 0  ->  1
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -2^31 <= nums[i] <= 2^31 - 1,   -10^5 <= lower <= upper <= 10^5,
 *   the answer fits in a 32-bit integer
 * </pre>
 */
public class LC327CountOfRangeSum {

    /**
     * Variant A - brute force: test the difference of every pair of prefix sums
     * <br>Target: time O(n^2), space O(n)
     */
    public static int countRangeSumBruteForce(int[] nums, int lower, int upper) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - Fenwick tree over compressed prefix sums: count the earlier ones in a window
     * <br>Target: time O(n log n), space O(n)
     */
    public static int countRangeSumFenwick(int[] nums, int lower, int upper) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: merge sort on prefix sums, count cross pairs per merge
     * <br>Target: time O(n log n), space O(n)
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC327CountOfRangeSum::countRangeSumBruteForce);
        runAll("B", LC327CountOfRangeSum::countRangeSumFenwick);
        runAll("C", LC327CountOfRangeSum::countRangeSum);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 327. Count of Range Sum)
    // https://leetcode.com/problems/count-of-range-sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected counts checked against an all-ranges reference that
    // uses long arithmetic).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int lower, int upper);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh array)
        check(v + " example 1", 3, () -> s.solve(new int[]{-2, 5, -1}, -2, 2));
        check(v + " example 2", 1, () -> s.solve(new int[]{0}, 0, 0));
        // edge: prefix sums leave the int range (they reach about 4.3 * 10^9)
        check(v + " sums beyond int", 2,
                () -> s.solve(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MIN_VALUE, Integer.MIN_VALUE}, -2, -1));
        // edge: lower == upper == 0 and every range sums to 0
        check(v + " all zeros", 6, () -> s.solve(new int[]{0, 0, 0}, 0, 0));
        // edge: negative window over mixed signs
        check(v + " negative window", 6, () -> s.solve(new int[]{3, -1, -4, 2, -5}, -4, -1));
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

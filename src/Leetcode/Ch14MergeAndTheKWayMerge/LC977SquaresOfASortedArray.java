package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 977. Squares of a Sorted Array   [Easy]
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #3
 *       also: ch. 8 Two pointers, converging (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is sorted in non-decreasing order and may contain negative numbers. Return an array
 *   holding the square of every element of nums, also sorted in non-decreasing order.
 *   Follow-up: squaring everything and sorting is the easy way - find an O(n) algorithm
 *   that works differently.
 *
 * EXAMPLES
 *   nums = [-4,-1,0,3,10]  ->  [0,1,9,16,100]     squares are [16,1,0,9,100] before sorting
 *   nums = [-7,-3,2,3,11]  ->  [4,9,9,49,121]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -10^4 <= nums[i] <= 10^4,
 *   nums is sorted in non-decreasing order
 * </pre>
 */
public class LC977SquaresOfASortedArray {

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(1) extra besides the answer
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int[] sortedSquaresA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1) extra besides the answer
     * <br>Complete first: ch. 8 Two pointers, converging
     */
    public static int[] sortedSquaresB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1) extra besides the answer
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     */
    public static int[] sortedSquaresC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC977SquaresOfASortedArray::sortedSquaresA);
        runAll("B", LC977SquaresOfASortedArray::sortedSquaresB);
        runAll("C", LC977SquaresOfASortedArray::sortedSquaresC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 977. Squares of a Sorted Array)
    // https://leetcode.com/problems/squares-of-a-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected arrays checked against square-then-sort).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh array)
        check(v + " example 1", new int[]{0, 1, 9, 16, 100},
                () -> s.solve(new int[]{-4, -1, 0, 3, 10}));
        check(v + " example 2", new int[]{4, 9, 9, 49, 121},
                () -> s.solve(new int[]{-7, -3, 2, 3, 11}));
        // edge: only negative numbers (the answer is the input reversed and squared)
        check(v + " all negative", new int[]{1, 4, 9, 25},
                () -> s.solve(new int[]{-5, -3, -2, -1}));
        // edge: no negative numbers, with a zero and a repeated value
        check(v + " all non-negative", new int[]{0, 4, 4, 9}, () -> s.solve(new int[]{0, 2, 2, 3}));
        // edge: the largest allowed magnitude on both sides, with ties
        check(v + " extremes and ties", new int[]{0, 100000000, 100000000, 100000000},
                () -> s.solve(new int[]{-10000, -10000, 0, 10000}));
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

package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 977. Squares of a Sorted Array   [Easy]
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #5
 *       also: ch. 14 Merge and the k-way merge (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is sorted in non-decreasing order and may contain negative numbers. Return a new
 *   array holding the square of every element, also sorted in non-decreasing order.
 *   Follow-up: squaring everything and sorting is the obvious route - can you do it in O(n)?
 *
 * EXAMPLES
 *   nums = [-4,-1,0,3,10]   ->  [0,1,9,16,100]     squares are [16,1,0,9,100] before sorting
 *   nums = [-7,-3,2,3,11]   ->  [4,9,9,49,121]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -10^4 <= nums[i] <= 10^4,   nums is sorted in non-decreasing order
 * </pre>
 */
public class LC977SquaresOfASortedArray {

    /**
     * Variant A - square every element, then sort the result
     * <br>Target: time O(n log n), space O(n)
     */
    public static int[] sortedSquaresSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - find where the numbers turn non-negative, then merge the two sorted halves
     * <br>Target: time O(n), space O(n)
     */
    public static int[] sortedSquaresMerge(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: two pointers at the ends, moving towards each other
     * <br>Target: time O(n), space O(n)    (the result array)
     */
    public static int[] sortedSquares(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC977SquaresOfASortedArray::sortedSquaresSort);
        runAll("B", LC977SquaresOfASortedArray::sortedSquaresMerge);
        runAll("C", LC977SquaresOfASortedArray::sortedSquares);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 977. Squares of a Sorted Array)
    // https://leetcode.com/problems/squares-of-a-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a square-and-sort reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{0, 1, 9, 16, 100},
                () -> s.solve(new int[]{-4, -1, 0, 3, 10}));
        check(v + " example 2", new int[]{4, 9, 9, 49, 121},
                () -> s.solve(new int[]{-7, -3, 2, 3, 11}));
        // edge: a single negative element
        check(v + " single negative", new int[]{9}, () -> s.solve(new int[]{-3}));
        // edge: every element is negative, so the order has to flip
        check(v + " all negative", new int[]{4, 25, 49}, () -> s.solve(new int[]{-7, -5, -2}));
        // edge: extreme values, a zero and repeated values on both sides
        check(v + " extremes", new int[]{0, 100000000, 100000000, 100000000, 100000000},
                () -> s.solve(new int[]{-10000, -10000, 0, 10000, 10000}));
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

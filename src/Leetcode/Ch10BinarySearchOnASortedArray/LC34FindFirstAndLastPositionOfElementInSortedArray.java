package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 34. Find First and Last Position of Element in Sorted Array   [Medium]
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is sorted in non-decreasing order, so equal values sit next to each other. Return
 *   [first, last], the first and the last index at which target occurs, or [-1, -1] when
 *   target does not occur at all. The intended solution runs in O(log n).
 *
 * EXAMPLES
 *   nums = [5,7,7,8,8,10], target = 8  ->  [3,4]      the 8s sit at indices 3 and 4
 *   nums = [5,7,7,8,8,10], target = 6  ->  [-1,-1]    6 is not in the array
 *   nums = [],             target = 0  ->  [-1,-1]    empty array
 *
 * CONSTRAINTS
 *   0 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9,   -10^9 <= target <= 10^9,
 *   nums is sorted in non-decreasing order
 * </pre>
 */
public class LC34FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * Variant A - brute force: scan for the first occurrence, keep going for the last
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] searchRangeBruteForce(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - two separate binary searches, one biased to the left and one to the right
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int[] searchRangeTwoSearches(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: first-index boundary search run twice (>= and >)
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int[] searchRange(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC34FindFirstAndLastPositionOfElementInSortedArray::searchRangeBruteForce);
        runAll("B", LC34FindFirstAndLastPositionOfElementInSortedArray::searchRangeTwoSearches);
        runAll("C", LC34FindFirstAndLastPositionOfElementInSortedArray::searchRange);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 34. Find First and Last Position of Element in Sorted Array)
    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a linear scan.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums, int target);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{3, 4}, () -> s.solve(new int[]{5, 7, 7, 8, 8, 10}, 8));
        check(v + " example 2", new int[]{-1, -1}, () -> s.solve(new int[]{5, 7, 7, 8, 8, 10}, 6));
        check(v + " example 3", new int[]{-1, -1}, () -> s.solve(new int[]{}, 0));
        // edge: every element is the target
        check(v + " all equal", new int[]{0, 3}, () -> s.solve(new int[]{2, 2, 2, 2}, 2));
        // edge: a single element that is the target
        check(v + " single element", new int[]{0, 0}, () -> s.solve(new int[]{1}, 1));
        // edge: target larger than every element
        check(v + " above all", new int[]{-1, -1}, () -> s.solve(new int[]{1, 2, 3}, 4));
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

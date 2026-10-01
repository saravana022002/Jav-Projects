package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 153. Find Minimum in Rotated Sorted Array   [Medium]
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #8
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A strictly ascending array was rotated between 1 and n times; one rotation moves the last
 *   element to the front. For example [0,1,2,4,5,6,7] rotated 4 times is [4,5,6,7,0,1,2],
 *   and rotating it 7 times gives the original array back. All values are distinct. Return
 *   the minimum element of the rotated array. The intended solution runs in O(log n).
 *
 * EXAMPLES
 *   nums = [3,4,5,1,2]        ->  1    the original [1,2,3,4,5] rotated 3 times
 *   nums = [4,5,6,7,0,1,2]    ->  0    the original [0,1,2,4,5,6,7] rotated 4 times
 *   nums = [11,13,15,17]      ->  11   the original rotated 4 times, i.e. not changed
 *
 * CONSTRAINTS
 *   n = nums.length,   1 <= n <= 5000,   -5000 <= nums[i] <= 5000,   all values are unique,
 *   nums is sorted and rotated between 1 and n times
 * </pre>
 */
public class LC153FindMinimumInRotatedSortedArray {

    /**
     * Variant A - brute force: scan every element and keep the smallest
     * <br>Target: time O(n), space O(1)
     */
    public static int findMinBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - binary search for the inflection point where a value is smaller than its left
     * <br>Target: time O(log n), space O(1)
     */
    public static int findMinInflectionPoint(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: boundary search against the last element
     * <br>Target: time O(log n), space O(1)
     */
    public static int findMin(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC153FindMinimumInRotatedSortedArray::findMinBruteForce);
        runAll("B", LC153FindMinimumInRotatedSortedArray::findMinInflectionPoint);
        runAll("C", LC153FindMinimumInRotatedSortedArray::findMin);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 153. Find Minimum in Rotated Sorted Array)
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a linear scan.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{3, 4, 5, 1, 2}));
        check(v + " example 2", 0, () -> s.solve(new int[]{4, 5, 6, 7, 0, 1, 2}));
        check(v + " example 3", 11, () -> s.solve(new int[]{11, 13, 15, 17}));
        // edge: a single element
        check(v + " single element", 1, () -> s.solve(new int[]{1}));
        // edge: two elements, rotated once
        check(v + " two elements", 1, () -> s.solve(new int[]{2, 1}));
        // edge: the minimum is the last element
        check(v + " minimum at the end", 1, () -> s.solve(new int[]{2, 3, 4, 5, 1}));
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

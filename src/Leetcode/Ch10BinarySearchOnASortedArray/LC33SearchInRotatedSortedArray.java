package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 33. Search in Rotated Sorted Array   [Medium]
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #9
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums was a strictly ascending array (all values distinct) that may have been rotated to
 *   the left by some k, 1 <= k < n, giving [nums[k], ..., nums[n-1], nums[0], ..., nums[k-1]].
 *   For example [0,1,2,4,5,6,7] rotated by 3 becomes [4,5,6,7,0,1,2]; an unrotated array is
 *   also possible. Return the index of target in the array you are given, or -1 when it is
 *   not there. The intended solution runs in O(log n).
 *
 * EXAMPLES
 *   nums = [4,5,6,7,0,1,2], target = 0  ->  4
 *   nums = [4,5,6,7,0,1,2], target = 3  ->  -1
 *   nums = [1],             target = 0  ->  -1
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 5000,   -10^4 <= nums[i] <= 10^4,   -10^4 <= target <= 10^4,
 *   all values of nums are unique, nums is an ascending array that is possibly rotated
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/BinarySearch/LC33SearchInRotatedSortedArray.java
 * </pre>
 */
public class LC33SearchInRotatedSortedArray {

    /**
     * Variant A
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int searchA(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int searchB(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int searchC(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC33SearchInRotatedSortedArray::searchA);
        runAll("B", LC33SearchInRotatedSortedArray::searchB);
        runAll("C", LC33SearchInRotatedSortedArray::searchC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 33. Search in Rotated Sorted Array)
    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a linear scan.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int target);
    }

    private static void runAll(String v, Solver s) {
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        // official examples
        check(v + " example 1", 4, () -> s.solve(rotated, 0));
        check(v + " example 2", -1, () -> s.solve(rotated, 3));
        check(v + " example 3", -1, () -> s.solve(new int[]{1}, 0));
        // edge: two elements, rotated, target is the second one
        check(v + " two elements", 1, () -> s.solve(new int[]{3, 1}, 1));
        // edge: the array may be not rotated at all
        check(v + " not rotated", 3, () -> s.solve(new int[]{1, 2, 3, 4, 5}, 4));
        // edge: the first and the last element of a rotated array (indices 0 and 6)
        check(v + " both ends", new int[]{0, 6},
                () -> new int[]{s.solve(rotated, 4), s.solve(rotated, 2)});
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

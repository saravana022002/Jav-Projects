package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 35. Search Insert Position   [Easy]
 * https://leetcode.com/problems/search-insert-position/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is sorted in ascending order and has no duplicates. Return the index of target if it
 *   is present; otherwise return the index at which target would have to be inserted to keep
 *   the array sorted. The intended solution runs in O(log n).
 *
 * EXAMPLES
 *   nums = [1,3,5,6], target = 5  ->  2    found at index 2
 *   nums = [1,3,5,6], target = 2  ->  1    it would go between 1 and 3
 *   nums = [1,3,5,6], target = 7  ->  4    it would go after the last element
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -10^4 <= nums[i] <= 10^4,   -10^4 <= target <= 10^4,
 *   nums contains distinct values sorted in ascending order
 * </pre>
 */
public class LC35SearchInsertPosition {

    /**
     * Variant A - brute force: scan from the left for the first element >= target
     * <br>Target: time O(n), space O(1)
     */
    public static int searchInsertBruteForce(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - classic binary search with a three-way branch (equal / smaller / larger)
     * <br>Target: time O(log n), space O(1)
     */
    public static int searchInsertThreeWay(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: lower bound, the first index with nums[i] >= target
     * <br>Target: time O(log n), space O(1)
     */
    public static int searchInsert(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC35SearchInsertPosition::searchInsertBruteForce);
        runAll("B", LC35SearchInsertPosition::searchInsertThreeWay);
        runAll("C", LC35SearchInsertPosition::searchInsert);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 35. Search Insert Position)
    // https://leetcode.com/problems/search-insert-position/
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
        int[] nums = {1, 3, 5, 6};
        // official examples
        check(v + " example 1", 2, () -> s.solve(nums, 5));
        check(v + " example 2", 1, () -> s.solve(nums, 2));
        check(v + " example 3", 4, () -> s.solve(nums, 7));
        // edge: smaller than every element, so it goes to the front
        check(v + " below all", 0, () -> s.solve(nums, 0));
        // edge: equal to the last element
        check(v + " equals the last", 3, () -> s.solve(nums, 6));
        // edge: a single element, target just above it
        check(v + " single element", 1, () -> s.solve(new int[]{3}, 4));
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

package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 704. Binary Search   [Easy]
 * https://leetcode.com/problems/binary-search/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is sorted in ascending order and has no duplicates. Return the index where target
 *   sits, or -1 if it is not there. The intended solution runs in O(log n).
 *
 * EXAMPLES
 *   nums = [-1,0,3,5,9,12], target = 9  ->  4     9 is at index 4
 *   nums = [-1,0,3,5,9,12], target = 2  ->  -1    2 is not in the array
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -10^4 < nums[i], target < 10^4,
 *   all integers in nums are unique, nums is sorted in ascending order
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/BinarySearch/LC704BinarySearch.java
 * </pre>
 */
public class LC704BinarySearch {

    /**
     * Variant A - brute force: scan from the left until the value appears
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int searchBruteForce(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - classic binary search: compare the middle, then branch three ways
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int searchThreeWay(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: find the first index with nums[i] >= target,
     *             then check it really holds target
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int search(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC704BinarySearch::searchBruteForce);
        runAll("B", LC704BinarySearch::searchThreeWay);
        runAll("C", LC704BinarySearch::search);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 704. Binary Search)
    // https://leetcode.com/problems/binary-search/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a linear scan.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int target);
    }

    private static void runAll(String v, Solver s) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        // official examples
        check(v + " example 1", 4, () -> s.solve(nums, 9));
        check(v + " example 2", -1, () -> s.solve(nums, 2));
        // edge: a single element
        check(v + " single element", 0, () -> s.solve(new int[]{5}, 5));
        // edge: the first and the last element (indices 0 and 5)
        check(v + " both ends", new int[]{0, 5},
                () -> new int[]{s.solve(nums, -1), s.solve(nums, 12)});
        // edge: targets below the smallest and above the largest value
        check(v + " out of range", new int[]{-1, -1},
                () -> new int[]{s.solve(nums, -9), s.solve(nums, 99)});
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

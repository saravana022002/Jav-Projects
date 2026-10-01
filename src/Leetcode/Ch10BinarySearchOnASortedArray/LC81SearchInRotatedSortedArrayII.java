package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 81. Search in Rotated Sorted Array II   [Medium]
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums was sorted in non-decreasing order (duplicates allowed) and then rotated to the left
 *   at an unknown pivot k, 0 <= k < n, giving [nums[k], ..., nums[n-1], nums[0], ..., nums[k-1]].
 *   For example [0,1,2,4,4,4,5,6,6,7] rotated at pivot 5 becomes [4,5,6,6,7,0,1,2,4,4].
 *   Return true if target occurs in the array you are given, false otherwise. Keep the number
 *   of steps as low as you can. Follow-up: how do the duplicates change the running time?
 *
 * EXAMPLES
 *   nums = [2,5,6,0,0,1,2], target = 0  ->  true
 *   nums = [2,5,6,0,0,1,2], target = 3  ->  false
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 5000,   -10^4 <= nums[i] <= 10^4,   -10^4 <= target <= 10^4,
 *   nums is guaranteed to be rotated at some pivot
 * </pre>
 */
public class LC81SearchInRotatedSortedArrayII {

    /**
     * Variant A
     * <br>Target: time O(n), space O(1)
     */
    public static boolean searchA(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(log n) typically but O(n) worst case (many duplicates), space O(1)
     */
    public static boolean searchB(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC81SearchInRotatedSortedArrayII::searchA);
        runAll("B", LC81SearchInRotatedSortedArrayII::searchB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 81. Search in Rotated Sorted Array II)
    // https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official examples
    // plus edge cases checked against a linear scan.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(int[] nums, int target);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", true, () -> s.solve(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        check(v + " example 2", false, () -> s.solve(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        // edge: all elements equal and the target is missing
        check(v + " all equal", false, () -> s.solve(new int[]{1, 1, 1, 1, 1}, 2));
        // edge: the duplicates at both ends hide where the rotation happened
        check(v + " hidden pivot", true, () -> s.solve(new int[]{1, 0, 1, 1, 1}, 0));
        // edge: one different value buried in a run of equal values
        check(v + " one odd value", true, () -> s.solve(new int[]{1, 13, 1, 1, 1, 1, 1, 1}, 13));
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

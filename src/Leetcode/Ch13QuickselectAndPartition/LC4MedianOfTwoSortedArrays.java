package Leetcode.Ch13QuickselectAndPartition;

import java.util.function.Supplier;

/**
 * <pre>
 * LC 4. Median of Two Sorted Arrays   [Hard]
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * Topics: Array, Binary Search, Divide and Conquer
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #12
 *       also: ch. 10 Binary search on a sorted array (#12)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums1 and nums2 are each sorted in non-decreasing order (either one may be empty, but not
 *   both). Return the median of all their values taken together, as a double: the middle value
 *   when the total count is odd, the average of the two middle values when it is even.
 *   The intended running time is O(log (m + n)).
 *
 * EXAMPLES
 *   nums1 = [1,3], nums2 = [2]    ->  2.0     merged [1,2,3]
 *   nums1 = [1,2], nums2 = [3,4]  ->  2.5     merged [1,2,3,4], (2 + 3) / 2 = 2.5
 *
 * CONSTRAINTS
 *   nums1.length == m,   nums2.length == n,   0 <= m <= 1000,   0 <= n <= 1000,
 *   1 <= m + n <= 2000,   -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/BinarySearch/LC4MedianOfTwoSortedArrays.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC4MedianOfTwoSortedArrays {

    /**
     * Variant A - merge both arrays into one sorted array, read the middle value(s)
     * <br>Target: time O(m + n), space O(m + n)
     */
    public static double findMedianSortedArraysMerge(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - walk both arrays with two pointers, only as far as the middle (no merged array)
     * <br>Target: time O(m + n), space O(1)
     */
    public static double findMedianSortedArraysTwoPointers(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: select the k-th smallest of the two arrays,
     *             discarding about k/2 candidates per step
     * <br>Target: time O(log(m + n)), space O(1)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC4MedianOfTwoSortedArrays::findMedianSortedArraysMerge);
        runAll("B", LC4MedianOfTwoSortedArrays::findMedianSortedArraysTwoPointers);
        runAll("C", LC4MedianOfTwoSortedArrays::findMedianSortedArrays);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 4. Median of Two Sorted Arrays)
    // https://leetcode.com/problems/median-of-two-sorted-arrays/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected medians checked against the sorted merged list).
    // The result is a double, so it is compared with a tolerance of 1e-5.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        double solve(int[] nums1, int[] nums2);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2.0, () -> s.solve(new int[]{1, 3}, new int[]{2}));
        check(v + " example 2", 2.5, () -> s.solve(new int[]{1, 2}, new int[]{3, 4}));
        // edge: the first array is empty
        check(v + " first array empty", 2.5, () -> s.solve(new int[]{}, new int[]{2, 3}));
        // edge: the first array is longer and lies entirely above the second one
        check(v + " disjoint ranges", 7.0,
                () -> s.solve(new int[]{6, 7, 8, 9, 10}, new int[]{1, 2}));
        // edge: duplicates, negatives and the extreme values of the constraints
        check(v + " duplicates and extremes", -3.0,
                () -> s.solve(new int[]{-1000000, -3, -3}, new int[]{-3, 1000000}));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, double expected, Supplier<Double> actual) {
        total++;
        String got;
        boolean ok;
        try {
            double d = actual.get();
            ok = Math.abs(d - expected) < 1e-5;
            got = String.valueOf(d);
        } catch (Throwable t) {
            ok = false;
            got = "threw " + t.getClass().getSimpleName();
        }
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

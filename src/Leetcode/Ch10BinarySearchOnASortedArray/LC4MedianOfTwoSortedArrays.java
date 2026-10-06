package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.function.Supplier;

/**
 * <pre>
 * LC 4. Median of Two Sorted Arrays   [Hard]
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * Topics: Array, Binary Search, Divide and Conquer
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #12
 *       also: ch. 13 Quickselect and partition (#12)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums1 (size m) and nums2 (size n) are each sorted in ascending order. Return the median of
 *   all m + n numbers taken together: the middle value when m + n is odd, the average of the
 *   two middle values when it is even. The intended solution runs in O(log(m + n)).
 *
 * EXAMPLES
 *   nums1 = [1,3], nums2 = [2]    ->  2.00000    merged [1,2,3], the median is 2
 *   nums1 = [1,2], nums2 = [3,4]  ->  2.50000    merged [1,2,3,4], the median is (2 + 3) / 2
 *
 * CONSTRAINTS
 *   nums1.length == m,   nums2.length == n,   0 <= m <= 1000,   0 <= n <= 1000,
 *   1 <= m + n <= 2000,   -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/BinarySearch/LC4MedianOfTwoSortedArrays.java
 * </pre>
 */
public class LC4MedianOfTwoSortedArrays {

    /**
     * Variant A
     * <br>Target: time O(m + n), space O(m + n)
     * <br>Complete first: ch. 14 Merge and the k-way merge (later chapter)
     */
    public static double findMedianSortedArraysA(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(m + n), space O(1)
     * <br>Complete first: ch. 14 Merge and the k-way merge (later chapter)
     */
    public static double findMedianSortedArraysB(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(log(min(m, n))), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static double findMedianSortedArraysC(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC4MedianOfTwoSortedArrays::findMedianSortedArraysA);
        runAll("B", LC4MedianOfTwoSortedArrays::findMedianSortedArraysB);
        runAll("C", LC4MedianOfTwoSortedArrays::findMedianSortedArraysC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 4. Median of Two Sorted Arrays)
    // https://leetcode.com/problems/median-of-two-sorted-arrays/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against sorting the merged values. The result is a
    // double, compared with a tolerance of 1e-5.
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
        // edge: every number of one array comes before every number of the other
        check(v + " disjoint ranges", 3.5, () -> s.solve(new int[]{1, 2}, new int[]{3, 4, 5, 6}));
        // edge: negative numbers and a value repeated across both arrays
        check(v + " negatives and repeats", -3.0,
                () -> s.solve(new int[]{-5, -3, -3}, new int[]{-3, -1}));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, double expected, Supplier<Double> actual) {
        total++;
        Object got;
        boolean ok;
        try {
            double d = actual.get();
            got = d;
            ok = Math.abs(d - expected) <= 1e-5;
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
            ok = false;
        }
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 88. Merge Sorted Array   [Easy]
 * https://leetcode.com/problems/merge-sorted-array/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #1
 *       also: ch. 14 Merge and the k-way merge (#2), ch. 59 Divide and conquer (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums1 and nums2 are sorted in non-decreasing order. nums1 has length m + n: its first m
 *   entries are real values and its last n entries are zero padding to be ignored. nums2 has
 *   n entries. Merge nums2 into nums1 so that nums1 ends up holding all m + n values in
 *   non-decreasing order. Return nothing - the result must be left inside nums1.
 *   Follow-up: can you do it in O(m + n) time?
 *
 * EXAMPLES
 *   nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3  ->  nums1 = [1,2,2,3,5,6]
 *   nums1 = [1],           m = 1, nums2 = [],      n = 0  ->  nums1 = [1]
 *   nums1 = [0],           m = 0, nums2 = [1],     n = 1  ->  nums1 = [1]   (the 0 is padding)
 *
 * CONSTRAINTS
 *   nums1.length == m + n,   nums2.length == n,   0 <= m, n <= 200,   1 <= m + n <= 200,
 *   -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Sorting/LC88MergeSortedArray.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC88MergeSortedArray {

    /**
     * Variant A - copy nums2 into the padding of nums1, then sort all of nums1
     * <br>Target: time O((m + n) log(m + n)), space O(1) besides the sort
     */
    public static void mergeSortAll(int[] nums1, int m, int[] nums2, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - merge from the front into a temporary array, then copy it back
     * <br>Target: time O(m + n), space O(m + n)
     */
    public static void mergeWithCopy(int[] nums1, int m, int[] nums2, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: merge backwards, biggest value into the last free slot
     * <br>Target: time O(m + n), space O(1)
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC88MergeSortedArray::mergeSortAll);
        runAll("B", LC88MergeSortedArray::mergeWithCopy);
        runAll("C", LC88MergeSortedArray::merge);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 88. Merge Sorted Array)
    // https://leetcode.com/problems/merge-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases (expected arrays = the sorted values of both inputs). The method
    // works in place, so every case builds fresh arrays, calls it and checks nums1 afterwards.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        void solve(int[] nums1, int m, int[] nums2, int n);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2, 2, 3, 5, 6},
                () -> merged(s, new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3));
        check(v + " example 2", new int[]{1},
                () -> merged(s, new int[]{1}, 1, new int[]{}, 0));
        check(v + " example 3", new int[]{1},
                () -> merged(s, new int[]{0}, 0, new int[]{1}, 1));
        // edge: every value of nums2 is smaller than all of nums1
        check(v + " nums2 all smaller", new int[]{1, 2, 3, 4, 5, 6},
                () -> merged(s, new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3));
        // edge: every value of nums2 is larger than all of nums1
        check(v + " nums2 all larger", new int[]{1, 2, 3, 4, 5, 6},
                () -> merged(s, new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{4, 5, 6}, 3));
        // edge: negatives and duplicates, the two arrays interleave
        check(v + " negatives and duplicates", new int[]{-5, -1, -1, 0, 0, 2, 2, 7},
                () -> merged(s, new int[]{-1, 0, 2, 7, 0, 0, 0, 0}, 4, new int[]{-5, -1, 0, 2}, 4));
    }

    // runs the merge on the given arrays and returns nums1 afterwards
    private static int[] merged(Solver s, int[] nums1, int m, int[] nums2, int n) {
        s.solve(nums1, m, nums2, n);
        return nums1;
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

package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 88. Merge Sorted Array   [Easy]
 * https://leetcode.com/problems/merge-sorted-array/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #2
 *       also: ch. 6 Sorting as a preprocessing step (#1), ch. 59 Divide and conquer (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums1 and nums2 are sorted in non-decreasing order. nums1 has length m + n: its first m
 *   slots hold the values to merge and its last n slots are zero padding to be ignored.
 *   nums2 holds n values. Merge nums2 into nums1 so that nums1 ends up holding all m + n
 *   values in sorted order. Nothing is returned - the result must be stored in nums1 itself.
 *   Follow-up: can you do it in O(m + n) time?
 *
 * EXAMPLES
 *   nums1 = [1,2,3,0,0,0],  m = 3,  nums2 = [2,5,6],  n = 3  ->  nums1 = [1,2,2,3,5,6]
 *   nums1 = [1],            m = 1,  nums2 = [],       n = 0  ->  nums1 = [1]
 *   nums1 = [0],            m = 0,  nums2 = [1],      n = 1  ->  nums1 = [1]
 *                           (the 0 in nums1 is only padding)
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
     * Variant A - copy nums2 into the padding of nums1, then sort the whole array
     * <br>Target: time O((m + n) log(m + n)), space O(1) extra (the sort works in place)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static void mergeA(int[] nums1, int m, int[] nums2, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(m + n), space O(m)
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     */
    public static void mergeB(int[] nums1, int m, int[] nums2, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(m + n), space O(1)
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     */
    public static void mergeC(int[] nums1, int m, int[] nums2, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC88MergeSortedArray::mergeA);
        runAll("B", LC88MergeSortedArray::mergeB);
        runAll("C", LC88MergeSortedArray::mergeC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 88. Merge Sorted Array)
    // https://leetcode.com/problems/merge-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases (expected arrays checked against sorting both parts together).
    // The method works in place, so nums1 is checked after the call.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        void solve(int[] nums1, int m, int[] nums2, int n);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2, 2, 3, 5, 6},
                () -> run(s, new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3));
        check(v + " example 2", new int[]{1},
                () -> run(s, new int[]{1}, 1, new int[]{}, 0));
        check(v + " example 3", new int[]{1},
                () -> run(s, new int[]{0}, 0, new int[]{1}, 1));
        // edge: every value of nums2 is smaller than every value of nums1
        check(v + " nums2 all smaller", new int[]{1, 2, 3, 4, 5, 6},
                () -> run(s, new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3));
        // edge: real zeros among the values (a 0 is not always padding)
        check(v + " zeros are real values", new int[]{-1, 0, 0, 2, 2, 3},
                () -> run(s, new int[]{0, 0, 3, 0, 0, 0}, 3, new int[]{-1, 2, 2}, 3));
        // edge: extreme values and a duplicate across the two arrays
        check(v + " extremes", new int[]{-1000000000, -1000000000, 5, 6, 1000000000},
                () -> run(s, new int[]{-1000000000, 0, 0, 0, 0}, 1,
                        new int[]{-1000000000, 5, 6, 1000000000}, 4));
    }

    // runs the method on copies of the inputs and returns nums1 afterwards
    private static int[] run(Solver s, int[] nums1, int m, int[] nums2, int n) {
        int[] a = nums1.clone();
        s.solve(a, m, nums2.clone(), n);
        return a;
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

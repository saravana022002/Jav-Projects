package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1122. Relative Sort Array   [Easy]
 * https://leetcode.com/problems/relative-sort-array/
 * Topics: Array, Hash Table, Sorting, Counting Sort, Quicksort, Bubble Sort
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   arr2 holds distinct values and every one of them also occurs in arr1. Reorder arr1 so
 *   that the values listed in arr2 come first, in the order arr2 lists them (all copies of
 *   a value stay together). Values of arr1 that arr2 does not mention go at the very end,
 *   in ascending order. Return the reordered array.
 *
 * EXAMPLES
 *   arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]  ->  [2,2,2,1,4,3,3,9,6,7,19]
 *   arr1 = [28,6,22,8,44,17],        arr2 = [22,28,8,6]    ->  [22,28,8,6,17,44]
 *
 * CONSTRAINTS
 *   1 <= arr1.length, arr2.length <= 1000,   0 <= arr1[i], arr2[i] <= 1000,
 *   all elements of arr2 are distinct,   each arr2[i] occurs in arr1
 * </pre>
 */
public class LC1122RelativeSortArray {

    /**
     * Variant A - for each value of arr2 pull its copies out of arr1, then sort the rest
     * <br>Target: time O(n * m + n log n), space O(n)    (n = arr1.length, m = arr2.length)
     */
    public static int[] relativeSortArrayBruteForce(int[] arr1, int[] arr2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - comparison sort of arr1 with a custom order taken from arr2's positions
     * <br>Target: time O(n log n + m), space O(n + m)
     */
    public static int[] relativeSortArrayComparator(int[] arr1, int[] arr2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: count the values, then drain the counts in a set order
     * <br>Target: time O(n + m + R), space O(R)    (R = largest possible value = 1000)
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1122RelativeSortArray::relativeSortArrayBruteForce);
        runAll("B", LC1122RelativeSortArray::relativeSortArrayComparator);
        runAll("C", LC1122RelativeSortArray::relativeSortArray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1122. Relative Sort Array)
    // https://leetcode.com/problems/relative-sort-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a reference solution.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] arr1, int[] arr2);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19},
                () -> s.solve(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19},
                        new int[]{2, 1, 4, 3, 9, 6}));
        check(v + " example 2", new int[]{22, 28, 8, 6, 17, 44},
                () -> s.solve(new int[]{28, 6, 22, 8, 44, 17}, new int[]{22, 28, 8, 6}));
        // edge: a single element
        check(v + " single element", new int[]{0},
                () -> s.solve(new int[]{0}, new int[]{0}));
        // edge: several leftover values, with duplicates, must come out ascending
        check(v + " leftovers ascending", new int[]{3, 3, 1, 5, 5, 7, 9},
                () -> s.solve(new int[]{7, 3, 5, 3, 1, 5, 9}, new int[]{3}));
        // edge: smallest and largest allowed values
        check(v + " boundary values", new int[]{1000, 1000, 0, 0, 7},
                () -> s.solve(new int[]{1000, 0, 1000, 0, 7}, new int[]{1000}));
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

package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 378. Kth Smallest Element in a Sorted Matrix   [Medium]
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * Topics: Array, Binary Search, Sorting, Heap (Priority Queue), Matrix
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #8
 *       also: ch. 11 Binary search on the answer (#10)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   matrix is an n x n grid in which every row and every column is sorted in non-decreasing
 *   order. Return the k-th smallest of all n * n entries. Equal entries count separately: it
 *   is the k-th element of the sorted list of all entries, not the k-th distinct value.
 *   The statement asks for memory better than O(n^2).
 *   Follow-up: can you do it with O(1) extra memory?
 *
 * EXAMPLES
 *   matrix = [[1,5,9],[10,11,13],[12,13,15]],  k = 8  ->  13
 *       all entries sorted: 1,5,9,10,11,12,13,13,15
 *   matrix = [[-5]],  k = 1  ->  -5
 *
 * CONSTRAINTS
 *   n == matrix.length == matrix[i].length,   1 <= n <= 300,   -10^9 <= matrix[i][j] <= 10^9,
 *   every row and every column is sorted in non-decreasing order,   1 <= k <= n^2
 * </pre>
 */
public class LC378KthSmallestElementInASortedMatrix {

    /**
     * Variant A - copy all entries into one array and sort it (O(n^2) memory, the baseline to beat)
     * <br>Target: time O(n^2 log n), space O(n^2)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int kthSmallestSorting(int[][] matrix, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - binary search on the value: count the entries <= mid with a staircase walk
     * <br>Target: time O(n log(max - min)), space O(1)
     * <br>Complete first: ch. 11 Binary search on the answer
     */
    public static int kthSmallestBinarySearch(int[][] matrix, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: k-way merge of the rows with a min-heap, stop at k
     * <br>Target: time O(k log min(n, k)), space O(min(n, k))
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     *     + ch. 18 Heaps and the k-selection family (later chapter)
     */
    public static int kthSmallest(int[][] matrix, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC378KthSmallestElementInASortedMatrix::kthSmallestSorting);
        runAll("B", LC378KthSmallestElementInASortedMatrix::kthSmallestBinarySearch);
        runAll("C", LC378KthSmallestElementInASortedMatrix::kthSmallest);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 378. Kth Smallest Element in a Sorted Matrix)
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected values checked against flatten-and-sort).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] matrix, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh matrix)
        check(v + " example 1", 13,
                () -> s.solve(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        check(v + " example 2", -5, () -> s.solve(new int[][]{{-5}}, 1));
        // edge: k = 1 (the top-left corner), with a repeated smallest value
        check(v + " k = 1", 1, () -> s.solve(new int[][]{{1, 2}, {1, 3}}, 1));
        // edge: k = n * n (the bottom-right corner)
        check(v + " k = n * n", 15,
                () -> s.solve(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 9));
        // edge: equal values count separately, extreme values of the constraints
        check(v + " duplicates and extremes", -1000000000,
                () -> s.solve(new int[][]{{-1000000000, -1000000000},
                        {-1000000000, 1000000000}}, 3));
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

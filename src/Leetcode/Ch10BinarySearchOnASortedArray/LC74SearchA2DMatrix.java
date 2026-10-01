package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 74. Search a 2D Matrix   [Medium]
 * https://leetcode.com/problems/search-a-2d-matrix/
 * Topics: Array, Binary Search, Matrix
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   matrix is an m x n grid of integers. Every row is sorted in non-decreasing order, and the
 *   first number of each row is larger than the last number of the row above it. Return true
 *   if target is somewhere in the grid, false otherwise. The intended solution runs in
 *   O(log(m * n)).
 *
 * EXAMPLES
 *   matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3   ->  true
 *   matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13  ->  false
 *
 * CONSTRAINTS
 *   m = matrix.length,   n = matrix[i].length,   1 <= m, n <= 100,
 *   -10^4 <= matrix[i][j], target <= 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/BinarySearch/LC74SearchA2DMatrix.java
 * </pre>
 */
public class LC74SearchA2DMatrix {

    /**
     * Variant A
     * <br>Target: time O(m * n), space O(1)
     */
    public static boolean searchMatrixA(int[][] matrix, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(log m + log n), space O(1)
     */
    public static boolean searchMatrixB(int[][] matrix, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(log(m * n)), space O(1)
     */
    public static boolean searchMatrixC(int[][] matrix, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC74SearchA2DMatrix::searchMatrixA);
        runAll("B", LC74SearchA2DMatrix::searchMatrixB);
        runAll("C", LC74SearchA2DMatrix::searchMatrixC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 74. Search a 2D Matrix)
    // https://leetcode.com/problems/search-a-2d-matrix/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a scan of every cell.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(int[][] matrix, int target);
    }

    private static void runAll(String v, Solver s) {
        int[][] grid = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        // official examples
        check(v + " example 1", true, () -> s.solve(grid, 3));
        check(v + " example 2", false, () -> s.solve(grid, 13));
        // edge: a 1 x 1 matrix
        check(v + " single cell", true, () -> s.solve(new int[][]{{1}}, 1));
        // edge: a single column, so every row holds just one number
        check(v + " single column", true, () -> s.solve(new int[][]{{1}, {3}, {5}}, 3));
        // edge: targets that are missing: below everything, between two rows, above everything
        check(v + " misses", new boolean[]{false, false, false},
                () -> new boolean[]{s.solve(grid, 0), s.solve(grid, 8), s.solve(grid, 61)});
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
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

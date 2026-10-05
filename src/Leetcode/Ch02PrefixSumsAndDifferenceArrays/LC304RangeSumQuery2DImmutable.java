package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;

/**
 * <pre>
 * LC 304. Range Sum Query 2D - Immutable   [Medium]
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * Topics: Array, Design, Matrix, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Design a class that is built once from a 2D integer matrix and then answers many
 *   rectangle-sum queries.
 *     NumMatrix(matrix)                       - prepares the object from the matrix
 *     sumRegion(row1, col1, row2, col2)       - returns the sum of all cells inside the
 *                                               rectangle whose upper left corner is
 *                                               (row1, col1) and lower right corner is
 *                                               (row2, col2), both corners included
 *   sumRegion must run in O(1) time.
 *
 * EXAMPLES
 *   matrix = [[3,0,1,4,2],
 *             [5,6,3,2,1],
 *             [1,2,0,1,5],
 *             [4,1,0,1,7],
 *             [1,0,3,0,5]]
 *   sumRegion(2,1,4,3) -> 8,   sumRegion(1,1,2,2) -> 11,   sumRegion(1,2,2,4) -> 12
 *
 * CONSTRAINTS
 *   m == matrix.length,   n == matrix[i].length,   1 <= m, n <= 200,
 *   -10^4 <= matrix[i][j] <= 10^4,   0 <= row1 <= row2 < m,   0 <= col1 <= col2 < n,
 *   at most 10^4 calls will be made to sumRegion
 * </pre>
 */
public class LC304RangeSumQuery2DImmutable {

    /**
     * Variant A - brute force: add up every cell of the rectangle on each query
     * <br>Target: time O(1) to build, O(m * n) per sumRegion, space O(1) extra
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static class NumMatrixBruteForce {
        public NumMatrixBruteForce(int[][] matrix) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B - a prefix-sum array per row; each query adds one slice per row
     * <br>Target: time O(m * n) to build, O(m) per sumRegion, space O(m * n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays (this chapter)
     */
    public static class NumMatrixRowPrefix {
        public NumMatrixRowPrefix(int[][] matrix) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant C - this chapter's technique: 2D prefix sums, each query reads four table entries
     * <br>Target: time O(m * n) to build, O(1) per sumRegion, space O(m * n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays (this chapter)
     */
    public static class NumMatrix {
        public NumMatrix(int[][] matrix) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", m -> {
            LC304RangeSumQuery2DImmutable.NumMatrixBruteForce nm =
                    new LC304RangeSumQuery2DImmutable.NumMatrixBruteForce(m);
            return nm::sumRegion;
        });
        runAll("B", m -> {
            LC304RangeSumQuery2DImmutable.NumMatrixRowPrefix nm =
                    new LC304RangeSumQuery2DImmutable.NumMatrixRowPrefix(m);
            return nm::sumRegion;
        });
        runAll("C", m -> {
            LC304RangeSumQuery2DImmutable.NumMatrix nm =
                    new LC304RangeSumQuery2DImmutable.NumMatrix(m);
            return nm::sumRegion;
        });
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 304. Range Sum Query 2D - Immutable)
    // https://leetcode.com/problems/range-sum-query-2d-immutable/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the official example
    // (one object, three queries) plus edge cases whose sums were added up by a separate
    // brute-force script. A case builds one object from a matrix and runs its queries in order;
    // the list of returned sums is compared.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // the one operation every variant offers
    interface Matrix {
        int sumRegion(int row1, int col1, int row2, int col2);
    }

    interface Factory {
        Matrix create(int[][] matrix);
    }

    private static final int[][] EXAMPLE = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}};
    private static void runAll(String v, Factory f) {
        // official example
        check(v + " example 1", new int[]{8, 11, 12}, f, EXAMPLE,
                new int[][]{{2, 1, 4, 3}, {1, 1, 2, 2}, {1, 2, 2, 4}});
        // edge: a 1 x 1 matrix with a negative value
        check(v + " single cell", new int[]{-5}, f, new int[][]{{-5}}, new int[][]{{0, 0, 0, 0}});
        // edge: the whole matrix and its two extreme corner cells (nothing before row 0 / col 0)
        check(v + " whole matrix and corners", new int[]{58, 3, 5}, f, EXAMPLE,
                new int[][]{{0, 0, 4, 4}, {0, 0, 0, 0}, {4, 4, 4, 4}});
        // edge: a full first row and a full first column
        check(v + " one row, one column", new int[]{10, 14}, f, EXAMPLE,
                new int[][]{{0, 0, 0, 4}, {0, 0, 4, 0}});
    }

    // one case: builds the object, runs the queries in order, compares the list of sums;
    // a wrong value or an exception counts as FAIL and the run always continues
    private static void check(String name, int[] expected, Factory f, int[][] matrix,
                              int[][] queries) {
        total++;
        String got;
        boolean ok = false;
        try {
            Matrix nm = f.create(matrix);
            int[] sums = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int[] q = queries[i];
                sums[i] = nm.sumRegion(q[0], q[1], q[2], q[3]);
            }
            ok = Arrays.equals(expected, sums);
            got = Arrays.toString(sums);
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + Arrays.toString(expected) + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

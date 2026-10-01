package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1030. Matrix Cells in Distance Order   [Easy]
 * https://leetcode.com/problems/matrix-cells-in-distance-order/
 * Topics: Array, Math, Geometry, Sorting, Matrix
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #9
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A matrix has rows x cols cells and you stand on the cell (rCenter, cCenter). Return the
 *   coordinates [r, c] of every cell of the matrix, ordered from the nearest cell to the
 *   farthest one. The distance between (r1, c1) and (r2, c2) is |r1 - r2| + |c1 - c2|.
 *   Cells at the same distance may come in any order - the test checks the distances.
 *
 * EXAMPLES
 *   rows = 1, cols = 2, rCenter = 0, cCenter = 0  ->  [[0,0],[0,1]]
 *   rows = 2, cols = 2, rCenter = 0, cCenter = 1  ->  [[0,1],[0,0],[1,1],[1,0]]
 *       distances 0,1,1,2; [[0,1],[1,1],[0,0],[1,0]] is accepted too
 *   rows = 2, cols = 3, rCenter = 1, cCenter = 2  ->  [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 *       distances 0,1,1,2,2,3; other orders of equal distances are accepted too
 *
 * CONSTRAINTS
 *   1 <= rows, cols <= 100,   0 <= rCenter < rows,   0 <= cCenter < cols
 * </pre>
 */
public class LC1030MatrixCellsInDistanceOrder {

    /**
     * Variant A - list every cell, then sort the cells by their distance from the centre
     * <br>Target: time O(N log N), space O(N)    (N = rows * cols)
     */
    public static int[][] allCellsDistOrderSort(int rows, int cols, int rCenter, int cCenter) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - breadth-first search outwards from the centre cell
     * <br>Target: time O(N), space O(N)
     */
    public static int[][] allCellsDistOrderBfs(int rows, int cols, int rCenter, int cCenter) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: bucket the cells by distance, then read the buckets
     * <br>Target: time O(N), space O(N)
     */
    public static int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1030MatrixCellsInDistanceOrder::allCellsDistOrderSort);
        runAll("B", LC1030MatrixCellsInDistanceOrder::allCellsDistOrderBfs);
        runAll("C", LC1030MatrixCellsInDistanceOrder::allCellsDistOrder);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1030. Matrix Cells in Distance Order)
    // https://leetcode.com/problems/matrix-cells-in-distance-order/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases. Cells at equal distance may come in any order, so each answer is
    // checked against the rules (every cell exactly once, distances never decrease) instead of
    // being compared with one fixed list.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int rows, int cols, int rCenter, int cCenter);
    }

    private static void runAll(String v, Solver s) {
        // official examples (any order of equal distances is accepted)
        check(v + " example 1", "valid", () -> verdict(1, 2, 0, 0, s.solve(1, 2, 0, 0)));
        check(v + " example 2", "valid", () -> verdict(2, 2, 0, 1, s.solve(2, 2, 0, 1)));
        check(v + " example 3", "valid", () -> verdict(2, 3, 1, 2, s.solve(2, 3, 1, 2)));
        // edge: a matrix with a single cell
        check(v + " single cell", "valid", () -> verdict(1, 1, 0, 0, s.solve(1, 1, 0, 0)));
        // edge: one row, centre in the middle, so cells at equal distance lie on both sides
        check(v + " one row", "valid", () -> verdict(1, 5, 0, 2, s.solve(1, 5, 0, 2)));
        // edge: the largest matrix, centre off to one side
        check(v + " 100 x 100", "valid",
                () -> verdict(100, 100, 37, 62, s.solve(100, 100, 37, 62)));
    }

    // "valid" when out lists every cell once with non-decreasing distance, otherwise a reason
    private static String verdict(int rows, int cols, int rCenter, int cCenter, int[][] out) {
        if (out == null) return "returned null";
        if (out.length != rows * cols) return out.length + " cells instead of " + rows * cols;
        boolean[][] seen = new boolean[rows][cols];
        int previous = -1;
        for (int i = 0; i < out.length; i++) {
            int[] cell = out[i];
            if (cell == null || cell.length != 2) return "entry " + i + " is not a [row, col] pair";
            int r = cell[0], c = cell[1];
            if (r < 0 || r >= rows || c < 0 || c >= cols) {
                return "cell [" + r + "," + c + "] is outside the matrix";
            }
            if (seen[r][c]) return "cell [" + r + "," + c + "] appears twice";
            seen[r][c] = true;
            int d = Math.abs(r - rCenter) + Math.abs(c - cCenter);
            if (d < previous) {
                return "cell [" + r + "," + c + "] at distance " + d
                        + " comes after distance " + previous;
            }
            previous = d;
        }
        return "valid";
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

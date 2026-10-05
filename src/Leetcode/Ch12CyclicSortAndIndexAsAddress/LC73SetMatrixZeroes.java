package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 73. Set Matrix Zeroes   [Medium]
 * https://leetcode.com/problems/set-matrix-zeroes/
 * Topics: Array, Hash Table, Matrix
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #8
 *       also: ch. 22 Matrix traversal and in-place simulation (#11)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Given an m x n integer matrix, whenever an element is 0, set its whole row and its
 *   whole column to 0. Do it in place (the methods return nothing, they change matrix).
 *
 * EXAMPLES
 *   matrix = [[1,1,1],[1,0,1],[1,1,1]]        ->  [[1,0,1],[0,0,0],[1,0,1]]
 *   matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]  ->  [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * CONSTRAINTS
 *   m == matrix.length,   n == matrix[0].length,   1 <= m, n <= 200,
 *   -2^31 <= matrix[i][j] <= 2^31 - 1
 *   Follow up: O(m * n) extra space is a bad idea, O(m + n) is better - can you use O(1)?
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Matrix/LC73SetMatrixZeroes.java
 * </pre>
 */
public class LC73SetMatrixZeroes {

    /**
     * Variant A - read the zeros from a copy of the matrix, write the changes into the original
     * <br>Target: time O(m * n), space O(m * n)
     * <br>Complete first: ch. 22 Matrix traversal and in-place simulation (later chapter)
     */
    public static void setZeroesCopy(int[][] matrix) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - two boolean arrays remember which rows and which columns hold a zero
     * <br>Target: time O(m * n), space O(m + n)
     * <br>Complete first: ch. 22 Matrix traversal and in-place simulation (later chapter)
     */
    public static void setZeroesFlags(int[][] matrix) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: the matrix's own first row and column hold the flags
     * <br>Target: time O(m * n), space O(1)
     * <br>Complete first: ch. 12 Cyclic sort and index-as-address (this chapter)
     *     + ch. 22 Matrix traversal and in-place simulation (later chapter)
     */
    public static void setZeroes(int[][] matrix) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC73SetMatrixZeroes::setZeroesCopy);
        runAll("B", LC73SetMatrixZeroes::setZeroesFlags);
        runAll("C", LC73SetMatrixZeroes::setZeroes);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 73. Set Matrix Zeroes)
    // https://leetcode.com/problems/set-matrix-zeroes/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (rows and columns
    // holding a zero, computed up front). The methods work in place: every case runs the
    // method on a fresh matrix and compares the matrix afterwards.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        void solve(int[][] matrix);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}},
                () -> after(s, new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        check(v + " example 2", new int[][]{{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}},
                () -> after(s, new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}}));
        // edge: the only zero is in the top-left corner
        check(v + " corner zero", new int[][]{{0, 0}, {0, 4}},
                () -> after(s, new int[][]{{0, 2}, {3, 4}}));
        // edge: the only zero is in the first row
        check(v + " first row zero", new int[][]{{0, 0, 0}, {4, 0, 6}},
                () -> after(s, new int[][]{{1, 0, 3}, {4, 5, 6}}));
        // edge: the only zero is in the first column
        check(v + " first column zero", new int[][]{{0, 2, 3}, {0, 0, 0}, {0, 8, 9}},
                () -> after(s, new int[][]{{1, 2, 3}, {0, 5, 6}, {7, 8, 9}}));
    }

    // runs the in-place method and returns the changed matrix
    private static int[][] after(Solver s, int[][] matrix) {
        s.solve(matrix);
        return matrix;
    }

    // one case: a wrong value, an exception or an endless loop counts as FAIL; the run continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got = hung > 0 ? "skipped (an earlier case of this variant never finished)"
                : callWithTimeout(actual);
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    // runs the case on its own thread, so that an endless loop cannot block the whole run
    private static Object callWithTimeout(Supplier<Object> actual) {
        Object[] box = new Object[1];
        Thread worker = new Thread(null, () -> {
            try {
                box[0] = actual.get();
            } catch (Throwable t) {
                box[0] = "threw " + t.getClass().getSimpleName();
            }
        }, "case", 1L << 26);
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(TIMEOUT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (worker.isAlive()) {
            hung++;
            return "no result after " + TIMEOUT_MS + " ms (endless loop?)";
        }
        return box[0];
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

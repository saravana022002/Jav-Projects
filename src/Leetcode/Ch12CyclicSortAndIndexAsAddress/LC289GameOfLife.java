package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 289. Game of Life   [Medium]
 * https://leetcode.com/problems/game-of-life/
 * Topics: Array, Matrix, Simulation
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #9
 *       also: ch. 22 Matrix traversal and in-place simulation (#12)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   board is an m x n grid of cells, each live (1) or dead (0). A cell has up to eight
 *   neighbours (horizontal, vertical and diagonal). The next generation is computed from
 *   the current one for ALL cells at the same moment, by these rules:
 *     - a live cell with fewer than two live neighbours dies
 *     - a live cell with two or three live neighbours stays alive
 *     - a live cell with more than three live neighbours dies
 *     - a dead cell with exactly three live neighbours becomes alive
 *   Change board in place into the next generation (the methods return nothing).
 *
 * EXAMPLES
 *   board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]  ->  [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *   board = [[1,1],[1,0]]                      ->  [[1,1],[1,1]]
 *
 * CONSTRAINTS
 *   m == board.length,   n == board[i].length,   1 <= m, n <= 25,   board[i][j] is 0 or 1
 *   Follow up: do it in place without using the already updated cells for the others.
 * </pre>
 */
public class LC289GameOfLife {

    /**
     * Variant A
     * <br>Target: time O(m * n), space O(m * n)
     */
    public static void gameOfLifeA(int[][] board) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(m * n), space O(1)
     */
    public static void gameOfLifeB(int[][] board) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC289GameOfLife::gameOfLifeA);
        runAll("B", LC289GameOfLife::gameOfLifeB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 289. Game of Life)
    // https://leetcode.com/problems/game-of-life/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference simulation. The
    // methods work in place: every case runs the method on a fresh board and compares the
    // board afterwards.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        void solve(int[][] board);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", new int[][]{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}},
                () -> after(s, new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}}));
        check(v + " example 2", new int[][]{{1, 1}, {1, 1}},
                () -> after(s, new int[][]{{1, 1}, {1, 0}}));
        // edge: a single live cell dies of loneliness
        check(v + " single cell", new int[][]{{0}}, () -> after(s, new int[][]{{1}}));
        // edge: a vertical bar of three turns into a horizontal bar
        check(v + " blinker", new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}},
                () -> after(s, new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}));
        // edge: one row, only the middle cell keeps two neighbours
        check(v + " single row", new int[][]{{0, 1, 0}}, () -> after(s, new int[][]{{1, 1, 1}}));
    }

    // runs the in-place method and returns the changed board
    private static int[][] after(Solver s, int[][] board) {
        s.solve(board);
        return board;
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

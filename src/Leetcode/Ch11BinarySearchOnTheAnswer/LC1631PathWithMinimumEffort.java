package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1631. Path With Minimum Effort   [Medium]
 * https://leetcode.com/problems/path-with-minimum-effort/
 * Topics: Array, Binary Search, Depth-First Search, Breadth-First Search, Union-Find,
 *         Heap (Priority Queue), Matrix, Dijkstra's Algorithm
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #9
 *       also: ch. 48 Dijkstra (#4), ch. 49 Bellman-Ford and negative weights (#6),
 *             ch. 50 Floyd-Warshall and all-pairs (#6),
 *             ch. 51 0-1 BFS and small-weight shortcuts (#12), ch. 52 Minimum spanning trees (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   heights is a rows x columns grid of cell heights. You start in the top-left cell and
 *   want to reach the bottom-right cell, moving one step at a time up, down, left or
 *   right. The effort of a route is the largest absolute height difference between two
 *   consecutive cells on it. Return the smallest effort over all routes.
 *
 * EXAMPLES
 *   heights = [[1,2,2],[3,8,2],[5,3,5]]  ->  2    route 1,3,5,3,5: its largest step is 2
 *   heights = [[1,2,3],[3,8,4],[5,3,5]]  ->  1    route 1,2,3,4,5: its largest step is 1
 *   heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]  ->  0
 *     (a route that only visits cells of height 1 exists, so no climbing is needed)
 *
 * CONSTRAINTS
 *   rows == heights.length,   columns == heights[i].length,   1 <= rows, columns <= 100,
 *   1 <= heights[i][j] <= 10^6
 * </pre>
 */
public class LC1631PathWithMinimumEffort {

    /**
     * Variant A
     * <br>Target: time O(R * C * H), space O(R * C)   (R rows, C columns, H = largest height)
     * <br>Complete first: ch. 44 Grids as graphs (later chapter;
     *       needs ch. 15, 34, 35, 42, 43 first)
     */
    public static int minimumEffortPathA(int[][] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(R * C * log(R * C)), space O(R * C)
     * <br>Complete first: ch. 44 Grids as graphs (later chapter;
     *       needs ch. 15, 34, 35, 42, 43 first)
     *     + ch. 48 Dijkstra (later chapter; needs ch. 18 first)
     */
    public static int minimumEffortPathB(int[][] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(R * C * log H), space O(R * C)
     * <br>Complete first: ch. 11 Binary search on the answer (this chapter)
     *     + ch. 44 Grids as graphs (later chapter; needs ch. 15, 34, 35, 42, 43 first)
     */
    public static int minimumEffortPathC(int[][] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1631PathWithMinimumEffort::minimumEffortPathA);
        runAll("B", LC1631PathWithMinimumEffort::minimumEffortPathB);
        runAll("C", LC1631PathWithMinimumEffort::minimumEffortPathC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1631. Path With Minimum Effort)
    // https://leetcode.com/problems/path-with-minimum-effort/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against two independent references (Dijkstra in Python
    // and, for the small grids, an enumeration of every simple route).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[][] heights);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
        check(v + " example 2", 1, () -> s.solve(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}));
        check(v + " example 3", 0, () -> s.solve(new int[][]{
                {1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}}));
        // edge: start and destination are the same cell
        check(v + " single cell", 0, () -> s.solve(new int[][]{{7}}));
        // edge: one row, the only route is forced and its step is the largest possible
        check(v + " huge step", 999999, () -> s.solve(new int[][]{{1, 1000000}}));
        // edge: the best route has to go up and left now and then
        check(v + " winding route", 1, () -> s.solve(new int[][]{
                {1, 9, 5, 6, 7}, {2, 9, 4, 9, 8}, {3, 4, 3, 9, 9}}));
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

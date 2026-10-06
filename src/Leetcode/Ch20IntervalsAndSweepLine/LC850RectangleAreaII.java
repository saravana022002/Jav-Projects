package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 850. Rectangle Area II   [Hard]
 * https://leetcode.com/problems/rectangle-area-ii/
 * Topics: Array, Segment Tree, Sweep Line, Ordered Set
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #12
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   rectangles[i] = [x1, y1, x2, y2] gives the bottom-left and the top-right corner of an
 *   axis-aligned rectangle (non-zero area). Return the total area of the plane that is covered
 *   by at least one rectangle - ground covered by several rectangles counts only once - modulo
 *   10^9 + 7.
 *
 * EXAMPLES
 *   rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]  ->  6    (a picture is on the LeetCode page)
 *   rectangles = [[0,0,1000000000,1000000000]]    ->  49   the area is 10^18, modulo 10^9 + 7
 *
 * CONSTRAINTS
 *   1 <= rectangles.length <= 200,   rectangles[i].length == 4,
 *   0 <= x_i1, y_i1, x_i2, y_i2 <= 10^9,   x_i1 <= x_i2,   y_i1 <= y_i2,
 *   all rectangles have non zero area
 * </pre>
 */
public class LC850RectangleAreaII {

    /**
     * Variant A
     * <br>Target: time O(n^3), space O(n^2)     (n = rectangles.length)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int rectangleAreaA(int[][] rectangles) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n^2 log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static int rectangleAreaB(int[][] rectangles) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 20 Intervals and sweep line (this chapter)
     *     + ch. 40 Segment trees (later chapter; needs ch. 39 first)
     */
    public static int rectangleAreaC(int[][] rectangles) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC850RectangleAreaII::rectangleAreaA);
        runAll("B", LC850RectangleAreaII::rectangleAreaB);
        runAll("C", LC850RectangleAreaII::rectangleAreaC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 850. Rectangle Area II)
    // https://leetcode.com/problems/rectangle-area-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a compressed-grid reference computed with exact
    // integer arithmetic before taking the modulo.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] rectangles);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 6,
                () -> s.solve(new int[][]{{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}}));
        check(v + " example 2", 49, () -> s.solve(new int[][]{{0, 0, 1000000000, 1000000000}}));
        // edge: two rectangles far apart, nothing between them is counted
        check(v + " disjoint", 2, () -> s.solve(new int[][]{{0, 0, 1, 1}, {2, 2, 3, 3}}));
        // edge: nested, touching and partly overlapping rectangles
        check(v + " mixed", 28,
                () -> s.solve(new int[][]{{0, 0, 3, 3}, {1, 1, 2, 2}, {2, 0, 5, 1}, {4, 0, 6, 4},
                        {0, 3, 6, 5}}));
        // edge: huge overlapping rectangles, the exact area is 10^18 - 1
        check(v + " huge overlap", 48,
                () -> s.solve(new int[][]{{0, 0, 999999999, 1000000000},
                        {1, 1, 1000000000, 1000000000}}));
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
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

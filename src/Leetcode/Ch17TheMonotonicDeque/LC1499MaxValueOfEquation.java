package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1499. Max Value of Equation   [Hard]
 * https://leetcode.com/problems/max-value-of-equation/
 * Topics: Array, Queue, Sliding Window, Heap (Priority Queue), Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #7
 *       also: ch. 75 DP optimisation (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   points[i] = [x_i, y_i] are points on a plane, already sorted by x with all x different.
 *   For two points i < j whose x-distance is at most k (x_j - x_i <= k) the value of the
 *   pair is  y_i + y_j + |x_i - x_j|.  Return the largest value over all such pairs.
 *   At least one pair is close enough, so an answer always exists.
 *
 * EXAMPLES
 *   points = [[1,3],[2,0],[5,10],[6,-10]],  k = 1  ->  4    3 + 0 + |1 - 2|; the other close
 *                                                            pair gives 10 - 10 + 1 = 1
 *   points = [[0,0],[3,0],[9,2]],           k = 3  ->  3    only the first two are close enough
 *
 * CONSTRAINTS
 *   2 <= points.length <= 10^5,   points[i].length == 2,   -10^8 <= x_i, y_i <= 10^8,
 *   0 <= k <= 2 * 10^8,   x_i < x_j for i < j (strictly increasing),
 *   at least one pair of points has |x_i - x_j| <= k
 * </pre>
 */
public class LC1499MaxValueOfEquation {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int findMaxValueOfEquationA(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     */
    public static int findMaxValueOfEquationB(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     */
    public static int findMaxValueOfEquationC(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1499MaxValueOfEquation::findMaxValueOfEquationA);
        runAll("B", LC1499MaxValueOfEquation::findMaxValueOfEquationB);
        runAll("C", LC1499MaxValueOfEquation::findMaxValueOfEquationC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1499. Max Value of Equation)
    // https://leetcode.com/problems/max-value-of-equation/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose answers were checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] points, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4,
                () -> s.solve(new int[][]{{1, 3}, {2, 0}, {5, 10}, {6, -10}}, 1));
        check(v + " example 2", 3, () -> s.solve(new int[][]{{0, 0}, {3, 0}, {9, 2}}, 3));
        // edge: two points at the extremes of the allowed ranges (the largest possible value)
        check(v + " extreme values", 400000000,
                () -> s.solve(new int[][]{{-100000000, 100000000}, {100000000, 100000000}},
                        200000000));
        // edge: the best pair skips a terrible point in between
        check(v + " skips a bad point", 22,
                () -> s.solve(new int[][]{{1, 10}, {2, -100}, {3, 10}}, 2));
        // edge: the point with the huge y is too far away to be used with the later points
        check(v + " stale candidate", 6,
                () -> s.solve(new int[][]{{0, 100}, {10, 1}, {11, 2}, {12, 3}}, 2));
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

package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1288. Remove Covered Intervals   [Medium]
 * https://leetcode.com/problems/remove-covered-intervals/
 * Topics: Array, Sorting
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #3
 *       also: ch. 76 Greedy and the exchange argument (#5),
 *             ch. 77 Scheduling and interval greedy (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   intervals[i] = [l, r] stands for the half-open interval [l, r). The interval [a, b) is
 *   covered by [c, d) exactly when c <= a and b <= d. Remove every interval that is covered by
 *   some other interval of the list and return how many intervals remain. All intervals in the
 *   input are different from each other.
 *
 * EXAMPLES
 *   intervals = [[1,4],[3,6],[2,8]]  ->  2     [3,6] is covered by [2,8]
 *   intervals = [[1,4],[2,3]]        ->  1
 *
 * CONSTRAINTS
 *   1 <= intervals.length <= 1000,   intervals[i].length == 2,   0 <= l_i < r_i <= 10^5,
 *   all the given intervals are unique
 * </pre>
 */
public class LC1288RemoveCoveredIntervals {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int removeCoveredIntervalsA(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(1)
     */
    public static int removeCoveredIntervalsB(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1288RemoveCoveredIntervals::removeCoveredIntervalsA);
        runAll("B", LC1288RemoveCoveredIntervals::removeCoveredIntervalsB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1288. Remove Covered Intervals)
    // https://leetcode.com/problems/remove-covered-intervals/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an every-pair-of-intervals reference.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] intervals);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[][]{{1, 4}, {3, 6}, {2, 8}}));
        check(v + " example 2", 1, () -> s.solve(new int[][]{{1, 4}, {2, 3}}));
        // edge: several intervals share one start, the shortest is listed first
        check(v + " equal starts", 1, () -> s.solve(new int[][]{{1, 2}, {1, 4}, {1, 3}}));
        // edge: equal ends
        check(v + " equal ends", 1, () -> s.solve(new int[][]{{3, 5}, {1, 5}, {4, 5}}));
        // edge: intervals overlap but none is covered
        check(v + " none covered", 4, () -> s.solve(new int[][]{{1, 3}, {2, 4}, {3, 5}, {4, 6}}));
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

package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 986. Interval List Intersections   [Medium]
 * https://leetcode.com/problems/interval-list-intersections/
 * Topics: Array, Two Pointers, Sweep Line
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #4
 *       also: ch. 8 Two pointers, converging (#12)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   firstList and secondList each hold closed intervals [start, end]. Inside one list the
 *   intervals are sorted and never overlap each other. Return every non-empty intersection
 *   of an interval of the first list with an interval of the second list, as a list of closed
 *   intervals in sorted order. Intervals that only touch at an endpoint intersect in that
 *   single point: [1,3] and [3,5] give [3,3].
 *   Below, m and n are the lengths of firstList and secondList.
 *
 * EXAMPLES
 *   firstList  = [[0,2],[5,10],[13,23],[24,25]]
 *   secondList = [[1,5],[8,12],[15,24],[25,26]]
 *       ->  [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *   firstList  = [[1,3],[5,9]],  secondList = []  ->  []
 *
 * CONSTRAINTS
 *   0 <= firstList.length, secondList.length <= 1000,   firstList.length + secondList.length >= 1,
 *   0 <= start < end <= 10^9 for every interval,
 *   inside a list each interval ends before the next one starts
 * </pre>
 */
public class LC986IntervalListIntersections {

    /**
     * Variant A - brute force: test every pair of intervals for an overlap
     * <br>Target: time O(m * n), space O(1) extra besides the answer
     */
    public static int[][] intervalIntersectionBruteForce(int[][] firstList, int[][] secondList) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - for each interval of one list, binary-search where its overlaps begin in the
     *             other list, then scan on while they still overlap
     * <br>Target: time O(m log n + k), space O(1) extra besides the answer   (k = intersections)
     */
    public static int[][] intervalIntersectionBinarySearch(int[][] firstList, int[][] secondList) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: merge-style walk over both lists, one pointer each
     * <br>Target: time O(m + n), space O(1) extra besides the answer
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC986IntervalListIntersections::intervalIntersectionBruteForce);
        runAll("B", LC986IntervalListIntersections::intervalIntersectionBinarySearch);
        runAll("C", LC986IntervalListIntersections::intervalIntersection);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 986. Interval List Intersections)
    // https://leetcode.com/problems/interval-list-intersections/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected values checked against an all-pairs reference).
    // The answer must list the intersections in sorted order, exactly as the examples do.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int[][] firstList, int[][] secondList);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1",
                new int[][]{{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}},
                () -> s.solve(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                        new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}}));
        check(v + " example 2", new int[][]{},
                () -> s.solve(new int[][]{{1, 3}, {5, 9}}, new int[][]{}));
        // edge: two intervals that only touch give a single point
        check(v + " touching endpoints", new int[][]{{3, 3}},
                () -> s.solve(new int[][]{{1, 3}}, new int[][]{{3, 5}}));
        // edge: one long interval covers several short ones of the other list
        check(v + " one covers many", new int[][]{{1, 2}, {5, 6}, {10, 20}},
                () -> s.solve(new int[][]{{0, 100}}, new int[][]{{1, 2}, {5, 6}, {10, 20}}));
        // edge: both lists non-empty, interleaved, but no overlap at all
        check(v + " interleaved, no overlap", new int[][]{},
                () -> s.solve(new int[][]{{1, 2}, {5, 6}}, new int[][]{{3, 4}, {7, 8}}));
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

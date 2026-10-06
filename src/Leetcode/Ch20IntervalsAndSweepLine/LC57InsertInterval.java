package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 57. Insert Interval   [Medium]
 * https://leetcode.com/problems/insert-interval/
 * Topics: Array
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   intervals holds non-overlapping closed intervals [start, end], sorted by start. Insert
 *   newInterval so that the result is still sorted and still has no overlaps, merging every
 *   interval the new one overlaps (sharing a single point counts as overlapping). Return the
 *   resulting intervals; you may build a new array instead of changing the input.
 *
 * EXAMPLES
 *   intervals = [[1,3],[6,9]],  newInterval = [2,5]  ->  [[1,5],[6,9]]
 *   intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]],  newInterval = [4,8]
 *       ->  [[1,2],[3,10],[12,16]]       [4,8] overlaps [3,5], [6,7] and [8,10]
 *
 * CONSTRAINTS
 *   0 <= intervals.length <= 10^4,   intervals[i].length == 2,   0 <= start_i <= end_i <= 10^5,
 *   intervals is sorted by start_i in ascending order,   newInterval.length == 2,
 *   0 <= start <= end <= 10^5
 * </pre>
 */
public class LC57InsertInterval {

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static int[][] insertA(int[][] intervals, int[] newInterval) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - binary-search the first and last overlapped intervals, then splice the answer
     * <br>Target: time O(n) (the search itself is O(log n)), space O(n)
     * <br>Complete first: ch. 10 Binary search on a sorted array
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static int[][] insertBinarySearch(int[][] intervals, int[] newInterval) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: one pass, no sort - copy, merge, copy
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 20 Intervals and sweep line (this chapter)
     */
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 20 Intervals and sweep line (this chapter)
     */
    public static int[][] insertB(int[][] intervals, int[] newInterval) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     */
    public static int[][] insertC(int[][] intervals, int[] newInterval) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC57InsertInterval::insertA);
        runAll("B", LC57InsertInterval::insertB);
        runAll("C", LC57InsertInterval::insertC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 57. Insert Interval)
    // https://leetcode.com/problems/insert-interval/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an append-sort-merge reference. The result must
    // be sorted by start (the statement asks for it), so the order is compared as returned.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int[][] intervals, int[] newInterval);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[][]{{1, 5}, {6, 9}},
                () -> s.solve(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}));
        check(v + " example 2", new int[][]{{1, 2}, {3, 10}, {12, 16}},
                () -> s.solve(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                        new int[]{4, 8}));
        // edge: no intervals yet
        check(v + " empty input", new int[][]{{5, 7}},
                () -> s.solve(new int[][]{}, new int[]{5, 7}));
        // edge: the new interval lies before all the others, without overlap
        check(v + " before all", new int[][]{{0, 1}, {3, 5}, {8, 9}},
                () -> s.solve(new int[][]{{3, 5}, {8, 9}}, new int[]{0, 1}));
        // edge: the new interval lies after all the others, without overlap
        check(v + " after all", new int[][]{{1, 2}, {3, 4}, {6, 7}},
                () -> s.solve(new int[][]{{1, 2}, {3, 4}}, new int[]{6, 7}));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, int[][] expected, Supplier<int[][]> actual) {
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
        if (o instanceof int[][]) return Arrays.deepToString((int[][]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

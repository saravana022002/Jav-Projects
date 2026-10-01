package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 56. Merge Intervals   [Medium]
 * https://leetcode.com/problems/merge-intervals/
 * Topics: Array, Sorting, Quicksort
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #1
 *       also: ch. 6 Sorting as a preprocessing step (#9),
 *             ch. 77 Scheduling and interval greedy (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   intervals[i] = [start, end] is a closed interval. Merge every group of overlapping
 *   intervals - two intervals that share even a single point, like [1,4] and [4,5], overlap -
 *   and return the resulting non-overlapping intervals, which cover exactly the same points
 *   as the input. (The test accepts the merged intervals in any order.)
 *
 * EXAMPLES
 *   intervals = [[1,3],[2,6],[8,10],[15,18]]  ->  [[1,6],[8,10],[15,18]]
 *   intervals = [[1,4],[4,5]]                 ->  [[1,5]]      touching intervals overlap
 *   intervals = [[4,7],[1,4]]                 ->  [[1,7]]
 *
 * CONSTRAINTS
 *   1 <= intervals.length <= 10^4,   intervals[i].length == 2,   0 <= start_i <= end_i <= 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Intervals/LC56MergeIntervals.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC56MergeIntervals {

    /**
     * Variant A - brute force: repeatedly merge any two overlapping intervals until none overlap
     * <br>Target: time O(n^3), space O(n)
     */
    public static int[][] mergeBruteForce(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - events: +1 at every start, -1 at every end; a merged interval ends at count 0
     * <br>Target: time O(n log n), space O(n)
     */
    public static int[][] mergeEventSweep(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: order the intervals by start, then sweep and extend
     * <br>Target: time O(n log n), space O(n)
     */
    public static int[][] merge(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC56MergeIntervals::mergeBruteForce);
        runAll("B", LC56MergeIntervals::mergeEventSweep);
        runAll("C", LC56MergeIntervals::merge);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 56. Merge Intervals)
    // https://leetcode.com/problems/merge-intervals/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a pairwise-merge reference. The merged intervals
    // do not overlap, so both sides are sorted by start before comparing (any output order passes).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int[][] intervals);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[][]{{1, 6}, {8, 10}, {15, 18}},
                () -> s.solve(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
        check(v + " example 2", new int[][]{{1, 5}}, () -> s.solve(new int[][]{{1, 4}, {4, 5}}));
        check(v + " example 3", new int[][]{{1, 7}}, () -> s.solve(new int[][]{{4, 7}, {1, 4}}));
        // edge: a single zero-length interval
        check(v + " single point", new int[][]{{7, 7}}, () -> s.solve(new int[][]{{7, 7}}));
        // edge: unsorted input, one interval swallows all the others
        check(v + " nested", new int[][]{{1, 10}},
                () -> s.solve(new int[][]{{6, 7}, {1, 10}, {2, 3}, {4, 5}}));
        // edge: unsorted, a gap of 1 stays open, a chain of touching intervals merges
        check(v + " gap and chain", new int[][]{{1, 2}, {3, 7}, {8, 8}},
                () -> s.solve(new int[][]{{8, 8}, {3, 4}, {1, 2}, {6, 7}, {4, 6}}));
    }

    // sorted by start, so the order of the merged intervals does not matter
    private static int[][] sorted(int[][] a) {
        int[][] copy = new int[a.length][];
        for (int i = 0; i < a.length; i++) copy[i] = a[i].clone();
        Arrays.sort(copy, (x, y) -> Integer.compare(x[0], y[0]));
        return copy;
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, int[][] expected, Supplier<int[][]> actual) {
        total++;
        Object got;
        try {
            got = sorted(actual.get());
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(sorted(expected), got);
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

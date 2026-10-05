package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 56. Merge Intervals   [Medium]
 * https://leetcode.com/problems/merge-intervals/
 * Topics: Array, Sorting, Quicksort
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #9
 *       also: ch. 20 Intervals and sweep line (#1), ch. 77 Scheduling and interval greedy (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   intervals[i] = [start_i, end_i]. Merge every group of overlapping intervals into one
 *   interval and return the resulting non-overlapping intervals, which together cover exactly
 *   what the input covered. Intervals that only touch at an endpoint, like [1,4] and [4,5],
 *   count as overlapping. The merged intervals may be returned in any order.
 *
 * EXAMPLES
 *   intervals = [[1,3],[2,6],[8,10],[15,18]]  ->  [[1,6],[8,10],[15,18]]   [1,3] and [2,6] merge
 *   intervals = [[1,4],[4,5]]                 ->  [[1,5]]                  touching counts
 *   intervals = [[4,7],[1,4]]                 ->  [[1,7]]                  input is not sorted
 *
 * CONSTRAINTS
 *   1 <= intervals.length <= 10^4,   intervals[i].length == 2,   0 <= start_i <= end_i <= 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Intervals/LC56MergeIntervals.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC56MergeIntervals {

    /**
     * Variant A - brute force: link every overlapping pair, merge each connected group
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 47 Union-find and dynamic connectivity (later chapter)
     */
    public static int[][] mergeOverlapGraph(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - sort the starts and the ends separately, sweep them counting open intervals
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     *     + ch. 20 Intervals and sweep line (later chapter)
     */
    public static int[][] mergeStartsAndEnds(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sort the intervals by start, then one merging pass
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static int[][] merge(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC56MergeIntervals::mergeOverlapGraph);
        runAll("B", LC56MergeIntervals::mergeStartsAndEnds);
        runAll("C", LC56MergeIntervals::merge);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 56. Merge Intervals)
    // https://leetcode.com/problems/merge-intervals/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a sort-and-sweep reference. The merged intervals
    // may come back in any order, so the result is sorted by start before comparing. Every call
    // gets fresh arrays.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int[][] intervals);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[][]{{1, 6}, {8, 10}, {15, 18}},
                () -> sorted(s.solve(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        check(v + " example 2", new int[][]{{1, 5}},
                () -> sorted(s.solve(new int[][]{{1, 4}, {4, 5}})));
        check(v + " example 3", new int[][]{{1, 7}},
                () -> sorted(s.solve(new int[][]{{4, 7}, {1, 4}})));
        // edge: a single interval with start == end
        check(v + " single point", new int[][]{{5, 5}},
                () -> sorted(s.solve(new int[][]{{5, 5}})));
        // edge: one big interval swallows the others, which come in unsorted
        check(v + " nested", new int[][]{{1, 10}},
                () -> sorted(s.solve(new int[][]{{6, 7}, {1, 10}, {4, 5}, {2, 3}})));
        // edge: neighbours 2 and 3 do not touch; an exact duplicate must collapse
        check(v + " gap and duplicate", new int[][]{{1, 2}, {3, 4}},
                () -> sorted(s.solve(new int[][]{{3, 4}, {1, 2}, {3, 4}})));
    }

    // copy of the intervals sorted by start, then end (null stays null so check() fails it)
    private static int[][] sorted(int[][] intervals) {
        if (intervals == null) return null;
        int[][] copy = new int[intervals.length][];
        for (int i = 0; i < intervals.length; i++) copy[i] = intervals[i].clone();
        Arrays.sort(copy, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0])
                : Integer.compare(a[1], b[1]));
        return copy;
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

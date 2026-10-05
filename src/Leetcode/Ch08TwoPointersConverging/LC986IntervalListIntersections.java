package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 986. Interval List Intersections   [Medium]
 * https://leetcode.com/problems/interval-list-intersections/
 * Topics: Array, Two Pointers, Sweep Line
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #12
 *       also: ch. 14 Merge and the k-way merge (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   firstList and secondList each hold closed intervals [start, end]. Inside one list the
 *   intervals are sorted and do not overlap. Return all intersections between an interval of
 *   the first list and an interval of the second list, in increasing order. The overlap of two
 *   closed intervals is either empty or again a closed interval - possibly a single point
 *   such as [5,5].
 *
 * EXAMPLES
 *   firstList  = [[0,2],[5,10],[13,23],[24,25]]
 *   secondList = [[1,5],[8,12],[15,24],[25,26]]
 *       ->  [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *   firstList  = [[1,3],[5,9]],   secondList = []   ->  []
 *
 * CONSTRAINTS
 *   0 <= firstList.length, secondList.length <= 1000,   firstList.length + secondList.length >= 1,
 *   0 <= start_i < end_i <= 10^9,   end_i < start_(i+1)   (same for both lists)
 * </pre>
 */
public class LC986IntervalListIntersections {

    /**
     * Variant A - brute force: compare every interval of one list with every one of the other
     * <br>Target: time O(m * n), space O(1) besides the result    (m, n = the two list lengths)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[][] intervalIntersectionBruteForce(int[][] firstList, int[][] secondList) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - for each interval of the first list, binary-search where it meets the second
     * <br>Target: time O(m log n + m + n), space O(1) besides the result
     * <br>Complete first: ch. 10 Binary search on a sorted array (later chapter)
     */
    public static int[][] intervalIntersectionBinarySearch(int[][] firstList, int[][] secondList) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: one pointer per list, both only moving forward
     * <br>Target: time O(m + n), space O(1) besides the result
     * <br>Complete first: ch. 8 Two pointers, converging (this chapter)
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
    // examples plus edge cases checked against a compare-every-pair reference. The result must
    // list the intersections in increasing order, exactly as in the official examples.
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
        check(v + " example 2", new int[0][],
                () -> s.solve(new int[][]{{1, 3}, {5, 9}}, new int[0][]));
        // edge: the first list is empty
        check(v + " first list empty", new int[0][],
                () -> s.solve(new int[0][], new int[][]{{4, 8}, {10, 12}}));
        // edge: one long interval covers several intervals of the other list
        check(v + " one interval covers many", new int[][]{{1, 2}, {5, 6}, {10, 20}},
                () -> s.solve(new int[][]{{0, 100}}, new int[][]{{1, 2}, {5, 6}, {10, 20}}));
        // edge: the intervals only touch, the overlap is a single point
        check(v + " touching endpoints", new int[][]{{3, 3}},
                () -> s.solve(new int[][]{{1, 3}}, new int[][]{{3, 5}}));
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

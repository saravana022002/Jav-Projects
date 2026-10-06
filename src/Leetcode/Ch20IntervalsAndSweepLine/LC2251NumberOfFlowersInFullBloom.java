package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2251. Number of Flowers in Full Bloom   [Hard]
 * https://leetcode.com/problems/number-of-flowers-in-full-bloom/
 * Topics: Array, Hash Table, Binary Search, Sorting, Prefix Sum, Ordered Set
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #9
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   flowers[i] = [start, end] means the i-th flower is in full bloom from time start to time
 *   end, both included. people[j] is the time at which the j-th person arrives. Return an array
 *   answer where answer[j] is the number of flowers in full bloom at time people[j].
 *
 * EXAMPLES
 *   flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]  ->  [1,2,2,2]
 *   flowers = [[1,10],[3,3]],              people = [3,3,2]     ->  [2,2,1]
 *
 * CONSTRAINTS
 *   1 <= flowers.length <= 5 * 10^4,   flowers[i].length == 2,   1 <= start_i <= end_i <= 10^9,
 *   1 <= people.length <= 5 * 10^4,   1 <= people[i] <= 10^9
 * </pre>
 */
public class LC2251NumberOfFlowersInFullBloom {

    /**
     * Variant A
     * <br>Target: time O(n * m), space O(1) besides the answer     (n flowers, m people)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] fullBloomFlowersA(int[][] flowers, int[] people) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O((n + m) log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 10 Binary search on a sorted array
     */
    public static int[] fullBloomFlowersB(int[][] flowers, int[] people) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O((n + m) log (n + m)), space O(n + m)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static int[] fullBloomFlowersC(int[][] flowers, int[] people) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2251NumberOfFlowersInFullBloom::fullBloomFlowersA);
        runAll("B", LC2251NumberOfFlowersInFullBloom::fullBloomFlowersB);
        runAll("C", LC2251NumberOfFlowersInFullBloom::fullBloomFlowersC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2251. Number of Flowers in Full Bloom)
    // https://leetcode.com/problems/number-of-flowers-in-full-bloom/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a scan-all-flowers reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[][] flowers, int[] people);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2, 2, 2},
                () -> s.solve(new int[][]{{1, 6}, {3, 7}, {9, 12}, {4, 13}},
                        new int[]{2, 3, 7, 11}));
        check(v + " example 2", new int[]{2, 2, 1},
                () -> s.solve(new int[][]{{1, 10}, {3, 3}}, new int[]{3, 3, 2}));
        // edge: a one-instant flower; arriving at its first or last moment counts
        check(v + " one instant", new int[]{0, 1, 0},
                () -> s.solve(new int[][]{{5, 5}}, new int[]{4, 5, 6}));
        // edge: values at the limits of the constraints
        check(v + " limits", new int[]{1, 2, 1},
                () -> s.solve(new int[][]{{1, 1000000000}, {1000000000, 1000000000}},
                        new int[]{1, 1000000000, 500000000}));
        // edge: nobody arrives while a flower is in bloom (before, between, after)
        check(v + " nobody sees a flower", new int[]{0, 0, 0},
                () -> s.solve(new int[][]{{10, 20}, {30, 40}}, new int[]{1, 25, 50}));
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
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

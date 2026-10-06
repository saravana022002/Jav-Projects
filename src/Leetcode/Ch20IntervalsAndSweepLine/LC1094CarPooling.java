package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1094. Car Pooling   [Medium]
 * https://leetcode.com/problems/car-pooling/
 * Topics: Array, Sorting, Heap (Priority Queue), Simulation, Prefix Sum
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #6
 *       also: ch. 2 Prefix sums and difference arrays (#11)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A car with `capacity` seats drives east only. trips[i] = [numPassengers, from, to] means
 *   that many people get in at kilometre `from` and get out at kilometre `to`. Where some
 *   people leave and others board at the same kilometre, the leaving ones get out first.
 *   Return true if the car never holds more than `capacity` people at any moment on the way,
 *   false otherwise.
 *
 * EXAMPLES
 *   trips = [[2,1,5],[3,3,7]], capacity = 4  ->  false   at km 3 the car holds 2 + 3 = 5 > 4
 *   trips = [[2,1,5],[3,3,7]], capacity = 5  ->  true    the peak is 5, which is allowed
 *
 * CONSTRAINTS
 *   1 <= trips.length <= 1000,   trips[i].length == 3,   1 <= numPassengers_i <= 100,
 *   0 <= from_i < to_i <= 1000,   1 <= capacity <= 10^5
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1094CarPooling.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC1094CarPooling {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static boolean carPoolingA(int[][] trips, int capacity) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n + L), space O(L)     (L = largest kilometre, at most 1000)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     */
    public static boolean carPoolingB(int[][] trips, int capacity) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static boolean carPoolingC(int[][] trips, int capacity) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1094CarPooling::carPoolingA);
        runAll("B", LC1094CarPooling::carPoolingB);
        runAll("C", LC1094CarPooling::carPoolingC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1094. Car Pooling)
    // https://leetcode.com/problems/car-pooling/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a load-at-every-kilometre reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(int[][] trips, int capacity);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", false, () -> s.solve(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
        check(v + " example 2", true, () -> s.solve(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5));
        // edge: passengers leave and board at the same kilometre (leaving first)
        check(v + " swap at one stop", true, () -> s.solve(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3));
        // edge: one trip alone is already too big
        check(v + " one trip too big", false, () -> s.solve(new int[][]{{5, 0, 1}}, 4));
        // edge: unsorted trips whose peak load (6) equals the capacity exactly
        check(v + " peak equals capacity", true,
                () -> s.solve(new int[][]{{3, 4, 8}, {1, 0, 10}, {2, 2, 6}}, 6));
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

package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 871. Minimum Number of Refueling Stops   [Hard]
 * https://leetcode.com/problems/minimum-number-of-refueling-stops/
 * Topics: Array, Dynamic Programming, Greedy, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A car drives east from position 0 to a destination `target` miles away, burning one
 *   liter per mile. Its tank has no size limit and holds startFuel liters at the start.
 *   stations[i] = [position_i, fuel_i] is a gas station position_i miles from the start with
 *   fuel_i liters; if the car stops there it takes all of that fuel. The stations are listed
 *   in increasing order of position. Return the minimum number of refueling stops needed to
 *   reach the destination, or -1 if it cannot be reached. Arriving at a station, or at the
 *   destination, with exactly 0 liters left is fine.
 *
 * EXAMPLES
 *   target = 1,   startFuel = 1,   stations = []                                  ->  0
 *   target = 100, startFuel = 1,   stations = [[10,100]]                          ->  -1
 *       the car cannot even reach the first station
 *   target = 100, startFuel = 10,  stations = [[10,60],[20,30],[30,30],[60,40]]   ->  2
 *       stop at position 10 (tank 60), drive to 60 and stop again (tank 40), then reach 100
 *
 * CONSTRAINTS
 *   1 <= target, startFuel <= 10^9,   0 <= stations.length <= 500,
 *   1 <= position_i < position_i+1 < target,   1 <= fuel_i < 10^9
 * </pre>
 */
public class LC871MinimumNumberOfRefuelingStops {

    /**
     * Variant A - dp: the furthest distance reachable with exactly i stops, station by station
     * <br>Target: time O(n^2), space O(n)     (n = number of stations)
     */
    public static int minRefuelStopsDP(int target, int startFuel, int[][] stations) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: drive on; when stuck, take the largest passed station
     * <br>Target: time O(n log n), space O(n)
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC871MinimumNumberOfRefuelingStops::minRefuelStopsDP);
        runAll("B", LC871MinimumNumberOfRefuelingStops::minRefuelStops);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 871. Minimum Number of Refueling Stops)
    // https://leetcode.com/problems/minimum-number-of-refueling-stops/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were checked against an exhaustive search over
    // every set of stops. Every call gets fresh arrays.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int target, int startFuel, int[][] stations);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 0, () -> s.solve(1, 1, new int[][]{}));
        check(v + " example 2", -1, () -> s.solve(100, 1, new int[][]{{10, 100}}));
        check(v + " example 3", 2,
                () -> s.solve(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
        // edge: arrives at the station and at the target with exactly 0 liters left
        check(v + " exact fuel", 1, () -> s.solve(100, 10, new int[][]{{10, 90}}));
        // edge: after the first stop the next station is still out of reach
        check(v + " stuck after a stop", -1,
                () -> s.solve(100, 10, new int[][]{{10, 5}, {30, 100}}));
        // edge: the starting fuel already reaches the target, stations are not needed
        check(v + " no stop needed", 0, () -> s.solve(50, 60, new int[][]{{10, 5}, {20, 5}}));
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

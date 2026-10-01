package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1109. Corporate Flight Bookings   [Medium]
 * https://leetcode.com/problems/corporate-flight-bookings/
 * Topics: Array, Prefix Sum
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #7
 *       also: ch. 2 Prefix sums and difference arrays (#10), ch. 39 Fenwick trees (#10)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Flights are labelled 1 to n. bookings[i] = [first, last, seats] reserves `seats` seats on
 *   every flight from first to last (both included). Return an array answer of length n in which
 *   answer[i] is the total number of seats reserved on flight i + 1 (answer[0] is flight 1).
 *
 * EXAMPLES
 *   bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5  ->  [10,55,45,25,25]
 *       flight 1: 10;  flight 2: 10 + 20 + 25 = 55;  flight 3: 20 + 25 = 45;  flights 4, 5: 25
 *   bookings = [[1,2,10],[2,2,15]],          n = 2  ->  [10,25]
 *
 * CONSTRAINTS
 *   1 <= n <= 2 * 10^4,   1 <= bookings.length <= 2 * 10^4,   bookings[i].length == 3,
 *   1 <= first_i <= last_i <= n,   1 <= seats_i <= 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC1109CorporateFlightBookings.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC1109CorporateFlightBookings {

    /**
     * Variant A
     * <br>Target: time O(m * n), space O(1) besides the answer     (m = bookings.length)
     */
    public static int[] corpFlightBookingsA(int[][] bookings, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(m + n), space O(n)
     */
    public static int[] corpFlightBookingsB(int[][] bookings, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(m log m + n), space O(m + n)
     */
    public static int[] corpFlightBookingsC(int[][] bookings, int n) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1109CorporateFlightBookings::corpFlightBookingsA);
        runAll("B", LC1109CorporateFlightBookings::corpFlightBookingsB);
        runAll("C", LC1109CorporateFlightBookings::corpFlightBookingsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1109. Corporate Flight Bookings)
    // https://leetcode.com/problems/corporate-flight-bookings/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an add-to-every-flight reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[][] bookings, int n);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{10, 55, 45, 25, 25},
                () -> s.solve(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5));
        check(v + " example 2", new int[]{10, 25},
                () -> s.solve(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2));
        // edge: a single flight
        check(v + " single flight", new int[]{7}, () -> s.solve(new int[][]{{1, 1, 7}}, 1));
        // edge: bookings stacked on the whole range
        check(v + " whole range", new int[]{7, 7, 7, 7, 7},
                () -> s.solve(new int[][]{{1, 5, 3}, {1, 5, 4}}, 5));
        // edge: bookings reach the last flight, and flight 1 has no seats at all
        check(v + " last flight", new int[]{0, 1, 6},
                () -> s.solve(new int[][]{{3, 3, 5}, {2, 3, 1}}, 3));
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

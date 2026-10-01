package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1870. Minimum Speed to Arrive on Time   [Medium]
 * https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #8
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   You have hour hours (a real number) to reach the office and must take n trains in the
 *   given order; dist[i] is the length in km of the i-th ride. All trains travel at the
 *   same speed, which you choose. A train can only depart at a whole hour, so after a ride
 *   that ends between two whole hours you wait for the next one (no waiting is needed
 *   after the last ride). Return the smallest positive integer speed (km/h) that gets
 *   you there in time, or -1 if no speed does. Tests are built so that a valid answer never
 *   exceeds 10^7.
 *
 * EXAMPLES
 *   dist = [1,3,2], hour = 6    ->  1    rides take 1 + 3 + 2 = 6 hours, exactly on time
 *   dist = [1,3,2], hour = 2.7  ->  3    1/3 h (wait to hour 1), then 1 h, then 2/3 h = 2.67 h
 *   dist = [1,3,2], hour = 1.9  ->  -1   the last train cannot depart before hour 2
 *
 * CONSTRAINTS
 *   n == dist.length,   1 <= n <= 10^5,   1 <= dist[i] <= 10^5,   1 <= hour <= 10^9,
 *   hour has at most two digits after the decimal point
 * </pre>
 */
public class LC1870MinimumSpeedToArriveOnTime {

    /**
     * Variant A
     * <br>Target: time O(n * 10^7), space O(1)
     */
    public static int minSpeedOnTimeA(int[] dist, double hour) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log 10^7), space O(1)
     */
    public static int minSpeedOnTimeB(int[] dist, double hour) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1870MinimumSpeedToArriveOnTime::minSpeedOnTimeA);
        runAll("B", LC1870MinimumSpeedToArriveOnTime::minSpeedOnTimeB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1870. Minimum Speed to Arrive on Time)
    // https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent reference that uses exact
    // integer arithmetic on the hundredths of an hour.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] dist, double hour);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{1, 3, 2}, 6));
        check(v + " example 2", 3, () -> s.solve(new int[]{1, 3, 2}, 2.7));
        check(v + " example 3", -1, () -> s.solve(new int[]{1, 3, 2}, 1.9));
        // edge: hour equals the number of rides minus one, the last ride needs positive time
        check(v + " too tight", -1, () -> s.solve(new int[]{1, 1}, 1.0));
        // edge: a fractional hour that is only just enough
        check(v + " just enough", 100, () -> s.solve(new int[]{1, 1}, 1.01));
        // edge: the biggest possible answer
        check(v + " answer 10^7", 10000000, () -> s.solve(new int[]{1, 1, 100000}, 2.01));
    }

    // one case: a wrong value, an exception or an endless loop counts as FAIL; the run continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got = hung > 0 ? "skipped (an earlier case of this variant never finished)"
                : callWithTimeout(actual);
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    // runs the case on its own thread, so that an endless loop cannot block the whole run
    private static Object callWithTimeout(Supplier<Object> actual) {
        Object[] box = new Object[1];
        Thread worker = new Thread(null, () -> {
            try {
                box[0] = actual.get();
            } catch (Throwable t) {
                box[0] = "threw " + t.getClass().getSimpleName();
            }
        }, "case", 1L << 26);
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(TIMEOUT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (worker.isAlive()) {
            hung++;
            return "no result after " + TIMEOUT_MS + " ms (endless loop?)";
        }
        return box[0];
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

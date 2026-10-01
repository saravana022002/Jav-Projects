package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1011. Capacity To Ship Packages Within D Days   [Medium]
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Packages sit on a conveyor belt and must all be shipped within the given number of
 *   days. weights[i] is the weight of the i-th package. Every day the ship is loaded with
 *   packages in belt order (no reordering, no skipping), never carrying more than its
 *   weight capacity. Return the smallest capacity that gets everything shipped in time.
 *
 * EXAMPLES
 *   weights = [1,2,3,4,5,6,7,8,9,10], days = 5  ->  15    (1..5) | (6,7) | (8) | (9) | (10)
 *   weights = [3,2,2,4,1,4],          days = 3  ->  6     (3,2) | (2,4) | (1,4)
 *   weights = [1,2,3,1,1],            days = 4  ->  3     (1) | (2) | (3) | (1,1)
 *
 * CONSTRAINTS
 *   1 <= days <= weights.length <= 5 * 10^4,   1 <= weights[i] <= 500
 * </pre>
 */
public class LC1011CapacityToShipPackagesWithinDDays {

    /**
     * Variant A
     * <br>Target: time O(n * sum(weights)), space O(1)
     */
    public static int shipWithinDaysA(int[] weights, int days) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log sum(weights)), space O(1)
     */
    public static int shipWithinDaysB(int[] weights, int days) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1011CapacityToShipPackagesWithinDDays::shipWithinDaysA);
        runAll("B", LC1011CapacityToShipPackagesWithinDDays::shipWithinDaysB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1011. Capacity To Ship Packages Within D Days)
    // https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent brute-force reference.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] weights, int days);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 15, () -> s.solve(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        check(v + " example 2", 6, () -> s.solve(new int[]{3, 2, 2, 4, 1, 4}, 3));
        check(v + " example 3", 3, () -> s.solve(new int[]{1, 2, 3, 1, 1}, 4));
        // edge: a single package
        check(v + " one package", 500, () -> s.solve(new int[]{500}, 1));
        // edge: one day only, everything goes on the same ship
        check(v + " one day", 10, () -> s.solve(new int[]{5, 1, 4}, 1));
        // edge: one day per package, the heaviest package decides
        check(v + " day per package", 9, () -> s.solve(new int[]{3, 9, 2}, 3));
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

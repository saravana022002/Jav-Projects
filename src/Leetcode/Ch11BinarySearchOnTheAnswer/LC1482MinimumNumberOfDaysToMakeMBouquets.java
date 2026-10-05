package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1482. Minimum Number of Days to Make m Bouquets   [Medium]
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #5
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A garden is a row of n flowers; flower i blooms on day bloomDay[i] and from then on
 *   can be used in exactly one bouquet. A bouquet needs k flowers that are next to each
 *   other in the row, all bloomed. Return the minimum number of days to wait until m
 *   bouquets can be made, or -1 if the garden can never provide them.
 *
 * EXAMPLES
 *   bloomDay = [1,10,3,10,2],     m = 3, k = 1  ->  3    flowers 0, 4, 2 are open by day 3
 *   bloomDay = [1,10,3,10,2],     m = 3, k = 2  ->  -1   needs 6 flowers, the garden has 5
 *   bloomDay = [7,7,7,7,12,7,7],  m = 2, k = 3  ->  12   on day 7 only one bouquet fits
 *
 * CONSTRAINTS
 *   bloomDay.length == n,   1 <= n <= 10^5,   1 <= bloomDay[i] <= 10^9,
 *   1 <= m <= 10^6,   1 <= k <= n
 * </pre>
 */
public class LC1482MinimumNumberOfDaysToMakeMBouquets {

    /**
     * Variant A - brute force: try each distinct bloom day in increasing order, count bouquets
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int minDaysTryEachDay(int[] bloomDay, int m, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: binary search on the day, count runs of k open flowers
     * <br>Target: time O(n log max(bloomDay)), space O(1)
     * <br>Complete first: ch. 11 Binary search on the answer (this chapter)
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1482MinimumNumberOfDaysToMakeMBouquets::minDaysTryEachDay);
        runAll("B", LC1482MinimumNumberOfDaysToMakeMBouquets::minDays);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1482. Minimum Number of Days to Make m Bouquets)
    // https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent brute-force reference.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] bloomDay, int m, int k);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{1, 10, 3, 10, 2}, 3, 1));
        check(v + " example 2", -1, () -> s.solve(new int[]{1, 10, 3, 10, 2}, 3, 2));
        check(v + " example 3", 12, () -> s.solve(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
        // edge: a single flower
        check(v + " one flower", 5, () -> s.solve(new int[]{5}, 1, 1));
        // edge: on day 1 enough flowers are open, but no two of them are next to each other
        check(v + " not adjacent", 100, () -> s.solve(new int[]{1, 100, 1, 100, 1, 100, 1}, 2, 2));
        // edge: m * k is far larger than the number of flowers
        check(v + " far too many bouquets", -1, () -> s.solve(filled(3000, 1), 1000000, 2148));
    }

    private static int[] filled(int n, int value) {
        int[] a = new int[n];
        Arrays.fill(a, value);
        return a;
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

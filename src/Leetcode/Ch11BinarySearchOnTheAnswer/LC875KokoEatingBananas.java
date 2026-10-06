package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 875. Koko Eating Bananas   [Medium]
 * https://leetcode.com/problems/koko-eating-bananas/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   There are n piles of bananas; pile i holds piles[i] bananas. The guards come back in
 *   h hours. Koko picks one eating speed k (bananas per hour) and keeps it. Every hour she
 *   chooses a pile and eats up to k bananas from it; if the pile has fewer than k left she
 *   finishes it and the rest of that hour is wasted (she never switches piles mid-hour).
 *   Return the smallest integer k that lets her eat every banana within h hours.
 *
 * EXAMPLES
 *   piles = [3,6,7,11],       h = 8  ->  4
 *   piles = [30,11,23,4,20],  h = 5  ->  30
 *   piles = [30,11,23,4,20],  h = 6  ->  23
 *
 * CONSTRAINTS
 *   1 <= piles.length <= 10^4,   piles.length <= h <= 10^9,   1 <= piles[i] <= 10^9
 * </pre>
 */
public class LC875KokoEatingBananas {

    /**
     * Variant A
     * <br>Target: time O(n * max(piles)), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int minEatingSpeedA(int[] piles, int h) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log max(piles)), space O(1)
     * <br>Complete first: ch. 11 Binary search on the answer (this chapter)
     */
    public static int minEatingSpeedB(int[] piles, int h) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC875KokoEatingBananas::minEatingSpeedA);
        runAll("B", LC875KokoEatingBananas::minEatingSpeedB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 875. Koko Eating Bananas)
    // https://leetcode.com/problems/koko-eating-bananas/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent brute-force reference.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] piles, int h);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 4, () -> s.solve(new int[]{3, 6, 7, 11}, 8));
        check(v + " example 2", 30, () -> s.solve(new int[]{30, 11, 23, 4, 20}, 5));
        check(v + " example 3", 23, () -> s.solve(new int[]{30, 11, 23, 4, 20}, 6));
        // edge: plenty of time, the slowest possible speed is enough
        check(v + " speed one", 1, () -> s.solve(new int[]{3, 2, 1}, 6));
        // edge: a single huge pile
        check(v + " one huge pile", 2, () -> s.solve(new int[]{312884470}, 312884469));
        // edge: several piles of the maximum size
        check(v + " big piles", 4,
                () -> s.solve(new int[]{1000000000, 1000000000, 1000000000}, 1000000000));
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

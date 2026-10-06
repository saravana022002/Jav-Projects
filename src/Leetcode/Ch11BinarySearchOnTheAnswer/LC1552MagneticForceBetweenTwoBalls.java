package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1552. Magnetic Force Between Two Balls   [Medium]
 * https://leetcode.com/problems/magnetic-force-between-two-balls/
 * Topics: Array, Binary Search, Sorting
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Basket i sits at position[i] on a line. You must put m balls into m different
 *   baskets. Two balls placed at x and y pull on each other with force |x - y|.
 *   Choose the baskets so that the weakest pull between any two balls is as strong as
 *   possible, and return that strength.
 *
 * EXAMPLES
 *   position = [1,2,3,4,7],            m = 3  ->  3            baskets 1, 4, 7 (gaps 3, 3)
 *   position = [5,4,3,2,1,1000000000], m = 2  ->  999999999    baskets 1 and 1000000000
 *
 * CONSTRAINTS
 *   n == position.length,   2 <= n <= 10^5,   1 <= position[i] <= 10^9,
 *   all integers in position are distinct,   2 <= m <= position.length
 * </pre>
 */
public class LC1552MagneticForceBetweenTwoBalls {

    /**
     * Variant A
     * <br>Target: time O(C(n, m) * m), space O(m)
     * <br>Complete first: ch. 57 Backtracking (later chapter; needs ch. 56 first)
     */
    public static int maxDistanceA(int[] position, int m) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n + n log(max - min)), space O(1) extra
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 11 Binary search on the answer (this chapter)
     */
    public static int maxDistanceB(int[] position, int m) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n + n log(max - min)), space O(1) extra
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 11 Binary search on the answer (this chapter)
     */
    public static int maxDistanceC(int[] position, int m) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1552MagneticForceBetweenTwoBalls::maxDistanceA);
        runAll("B", LC1552MagneticForceBetweenTwoBalls::maxDistanceB);
        runAll("C", LC1552MagneticForceBetweenTwoBalls::maxDistanceC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1552. Magnetic Force Between Two Balls)
    // https://leetcode.com/problems/magnetic-force-between-two-balls/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent brute force that tries every
    // choice of m baskets.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] position, int m);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{1, 2, 3, 4, 7}, 3));
        check(v + " example 2", 999999999,
                () -> s.solve(new int[]{5, 4, 3, 2, 1, 1000000000}, 2));
        // edge: two baskets, two balls
        check(v + " two baskets", 4, () -> s.solve(new int[]{3, 7}, 2));
        // edge: every basket gets a ball, positions not sorted
        check(v + " ball in every basket", 3, () -> s.solve(new int[]{10, 1, 4}, 3));
        // edge: evenly spaced baskets
        check(v + " evenly spaced", 2, () -> s.solve(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 5));
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

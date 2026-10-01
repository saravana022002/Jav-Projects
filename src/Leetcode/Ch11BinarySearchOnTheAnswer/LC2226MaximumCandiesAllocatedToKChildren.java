package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2226. Maximum Candies Allocated to K Children   [Medium]
 * https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #7
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   candies[i] is the size of the i-th pile of candies. A pile can be divided into any
 *   number of smaller piles, but piles can never be merged. Hand candies to k children so
 *   that every child gets the same amount, each child takes from one pile only, and some
 *   piles may stay unused. Return the largest amount every child can get (0 if the
 *   children cannot all get at least one candy).
 *
 * EXAMPLES
 *   candies = [5,8,6],  k = 3   ->  5    8 -> 5 + 3 and 6 -> 5 + 1 give three piles of 5
 *   candies = [2,5],    k = 11  ->  0    only 7 candies for 11 children
 *
 * CONSTRAINTS
 *   1 <= candies.length <= 10^5,   1 <= candies[i] <= 10^7,   1 <= k <= 10^12
 * </pre>
 */
public class LC2226MaximumCandiesAllocatedToKChildren {

    /**
     * Variant A
     * <br>Target: time O(n * max(candies)), space O(1)
     */
    public static int maximumCandiesA(int[] candies, long k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log max(candies)), space O(1)
     */
    public static int maximumCandiesB(int[] candies, long k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2226MaximumCandiesAllocatedToKChildren::maximumCandiesA);
        runAll("B", LC2226MaximumCandiesAllocatedToKChildren::maximumCandiesB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 2226. Maximum Candies Allocated to K Children)
    // https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent brute-force reference.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] candies, long k);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 5, () -> s.solve(new int[]{5, 8, 6}, 3L));
        check(v + " example 2", 0, () -> s.solve(new int[]{2, 5}, 11L));
        // edge: a single child takes the biggest pile whole
        check(v + " one child", 10, () -> s.solve(new int[]{1, 10, 3}, 1L));
        // edge: every pile splits exactly
        check(v + " exact split", 2, () -> s.solve(new int[]{4, 4}, 4L));
        // edge: k is larger than any int
        check(v + " huge k", 0, () -> s.solve(new int[]{10000000}, 1000000000000L));
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

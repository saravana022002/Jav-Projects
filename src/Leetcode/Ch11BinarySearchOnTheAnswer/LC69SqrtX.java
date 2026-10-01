package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 69. Sqrt(x)   [Easy]
 * https://leetcode.com/problems/sqrtx/
 * Topics: Math, Binary Search, Newton's Method
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Given a non-negative integer x, return its square root rounded down to an integer,
 *   i.e. the largest s with s * s <= x. Built-in power / exponent functions and operators
 *   (pow, ** 0.5, ...) are not allowed.
 *
 * EXAMPLES
 *   x = 4  ->  2     sqrt(4) = 2 exactly
 *   x = 8  ->  2     sqrt(8) = 2.828..., rounded down
 *
 * CONSTRAINTS
 *   0 <= x <= 2^31 - 1
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/BinarySearch/LC69SqrtX.java
 * </pre>
 */
public class LC69SqrtX {

    /**
     * Variant A - brute force: try 0, 1, 2, ... for as long as the square still fits
     * <br>Target: time O(sqrt(x)), space O(1)
     */
    public static int mySqrtLinearScan(int x) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - Newton's method: keep averaging a guess r with x / r
     * <br>Target: time O(log x), space O(1)
     */
    public static int mySqrtNewton(int x) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: binary search on the answer, test mid * mid <= x
     * <br>Target: time O(log x), space O(1)
     */
    public static int mySqrt(int x) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC69SqrtX::mySqrtLinearScan);
        runAll("B", LC69SqrtX::mySqrtNewton);
        runAll("C", LC69SqrtX::mySqrt);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 69. Sqrt(x))
    // https://leetcode.com/problems/sqrtx/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (exact integer root).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int x);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 2, () -> s.solve(4));
        check(v + " example 2", 2, () -> s.solve(8));
        // edge: the smallest allowed input
        check(v + " zero", 0, () -> s.solve(0));
        // edge: smallest input whose answer is not zero
        check(v + " one", 1, () -> s.solve(1));
        // edge: the largest allowed input
        check(v + " max int", 46340, () -> s.solve(2147483647));
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

package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 739. Daily Temperatures   [Medium]
 * https://leetcode.com/problems/daily-temperatures/
 * Topics: Array, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #3
 *       also: ch. 16 The monotonic stack (#1)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   temperatures[i] is the temperature on day i. For every day, find how many days you have
 *   to wait until a strictly warmer day arrives, and return those waits as an array of the
 *   same length. When no later day is warmer, the answer for that day is 0.
 *
 * EXAMPLES
 *   temperatures = [73,74,75,71,69,72,76,73]  ->  [1,1,4,2,1,1,0,0]
 *   temperatures = [30,40,50,60]              ->  [1,1,1,0]
 *   temperatures = [30,60,90]                 ->  [1,1,0]
 *
 * CONSTRAINTS
 *   1 <= temperatures.length <= 10^5,   30 <= temperatures[i] <= 100
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Stack/LC739DailyTemperaturesRightToLeft.java
 *   src/Practice/Stack/LC739DailyTemperaturesLeftToRight.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC739DailyTemperatures {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting (this chapter)
     */
    public static int[] dailyTemperaturesA(int[] temperatures) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n * R), space O(R)   (R = 71, the number of possible temperatures)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform (later chapter)
     */
    public static int[] dailyTemperaturesB(int[] temperatures) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting (this chapter)
     *     + ch. 16 The monotonic stack (later chapter; needs ch. 15 first)
     */
    public static int[] dailyTemperaturesC(int[] temperatures) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC739DailyTemperatures::dailyTemperaturesA);
        runAll("B", LC739DailyTemperatures::dailyTemperaturesB);
        runAll("C", LC739DailyTemperatures::dailyTemperaturesC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 739. Daily Temperatures)
    // https://leetcode.com/problems/daily-temperatures/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] temperatures);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                () -> s.solve(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        check(v + " example 2", new int[]{1, 1, 1, 0}, () -> s.solve(new int[]{30, 40, 50, 60}));
        check(v + " example 3", new int[]{1, 1, 0}, () -> s.solve(new int[]{30, 60, 90}));
        // edge: a single day never has a warmer day after it
        check(v + " single day", new int[]{0}, () -> s.solve(new int[]{50}));
        // edge: equal temperatures are not warmer
        check(v + " all equal", new int[]{0, 0, 0}, () -> s.solve(new int[]{70, 70, 70}));
        // edge: a long cold spell, then the very first warm day answers every earlier day
        check(v + " late warm day", new int[]{4, 3, 2, 1, 0, 0},
                () -> s.solve(new int[]{90, 80, 70, 60, 100, 100}));
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

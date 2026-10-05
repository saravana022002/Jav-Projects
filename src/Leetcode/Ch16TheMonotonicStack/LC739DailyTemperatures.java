package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 739. Daily Temperatures   [Medium]
 * https://leetcode.com/problems/daily-temperatures/
 * Topics: Array, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #1
 *       also: ch. 1 Complexity, amortisation, and counting (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   temperatures[i] is the temperature on day i. For every day i, work out how many days you
 *   have to wait after day i until a strictly warmer day arrives. Return these waiting times
 *   as an array; use 0 for a day after which no warmer day ever comes.
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
     * Variant A - brute force: for every day, scan the following days until a warmer one shows up
     * <br>Target: time O(n^2), space O(1)    (besides the answer array)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] dailyTemperaturesBruteForce(int[] temperatures) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - go right to left, remember the latest day of each temperature
     * <br>Target: time O(n * R), space O(R)    (R = 71 possible temperatures)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     */
    public static int[] dailyTemperaturesLatestDay(int[] temperatures) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: stack of the days that are still waiting
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC739DailyTemperatures::dailyTemperaturesBruteForce);
        runAll("B", LC739DailyTemperatures::dailyTemperaturesLatestDay);
        runAll("C", LC739DailyTemperatures::dailyTemperatures);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 739. Daily Temperatures)
    // https://leetcode.com/problems/daily-temperatures/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent brute-force reference.
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
        // edge: a single day
        check(v + " single day", new int[]{0}, () -> s.solve(new int[]{50}));
        // edge: temperatures only fall, so nobody ever waits for a warmer day
        check(v + " falling", new int[]{0, 0, 0, 0, 0, 0, 0, 0},
                () -> s.solve(new int[]{100, 90, 80, 70, 60, 50, 40, 30}));
        // edge: equal temperatures are not warmer (the two 47s), long waits next to short ones
        check(v + " equal and long waits", new int[]{8, 1, 5, 4, 3, 2, 1, 1, 0, 0},
                () -> s.solve(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70}));
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

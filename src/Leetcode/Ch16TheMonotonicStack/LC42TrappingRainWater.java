package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 42. Trapping Rain Water   [Hard]
 * https://leetcode.com/problems/trapping-rain-water/
 * Topics: Array, Two Pointers, Dynamic Programming, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #8
 *       also: ch. 8 Two pointers, converging (#11)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   height[i] is the height of the i-th bar of an elevation map; every bar is 1 unit wide.
 *   After a rain, water stays in the dips between taller bars. Return the total amount of water
 *   the map traps.
 *
 * EXAMPLES
 *   height = [0,1,0,2,1,0,1,3,2,1,2,1]  ->  6    six units of water collect in the dips
 *   height = [4,2,0,3,2,5]              ->  9
 *
 * CONSTRAINTS
 *   n == height.length,   1 <= n <= 2 * 10^4,   0 <= height[i] <= 10^5
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/TwoPointers/LC42TrappingRainWaterV2.java
 *   src/Practice/TwoPointers/LC42TrappingRainWaterV1.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC42TrappingRainWater {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int trapA(int[] height) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 8 Two pointers, converging
     */
    public static int trapB(int[] height) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static int trapC(int[] height) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC42TrappingRainWater::trapA);
        runAll("B", LC42TrappingRainWater::trapB);
        runAll("C", LC42TrappingRainWater::trapC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 42. Trapping Rain Water)
    // https://leetcode.com/problems/trapping-rain-water/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] height);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 6, () -> s.solve(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        check(v + " example 2", 9, () -> s.solve(new int[]{4, 2, 0, 3, 2, 5}));
        // edge: a single bar holds nothing
        check(v + " single bar", 0, () -> s.solve(new int[]{5}));
        // edge: two equal walls with a flat bottom made of two equal bars
        check(v + " flat bottom", 10, () -> s.solve(new int[]{4, 1, 2, 2, 1, 4}));
        // edge: the right wall is lower, so it decides the water level
        check(v + " lower right wall", 5, () -> s.solve(new int[]{5, 0, 1, 0, 2}));
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

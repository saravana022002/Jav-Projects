package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 11. Container With Most Water   [Medium]
 * https://leetcode.com/problems/container-with-most-water/
 * Topics: Array, Two Pointers, Greedy
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   height[i] is the height of a vertical line standing at position i on the x-axis. Choose
 *   two of the lines: together with the x-axis they form an open-topped container, and the
 *   water it holds is limited by the shorter of the two lines. Return the largest amount of
 *   water (the area) any container can hold. The container may not be tilted.
 *
 * EXAMPLES
 *   height = [1,8,6,2,5,4,8,3,7]  ->  49     the lines of height 8 and 7: width 7 * height 7
 *   height = [1,1]                ->  1
 *
 * CONSTRAINTS
 *   n == height.length,   2 <= n <= 10^5,   0 <= height[i] <= 10^4
 * </pre>
 */
public class LC11ContainerWithMostWater {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int maxAreaA(int[] height) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 8 Two pointers, converging (this chapter)
     */
    public static int maxAreaB(int[] height) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC11ContainerWithMostWater::maxAreaA);
        runAll("B", LC11ContainerWithMostWater::maxAreaB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 11. Container With Most Water)
    // https://leetcode.com/problems/container-with-most-water/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a try-every-pair reference. The inputs are
    // kept short because the brute-force variant is quadratic.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] height);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 49, () -> s.solve(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        check(v + " example 2", 1, () -> s.solve(new int[]{1, 1}));
        // edge: all lines have height zero, nothing can be held
        check(v + " all zero", 0, () -> s.solve(new int[]{0, 0, 0}));
        // edge: the best container is the widest one (outermost pair)
        check(v + " outer pair is best", 20, () -> s.solve(new int[]{5, 1, 1, 1, 5}));
        // edge: strictly increasing heights, the best pair lies strictly inside
        check(v + " increasing heights", 25,
                () -> s.solve(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
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

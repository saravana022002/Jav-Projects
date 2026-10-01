package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 84. Largest Rectangle in Histogram   [Hard]
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Topics: Array, Stack, Monotonic Stack, Range Minimum/Maximum Query
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #4
 *       also: ch. 16 The monotonic stack (#7), ch. 17 The monotonic deque (#11)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   heights[i] is the height of the i-th bar of a histogram. All bars are 1 unit wide and
 *   stand side by side. Return the area of the largest rectangle that fits inside the
 *   histogram: it covers a run of consecutive bars, so it can be no taller than any bar in
 *   that run.
 *
 * EXAMPLES
 *   heights = [2,1,5,6,2,3]  ->  10     the bars 5 and 6 give a 2 x 5 rectangle
 *   heights = [2,4]          ->  4      the bar 4 alone, or both bars at height 2
 *
 * CONSTRAINTS
 *   1 <= heights.length <= 10^5,   0 <= heights[i] <= 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Stack/LC84LargestRectangleInHistogramIndexStack.java
 *   src/Practice/Stack/LC84LargestRectangleInHistogramPairStack.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC84LargestRectangleInHistogram {

    /**
     * Variant A - brute force: for every left edge, extend right while tracking the lowest bar
     * <br>Target: time O(n^2), space O(1)
     */
    public static int largestRectangleAreaBruteForce(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - two passes: nearest shorter bar on each side of every bar
     * <br>Target: time O(n), space O(n)
     */
    public static int largestRectangleAreaTwoPasses(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: one pass with a monotonic stack of bar indices
     * <br>Target: time O(n), space O(n)
     */
    public static int largestRectangleArea(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC84LargestRectangleInHistogram::largestRectangleAreaBruteForce);
        runAll("B", LC84LargestRectangleInHistogram::largestRectangleAreaTwoPasses);
        runAll("C", LC84LargestRectangleInHistogram::largestRectangleArea);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 84. Largest Rectangle in Histogram)
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] heights);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 10, () -> s.solve(new int[]{2, 1, 5, 6, 2, 3}));
        check(v + " example 2", 4, () -> s.solve(new int[]{2, 4}));
        // edge: a single bar
        check(v + " single bar", 7, () -> s.solve(new int[]{7}));
        // edge: strictly rising bars, so nothing is shorter than the bars before it
        check(v + " rising", 9, () -> s.solve(new int[]{1, 2, 3, 4, 5}));
        // edge: nested valleys; the best rectangle is the middle plateau
        check(v + " valleys", 12, () -> s.solve(new int[]{6, 2, 5, 4, 5, 1, 6}));
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

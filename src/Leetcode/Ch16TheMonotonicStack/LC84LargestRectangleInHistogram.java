package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 84. Largest Rectangle in Histogram   [Hard]
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Topics: Array, Stack, Monotonic Stack, Range Minimum/Maximum Query
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #7
 *       also: ch. 1 Complexity, amortisation, and counting (#4), ch. 17 The monotonic deque (#11)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   heights[i] is the height of the i-th bar of a histogram. Every bar is 1 unit wide and the
 *   bars stand next to each other. Find the area of the largest rectangle that fits inside the
 *   histogram: it covers a run of consecutive bars, is as wide as that run and as tall as the
 *   shortest bar in it.
 *
 * EXAMPLES
 *   heights = [2,1,5,6,2,3]  ->  10    the bars 5 and 6: height 5, width 2
 *   heights = [2,4]          ->  4
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
     * Variant A - brute force: for every bar, grow left and right while the bars are not shorter
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int largestRectangleAreaBruteForce(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - two stack passes for the nearest shorter bars, then one area per bar
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static int largestRectangleAreaTwoPasses(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: stack of rising bars, a pop closes a rectangle
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
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
    // examples plus edge cases confirmed with an independent brute-force reference.
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
        // edge: a single bar of height 0
        check(v + " zero bar", 0, () -> s.solve(new int[]{0}));
        // edge: equal bars form one wide rectangle
        check(v + " equal bars", 12, () -> s.solve(new int[]{3, 3, 3, 3}));
        // edge: a zero-height bar splits the histogram in two
        check(v + " zero splits", 2, () -> s.solve(new int[]{2, 0, 2}));
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

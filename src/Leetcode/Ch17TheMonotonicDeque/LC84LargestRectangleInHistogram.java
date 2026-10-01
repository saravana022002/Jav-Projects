package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 84. Largest Rectangle in Histogram   [Hard]
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Topics: Array, Stack, Monotonic Stack, Range Minimum/Maximum Query
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #11
 *       also: ch. 1 Complexity, amortisation, and counting (#4), ch. 16 The monotonic stack (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   heights[i] is the height of the i-th bar of a histogram. All bars have width 1 and stand
 *   right next to each other. Return the area of the largest rectangle that fits entirely
 *   inside the histogram (its base lies on the axis, so its height is limited by the lowest
 *   bar it covers).
 *
 * EXAMPLES
 *   heights = [2,1,5,6,2,3]  ->  10    the bars 5 and 6 side by side: 2 * 5
 *   heights = [2,4]          ->  4     the bar 4 alone, or both bars at height 2
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
     * Variant A - brute force: for every bar, widen the rectangle left and right while it fits
     * <br>Target: time O(n^2), space O(1)
     */
    public static int largestRectangleAreaBruteForce(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - nearest lower bar on each side by two passes, then the best area
     * <br>Target: time O(n), space O(n)
     */
    public static int largestRectangleAreaNearestLower(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: one pass, monotonic deque of increasing bars
     * <br>Target: time O(n), space O(n)
     */
    public static int largestRectangleArea(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC84LargestRectangleInHistogram::largestRectangleAreaBruteForce);
        runAll("B", LC84LargestRectangleInHistogram::largestRectangleAreaNearestLower);
        runAll("C", LC84LargestRectangleInHistogram::largestRectangleArea);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 84. Largest Rectangle in Histogram)
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose answers were checked against a brute-force reference.
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
        check(v + " single zero bar", 0, () -> s.solve(new int[]{0}));
        // edge: strictly increasing heights
        check(v + " increasing", 9, () -> s.solve(new int[]{1, 2, 3, 4, 5}));
        // edge: the lowest bar in the middle limits the widest rectangle
        check(v + " low bar in the middle", 3, () -> s.solve(new int[]{2, 1, 2}));
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

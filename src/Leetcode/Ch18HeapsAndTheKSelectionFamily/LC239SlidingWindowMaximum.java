package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 239. Sliding Window Maximum   [Hard]
 * https://leetcode.com/problems/sliding-window-maximum/
 * Topics: Array, Queue, Sliding Window, Heap (Priority Queue), Monotonic Queue,
 *         Range Minimum/Maximum Query
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #12
 *       also: ch. 17 The monotonic deque (#1), ch. 75 DP optimisation (#1)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A window of k consecutive elements starts at the left end of nums and moves right by one
 *   position at a time until it reaches the right end. Only the k numbers inside the window
 *   are visible. Return the maximum of the window at every position, in order, as an array
 *   of length nums.length - k + 1.
 *
 * EXAMPLES
 *   nums = [1,3,-1,-3,5,3,6,7],  k = 3  ->  [3,3,5,5,6,7]
 *       windows: [1 3 -1] [3 -1 -3] [-1 -3 5] [-3 5 3] [5 3 6] [3 6 7]
 *   nums = [1],                  k = 1  ->  [1]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4,   1 <= k <= nums.length
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/SlidingWindow/LC239SlidingWindowMaximum.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC239SlidingWindowMaximum {

    /**
     * Variant A
     * <br>Target: time O(n * k), space O(1) extra (the answer array not counted)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] maxSlidingWindowA(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(k)
     * <br>Complete first: ch. 17 The monotonic deque
     */
    public static int[] maxSlidingWindowB(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (this chapter)
     */
    public static int[] maxSlidingWindowC(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC239SlidingWindowMaximum::maxSlidingWindowA);
        runAll("B", LC239SlidingWindowMaximum::maxSlidingWindowB);
        runAll("C", LC239SlidingWindowMaximum::maxSlidingWindowC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 239. Sliding Window Maximum)
    // https://leetcode.com/problems/sliding-window-maximum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose answers were checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{3, 3, 5, 5, 6, 7},
                () -> s.solve(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        check(v + " example 2", new int[]{1}, () -> s.solve(new int[]{1}, 1));
        // edge: one window covering the whole array, all negative
        check(v + " k equals n", new int[]{-3}, () -> s.solve(new int[]{-5, -3, -9}, 3));
        // edge: equal values inside the windows
        check(v + " duplicates", new int[]{3, 3, 2, 5},
                () -> s.solve(new int[]{1, 3, 1, 2, 0, 5}, 3));
        // edge: strictly decreasing values
        check(v + " decreasing", new int[]{9, 8, 7, 6}, () -> s.solve(new int[]{9, 8, 7, 6, 5}, 2));
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

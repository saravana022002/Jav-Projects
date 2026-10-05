package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 503. Next Greater Element II   [Medium]
 * https://leetcode.com/problems/next-greater-element-ii/
 * Topics: Array, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is circular: the element after the last one is the first one again. For every element,
 *   walk forward from it (wrapping around the end if needed) and report the first value that
 *   is strictly greater, or -1 if one full lap brings you back without meeting a greater
 *   value. Return the answers for all elements as an array.
 *
 * EXAMPLES
 *   nums = [1,2,1]      ->  [2,-1,2]       the last 1 wraps around and finds the 2
 *   nums = [1,2,3,4,3]  ->  [2,3,4,-1,4]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -10^9 <= nums[i] <= 10^9
 * </pre>
 */
public class LC503NextGreaterElementII {

    /**
     * Variant A - brute force: for every index, walk the next n - 1 positions with wrap-around
     * <br>Target: time O(n^2), space O(1)    (besides the answer array)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] nextGreaterElementsBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: monotonic stack of indices, scanning the array twice
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static int[] nextGreaterElements(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC503NextGreaterElementII::nextGreaterElementsBruteForce);
        runAll("B", LC503NextGreaterElementII::nextGreaterElements);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 503. Next Greater Element II)
    // https://leetcode.com/problems/next-greater-element-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{2, -1, 2}, () -> s.solve(new int[]{1, 2, 1}));
        check(v + " example 2", new int[]{2, 3, 4, -1, 4}, () -> s.solve(new int[]{1, 2, 3, 4, 3}));
        // edge: a single element is its own (and only) lap
        check(v + " single element", new int[]{-1}, () -> s.solve(new int[]{5}));
        // edge: equal values are not greater
        check(v + " all equal", new int[]{-1, -1, -1}, () -> s.solve(new int[]{3, 3, 3}));
        // edge: falling values, every answer except the maximum's comes from wrapping around
        check(v + " falling", new int[]{-1, 5, 5, 5, 5}, () -> s.solve(new int[]{5, 4, 3, 2, 1}));
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

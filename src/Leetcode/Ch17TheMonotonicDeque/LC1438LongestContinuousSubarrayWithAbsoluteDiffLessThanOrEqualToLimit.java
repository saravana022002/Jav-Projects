package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit   [Medium]
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * Topics: Array, Queue, Sliding Window, Heap (Priority Queue), Ordered Set, Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #4
 *       also: ch. 9 Sliding window, chasing (#12), ch. 75 DP optimisation (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case
 *   prints PASS or FAIL.
 *
 * PROBLEM
 *   Find the longest contiguous stretch of nums in which no two elements differ by more than
 *   limit (that is: its maximum minus its minimum is at most limit) and return its length.
 *   A stretch of a single element always qualifies, so the answer is at least 1.
 *
 * EXAMPLES
 *   nums = [8,2,4,7],          limit = 4  ->  2    e.g. [2,4] or [4,7]
 *   nums = [10,1,2,4,7,2],     limit = 5  ->  4    [2,4,7,2]: 7 - 2 = 5
 *   nums = [4,2,2,2,4,4,2,2],  limit = 0  ->  3    [2,2,2]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^9,   0 <= limit <= 10^9
 * </pre>
 */
public class LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int longestSubarrayA(int[] nums, int limit) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     */
    public static int longestSubarrayB(int[] nums, int limit) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     */
    public static int longestSubarrayC(int[] nums, int limit) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit
                ::longestSubarrayA);
        runAll("B", LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit
                ::longestSubarrayB);
        runAll("C", LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit
                ::longestSubarrayC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were checked against an exhaustive search.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int limit);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[]{8, 2, 4, 7}, 4));
        check(v + " example 2", 4, () -> s.solve(new int[]{10, 1, 2, 4, 7, 2}, 5));
        check(v + " example 3", 3, () -> s.solve(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
        // edge: a single element
        check(v + " single element", 1, () -> s.solve(new int[]{5}, 0));
        // edge: values and limit at the upper bound of the constraints
        check(v + " huge values", 2, () -> s.solve(new int[]{1000000000, 1}, 1000000000));
        // edge: the longest stretch 6,7,8,10,6 sits behind a 1 and a 5 that do not fit with the 10
        check(v + " long stretch late", 5,
                () -> s.solve(new int[]{1, 5, 6, 7, 8, 10, 6, 5, 6}, 4));
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

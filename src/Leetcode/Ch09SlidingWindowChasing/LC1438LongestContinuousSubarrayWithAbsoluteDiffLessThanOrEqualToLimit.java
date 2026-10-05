package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit   [Medium]
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * Topics: Array, Queue, Sliding Window, Heap (Priority Queue), Ordered Set, Monotonic Queue
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #12
 *       also: ch. 17 The monotonic deque (#4), ch. 75 DP optimisation (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case
 *   prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the length of the longest non-empty contiguous subarray of nums in which any two
 *   elements differ by at most limit (the subarray's maximum minus its minimum is <= limit).
 *
 * EXAMPLES
 *   nums = [8,2,4,7],            limit = 4  ->  2    [2,4] or [4,7]; any longer one has a gap > 4
 *   nums = [10,1,2,4,7,2],       limit = 5  ->  4    [2,4,7,2]: the largest gap is |2 - 7| = 5
 *   nums = [4,2,2,2,4,4,2,2],    limit = 0  ->  3    [2,2,2]: all elements must be equal
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^9,   0 <= limit <= 10^9
 * </pre>
 */
public class LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    /**
     * Variant A - brute force: from every start, extend while max - min stays <= limit
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int longestSubarrayBruteForce(int[] nums, int limit) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - sliding window that keeps its values in an ordered multiset (TreeMap of counts)
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 9 Sliding window, chasing (this chapter)
     *     + ch. 41 Ordered maps in practice (later chapter; needs ch. 10 first)
     */
    public static int longestSubarrayTreeMap(int[] nums, int limit) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sliding window with a max deque and a min deque
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 9 Sliding window, chasing (this chapter)
     *     + ch. 17 The monotonic deque (later chapter; needs ch. 15, 16 first)
     */
    public static int longestSubarray(int[] nums, int limit) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit
                ::longestSubarrayBruteForce);
        runAll("B", LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit
                ::longestSubarrayTreeMap);
        runAll("C", LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit
                ::longestSubarray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference.
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
        // edge: a single element is always a valid subarray
        check(v + " single element", 1, () -> s.solve(new int[]{5}, 0));
        // edge: the gap is exactly the limit, with values at the constraint bounds
        check(v + " gap equals limit", 2, () -> s.solve(new int[]{1, 1000000000}, 999999999));
        // edge: the old maximum and minimum leave the window while it slides
        check(v + " old extremes expire", 5,
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

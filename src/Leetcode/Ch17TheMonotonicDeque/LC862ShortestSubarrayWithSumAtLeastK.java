package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 862. Shortest Subarray with Sum at Least K   [Hard]
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 * Topics: Array, Binary Search, Queue, Sliding Window, Heap (Priority Queue), Prefix Sum,
 *         Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #8
 *       also: ch. 75 DP optimisation (#5), ch. 83 What n tells you (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Find the shortest non-empty contiguous subarray of nums whose elements add up to at
 *   least k, and return its length. If no subarray reaches that sum, return -1.
 *   The elements may be negative, so a longer subarray can have a smaller sum.
 *
 * EXAMPLES
 *   nums = [1],       k = 1  ->  1
 *   nums = [1,2],     k = 4  ->  -1    even the whole array only sums to 3
 *   nums = [2,-1,2],  k = 3  ->  3     only the whole array reaches 3
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^5 <= nums[i] <= 10^5,   1 <= k <= 10^9
 * </pre>
 */
public class LC862ShortestSubarrayWithSumAtLeastK {

    /**
     * Variant A - brute force: from every start, extend until the running sum reaches k
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int shortestSubarrayBruteForce(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - prefix sums, with a min-heap of the earlier (prefix value, index) pairs
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     *     + ch. 18 Heaps and the k-selection family (later chapter)
     */
    public static int shortestSubarrayHeap(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: prefix sums plus a monotonic deque of indices
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     *     + ch. 17 The monotonic deque (this chapter)
     */
    public static int shortestSubarray(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC862ShortestSubarrayWithSumAtLeastK::shortestSubarrayBruteForce);
        runAll("B", LC862ShortestSubarrayWithSumAtLeastK::shortestSubarrayHeap);
        runAll("C", LC862ShortestSubarrayWithSumAtLeastK::shortestSubarray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 862. Shortest Subarray with Sum at Least K)
    // https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were checked against a brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{1}, 1));
        check(v + " example 2", -1, () -> s.solve(new int[]{1, 2}, 4));
        check(v + " example 3", 3, () -> s.solve(new int[]{2, -1, 2}, 3));
        // edge: mixed signs, the answer is the last three elements
        check(v + " negatives inside", 3, () -> s.solve(new int[]{84, -37, 32, 40, 95}, 167));
        // edge: one big element after a run of negatives
        check(v + " lone big element", 1, () -> s.solve(new int[]{-5, -4, -3, 100, -2}, 100));
        // edge: several earlier starts already reach 150 when the end moves to the 93
        check(v + " several starts reach k", 2,
                () -> s.solve(new int[]{17, 85, 93, -45, -21}, 150));
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

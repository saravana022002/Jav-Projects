package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 209. Minimum Size Subarray Sum   [Medium]
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * Topics: Array, Binary Search, Sliding Window, Prefix Sum
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #7
 *       also: ch. 1 Complexity, amortisation, and counting (#6), ch. 83 What n tells you (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums contains positive integers and target is a positive integer. Return the length of
 *   the shortest contiguous subarray whose sum is at least target, or 0 when even the whole
 *   array falls short.
 *
 * EXAMPLES
 *   target = 7,  nums = [2,3,1,2,4,3]        ->  2    [4,3]
 *   target = 4,  nums = [1,4,4]              ->  1    [4]
 *   target = 11, nums = [1,1,1,1,1,1,1,1]    ->  0    the total is only 8
 *
 * CONSTRAINTS
 *   1 <= target <= 10^9,   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^4
 * </pre>
 */
public class LC209MinimumSizeSubarraySum {

    /**
     * Variant A - brute force: from every start, extend until the sum reaches target
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int minSubArrayLenBruteForce(int target, int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - prefix sums, binary-search the nearest end reaching target for each start
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     *     + ch. 10 Binary search on a sorted array (later chapter)
     */
    public static int minSubArrayLenBinarySearch(int target, int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sliding window, shrink while sum >= target
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 9 Sliding window, chasing (this chapter)
     */
    public static int minSubArrayLen(int target, int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC209MinimumSizeSubarraySum::minSubArrayLenBruteForce);
        runAll("B", LC209MinimumSizeSubarraySum::minSubArrayLenBinarySearch);
        runAll("C", LC209MinimumSizeSubarraySum::minSubArrayLen);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 209. Minimum Size Subarray Sum)
    // https://leetcode.com/problems/minimum-size-subarray-sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int target, int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(7, new int[]{2, 3, 1, 2, 4, 3}));
        check(v + " example 2", 1, () -> s.solve(4, new int[]{1, 4, 4}));
        check(v + " example 3", 0, () -> s.solve(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
        // edge: only the whole array reaches the target
        check(v + " whole array", 5, () -> s.solve(15, new int[]{1, 2, 3, 4, 5}));
        // edge: the shortest window is at the very end
        check(v + " window at the end", 3, () -> s.solve(11, new int[]{1, 2, 3, 4, 5}));
        // edge: a single element that is too small
        check(v + " single too small", 0, () -> s.solve(4, new int[]{3}));
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

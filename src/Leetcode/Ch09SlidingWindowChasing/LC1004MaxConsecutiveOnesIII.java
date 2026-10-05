package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1004. Max Consecutive Ones III   [Medium]
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * Topics: Array, Binary Search, Sliding Window, Prefix Sum
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #5
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds only 0s and 1s. You may flip at most k of the 0s into 1s. Return the length
 *   of the longest run of consecutive 1s the array can contain after the flips.
 *
 * EXAMPLES
 *   nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2                       ->  6
 *       flip two zeros: [1,1,1,0,0,1,1,1,1,1,1]
 *   nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3      ->  10
 *       flip three zeros: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   nums[i] is 0 or 1,   0 <= k <= nums.length
 * </pre>
 */
public class LC1004MaxConsecutiveOnesIII {

    /**
     * Variant A - brute force: from every start, extend until more than k zeros are inside
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int longestOnesBruteForce(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - prefix counts of zeros, binary-search the farthest legal end for each start
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     *     + ch. 10 Binary search on a sorted array (later chapter)
     */
    public static int longestOnesPrefixBinarySearch(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sliding window holding at most k zeros
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 9 Sliding window, chasing (this chapter)
     */
    public static int longestOnes(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1004MaxConsecutiveOnesIII::longestOnesBruteForce);
        runAll("B", LC1004MaxConsecutiveOnesIII::longestOnesPrefixBinarySearch);
        runAll("C", LC1004MaxConsecutiveOnesIII::longestOnes);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1004. Max Consecutive Ones III)
    // https://leetcode.com/problems/max-consecutive-ones-iii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 6,
                () -> s.solve(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        check(v + " example 2", 10, () -> s.solve(
                new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
        // edge: no flips allowed, the longest run of 1s already in the array counts
        check(v + " k = 0", 3, () -> s.solve(new int[]{1, 1, 0, 1, 1, 1, 0, 1}, 0));
        // edge: nothing to flip and nothing to keep
        check(v + " all zeros, k = 0", 0, () -> s.solve(new int[]{0, 0, 0}, 0));
        // edge: k is as large as the array, so every position can become a 1
        check(v + " k = length", 4, () -> s.solve(new int[]{0, 1, 0, 0}, 4));
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

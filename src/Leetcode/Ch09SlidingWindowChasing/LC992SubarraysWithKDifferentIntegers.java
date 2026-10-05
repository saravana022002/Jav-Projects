package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 992. Subarrays with K Different Integers   [Hard]
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * Topics: Array, Hash Table, Sliding Window, Counting
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #11
 *       also: ch. 1 Complexity, amortisation, and counting (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Count the contiguous subarrays of nums that contain exactly k different integers.
 *   Equal values count once: [1,2,1,2] has 2 different integers.
 *
 * EXAMPLES
 *   nums = [1,2,1,2,3], k = 2  ->  7
 *       [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 *   nums = [1,2,1,3,4], k = 3  ->  3
 *       [1,2,1,3], [2,1,3], [1,3,4]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 2 * 10^4,   1 <= nums[i], k <= nums.length
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch01ComplexityAmortisationAndCounting/LC992SubarraysWithKDifferentIntegers.java
 * </pre>
 */
public class LC992SubarraysWithKDifferentIntegers {

    /**
     * Variant A - brute force: from every start, extend with fresh counts until more than k
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     */
    public static int subarraysWithKDistinctBruteForce(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: windows counting "<= k different" minus "<= k - 1"
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 9 Sliding window, chasing (this chapter)
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - one pass: a window of k values plus a count of removable leading elements
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 9 Sliding window, chasing (this chapter)
     */
    public static int subarraysWithKDistinctOnePass(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC992SubarraysWithKDifferentIntegers::subarraysWithKDistinctBruteForce);
        runAll("B", LC992SubarraysWithKDifferentIntegers::subarraysWithKDistinct);
        runAll("C", LC992SubarraysWithKDifferentIntegers::subarraysWithKDistinctOnePass);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 992. Subarrays with K Different Integers)
    // https://leetcode.com/problems/subarrays-with-k-different-integers/
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
        check(v + " example 1", 7, () -> s.solve(new int[]{1, 2, 1, 2, 3}, 2));
        check(v + " example 2", 3, () -> s.solve(new int[]{1, 2, 1, 3, 4}, 3));
        // edge: all values equal, every subarray has exactly one different integer
        check(v + " all equal, k = 1", 6, () -> s.solve(new int[]{1, 1, 1}, 1));
        // edge: k is larger than the number of different values in the whole array
        check(v + " k too big", 0, () -> s.solve(new int[]{1, 2, 1}, 3));
        // edge: long runs of one value next to the window edges
        check(v + " repeats at the edges", 7, () -> s.solve(new int[]{2, 1, 1, 1, 2}, 2));
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

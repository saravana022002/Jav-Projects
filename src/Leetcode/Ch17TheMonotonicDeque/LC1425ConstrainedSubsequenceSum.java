package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1425. Constrained Subsequence Sum   [Hard]
 * https://leetcode.com/problems/constrained-subsequence-sum/
 * Topics: Array, Dynamic Programming, Queue, Sliding Window, Heap (Priority Queue),
 *         Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #3
 *       also: ch. 75 DP optimisation (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Pick a non-empty subsequence of nums (some elements deleted, the order kept) so that any
 *   two neighbours in the chosen subsequence, at original positions i < j, satisfy j - i <= k.
 *   Return the largest possible sum of such a subsequence.
 *
 * EXAMPLES
 *   nums = [10,2,-10,5,20],   k = 2  ->  37    subsequence [10,2,5,20]
 *   nums = [-1,-2,-3],        k = 1  ->  -1    it must be non-empty: take the largest number
 *   nums = [10,-2,-10,-5,20], k = 2  ->  23    subsequence [10,-2,-5,20]
 *
 * CONSTRAINTS
 *   1 <= k <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
 * </pre>
 */
public class LC1425ConstrainedSubsequenceSum {

    /**
     * Variant A
     * <br>Target: time O(n * k), space O(n)
     * <br>Complete first: ch. 64 Linear DP (later chapter; needs ch. 56, 62, 63 first)
     */
    public static int constrainedSubsetSumA(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (later chapter)
     *     + ch. 64 Linear DP (later chapter; needs ch. 56, 62, 63 first)
     */
    public static int constrainedSubsetSumB(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 17 The monotonic deque (this chapter)
     *     + ch. 64 Linear DP (later chapter; needs ch. 56, 62, 63 first)
     */
    public static int constrainedSubsetSumC(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1425ConstrainedSubsequenceSum::constrainedSubsetSumA);
        runAll("B", LC1425ConstrainedSubsequenceSum::constrainedSubsetSumB);
        runAll("C", LC1425ConstrainedSubsequenceSum::constrainedSubsetSumC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1425. Constrained Subsequence Sum)
    // https://leetcode.com/problems/constrained-subsequence-sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were checked against an exhaustive search.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 37, () -> s.solve(new int[]{10, 2, -10, 5, 20}, 2));
        check(v + " example 2", -1, () -> s.solve(new int[]{-1, -2, -3}, 1));
        check(v + " example 3", 23, () -> s.solve(new int[]{10, -2, -10, -5, 20}, 2));
        // edge: a single negative element (the subsequence may not be empty)
        check(v + " single negative", -5, () -> s.solve(new int[]{-5}, 1));
        // edge: all negative and k = n, so the answer is just the largest element
        check(v + " all negative", -1, () -> s.solve(new int[]{-8, -3, -5, -1, -7}, 5));
        // edge: the gap of two bad cells cannot be bridged with k = 2, so start again at the 7
        check(v + " gap too wide", 7, () -> s.solve(new int[]{5, -100, -100, 7}, 2));
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

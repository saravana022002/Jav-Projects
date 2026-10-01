package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 493. Reverse Pairs   [Hard]
 * https://leetcode.com/problems/reverse-pairs/
 * Topics: Array, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree,
 *         Merge Sort, Ordered Set, Treap
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #11
 *       also: ch. 39 Fenwick trees (#6), ch. 59 Divide and conquer (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A reverse pair is a pair of positions (i, j) with i < j and nums[i] > 2 * nums[j].
 *   Return how many reverse pairs the integer array nums contains.
 *
 * EXAMPLES
 *   nums = [1,3,2,3,1]  ->  2     pairs (1,4) and (3,4): 3 > 2 * 1
 *   nums = [2,4,3,5,1]  ->  3     pairs (1,4), (2,4), (3,4): each of 4, 3, 5 is > 2 * 1
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 5 * 10^4,   -2^31 <= nums[i] <= 2^31 - 1
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Sorting/LC493ReversePairs.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC493ReversePairs {

    /**
     * Variant A - brute force: test every pair of positions
     * <br>Target: time O(n^2), space O(1)
     */
    public static int reversePairsBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - Fenwick tree over compressed values: scan left to right, count earlier values
     * <br>Target: time O(n log n), space O(n)
     */
    public static int reversePairsFenwick(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: merge sort; count the cross pairs before each merge
     * <br>Target: time O(n log n), space O(n)
     */
    public static int reversePairs(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC493ReversePairs::reversePairsBruteForce);
        runAll("B", LC493ReversePairs::reversePairsFenwick);
        runAll("C", LC493ReversePairs::reversePairs);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 493. Reverse Pairs)
    // https://leetcode.com/problems/reverse-pairs/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected counts checked against an all-pairs reference that
    // uses long arithmetic).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh array)
        check(v + " example 1", 2, () -> s.solve(new int[]{1, 3, 2, 3, 1}));
        check(v + " example 2", 3, () -> s.solve(new int[]{2, 4, 3, 5, 1}));
        // edge: the largest int values, where doubling a value no longer fits in an int
        check(v + " large positives", 2,
                () -> s.solve(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, 3}));
        // edge: the smallest int value, where doubling it no longer fits in an int
        check(v + " smallest int", 1, () -> s.solve(new int[]{-5, Integer.MIN_VALUE}));
        // edge: zeros and negatives
        check(v + " zeros and negatives", 5, () -> s.solve(new int[]{0, 0, -1, -1}));
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

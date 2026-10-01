package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 3013. Divide an Array Into Subarrays With Minimum Cost II   [Hard]
 * https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/
 * Topics: Array, Hash Table, Sliding Window, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #7
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   The cost of a subarray is its first element. Split nums (0-indexed, length n) into exactly
 *   k non-empty contiguous subarrays; the cost of the split is the sum of the k subarray costs.
 *   A split is only allowed when the start index of the k-th subarray minus the start index of
 *   the 2nd subarray is at most dist. Return the minimum possible cost of a split (as a long).
 *
 * EXAMPLES
 *   nums = [1,3,2,6,4,2],   k = 3, dist = 3  ->  5     [1,3] [2,6,4] [2]: 1 + 2 + 2
 *   nums = [10,1,2,2,2,1],  k = 4, dist = 3  ->  15    [10] [1] [2] [2,2,1]: 10 + 1 + 2 + 2
 *   nums = [10,8,18,9],     k = 3, dist = 1  ->  36    [10] [8] [18,9]: 10 + 8 + 18
 *
 * CONSTRAINTS
 *   3 <= n <= 10^5,   1 <= nums[i] <= 10^9,   3 <= k <= n,   k - 2 <= dist <= n - 2
 * </pre>
 */
public class LC3013DivideAnArrayIntoSubarraysWithMinimumCostII {

    /**
     * Variant A - brute force: for each possible 2nd start, sort its window, add the k-2 smallest
     * <br>Target: time O(n * dist * log dist), space O(dist)
     */
    public static long minimumCostBruteForce(int[] nums, int k, int dist) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: sliding window, two heaps hold the k-2 smallest
     * <br>Target: time O(n log n), space O(n)
     */
    public static long minimumCost(int[] nums, int k, int dist) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC3013DivideAnArrayIntoSubarraysWithMinimumCostII::minimumCostBruteForce);
        runAll("B", LC3013DivideAnArrayIntoSubarraysWithMinimumCostII::minimumCost);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 3013. Divide an Array Into Subarrays With Minimum Cost II)
    // https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an enumerate-all-splits reference.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        long solve(int[] nums, int k, int dist);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 5L, () -> s.solve(new int[]{1, 3, 2, 6, 4, 2}, 3, 3));
        check(v + " example 2", 15L, () -> s.solve(new int[]{10, 1, 2, 2, 2, 1}, 4, 3));
        check(v + " example 3", 36L, () -> s.solve(new int[]{10, 8, 18, 9}, 3, 1));
        // edge: smallest input, the only split is three single elements
        check(v + " smallest", 8L, () -> s.solve(new int[]{5, 1, 2}, 3, 1));
        // edge: sum does not fit in an int
        check(v + " large sum", 4000000000L,
                () -> s.solve(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, 4, 2));
        // edge: duplicates, and the window is cut off by the end of the array
        check(v + " cut window", 13L,
                () -> s.solve(new int[]{7, 2, 9, 2, 9, 2, 2, 8, 1, 1, 3, 4}, 5, 4));
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
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

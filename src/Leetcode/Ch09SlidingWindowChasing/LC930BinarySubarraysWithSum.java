package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 930. Binary Subarrays With Sum   [Medium]
 * https://leetcode.com/problems/binary-subarrays-with-sum/
 * Topics: Array, Hash Table, Sliding Window, Prefix Sum
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds only 0s and 1s. Count the non-empty contiguous subarrays whose sum is exactly
 *   goal.
 *
 * EXAMPLES
 *   nums = [1,0,1,0,1], goal = 2  ->  4    [1,0,1], [1,0,1,0], [0,1,0,1], [1,0,1]
 *   nums = [0,0,0,0,0], goal = 0  ->  15   every non-empty subarray sums to 0
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 3 * 10^4,   nums[i] is 0 or 1,   0 <= goal <= nums.length
 * </pre>
 */
public class LC930BinarySubarraysWithSum {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int numSubarraysWithSumA(int[] nums, int goal) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     */
    public static int numSubarraysWithSumB(int[] nums, int goal) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     */
    public static int numSubarraysWithSumC(int[] nums, int goal) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC930BinarySubarraysWithSum::numSubarraysWithSumA);
        runAll("B", LC930BinarySubarraysWithSum::numSubarraysWithSumB);
        runAll("C", LC930BinarySubarraysWithSum::numSubarraysWithSumC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 930. Binary Subarrays With Sum)
    // https://leetcode.com/problems/binary-subarrays-with-sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int goal);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4, () -> s.solve(new int[]{1, 0, 1, 0, 1}, 2));
        check(v + " example 2", 15, () -> s.solve(new int[]{0, 0, 0, 0, 0}, 0));
        // edge: goal = 0 mixed with a 1, only the runs of zeros count (3 + 6 subarrays)
        check(v + " goal 0, one 1", 9, () -> s.solve(new int[]{0, 0, 1, 0, 0, 0}, 0));
        // edge: goal is larger than the number of 1s
        check(v + " goal too big", 0, () -> s.solve(new int[]{1, 0, 1}, 3));
        // edge: a single element
        check(v + " single element", 1, () -> s.solve(new int[]{1}, 1));
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

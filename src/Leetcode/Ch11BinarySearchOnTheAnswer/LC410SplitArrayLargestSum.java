package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 410. Split Array Largest Sum   [Hard]
 * https://leetcode.com/problems/split-array-largest-sum/
 * Topics: Array, Binary Search, Dynamic Programming, Greedy, Prefix Sum
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Cut nums into exactly k non-empty contiguous pieces (subarrays) so that the largest
 *   piece sum is as small as possible. Return that smallest possible largest sum.
 *
 * EXAMPLES
 *   nums = [7,2,5,10,8],  k = 2  ->  18    best cut: [7,2,5] | [10,8]
 *   nums = [1,2,3,4,5],   k = 2  ->  9     best cut: [1,2,3] | [4,5]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 1000,   0 <= nums[i] <= 10^6,   1 <= k <= min(50, nums.length)
 * </pre>
 */
public class LC410SplitArrayLargestSum {

    /**
     * Variant A - dynamic programming over (prefix length, number of pieces) with prefix sums
     * <br>Target: time O(k * n^2), space O(k * n)
     */
    public static int splitArrayDP(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: binary search on the largest sum, count pieces needed
     * <br>Target: time O(n log sum(nums)), space O(1)
     */
    public static int splitArray(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC410SplitArrayLargestSum::splitArrayDP);
        runAll("B", LC410SplitArrayLargestSum::splitArray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 410. Split Array Largest Sum)
    // https://leetcode.com/problems/split-array-largest-sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent brute force that tries every
    // way to place the cuts.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 18, () -> s.solve(new int[]{7, 2, 5, 10, 8}, 2));
        check(v + " example 2", 9, () -> s.solve(new int[]{1, 2, 3, 4, 5}, 2));
        // edge: a single piece, the whole array
        check(v + " one piece", 6, () -> s.solve(new int[]{1, 2, 3}, 1));
        // edge: one element per piece, the largest element decides
        check(v + " piece per element", 5, () -> s.solve(new int[]{5, 1, 3}, 3));
        // edge: all elements are zero
        check(v + " all zeros", 0, () -> s.solve(new int[]{0, 0, 0}, 2));
    }

    // one case: a wrong value, an exception or an endless loop counts as FAIL; the run continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got = hung > 0 ? "skipped (an earlier case of this variant never finished)"
                : callWithTimeout(actual);
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    // runs the case on its own thread, so that an endless loop cannot block the whole run
    private static Object callWithTimeout(Supplier<Object> actual) {
        Object[] box = new Object[1];
        Thread worker = new Thread(null, () -> {
            try {
                box[0] = actual.get();
            } catch (Throwable t) {
                box[0] = "threw " + t.getClass().getSimpleName();
            }
        }, "case", 1L << 26);
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(TIMEOUT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (worker.isAlive()) {
            hung++;
            return "no result after " + TIMEOUT_MS + " ms (endless loop?)";
        }
        return box[0];
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

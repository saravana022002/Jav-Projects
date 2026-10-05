package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1696. Jump Game VI   [Medium]
 * https://leetcode.com/problems/jump-game-vi/
 * Topics: Array, Dynamic Programming, Queue, Heap (Priority Queue), Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #2
 *       also: ch. 64 Linear DP (#11), ch. 75 DP optimisation (#2)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   You stand on index 0 of nums and want to reach the last index. A move takes you forward
 *   by 1 to k positions (you may not leave the array). Your score is the sum of nums[j] over
 *   every index j you stood on, the first and the last one included. Return the maximum score
 *   that can be achieved.
 *
 * EXAMPLES
 *   nums = [1,-1,-2,4,-7,3],         k = 2  ->  7     jump through 1, -1, 4, 3
 *   nums = [10,-5,-2,4,0,3],         k = 3  ->  17    jump through 10, 4, 3
 *   nums = [1,-5,-20,4,-1,3,-6,-3],  k = 2  ->  0
 *
 * CONSTRAINTS
 *   1 <= nums.length, k <= 10^5,   -10^4 <= nums[i] <= 10^4
 * </pre>
 */
public class LC1696JumpGameVI {

    /**
     * Variant A - dp over positions; best of the previous k states found by scanning them
     * <br>Target: time O(n * k), space O(n)
     * <br>Complete first: ch. 64 Linear DP (later chapter; needs ch. 56, 62, 63 first)
     */
    public static int maxResultBruteForce(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - the same dp; best of the last k states from a lazy max-heap
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (later chapter)
     *     + ch. 64 Linear DP (later chapter; needs ch. 56, 62, 63 first)
     */
    public static int maxResultHeap(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: the same dp, window of states in a monotonic deque
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 17 The monotonic deque (this chapter)
     *     + ch. 64 Linear DP (later chapter; needs ch. 56, 62, 63 first)
     */
    public static int maxResult(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1696JumpGameVI::maxResultBruteForce);
        runAll("B", LC1696JumpGameVI::maxResultHeap);
        runAll("C", LC1696JumpGameVI::maxResult);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1696. Jump Game VI)
    // https://leetcode.com/problems/jump-game-vi/
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
        check(v + " example 1", 7, () -> s.solve(new int[]{1, -1, -2, 4, -7, 3}, 2));
        check(v + " example 2", 17, () -> s.solve(new int[]{10, -5, -2, 4, 0, 3}, 3));
        check(v + " example 3", 0, () -> s.solve(new int[]{1, -5, -20, 4, -1, 3, -6, -3}, 2));
        // edge: a single element, nothing to jump over (negative answer)
        check(v + " single element", -3, () -> s.solve(new int[]{-3}, 1));
        // edge: k is much larger than the array
        check(v + " k larger than n", 10, () -> s.solve(new int[]{3, -4, 2, -1, 5}, 100));
        // edge: every move loses points, so the jumps must skip the very bad cells
        check(v + " skip the bad cells", -3, () -> s.solve(new int[]{-1, -100, -1, -100, -1}, 2));
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

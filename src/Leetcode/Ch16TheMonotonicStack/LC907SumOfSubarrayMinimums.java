package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 907. Sum of Subarray Minimums   [Medium]
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 * Topics: Array, Dynamic Programming, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #9
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   arr holds positive integers. Look at every contiguous subarray of arr, take the smallest
 *   value inside it, and add all those smallest values together. Return that sum modulo
 *   1,000,000,007 (10^9 + 7), because it can be huge.
 *
 * EXAMPLES
 *   arr = [3,1,2,4]        ->  17    the ten subarrays have minimums 3, 1, 2, 4, 1, 1, 2, 1, 1, 1
 *   arr = [11,81,94,43,3]  ->  444
 *
 * CONSTRAINTS
 *   1 <= arr.length <= 3 * 10^4,   1 <= arr[i] <= 3 * 10^4
 * </pre>
 */
public class LC907SumOfSubarrayMinimums {

    /**
     * Variant A - brute force: for every start, extend the end while tracking the running minimum
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int sumSubarrayMinsBruteForce(int[] arr) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - stack plus DP: the total of the minimums of subarrays ending at each index
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     *     + ch. 64 Linear DP (later chapter; needs ch. 56, 62, 63 first)
     */
    public static int sumSubarrayMinsDp(int[] arr) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: count the subarrays where each element is the minimum
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static int sumSubarrayMins(int[] arr) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC907SumOfSubarrayMinimums::sumSubarrayMinsBruteForce);
        runAll("B", LC907SumOfSubarrayMinimums::sumSubarrayMinsDp);
        runAll("C", LC907SumOfSubarrayMinimums::sumSubarrayMins);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 907. Sum of Subarray Minimums)
    // https://leetcode.com/problems/sum-of-subarray-minimums/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases confirmed with an independent brute-force reference (Python,
    // exact big integers, then reduced modulo 10^9 + 7).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] arr);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 17, () -> s.solve(new int[]{3, 1, 2, 4}));
        check(v + " example 2", 444, () -> s.solve(new int[]{11, 81, 94, 43, 3}));
        // edge: a single element
        check(v + " single element", 50, () -> s.solve(new int[]{50}));
        // edge: equal values - each of the 6 subarrays must be counted exactly once
        check(v + " ties", 12, () -> s.solve(new int[]{2, 2, 2}));
        // edge: big numbers: one slightly smaller value in the middle of 2000 values of 30000;
        // the exact sum is 60089027999, so the modulo and long arithmetic both matter
        check(v + " big sum", 89027579, () -> s.solve(valley()));
    }

    // 1000 times 30000, then 29999, then 1000 times 30000
    private static int[] valley() {
        int[] a = new int[2001];
        Arrays.fill(a, 30000);
        a[1000] = 29999;
        return a;
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

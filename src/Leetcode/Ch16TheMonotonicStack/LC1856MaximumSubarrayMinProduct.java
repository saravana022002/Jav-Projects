package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1856. Maximum Subarray Min-Product   [Medium]
 * https://leetcode.com/problems/maximum-subarray-min-product/
 * Topics: Array, Stack, Monotonic Stack, Prefix Sum, Cartesian Tree
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   The min-product of an array is its smallest value multiplied by the sum of all its values
 *   (for [3,2,5] that is 2 * (3+2+5) = 20). Among all non-empty contiguous subarrays of nums,
 *   find the largest min-product and return it modulo 10^9 + 7. The largest one must be found
 *   before the modulo is applied; the unreduced answer is guaranteed to fit in a signed
 *   64-bit integer.
 *
 * EXAMPLES
 *   nums = [1,2,3,2]      ->  14    subarray [2,3,2]:  2 * (2+3+2) = 14
 *   nums = [2,3,3,1,2]    ->  18    subarray [3,3]:    3 * (3+3) = 18
 *   nums = [3,1,5,6,4,2]  ->  60    subarray [5,6,4]:  4 * (5+6+4) = 60
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^7
 * </pre>
 */
public class LC1856MaximumSubarrayMinProduct {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int maxSumMinProductA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     */
    public static int maxSumMinProductB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1856MaximumSubarrayMinProduct::maxSumMinProductA);
        runAll("B", LC1856MaximumSubarrayMinProduct::maxSumMinProductB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1856. Maximum Subarray Min-Product)
    // https://leetcode.com/problems/maximum-subarray-min-product/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent brute-force reference (Python,
    // exact big integers, then reduced modulo 10^9 + 7).
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 14, () -> s.solve(new int[]{1, 2, 3, 2}));
        check(v + " example 2", 18, () -> s.solve(new int[]{2, 3, 3, 1, 2}));
        check(v + " example 3", 60, () -> s.solve(new int[]{3, 1, 5, 6, 4, 2}));
        // edge: a single element, 7 * 7
        check(v + " single element", 49, () -> s.solve(new int[]{7}));
        // edge: the best product is 1600000000, which is 599999993 after the modulo; the
        // runner-up 900000000 is the bigger of the two after the modulo
        check(v + " maximise first", 599999993, () -> s.solve(new int[]{40000, 1, 30000}));
        // edge: 2000 values of 10^7: the true answer 2 * 10^17 needs a long, then the modulo
        check(v + " huge product", 600000014, () -> s.solve(allTenMillion()));
    }

    private static int[] allTenMillion() {
        int[] a = new int[2000];
        Arrays.fill(a, 10_000_000);
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

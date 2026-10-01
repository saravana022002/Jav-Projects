package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 268. Missing Number   [Easy]
 * https://leetcode.com/problems/missing-number/
 * Topics: Array, Hash Table, Math, Binary Search, Bit Manipulation, Sorting
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #5
 *       also: ch. 12 Cyclic sort and index-as-address (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds n distinct integers, each taken from the range [0, n]. Exactly one number of
 *   that range does not appear in nums - return it.
 *   Follow-up: can you do it with O(1) extra space and O(n) running time?
 *
 * EXAMPLES
 *   nums = [3,0,1]              ->  2     n = 3, the range is [0,3] and 2 is absent
 *   nums = [0,1]                ->  2     n = 2, the range is [0,2] and 2 is absent
 *   nums = [9,6,4,2,3,5,7,0,1]  ->  8     n = 9, the range is [0,9] and 8 is absent
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 10^4,   0 <= nums[i] <= n,   all numbers in nums are unique
 * </pre>
 */
public class LC268MissingNumber {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     */
    public static int missingNumberA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     */
    public static int missingNumberB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     */
    public static int missingNumberC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC268MissingNumber::missingNumberA);
        runAll("B", LC268MissingNumber::missingNumberB);
        runAll("C", LC268MissingNumber::missingNumberC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 268. Missing Number)
    // https://leetcode.com/problems/missing-number/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force search.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[]{3, 0, 1}));
        check(v + " example 2", 2, () -> s.solve(new int[]{0, 1}));
        check(v + " example 3", 8, () -> s.solve(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        // edge: smallest input, the missing number is n itself
        check(v + " single zero", 1, () -> s.solve(new int[]{0}));
        // edge: smallest input, the missing number is 0
        check(v + " single one", 0, () -> s.solve(new int[]{1}));
        // edge: 0 is the missing number in a longer, shuffled input
        check(v + " zero missing", 0, () -> s.solve(new int[]{3, 1, 2, 5, 4}));
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

package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 283. Move Zeroes   [Easy]
 * https://leetcode.com/problems/move-zeroes/
 * Topics: Array, Two Pointers
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Push every 0 of the integer array nums to the end of the array. The non-zero values must
 *   keep the order they had before. Work in place - do not make a second copy of the array.
 *   Follow-up: keep the total number of operations (writes) as small as you can.
 *
 * EXAMPLES
 *   nums = [0,1,0,3,12]  ->  [1,3,12,0,0]
 *   nums = [0]           ->  [0]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -2^31 <= nums[i] <= 2^31 - 1
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/TwoPointers/LC283MoveZeroes.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC283MoveZeroes {

    /**
     * Variant A - brute force: for every zero, shift the rest left and put the zero last
     * <br>Target: time O(n^2), space O(1)
     */
    public static void moveZeroesBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - two passes: pack the non-zero values to the front, then fill zeros
     * <br>Target: time O(n), space O(1)
     */
    public static void moveZeroesTwoPass(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: one partition pass, swap each non-zero forward
     * <br>Target: time O(n), space O(1)
     */
    public static void moveZeroes(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC283MoveZeroes::moveZeroesBruteForce);
        runAll("B", LC283MoveZeroes::moveZeroesTwoPass);
        runAll("C", LC283MoveZeroes::moveZeroes);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 283. Move Zeroes)
    // https://leetcode.com/problems/move-zeroes/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The methods work in place, so the array is checked afterwards.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        void run(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 3, 12, 0, 0},
                () -> apply(s, new int[]{0, 1, 0, 3, 12}));
        check(v + " example 2", new int[]{0}, () -> apply(s, new int[]{0}));
        // edge: no zero at all - nothing may move
        check(v + " no zeros", new int[]{4, 2, 7}, () -> apply(s, new int[]{4, 2, 7}));
        // edge: nothing but zeros
        check(v + " all zeros", new int[]{0, 0, 0, 0}, () -> apply(s, new int[]{0, 0, 0, 0}));
        // edge: extreme and negative values, zeros at the front, in the middle and at the end
        check(v + " extreme values",
                new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 0, 0, 0},
                () -> apply(s, new int[]{0, Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -1, 0}));
    }

    // runs the in-place method and hands back the array for comparison
    private static int[] apply(Solver s, int[] nums) {
        s.run(nums);
        return nums;
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

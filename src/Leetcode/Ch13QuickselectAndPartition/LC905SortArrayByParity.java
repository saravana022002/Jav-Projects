package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 905. Sort Array By Parity   [Easy]
 * https://leetcode.com/problems/sort-array-by-parity/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Rearrange the integer array nums so that all even numbers come first and all odd numbers
 *   after them. The order inside the two groups does not matter - return any arrangement
 *   that satisfies this.
 *
 * EXAMPLES
 *   nums = [3,1,2,4]  ->  [2,4,3,1]    [4,2,3,1], [2,4,1,3] and [4,2,1,3] are also accepted
 *   nums = [0]        ->  [0]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 5000,   0 <= nums[i] <= 5000
 * </pre>
 */
public class LC905SortArrayByParity {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     */
    public static int[] sortArrayByParityA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     */
    public static int[] sortArrayByParityB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     */
    public static int[] sortArrayByParityC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC905SortArrayByParity::sortArrayByParityA);
        runAll("B", LC905SortArrayByParity::sortArrayByParityB);
        runAll("C", LC905SortArrayByParity::sortArrayByParityC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 905. Sort Array By Parity)
    // https://leetcode.com/problems/sort-array-by-parity/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. Any arrangement with all evens first is accepted: the answer is
    // normalised by sorting its even part and its odd part separately before comparing.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples (the expected arrays are the normalised form)
        check(v + " example 1", new int[]{2, 4, 1, 3}, () -> normalised(s, new int[]{3, 1, 2, 4}));
        check(v + " example 2", new int[]{0}, () -> normalised(s, new int[]{0}));
        // edge: no even number at all
        check(v + " all odd", new int[]{1, 3, 5}, () -> normalised(s, new int[]{5, 3, 1}));
        // edge: every odd number sits in front of every even number
        check(v + " odds first", new int[]{2, 4, 6, 1, 3, 5},
                () -> normalised(s, new int[]{1, 3, 5, 2, 4, 6}));
        // edge: duplicates, zero and the largest allowed values
        check(v + " duplicates", new int[]{0, 5000, 5000, 1, 1, 4999},
                () -> normalised(s, new int[]{4999, 5000, 1, 0, 1, 5000}));
    }

    // Runs the solver and sorts the even part and the odd part of its answer separately, so every
    // valid arrangement gives the same array. The number of evens is counted from the input.
    private static int[] normalised(Solver s, int[] nums) {
        int evens = 0;
        for (int x : nums) {
            if (x % 2 == 0) evens++;
        }
        int[] got = s.solve(nums.clone());
        if (got == null || got.length != nums.length) return got;
        Arrays.sort(got, 0, evens);
        Arrays.sort(got, evens, got.length);
        return got;
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

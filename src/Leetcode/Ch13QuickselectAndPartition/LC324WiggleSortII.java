package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 324. Wiggle Sort II   [Medium]
 * https://leetcode.com/problems/wiggle-sort-ii/
 * Topics: Array, Divide and Conquer, Greedy, Sorting, Quickselect
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Rearrange nums in place so that the values strictly alternate between small and large:
 *   nums[0] < nums[1] > nums[2] < nums[3] > ... Several arrangements can be valid and any of
 *   them is accepted. The input is guaranteed to have at least one valid arrangement.
 *   Follow-up: can you do it in O(n) time and with O(1) extra space?
 *
 * EXAMPLES
 *   nums = [1,5,1,1,6,4]  ->  [1,6,1,5,1,4]     [1,4,1,5,1,6] is accepted too
 *   nums = [1,3,2,2,3,1]  ->  [2,3,1,3,1,2]     any valid arrangement is accepted
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 5 * 10^4,   0 <= nums[i] <= 5000,
 *   a valid arrangement always exists
 * </pre>
 */
public class LC324WiggleSortII {

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(n)
     */
    public static void wiggleSortA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n) expected (O(n^2) worst case), space O(n)
     */
    public static void wiggleSortB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n) expected (O(n^2) worst case), space O(1)
     */
    public static void wiggleSortC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC324WiggleSortII::wiggleSortA);
        runAll("B", LC324WiggleSortII::wiggleSortB);
        runAll("C", LC324WiggleSortII::wiggleSortC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 324. Wiggle Sort II)
    // https://leetcode.com/problems/wiggle-sort-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (every input has a valid arrangement, found by brute force).
    // Many arrangements are valid, so the method is run on a copy and the result is judged:
    // it must be a rearrangement of the input with nums[0] < nums[1] > nums[2] < ...
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        void solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "valid", () -> judge(s, new int[]{1, 5, 1, 1, 6, 4}));
        check(v + " example 2", "valid", () -> judge(s, new int[]{1, 3, 2, 2, 3, 1}));
        // edge: a single element
        check(v + " single element", "valid", () -> judge(s, new int[]{7}));
        // edge: the two middle values are equal (sorting and interleaving naively breaks it)
        check(v + " equal middle values", "valid", () -> judge(s, new int[]{4, 5, 5, 6}));
        // edge: odd length, the median appears in both halves
        check(v + " odd length, repeated median", "valid",
                () -> judge(s, new int[]{1, 1, 2, 2, 3}));
    }

    // runs the method on a copy of the input and describes what it produced:
    // "valid", or what is wrong with the result
    private static String judge(Solver s, int[] input) {
        int[] a = input.clone();
        s.solve(a);
        int[] x = a.clone(), y = input.clone();
        Arrays.sort(x);
        Arrays.sort(y);
        if (!Arrays.equals(x, y)) {
            return "not a rearrangement of the input: " + Arrays.toString(a);
        }
        for (int i = 1; i < a.length; i++) {
            boolean ok = (i % 2 == 1) ? a[i - 1] < a[i] : a[i - 1] > a[i];
            if (!ok) {
                return "pattern broken between index " + (i - 1) + " and " + i + ": "
                        + Arrays.toString(a);
            }
        }
        return "valid";
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

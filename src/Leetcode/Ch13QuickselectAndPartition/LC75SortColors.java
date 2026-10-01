package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 75. Sort Colors   [Medium]
 * https://leetcode.com/problems/sort-colors/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #3
 *       also: ch. 7 Non-comparison sorts: counting, bucket, radix (#2),
 *             ch. 86 Randomisation as a defence against the adversary (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds n objects coloured red, white or blue, written as 0, 1 and 2. Reorder the array
 *   in place so that all the 0s come first, then all the 1s, then all the 2s. The library's
 *   sort function is not allowed.
 *   Follow-up: can you do it in a single pass with only constant extra space?
 *
 * EXAMPLES
 *   nums = [2,0,2,1,1,0]  ->  [0,0,1,1,2,2]
 *   nums = [2,0,1]        ->  [0,1,2]
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 300,   nums[i] is either 0, 1, or 2
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/TwoPointers/LC75SortColors.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC75SortColors {

    /**
     * Variant A - brute force: sort by hand with insertion sort (no library sort)
     * <br>Target: time O(n^2), space O(1)
     */
    public static void sortColorsBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - count the 0s, 1s and 2s, then overwrite the array
     * <br>Target: time O(n), space O(1)   (two passes)
     */
    public static void sortColorsCounting(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: a single pass that partitions into three bands
     * <br>Target: time O(n), space O(1)   (one pass)
     */
    public static void sortColors(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC75SortColors::sortColorsBruteForce);
        runAll("B", LC75SortColors::sortColorsCounting);
        runAll("C", LC75SortColors::sortColors);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 75. Sort Colors)
    // https://leetcode.com/problems/sort-colors/
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
        check(v + " example 1", new int[]{0, 0, 1, 1, 2, 2},
                () -> apply(s, new int[]{2, 0, 2, 1, 1, 0}));
        check(v + " example 2", new int[]{0, 1, 2}, () -> apply(s, new int[]{2, 0, 1}));
        // edge: a single object
        check(v + " single", new int[]{2}, () -> apply(s, new int[]{2}));
        // edge: a 2 in front of a 0 that sits at the far end
        check(v + " two before far zero", new int[]{0, 1, 2}, () -> apply(s, new int[]{1, 2, 0}));
        // edge: completely reversed order
        check(v + " reversed", new int[]{0, 0, 1, 1, 2, 2},
                () -> apply(s, new int[]{2, 2, 1, 1, 0, 0}));
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

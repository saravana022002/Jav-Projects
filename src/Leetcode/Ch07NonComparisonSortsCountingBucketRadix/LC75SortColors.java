package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 75. Sort Colors   [Medium]
 * https://leetcode.com/problems/sort-colors/
 * Topics: Array, Two Pointers, Sorting, Quicksort, Bubble Sort
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #2
 *       also: ch. 13 Quickselect and partition (#3),
 *             ch. 86 Randomisation as a defence against the adversary (#3)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds only the values 0, 1 and 2 (think red, white, blue). Rearrange the array
 *   itself so that all 0s come first, then all 1s, then all 2s. Nothing is returned: nums
 *   must be modified in place. The library sort is not allowed.
 *   Follow-up: can it be done in a single pass using only constant extra space?
 *
 * EXAMPLES
 *   nums = [2,0,2,1,1,0]  ->  nums becomes [0,0,1,1,2,2]
 *   nums = [2,0,1]        ->  nums becomes [0,1,2]
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 300,   nums[i] is 0, 1 or 2
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/TwoPointers/LC75SortColors.java
 * </pre>
 */
public class LC75SortColors {

    /**
     * Variant A - hand-written comparison sort done in place (e.g. insertion sort)
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static void sortColorsInsertionSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: count each colour, then overwrite nums
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 7 Non-comparison sorts: counting, bucket, radix (this chapter)
     */
    public static void sortColors(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - single pass with two boundary pointers, swapping elements into place
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 13 Quickselect and partition (later chapter)
     */
    public static void sortColorsOnePass(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC75SortColors::sortColorsInsertionSort);
        runAll("B", LC75SortColors::sortColors);
        runAll("C", LC75SortColors::sortColorsOnePass);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 75. Sort Colors)
    // https://leetcode.com/problems/sort-colors/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The methods return nothing, so each case runs the method on a
    // fresh array and checks that same array afterwards.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        void solve(int[] nums);
    }

    // runs the in-place method and hands back the array it was given
    private static int[] apply(Solver s, int[] nums) {
        s.solve(nums);
        return nums;
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{0, 0, 1, 1, 2, 2},
                () -> apply(s, new int[]{2, 0, 2, 1, 1, 0}));
        check(v + " example 2", new int[]{0, 1, 2}, () -> apply(s, new int[]{2, 0, 1}));
        // edge: a single element
        check(v + " single element", new int[]{1}, () -> apply(s, new int[]{1}));
        // edge: one colour is missing entirely
        check(v + " no white", new int[]{0, 0, 2, 2}, () -> apply(s, new int[]{2, 0, 2, 0}));
        // edge: a 2 swapped to the back brings a 0 into the current position
        check(v + " swap brings a new value", new int[]{0, 1, 2},
                () -> apply(s, new int[]{1, 2, 0}));
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

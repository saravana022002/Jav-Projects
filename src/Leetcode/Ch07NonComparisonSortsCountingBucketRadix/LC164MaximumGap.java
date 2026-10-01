package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 164. Maximum Gap   [Medium]
 * https://leetcode.com/problems/maximum-gap/
 * Topics: Array, Sorting, Bucket Sort, Radix Sort, Pigeonhole Principle
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Look at nums in sorted order and return the largest difference between two neighbouring
 *   elements of that sorted order. If nums has fewer than two elements, return 0.
 *   The intended solution runs in linear time and uses linear extra space.
 *
 * EXAMPLES
 *   nums = [3,6,9,1]  ->  3     sorted [1,3,6,9], the neighbour gaps are 2, 3, 3
 *   nums = [10]       ->  0     fewer than two elements
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^9
 * </pre>
 */
public class LC164MaximumGap {

    /**
     * Variant A - sort the array, then compare neighbours
     * <br>Target: time O(n log n), space O(1)    (sort the input array in place)
     */
    public static int maximumGapSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - radix sort the values (one counting pass per digit), then compare neighbours
     * <br>Target: time O(n), space O(n)    (the number of digits is fixed by the value range)
     */
    public static int maximumGapRadixSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: value-range buckets plus the pigeonhole principle
     * <br>Target: time O(n), space O(n)
     */
    public static int maximumGap(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC164MaximumGap::maximumGapSort);
        runAll("B", LC164MaximumGap::maximumGapRadixSort);
        runAll("C", LC164MaximumGap::maximumGap);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 164. Maximum Gap)
    // https://leetcode.com/problems/maximum-gap/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a sort-and-scan reference. Every case passes a
    // fresh array, so a variant that sorts its input in place is fine.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{3, 6, 9, 1}));
        check(v + " example 2", 0, () -> s.solve(new int[]{10}));
        // edge: all values equal, the value range is zero
        check(v + " all equal", 0, () -> s.solve(new int[]{5, 5, 5, 5}));
        // edge: two elements at the extremes of the allowed range
        check(v + " two extremes", 1000000000, () -> s.solve(new int[]{0, 1000000000}));
        // edge: tight clusters with one big hole between them, with duplicates
        check(v + " clusters", 94, () -> s.solve(new int[]{100, 3, 2, 1, 98, 97, 97}));
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

package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 162. Find Peak Element   [Medium]
 * https://leetcode.com/problems/find-peak-element/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A peak is an element strictly greater than its neighbours. Outside the array counts as
 *   minus infinity, so the first and the last element only have to beat their single
 *   neighbour. Return the index of ANY peak. Neighbouring elements are never equal. The
 *   intended solution runs in O(log n).
 *
 * EXAMPLES
 *   nums = [1,2,3,1]          ->  2          3 is the only peak
 *   nums = [1,2,1,3,5,6,4]    ->  1 or 5     peaks 2 and 6; either index is accepted
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 1000,   -2^31 <= nums[i] <= 2^31 - 1,
 *   nums[i] != nums[i + 1] for all valid i
 * </pre>
 */
public class LC162FindPeakElement {

    /**
     * Variant A - brute force: scan for the first element that is larger than its successor
     * <br>Target: time O(n), space O(1)
     */
    public static int findPeakElementBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - binary search: stop if mid beats both neighbours, else move to the larger one
     * <br>Target: time O(log n), space O(1)
     */
    public static int findPeakElementThreeWay(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: boundary search on "nums[i] > nums[i + 1]"
     * <br>Target: time O(log n), space O(1)
     */
    public static int findPeakElement(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC162FindPeakElement::findPeakElementBruteForce);
        runAll("B", LC162FindPeakElement::findPeakElementThreeWay);
        runAll("C", LC162FindPeakElement::findPeakElement);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 162. Find Peak Element)
    // https://leetcode.com/problems/find-peak-element/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose peaks were listed by hand and checked with a brute force.
    // Any peak is a correct answer: every case lists all valid indices and passes when your
    // index is one of them.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        checkAny(v + " example 1", Arrays.asList(2), () -> s.solve(new int[]{1, 2, 3, 1}));
        checkAny(v + " example 2", Arrays.asList(1, 5),
                () -> s.solve(new int[]{1, 2, 1, 3, 5, 6, 4}));
        // edge: one element, and it is the smallest possible int
        checkAny(v + " single element", Arrays.asList(0),
                () -> s.solve(new int[]{Integer.MIN_VALUE}));
        // edge: strictly increasing, the peak is the last element
        checkAny(v + " increasing", Arrays.asList(3), () -> s.solve(new int[]{1, 2, 3, 4}));
        // edge: strictly decreasing, the peak is the first element
        checkAny(v + " decreasing", Arrays.asList(0), () -> s.solve(new int[]{4, 3, 2, 1}));
    }

    // one case: your answer must be one of the valid indices; an exception counts as FAIL
    private static void checkAny(String name, List<Integer> valid, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = valid.contains(got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected one of " + valid + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

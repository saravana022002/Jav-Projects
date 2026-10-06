package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 540. Single Element in a Sorted Array   [Medium]
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #7
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is sorted and every value appears exactly twice, except one value that appears
 *   exactly once. Return that single value. The intended solution runs in O(log n) time and
 *   O(1) extra space.
 *
 * EXAMPLES
 *   nums = [1,1,2,3,3,4,4,8,8]  ->  2
 *   nums = [3,3,7,7,10,11,11]   ->  10
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^5
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/BinarySearch/LC540SingleElementInASortedArray.java
 * </pre>
 */
public class LC540SingleElementInASortedArray {

    /**
     * Variant A
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 5 Bit manipulation as a set encoding
     */
    public static int singleNonDuplicateA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int singleNonDuplicateB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (this chapter)
     */
    public static int singleNonDuplicateC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC540SingleElementInASortedArray::singleNonDuplicateA);
        runAll("B", LC540SingleElementInASortedArray::singleNonDuplicateB);
        runAll("C", LC540SingleElementInASortedArray::singleNonDuplicateC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 540. Single Element in a Sorted Array)
    // https://leetcode.com/problems/single-element-in-a-sorted-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an XOR of all elements.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        check(v + " example 2", 10, () -> s.solve(new int[]{3, 3, 7, 7, 10, 11, 11}));
        // edge: the array has just the single element
        check(v + " single element", 1, () -> s.solve(new int[]{1}));
        // edge: the single element comes first
        check(v + " single at the start", 1, () -> s.solve(new int[]{1, 2, 2, 3, 3}));
        // edge: the single element comes last
        check(v + " single at the end", 3, () -> s.solve(new int[]{1, 1, 2, 2, 3}));
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

package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 215. Kth Largest Element in an Array   [Medium]
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Quickselect
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #3
 *       also: ch. 13 Quickselect and partition (#5),
 *             ch. 86 Randomisation as a defence against the adversary (#1)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the k-th largest value of the integer array nums: the value that would stand at
 *   position k if the array were sorted in descending order. Equal values count separately,
 *   so this is not the k-th distinct value. Can you do it without sorting the whole array?
 *
 * EXAMPLES
 *   nums = [3,2,1,5,6,4],        k = 2  ->  5
 *   nums = [3,2,3,1,2,4,5,5,6],  k = 4  ->  4
 *
 * CONSTRAINTS
 *   1 <= k <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Heap/LC215KthLargestElementInAnArrayPriorityQueue.java
 *   src/Practice/Heap/LC215KthLargestElementInAnArrayManualHeap.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC215KthLargestElementInAnArray {

    /**
     * Variant A - sort the array, then read the element at position n - k
     * <br>Target: time O(n log n), space O(1) extra (the sort works in place)
     */
    public static int findKthLargestSorting(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - quickselect: random pivot, partition, keep only the side that holds the answer
     * <br>Target: time O(n) expected (O(n^2) worst case), space O(1)
     */
    public static int findKthLargestQuickselect(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: a min-heap that never holds more than k values
     * <br>Target: time O(n log k), space O(k)
     */
    public static int findKthLargest(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC215KthLargestElementInAnArray::findKthLargestSorting);
        runAll("B", LC215KthLargestElementInAnArray::findKthLargestQuickselect);
        runAll("C", LC215KthLargestElementInAnArray::findKthLargest);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 215. Kth Largest Element in an Array)
    // https://leetcode.com/problems/kth-largest-element-in-an-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected values from a sort-and-read reference). The two big
    // cases (100000 values) are the inputs on which a quickselect with a careless pivot rule
    // becomes quadratic; they take a moment if yours does.
    // The solutions may reorder the array they are given, so every call gets a fresh array.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 5, () -> s.solve(new int[]{3, 2, 1, 5, 6, 4}, 2));
        check(v + " example 2", 4, () -> s.solve(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        // edge: k = n (the smallest value), with negatives and duplicates
        check(v + " k equals n", -7, () -> s.solve(new int[]{4, -7, 4, 10, -7, 0}, 6));
        // edge: 100000 values in ascending order 1..100000
        check(v + " big ascending", 75001, () -> s.solve(sequence(100000), 25000));
        // edge: 100000 equal values
        check(v + " big all equal", -4, () -> s.solve(filled(100000, -4), 70000));
    }

    private static int[] sequence(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        return a;
    }

    private static int[] filled(int n, int value) {
        int[] a = new int[n];
        Arrays.fill(a, value);
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

package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 215. Kth Largest Element in an Array   [Medium]
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Quickselect
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #5
 *       also: ch. 18 Heaps and the k-selection family (#3),
 *             ch. 86 Randomisation as a defence against the adversary (#1)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the k-th largest value of the integer array nums. Equal values count separately:
 *   it is the k-th element in sorted order, not the k-th distinct value.
 *   Can you do it without sorting the whole array?
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
     * Variant A
     * <br>Target: time O(n log n), space O(1) extra
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int findKthLargestA(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log k), space O(k)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (later chapter)
     */
    public static int findKthLargestB(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n) expected (O(n^2) worst case), space O(1)
     * <br>Complete first: ch. 13 Quickselect and partition (this chapter)
     */
    public static int findKthLargestC(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC215KthLargestElementInAnArray::findKthLargestA);
        runAll("B", LC215KthLargestElementInAnArray::findKthLargestB);
        runAll("C", LC215KthLargestElementInAnArray::findKthLargestC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 215. Kth Largest Element in an Array)
    // https://leetcode.com/problems/kth-largest-element-in-an-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The two big cases (100000 values) are the inputs on which a
    // quickselect with a careless pivot rule becomes quadratic; they take a moment if yours does.
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
        check(v + " k equals n", -2, () -> s.solve(new int[]{5, -2, 9, -2, 0}, 5));
        // edge: 100000 values in ascending order, k in the middle (sorted 1..100000)
        check(v + " big ascending", 50001, () -> s.solve(sequence(100000), 50000));
        // edge: 100000 equal values
        check(v + " big all equal", 7, () -> s.solve(filled(100000, 7), 50000));
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

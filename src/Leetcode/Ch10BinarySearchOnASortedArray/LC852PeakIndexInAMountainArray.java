package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 852. Peak Index in a Mountain Array   [Medium]
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 * Topics: Array, Binary Search, Ternary Search
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #5
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   arr is a mountain array: its values strictly increase up to one peak element and then
 *   strictly decrease. Return the index of the peak. The intended solution runs in O(log n).
 *
 * EXAMPLES
 *   arr = [0,1,0]     ->  1
 *   arr = [0,2,1,0]   ->  1
 *   arr = [0,10,5,2]  ->  1
 *
 * CONSTRAINTS
 *   3 <= arr.length <= 10^5,   0 <= arr[i] <= 10^6,   arr is guaranteed to be a mountain array
 * </pre>
 */
public class LC852PeakIndexInAMountainArray {

    /**
     * Variant A - brute force: scan for the first element that is larger than its successor
     * <br>Target: time O(n), space O(1)
     */
    public static int peakIndexInMountainArrayBruteForce(int[] arr) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - binary search that looks at both neighbours of mid and stops on the peak
     * <br>Target: time O(log n), space O(1)
     */
    public static int peakIndexInMountainArrayThreeWay(int[] arr) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: boundary search on "arr[i] > arr[i + 1]"
     * <br>Target: time O(log n), space O(1)
     */
    public static int peakIndexInMountainArray(int[] arr) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC852PeakIndexInAMountainArray::peakIndexInMountainArrayBruteForce);
        runAll("B", LC852PeakIndexInAMountainArray::peakIndexInMountainArrayThreeWay);
        runAll("C", LC852PeakIndexInAMountainArray::peakIndexInMountainArray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 852. Peak Index in a Mountain Array)
    // https://leetcode.com/problems/peak-index-in-a-mountain-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a linear scan.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] arr);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{0, 1, 0}));
        check(v + " example 2", 1, () -> s.solve(new int[]{0, 2, 1, 0}));
        check(v + " example 3", 1, () -> s.solve(new int[]{0, 10, 5, 2}));
        // edge: the peak is the second-to-last element
        check(v + " peak near the end", 5, () -> s.solve(new int[]{1, 2, 3, 4, 5, 6, 2}));
        // edge: a longer mountain with the peak in the middle
        check(v + " peak in the middle", 4, () -> s.solve(new int[]{1, 3, 5, 7, 9, 8, 6, 4, 2, 0}));
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

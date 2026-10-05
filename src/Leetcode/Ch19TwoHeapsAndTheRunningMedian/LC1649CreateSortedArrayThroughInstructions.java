package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1649. Create Sorted Array through Instructions   [Hard]
 * https://leetcode.com/problems/create-sorted-array-through-instructions/
 * Topics: Array, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree,
 *         Merge Sort, Ordered Set
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #12
 *       also: ch. 39 Fenwick trees (#8), ch. 40 Segment trees (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Start with an empty sorted container and insert the numbers of instructions one by one,
 *   from left to right. Inserting x costs the smaller of two counts: how many numbers already
 *   in the container are strictly smaller than x, and how many are strictly greater than x.
 *   Return the total cost of all insertions, modulo 10^9 + 7.
 *
 * EXAMPLES
 *   instructions = [1,5,6,2]            ->  1    costs 0,0,0,1  (2 has 1 smaller, 2 greater)
 *   instructions = [1,2,3,6,5,4]        ->  3    costs 0,0,0,0,1,2
 *   instructions = [1,3,3,3,2,4,2,1,2]  ->  4    costs 0,0,0,0,1,0,1,0,2
 *
 * CONSTRAINTS
 *   1 <= instructions.length <= 10^5,   1 <= instructions[i] <= 10^5
 * </pre>
 */
public class LC1649CreateSortedArrayThroughInstructions {

    /**
     * Variant A - brute force: for each instruction, scan the inserted numbers, count both sides
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int createSortedArrayBruteForce(int[] instructions) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - keep a sorted array; binary-search both counts, insert by shifting
     * <br>Target: time O(n^2) worst case (only the shifting; the searches are O(log n)), space O(n)
     * <br>Complete first: ch. 10 Binary search on a sorted array
     */
    public static int createSortedArrayBinarySearch(int[] instructions) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: Fenwick tree of counts indexed by value
     * <br>Target: time O(n log V), space O(V)     (V = largest value, at most 10^5)
     * <br>Complete first: ch. 19 Two heaps and the running median (this chapter)
     *     + ch. 39 Fenwick trees (later chapter)
     */
    public static int createSortedArray(int[] instructions) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1649CreateSortedArrayThroughInstructions::createSortedArrayBruteForce);
        runAll("B", LC1649CreateSortedArrayThroughInstructions::createSortedArrayBinarySearch);
        runAll("C", LC1649CreateSortedArrayThroughInstructions::createSortedArray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1649. Create Sorted Array through Instructions)
    // https://leetcode.com/problems/create-sorted-array-through-instructions/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a count-by-scanning reference. The modulo is not
    // exercised here: the total only passes 10^9 + 7 for inputs of more than 60000 numbers, which
    // would make the quadratic variants too slow for a quick test.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] instructions);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{1, 5, 6, 2}));
        check(v + " example 2", 3, () -> s.solve(new int[]{1, 2, 3, 6, 5, 4}));
        check(v + " example 3", 4, () -> s.solve(new int[]{1, 3, 3, 3, 2, 4, 2, 1, 2}));
        // edge: a single instruction
        check(v + " single", 0, () -> s.solve(new int[]{7}));
        // edge: equal numbers are neither smaller nor greater, so the cost stays 0
        check(v + " all equal", 0, () -> s.solve(new int[]{5, 5, 5, 5, 5}));
        // edge: smallest and largest allowed values, with repeats
        check(v + " limits", 6, () -> s.solve(new int[]{100000, 1, 100000, 50000, 1, 100000, 2,
                99999}));
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
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

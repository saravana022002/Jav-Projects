package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 315. Count of Smaller Numbers After Self   [Hard]
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * Topics: Array, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree,
 *         Merge Sort, Ordered Set, Treap
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #10
 *       also: ch. 39 Fenwick trees (#4), ch. 40 Segment trees (#3), ch. 59 Divide and conquer (#8)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   For every position i of the integer array nums, count how many elements to the right of
 *   position i are strictly smaller than nums[i]. Return these counts as a list, where entry
 *   i belongs to nums[i]. Equal values do not count as smaller.
 *
 * EXAMPLES
 *   nums = [5,2,6,1]  ->  [2,1,1,0]     5 has 2 and 1 after it, 2 has 1, 6 has 1, 1 has none
 *   nums = [-1]       ->  [0]
 *   nums = [-1,-1]    ->  [0,0]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
 * </pre>
 */
public class LC315CountOfSmallerNumbersAfterSelf {

    /**
     * Variant A - brute force: for every position, scan everything to its right
     * <br>Target: time O(n^2), space O(1) extra besides the answer
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static List<Integer> countSmallerBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - Fenwick tree over the values: scan from the right, count the smaller ones seen
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 39 Fenwick trees (later chapter)
     */
    public static List<Integer> countSmallerFenwick(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: merge sort that carries the indices, counting in merges
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     */
    public static List<Integer> countSmaller(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC315CountOfSmallerNumbersAfterSelf::countSmallerBruteForce);
        runAll("B", LC315CountOfSmallerNumbersAfterSelf::countSmallerFenwick);
        runAll("C", LC315CountOfSmallerNumbersAfterSelf::countSmaller);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 315. Count of Smaller Numbers After Self)
    // https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases (expected counts checked against a scan of the right side).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<Integer> solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh array)
        check(v + " example 1", Arrays.asList(2, 1, 1, 0), () -> s.solve(new int[]{5, 2, 6, 1}));
        check(v + " example 2", Arrays.asList(0), () -> s.solve(new int[]{-1}));
        check(v + " example 3", Arrays.asList(0, 0), () -> s.solve(new int[]{-1, -1}));
        // edge: strictly decreasing, every later element is smaller
        check(v + " decreasing", Arrays.asList(4, 3, 2, 1, 0),
                () -> s.solve(new int[]{5, 4, 3, 2, 1}));
        // edge: repeated values (equal is not smaller)
        check(v + " duplicates", Arrays.asList(2, 2, 0, 1, 0),
                () -> s.solve(new int[]{3, 3, 1, 3, 1}));
        // edge: the extreme values of the constraints
        check(v + " extremes", Arrays.asList(3, 0, 1, 1, 0),
                () -> s.solve(new int[]{10000, -10000, 0, 10000, -10000}));
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

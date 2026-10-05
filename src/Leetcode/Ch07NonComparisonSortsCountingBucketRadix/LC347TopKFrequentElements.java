package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 347. Top K Frequent Elements   [Medium]
 * https://leetcode.com/problems/top-k-frequent-elements/
 * Topics: Array, Hash Table, Divide and Conquer, Sorting, Heap (Priority Queue), Bucket Sort,
 *         Counting, Quickselect
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #6
 *       also: ch. 4 Frequency and multiset reasoning (#7), ch. 13 Quickselect and partition (#8),
 *             ch. 18 Heaps and the k-selection family (#5),
 *             ch. 86 Randomisation as a defence against the adversary (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the k values that occur most often in nums. The order of the returned values does
 *   not matter. The input guarantees that the answer is unique (no tie at the cut-off).
 *   Follow-up: the running time must be better than O(n log n).
 *
 * EXAMPLES
 *   nums = [1,1,1,2,2,3],            k = 2  ->  [1,2]
 *   nums = [1],                      k = 1  ->  [1]
 *   nums = [1,2,1,2,1,2,3,1,3,2],    k = 2  ->  [1,2]
 *   (any order of the returned values is accepted)
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4,
 *   k is in the range [1, number of unique elements],   the answer is guaranteed to be unique
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Heap/LC347TopKFrequentElements.java
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC347TopKFrequentElements.java
 * </pre>
 */
public class LC347TopKFrequentElements {

    /**
     * Variant A - count the values, sort the distinct values by count, take the first k
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 6 Sorting as a preprocessing step
     */
    public static int[] topKFrequentSort(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - count the values, keep a min-heap of at most k distinct values
     * <br>Target: time O(n log k), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 18 Heaps and the k-selection family (later chapter)
     */
    public static int[] topKFrequentHeap(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: bucket the values by count, read buckets from the top
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 7 Non-comparison sorts: counting, bucket, radix (this chapter)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC347TopKFrequentElements::topKFrequentSort);
        runAll("B", LC347TopKFrequentElements::topKFrequentHeap);
        runAll("C", LC347TopKFrequentElements::topKFrequent);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 347. Top K Frequent Elements)
    // https://leetcode.com/problems/top-k-frequent-elements/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a reference solution. The answer may come back
    // in any order, so both sides are sorted before they are compared.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2},
                () -> sorted(s.solve(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        check(v + " example 2", new int[]{1}, () -> sorted(s.solve(new int[]{1}, 1)));
        check(v + " example 3", new int[]{1, 2},
                () -> sorted(s.solve(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2)));
        // edge: k equals the number of distinct values, so everything is returned
        check(v + " k = all distinct", new int[]{4, 5, 6},
                () -> sorted(s.solve(new int[]{4, 4, 4, 5, 5, 6}, 3)));
        // edge: negative values and both extreme values of the range
        check(v + " negatives and extremes", new int[]{-10000, 0},
                () -> sorted(s.solve(new int[]{-10000, -10000, 10000, 0, 0, 0}, 2)));
        // edge: one value fills the whole array, its count equals n
        check(v + " all equal", new int[]{2}, () -> sorted(s.solve(new int[]{2, 2, 2, 2}, 1)));
    }

    private static int[] sorted(int[] a) {
        int[] copy = a.clone();
        Arrays.sort(copy);
        return copy;
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

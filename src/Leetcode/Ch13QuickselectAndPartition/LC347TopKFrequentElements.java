package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 347. Top K Frequent Elements   [Medium]
 * https://leetcode.com/problems/top-k-frequent-elements/
 * Topics: Array, Hash Table, Divide and Conquer, Sorting, Heap (Priority Queue), Bucket Sort,
 *         Counting, Quickselect
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #8
 *       also: ch. 4 Frequency and multiset reasoning (#7),
 *             ch. 7 Non-comparison sorts: counting, bucket, radix (#6),
 *             ch. 18 Heaps and the k-selection family (#5),
 *             ch. 86 Randomisation as a defence against the adversary (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the k values that occur most often in the integer array nums. The values may be
 *   returned in any order, and the answer is guaranteed to be unique (no tie across the
 *   cut-off). Follow-up: the running time must be better than O(n log n).
 *
 * EXAMPLES
 *   nums = [1,1,1,2,2,3],            k = 2  ->  [1,2]
 *   nums = [1],                      k = 1  ->  [1]
 *   nums = [1,2,1,2,1,2,3,1,3,2],    k = 2  ->  [1,2]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4,
 *   k is between 1 and the number of distinct values, the answer is unique
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Heap/LC347TopKFrequentElements.java
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC347TopKFrequentElements.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC347TopKFrequentElements {

    /**
     * Variant A - count each value, sort the distinct values by count, take the first k
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 6 Sorting as a preprocessing step
     */
    public static int[] topKFrequentSorting(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - count each value, keep a min-heap of the k most frequent values
     * <br>Target: time O(n log k), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 18 Heaps and the k-selection family (later chapter)
     */
    public static int[] topKFrequentHeap(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: quickselect over the distinct values by their counts
     * <br>Target: time O(n) expected (O(n^2) worst case), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 13 Quickselect and partition (this chapter)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC347TopKFrequentElements::topKFrequentSorting);
        runAll("B", LC347TopKFrequentElements::topKFrequentHeap);
        runAll("C", LC347TopKFrequentElements::topKFrequent);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 347. Top K Frequent Elements)
    // https://leetcode.com/problems/top-k-frequent-elements/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases. The values may come back in any order, so the answer is
    // sorted before comparing; expected arrays are in sorted order.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh array: a solution may reorder its input)
        check(v + " example 1", new int[]{1, 2},
                () -> sorted(s.solve(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        check(v + " example 2", new int[]{1}, () -> sorted(s.solve(new int[]{1}, 1)));
        check(v + " example 3", new int[]{1, 2},
                () -> sorted(s.solve(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2)));
        // edge: negative values and both ends of the allowed range
        check(v + " negatives and extremes", new int[]{-10000, 10000},
                () -> sorted(s.solve(new int[]{-10000, 10000, 10000, -10000, -10000, 0}, 2)));
        // edge: k equals the number of distinct values
        check(v + " k equals distinct count", new int[]{4, 5, 6},
                () -> sorted(s.solve(new int[]{4, 4, 5, 6, 6, 6}, 3)));
        // edge: everything below the winner is tied (the tie is outside the answer)
        check(v + " ties below the cut-off", new int[]{5},
                () -> sorted(s.solve(new int[]{5, 5, 5, 5, 1, 2, 3, 4}, 1)));
    }

    // order-insensitive form of an answer: a sorted copy
    private static int[] sorted(int[] a) {
        if (a == null) return null;
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

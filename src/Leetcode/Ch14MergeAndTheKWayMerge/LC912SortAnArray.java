package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 912. Sort an Array   [Medium]
 * https://leetcode.com/problems/sort-an-array/
 * Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Merge Sort, Bucket Sort,
 *         Radix Sort, Counting Sort
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #5
 *       also: ch. 6 Sorting as a preprocessing step (#2),
 *             ch. 7 Non-comparison sorts: counting, bucket, radix (#12),
 *             ch. 59 Divide and conquer (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Sort the integer array nums in ascending order and return it. Write the sort yourself
 *   (no built-in sorting calls); the running time must be O(n log n) and the extra space as
 *   small as you can make it.
 *
 * EXAMPLES
 *   nums = [5,2,3,1]       ->  [1,2,3,5]
 *   nums = [5,1,1,2,0,0]   ->  [0,0,1,1,2,5]     values need not be unique
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 5 * 10^4,   -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Sorting/LC912SortAnArrayV1.java
 *   src/Practice/Sorting/LC912SortAnArrayV2.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC912SortAnArray {

    /**
     * Variant A - insertion sort: grow a sorted prefix, shifting larger values to the right
     * <br>Target: time O(n^2), space O(1)
     */
    public static int[] sortArrayInsertion(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - heap sort: build a max-heap inside the array, then move the maximum to the back
     * <br>Target: time O(n log n), space O(1)
     */
    public static int[] sortArrayHeapSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: top-down merge sort merging through one scratch array
     * <br>Target: time O(n log n), space O(n)
     */
    public static int[] sortArray(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC912SortAnArray::sortArrayInsertion);
        runAll("B", LC912SortAnArray::sortArrayHeapSort);
        runAll("C", LC912SortAnArray::sortArray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 912. Sort an Array)
    // https://leetcode.com/problems/sort-an-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The large case uses a fixed random seed and takes its expected
    // result from Arrays.sort (the test may use it; your solution may not).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums);
    }

    // the largest allowed size, pseudo-random values over the whole allowed range
    private static final int[] BIG = new int[50000];

    private static final int[] BIG_SORTED;

    static {
        Random rnd = new Random(12345);
        for (int i = 0; i < BIG.length; i++) BIG[i] = rnd.nextInt(100001) - 50000;
        BIG_SORTED = BIG.clone();
        Arrays.sort(BIG_SORTED);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh array)
        check(v + " example 1", new int[]{1, 2, 3, 5}, () -> s.solve(new int[]{5, 2, 3, 1}));
        check(v + " example 2", new int[]{0, 0, 1, 1, 2, 5},
                () -> s.solve(new int[]{5, 1, 1, 2, 0, 0}));
        // edge: a single element
        check(v + " single element", new int[]{7}, () -> s.solve(new int[]{7}));
        // edge: negatives, duplicates and the extreme values of the constraints
        check(v + " negatives, duplicates, extremes",
                new int[]{-50000, -50000, -3, -3, 0, 0, 50000, 50000},
                () -> s.solve(new int[]{50000, -50000, 0, -3, 50000, -50000, -3, 0}));
        // edge: the maximum size
        check(v + " 50000 random values", BIG_SORTED, () -> s.solve(BIG.clone()));
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

    // arrays longer than 20 values are shown shortened
    private static String show(Object o) {
        if (o instanceof int[]) {
            int[] a = (int[]) o;
            if (a.length <= 20) return Arrays.toString(a);
            return "int[" + a.length + "] starting " + Arrays.toString(Arrays.copyOf(a, 8));
        }
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

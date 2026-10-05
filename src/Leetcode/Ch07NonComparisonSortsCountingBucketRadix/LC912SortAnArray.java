package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 912. Sort an Array   [Medium]
 * https://leetcode.com/problems/sort-an-array/
 * Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Merge Sort, Bucket Sort,
 *         Radix Sort, Counting Sort
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #12
 *       also: ch. 6 Sorting as a preprocessing step (#2), ch. 14 Merge and the k-way merge (#5),
 *             ch. 59 Divide and conquer (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Sort nums in ascending order and return it. No built-in sort function may be used. The
 *   expected running time is O(n log n) with the smallest extra space you can manage.
 *
 * EXAMPLES
 *   nums = [5,2,3,1]       ->  [1,2,3,5]
 *   nums = [5,1,1,2,0,0]   ->  [0,0,1,1,2,5]     values may repeat
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 5 * 10^4,   -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Sorting/LC912SortAnArrayV1.java
 *   src/Practice/Sorting/LC912SortAnArrayV2.java
 * </pre>
 */
public class LC912SortAnArray {

    /**
     * Variant A - hand-written quadratic sort (e.g. insertion sort)
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int[] sortArrayInsertionSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - hand-written O(n log n) comparison sort (merge sort or heap sort)
     * <br>Target: time O(n log n), space O(n)    (heap sort gets away with O(1))
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int[] sortArrayComparisonSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: LSD radix sort, one stable counting pass per digit
     * <br>Target: time O(d * (n + b)), space O(n + b)    (d digits in base b: fixed here, so O(n))
     * <br>Complete first: ch. 7 Non-comparison sorts: counting, bucket, radix (this chapter)
     */
    public static int[] sortArray(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC912SortAnArray::sortArrayInsertionSort);
        runAll("B", LC912SortAnArray::sortArrayComparisonSort);
        runAll("C", LC912SortAnArray::sortArray);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 912. Sort an Array)
    // https://leetcode.com/problems/sort-an-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The expected arrays of the edge cases were written by hand;
    // the big case is compared with java.util.Arrays.sort (used here only to build the
    // expectation - your solution must not call it). Every case passes a fresh array.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // 50000 pseudo-random values in [-50000, 50000], and the same values sorted by the JDK
    private static final int[] BIG = bigInput();

    private static final int[] BIG_SORTED = sorted(BIG);

    interface Solver {
        int[] solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2, 3, 5}, () -> s.solve(new int[]{5, 2, 3, 1}));
        check(v + " example 2", new int[]{0, 0, 1, 1, 2, 5},
                () -> s.solve(new int[]{5, 1, 1, 2, 0, 0}));
        // edge: a single element
        check(v + " single element", new int[]{42}, () -> s.solve(new int[]{42}));
        // edge: negatives, duplicates and both extreme values of the range
        check(v + " negatives and extremes", new int[]{-50000, -50000, -1, 0, 1, 50000, 50000},
                () -> s.solve(new int[]{50000, -50000, 0, -50000, 50000, -1, 1}));
        // edge: the maximum length with random values
        check(v + " 50000 random values", BIG_SORTED, () -> s.solve(BIG.clone()));
    }

    private static int[] bigInput() {
        int[] a = new int[50_000];
        long x = 2024;
        for (int i = 0; i < a.length; i++) {
            x = (x * 1103515245L + 12345L) & 0x7fffffffL;
            a[i] = (int) ((x >>> 8) % 100_001) - 50_000;
        }
        return a;
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
        if (o instanceof int[]) {
            int[] a = (int[]) o;
            return a.length <= 20 ? Arrays.toString(a) : "an int[" + a.length + "]";
        }
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

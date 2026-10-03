package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 912. Sort an Array   [Medium]
 * https://leetcode.com/problems/sort-an-array/
 * Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Merge Sort, Counting Sort
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #2
 *       also: ch. 7 Non-comparison sorts: counting, bucket, radix (#12),
 *             ch. 14 Merge and the k-way merge (#5), ch. 59 Divide and conquer (#4)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Sort the integer array nums in ascending order and return it. Built-in sorting functions
 *   are not allowed. Aim for O(n log n) time and as little extra space as you can manage.
 *
 * EXAMPLES
 *   nums = [5,2,3,1]      ->  [1,2,3,5]
 *   nums = [5,1,1,2,0,0]  ->  [0,0,1,1,2,5]     the values need not be unique
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
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int[] sortArrayA(int[] nums) {
        // TODO: write your solution
        int min;
        int index = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            min = Integer.MAX_VALUE;
            for (int j = i; j < nums.length; j++) {
                if(min > nums[j]) {
                    min = nums[j];
                    index = j;
                }
            }
            temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    /**
     * Variant B
     * <br>Target: time O(n log n) expected (O(n^2) worst case), space O(log n)
     */
    public static int[] sortArrayB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     */
    public static int[] sortArrayC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC912SortAnArray::sortArrayA);
        runAll("B", LC912SortAnArray::sortArrayB);
        runAll("C", LC912SortAnArray::sortArrayC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 912. Sort an Array)
    // https://leetcode.com/problems/sort-an-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The big case is 5000 pseudo-random values (fixed seed, many
    // duplicates) compared with java.util.Arrays.sort, used here only as the reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2, 3, 5}, () -> s.solve(new int[]{5, 2, 3, 1}));
        check(v + " example 2", new int[]{0, 0, 1, 1, 2, 5},
                () -> s.solve(new int[]{5, 1, 1, 2, 0, 0}));
        // edge: a single element
        check(v + " single element", new int[]{7}, () -> s.solve(new int[]{7}));
        // edge: strictly descending input with the extreme values
        check(v + " descending", new int[]{-50000, -20, -10, 0, 10, 20, 30, 40, 50000},
                () -> s.solve(new int[]{50000, 40, 30, 20, 10, 0, -10, -20, -50000}));
        // edge: 5000 values from -50..50, so every value repeats about 50 times
        int[] big = new int[5000];
        Random rnd = new Random(12345);
        for (int i = 0; i < big.length; i++) big[i] = rnd.nextInt(101) - 50;
        int[] sortedBig = big.clone();
        Arrays.sort(sortedBig);
        check(v + " many duplicates", sortedBig, () -> s.solve(big.clone()));
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
            String s = Arrays.toString(a.length > 20 ? Arrays.copyOf(a, 20) : a);
            return a.length > 20 ? s + "... (" + a.length + " values)" : s;
        }
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

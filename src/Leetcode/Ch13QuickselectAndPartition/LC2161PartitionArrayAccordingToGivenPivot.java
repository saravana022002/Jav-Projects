package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2161. Partition Array According to Given Pivot   [Medium]
 * https://leetcode.com/problems/partition-array-according-to-given-pivot/
 * Topics: Array, Two Pointers, Simulation
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Rearrange nums around the value pivot (it always occurs in nums): first come all the
 *   elements smaller than pivot, then all the elements equal to pivot, then all the elements
 *   bigger than pivot. The smaller elements must keep their original relative order, and so
 *   must the bigger elements. Return the rearranged array.
 *
 * EXAMPLES
 *   nums = [9,12,5,10,14,3,10], pivot = 10  ->  [9,5,3,10,10,12,14]
 *   nums = [-3,4,3,2],          pivot = 2   ->  [-3,2,4,3]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^6 <= nums[i] <= 10^6,   pivot equals an element of nums
 * </pre>
 */
public class LC2161PartitionArrayAccordingToGivenPivot {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1) besides the returned array
     */
    public static int[] pivotArrayA(int[] nums, int pivot) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     */
    public static int[] pivotArrayB(int[] nums, int pivot) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     */
    public static int[] pivotArrayC(int[] nums, int pivot) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2161PartitionArrayAccordingToGivenPivot::pivotArrayA);
        runAll("B", LC2161PartitionArrayAccordingToGivenPivot::pivotArrayB);
        runAll("C", LC2161PartitionArrayAccordingToGivenPivot::pivotArrayC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2161. Partition Array According to
    // Given Pivot)
    // https://leetcode.com/problems/partition-array-according-to-given-pivot/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The answer is unique because the order inside each band is fixed.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums, int pivot);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{9, 5, 3, 10, 10, 12, 14},
                () -> s.solve(new int[]{9, 12, 5, 10, 14, 3, 10}, 10));
        check(v + " example 2", new int[]{-3, 2, 4, 3}, () -> s.solve(new int[]{-3, 4, 3, 2}, 2));
        // edge: a single element
        check(v + " single", new int[]{7}, () -> s.solve(new int[]{7}, 7));
        // edge: the original order must survive inside the smaller and the bigger band
        check(v + " stability", new int[]{1, 2, 2, 3, 6, 5, 4},
                () -> s.solve(new int[]{6, 1, 5, 2, 4, 3, 2}, 3));
        // edge: the pivot is the minimum, so nothing is smaller than it
        check(v + " pivot is minimum", new int[]{2, 2, 4, 9, 7, 5},
                () -> s.solve(new int[]{4, 2, 9, 2, 7, 5}, 2));
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

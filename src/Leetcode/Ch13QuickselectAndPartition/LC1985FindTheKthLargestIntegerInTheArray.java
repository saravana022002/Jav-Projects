package Leetcode.Ch13QuickselectAndPartition;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1985. Find the Kth Largest Integer in the Array   [Medium]
 * https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/
 * Topics: Array, String, Divide and Conquer, Sorting, Heap (Priority Queue), Quickselect
 * Book: Algorithmic Thinking - ch. 13 Quickselect and partition, Practice #7
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds non-negative integers written as strings, without leading zeros. They can be
 *   up to 100 digits long, so they do not fit in a long. Return the string of the k-th largest
 *   integer in nums. Equal values count separately: in ["1","2","2"] both the first and the
 *   second largest are "2", and the third largest is "1".
 *
 * EXAMPLES
 *   nums = ["3","6","7","10"],   k = 4  ->  "3"    sorted: 3, 6, 7, 10
 *   nums = ["2","21","12","1"],  k = 3  ->  "2"    sorted: 1, 2, 12, 21
 *   nums = ["0","0"],            k = 2  ->  "0"    sorted: 0, 0
 *
 * CONSTRAINTS
 *   1 <= k <= nums.length <= 10^4,   1 <= nums[i].length <= 100,
 *   nums[i] consists of digits only and has no leading zeros
 * </pre>
 */
public class LC1985FindTheKthLargestIntegerInTheArray {

    /**
     * Variant A
     * <br>Target: time O(n log n * L) with L = longest string, space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static String kthLargestNumberA(String[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log k * L) with L = longest string, space O(k)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (later chapter)
     */
    public static String kthLargestNumberB(String[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n * L) expected (O(n^2 * L) worst case), space O(1) extra
     * <br>Complete first: ch. 13 Quickselect and partition (this chapter)
     */
    public static String kthLargestNumberC(String[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1985FindTheKthLargestIntegerInTheArray::kthLargestNumberA);
        runAll("B", LC1985FindTheKthLargestIntegerInTheArray::kthLargestNumberB);
        runAll("C", LC1985FindTheKthLargestIntegerInTheArray::kthLargestNumberC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1985. Find the Kth Largest Integer in the Array)
    // https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases (expected values checked against a BigInteger reference).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call gets a fresh array: a solution may reorder its input)
        check(v + " example 1", "3", () -> s.solve(new String[]{"3", "6", "7", "10"}, 4));
        check(v + " example 2", "2", () -> s.solve(new String[]{"2", "21", "12", "1"}, 3));
        check(v + " example 3", "0", () -> s.solve(new String[]{"0", "0"}, 2));
        // edge: a longer string is larger, even when it sorts earlier as text ("10" < "9")
        check(v + " length beats text order", "10",
                () -> s.solve(new String[]{"10", "9", "100"}, 2));
        // edge: equal values count separately
        check(v + " duplicates", "1", () -> s.solve(new String[]{"1", "2", "2"}, 3));
        // edge: 100-digit values, far beyond the range of long
        String nines100 = "9".repeat(100);
        String tenPow99 = "1" + "0".repeat(99);
        String nines99 = "9".repeat(99);
        check(v + " 100-digit values", tenPow99,
                () -> s.solve(new String[]{nines99, tenPow99, nines100}, 2));
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

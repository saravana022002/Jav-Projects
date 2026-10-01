package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 703. Kth Largest Element in a Stream   [Easy]
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * Topics: Tree, Design, Binary Search Tree, Heap (Priority Queue), Binary Tree, Data Stream
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #1
 *       also: ch. 13 Quickselect and partition (#11), ch. 18 Heaps and the k-selection family (#2)
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Build a class that follows a growing collection of test scores and always knows the
 *   k-th highest one. The constructor receives k and the scores known so far. Every call
 *   add(val) inserts one more score and returns the k-th largest score among all scores
 *   seen so far. Equal scores count separately: it is the k-th element of the sorted list,
 *   not the k-th distinct value.
 *
 * EXAMPLES
 *   KthLargest(3, [4,5,8,2]):
 *     add(3) -> 4,  add(5) -> 5,  add(10) -> 5,  add(9) -> 8,  add(4) -> 8
 *   KthLargest(4, [7,7,7,7,8,3]):
 *     add(2) -> 7,  add(10) -> 7,  add(9) -> 7,  add(9) -> 8
 *
 * CONSTRAINTS
 *   0 <= nums.length <= 10^4,   1 <= k <= nums.length + 1,   -10^4 <= nums[i] <= 10^4,
 *   -10^4 <= val <= 10^4,   at most 10^4 calls will be made to add
 * </pre>
 */
public class LC703KthLargestElementInAStream {

    /**
     * Variant A
     * <br>Target: time O(n log n) per add, space O(n)     (n = scores seen so far)
     */
    public static class KthLargestA {
        public KthLargestA(int k, int[] nums) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int add(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B
     * <br>Target: time O(k) per add, space O(k)
     */
    public static class KthLargestB {
        public KthLargestB(int k, int[] nums) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int add(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant C
     * <br>Target: time O(log k) per add (O(n log k) to build), space O(k)
     */
    public static class KthLargestC {
        public KthLargestC(int k, int[] nums) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int add(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", (k, nums) ->
                new LC703KthLargestElementInAStream.KthLargestA(k, nums)::add);
        runAll("B", (k, nums) ->
                new LC703KthLargestElementInAStream.KthLargestB(k, nums)::add);
        runAll("C", (k, nums) ->
                new LC703KthLargestElementInAStream.KthLargestC(k, nums)::add);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 703. Kth Largest Element in a Stream)
    // https://leetcode.com/problems/kth-largest-element-in-a-stream/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a sort-everything reference. One case replays
    // a whole sequence of add calls and compares the list of returned values.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // builds the object for (k, nums) and returns its add method
    interface Factory {
        IntUnaryOperator create(int k, int[] nums);
    }

    private static void runAll(String v, Factory f) {
        // official examples
        check(v + " example 1", Arrays.asList(4, 5, 5, 8, 8),
                () -> replay(f, 3, new int[]{4, 5, 8, 2}, 3, 5, 10, 9, 4));
        check(v + " example 2", Arrays.asList(7, 7, 7, 8),
                () -> replay(f, 4, new int[]{7, 7, 7, 7, 8, 3}, 2, 10, 9, 9));
        // edge: k = 1 and the stream starts empty
        check(v + " empty start", Arrays.asList(-3, -2, -2, 0),
                () -> replay(f, 1, new int[]{}, -3, -2, -4, 0));
        // edge: fewer than k scores at the start (k = nums.length + 1)
        check(v + " k above size", Arrays.asList(1, 2, 2, 5),
                () -> replay(f, 3, new int[]{5, 1}, 2, 9, 0, 10));
        // edge: extreme values from the constraints
        check(v + " extremes", Arrays.asList(-10000, 9999, 10000, 10000),
                () -> replay(f, 2, new int[]{-10000, 10000}, -10000, 9999, 10000, 0));
    }

    private static List<Integer> replay(Factory f, int k, int[] nums, int... vals) {
        IntUnaryOperator add = f.create(k, nums);
        List<Integer> out = new ArrayList<>();
        for (int val : vals) out.add(add.applyAsInt(val));
        return out;
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

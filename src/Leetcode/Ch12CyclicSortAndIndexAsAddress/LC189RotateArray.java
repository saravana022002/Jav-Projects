package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 189. Rotate Array   [Medium]
 * https://leetcode.com/problems/rotate-array/
 * Topics: Array, Math, Two Pointers
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Turn the array nums rightwards by k places (k is never negative): one place moves the
 *   last element to the front and pushes every other element one index up. Change nums
 *   in place (the methods return nothing).
 *
 * EXAMPLES
 *   nums = [1,2,3,4,5,6,7], k = 3  ->  [5,6,7,1,2,3,4]
 *   nums = [-1,-100,3,99],  k = 2  ->  [3,99,-1,-100]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -2^31 <= nums[i] <= 2^31 - 1,   0 <= k <= 10^5
 *   Follow up: find as many solutions as you can (at least three); can you rotate in place
 *   with O(1) extra space?
 * </pre>
 */
public class LC189RotateArray {

    /**
     * Variant A - extra array: put element i at index (i + k) % n of a new array, copy it back
     * <br>Target: time O(n), space O(n)
     */
    public static void rotateExtraArray(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - three reversals: the whole array, then the first k elements, then the rest
     * <br>Target: time O(n), space O(1)
     */
    public static void rotateReverse(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: cyclic replacement, carry values along i -> (i + k) % n
     * <br>Target: time O(n), space O(1)
     */
    public static void rotate(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC189RotateArray::rotateExtraArray);
        runAll("B", LC189RotateArray::rotateReverse);
        runAll("C", LC189RotateArray::rotate);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 189. Rotate Array)
    // https://leetcode.com/problems/rotate-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (k single-step
    // rotations). The methods work in place: every case runs the method on a fresh array and
    // compares the array afterwards.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        void solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", new int[]{5, 6, 7, 1, 2, 3, 4},
                () -> after(s, new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
        check(v + " example 2", new int[]{3, 99, -1, -100},
                () -> after(s, new int[]{-1, -100, 3, 99}, 2));
        // edge: no rotation at all
        check(v + " k is zero", new int[]{1, 2, 3}, () -> after(s, new int[]{1, 2, 3}, 0));
        // edge: k is larger than the length
        check(v + " k above length", new int[]{2, 1}, () -> after(s, new int[]{1, 2}, 3));
        // edge: k and the length share a factor
        check(v + " shared factor", new int[]{5, 6, 1, 2, 3, 4},
                () -> after(s, new int[]{1, 2, 3, 4, 5, 6}, 2));
    }

    // runs the in-place method and returns the changed array
    private static int[] after(Solver s, int[] nums, int k) {
        s.solve(nums, k);
        return nums;
    }

    // one case: a wrong value, an exception or an endless loop counts as FAIL; the run continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got = hung > 0 ? "skipped (an earlier case of this variant never finished)"
                : callWithTimeout(actual);
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    // runs the case on its own thread, so that an endless loop cannot block the whole run
    private static Object callWithTimeout(Supplier<Object> actual) {
        Object[] box = new Object[1];
        Thread worker = new Thread(null, () -> {
            try {
                box[0] = actual.get();
            } catch (Throwable t) {
                box[0] = "threw " + t.getClass().getSimpleName();
            }
        }, "case", 1L << 26);
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(TIMEOUT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (worker.isAlive()) {
            hung++;
            return "no result after " + TIMEOUT_MS + " ms (endless loop?)";
        }
        return box[0];
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

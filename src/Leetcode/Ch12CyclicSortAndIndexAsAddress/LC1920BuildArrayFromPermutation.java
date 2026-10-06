package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1920. Build Array from Permutation   [Easy]
 * https://leetcode.com/problems/build-array-from-permutation/
 * Topics: Array, Simulation
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is a zero-based permutation: its n elements are distinct integers from 0 to
 *   n - 1. Build and return an array ans of the same length with ans[i] = nums[nums[i]]
 *   for every i. Follow-up: can you do it with O(1) extra memory (no second array)?
 *
 * EXAMPLES
 *   nums = [0,2,1,5,3,4]  ->  [0,1,2,4,5,3]    e.g. ans[1] = nums[nums[1]] = nums[2] = 1
 *   nums = [5,0,1,2,3,4]  ->  [4,5,0,1,2,3]    e.g. ans[0] = nums[nums[0]] = nums[5] = 4
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 1000,   0 <= nums[i] < nums.length,   the elements of nums are distinct
 * </pre>
 */
public class LC1920BuildArrayFromPermutation {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] buildArrayA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1) extra (the answer is nums itself)
     * <br>Complete first: ch. 12 Cyclic sort and index-as-address (this chapter)
     */
    public static int[] buildArrayB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1920BuildArrayFromPermutation::buildArrayA);
        runAll("B", LC1920BuildArrayFromPermutation::buildArrayB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1920. Build Array from Permutation)
    // https://leetcode.com/problems/build-array-from-permutation/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (the definition
    // ans[i] = nums[nums[i]] evaluated in Python).
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int[] solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", new int[]{0, 1, 2, 4, 5, 3},
                () -> s.solve(new int[]{0, 2, 1, 5, 3, 4}));
        check(v + " example 2", new int[]{4, 5, 0, 1, 2, 3},
                () -> s.solve(new int[]{5, 0, 1, 2, 3, 4}));
        // edge: the smallest input
        check(v + " single element", new int[]{0}, () -> s.solve(new int[]{0}));
        // edge: two elements swapped
        check(v + " swap", new int[]{0, 1}, () -> s.solve(new int[]{1, 0}));
        // edge: a longer permutation with several cycles
        check(v + " ten elements", new int[]{4, 3, 5, 1, 0, 6, 9, 7, 2, 8},
                () -> s.solve(new int[]{3, 0, 9, 4, 1, 8, 2, 7, 6, 5}));
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

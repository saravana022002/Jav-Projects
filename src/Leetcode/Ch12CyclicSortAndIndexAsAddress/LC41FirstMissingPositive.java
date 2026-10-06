package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 41. First Missing Positive   [Hard]
 * https://leetcode.com/problems/first-missing-positive/
 * Topics: Array, Hash Table
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums is an unsorted integer array (negative numbers, zero and repeats are allowed).
 *   Find the smallest positive integer that does not occur in it. The intended solution
 *   needs linear time and only O(1) memory beyond the input.
 *
 * EXAMPLES
 *   nums = [1,2,0]        ->  3    1 and 2 are present
 *   nums = [3,4,-1,1]     ->  2    1 is present, 2 is not
 *   nums = [7,8,9,11,12]  ->  1    1 itself is missing
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -2^31 <= nums[i] <= 2^31 - 1
 * </pre>
 */
public class LC41FirstMissingPositive {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     */
    public static int firstMissingPositiveA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 12 Cyclic sort and index-as-address (this chapter)
     */
    public static int firstMissingPositiveB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 12 Cyclic sort and index-as-address (this chapter)
     */
    public static int firstMissingPositiveC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC41FirstMissingPositive::firstMissingPositiveA);
        runAll("B", LC41FirstMissingPositive::firstMissingPositiveB);
        runAll("C", LC41FirstMissingPositive::firstMissingPositiveC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 41. First Missing Positive)
    // https://leetcode.com/problems/first-missing-positive/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent reference (set lookup from 1).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{1, 2, 0}));
        check(v + " example 2", 2, () -> s.solve(new int[]{3, 4, -1, 1}));
        check(v + " example 3", 1, () -> s.solve(new int[]{7, 8, 9, 11, 12}));
        // edge: a repeated value
        check(v + " repeated one", 2, () -> s.solve(new int[]{1, 1}));
        // edge: 1..n all present, the answer is n + 1
        check(v + " full range", 5, () -> s.solve(new int[]{4, 3, 2, 1}));
        // edge: the extreme int values next to the small positives
        check(v + " extreme values", 4,
                () -> s.solve(new int[]{2147483647, -2147483648, 1, 2, 3}));
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

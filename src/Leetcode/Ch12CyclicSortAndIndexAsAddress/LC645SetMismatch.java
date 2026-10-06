package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 645. Set Mismatch   [Easy]
 * https://leetcode.com/problems/set-mismatch/
 * Topics: Array, Hash Table, Bit Manipulation, Sorting
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A set originally held every integer from 1 to n. Because of an error, one number was
 *   copied over another: now one value occurs twice and one value is missing. Given the
 *   damaged array nums, return [the value that occurs twice, the missing value].
 *
 * EXAMPLES
 *   nums = [1,2,2,4]  ->  [2,3]    2 is doubled, 3 is gone
 *   nums = [1,1]      ->  [1,2]
 *
 * CONSTRAINTS
 *   2 <= nums.length <= 10^4,   1 <= nums[i] <= 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Arrays/LC645SetMismatch.java
 * </pre>
 */
public class LC645SetMismatch {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     */
    public static int[] findErrorNumsA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] findErrorNumsB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 12 Cyclic sort and index-as-address (this chapter)
     */
    public static int[] findErrorNumsC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC645SetMismatch::findErrorNumsA);
        runAll("B", LC645SetMismatch::findErrorNumsB);
        runAll("C", LC645SetMismatch::findErrorNumsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 645. Set Mismatch)
    // https://leetcode.com/problems/set-mismatch/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (value counts).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
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
        check(v + " example 1", new int[]{2, 3}, () -> s.solve(new int[]{1, 2, 2, 4}));
        check(v + " example 2", new int[]{1, 2}, () -> s.solve(new int[]{1, 1}));
        // edge: the smallest input, the repeated value is the larger one
        check(v + " two elements", new int[]{2, 1}, () -> s.solve(new int[]{2, 2}));
        // edge: the missing number is 1
        check(v + " missing one", new int[]{3, 1}, () -> s.solve(new int[]{3, 2, 3}));
        // edge: the missing number is n
        check(v + " missing n", new int[]{2, 10},
                () -> s.solve(new int[]{1, 5, 3, 2, 2, 7, 6, 4, 8, 9}));
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

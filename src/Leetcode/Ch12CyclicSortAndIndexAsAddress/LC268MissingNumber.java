package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 268. Missing Number   [Easy]
 * https://leetcode.com/problems/missing-number/
 * Topics: Array, Hash Table, Math, Binary Search, Bit Manipulation, Sorting
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #4
 *       also: ch. 5 Bit manipulation as a set encoding (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds n distinct numbers taken from the range [0, n], so exactly one number of
 *   that range is absent. Return the absent number.
 *
 * EXAMPLES
 *   nums = [3,0,1]              ->  2    n = 3, the range is 0..3 and 2 is absent
 *   nums = [0,1]                ->  2    n = 2, the range is 0..2
 *   nums = [9,6,4,2,3,5,7,0,1]  ->  8
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 10^4,   0 <= nums[i] <= n,   all numbers of nums are unique
 *   Follow up: O(1) extra space and O(n) runtime.
 * </pre>
 */
public class LC268MissingNumber {

    /**
     * Variant A - brute force: sort, then find the first index whose value differs from it
     * <br>Target: time O(n log n), space O(1) (sorting in place)
     */
    public static int missingNumberSort(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - arithmetic: the sum of 0..n minus the sum of the array
     * <br>Target: time O(n), space O(1)
     */
    public static int missingNumberSum(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: cyclic sort into slots 0..n-1, skipping the value n
     * <br>Target: time O(n), space O(1)
     */
    public static int missingNumber(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC268MissingNumber::missingNumberSort);
        runAll("B", LC268MissingNumber::missingNumberSum);
        runAll("C", LC268MissingNumber::missingNumber);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 268. Missing Number)
    // https://leetcode.com/problems/missing-number/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent reference (set difference).
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
        check(v + " example 1", 2, () -> s.solve(new int[]{3, 0, 1}));
        check(v + " example 2", 2, () -> s.solve(new int[]{0, 1}));
        check(v + " example 3", 8, () -> s.solve(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        // edge: the smallest input, the missing number is 1
        check(v + " only zero", 1, () -> s.solve(new int[]{0}));
        // edge: the smallest input, the missing number is 0
        check(v + " only one", 0, () -> s.solve(new int[]{1}));
        // edge: the largest value n is present, the gap is in the middle
        check(v + " n present", 3, () -> s.solve(new int[]{4, 2, 0, 1}));
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

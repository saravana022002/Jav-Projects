package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1539. Kth Missing Positive Number   [Easy]
 * https://leetcode.com/problems/kth-missing-positive-number/
 * Topics: Array, Binary Search
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #7
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   arr holds positive integers in strictly increasing order. Imagine the list of all
 *   positive integers that do NOT occur in arr, in increasing order. Return its k-th
 *   element (k counts from 1).
 *
 * EXAMPLES
 *   arr = [2,3,4,7,11], k = 5  ->  9    missing: 1,5,6,8,9,10,12,... - the 5th one is 9
 *   arr = [1,2,3,4],    k = 2  ->  6    missing: 5,6,7,... - the 2nd one is 6
 *
 * CONSTRAINTS
 *   1 <= arr.length <= 1000,   1 <= arr[i] <= 1000,   1 <= k <= 1000,
 *   arr is strictly increasing
 *   Follow up: can you do better than O(n)?
 * </pre>
 */
public class LC1539KthMissingPositiveNumber {

    /**
     * Variant A - count up from 1, skipping the values found in a HashSet, until k gaps are seen
     * <br>Target: time O(n + k), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     */
    public static int findKthPositiveSet(int[] arr, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - one pass over arr: every element not beyond the answer pushes it one further
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int findKthPositiveLinear(int[] arr, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: binary search on how many numbers are missing so far
     * <br>Target: time O(log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array
     *     + ch. 12 Cyclic sort and index-as-address (this chapter)
     */
    public static int findKthPositive(int[] arr, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1539KthMissingPositiveNumber::findKthPositiveSet);
        runAll("B", LC1539KthMissingPositiveNumber::findKthPositiveLinear);
        runAll("C", LC1539KthMissingPositiveNumber::findKthPositive);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1539. Kth Missing Positive Number)
    // https://leetcode.com/problems/kth-missing-positive-number/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (counting up from 1).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] arr, int k);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 9, () -> s.solve(new int[]{2, 3, 4, 7, 11}, 5));
        check(v + " example 2", 6, () -> s.solve(new int[]{1, 2, 3, 4}, 2));
        // edge: the answer lies before the first element
        check(v + " before first", 3, () -> s.solve(new int[]{5, 6, 7}, 3));
        // edge: the answer lies after the last element
        check(v + " after last", 2, () -> s.solve(new int[]{1}, 1));
        // edge: one big gap in front, then the answer just past the only element
        check(v + " big gap", 1001, () -> s.solve(new int[]{1000}, 1000));
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

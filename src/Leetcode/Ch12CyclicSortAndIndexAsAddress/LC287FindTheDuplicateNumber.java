package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 287. Find the Duplicate Number   [Medium]
 * https://leetcode.com/problems/find-the-duplicate-number/
 * Topics: Array, Two Pointers, Binary Search, Bit Manipulation, Pigeonhole Principle,
 *         Floyd's Cycle Finding Algorithm
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #5
 *       also: ch. 32 Fast and slow pointers (#11)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums has n + 1 integers, each between 1 and n. Exactly one value is repeated (it may
 *   occur more than twice); return it. You must not modify nums and may use only constant
 *   extra space.
 *
 * EXAMPLES
 *   nums = [1,3,4,2,2]  ->  2
 *   nums = [3,1,3,4,2]  ->  3
 *   nums = [3,3,3,3,3]  ->  3
 *
 * CONSTRAINTS
 *   1 <= n <= 10^5,   nums.length == n + 1,   1 <= nums[i] <= n,
 *   every integer appears once except one that appears two or more times
 *   Follow up: prove that a duplicate must exist; solve it in linear runtime.
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/TwoPointers/LC287FindTheDuplicateNumber.java
 * </pre>
 */
public class LC287FindTheDuplicateNumber {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int findDuplicateA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(1)
     * <br>Complete first: ch. 11 Binary search on the answer
     */
    public static int findDuplicateB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 12 Cyclic sort and index-as-address (this chapter)
     *     + ch. 32 Fast and slow pointers (later chapter; needs ch. 31 first)
     */
    public static int findDuplicateC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC287FindTheDuplicateNumber::findDuplicateA);
        runAll("B", LC287FindTheDuplicateNumber::findDuplicateB);
        runAll("C", LC287FindTheDuplicateNumber::findDuplicateC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 287. Find the Duplicate Number)
    // https://leetcode.com/problems/find-the-duplicate-number/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent reference (value counts).
    // The statement forbids changing nums, so every case also fails if the input array was
    // modified by the call.
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
        check(v + " example 1", 2, () -> guarded(s, new int[]{1, 3, 4, 2, 2}));
        check(v + " example 2", 3, () -> guarded(s, new int[]{3, 1, 3, 4, 2}));
        check(v + " example 3", 3, () -> guarded(s, new int[]{3, 3, 3, 3, 3}));
        // edge: the smallest input
        check(v + " two elements", 1, () -> guarded(s, new int[]{1, 1}));
        // edge: the repeated value occurs three times
        check(v + " three copies", 9, () -> guarded(s, new int[]{2, 5, 9, 6, 9, 3, 8, 9, 7, 1}));
        // edge: the repeated value is the largest possible value
        check(v + " large value", 4, () -> guarded(s, new int[]{1, 4, 4, 2, 4}));
    }

    // calls the solver and reports a modified input instead of the answer
    private static Object guarded(Solver s, int[] nums) {
        int[] before = nums.clone();
        int answer = s.solve(nums);
        return Arrays.equals(before, nums) ? (Object) answer : "modified the input array";
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

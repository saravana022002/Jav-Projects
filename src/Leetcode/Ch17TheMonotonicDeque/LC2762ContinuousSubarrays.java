package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2762. Continuous Subarrays   [Medium]
 * https://leetcode.com/problems/continuous-subarrays/
 * Topics: Array, Queue, Sliding Window, Heap (Priority Queue), Ordered Set, Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #5
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Call a subarray of nums "continuous" when every two of its elements differ by at most 2
 *   (so its maximum minus its minimum is at most 2). Count how many subarrays of nums are
 *   continuous. Subarrays are contiguous and non-empty; equal ones at different positions
 *   count separately. The count can exceed the range of int, so the result type is long.
 *
 * EXAMPLES
 *   nums = [5,4,2,4]  ->  8    4 of size 1, [5,4] [4,2] [2,4] of size 2, [4,2,4] of size 3
 *   nums = [1,2,3]    ->  6    all 3 + 2 + 1 subarrays qualify
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^9
 * </pre>
 */
public class LC2762ContinuousSubarrays {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static long continuousSubarraysA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 9 Sliding window, chasing
     *     + ch. 41 Ordered maps in practice (later chapter)
     */
    public static long continuousSubarraysB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 9 Sliding window, chasing
     *     + ch. 17 The monotonic deque (this chapter)
     */
    public static long continuousSubarraysC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2762ContinuousSubarrays::continuousSubarraysA, false);
        runAll("B", LC2762ContinuousSubarrays::continuousSubarraysB, true);
        runAll("C", LC2762ContinuousSubarrays::continuousSubarraysC, true);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2762. Continuous Subarrays)
    // https://leetcode.com/problems/continuous-subarrays/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose answers were checked against a brute-force count.
    // One extra case, 100000 equal values (answer 5000050000, too big for an int), is run for
    // B and C only: the brute force of Variant A would need five billion steps for it.
    //   Expected result: 0/14 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        long solve(int[] nums);
    }

    private static void runAll(String v, Solver s, boolean withBigCase) {
        // official examples
        check(v + " example 1", 8L, () -> s.solve(new int[]{5, 4, 2, 4}));
        check(v + " example 2", 6L, () -> s.solve(new int[]{1, 2, 3}));
        // edge: a single element
        check(v + " single element", 1L, () -> s.solve(new int[]{7}));
        // edge: the 5 fits with the 3s but not with the 1s in front of them
        check(v + " mixed run", 25L, () -> s.solve(new int[]{1, 1, 1, 3, 3, 3, 5}));
        // edge: n * (n + 1) / 2 does not fit in an int
        if (withBigCase) {
            int[] equal = new int[100000];
            Arrays.fill(equal, 2);
            check(v + " big, all equal", 5000050000L, () -> s.solve(equal));
        }
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

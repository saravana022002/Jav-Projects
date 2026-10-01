package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 719. Find K-th Smallest Pair Distance   [Hard]
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 * Topics: Array, Two Pointers, Binary Search, Sorting
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Two numbers a and b are |a - b| apart. Given nums and k, take every pair of positions
 *   i < j, measure how far apart nums[i] and nums[j] are, and return the k-th smallest of
 *   all those distances (equal distances are counted one by one).
 *
 * EXAMPLES
 *   nums = [1,3,1], k = 1  ->  0    pair distances: 2, 0, 2 -> the smallest is 0
 *   nums = [1,1,1], k = 2  ->  0    all three pair distances are 0
 *   nums = [1,6,1], k = 3  ->  5    pair distances: 5, 0, 5 -> sorted 0, 5, 5
 *
 * CONSTRAINTS
 *   n == nums.length,   2 <= n <= 10^4,   0 <= nums[i] <= 10^6,   1 <= k <= n * (n - 1) / 2
 * </pre>
 */
public class LC719FindKThSmallestPairDistance {

    /**
     * Variant A
     * <br>Target: time O(n^2 log n), space O(n^2)
     */
    public static int smallestDistancePairA(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n^2 + M), space O(M)   (M = largest value in nums)
     */
    public static int smallestDistancePairB(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n + n log M), space O(1) extra
     */
    public static int smallestDistancePairC(int[] nums, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC719FindKThSmallestPairDistance::smallestDistancePairA);
        runAll("B", LC719FindKThSmallestPairDistance::smallestDistancePairB);
        runAll("C", LC719FindKThSmallestPairDistance::smallestDistancePairC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 719. Find K-th Smallest Pair Distance)
    // https://leetcode.com/problems/find-k-th-smallest-pair-distance/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent reference (all pair distances,
    // sorted).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] nums, int k);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 0, () -> s.solve(new int[]{1, 3, 1}, 1));
        check(v + " example 2", 0, () -> s.solve(new int[]{1, 1, 1}, 2));
        check(v + " example 3", 5, () -> s.solve(new int[]{1, 6, 1}, 3));
        // edge: two numbers, a single pair, the biggest possible distance
        check(v + " one pair", 1000000, () -> s.solve(new int[]{0, 1000000}, 1));
        // edge: k points at the very last (largest) distance
        check(v + " last pair", 7, () -> s.solve(new int[]{1, 2, 4, 8}, 6));
        // edge: several zero distances, k is the first non-zero one
        check(v + " zeros first", 4, () -> s.solve(new int[]{5, 5, 5, 9}, 4));
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

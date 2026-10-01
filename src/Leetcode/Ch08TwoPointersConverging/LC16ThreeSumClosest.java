package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 16. 3Sum Closest   [Medium]
 * https://leetcode.com/problems/3sum-closest/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #8
 *       also: ch. 6 Sorting as a preprocessing step (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Pick three numbers from three different positions of nums so that their sum is as close
 *   as possible to target (smallest absolute difference). Return that sum - not the numbers.
 *   Every input has exactly one closest sum.
 *
 * EXAMPLES
 *   nums = [-1,2,1,-4],  target = 1  ->  2     -1 + 2 + 1 = 2
 *   nums = [0,0,0],      target = 1  ->  0     0 + 0 + 0 = 0
 *
 * CONSTRAINTS
 *   3 <= nums.length <= 500,   -1000 <= nums[i] <= 1000,   -10^4 <= target <= 10^4
 * </pre>
 */
public class LC16ThreeSumClosest {

    /**
     * Variant A - brute force: try every triple of positions, keep the sum nearest to target
     * <br>Target: time O(n^3), space O(1)
     */
    public static int threeSumClosestBruteForce(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - sort, fix two numbers, binary-search the third
     * <br>Target: time O(n^2 log n), space O(1)
     */
    public static int threeSumClosestBinarySearch(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sort, fix one number, two converging pointers
     * <br>Target: time O(n^2), space O(1)
     */
    public static int threeSumClosest(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC16ThreeSumClosest::threeSumClosestBruteForce);
        runAll("B", LC16ThreeSumClosest::threeSumClosestBinarySearch);
        runAll("C", LC16ThreeSumClosest::threeSumClosest);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 16. 3Sum Closest)
    // https://leetcode.com/problems/3sum-closest/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a try-every-triple reference (every case has a
    // single closest sum, as the problem promises). Every case passes a fresh array, so a
    // variant that sorts its input in place is fine.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums, int target);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[]{-1, 2, 1, -4}, 1));
        check(v + " example 2", 0, () -> s.solve(new int[]{0, 0, 0}, 1));
        // edge: a triple hits the target exactly
        check(v + " exact hit", 3, () -> s.solve(new int[]{1, 1, 1, 0}, 3));
        // edge: target far above every reachable sum
        check(v + " target too large", 9, () -> s.solve(new int[]{1, 2, 3, 4}, 100));
        // edge: negative target far below every reachable sum, all numbers negative
        check(v + " target too small", -12, () -> s.solve(new int[]{-5, -4, -3, -2, -1}, -20));
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

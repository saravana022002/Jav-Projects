package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 16. 3Sum Closest   [Medium]
 * https://leetcode.com/problems/3sum-closest/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #7
 *       also: ch. 8 Two pointers, converging (#8)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Choose three numbers from three different positions of nums so that their sum is as
 *   close as possible to target. Return that sum (not the numbers). You may assume every
 *   input has exactly one closest sum.
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
     * Variant A
     * <br>Target: time O(n^3), space O(1)
     */
    public static int threeSumClosestA(int[] nums, int target) {
        // TODO: write your solution
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sums = nums[i] + nums[j] + nums[k];
                    int factor = sums > target ? sums - target : target - sums;
                    if(factor < min){
                        min = factor;
                        ans = sums;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Variant B
     * <br>Target: time O(n^2 log n), space O(1)
     */
    public static int threeSumClosestB(int[] nums, int target) {
        // TODO: write your solution
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        int ans = 0;
        int sums = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int need = target - (nums[i] + nums[j]);
                int pos = Arrays.binarySearch(nums, j + 1, n, need);
                if(pos >= 0) return target;
                pos = - pos - 1;
                if(pos - 1 >= j + 1) {
                    sums = nums[i] + nums[j] + nums[pos - 1];
                    int low = Math.abs(sums - target);
                    if (low < min) {
                        min = low;
                        ans = sums;
                    }
                }
                if(pos < nums.length) {
                    sums = nums[i] + nums[j] + nums[pos];
                    int high = Math.abs(sums - target);
                    if (high < min) {
                        min = high;
                        ans = sums;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Variant C
     * <br>Target: time O(n^2), space O(1)
     */
    public static int threeSumClosestC(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods

        int[] nums = {-1, 2, 1, -4};
        threeSumClosestB(nums, 1);
//        runAll("A", LC16ThreeSumClosest::threeSumClosestA);
//        runAll("B", LC16ThreeSumClosest::threeSumClosestB);
//        runAll("C", LC16ThreeSumClosest::threeSumClosestC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 16. 3Sum Closest)
    // https://leetcode.com/problems/3sum-closest/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force script (the closest sum is unique
    // in every case). Every call gets a fresh array, so sorting the input in place is fine.
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
        // edge: a triple hits the target exactly (1+3+5 and 2+3+4)
        check(v + " exact match", 9, () -> s.solve(new int[]{1, 2, 3, 4, 5}, 9));
        // edge: target far above every possible sum, the biggest triple wins
        check(v + " target too high", 1, () -> s.solve(new int[]{-5, -2, 0, 3}, 100));
        // edge: target far below every possible sum, the smallest triple wins
        check(v + " target too low", -7, () -> s.solve(new int[]{-5, -2, 0, 3}, -100));
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

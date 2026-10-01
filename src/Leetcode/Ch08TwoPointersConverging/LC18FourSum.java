package Leetcode.Ch08TwoPointersConverging;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 18. 4Sum   [Medium]
 * https://leetcode.com/problems/4sum/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #9
 *       also: ch. 60 Meet in the middle (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return every quadruplet of numbers from nums, taken from four different positions, that
 *   adds up to target. The answer must not contain the same quadruplet twice (two
 *   quadruplets with the same numbers are the same, whatever their positions). The order of
 *   the quadruplets and of the numbers inside them does not matter.
 *
 * EXAMPLES
 *   nums = [1,0,-1,0,-2,2],  target = 0  ->  [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *   nums = [2,2,2,2,2],      target = 8  ->  [[2,2,2,2]]
 *   (any order is accepted)
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 200,   -10^9 <= nums[i] <= 10^9,   -10^9 <= target <= 10^9
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/TwoPointers/LC18FourSum.java
 * </pre>
 */
public class LC18FourSum {

    /**
     * Variant A - brute force: four nested loops, never reporting a quadruplet twice
     * <br>Target: time O(n^4), space O(1) besides the result
     */
    public static List<List<Integer>> fourSumBruteForce(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - sort, fix three numbers, binary-search the fourth
     * <br>Target: time O(n^3 log n), space O(1) besides the result
     */
    public static List<List<Integer>> fourSumBinarySearch(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sort, fix two numbers, two converging pointers
     * <br>Target: time O(n^3), space O(1) besides the result
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC18FourSum::fourSumBruteForce);
        runAll("B", LC18FourSum::fourSumBinarySearch);
        runAll("C", LC18FourSum::fourSum);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 18. 4Sum)
    // https://leetcode.com/problems/4sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a try-every-combination reference (computed with
    // 64-bit sums). The order of the quadruplets and of the numbers inside a quadruplet does not
    // matter, so both levels are sorted before comparing (a quadruplet returned twice is still a
    // failure). The inputs are kept short because the brute-force variant is quartic.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<List<Integer>> solve(int[] nums, int target);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1",
                lists(new int[]{-2, -1, 1, 2}, new int[]{-2, 0, 0, 2}, new int[]{-1, 0, 0, 1}),
                () -> normalised(s.solve(new int[]{1, 0, -1, 0, -2, 2}, 0)));
        check(v + " example 2", lists(new int[]{2, 2, 2, 2}),
                () -> normalised(s.solve(new int[]{2, 2, 2, 2, 2}, 8)));
        // edge: the real sum is 4 * 10^9, which wraps around to the target in 32-bit arithmetic
        check(v + " int overflow", lists(),
                () -> normalised(s.solve(
                        new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296)));
        // edge: fewer than four numbers
        check(v + " too few numbers", lists(), () -> normalised(s.solve(new int[]{1, 2, 3}, 6)));
        // edge: negatives and repeated numbers, two different quadruplets
        check(v + " repeated numbers", lists(new int[]{-2, -1, 1, 2}, new int[]{-1, -1, 1, 1}),
                () -> normalised(s.solve(new int[]{-2, -1, -1, 1, 1, 2, 2}, 0)));
    }

    // builds a list of quadruplets from int arrays
    private static List<List<Integer>> lists(int[]... rows) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] row : rows) {
            List<Integer> one = new ArrayList<>();
            for (int x : row) one.add(x);
            out.add(one);
        }
        return out;
    }

    // sorts the numbers inside every quadruplet, then the quadruplets themselves
    private static List<List<Integer>> normalised(List<List<Integer>> quadruplets) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> q : quadruplets) {
            List<Integer> copy = new ArrayList<>(q);
            Collections.sort(copy);
            out.add(copy);
        }
        out.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                int c = Integer.compare(a.get(i), b.get(i));
                if (c != 0) return c;
            }
            return Integer.compare(a.size(), b.size());
        });
        return out;
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

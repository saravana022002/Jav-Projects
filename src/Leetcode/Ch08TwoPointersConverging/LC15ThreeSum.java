package Leetcode.Ch08TwoPointersConverging;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 15. 3Sum   [Medium]
 * https://leetcode.com/problems/3sum/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #7
 *       also: ch. 6 Sorting as a preprocessing step (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return every triplet of numbers from nums, taken from three different positions, that
 *   adds up to 0. The answer must not contain the same triplet twice (two triplets with the
 *   same numbers are the same triplet, whatever their positions). The order of the triplets
 *   and of the numbers inside a triplet does not matter.
 *
 * EXAMPLES
 *   nums = [-1,0,1,2,-1,-4]  ->  [[-1,-1,2],[-1,0,1]]     (any order is accepted)
 *   nums = [0,1,1]           ->  []                       the only triplet sums to 2
 *   nums = [0,0,0]           ->  [[0,0,0]]
 *
 * CONSTRAINTS
 *   3 <= nums.length <= 3000,   -10^5 <= nums[i] <= 10^5
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/TwoPointers/LC15ThreeSum.java
 * </pre>
 */
public class LC15ThreeSum {

    /**
     * Variant A
     * <br>Target: time O(n^3), space O(1) besides the result
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static List<List<Integer>> threeSumA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     *     + ch. 6 Sorting as a preprocessing step
     */
    public static List<List<Integer>> threeSumB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n^2), space O(1) besides the result
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 8 Two pointers, converging (this chapter)
     */
    public static List<List<Integer>> threeSumC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC15ThreeSum::threeSumA);
        runAll("B", LC15ThreeSum::threeSumB);
        runAll("C", LC15ThreeSum::threeSumC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 15. 3Sum)
    // https://leetcode.com/problems/3sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference. The order of the
    // triplets and of the numbers inside a triplet does not matter, so both levels are sorted
    // before comparing (a triplet returned twice is still a failure). The inputs are kept
    // short because the brute-force variant is cubic.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<List<Integer>> solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", lists(new int[]{-1, -1, 2}, new int[]{-1, 0, 1}),
                () -> normalised(s.solve(new int[]{-1, 0, 1, 2, -1, -4})));
        check(v + " example 2", lists(), () -> normalised(s.solve(new int[]{0, 1, 1})));
        check(v + " example 3", lists(new int[]{0, 0, 0}),
                () -> normalised(s.solve(new int[]{0, 0, 0})));
        // edge: many zeros, the single triplet must be reported once
        check(v + " many zeros", lists(new int[]{0, 0, 0}),
                () -> normalised(s.solve(new int[]{0, 0, 0, 0})));
        // edge: repeated numbers on both sides of the answer
        check(v + " repeated pair", lists(new int[]{-2, 0, 2}),
                () -> normalised(s.solve(new int[]{-2, 0, 0, 2, 2})));
        // edge: several triplets and plenty of repeated numbers
        check(v + " many triplets",
                lists(new int[]{-4, -2, 6}, new int[]{-4, 0, 4}, new int[]{-4, 1, 3},
                        new int[]{-4, 2, 2}, new int[]{-2, -2, 4}, new int[]{-2, 0, 2}),
                () -> normalised(s.solve(
                        new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6})));
    }

    // builds a list of triplets from int arrays
    private static List<List<Integer>> lists(int[]... rows) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] row : rows) {
            List<Integer> one = new ArrayList<>();
            for (int x : row) one.add(x);
            out.add(one);
        }
        return out;
    }

    // sorts the numbers inside every triplet, then the triplets themselves
    private static List<List<Integer>> normalised(List<List<Integer>> triplets) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> t : triplets) {
            List<Integer> copy = new ArrayList<>(t);
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

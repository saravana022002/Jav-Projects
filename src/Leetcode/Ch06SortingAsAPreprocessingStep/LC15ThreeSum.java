package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 15. 3Sum   [Medium]
 * https://leetcode.com/problems/3sum/
 * Topics: Array, Two Pointers, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #6
 *       also: ch. 8 Two pointers, converging (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Find all distinct triplets of numbers in nums, taken from three different positions,
 *   whose sum is 0. The result must not contain the same triplet twice. The order of the
 *   triplets and the order of the numbers inside a triplet do not matter.
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
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC15ThreeSum {

    /**
     * Variant A - brute force: try every triple of positions, keep each distinct triple once
     * <br>Target: time O(n^3), space O(1) besides the result
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static List<List<Integer>> threeSumBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - sort, fix two numbers, binary-search the sorted rest for the third
     * <br>Target: time O(n^2 log n), space O(1) besides the result and the sort
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     *     + ch. 10 Binary search on a sorted array (later chapter)
     */
    public static List<List<Integer>> threeSumBinarySearch(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sort, fix the first number, two pointers do the rest
     * <br>Target: time O(n^2), space O(1) besides the result and the sort
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     *     + ch. 8 Two pointers, converging (later chapter)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC15ThreeSum::threeSumBruteForce);
        runAll("B", LC15ThreeSum::threeSumBinarySearch);
        runAll("C", LC15ThreeSum::threeSum);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 15. 3Sum)
    // https://leetcode.com/problems/3sum/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose triplets were listed by a brute-force script. The answer
    // may come back in any order, so both sides are normalised first: every triplet is sorted
    // and the list of triplets is sorted. Every call gets a fresh array.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<List<Integer>> solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", triples(new int[][]{{-1, -1, 2}, {-1, 0, 1}}),
                () -> normalised(s.solve(new int[]{-1, 0, 1, 2, -1, -4})));
        check(v + " example 2", triples(new int[][]{}),
                () -> normalised(s.solve(new int[]{0, 1, 1})));
        check(v + " example 3", triples(new int[][]{{0, 0, 0}}),
                () -> normalised(s.solve(new int[]{0, 0, 0})));
        // edge: four zeros give the triplet [0,0,0] only once
        check(v + " many zeros", triples(new int[][]{{0, 0, 0}}),
                () -> normalised(s.solve(new int[]{0, 0, 0, 0})));
        // edge: repeated values on both sides still give a single triplet
        check(v + " repeated values", triples(new int[][]{{-2, 0, 2}}),
                () -> normalised(s.solve(new int[]{-2, 0, 0, 2, 2})));
        // edge: many overlapping triplets with repeated values
        check(v + " many triplets",
                triples(new int[][]{{-4, -2, 6}, {-4, 0, 4}, {-4, 1, 3}, {-4, 2, 2}, {-2, -2, 4},
                        {-2, 0, 2}}),
                () -> normalised(s.solve(
                        new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6})));
    }

    // the expected triplets as a list of lists (each row must already be in ascending order)
    private static List<List<Integer>> triples(int[][] rows) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] row : rows) {
            List<Integer> t = new ArrayList<>();
            for (int x : row) t.add(x);
            out.add(t);
        }
        out.sort(LC15ThreeSum::compareLists);
        return out;
    }

    // sorts every triplet and then the list of triplets (null stays null so check() fails it)
    private static List<List<Integer>> normalised(List<List<Integer>> result) {
        if (result == null) return null;
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> t : result) {
            List<Integer> copy = new ArrayList<>(t);
            copy.sort(null);
            out.add(copy);
        }
        out.sort(LC15ThreeSum::compareLists);
        return out;
    }

    private static int compareLists(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            int c = Integer.compare(a.get(i), b.get(i));
            if (c != 0) return c;
        }
        return Integer.compare(a.size(), b.size());
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

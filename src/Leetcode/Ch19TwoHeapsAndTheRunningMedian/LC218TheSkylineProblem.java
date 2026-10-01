package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 218. The Skyline Problem   [Hard]
 * https://leetcode.com/problems/the-skyline-problem/
 * Topics: Array, Divide and Conquer, Binary Indexed Tree, Segment Tree, Sweep Line, Sorting,
 *         Heap (Priority Queue), Ordered Set
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #5
 *       also: ch. 20 Intervals and sweep line (#11), ch. 41 Ordered maps in practice (#12),
 *             ch. 59 Divide and conquer (#12)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Each building is a rectangle standing on flat ground: buildings[i] = [left, right, height].
 *   Seen from far away, all buildings together form a silhouette. Return its outline (the
 *   skyline) as a list of key points [x, y] sorted by x. A key point is the left end of a
 *   horizontal segment of the outline, so y is the height from that x onwards; the last point
 *   has y = 0 and marks where the rightmost building ends. Bare ground between buildings is
 *   part of the outline (it drops to height 0 there). Two consecutive key points must never
 *   have the same height: such segments have to be merged into one.
 *
 * EXAMPLES
 *   buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 *       ->  [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 *   buildings = [[0,2,3],[2,5,3]]  ->  [[0,3],[5,0]]
 *   (a picture of the first example is on the LeetCode page)
 *
 * CONSTRAINTS
 *   1 <= buildings.length <= 10^4,   0 <= left_i < right_i <= 2^31 - 1,
 *   1 <= height_i <= 2^31 - 1,   buildings is sorted by left_i in non-decreasing order
 * </pre>
 */
public class LC218TheSkylineProblem {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     */
    public static List<List<Integer>> getSkylineA(int[][] buildings) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     */
    public static List<List<Integer>> getSkylineB(int[][] buildings) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     */
    public static List<List<Integer>> getSkylineC(int[][] buildings) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC218TheSkylineProblem::getSkylineA);
        runAll("B", LC218TheSkylineProblem::getSkylineB);
        runAll("C", LC218TheSkylineProblem::getSkylineC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 218. The Skyline Problem)
    // https://leetcode.com/problems/the-skyline-problem/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference (tallest building at
    // every edge coordinate).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<List<Integer>> solve(int[][] buildings);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1",
                skyline(new int[]{2, 10}, new int[]{3, 15}, new int[]{7, 12}, new int[]{12, 0},
                        new int[]{15, 10}, new int[]{20, 8}, new int[]{24, 0}),
                () -> s.solve(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10},
                        {19, 24, 8}}));
        check(v + " example 2", skyline(new int[]{0, 3}, new int[]{5, 0}),
                () -> s.solve(new int[][]{{0, 2, 3}, {2, 5, 3}}));
        // edge: a single building
        check(v + " single building", skyline(new int[]{7, 3}, new int[]{9, 0}),
                () -> s.solve(new int[][]{{7, 9, 3}}));
        // edge: shared left and right edges, a building hidden behind a taller one
        check(v + " shared edges and hidden building",
                skyline(new int[]{1, 8}, new int[]{4, 3}, new int[]{6, 1}, new int[]{9, 0}),
                () -> s.solve(new int[][]{{1, 6, 3}, {1, 4, 8}, {2, 4, 5}, {3, 6, 3}, {4, 9, 1}}));
        // edge: heights at the int limit, two touching buildings of equal height
        check(v + " int limits", skyline(new int[]{1, 2147483647}, new int[]{9, 0}),
                () -> s.solve(new int[][]{{1, 5, 2147483647}, {5, 9, 2147483647}}));
    }

    // builds the expected skyline from its key points
    private static List<List<Integer>> skyline(int[]... points) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] p : points) {
            List<Integer> point = new ArrayList<>();
            point.add(p[0]);
            point.add(p[1]);
            res.add(point);
        }
        return res;
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
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

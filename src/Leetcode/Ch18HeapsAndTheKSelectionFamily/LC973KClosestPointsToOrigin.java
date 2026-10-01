package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 973. K Closest Points to Origin   [Medium]
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * Topics: Array, Math, Divide and Conquer, Geometry, Sorting, Heap (Priority Queue),
 *         Quickselect, K-D Tree
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #4
 *       also: ch. 6 Sorting as a preprocessing step (#5), ch. 13 Quickselect and partition (#6),
 *             ch. 82 Geometry basics (#2),
 *             ch. 86 Randomisation as a defence against the adversary (#2)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   points[i] = [x_i, y_i] is a point on the X-Y plane. Return the k points that are closest
 *   to the origin (0, 0), measured by Euclidean distance. The points may be returned in any
 *   order; the set of k closest points is guaranteed to be unique.
 *
 * EXAMPLES
 *   points = [[1,3],[-2,2]],         k = 1  ->  [[-2,2]]        sqrt(8) < sqrt(10)
 *   points = [[3,3],[5,-1],[-2,4]],  k = 2  ->  [[3,3],[-2,4]]  [[-2,4],[3,3]] is accepted too
 *
 * CONSTRAINTS
 *   1 <= k <= points.length <= 10^4,   -10^4 <= x_i, y_i <= 10^4
 * </pre>
 */
public class LC973KClosestPointsToOrigin {

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(n) besides the answer
     */
    public static int[][] kClosestA(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n) expected (O(n^2) worst case), space O(1) extra besides the answer
     */
    public static int[][] kClosestB(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log k), space O(k)
     */
    public static int[][] kClosestC(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC973KClosestPointsToOrigin::kClosestA);
        runAll("B", LC973KClosestPointsToOrigin::kClosestB);
        runAll("C", LC973KClosestPointsToOrigin::kClosestC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 973. K Closest Points to Origin)
    // https://leetcode.com/problems/k-closest-points-to-origin/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected sets from a sort-by-distance reference; none has a tie
    // at the cut-off). The points may come back in any order, so the answer is normalised
    // (rows sorted by x, then y) before comparing; expected rows are given in that order.
    // The solutions may reorder the points they are given, so every call gets fresh arrays.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int[][] points, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[][]{{-2, 2}},
                () -> sorted(s.solve(pts(1, 3, -2, 2), 1)));
        check(v + " example 2", new int[][]{{-2, 4}, {3, 3}},
                () -> sorted(s.solve(pts(3, 3, 5, -1, -2, 4), 2)));
        // edge: k equals the number of points
        check(v + " k equals n", new int[][]{{-3, 1}, {0, -2}, {5, -5}},
                () -> sorted(s.solve(pts(5, -5, 0, -2, -3, 1), 3)));
        // edge: the origin itself next to points at the far corners of the allowed range
        check(v + " origin and corners", new int[][]{{0, -1}, {0, 0}},
                () -> sorted(s.solve(pts(-10000, 10000, 0, 0, 10000, -10000, 0, -1), 2)));
        // edge: a repeated point and two different points at the same distance
        check(v + " duplicates and ties", new int[][]{{3, 4}, {3, 4}, {4, 3}},
                () -> sorted(s.solve(pts(3, 4, 4, 3, 3, 4, 8, 8), 3)));
    }

    // builds a point list from x0, y0, x1, y1, ...
    private static int[][] pts(int... xy) {
        int[][] p = new int[xy.length / 2][];
        for (int i = 0; i < p.length; i++) {
            p[i] = new int[]{xy[2 * i], xy[2 * i + 1]};
        }
        return p;
    }

    // order-insensitive form of an answer: rows sorted by x, then by y
    private static int[][] sorted(int[][] a) {
        if (a == null) return null;
        int[][] copy = a.clone();
        Arrays.sort(copy, (p, q) -> p[0] != q[0] ? Integer.compare(p[0], q[0])
                : Integer.compare(p[1], q[1]));
        return copy;
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

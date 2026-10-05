package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 973. K Closest Points to Origin   [Medium]
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * Topics: Array, Math, Divide and Conquer, Geometry, Sorting, Heap (Priority Queue), Quickselect
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #5
 *       also: ch. 13 Quickselect and partition (#6), ch. 18 Heaps and the k-selection family (#4),
 *             ch. 82 Geometry basics (#2),
 *             ch. 86 Randomisation as a defence against the adversary (#2)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   points[i] = [x_i, y_i] is a point on the X-Y plane. Return the k points that are closest
 *   to the origin (0, 0), by straight-line (Euclidean) distance. The points may be returned
 *   in any order; the answer is guaranteed to be unique apart from its order.
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
     * Variant A - brute force: k rounds, each picking the closest point not taken yet
     * <br>Target: time O(n * k), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[][] kClosestBruteForce(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - max-heap of size k that holds the closest points seen so far
     * <br>Target: time O(n log k), space O(k)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (later chapter)
     */
    public static int[][] kClosestMaxHeap(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sort the points by squared distance, take the first k
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static int[][] kClosest(int[][] points, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC973KClosestPointsToOrigin::kClosestBruteForce);
        runAll("B", LC973KClosestPointsToOrigin::kClosestMaxHeap);
        runAll("C", LC973KClosestPointsToOrigin::kClosest);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 973. K Closest Points to Origin)
    // https://leetcode.com/problems/k-closest-points-to-origin/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose answers were computed by sorting all points by squared
    // distance (no tie at the k-th place, so the answer is unique). The answer may come back
    // in any order, so both sides are sorted (by x, then y) before comparing.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int[][] points, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[][]{{-2, 2}},
                () -> sorted(s.solve(new int[][]{{1, 3}, {-2, 2}}, 1)));
        check(v + " example 2", new int[][]{{-2, 4}, {3, 3}},
                () -> sorted(s.solve(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));
        // edge: k equals the number of points, everything comes back
        check(v + " k equals n", new int[][]{{-1, 0}, {0, 3}, {2, 2}},
                () -> sorted(s.solve(new int[][]{{2, 2}, {-1, 0}, {0, 3}}, 3)));
        // edge: the same point twice, both copies belong to the answer
        check(v + " duplicate points", new int[][]{{1, 1}, {1, 1}},
                () -> sorted(s.solve(new int[][]{{1, 1}, {1, 1}, {5, 5}}, 2)));
        // edge: the origin itself, and the far corners of the allowed range
        check(v + " origin and corners", new int[][]{{-5, 12}, {0, 0}, {3, 4}},
                () -> sorted(s.solve(new int[][]{{10000, -10000}, {-10000, 10000}, {3, 4},
                        {-5, 12}, {0, 0}}, 3)));
    }

    // copy of the points sorted by x, then y (null stays null so check() reports a FAIL)
    private static int[][] sorted(int[][] points) {
        if (points == null) return null;
        int[][] copy = new int[points.length][];
        for (int i = 0; i < points.length; i++) copy[i] = points[i].clone();
        Arrays.sort(copy, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0])
                : Integer.compare(a[1], b[1]));
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

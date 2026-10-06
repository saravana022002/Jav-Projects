package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2848. Points That Intersect With Cars   [Easy]
 * https://leetcode.com/problems/points-that-intersect-with-cars/
 * Topics: Array, Hash Table, Prefix Sum
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Cars are parked on a number line: nums[i] = [start, end] means the i-th car occupies every
 *   point from start to end (both included). Return how many integer points of the line are
 *   covered by at least one car.
 *
 * EXAMPLES
 *   nums = [[3,6],[1,5],[4,7]]  ->  7     points 1 to 7 are all covered
 *   nums = [[1,3],[5,8]]        ->  7     covered points: 1, 2, 3, 5, 6, 7, 8
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 100,   nums[i].length == 2,   1 <= start_i <= end_i <= 100
 * </pre>
 */
public class LC2848PointsThatIntersectWithCars {

    /**
     * Variant A
     * <br>Target: time O(n * R), space O(R)     (R = largest coordinate, here at most 100)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int numberOfPointsA(List<List<Integer>> nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static int numberOfPointsB(List<List<Integer>> nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n + R), space O(R)
     * <br>Complete first: ch. 2 Prefix sums and difference arrays
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static int numberOfPointsC(List<List<Integer>> nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2848PointsThatIntersectWithCars::numberOfPointsA);
        runAll("B", LC2848PointsThatIntersectWithCars::numberOfPointsB);
        runAll("C", LC2848PointsThatIntersectWithCars::numberOfPointsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2848. Points That Intersect With Cars)
    // https://leetcode.com/problems/points-that-intersect-with-cars/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a set-of-covered-points reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(List<List<Integer>> nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 7,
                () -> s.solve(cars(new int[]{3, 6}, new int[]{1, 5}, new int[]{4, 7})));
        check(v + " example 2", 7, () -> s.solve(cars(new int[]{1, 3}, new int[]{5, 8})));
        // edge: one car covering a single point
        check(v + " single point", 1, () -> s.solve(cars(new int[]{5, 5})));
        // edge: cars on neighbouring integer points leave no gap
        check(v + " adjacent cars", 4, () -> s.solve(cars(new int[]{1, 2}, new int[]{3, 4})));
        // edge: the whole line, with cars nested inside each other
        check(v + " whole line", 100,
                () -> s.solve(cars(new int[]{1, 100}, new int[]{20, 30}, new int[]{100, 100})));
    }

    // builds the input list of [start, end] pairs
    private static List<List<Integer>> cars(int[]... pairs) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] p : pairs) {
            List<Integer> car = new ArrayList<>();
            car.add(p[0]);
            car.add(p[1]);
            res.add(car);
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

package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1642. Furthest Building You Can Reach   [Medium]
 * https://leetcode.com/problems/furthest-building-you-can-reach/
 * Topics: Array, Greedy, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #10
 *       also: ch. 78 Huffman and the merge-cost family (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   heights[i] is the height of building i. You start on building 0 and walk right, from
 *   building i to building i + 1. If the next building is not taller, the step is free.
 *   If it is taller you must pay for the climb, either with one ladder (any height
 *   difference) or with heights[i + 1] - heights[i] bricks. You own `bricks` bricks and
 *   `ladders` ladders. Return the index of the furthest building you can reach.
 *
 * EXAMPLES
 *   heights = [4,2,7,6,9,14,12],        bricks = 5,  ladders = 1  ->  4
 *       2->7 costs 5 bricks, 6->9 needs the ladder, and 9->14 can no longer be paid
 *   heights = [4,12,2,7,3,18,20,3,19],  bricks = 10, ladders = 2  ->  7
 *   heights = [14,3,19,3],              bricks = 17, ladders = 0  ->  3
 *
 * CONSTRAINTS
 *   1 <= heights.length <= 10^5,   1 <= heights[i] <= 10^6,   0 <= bricks <= 10^9,
 *   0 <= ladders <= heights.length
 * </pre>
 */
public class LC1642FurthestBuildingYouCanReach {

    /**
     * Variant A - for each target building, sort the climbs so far; ladders take the biggest ones
     * <br>Target: time O(n^2 log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int furthestBuildingBruteForce(int[] heights, int bricks, int ladders) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - binary search on the target building, each test is the check of Variant A
     * <br>Target: time O(n log^2 n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 11 Binary search on the answer
     */
    public static int furthestBuildingBinarySearch(int[] heights, int bricks, int ladders) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: min-heap of the climbs that currently hold a ladder
     * <br>Target: time O(n log L), space O(L)     (L = ladders)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (this chapter)
     */
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1642FurthestBuildingYouCanReach::furthestBuildingBruteForce);
        runAll("B", LC1642FurthestBuildingYouCanReach::furthestBuildingBinarySearch);
        runAll("C", LC1642FurthestBuildingYouCanReach::furthestBuilding);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1642. Furthest Building You Can Reach)
    // https://leetcode.com/problems/furthest-building-you-can-reach/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were checked against an exhaustive search over
    // every way to spend the ladders. Every call gets a fresh array.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] heights, int bricks, int ladders);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4, () -> s.solve(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1));
        check(v + " example 2", 7, () -> s.solve(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
        check(v + " example 3", 3, () -> s.solve(new int[]{14, 3, 19, 3}, 17, 0));
        // edge: a single building, nothing to climb
        check(v + " single building", 0, () -> s.solve(new int[]{7}, 0, 0));
        // edge: only descents, so the last building is reached without any resources
        check(v + " only descents", 4, () -> s.solve(new int[]{5, 4, 3, 2, 1}, 0, 0));
        // edge: a small climb (2) comes first, a much bigger one (7) right after it
        check(v + " small climb then big climb", 4,
                () -> s.solve(new int[]{1, 3, 10, 11, 3}, 3, 1));
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

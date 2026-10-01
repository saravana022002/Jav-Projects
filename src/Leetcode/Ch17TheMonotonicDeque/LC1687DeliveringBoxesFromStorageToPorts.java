package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1687. Delivering Boxes from Storage to Ports   [Hard]
 * https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/
 * Topics: Array, Dynamic Programming, Segment Tree, Queue, Heap (Priority Queue), Prefix Sum,
 *         Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   One ship must deliver boxes[i] = [port_i, weight_i] in the given order. Each time it
 *   sets out from storage it takes the next few boxes of the queue: at most maxBoxes boxes
 *   and at most maxWeight total weight. It delivers them in order, sailing to each box's
 *   port (no trip when the box goes to the port where the ship already is), and then sails
 *   back to storage. Every sailing between storage and a port, or between two different
 *   ports, is one trip. Return the minimum total number of trips for all boxes.
 *
 * EXAMPLES
 *   boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3  ->  4
 *       one load: storage -> port 1 -> port 2 -> port 1 -> storage
 *   boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6  ->  6
 *       loads: box 1 | boxes 2-4 | box 5, each costs 2 trips
 *   boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7
 *       ->  6     loads: boxes 1-2 | boxes 3-4 | boxes 5-6, each costs 2 trips
 *
 * CONSTRAINTS
 *   1 <= boxes.length <= 10^5,   1 <= portsCount, maxBoxes, maxWeight <= 10^5,
 *   1 <= ports_i <= portsCount,   1 <= weights_i <= maxWeight
 * </pre>
 */
public class LC1687DeliveringBoxesFromStorageToPorts {

    /**
     * Variant A
     * <br>Target: time O(n * maxBoxes) (at most O(n^2)), space O(n)
     */
    public static int boxDeliveringA(int[][] boxes, int portsCount, int maxBoxes,
                                              int maxWeight) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     */
    public static int boxDeliveringB(int[][] boxes, int portsCount, int maxBoxes,
                                        int maxWeight) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     */
    public static int boxDeliveringC(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1687DeliveringBoxesFromStorageToPorts::boxDeliveringA);
        runAll("B", LC1687DeliveringBoxesFromStorageToPorts::boxDeliveringB);
        runAll("C", LC1687DeliveringBoxesFromStorageToPorts::boxDeliveringC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1687. Delivering Boxes from Storage to Ports)
    // https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were checked against an exhaustive search over
    // all ways to cut the boxes into loads.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] boxes, int portsCount, int maxBoxes, int maxWeight);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4, () -> s.solve(new int[][]{{1, 1}, {2, 1}, {1, 1}}, 2, 3, 3));
        check(v + " example 2", 6,
                () -> s.solve(new int[][]{{1, 2}, {3, 3}, {3, 1}, {3, 1}, {2, 4}}, 3, 3, 6));
        check(v + " example 3", 6,
                () -> s.solve(new int[][]{{1, 4}, {1, 2}, {2, 1}, {2, 1}, {3, 2}, {3, 4}},
                        3, 6, 7));
        // edge: a single box (one trip out, one trip back)
        check(v + " single box", 2, () -> s.solve(new int[][]{{1, 1}}, 1, 1, 1));
        // edge: one port only, so the box limit alone decides how many loads are needed
        check(v + " box limit decides", 6,
                () -> s.solve(new int[][]{{1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}}, 1, 2, 100));
        // edge: nine boxes, five ports, mixed weights (a known tricky case)
        check(v + " mixed nine boxes", 14,
                () -> s.solve(new int[][]{{2, 4}, {2, 5}, {3, 1}, {3, 2}, {3, 7}, {3, 1}, {4, 4},
                        {1, 3}, {5, 2}}, 5, 5, 7));
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

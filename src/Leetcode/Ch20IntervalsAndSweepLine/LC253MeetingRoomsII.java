package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 253. Meeting Rooms II   [Medium]
 * https://leetcode.com/problems/meeting-rooms-ii/
 * Topics: Array, Two Pointers, Greedy, Sorting, Heap (Priority Queue), Prefix Sum
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #8
 *       also: ch. 77 Scheduling and interval greedy (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   intervals[i] = [start, end] is a meeting that occupies a room from start until end. Return
 *   the minimum number of conference rooms needed to hold all the meetings. A meeting that
 *   ends at time t does not clash with one that starts at time t: they can share a room.
 *   NOTE: premium problem - statement reconstructed, confirm at the link.
 *
 * EXAMPLES
 *   intervals = [[0,30],[5,10],[15,20]]  ->  2     [0,30] overlaps both of the others
 *   intervals = [[7,10],[2,4]]           ->  1
 *
 * CONSTRAINTS
 *   1 <= intervals.length <= 10^4,   0 <= start_i < end_i <= 10^6
 * </pre>
 */
public class LC253MeetingRoomsII {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int minMeetingRoomsA(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 18 Heaps and the k-selection family
     */
    public static int minMeetingRoomsB(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 20 Intervals and sweep line (this chapter)
     */
    public static int minMeetingRoomsC(int[][] intervals) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC253MeetingRoomsII::minMeetingRoomsA);
        runAll("B", LC253MeetingRoomsII::minMeetingRoomsB);
        runAll("C", LC253MeetingRoomsII::minMeetingRoomsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 253. Meeting Rooms II)
    // https://leetcode.com/problems/meeting-rooms-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two well-known
    // examples (premium problem, taken from memory) plus edge cases checked against a
    // count-the-running-meetings reference, where a meeting occupies [start, end).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] intervals);
    }

    private static void runAll(String v, Solver s) {
        // examples
        check(v + " example 1", 2, () -> s.solve(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        check(v + " example 2", 1, () -> s.solve(new int[][]{{7, 10}, {2, 4}}));
        // edge: a meeting that ends exactly when the next one starts shares the room
        check(v + " back to back", 1, () -> s.solve(new int[][]{{0, 5}, {5, 10}}));
        // edge: three identical meetings need three rooms
        check(v + " identical meetings", 3, () -> s.solve(new int[][]{{3, 8}, {3, 8}, {3, 8}}));
        // edge: unsorted input, the busiest moment is not at the first start
        check(v + " late peak", 3,
                () -> s.solve(new int[][]{{20, 30}, {1, 10}, {25, 28}, {26, 27}, {2, 3}}));
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

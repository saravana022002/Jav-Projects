package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 732. My Calendar III   [Hard]
 * https://leetcode.com/problems/my-calendar-iii/
 * Topics: Binary Search, Design, Segment Tree, Prefix Sum, Ordered Set
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #10
 *       also: ch. 40 Segment trees (#8), ch. 41 Ordered maps in practice (#6)
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A k-booking is a moment of time that belongs to k events at once. Design a calendar where
 *   book(startTime, endTime) adds the event [startTime, endTime) (start included, end
 *   excluded) and returns the largest k such that a k-booking exists among all the events
 *   booked so far, the new one included.
 *
 * EXAMPLES
 *   MyCalendarThree
 *     book(10, 20) -> 1     book(50, 60) -> 1     book(10, 40) -> 2
 *     book(5, 15)  -> 3     book(5, 10)  -> 3     book(25, 55) -> 3
 *
 * CONSTRAINTS
 *   0 <= startTime < endTime <= 10^9,   at most 400 calls will be made to book
 * </pre>
 */
public class LC732MyCalendarIII {

    /**
     * Variant A
     * <br>Target: time O(n log n) per book, space O(n)     (n = events booked so far)
     */
    public static class MyCalendarThreeA {
        public MyCalendarThreeA() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int book(int startTime, int endTime) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B
     * <br>Target: time O(n) per book, space O(n)
     */
    public static class MyCalendarThreeB {
        public MyCalendarThreeB() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int book(int startTime, int endTime) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant C
     * <br>Target: time O(log C) per book, space O(n log C)     (C = 10^9, the range of times)
     */
    public static class MyCalendarThreeC {
        public MyCalendarThreeC() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int book(int startTime, int endTime) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", () -> {
            LC732MyCalendarIII.MyCalendarThreeA c =
                    new LC732MyCalendarIII.MyCalendarThreeA();
            return c::book;
        });
        runAll("B", () -> {
            LC732MyCalendarIII.MyCalendarThreeB c = new LC732MyCalendarIII.MyCalendarThreeB();
            return c::book;
        });
        runAll("C", () -> {
            LC732MyCalendarIII.MyCalendarThreeC c =
                    new LC732MyCalendarIII.MyCalendarThreeC();
            return c::book;
        });
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 732. My Calendar III)
    // https://leetcode.com/problems/my-calendar-iii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the official example
    // (the list of values returned by the six book calls) plus edge cases checked against a
    // recompute-from-scratch reference.
    //   Expected result: 0/12 passed (every case fails until you implement the classes)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // builds a fresh calendar and returns its book method
    interface Factory {
        BiFunction<Integer, Integer, Integer> create();
    }

    private static void runAll(String v, Factory f) {
        // official example
        check(v + " example 1", Arrays.asList(1, 1, 2, 3, 3, 3),
                () -> replay(f, new int[][]{{10, 20}, {50, 60}, {10, 40}, {5, 15}, {5, 10},
                        {25, 55}}));
        // edge: events that only touch (end of one = start of the next) do not overlap
        check(v + " touching events", Arrays.asList(1, 1, 2, 3),
                () -> replay(f, new int[][]{{1, 2}, {2, 3}, {2, 3}, {1, 3}}));
        // edge: the same event again and again
        check(v + " identical events", Arrays.asList(1, 2, 3, 4),
                () -> replay(f, new int[][]{{0, 10}, {0, 10}, {0, 10}, {0, 10}}));
        // edge: times at the limits of the constraints
        check(v + " huge times", Arrays.asList(1, 2, 2, 3),
                () -> replay(f, new int[][]{{0, 1000000000}, {999999999, 1000000000}, {0, 1},
                        {0, 1000000000}}));
    }

    private static List<Integer> replay(Factory f, int[][] books) {
        BiFunction<Integer, Integer, Integer> book = f.create();
        List<Integer> answers = new ArrayList<>();
        for (int[] b : books) answers.add(book.apply(b[0], b[1]));
        return answers;
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

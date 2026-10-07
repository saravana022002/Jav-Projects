package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1825. Finding MK Average   [Hard]
 * https://leetcode.com/problems/finding-mk-average/
 * Topics: Design, Queue, Heap (Priority Queue), Data Stream, Ordered Set, Treap
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Build a class around a stream of integers. The constructor gets two numbers m and k.
 *     addElement(num)         - appends num to the stream
 *     calculateMKAverage()    - returns the "MK average" of the stream so far:
 *                               * -1 if fewer than m numbers have arrived;
 *                               * otherwise take the last m numbers, throw away the k smallest
 *                                 and the k largest of them, and return the average of what is
 *                                 left (m - 2k numbers), rounded down to an integer.
 *
 * EXAMPLES
 *   MKAverage(3, 1)
 *     addElement(3), addElement(1), calculateMKAverage() -> -1     (only 2 numbers so far)
 *     addElement(10), calculateMKAverage() -> 3      last 3 = [3,1,10], middle part = [3]
 *     addElement(5), addElement(5), addElement(5), calculateMKAverage() -> 5
 *                                                    last 3 = [5,5,5], middle part = [5]
 *
 * CONSTRAINTS
 *   3 <= m <= 10^5,   1 < k*2 < m,   1 <= num <= 10^5,
 *   at most 10^5 calls will be made to addElement and calculateMKAverage
 * </pre>
 */
public class LC1825FindingMKAverage {

    /**
     * Variant A
     * <br>Target: time O(1) per addElement, O(m log m) per calculateMKAverage, space O(m)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static class MKAverageA {
        public MKAverageA(int m, int k) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void addElement(int num) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int calculateMKAverage() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B
     * <br>Target: time O(m) per addElement, O(m) per calculateMKAverage, space O(m)
     * <br>Complete first: ch. 9 Sliding window, chasing
     *     + ch. 10 Binary search on a sorted array
     */
    public static class MKAverageB {
        public MKAverageB(int m, int k) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void addElement(int num) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int calculateMKAverage() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant C
     * <br>Target: time O(log m) per addElement, O(1) per calculateMKAverage, space O(m)
     * <br>Complete first: ch. 19 Two heaps and the running median (this chapter)
     */
    public static class MKAverageC {
        public MKAverageC(int m, int k) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void addElement(int num) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int calculateMKAverage() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", (m, k) -> {
            LC1825FindingMKAverage.MKAverageA s =
                    new LC1825FindingMKAverage.MKAverageA(m, k);
            return new Stream() {
                public void add(int num) { s.addElement(num); }
                public int average() { return s.calculateMKAverage(); }
            };
        });
        runAll("B", (m, k) -> {
            LC1825FindingMKAverage.MKAverageB s =
                    new LC1825FindingMKAverage.MKAverageB(m, k);
            return new Stream() {
                public void add(int num) { s.addElement(num); }
                public int average() { return s.calculateMKAverage(); }
            };
        });
        runAll("C", (m, k) -> {
            LC1825FindingMKAverage.MKAverageC s = new LC1825FindingMKAverage.MKAverageC(m, k);
            return new Stream() {
                public void add(int num) { s.addElement(num); }
                public int average() { return s.calculateMKAverage(); }
            };
        });
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1825. Finding MK Average)
    // https://leetcode.com/problems/finding-mk-average/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the official example
    // plus edge cases checked against a sort-the-last-m reference. A case is a sequence like
    // "3 1 ? 10 ?": a number means addElement(number), "?" means calculateMKAverage(); the list
    // of returned averages is compared.
    //   Expected result: 0/12 passed (every case fails until you implement the classes)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // the two operations every variant offers
    interface Stream {
        void add(int num);

        int average();
    }

    interface Factory {
        Stream create(int m, int k);
    }

    private static void runAll(String v, Factory f) {
        // official example
        check(v + " example 1", Arrays.asList(-1, 3, 5),
                () -> replay(f, 3, 1, "3 1 ? 10 ? 5 5 5 ?"));
        // edge: m = 5, k = 1: the window slides, averages are rounded down
        check(v + " window slides, rounds down", Arrays.asList(-1, 4, 5, 4, 5, 5, 6, 5),
                () -> replay(f, 5, 1, "4 8 2 9 ? 1 ? 7 ? 3 ? 6 ? 100 ? 5 ? 5 ?"));
        // edge: m = 6, k = 2: two numbers are cut away on each side
        check(v + " k is 2", Arrays.asList(-1, 35, 35, 55, 3, 35, 40),
                () -> replay(f, 6, 2, "10 20 30 40 50 ? 60 ? 5 ? 70 80 ? 1 1 1 ? 90 ? 90 ?"));
        // edge: 30000 numbers up to 10^5 - the sum of the middle part does not fit in an int
        check(v + " large sums", Arrays.asList(100000),
                () -> replay(f, 30000, 1, "1 " + "100000 ".repeat(29999) + "?"));
    }

    private static List<Integer> replay(Factory f, int m, int k, String ops) {
        Stream stream = f.create(m, k);
        List<Integer> answers = new ArrayList<>();
        for (String op : ops.split(" ")) {
            if (op.equals("?")) answers.add(stream.average());
            else stream.add(Integer.parseInt(op));
        }
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

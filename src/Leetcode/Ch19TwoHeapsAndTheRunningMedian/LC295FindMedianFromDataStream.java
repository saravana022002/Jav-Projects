package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * LC 295. Find Median from Data Stream   [Hard]
 * https://leetcode.com/problems/find-median-from-data-stream/
 * Topics: Two Pointers, Design, Sorting, Heap (Priority Queue), Data Stream
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Design a structure that receives integers one at a time and can report the median of
 *   everything received so far at any moment. The median is the middle value of the sorted
 *   numbers; when the count is even it is the mean of the two middle values.
 *     addNum(num)    - adds one integer to the structure
 *     findMedian()   - returns the current median as a double (an error up to 10^-5 is fine)
 *   Follow-ups to think about afterwards: how would you speed things up if every number is in
 *   [0, 100]? What if 99% of the numbers are in [0, 100]?
 *
 * EXAMPLES
 *   addNum(1), addNum(2), findMedian() -> 1.5, addNum(3), findMedian() -> 2.0
 *   (the median of [2,3,4] is 3; the median of [2,3] is (2 + 3) / 2 = 2.5)
 *
 * CONSTRAINTS
 *   -10^5 <= num <= 10^5,   at least one number is stored before every findMedian call,
 *   at most 5 * 10^4 calls will be made to addNum and findMedian
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Heap/LC295FindMedianFromDataStream.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC295FindMedianFromDataStream {

    /**
     * Variant A
     * <br>Target: time O(1) per addNum, O(n log n) per findMedian, space O(n)
     */
    public static class MedianFinderA {
        public MedianFinderA() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void addNum(int num) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public double findMedian() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B
     * <br>Target: time O(n) per addNum, O(1) per findMedian, space O(n)
     */
    public static class MedianFinderB {
        public MedianFinderB() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void addNum(int num) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public double findMedian() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant C
     * <br>Target: time O(log n) per addNum, O(1) per findMedian, space O(n)
     */
    public static class MedianFinderC {
        public MedianFinderC() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void addNum(int num) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public double findMedian() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", () -> {
            LC295FindMedianFromDataStream.MedianFinderA m =
                    new LC295FindMedianFromDataStream.MedianFinderA();
            return new Finder() {
                public void add(int num) { m.addNum(num); }
                public double median() { return m.findMedian(); }
            };
        });
        runAll("B", () -> {
            LC295FindMedianFromDataStream.MedianFinderB m =
                    new LC295FindMedianFromDataStream.MedianFinderB();
            return new Finder() {
                public void add(int num) { m.addNum(num); }
                public double median() { return m.findMedian(); }
            };
        });
        runAll("C", () -> {
            LC295FindMedianFromDataStream.MedianFinderC m =
                    new LC295FindMedianFromDataStream.MedianFinderC();
            return new Finder() {
                public void add(int num) { m.addNum(num); }
                public double median() { return m.findMedian(); }
            };
        });
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 295. Find Median from Data Stream)
    // https://leetcode.com/problems/find-median-from-data-stream/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the official example
    // plus edge cases checked against a sort-everything reference. A case is a sequence like
    // "1 2 ? 3 ?": a number means addNum(number), "?" means findMedian(); the list of medians
    // is compared with a tolerance of 1e-5.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // the two operations every variant offers
    interface Finder {
        void add(int num);

        double median();
    }

    interface Factory {
        Finder create();
    }

    private static void runAll(String v, Factory f) {
        // official example
        check(v + " example 1", new double[]{1.5, 2.0}, f, "1 2 ? 3 ?");
        // edge: a single number
        check(v + " single number", new double[]{5.0}, f, "5 ?");
        // edge: negative numbers arriving in descending order
        check(v + " negatives descending", new double[]{-1.0, -1.5, -2.0, -2.5}, f,
                "-1 ? -2 ? -3 ? -4 ?");
        // edge: mixed order with duplicates, the median moves both ways
        check(v + " mixed", new double[]{4.0, 5.0, 7.0, 7.5, 8.0, 6.0, 5.0}, f,
                "5 15 1 3 ? 8 ? 7 9 ? 20 ? 10 ? 2 2 2 ? 2 ?");
    }

    private static List<Double> replay(Factory f, String ops) {
        Finder finder = f.create();
        List<Double> medians = new ArrayList<>();
        for (String op : ops.split(" ")) {
            if (op.equals("?")) medians.add(finder.median());
            else finder.add(Integer.parseInt(op));
        }
        return medians;
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, double[] expected, Factory f, String ops) {
        total++;
        String got;
        boolean ok = false;
        try {
            List<Double> medians = replay(f, ops);
            ok = medians.size() == expected.length;
            for (int i = 0; ok && i < expected.length; i++) {
                ok = Math.abs(medians.get(i) - expected[i]) <= 1e-5;
            }
            got = medians.toString();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + Arrays.toString(expected) + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

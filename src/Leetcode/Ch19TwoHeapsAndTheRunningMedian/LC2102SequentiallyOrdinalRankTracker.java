package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2102. Sequentially Ordinal Rank Tracker   [Hard]
 * https://leetcode.com/problems/sequentially-ordinal-rank-tracker/
 * Topics: Design, Heap (Priority Queue), Data Stream, Ordered Set
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Scenic locations have a unique lowercase name and an integer score. A location ranks
 *   better when its score is higher; on equal scores the lexicographically smaller name
 *   ranks better. The tracker starts empty and supports two operations:
 *     add(name, score)  - registers one more location
 *     get()             - returns the name of the i-th best location among all locations added
 *                         so far, where i is the number of get calls made so far (this one
 *                         included): the 1st call returns the best, the 2nd call the 2nd best...
 *   The tests never call get more often than add (at any moment).
 *
 * EXAMPLES
 *   add(bradford, 2), add(branford, 3), get() -> branford
 *   add(alps, 2),     get() -> alps
 *   add(orland, 2),   get() -> bradford
 *   add(orlando, 3),  get() -> bradford
 *   add(alpine, 2),   get() -> bradford,   get() -> orland
 *   (after the last add the ranking is branford, orlando, alpine, alps, bradford, orland)
 *
 * CONSTRAINTS
 *   name consists of lowercase English letters and is unique among all locations,
 *   1 <= name.length <= 10,   1 <= score <= 10^5,
 *   at any time the number of calls to get does not exceed the number of calls to add,
 *   at most 4 * 10^4 calls in total will be made to add and get
 * </pre>
 */
public class LC2102SequentiallyOrdinalRankTracker {

    /**
     * Variant A
     * <br>Target: time O(1) per add, O(n log n) per get, space O(n)
     */
    public static class SORTrackerA {
        public SORTrackerA() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void add(String name, int score) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public String get() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B
     * <br>Target: time O(n) per add, O(1) per get, space O(n)
     */
    public static class SORTrackerB {
        public SORTrackerB() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void add(String name, int score) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public String get() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant C
     * <br>Target: time O(log n) per add and per get, space O(n)
     */
    public static class SORTrackerC {
        public SORTrackerC() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void add(String name, int score) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public String get() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", () -> {
            LC2102SequentiallyOrdinalRankTracker.SORTrackerA t =
                    new LC2102SequentiallyOrdinalRankTracker.SORTrackerA();
            return new Tracker() {
                public void add(String name, int score) { t.add(name, score); }
                public String get() { return t.get(); }
            };
        });
        runAll("B", () -> {
            LC2102SequentiallyOrdinalRankTracker.SORTrackerB t =
                    new LC2102SequentiallyOrdinalRankTracker.SORTrackerB();
            return new Tracker() {
                public void add(String name, int score) { t.add(name, score); }
                public String get() { return t.get(); }
            };
        });
        runAll("C", () -> {
            LC2102SequentiallyOrdinalRankTracker.SORTrackerC t =
                    new LC2102SequentiallyOrdinalRankTracker.SORTrackerC();
            return new Tracker() {
                public void add(String name, int score) { t.add(name, score); }
                public String get() { return t.get(); }
            };
        });
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2102. Sequentially Ordinal Rank Tracker)
    // https://leetcode.com/problems/sequentially-ordinal-rank-tracker/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the official example
    // plus edge cases checked against a sort-everything reference. A case is a sequence like
    // "bradford:2 branford:3 ?": "name:score" means add(name, score), "?" means get(); the list
    // of names returned by get is compared.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // the two operations every variant offers
    interface Tracker {
        void add(String name, int score);

        String get();
    }

    interface Factory {
        Tracker create();
    }

    private static void runAll(String v, Factory f) {
        // official example
        check(v + " example 1",
                Arrays.asList("branford", "alps", "bradford", "bradford", "bradford", "orland"),
                () -> replay(f, "bradford:2 branford:3 ? alps:2 ? orland:2 ? orlando:3 ? "
                        + "alpine:2 ? ?"));
        // edge: a single location
        check(v + " single location", Arrays.asList("q"), () -> replay(f, "q:7 ?"));
        // edge: equal scores are ordered by name
        check(v + " tie on score", Arrays.asList("a", "b"), () -> replay(f, "b:5 a:5 ? ?"));
        // edge: better and worse locations keep arriving between the queries
        check(v + " mixed", Arrays.asList("ee", "kk", "kk", "kk", "zz", "aa", "mm"),
                () -> replay(f, "mm:50 kk:70 ee:70 ? zz:70 aa:50 ? bb:100000 cc:1 ? "
                        + "dd:70 ? ? ? ?"));
    }

    private static List<String> replay(Factory f, String ops) {
        Tracker tracker = f.create();
        List<String> names = new ArrayList<>();
        for (String op : ops.split(" ")) {
            if (op.equals("?")) {
                names.add(tracker.get());
            } else {
                String[] parts = op.split(":");
                tracker.add(parts[0], Integer.parseInt(parts[1]));
            }
        }
        return names;
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

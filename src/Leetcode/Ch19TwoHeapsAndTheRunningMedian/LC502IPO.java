package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 502. IPO   [Hard]
 * https://leetcode.com/problems/ipo/
 * Topics: Array, Greedy, Sorting, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #9
 *       also: ch. 18 Heaps and the k-selection family (#9),
 *             ch. 76 Greedy and the exchange argument (#11),
 *             ch. 77 Scheduling and interval greedy (#10),
 *             ch. 78 Huffman and the merge-cost family (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Before its IPO a company wants to finish at most k different projects. Project i brings a
 *   pure profit of profits[i], but it can only be started while the current capital is at least
 *   capital[i]; once finished, its profit is added to the capital. You start with capital w.
 *   Pick at most k distinct projects, in a sensible order, so that the final capital is as large
 *   as possible, and return that capital (it is guaranteed to fit in a 32-bit signed integer).
 *
 * EXAMPLES
 *   k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]  ->  4    projects 0 then 2: 0 + 1 + 3
 *   k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]  ->  6
 *
 * CONSTRAINTS
 *   1 <= k <= 10^5,   0 <= w <= 10^9,   n == profits.length == capital.length,   1 <= n <= 10^5,
 *   0 <= profits[i] <= 10^4,   0 <= capital[i] <= 10^9
 * </pre>
 */
public class LC502IPO {

    /**
     * Variant A - brute force: k times, scan for the affordable unfinished project of top profit
     * <br>Target: time O(k * n), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int findMaximizedCapitalBruteForce(int k, int w, int[] profits, int[] capital) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: heap keyed on capital feeds a heap keyed on profit
     * <br>Target: time O((n + k) log n), space O(n)
     * <br>Complete first: ch. 19 Two heaps and the running median (this chapter)
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC502IPO::findMaximizedCapitalBruteForce);
        runAll("B", LC502IPO::findMaximizedCapital);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 502. IPO)
    // https://leetcode.com/problems/ipo/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an exhaustive search over all project orders.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int k, int w, int[] profits, int[] capital);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4,
                () -> s.solve(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        check(v + " example 2", 6,
                () -> s.solve(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        // edge: no project is affordable, the capital stays w
        check(v + " nothing affordable", 0,
                () -> s.solve(2, 0, new int[]{5, 6}, new int[]{1, 2}));
        // edge: k is larger than the number of projects, and one project has profit 0
        check(v + " k above n", 6,
                () -> s.solve(5, 2, new int[]{3, 0, 1}, new int[]{2, 0, 3}));
        // edge: values at the constraint limits, capital equal to the money in hand
        check(v + " large values", 1000020000,
                () -> s.solve(2, 1000000000, new int[]{10000, 10000},
                        new int[]{1000000000, 1000010000}));
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

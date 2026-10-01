package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1801. Number of Orders in the Backlog   [Medium]
 * https://leetcode.com/problems/number-of-orders-in-the-backlog/
 * Topics: Array, Heap (Priority Queue), Simulation
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   orders[i] = [price, amount, type] describes a batch of `amount` separate orders with the
 *   same price; type 0 = buy, type 1 = sell. Batches are placed one after another, and the
 *   backlog of unexecuted orders starts empty. When one order is placed:
 *     - a buy order looks at the cheapest sell order in the backlog: if that price is <= the
 *       buy price, the two match and the sell order is removed; otherwise the buy joins the backlog
 *     - a sell order looks at the most expensive buy order in the backlog: if that price is
 *       >= the sell price, the two match and the buy order is removed; otherwise the sell joins
 *   Return how many orders are left in the backlog after all batches, modulo 10^9 + 7.
 *
 * EXAMPLES
 *   orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]  ->  6
 *       the last batch (4 buys @ 30) matches the sells @ 15 (two) and @ 25, one buy joins the
 *       backlog, which ends as 5 buys @ 10 and 1 buy @ 30
 *   orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]  ->  999999984
 *       the backlog ends with 10^9 - 3 sells @ 7 and 999999994 buys @ 5, i.e. 1999999991
 *       orders, which is 999999984 modulo 10^9 + 7
 *
 * CONSTRAINTS
 *   1 <= orders.length <= 10^5,   orders[i].length == 3,   1 <= price_i, amount_i <= 10^9,
 *   orderType_i is either 0 (buy) or 1 (sell)
 * </pre>
 */
public class LC1801NumberOfOrdersInTheBacklog {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     */
    public static int getNumberOfBacklogOrdersA(int[][] orders) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     */
    public static int getNumberOfBacklogOrdersB(int[][] orders) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1801NumberOfOrdersInTheBacklog::getNumberOfBacklogOrdersA);
        runAll("B", LC1801NumberOfOrdersInTheBacklog::getNumberOfBacklogOrdersB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1801. Number of Orders in the Backlog)
    // https://leetcode.com/problems/number-of-orders-in-the-backlog/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a one-order-at-a-time simulation (or, for the
    // huge case, against the sum taken modulo 10^9 + 7).
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] orders);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 6,
                () -> s.solve(new int[][]{{10, 5, 0}, {15, 2, 1}, {25, 1, 1}, {30, 4, 0}}));
        check(v + " example 2", 999999984,
                () -> s.solve(new int[][]{{7, 1000000000, 1}, {15, 3, 0}, {5, 999999995, 0},
                        {5, 1, 1}}));
        // edge: a buy and a sell at the same price match each other
        check(v + " equal prices match", 0, () -> s.solve(new int[][]{{5, 3, 0}, {5, 3, 1}}));
        // edge: both sides fill up and empty again several times
        check(v + " mixed", 1,
                () -> s.solve(new int[][]{{3, 2, 0}, {4, 2, 0}, {2, 1, 1}, {1, 4, 1}, {9, 1, 0},
                        {5, 3, 1}, {7, 2, 0}}));
        // edge: nothing matches and 3 * 10^9 orders need the modulo (and more than an int)
        check(v + " huge backlog", 999999986,
                () -> s.solve(new int[][]{{1, 1000000000, 0}, {2, 1000000000, 0},
                        {3, 1000000000, 0}}));
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

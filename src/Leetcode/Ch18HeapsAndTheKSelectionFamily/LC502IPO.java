package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 502. IPO   [Hard]
 * https://leetcode.com/problems/ipo/
 * Topics: Array, Greedy, Sorting, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #9
 *       also: ch. 19 Two heaps and the running median (#9),
 *             ch. 76 Greedy and the exchange argument (#11),
 *             ch. 77 Scheduling and interval greedy (#10),
 *             ch. 78 Huffman and the merge-cost family (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A company can finish at most k different projects before its IPO. Project i brings a
 *   pure profit of profits[i], but it can only be started when the company currently holds
 *   at least capital[i] (this is a threshold; nothing is spent). The company starts with
 *   capital w, and every finished project adds its profit to the capital. Choose at most k
 *   distinct projects, in a good order, so that the final capital is as large as possible and
 *   return that capital. The answer is guaranteed to fit in a 32-bit signed integer.
 *
 * EXAMPLES
 *   k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]  ->  4
 *       only project 0 is possible at first; then, with capital 1, take project 2: 0 + 1 + 3
 *   k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]  ->  6
 *
 * CONSTRAINTS
 *   1 <= k <= 10^5,   0 <= w <= 10^9,   n == profits.length == capital.length,
 *   1 <= n <= 10^5,   0 <= profits[i] <= 10^4,   0 <= capital[i] <= 10^9
 * </pre>
 */
public class LC502IPO {

    /**
     * Variant A - k rounds; each round scans all projects for the best affordable unfinished one
     * <br>Target: time O(n * min(k, n)) (at most O(n^2)), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int findMaximizedCapitalBruteForce(int k, int w, int[] profits, int[] capital) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: sort by capital; max-heap of affordable profits
     * <br>Target: time O(n log n + k log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 18 Heaps and the k-selection family (this chapter)
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
    // Each variant (A, B) is checked separately with the same cases: the two official examples
    // plus edge cases whose answers were checked against an exhaustive search over all orders
    // of projects. The solutions may sort or change the arrays they are given, so every call
    // gets fresh arrays.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int k, int w, int[] profits, int[] capital);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4, () -> s.solve(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        check(v + " example 2", 6, () -> s.solve(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        // edge: k is larger than the number of projects, so every project gets done
        check(v + " k above n", 6, () -> s.solve(10, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        // edge: no project can be started at all, the capital stays w
        check(v + " nothing affordable", 0, () -> s.solve(2, 0, new int[]{5, 6}, new int[]{1, 2}));
        // edge: k = 1, the best project among the affordable ones (the 9 is out of reach)
        check(v + " one pick", 9, () -> s.solve(1, 5, new int[]{3, 9, 4}, new int[]{5, 6, 4}));
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

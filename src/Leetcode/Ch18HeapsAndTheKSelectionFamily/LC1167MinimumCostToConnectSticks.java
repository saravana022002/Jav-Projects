package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1167. Minimum Cost to Connect Sticks   [Medium]
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 * Topics: Array, Greedy, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #7
 *       also: ch. 78 Huffman and the merge-cost family (#2)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   sticks[i] is the length of the i-th stick. Two sticks of lengths x and y can be joined
 *   into one stick of length x + y, and joining them costs x + y. Keep joining sticks, in any
 *   order you like, until only one stick is left. Return the smallest total cost.
 *   NOTE: premium problem - statement reconstructed, confirm at the link.
 *
 * EXAMPLES
 *   sticks = [2,4,3]    ->  14    join 2+3=5 (cost 5), then 5+4=9 (cost 9)
 *   sticks = [1,8,3,5]  ->  30    join 1+3=4, 4+5=9, 9+8=17: costs 4 + 9 + 17
 *   sticks = [5]        ->  0     already a single stick, nothing to join
 *
 * CONSTRAINTS
 *   1 <= sticks.length <= 10^4,   1 <= sticks[i] <= 10^4
 * </pre>
 */
public class LC1167MinimumCostToConnectSticks {

    /**
     * Variant A
     * <br>Target: time O(n^2 log n), space O(n)
     */
    public static int connectSticksA(int[] sticks) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     */
    public static int connectSticksB(int[] sticks) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(n)
     */
    public static int connectSticksC(int[] sticks) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1167MinimumCostToConnectSticks::connectSticksA);
        runAll("B", LC1167MinimumCostToConnectSticks::connectSticksB);
        runAll("C", LC1167MinimumCostToConnectSticks::connectSticksC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1167. Minimum Cost to Connect Sticks)
    // https://leetcode.com/problems/minimum-cost-to-connect-sticks/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples (premium problem, taken from the usual statement) plus edge cases whose answers
    // were checked against an exhaustive search over all joining orders.
    // The solutions may reorder the array they are given, so every call gets a fresh array.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] sticks);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 14, () -> s.solve(new int[]{2, 4, 3}));
        check(v + " example 2", 30, () -> s.solve(new int[]{1, 8, 3, 5}));
        check(v + " example 3", 0, () -> s.solve(new int[]{5}));
        // edge: just two sticks, one join
        check(v + " two sticks", 10, () -> s.solve(new int[]{7, 3}));
        // edge: four equal sticks
        check(v + " equal sticks", 32, () -> s.solve(new int[]{4, 4, 4, 4}));
        // edge: the sticks 1..5 in sorted order
        check(v + " sorted 1..5", 33, () -> s.solve(new int[]{1, 2, 3, 4, 5}));
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

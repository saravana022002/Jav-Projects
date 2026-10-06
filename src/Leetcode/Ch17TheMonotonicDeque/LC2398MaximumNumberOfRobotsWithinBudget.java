package Leetcode.Ch17TheMonotonicDeque;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2398. Maximum Number of Robots Within Budget   [Hard]
 * https://leetcode.com/problems/maximum-number-of-robots-within-budget/
 * Topics: Array, Binary Search, Queue, Sliding Window, Heap (Priority Queue), Prefix Sum,
 *         Monotonic Queue
 * Book: Algorithmic Thinking - ch. 17 The monotonic deque, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   There are n robots in a row. Robot i needs chargeTimes[i] to be charged and
 *   runningCosts[i] to run. Running a group of k consecutive robots costs
 *       max(chargeTimes of the group) + k * (sum of runningCosts of the group).
 *   Return the largest k for which some group of k consecutive robots costs at most budget,
 *   or 0 if not even one robot can be run. The cost can exceed the range of int.
 *
 * EXAMPLES
 *   chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25  ->  3
 *       the first three robots: max(3,6,1) + 3 * (2+1+3) = 6 + 18 = 24 <= 25
 *   chargeTimes = [11,12,19],  runningCosts = [10,8,7],    budget = 19  ->  0
 *       even one robot is too expensive: 11 + 1 * 10 = 21 > 19
 *
 * CONSTRAINTS
 *   chargeTimes.length == runningCosts.length == n,   1 <= n <= 5 * 10^4,
 *   1 <= chargeTimes[i], runningCosts[i] <= 10^5,   1 <= budget <= 10^15
 * </pre>
 */
public class LC2398MaximumNumberOfRobotsWithinBudget {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int maximumRobotsA(int[] chargeTimes, int[] runningCosts, long budget) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 11 Binary search on the answer
     *     + ch. 17 The monotonic deque (this chapter)
     */
    public static int maximumRobotsB(int[] chargeTimes, int[] runningCosts,
                                                long budget) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 9 Sliding window, chasing
     *     + ch. 17 The monotonic deque (this chapter)
     */
    public static int maximumRobotsC(int[] chargeTimes, int[] runningCosts, long budget) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2398MaximumNumberOfRobotsWithinBudget::maximumRobotsA);
        runAll("B", LC2398MaximumNumberOfRobotsWithinBudget::maximumRobotsB);
        runAll("C", LC2398MaximumNumberOfRobotsWithinBudget::maximumRobotsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 2398. Maximum Number of Robots Within Budget)
    // https://leetcode.com/problems/maximum-number-of-robots-within-budget/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose answers were checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] chargeTimes, int[] runningCosts, long budget);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3,
                () -> s.solve(new int[]{3, 6, 1, 3, 4}, new int[]{2, 1, 3, 4, 5}, 25L));
        check(v + " example 2", 0, () -> s.solve(new int[]{11, 12, 19}, new int[]{10, 8, 7}, 19L));
        // edge: one robot whose cost 5 + 1 * 3 equals the budget exactly (must count)
        check(v + " cost equals budget", 1, () -> s.solve(new int[]{5}, new int[]{3}, 8L));
        // edge: the best group is in the middle, a big charge time sits on both sides of it
        check(v + " best group in the middle", 3,
                () -> s.solve(new int[]{9, 1, 1, 1, 9}, new int[]{1, 1, 1, 1, 1}, 12L));
        // edge: 300 robots, group costs of billions: k * sum does not fit in an int
        check(v + " costs beyond int", 223,
                () -> s.solve(filled(300, 100000), filled(300, 100000), 5000000000L));
    }

    private static int[] filled(int n, int value) {
        int[] a = new int[n];
        Arrays.fill(a, value);
        return a;
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

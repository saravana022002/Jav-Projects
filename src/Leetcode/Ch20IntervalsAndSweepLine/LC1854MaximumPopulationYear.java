package Leetcode.Ch20IntervalsAndSweepLine;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1854. Maximum Population Year   [Easy]
 * https://leetcode.com/problems/maximum-population-year/
 * Topics: Array, Counting, Prefix Sum
 * Book: Algorithmic Thinking - ch. 20 Intervals and sweep line, Practice #5
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   logs[i] = [birth, death] gives the birth year and the death year of one person. That person
 *   counts in the population of year x when birth <= x < death - the year of death itself is
 *   not counted. Return the earliest year in which the population is at its maximum.
 *
 * EXAMPLES
 *   logs = [[1993,1999],[2000,2010]]                  ->  1993    the maximum population is 1
 *   logs = [[1950,1961],[1960,1971],[1970,1981]]      ->  1960    population 2 in 1960 and 1970
 *
 * CONSTRAINTS
 *   1 <= logs.length <= 100,   1950 <= birth_i < death_i <= 2050
 * </pre>
 */
public class LC1854MaximumPopulationYear {

    /**
     * Variant A - brute force: for every year, count the people alive in it
     * <br>Target: time O(Y * n), space O(1)     (Y = number of years, here at most 101)
     */
    public static int maximumPopulationBruteForce(int[][] logs) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - sort births and deaths separately, then walk both lists with two pointers
     * <br>Target: time O(n log n), space O(n)
     */
    public static int maximumPopulationSortedEvents(int[][] logs) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: difference array indexed by year, then prefix sums
     * <br>Target: time O(n + Y), space O(Y)
     */
    public static int maximumPopulation(int[][] logs) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1854MaximumPopulationYear::maximumPopulationBruteForce);
        runAll("B", LC1854MaximumPopulationYear::maximumPopulationSortedEvents);
        runAll("C", LC1854MaximumPopulationYear::maximumPopulation);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1854. Maximum Population Year)
    // https://leetcode.com/problems/maximum-population-year/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a count-every-year reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] logs);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1993, () -> s.solve(new int[][]{{1993, 1999}, {2000, 2010}}));
        check(v + " example 2", 1960,
                () -> s.solve(new int[][]{{1950, 1961}, {1960, 1971}, {1970, 1981}}));
        // edge: one person living through the whole allowed range
        check(v + " whole range", 1950, () -> s.solve(new int[][]{{1950, 2050}}));
        // edge: the year of death is not counted, so these two never live at the same time
        check(v + " death year not counted", 1990,
                () -> s.solve(new int[][]{{1990, 2000}, {2000, 2010}}));
        // edge: the peak (population 4) is in the middle of several overlapping lives
        check(v + " peak in the middle", 1964,
                () -> s.solve(new int[][]{{1955, 1990}, {1960, 1965}, {1963, 1980}, {1964, 1970},
                        {2000, 2050}}));
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

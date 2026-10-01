package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1370. Increasing Decreasing String   [Easy]
 * https://leetcode.com/problems/increasing-decreasing-string/
 * Topics: Hash Table, String, Counting
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Rebuild s into a new string, round after round, until every character has been used.
 *   One round has two sweeps:
 *     up sweep:   take the smallest remaining character, then repeatedly the smallest
 *                 remaining character that is strictly greater than the one taken last,
 *                 until no such character is left;
 *     down sweep: take the largest remaining character, then repeatedly the largest
 *                 remaining character that is strictly smaller than the one taken last,
 *                 until no such character is left.
 *   Equal characters are interchangeable, so the result is unique. Return it.
 *
 * EXAMPLES
 *   s = "aaaabbbbcccc"  ->  "abccbaabccba"     round 1 gives "abccba", round 2 the same
 *   s = "rat"           ->  "art"
 *
 * CONSTRAINTS
 *   1 <= s.length <= 500,   s consists of lowercase English letters only
 * </pre>
 */
public class LC1370IncreasingDecreasingString {

    /**
     * Variant A - simulate the steps literally, scanning the remaining characters for each pick
     * <br>Target: time O(n^2), space O(n)
     */
    public static String sortStringSimulation(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: count the letters, drain them in alternating sweeps
     * <br>Target: time O(26 * n), space O(1) besides the result string
     */
    public static String sortString(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1370IncreasingDecreasingString::sortStringSimulation);
        runAll("B", LC1370IncreasingDecreasingString::sortString);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1370. Increasing Decreasing String)
    // https://leetcode.com/problems/increasing-decreasing-string/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a literal simulation of the steps.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "abccbaabccba", () -> s.solve("aaaabbbbcccc"));
        check(v + " example 2", "art", () -> s.solve("rat"));
        // edge: a single character
        check(v + " single character", "z", () -> s.solve("z"));
        // edge: only one distinct letter, drained one per sweep
        check(v + " one distinct letter", "aaaaa", () -> s.solve("aaaaa"));
        // edge: the last letters are left over after the other letters ran out
        check(v + " leftover tail", "cdelotee", () -> s.solve("leetcode"));
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

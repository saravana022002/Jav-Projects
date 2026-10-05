package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 32. Longest Valid Parentheses   [Hard]
 * https://leetcode.com/problems/longest-valid-parentheses/
 * Topics: String, Dynamic Programming, Stack, Bracket Sequences
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s contains only '(' and ')'. Some contiguous pieces of s are well-formed (balanced)
 *   parentheses strings. Return the length of the longest such piece, or 0 if s has none
 *   (this includes the empty string).
 *
 * EXAMPLES
 *   s = "(()"     ->  2    the piece "()"
 *   s = ")()())"  ->  4    the piece "()()"
 *   s = ""        ->  0
 *
 * CONSTRAINTS
 *   0 <= s.length <= 3 * 10^4,   s[i] is '(' or ')'
 * </pre>
 */
public class LC32LongestValidParentheses {

    /**
     * Variant A - brute force: from every start index extend to the right, tracking the balance
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int longestValidParenthesesBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - two counters, no stack: one pass in each direction
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static int longestValidParenthesesTwoCounters(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: stack of the indices of the unmatched characters
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static int longestValidParentheses(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC32LongestValidParentheses::longestValidParenthesesBruteForce);
        runAll("B", LC32LongestValidParentheses::longestValidParenthesesTwoCounters);
        runAll("C", LC32LongestValidParentheses::longestValidParentheses);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 32. Longest Valid Parentheses)
    // https://leetcode.com/problems/longest-valid-parentheses/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve("(()"));
        check(v + " example 2", 4, () -> s.solve(")()())"));
        check(v + " example 3", 0, () -> s.solve(""));
        // edge: not empty, but no valid piece at all
        check(v + " no valid piece", 0, () -> s.solve(")("));
        // edge: valid blocks glued together make the whole string
        check(v + " glued blocks", 6, () -> s.solve("()(())"));
        // edge: an unmatched opener cuts the string into two pieces of length 2
        check(v + " cut by an opener", 2, () -> s.solve("()(()"));
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

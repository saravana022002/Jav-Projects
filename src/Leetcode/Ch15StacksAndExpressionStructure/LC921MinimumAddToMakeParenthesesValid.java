package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 921. Minimum Add to Make Parentheses Valid   [Medium]
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * Topics: String, Stack, Greedy, Bracket Sequences
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s contains only '(' and ')'. A string is valid when it is empty, or two valid strings
 *   written one after the other, or a valid string wrapped in a '(' ')' pair. One move inserts
 *   a single parenthesis (either kind) at any position of s. Return the smallest number of
 *   moves that turns s into a valid string.
 *
 * EXAMPLES
 *   s = "())"  ->  1    insert one '(' to get "(())"
 *   s = "((("  ->  3    insert three ')'
 *
 * CONSTRAINTS
 *   1 <= s.length <= 1000,   s[i] is '(' or ')'
 * </pre>
 */
public class LC921MinimumAddToMakeParenthesesValid {

    /**
     * Variant A - brute force: delete adjacent "()" pairs again and again, count what is left
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int minAddToMakeValidBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - stack: push every '(', a ')' pops one or counts as unmatched
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static int minAddToMakeValidStack(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: only one kind of symbol, so the stack is just a counter
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static int minAddToMakeValid(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC921MinimumAddToMakeParenthesesValid::minAddToMakeValidBruteForce);
        runAll("B", LC921MinimumAddToMakeParenthesesValid::minAddToMakeValidStack);
        runAll("C", LC921MinimumAddToMakeParenthesesValid::minAddToMakeValid);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 921. Minimum Add to Make Parentheses Valid)
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1, () -> s.solve("())"));
        check(v + " example 2", 3, () -> s.solve("((("));
        // edge: already valid, nothing to add
        check(v + " already valid", 0, () -> s.solve("()"));
        // edge: the pair is in the wrong order, one insert on each side
        check(v + " reversed pair", 2, () -> s.solve(")("));
        // edge: unmatched closers first, unmatched openers last
        check(v + " both kinds left", 4, () -> s.solve("()))(("));
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

package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 856. Score of Parentheses   [Medium]
 * https://leetcode.com/problems/score-of-parentheses/
 * Topics: String, Stack, Bracket Sequences
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #7
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s is a balanced parentheses string. Its score follows three rules: "()" is worth 1; two
 *   balanced strings written side by side, A B, are worth score(A) + score(B); a balanced
 *   string wrapped in one more pair, (A), is worth 2 * score(A). Return the score of s.
 *
 * EXAMPLES
 *   s = "()"    ->  1
 *   s = "(())"  ->  2    2 * score("()")
 *   s = "()()"  ->  2    1 + 1
 *
 * CONSTRAINTS
 *   2 <= s.length <= 50,   s consists only of '(' and ')',   s is a balanced parentheses string
 * </pre>
 */
public class LC856ScoreOfParentheses {

    /**
     * Variant A - brute force: split into top-level pieces, recurse into the inside of each
     * <br>Target: time O(n^2), space O(n)
     */
    public static int scoreOfParenthesesBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - track the nesting depth: every "()" contributes a power of two
     * <br>Target: time O(n), space O(1)
     */
    public static int scoreOfParenthesesDepth(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: stack with a running score for each open bracket
     * <br>Target: time O(n), space O(n)
     */
    public static int scoreOfParentheses(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC856ScoreOfParentheses::scoreOfParenthesesBruteForce);
        runAll("B", LC856ScoreOfParentheses::scoreOfParenthesesDepth);
        runAll("C", LC856ScoreOfParentheses::scoreOfParentheses);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 856. Score of Parentheses)
    // https://leetcode.com/problems/score-of-parentheses/
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
        check(v + " example 1", 1, () -> s.solve("()"));
        check(v + " example 2", 2, () -> s.solve("(())"));
        check(v + " example 3", 2, () -> s.solve("()()"));
        // edge: a pair next to a nested pair inside another pair
        check(v + " mixed", 6, () -> s.solve("(()(()))"));
        // edge: siblings add up, nesting doubles
        check(v + " siblings", 4, () -> s.solve("()(())()"));
        // edge: the deepest nesting the constraints allow (25 pairs): 2^24
        check(v + " deepest", 16777216, () -> s.solve("(".repeat(25) + ")".repeat(25)));
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

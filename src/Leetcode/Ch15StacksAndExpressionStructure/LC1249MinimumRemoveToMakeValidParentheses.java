package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1249. Minimum Remove to Make Valid Parentheses   [Medium]
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Topics: String, Stack
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s is made of lowercase letters, '(' and ')'. Delete as few parentheses as you can (every
 *   letter must stay) so that the parentheses left over are balanced, and return the string
 *   that remains. When several results are possible, any one of them is accepted.
 *
 * EXAMPLES
 *   s = "lee(t(c)o)de)"  ->  "lee(t(c)o)de"   "lee(t(co)de)" and "lee(t(c)ode)" also count
 *   s = "a)b(c)d"        ->  "ab(c)d"
 *   s = "))(("           ->  ""                the empty string is valid
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s[i] is '(' , ')' or a lowercase English letter
 * </pre>
 */
public class LC1249MinimumRemoveToMakeValidParentheses {

    /**
     * Variant A - brute force: find one parenthesis that cannot be matched, delete it, rescan
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static String minRemoveToMakeValidBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - two passes with a counter: drop surplus ')' going right, surplus '(' going left
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static String minRemoveToMakeValidTwoPass(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: stack of the indices of '('; unmatched ones are deleted
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static String minRemoveToMakeValid(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1249MinimumRemoveToMakeValidParentheses::minRemoveToMakeValidBruteForce);
        runAll("B", LC1249MinimumRemoveToMakeValidParentheses::minRemoveToMakeValidTwoPass);
        runAll("C", LC1249MinimumRemoveToMakeValidParentheses::minRemoveToMakeValid);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1249. Minimum Remove to Make Valid Parentheses)
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases; the best possible length of every answer was confirmed with an
    // independent brute force over all ways to delete parentheses.
    // "Return any valid string": the answer is checked (see verdict) instead of compared.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples (expected length = input length minus the minimum removals)
        verdictCase(v + " example 1", s, "lee(t(c)o)de)", 12);
        verdictCase(v + " example 2", s, "a)b(c)d", 6);
        verdictCase(v + " example 3", s, "))((", 0);
        // edge: no parentheses at all, nothing to remove
        verdictCase(v + " letters only", s, "abc", 3);
        // edge: one '(' too many, several equally good answers
        verdictCase(v + " extra opener", s, "(a(b(c)d)", 8);
        // edge: surplus closer at the start and surplus opener at the end
        verdictCase(v + " both kinds", s, ")(a)(", 3);
    }

    // The answer is valid when
    //   - it is s with some parentheses deleted (same letters, same order, nothing added),
    //   - its parentheses are balanced,
    //   - it has the expected length, so nothing extra was removed.
    private static void verdictCase(String name, Solver s, String input, int expectedLength) {
        check(name, "ok", () -> verdict(input, s.solve(input), expectedLength));
    }

    private static String verdict(String input, String answer, int expectedLength) {
        if (answer == null) return "null";
        if (!isSubsequence(answer, input) || !letters(answer).equals(letters(input))) {
            return "\"" + answer + "\" is not s with some parentheses deleted";
        }
        if (!balanced(answer)) return "\"" + answer + "\" is not balanced";
        if (answer.length() != expectedLength) {
            return "\"" + answer + "\" has length " + answer.length()
                    + ", expected " + expectedLength;
        }
        return "ok";
    }

    private static boolean isSubsequence(String small, String big) {
        int j = 0;
        for (int i = 0; i < big.length() && j < small.length(); i++) {
            if (big.charAt(i) == small.charAt(j)) j++;
        }
        return j == small.length();
    }

    private static String letters(String t) {
        return t.replace("(", "").replace(")", "");
    }

    private static boolean balanced(String t) {
        int open = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == '(') open++;
            else if (c == ')' && --open < 0) return false;
        }
        return open == 0;
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

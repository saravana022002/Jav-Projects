package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 20. Valid Parentheses   [Easy]
 * https://leetcode.com/problems/valid-parentheses/
 * Topics: String, Stack, Bracket Sequences
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s contains only the six characters ( ) [ ] { }. Decide whether the brackets are balanced:
 *   every opening bracket is closed by a bracket of the same kind, brackets are closed in the
 *   reverse of the order they were opened, and no closing bracket appears without an opening
 *   bracket before it.
 *
 * EXAMPLES
 *   s = "()"      ->  true
 *   s = "()[]{}"  ->  true
 *   s = "(]"      ->  false    the kinds do not match
 *   s = "([])"    ->  true
 *   s = "([)]"    ->  false    closed in the wrong order
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^4,   s consists only of the characters ()[]{}
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Stack/LC20ValidParentheses.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC20ValidParentheses {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static boolean isValidA(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static boolean isValidB(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC20ValidParentheses::isValidA);
        runAll("B", LC20ValidParentheses::isValidB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 20. Valid Parentheses)
    // https://leetcode.com/problems/valid-parentheses/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the five official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/16 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", true, () -> s.solve("()"));
        check(v + " example 2", true, () -> s.solve("()[]{}"));
        check(v + " example 3", false, () -> s.solve("(]"));
        check(v + " example 4", true, () -> s.solve("([])"));
        check(v + " example 5", false, () -> s.solve("([)]"));
        // edge: a closer shows up while nothing is open
        check(v + " closer first", false, () -> s.solve(")("));
        // edge: the start is balanced, but two openers are never closed
        check(v + " openers left over", false, () -> s.solve("(())(("));
        // edge: deeper nesting with siblings, all three kinds
        check(v + " nested siblings", true, () -> s.solve("{[()()]}"));
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

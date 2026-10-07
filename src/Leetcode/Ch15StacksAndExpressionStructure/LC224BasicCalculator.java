package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 224. Basic Calculator   [Hard]
 * https://leetcode.com/problems/basic-calculator/
 * Topics: Math, String, Stack, Recursion
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s is an arithmetic expression made of non-negative integers, '+', '-', parentheses and
 *   spaces. A '-' can also be a unary minus, as in "-1" or "-(2 + 3)"; a '+' never is. Evaluate
 *   the expression and return its value. Built-in evaluators such as eval() are not allowed.
 *
 * EXAMPLES
 *   s = "1 + 1"                ->  2
 *   s = " 2-1 + 2 "            ->  3
 *   s = "(1+(4+5+2)-3)+(6+8)"  ->  23
 *
 * CONSTRAINTS
 *   1 <= s.length <= 3 * 10^5,   s consists of digits, '+', '-', '(', ')' and ' ',
 *   s is a valid expression,   '+' is never used as a unary operator,   '-' may be unary,
 *   no two operators appear in a row,   every number and running result fits in 32 bits
 * </pre>
 */
public class LC224BasicCalculator {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int calculateA(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 56 Recursion and the shape of a subproblem (later chapter)
     */
    public static int calculateB(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static int calculateC(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC224BasicCalculator::calculateA);
        runAll("B", LC224BasicCalculator::calculateB);
        runAll("C", LC224BasicCalculator::calculateC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 224. Basic Calculator)
    // https://leetcode.com/problems/basic-calculator/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent reference (Python evaluator).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve("1 + 1"));
        check(v + " example 2", 3, () -> s.solve(" 2-1 + 2 "));
        check(v + " example 3", 23, () -> s.solve("(1+(4+5+2)-3)+(6+8)"));
        // edge: unary minus in front of a nested group, with spaces
        check(v + " unary before group", -12, () -> s.solve("- (3 + (4 + 5))"));
        // edge: a minus in front of a group flips every sign inside it
        check(v + " minus before group", 3, () -> s.solve("2-(5-6)"));
        // edge: unary minus right after an opening bracket
        check(v + " unary inside group", 3, () -> s.solve("1-(     -2)"));
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

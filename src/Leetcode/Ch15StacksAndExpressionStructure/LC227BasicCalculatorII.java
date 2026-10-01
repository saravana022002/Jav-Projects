package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 227. Basic Calculator II   [Medium]
 * https://leetcode.com/problems/basic-calculator-ii/
 * Topics: Math, String, Stack
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #10
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s is an arithmetic expression: non-negative integers and the operators + - * / with any
 *   number of spaces in between. Evaluate it with the usual precedence ( * and / bind tighter
 *   than + and -, operators of the same level go from left to right ). Integer division
 *   truncates toward zero. Built-in evaluators such as eval() are not allowed.
 *
 * EXAMPLES
 *   s = "3+2*2"      ->  7
 *   s = " 3/2 "      ->  1
 *   s = " 3+5 / 2 "  ->  5
 *
 * CONSTRAINTS
 *   1 <= s.length <= 3 * 10^5,   s consists of integers and the operators + - * / separated by
 *   spaces,   s is a valid expression,   every integer is in [0, 2^31 - 1],
 *   all intermediate results and the answer fit in a signed 32-bit integer
 * </pre>
 */
public class LC227BasicCalculatorII {

    /**
     * Variant A - brute force: split into tokens, collapse every * and / first, then fold + and -
     * <br>Target: time O(n^2), space O(n)
     */
    public static int calculateBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - no stack: keep only the running sum and the term being built
     * <br>Target: time O(n), space O(1)
     */
    public static int calculateTwoVariables(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: stack of signed terms, * and / fire on the top at once
     * <br>Target: time O(n), space O(n)
     */
    public static int calculate(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC227BasicCalculatorII::calculateBruteForce);
        runAll("B", LC227BasicCalculatorII::calculateTwoVariables);
        runAll("C", LC227BasicCalculatorII::calculate);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 227. Basic Calculator II)
    // https://leetcode.com/problems/basic-calculator-ii/
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
        check(v + " example 1", 7, () -> s.solve("3+2*2"));
        check(v + " example 2", 1, () -> s.solve(" 3/2 "));
        check(v + " example 3", 5, () -> s.solve(" 3+5 / 2 "));
        // edge: just a number, no operator at all
        check(v + " single number", 42, () -> s.solve("42"));
        // edge: same-level operators go left to right: (30 / 4) * 2, not 30 / (4 * 2)
        check(v + " left to right", 14, () -> s.solve(" 30 / 4 * 2 "));
        // edge: every operator, with a subtraction between the products
        check(v + " all operators", -24, () -> s.solve("1*2-3/4+5*6-7*8+9/10"));
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

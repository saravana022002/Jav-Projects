package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 150. Evaluate Reverse Polish Notation   [Medium]
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * Topics: Array, Math, Stack
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #9
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   tokens is an arithmetic expression in Reverse Polish (postfix) notation: the operands come
 *   first and their operator follows them. The operators are + - * / and an operand is either
 *   an integer or the value of a smaller expression. Division truncates toward zero. There is
 *   no division by zero, the expression is valid, and every intermediate value fits in 32 bits.
 *   Return the value of the whole expression.
 *
 * EXAMPLES
 *   tokens = ["2","1","+","3","*"]                                       ->  9    (2 + 1) * 3
 *   tokens = ["4","13","5","/","+"]                                      ->  6    4 + (13 / 5)
 *   tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]   ->  22
 *
 * CONSTRAINTS
 *   1 <= tokens.length <= 10^4,   every token is "+", "-", "*", "/" or an integer in [-200, 200]
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Stack/LC150EvaluateReversePolishNotation.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC150EvaluateReversePolishNotation {

    /**
     * Variant A - brute force: find the first operator, replace "a b op" by its value, repeat
     * <br>Target: time O(n^2), space O(n)
     */
    public static int evalRPNBruteForce(String[] tokens) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: stack of operands, an operator combines the top two
     * <br>Target: time O(n), space O(n)
     */
    public static int evalRPN(String[] tokens) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC150EvaluateReversePolishNotation::evalRPNBruteForce);
        runAll("B", LC150EvaluateReversePolishNotation::evalRPN);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 150. Evaluate Reverse Polish Notation)
    // https://leetcode.com/problems/evaluate-reverse-polish-notation/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(String[] tokens);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 9, () -> s.solve(new String[]{"2", "1", "+", "3", "*"}));
        check(v + " example 2", 6, () -> s.solve(new String[]{"4", "13", "5", "/", "+"}));
        check(v + " example 3", 22, () -> s.solve(new String[]{"10", "6", "9", "3", "+", "-11",
                "*", "/", "*", "17", "+", "5", "+"}));
        // edge: a single operand
        check(v + " single operand", 42, () -> s.solve(new String[]{"42"}));
        // edge: operand order matters for '-'
        check(v + " minus order", -1, () -> s.solve(new String[]{"3", "4", "-"}));
        // edge: division truncates toward zero (-3, not -4)
        check(v + " negative division", -3, () -> s.solve(new String[]{"-7", "2", "/"}));
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

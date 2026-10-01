package Practice.Stack;

import java.util.Arrays;
import java.util.Stack;

// ============================================================================
// LC 150. Evaluate Reverse Polish Notation   [Medium]
// https://leetcode.com/problems/evaluate-reverse-polish-notation/
// Topics: Array, Math, Stack
// ============================================================================
//
// PROBLEM
//   An expression is given in Reverse Polish Notation (operator AFTER its two operands):
//   "2 1 + 3 *" means (2 + 1) * 3. Evaluate it and return the integer result. Operators
//   are + - * /, division truncates toward zero, and the input is always a valid
//   expression whose values all fit in 32 bits.
//
// EXAMPLES
//   ["2","1","+","3","*"]   ->  9      (2 + 1) * 3
//   ["4","13","5","/","+"]  ->  6      4 + (13 / 5) = 4 + 2
//   ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]  ->  22
//                                      10 * (6 / (12 * -11)) + 17 + 5, and 6 / -132 = 0
//
// CONSTRAINTS
//   1 <= tokens.length <= 10^4,
//   tokens[i] is "+", "-", "*", "/" or an integer in [-200, 200]
//
// INTUITION
//   RPN was designed for a stack: read left to right, push every number, and when an
//   operator arrives its two operands are exactly the two most recent values - pop them,
//   combine them, push the result back. At the end the stack holds only the answer.
//
// APPROACH (as implemented below)
//   1. For each token:
//        a one-character operator -> b = pop() (right operand), a = pop() (left operand),
//                                    push a op b
//        otherwise (a number)     -> push its value
//   2. Return the top of the stack.
//
// DRY RUN   ["2","1","+","3","*"]   (main() runs example 3 and prints 22)
//   "2" -> [2]      "1" -> [2, 1]     "+" -> b = 1, a = 2 -> [3]
//   "3" -> [3, 3]   "*" -> b = 3, a = 3 -> [9]                       answer 9
//
// COMPLEXITY
//   Time : O(n) - each token is pushed or popped a constant number of times
//   Space: O(n) - the stack holds at most about half of the tokens
//
// GOTCHAS
//   - Pop the RIGHT operand first: for "13 5 /" the first pop is 5, so a / b = 13 / 5.
//   - "-11" is a number, not an operator - hence the token.length() == 1 check.
//   - Java's int division already truncates toward zero, as the problem wants.
//   - The arrow-style switch (case "+" -> ...) needs Java 14 or newer.
//
// RELATED
//   - LC 224 Basic Calculator - evaluating an ordinary (infix) expression, also with stacks.
// ============================================================================
public class LC150EvaluateReversePolishNotation {
    public static void main(String[] args) {
        // Example 3 -> expected output: 22
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        // operands waiting for their operator
        Stack<Integer> ans = new Stack<>();
        int a;
        int b;
        for (String token : tokens) {
            // length 1 so that negative numbers like "-11" are not mistaken for "-"
            if (token.length() == 1 && "+*-/".contains(token)) {
                // right operand is on top, left operand below it
                b = ans.pop();
                a = ans.pop();
                switch (token) {
                    case "+" -> ans.push(a + b);
                    case "-" -> ans.push(a - b);
                    case "*" -> ans.push(a * b);
                    case "/" -> ans.push(a / b);
                }
            } else {
                // a number: wait on the stack for its operator
                ans.push(Integer.valueOf(token));
            }
        }
        // a valid expression leaves exactly one value: the result
        return ans.peek();
    }
}

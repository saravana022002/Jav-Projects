package Practice.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// ============================================================================
// LC 20. Valid Parentheses   [Easy]
// https://leetcode.com/problems/valid-parentheses/
// Topics: String, Stack, Bracket Sequences
// ============================================================================
//
// PROBLEM
//   s contains only the brackets ( ) [ ] { }. Return true if it is balanced: every
//   opening bracket is closed by a bracket of the same type, in the right order, and
//   every closing bracket has a matching opener.
//
// EXAMPLES
//   s = "()[]{}"  ->  true
//   s = "([])"    ->  true      nested correctly
//   s = "(]"      ->  false     wrong type
//   s = "([)]"    ->  false     right types, wrong order
//
// CONSTRAINTS
//   1 <= s.length <= 10^4,   s consists only of '()[]{}'
//
// INTUITION
//   A closing bracket must match the MOST RECENT opener that is still open - "last opened,
//   first closed". That is exactly what a stack gives: push openers; on a closer, the top
//   of the stack must be its partner, and then both are done (pop). The string is
//   balanced only if nothing is left open at the end.
//
// APPROACH (as implemented below)
//   1. For each character ch:
//        an opener ({ ( [)       -> push it
//        a closer, stack empty   -> nothing to close -> false
//        a closer matching top   -> pop
//        a closer not matching   -> false
//   2. Return true only if the stack is empty (no opener left unclosed).
//
// DRY RUN   s = "([])"
//   '(' -> push         stack [(]
//   '[' -> push         stack [(, []
//   ']' -> matches '['  stack [(]
//   ')' -> matches '('  stack []      -> empty -> true
//   main() runs s = "]": a closer with an empty stack -> false
//
// COMPLEXITY
//   Time : O(n) - one push or pop per character
//   Space: O(n) - e.g. "(((((" keeps every character on the stack
//
// GOTCHAS
//   - Check isEmpty() before peek(): a leading closer ("]") would otherwise throw.
//   - Do not forget the final emptiness check - "((" never fails inside the loop.
//
// RELATED
//   - LC 22 Generate Parentheses - builds every balanced string instead of checking one.
// ============================================================================
public class LC20ValidParentheses {
    public static void main(String[] args) {
        // a closing bracket with nothing open -> expected output: false
        String s = "]";
        System.out.println(isValid(s));
    }
    public static boolean isValid(String s) {
        // openers that are still waiting for their closer
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == '{' || ch == '(' || ch == '['){
                stk.push(ch);
            }else {
                // a closer with nothing open can never be matched
                if(stk.isEmpty()){
                    return false;
                }

                // it must close the most recent opener, of the same type
                char top = stk.peek();
                if(top == '{' && ch == '}'
                        || top == '(' && ch == ')'
                        || top == '[' && ch == ']'){
                    stk.pop();
                }else {
                    return false;
                }
            }
        }
        // balanced only if no opener is left unclosed
        return stk.empty();
    }
}

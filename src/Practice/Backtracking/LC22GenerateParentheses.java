package Practice.Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// ============================================================================
// LC 22. Generate Parentheses   [Medium]
// https://leetcode.com/problems/generate-parentheses/
// Topics: String, Dynamic Programming, Backtracking, Bracket Sequences
// ============================================================================
//
// PROBLEM
//   Given n, list every string made of n '(' and n ')' that is well-formed: brackets are
//   properly matched and nested, as in normal code. Any order of the strings is accepted.
//
// EXAMPLES
//   n = 3  ->  ["((()))","(()())","(())()","()(())","()()()"]
//   n = 1  ->  ["()"]
//
// CONSTRAINTS
//   1 <= n <= 8
//
// INTUITION
//   Reading a bracket string left to right, it is well-formed exactly when the number of
//   ')' never gets ahead of the number of '(' and both end at n. So build the string one
//   character at a time and only make moves that keep this true:
//     - '(' is allowed while fewer than n have been used   (left < n)
//     - ')' is allowed only if it closes an open one       (right < left)
//   Every branch then ends in a valid string - no invalid string is ever generated, so
//   there is nothing to filter out afterwards.
//
// APPROACH (as implemented below)
//   1. generateParenthesis(n) starts parenthesis(left = 0, right = 0, ...) with an empty
//      characters list (the string built so far).
//   2. If left == right == n, join characters into a String and add it to ans.
//   3. If left < n: append '(', recurse with left + 1, then remove it (backtrack).
//   4. If right < left: append ')', recurse with right + 1, then remove it.
//
// DRY RUN   n = 3 (main's input); "string so far" (left, right)
//   "" -> "(" (1,0) -> "((" (2,0) -> "(((" (3,0) -> only ')' fits      -> "((()))"
//                                 -> "(()" (2,1) -> "(()(" (3,1)       -> "(()())"
//                                                -> "(())" (2,2) -> "(" -> "(())()"
//                   -> "()" (1,1) -> only '(' fits -> "()(" (2,1)
//                                                -> "()((" (3,1)       -> "()(())"
//                                                -> "()()" (2,2) -> "(" -> "()()()"
//   '(' is always tried before ')', which gives exactly the official output order.
//
// COMPLEXITY
//   Time : O(4^n / sqrt(n)) - the number of answers is the n-th Catalan number,
//          about 4^n / (n * sqrt(n)), and each answer costs O(n) to build and join.
//   Space: O(n) - recursion depth and the characters list are at most 2n long
//          (the output list is extra).
//
// GOTCHAS
//   - The base case has no return, but it is still safe: when left == right == n both
//     "if" tests below it are false, so the call ends there.
//   - The rule is right < left, not right < n - otherwise strings like "())(" appear.
//   - characters.remove(characters.size() - 1) removes by INDEX (int argument), which is
//     what backtracking needs here.
//
// RELATED
//   - LC 20 Valid Parentheses (in this repo) - checks a given string; the "never more ')'
//     than '('" rule used here is the same balance idea.
//   - LC 17 Letter Combinations of a Phone Number - the same build-a-string-one-char-
//     at-a-time backtracking.
// ============================================================================
public class LC22GenerateParentheses {
    public static void main(String[] args) {
        // Example 1. Expected output: [((())), (()()), (())(), ()(()), ()()()]
        System.out.println(generateParenthesis(3));
    }

    // Returns every well-formed string of n pairs of parentheses.
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        parenthesis(0, 0, n, new ArrayList<>(), ans);
        return ans;
    }

    // left / right = how many '(' / ')' are already in characters.
    private static void parenthesis(int left, int right, int n, List<Character> characters, List<String> ans) {
        // all 2n characters placed: join them into one answer
        if(left == right && left == n){
            String result = characters.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            ans.add(result);
        }

        // open a new pair while some '(' are still unused
        if (left < n) {
            characters.add('(');
            parenthesis(left + 1, right, n, characters, ans);
            characters.remove(characters.size() - 1);
        }

        // close only when an open '(' is waiting, so ')' never gets ahead
        if (right < left) {
            characters.add(')');
            parenthesis(left, right + 1, n, characters, ans);
            characters.remove(characters.size() - 1);
        }
    }
}

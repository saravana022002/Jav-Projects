package Practice.Stack;

import java.util.Stack;

// ============================================================================
// LC 2434. Using a Robot to Print the Lexicographically Smallest String   [Medium]
// https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/
// Topics: Hash Table, String, Stack, Greedy
// ============================================================================
//
// PROBLEM
//   A robot holds an empty string t. Repeat until s and t are both empty:
//     - move the FIRST character of s to the END of t, or
//     - take the LAST character of t and write it on paper.
//   Return the alphabetically smallest string the paper can end up with.
//
// EXAMPLES
//   s = "zza"   ->  "azz"     move all three into t, then write them back from the end
//   s = "bac"   ->  "abc"     move b, a; write a, b; move c; write c
//   s = "bdda"  ->  "addb"
//
// CONSTRAINTS
//   1 <= s.length <= 10^5,   s has only lowercase English letters
//
// INTUITION
//   t only grows at its end and is written from its end, so it is a STACK. The paper
//   should always get the smallest letter it can. Should the top of t be written now?
//   Yes, exactly when it is <= every letter still left in s: nothing smaller can arrive
//   later to be written before it. If a smaller letter is still coming, keep moving
//   letters into t until that letter is on top. Precompute minSuffix[i] = the smallest
//   letter in s[i..] so this test is O(1).
//
// APPROACHES IN THIS FILE   (same idea, same result)
//   A. robotWithString()  - feeds s through a second stack sStk   <- main() runs this one
//   B. robotWithString1() - iterates over s directly with one stack
//
// APPROACH (as implemented below)
//   1. minSuffix[i] = min(s[i], minSuffix[i + 1]), filled right to left.
//   2. For each character s[i]: push it onto t, then while t's top <= minSuffix[i + 1]
//      (or s is used up), pop the top and write it.
//   3. Write whatever is left in t, top first.
//   (In A, sStk holds s reversed, so popping it yields s left to right; i counts pushes.)
//
// DRY RUN   s = "bac"   minSuffix = [a, a, c]   (main() runs "bdda" and prints "addb")
//   push 'b': t [b]      top b <= min of "ac" = a? no
//   push 'a': t [b, a]   a <= min of "c" = c -> write a;  b <= c -> write b   paper "ab"
//   push 'c': t [c]      s is used up -> write c                             paper "abc"
//
// COMPLEXITY
//   Time : O(n) - every character is pushed once and popped once
//   Space: O(n) - minSuffix, the stack(s) and the output
//
// GOTCHAS
//   - Compare with the letters still LEFT in s (minSuffix[i + 1]), not including the one
//     just pushed.
//   - Use <=: a letter equal to the smallest remaining one is safe to write now.
//   - Do not forget the final loop that empties t.
// ============================================================================
public class LC2434UsingARobotToPrintTheLexicographicallySmallestString {
    public static void main(String[] args) {
        // Example 3 -> expected output: addb
        String s = "bdda";
        System.out.println(robotWithString(s));
    }
    // Approach A: same greedy, feeding s through a second stack
    public static String robotWithString(String s) {
        Stack<Character> sStk = new Stack<>();
        Stack<Character> tStk = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        // minSuffix[i] = smallest letter in s[i..]
        char[] minSuffix = new char[s.length()];
        minSuffix[s.length() - 1] = s.charAt(s.length() - 1);
        for (int i = s.length() - 2; i >= 0; i--) {
            minSuffix[i] = (char) Math.min(s.charAt(i), minSuffix[i + 1]);
        }

        // push s in reverse, so popping sStk yields s from left to right
        for (int i = s.length() - 1; i >= 0; i--) {
            sStk.push(s.charAt(i));
        }

        // i = index of the character just moved into t
        int i = 0;
        while (!sStk.isEmpty()){
            Character ch = sStk.pop();
            tStk.push(ch);
            // write the top while nothing smaller is still waiting in s
            while (!tStk.isEmpty() && (i == s.length() - 1 || tStk.peek() <= minSuffix[i+1])){
                stringBuilder.append(tStk.pop());
            }
            i++;
        }
        // s is used up: write the rest of t, top first
        while (!tStk.isEmpty()){
            stringBuilder.append(tStk.pop());
        }
        return stringBuilder.toString();
    }



    // Approach B: the same greedy, iterating over s directly
    public static String robotWithString1(String s) {
        Stack<Character> t = new Stack<>();
        StringBuilder result = new StringBuilder();
        int n = s.length();
        // minSuffix[i] = smallest letter in s[i..]
        char[] minSuffix = new char[n];
        minSuffix[n - 1] = s.charAt(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = (char) Math.min(s.charAt(i), minSuffix[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            t.push(s.charAt(i));
            // write the top while it is <= every letter still left in s
            while (!t.isEmpty() && (i == n - 1 || t.peek() <= minSuffix[i + 1])) {
                result.append(t.pop());
            }
        }
        // flush what is left in t
        while (!t.isEmpty()) {
            result.append(t.pop());
        }
        return result.toString();
    }

}

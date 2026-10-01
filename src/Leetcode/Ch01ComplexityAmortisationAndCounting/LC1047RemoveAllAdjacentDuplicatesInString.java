package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1047. Remove All Adjacent Duplicates In String   [Easy]
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 * Topics: String, Stack
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #2
 *       also: ch. 15 Stacks and expression structure (#5)
 *
 * PROBLEM
 *   s is a string of lowercase letters. One move picks two neighbouring equal letters and
 *   deletes both; a deletion can bring two new letters side by side. Keep making moves
 *   until none is possible and return what is left (it may be the empty string). The
 *   final string is the same whatever order you delete in.
 *
 * EXAMPLES
 *   s = "abbaca"  ->  "ca"    delete "bb" -> "aaca", then "aa" -> "ca"
 *   s = "azxxzy"  ->  "ay"    delete "xx" -> "azzy", then "zz" -> "ay"
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s consists of lowercase English letters
 *
 * INTUITION
 *   Read the string left to right and remember the survivors so far. A new letter can only
 *   pair up with the LAST survivor, so keep the survivors on a stack: if the new letter
 *   equals the top, both vanish (pop), otherwise it joins (push). Popping exposes the
 *   previous survivor as the new top, which is exactly how a chain like "abba" collapses
 *   all the way. Every letter is pushed once and popped at most once, so the total work is
 *   linear - an amortised O(1) per character.
 *
 * APPROACHES IN THIS FILE
 *   A. removeDuplicates()    - java.util.Stack&lt;Character>; at the end pop everything into a
 *                              StringBuilder and reverse it - O(n) time, O(n) space
 *   B. removeDuplicatesStr() - the StringBuilder itself is the stack, so no reversal is
 *                              needed - O(n) time, O(n) space      <- main() runs this one
 *
 * APPROACH (as implemented below, method B)
 *   1. reversed = an empty StringBuilder; it holds the survivors, already in reading order.
 *   2. For every character c of s: if reversed is not empty and its last character equals c,
 *      deleteCharAt the last position (the pair cancels); otherwise append c.
 *   3. Return reversed.toString().
 *
 * DRY RUN   s = "abbaca"
 *   a -> empty builder, append               "a"
 *   b -> last is 'a' != 'b', append          "ab"
 *   b -> last is 'b' == 'b', delete it       "a"
 *   a -> last is 'a' == 'a', delete it       ""
 *   c -> empty builder, append               "c"
 *   a -> last is 'c' != 'a', append          "ca"       -> return "ca"
 *
 * COMPLEXITY
 *   Time : O(n) - each character is appended at most once and deleted at most once (deleting
 *          the LAST char of a StringBuilder moves nothing, so it is O(1))
 *   Space: O(n) - the survivors, worst case nothing cancels; method A also builds a copy
 *
 * GOTCHAS
 *   - The answer can be empty although s has at least one letter: "abba" -> "".
 *   - Runs shrink by pairs: "aaa" -> "a", "aaaa" -> "".
 *   - Method A uses ae.peek().equals(c): boxed Characters should be compared by value.
 *     == would only work by luck of the small-value cache. Method B compares a char with a
 *     Character using ==, which unboxes the Character first, so it is safe.
 *   - Method A pops the stack top-first, which is why it ends with reverse().
 *
 * RELATED
 *   - LC 1209 Remove All Adjacent Duplicates in String II - the same stack, but k equal
 *     letters in a row are removed, so each stack entry also stores a count.
 *   - LC 2390 Removing Stars From a String - every '*' deletes the closest letter on its
 *     left: the same keep-the-survivors-on-a-stack idea.
 * </pre>
 */
public class LC1047RemoveAllAdjacentDuplicatesInString {
    public static void main(String[] args) {
        // Example 1: prints "ca" (approach B; removeDuplicates(s) returns the same)
        String s = "abbaca";
        System.out.println(removeDuplicatesStr(s));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Approach A: java.util.Stack<Character> as the stack of survivors - O(n) time, O(n) space
    public static String removeDuplicates(String s) {
        Stack<Character> ae = new Stack<Character>();
        for (Character c : s.toCharArray()){
            // same letter as the top -> the pair cancels; otherwise the letter stays
            if(!ae.isEmpty() && ae.peek().equals(c)){
                ae.pop();
            }else {
                ae.push(c);
            }
        }
        // popping yields the survivors last-to-first, so reverse them back to reading order
        StringBuilder reversed = new StringBuilder();
        while (!ae.isEmpty()){
            reversed.append(ae.pop());
        }
        return reversed.reverse().toString();
    }

    // Approach B: the StringBuilder is the stack, no reversal needed - O(n) time, O(n) space
    public static String removeDuplicatesStr(String s) {
        // survivors so far, already in reading order (the name "reversed" is left over from A)
        StringBuilder reversed = new StringBuilder();
        for (Character c : s.toCharArray()){
            int len = reversed.length();
            // last survivor equals c -> both vanish (drop the last char); otherwise keep c
            if (len > 0 && reversed.charAt(reversed.length() - 1) == c){
                reversed.deleteCharAt(reversed.length() - 1);
            }else {
                reversed.append(c);
            }
        }
        return reversed.toString();
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1047. Remove All Adjacent Duplicates In String)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases; every case is run against both methods
    // of the solution (removeDuplicates and removeDuplicatesStr). Expected values come from the
    // statement or from a brute force that deletes the first adjacent pair again and again.
    //   Expected result: 12/12 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: abbaca", "abbaca", "ca");
        both("example 2: azxxzy", "azxxzy", "ay");
        // edge: a single letter stays
        both("single letter", "a", "a");
        // edge: a cascade that removes everything - the answer is the empty string
        both("abba collapses to empty", "abba", "");
        // edge: an odd run keeps one letter
        both("aaa -> a", "aaa", "a");
        // edge: 10^5 letters, deepest cascade (x followed by reverse(x) always cancels fully)
        both("10^5 letters, full cascade", fullCascade(), "");
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static String fullCascade() {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < 50000; i++) x.append((char) ('a' + i % 3));
        String half = x.toString();
        return half + new StringBuilder(half).reverse();
    }

    // runs one input through both methods of the solution
    private static void both(String name, String s, String expected) {
        check(name + "  [removeDuplicates]", expected,
                () -> LC1047RemoveAllAdjacentDuplicatesInString.removeDuplicates(s));
        check(name + "  [removeDuplicatesStr]", expected,
                () -> LC1047RemoveAllAdjacentDuplicatesInString.removeDuplicatesStr(s));
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
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

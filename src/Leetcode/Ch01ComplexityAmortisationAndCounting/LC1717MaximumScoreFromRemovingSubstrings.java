package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.Stack;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1717. Maximum Score From Removing Substrings   [Medium]
 * https://leetcode.com/problems/maximum-score-from-removing-substrings/
 * Topics: String, Stack, Greedy
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #8
 *
 * PROBLEM
 *   You get a string s and two scores x and y. One move deletes a substring "ab" and earns
 *   x points, or deletes a substring "ba" and earns y points; the letters on both sides then
 *   close up and may form new pairs. Make as many moves as you like, in any order, and
 *   return the largest total score you can collect.
 *
 * EXAMPLES
 *   s = "cdbcbbaaabab",   x = 4, y = 5  ->  19    "ba" is pricier: three "ba" (15) + one "ab" (4)
 *   s = "aabbaaxybbaabb", x = 5, y = 4  ->  20    "ab" is pricier: four "ab" (20), no "ba" left
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   1 <= x, y <= 10^4,   s consists of lowercase English letters
 *
 * INTUITION
 *   "ab" and "ba" overlap (in "aba" only one of them can be taken), so they compete for the
 *   same letters, and the pricier pair should win every fight: first remove every pricier pair
 *   that can be formed, then remove the cheaper pair from what is left. (The LeetCode hint
 *   says the same: take one type of substring completely before the other.) Letters other
 *   than a and b are never removed, so they act as walls that no pair can cross.
 *   One pass is a stack sweep: push each letter, but when the new letter completes the wanted
 *   pair with the top of the stack, pop the top instead and add the score. Popping exposes the
 *   older letter, so chains like "aabb" disappear completely.
 *   For the second pass the code reuses the same helper on the REVERSED leftovers: a "ba"
 *   in the leftovers reads "ab" backwards. Popping the stack into a StringBuilder already
 *   produces exactly that reversed text.
 *
 * APPROACH (as implemented below)
 *   1. ans is a static running total (see STATUS - it is never reset).
 *   2. If x >= y, "ab" is the pricier pair:
 *        c   = abHalfSum(s, x)      pass 1: every "ab" popped adds x to ans; c = leftovers
 *        str = c popped into a StringBuilder (leftovers in reverse order)
 *        abHalfSum(str, y)          pass 2: an "ab" in the reversed text is a "ba" in s
 *   3. Otherwise "ba" is pricier: the mirror image, baHalfSum(s, y) first, then
 *      baHalfSum(reversed leftovers, x).
 *   4. Return ans.
 *
 * DRY RUN   s = "aabbaaxybbaabb", x = 5, y = 4 (the input main() runs; x >= y)
 *   pass 1, abHalfSum(s, 5):
 *     a a            -> stack [a, a]
 *     b, b           -> each pops an a: ans = 5, then 10; stack []
 *     a a x y b b    -> all pushed ('b' only pairs with an 'a' on TOP; here y, then b, is)
 *     a a            -> pushed: stack [a, a, x, y, b, b, a, a]
 *     b, b           -> each pops an a: ans = 15, then 20; stack [a, a, x, y, b, b]
 *   popped into str: "bbyxaa"
 *   pass 2, abHalfSum("bbyxaa", 4): no 'a' is ever followed by 'b' -> nothing removed
 *   return 20
 *   A tiny case for pass 2: s = "abba", x = 5, y = 4. Pass 1 removes the first "ab" (5) and
 *   leaves "ba"; reversed that is "ab", which pass 2 removes for 4 -> 9.
 *
 * COMPLEXITY
 *   Time : O(n) - each pass is one sweep with O(1) stack work per letter, plus one copy of the
 *          leftovers into the StringBuilder
 *   Space: O(n) - the stack and the StringBuilder
 *
 * GOTCHAS
 *   - Only the FIRST call in a run is correct, because ans is static (see STATUS).
 *   - x == y takes the first branch; with equal prices the order does not matter.
 *   - The total fits in an int: at most n / 2 = 50000 removals of 10^4 points each = 5 * 10^8.
 *   - str.peek().equals('a') compares a boxed Character with the boxed 'a'; the new letter ch
 *     is a plain char, so ch == 'b' is a primitive comparison.
 *
 * STATUS
 *   Known issue: the static field `ans` is never reset, so maximumGain() is only right on
 *   the first call in a JVM run. Every later call returns its own score PLUS the totals of
 *   all earlier calls: maximumGain("cdbcbbaaabab", 4, 5) returns 19, then 38, then 57
 *   (correct answer: 19 each time). main() calls it once, so it prints the right 20.
 *   The code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 1047 Remove All Adjacent Duplicates In String (same chapter) - the same stack sweep,
 *     cancelling equal neighbours instead of the pair "ab" / "ba".
 * </pre>
 */
public class LC1717MaximumScoreFromRemovingSubstrings {
    public static void main(String[] args) {
       // Example 2: "ab" (x = 5) is pricier than "ba" (y = 4). Prints 20 (it is the only call)
       String s = "aabbaaxybbaabb";
       int x = 5;
       int y = 4;
        System.out.println(maximumGain(s, x, y));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // running total of points; never reset, so only the first call per run is right (STATUS)
    static int ans = 0;
    // Greedy: remove the pricier pair first, then the cheaper pair from the leftovers
    public static int maximumGain(String s, int x, int y) {
       if(x >= y){
           // pass 1: remove every "ab" for x points; c holds the leftover letters
           Stack<Character> c = abHalfSum(s, x);
           // popping reverses the leftovers, so a "ba" of the original reads "ab" in str
           StringBuilder str = new StringBuilder();
           while(!c.isEmpty()){
               str.append(c.pop());
           }
           // pass 2: the "ab" pairs of the reversed text are the "ba" pairs of s: y points each
           abHalfSum(str.toString(), y);
       }else {
           // mirror image: "ba" is pricier, remove it first (y points), keep the leftovers
           Stack<Character> c = baHalfSum(s, y);
           // reverse the leftovers: now their "ab" pairs read "ba"
           StringBuilder str = new StringBuilder();
           while(!c.isEmpty()){
               str.append(c.pop());
           }
           // pass 2: the "ba" pairs of the reversed text are the "ab" pairs of s: x points each
           baHalfSum(str.toString(), x);
       }
       return ans;
    }

    // One stack sweep: removes every "ab" (adds score to ans each time) and returns the leftovers
    public static Stack<Character> abHalfSum(String s, int score){
        Stack<Character> str = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 'a' on top and 'b' arriving -> the pair "ab" is removed and scored
            if(!str.isEmpty() && str.peek().equals('a') && ch == 'b'){
                str.pop();
                ans += score;
                continue;
            }
            str.push(s.charAt(i));
        }
        return str;
    }

    // Same sweep for "ba": removes every "ba" (adds score to ans each time), returns the leftovers
    public static Stack<Character> baHalfSum(String s, int score){
        Stack<Character> str = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 'b' on top and 'a' arriving -> the pair "ba" is removed and scored
            if(!str.isEmpty() && str.peek().equals('b') && ch == 'a'){
                str.pop();
                ans += score;
                continue;
            }
            str.push(s.charAt(i));
        }
        return str;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1717. Maximum Score From Removing Substrings)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases; expected values from the statement, from the
    // reasoning noted per case, and (checked offline) a memoised brute force over all removal orders.
    // The solution keeps its running total in a static field that it never resets, so every case
    // resets that field first (it is package-private) - except the last case, which calls the
    // method twice without a reset on purpose.
    //   Expected result: 6/7 passed
    //   The 1 failure is the known issue described in the STATUS of
    //   LC1717MaximumScoreFromRemovingSubstrings.java (the second call returns 38, not 19).
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: x=4, y=5", 19, () -> gain("cdbcbbaaabab", 4, 5));
        check("example 2: x=5, y=4", 20, () -> gain("aabbaaxybbaabb", 5, 4));
        // edge: a single letter, nothing to remove
        check("single letter", 0, () -> gain("a", 1, 1));
        // edge: in "aba" only one pair can go - the pricier one must be taken (5, not 4)
        check("aba, ab is pricier", 5, () -> gain("aba", 5, 4));
        check("aba, ba is pricier", 5, () -> gain("aba", 4, 5));
        // edge: the largest input - 50000 removals of 10^4 points = 5 * 10^8, fits in an int
        StringBuilder big = new StringBuilder();
        for (int i = 0; i < 50000; i++) big.append("ab");
        String bigString = big.toString();
        check("10^5 letters, x = y = 10^4", 500000000, () -> gain(bigString, 10000, 10000));
        // known issue: no reset between two calls, so the second one adds to the first result
        check("known issue: same input called twice", 19, () -> {
            LC1717MaximumScoreFromRemovingSubstrings.ans = 0;
            LC1717MaximumScoreFromRemovingSubstrings.maximumGain("cdbcbbaaabab", 4, 5);
            return LC1717MaximumScoreFromRemovingSubstrings.maximumGain("cdbcbbaaabab", 4, 5);
        });
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // starts from a clean total, because the solution never resets its static field
    private static int gain(String s, int x, int y) {
        LC1717MaximumScoreFromRemovingSubstrings.ans = 0;
        return LC1717MaximumScoreFromRemovingSubstrings.maximumGain(s, x, y);
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

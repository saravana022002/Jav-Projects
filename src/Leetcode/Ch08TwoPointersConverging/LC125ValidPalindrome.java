package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 125. Valid Palindrome   [Easy]
 * https://leetcode.com/problems/valid-palindrome/
 * Topics: Two Pointers, String
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #2
 *       also: ch. 24 Palindromes and expand-around-centre (#1)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Turn every upper-case letter of s into lower case and throw away every character that is
 *   not a letter or a digit. If what remains reads the same forwards and backwards, s counts
 *   as a palindrome (an empty remainder does too). Return true for such a string, else false.
 *
 * EXAMPLES
 *   s = "A man, a plan, a canal: Panama"  ->  true     cleaned: "amanaplanacanalpanama"
 *   s = "race a car"                      ->  false    cleaned: "raceacar"
 *   s = " "                               ->  true     nothing is left after cleaning
 *
 * CONSTRAINTS
 *   1 <= s.length <= 2 * 10^5,   s consists only of printable ASCII characters
 * </pre>
 */
public class LC125ValidPalindrome {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static boolean isPalindromeA(String s) {
        // TODO: write your solution
        StringBuilder stringBuilder = new StringBuilder();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if(Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i))) {
                stringBuilder.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        String comp =  stringBuilder.toString();
        int low = 0;
        int high = comp.length() - 1;
        while (low < high){
            if(comp.charAt(low) != comp.charAt(high)){
                return false;
            }else {
                low++;
                high--;
            }
        }
        return true;
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 8 Two pointers, converging (this chapter)
     */
    public static boolean isPalindromeB(String s) {
        // TODO: write your solution
        int low = 0;
        int high = s.length() - 1;
        while (low < high){
            while (low < high && !Character.isLetterOrDigit(s.charAt(low))) low++;
            while (low < high && !Character.isLetterOrDigit(s.charAt(high))) high--;
            if(Character.toLowerCase(s.charAt(low)) != Character.toLowerCase(s.charAt(high))){
                return false;
            }else {
                low++;
                high--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC125ValidPalindrome::isPalindromeA);
        runAll("B", LC125ValidPalindrome::isPalindromeB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 125. Valid Palindrome)
    // https://leetcode.com/problems/valid-palindrome/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a clean-and-compare reference.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", true, () -> s.solve("A man, a plan, a canal: Panama"));
        check(v + " example 2", false, () -> s.solve("race a car"));
        check(v + " example 3", true, () -> s.solve(" "));
        // edge: a digit and a letter whose character codes differ by exactly 32
        check(v + " digit vs letter", false, () -> s.solve("0P"));
        // edge: no letters or digits at all, more than one character
        check(v + " only punctuation", true, () -> s.solve(".,"));
        // edge: an underscore is not a letter or digit
        check(v + " underscore", true, () -> s.solve("ab_a"));
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

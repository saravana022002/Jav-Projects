package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 409. Longest Palindrome   [Easy]
 * https://leetcode.com/problems/longest-palindrome/
 * Topics: Hash Table, String, Greedy
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #3
 *
 * PROBLEM
 *   You get a string s of upper- and lowercase letters. Using some or all of its letters
 *   (each letter at most as often as it occurs in s), build the longest palindrome you can
 *   and return its length. Case counts: 'A' and 'a' are different letters, so "Aa" is not
 *   a palindrome.
 *
 * EXAMPLES
 *   s = "abccccdd"  ->  7    e.g. "dccaccd": two d, four c and the single a in the middle
 *   s = "a"         ->  1    the single letter is a palindrome
 *
 * CONSTRAINTS
 *   1 <= s.length <= 2000,   s consists of lowercase and/or uppercase English letters only
 *
 * INTUITION
 *   A palindrome is mirrored around its centre: the letter k places from the left end equals
 *   the letter k places from the right end. So letters come in PAIRS, one on each side - with
 *   one possible exception, the single character in the exact middle. Hence: use every pair
 *   there is (a letter that occurs c times gives (c / 2) * 2 letters in pairs), and if some
 *   letter has a spare copy left over (an odd count), put ONE spare in the middle for +1.
 *   There is only one middle, so several odd counts still add just 1.
 *
 * APPROACH (as implemented below)
 *   1. max = new int[256]: despite its name, max[letter - 'A'] counts how often the letter
 *      occurs ('A'..'Z' land in slots 0..25, 'a'..'z' in 32..57). hasOdd = false, ans = 0.
 *   2. One pass over s: max[s.charAt(i) - 'A']++.
 *   3. For every count j: ans += (j / 2) * 2 (all full pairs); if j is odd, hasOdd = true.
 *   4. If hasOdd, ans += 1 - one spare letter goes in the middle.
 *   5. Return ans.
 *
 * DRY RUN   s = "abccccdd"   (what main() runs)
 *   counts: a=1, b=1, c=4, d=2
 *   a: no pair, odd count -> hasOdd = true      ans = 0
 *   b: no pair, odd count                       ans = 0
 *   c: two pairs = 4 letters                    ans = 4
 *   d: one pair = 2 letters                     ans = 6
 *   hasOdd is true -> ans = 7
 *
 * COMPLEXITY
 *   Time : O(n) - one counting pass over s plus a loop over the fixed 256-slot table
 *   Space: O(1) - the 256-slot table (only 58 slots can be reached by letters)
 *
 * GOTCHAS
 *   - One middle only: "abccccdd" has two letters with an odd count (a and b) but the answer
 *     is 6 + 1, not 6 + 2.
 *   - An odd count still contributes its pairs: a letter occurring 5 times gives 4 letters in
 *     pairs, and its fifth copy may become the middle.
 *   - Case matters: 'A' and 'a' use different slots, so "Aa" gives 1, not 2.
 *   - The table is indexed with letter - 'A', so only letters work. A digit or a space would
 *     give a negative index and throw ArrayIndexOutOfBoundsException.
 *
 * STATUS
 *   Correct (checked against a brute force over all sub-multisets on random short strings).
 *   main() calls longestPalindrome() but does not print the result (it would be 7).
 *
 * RELATED
 *   - LC 266 Palindrome Permutation - can ALL the letters be rearranged into a palindrome?
 *     Same parity idea: at most one letter may have an odd count.
 *   - LC 2131 Longest Palindrome by Concatenating Two Letter Words - the same "pairs plus
 *     one optional middle" idea, with two-letter words instead of single letters.
 * </pre>
 */
public class LC409LongestPalindrome {
    public static void main(String[] args) {
        // Example 1 - the result is not printed (longestPalindrome returns 7)
        String s = "abccccdd";
        longestPalindrome(s);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Takes every pair of equal letters, then adds 1 if any letter has a spare copy for the middle.
    public static int longestPalindrome(String s) {
        // despite the name: max[letter - 'A'] = how many times the letter occurs in s
        int[] max = new int[256];
        boolean hasOdd = false;
        int ans = 0;
        // pass 1: count the letters
        for (int i = 0; i < s.length(); i++) {
            max[s.charAt(i) - 'A']++;
        }

        // pass 2: each letter contributes its full pairs; an odd count leaves one spare copy
        for (int j : max) {
            ans += (j / 2) * 2;
            if(j%2 == 1) {
                hasOdd = true;
            }
        }

        // a single spare letter fits in the middle (one, however many letters have a spare)
        if(hasOdd) {
            ans += 1;
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 409. Longest Palindrome)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (case sensitivity, all counts even, all counts
    // odd, two letters with odd counts, the length limit 2000). Expected values come from the
    // statement or from a brute force over all sub-multisets of the letters, done outside the
    // solution; the two 2000-letter cases follow directly from the definition.
    //   Expected result: 9/9 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: abccccdd", 7, () -> LC409LongestPalindrome.longestPalindrome("abccccdd"));
        check("example 2: a", 1, () -> LC409LongestPalindrome.longestPalindrome("a"));
        // edge: upper and lower case are different letters
        check("case matters: Aa", 1, () -> LC409LongestPalindrome.longestPalindrome("Aa"));
        // edge: every count is even - the whole string can be used
        check("all counts even: aaaa", 4, () -> LC409LongestPalindrome.longestPalindrome("aaaa"));
        // edge: every count is odd - pairs are impossible, only one middle letter
        check("all counts odd: abc", 1, () -> LC409LongestPalindrome.longestPalindrome("abc"));
        // edge: two letters with an odd count - 2 pairs plus ONE middle letter
        check("two odd counts: aaabbb", 5,
                () -> LC409LongestPalindrome.longestPalindrome("aaabbb"));
        check("mixed case, odd counts: zzzZZ", 5,
                () -> LC409LongestPalindrome.longestPalindrome("zzzZZ"));
        // edge: the length limit 2000
        check("2000 equal letters", 2000,
                () -> LC409LongestPalindrome.longestPalindrome("z".repeat(2000)));
        // 1999 z + one A: two odd counts, so the A cannot be used (1998 in pairs + 1 middle z)
        check("1999 z and one A", 1999,
                () -> LC409LongestPalindrome.longestPalindrome("z".repeat(1999) + "A"));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

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

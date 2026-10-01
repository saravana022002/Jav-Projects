package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 76. Minimum Window Substring   [Hard]
 * https://leetcode.com/problems/minimum-window-substring/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #11
 *       also: ch. 9 Sliding window, chasing (#8)
 *
 * PROBLEM
 *   Given two strings s and t (lengths m and n), find the shortest substring of s that
 *   contains every character of t, duplicates included: if t has two a's, the window needs
 *   two a's. Return that window, or "" when no window works. The tests are built so that
 *   the answer is unique.
 *
 * EXAMPLES
 *   s = "ADOBECODEBANC", t = "ABC"  ->  "BANC"   the shortest piece with an A, a B and a C
 *   s = "a",            t = "a"    ->  "a"      the whole of s is the window
 *   s = "a",            t = "aa"   ->  ""       t needs two a's, s has only one
 *
 * CONSTRAINTS
 *   m == s.length,  n == t.length,  1 <= m, n <= 10^5
 *   s and t consist of uppercase and lowercase English letters
 *   Follow-up: can it be done in O(m + n) time?
 *
 * INTUITION
 *   A window s[i..j] "covers" t when it holds at least as many copies of every letter as t
 *   does. Growing j can only help, dropping letters on the left (growing i) can only hurt.
 *   So for every right end j the interesting window is the one whose left end is pushed as
 *   far right as still covers t. The code scans j from left to right and, for each j, moves
 *   i right while the window still covers t, remembering the shortest covering window it
 *   meets. Both edges only move forward, so there are at most about 2 * m window tests.
 *   What makes this version slow is the test itself: isContains() recounts the whole window
 *   every time, instead of updating the counts when one letter enters and one leaves and
 *   keeping a counter of satisfied letters (the trick of LC 567 / LC 438 in this folder).
 *
 * APPROACH (as implemented below)
 *   1. i = left edge of the window, min = length of the best window so far
 *      (Integer.MAX_VALUE = none yet), ans = that window ("" at first).
 *      tally and required in minWindow are never read - see STATUS.
 *   2. For each right edge j, the window is s[i..j].
 *   3. While isContains(s.substring(i, j + 1), t) says the window still covers t: if it is
 *      shorter than min, store it in min / ans; then i++ (drop the leftmost letter, test again).
 *   4. Return ans - "" if no window ever covered t.
 *   isContains(sub, t): false at once when t is longer than sub; otherwise tally[c] counts the
 *   letters of sub, then each letter of t subtracts 1; a count below 0 means sub has too few
 *   of that letter -> false, otherwise true. Duplicates in t are honoured: each occurrence
 *   subtracts its own 1.
 *
 * DRY RUN   s = "ADOBECODEBANC", t = "ABC"   (what main() runs; "covers" = isContains is true)
 *   j=0..4   "A" .. "ADOBE" do not cover t (no C yet)
 *   j=5 'C'  "ADOBEC" covers -> best "ADOBEC" (6); i=1: "DOBEC" does not cover -> stop
 *   j=6..9   "DOBECO" .. "DOBECODEB" do not cover (no A)
 *   j=10 'A' "DOBECODEBA" covers (10, no better); i moves on while it still covers:
 *            "OBECODEBA", "BECODEBA", "ECODEBA", "CODEBA" cover (none shorter than 6);
 *            "ODEBA" does not -> stop with i = 6
 *   j=11 'N' "ODEBAN" does not cover (no C)
 *   j=12 'C' "ODEBANC" covers (7), "DEBANC" covers (6), "EBANC" covers -> best "EBANC" (5),
 *            "BANC" covers -> best "BANC" (4), "ANC" does not (no B) -> stop
 *   return "BANC"    (23 calls of isContains for these 13 letters)
 *
 * COMPLEXITY   (m = s.length(), n = t.length())
 *   Time : O(m * (m + n)) in the worst case - at most about 2 * m window tests, and each one
 *          copies the window with substring() and recounts it in isContains(), O(window + n)
 *   Space: O(m) - the temporary substring copies and the answer; the 128-slot tables are
 *          constant
 *
 * GOTCHAS
 *   - Duplicates in t matter: t = "aa" needs two a's, not just the letter a (example 3).
 *   - Case matters: 'a' and 'A' are different letters. The tables are indexed by character
 *     code, so only ASCII works.
 *   - The comparison min > j - i + 1 is strict: of two equally short windows the leftmost is
 *     kept (the problem guarantees a unique answer anyway).
 *   - i can never run past j + 1: substring(j + 1, j + 1) is "", and isContains("", t) is
 *     false because t is not empty.
 *
 * STATUS
 *   Known issue (speed, not correctness): every result was right in my checks (about 22000
 *   random strings against a brute force), but the method is quadratic. s = "A" * 99998 + "BC",
 *   t = "ABC" (m = 10^5, the maximum) took about 14 seconds in my run; 10^4 letters took 0.17 s
 *   and the time grows about 4x per doubling. The follow-up asks for O(m + n). The array
 *   tally and the variable required in minWindow are never used - they look like the start
 *   of that faster version. The code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 567 Permutation in String - the incremental version of this window test: counts
 *     updated as letters enter and leave, plus a counter of matching letters; solved in
 *     LC567PermutationInString.java in this folder.
 *   - LC 209 Minimum Size Subarray Sum - the same "shrink while the window is still valid"
 *     two-pointer pattern, on numbers.
 * </pre>
 */
public class LC76MinimumWindowSubstring {
    public static void main(String[] args) {
        // Example 1 - prints BANC, the shortest piece that holds an A, a B and a C
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Two-pointer window s[i..j]: for every right end j drop letters on the left while the
    // window still covers t, remembering the shortest covering window. Correct but slow:
    // every test recounts the window, see STATUS
    public static String minWindow(String s, String t) {
        // i = left edge of the window, min = length of the best window so far, ans = that window
        int i = 0;
        int min = Integer.MAX_VALUE;
        String ans = "";

        // tally and required are never read - they look like the start of an O(m + n) version
        int[] tally = new int[128];
        int required = 0; // How many distinct characters we need to satisfy
        for(int j = 0; j < s.length(); j++){
            tally[s.charAt(i)]++;
            // the window s[i..j] still covers t: keep it if it is the shortest so far, then
            // drop its leftmost letter and test again
            while (isContains(s.substring(i, j + 1) , t)){
                if(min > j - i + 1){
                    min = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
                i++;
            }
        }
        // "" if no window ever covered t
        return ans;
    }


    // True if sub holds every letter of t, duplicates included: count the letters of sub, then
    // use up one count per letter of t. O(sub.length() + t.length())
    public static boolean isContains(String sub, String t) {
        // a shorter window cannot hold all of t
        if (t.length() > sub.length()) {
            return false;
        }
        // tally[c] = how many times character c occurs in sub
        int[] tally = new int[128];
        for (int i = 0; i < sub.length(); i++) {
            tally[sub.charAt(i)]++;
        }

        // a count that drops below 0 means sub has too few of that letter
        for (int i = 0; i < t.length(); i++) {
            int charIndex = t.charAt(i);
            tally[charIndex]--;
            if(tally[charIndex] < 0){
                return false;
            }
        }
        return true;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 76. Minimum Window Substring)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (a letter of t missing from s, t longer than s,
    // s equal to t, a duplicate letter in t, case sensitivity, the window at the very end, 10^4
    // letters). Every case has a unique shortest window, as the problem promises; expected values
    // come from the statement or from a brute force over all substrings, done outside the
    // solution. Only moderate sizes are used: the solution re-counts the window at every step,
    // so the largest inputs (10^5 letters) take seconds - see its STATUS.
    //   Expected result: 11/11 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: ADOBECODEBANC / ABC", "BANC",
                () -> LC76MinimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
        check("example 2: a / a", "a", () -> LC76MinimumWindowSubstring.minWindow("a", "a"));
        check("example 3: a / aa", "", () -> LC76MinimumWindowSubstring.minWindow("a", "aa"));
        // edge: a letter of t does not occur in s at all
        check("letter missing from s: ABC / D", "",
                () -> LC76MinimumWindowSubstring.minWindow("ABC", "D"));
        // edge: t is longer than s
        check("t longer than s: a / ab", "", () -> LC76MinimumWindowSubstring.minWindow("a", "ab"));
        // edge: the whole of s is the window
        check("s equals t: ab / ab", "ab", () -> LC76MinimumWindowSubstring.minWindow("ab", "ab"));
        // edge: t has a duplicate letter, the window sits in the middle of s
        check("duplicate letter in t: bbaac / aac", "aac",
                () -> LC76MinimumWindowSubstring.minWindow("bbaac", "aac"));
        // edge: upper and lower case are different letters
        check("case matters: aAbB / Ab", "Ab",
                () -> LC76MinimumWindowSubstring.minWindow("aAbB", "Ab"));
        // edge: the shortest window is at the very end
        check("window at the end: xyzzzab / ba", "ab",
                () -> LC76MinimumWindowSubstring.minWindow("xyzzzab", "ba"));
        // edge: a long run of one letter before the only B
        check("long run of one letter: aaaaaaab / ab", "ab",
                () -> LC76MinimumWindowSubstring.minWindow("aaaaaaab", "ab"));
        // edge: 10^4 letters, the only window with a B and a C is at the end
        check("10^4 letters: A...ABC / ABC", "ABC",
                () -> LC76MinimumWindowSubstring.minWindow("A".repeat(9998) + "BC", "ABC"));
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

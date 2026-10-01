package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 438. Find All Anagrams in a String   [Medium]
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #8
 *       also: ch. 9 Sliding window, chasing (#3)
 *
 * PROBLEM
 *   Given two strings s and p, return every index where an anagram of p starts inside s,
 *   i.e. every start i for which the piece s[i .. i + p.length() - 1] is a rearrangement of
 *   p (same letters, same counts). The indices may be returned in any order.
 *
 * EXAMPLES
 *   s = "cbaebabacd", p = "abc"  ->  [0,6]      "cba" at 0 and "bac" at 6 both use a, b, c once
 *   s = "abab",       p = "ab"   ->  [0,1,2]    "ab" at 0, "ba" at 1, "ab" at 2 (windows overlap)
 *
 * CONSTRAINTS
 *   1 <= s.length, p.length <= 3 * 10^4,   s and p consist of lowercase English letters
 *
 * INTUITION
 *   An anagram of p is a piece of s that is exactly p.length() long and has p's letter
 *   counts. So slide a window of that fixed length over s and ask at every position: "do the
 *   window's letter counts equal p's?" Comparing all 26 counts each time costs 26 steps per
 *   position. Instead keep `matching` = how many of the 26 letters currently have equal
 *   counts in the window and in p. Sliding by one step changes just two counts (one letter
 *   enters, one leaves), so matching can be repaired in O(1); matching == 26 means "every
 *   count agrees" - this window is an anagram.
 *
 * APPROACHES IN THIS FILE
 *   A. findAnagrams()    - brute force: take every window with substring() and compare its
 *                          letter counts with p in isAnagram() - O(n * m)
 *   B. findAnagramsOpt() - sliding window with need[] / have[] and the matching counter
 *                          - O(n + m)     <- main() runs this one
 *   (n = s.length(), m = p.length().)
 *
 * APPROACH (as implemented below, method B - findAnagramsOpt)
 *   1. If s is shorter than p return null (a flaw - see STATUS; the right answer is []).
 *   2. need[c] = how often letter c occurs in p. have[c] = the same for the current window,
 *      all 0 at the start.
 *   3. matching = the number of letters with need[c] == 0 (they equal have[c] == 0 already).
 *   4. For each i, the right end of the window:
 *      a. in = s[i] enters. If need[in] == have[in] before the change, matching-- (the count is
 *         about to stop matching); have[in]++; if they are equal after it, matching++.
 *      b. Once i >= p.length() the window is one letter too long, so out = s[i - p.length()]
 *         leaves: the same two checks around have[out]--.
 *      c. matching == 26 -> the window s[i - p.length() + 1 .. i] is an anagram:
 *         ans.add(i - p.length() + 1).
 *   5. Return ans.
 *
 * DRY RUN   s = "abab", p = "ab"   (what main() runs)
 *   start      need: a=1, b=1;  matching = 24 (the other 24 letters have 0 == 0)
 *   i=0 'a' in                                          matching 25   (a: 0 -> 1 = need)
 *   i=1 'b' in                                          matching 26   ans = [0]   "ab"
 *   i=2 'a' in  (a: 1 -> 2 breaks)       matching 25;
 *       s[0]='a' out (a: 2 -> 1 equal again)            matching 26   ans = [0, 1]   "ba"
 *   i=3 'b' in  (b: 1 -> 2 breaks)       matching 25;
 *       s[1]='b' out (b: 2 -> 1 equal again)            matching 26   ans = [0, 1, 2]   "ab"
 *   return [0, 1, 2]
 *
 * COMPLEXITY
 *   A: Time O(n * m) - about n - m + 1 windows, each costs O(m) for substring() and O(m + 26)
 *      for isAnagram().  Space O(m) - the window copy and a 26-slot table
 *   B: Time O(n + m) - counting p is O(m), then n steps with O(1) work each.  Space O(1) -
 *      two 26-slot tables (plus the answer list)
 *
 * GOTCHAS
 *   - An anagram window is exactly p.length() long; that is why a letter only leaves once
 *     i >= p.length().
 *   - matching counts all 26 letters, including the ones that are not in p, so the target is
 *     26 and not "the number of distinct letters of p".
 *   - Every count change needs both checks (equal before -> matching--, equal after ->
 *     matching++), because a count can move out of a match or into one. They also keep it
 *     correct when the entering and the leaving letter are the same.
 *   - The start indices come out in increasing order; any order would be accepted.
 *   - In isAnagram() the parameter names are swapped (charp is the window cut from s, window
 *     is p). It does not matter: only the letter counts are compared.
 *
 * STATUS
 *   Known issue in findAnagramsOpt (method B, the one main() runs): when s is shorter than p
 *   (a valid input, e.g. s = "a", p = "ab") it returns null instead of an empty list, so a
 *   caller printing it sees "null" and a caller looping over it gets a NullPointerException;
 *   the correct answer is []. findAnagrams (method A) returns [] there. For every other
 *   input both methods are correct (checked against a brute force on random strings). The
 *   code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 242 Valid Anagram - the one-pair version of the same test; solved in
 *     LC242ValidAnagram.java in this folder.
 *   - LC 567 Permutation in String - the same fixed-size window and matching counter, but it
 *     stops at the first match; solved in LC567PermutationInString.java in this folder.
 * </pre>
 */
public class LC438FindAllAnagramsInAString {
    public static void main(String[] args) {
        // Example 2 (method B) - prints [0, 1, 2]
        String s = "abab", p = "ab";
        System.out.println(findAnagramsOpt(s, p));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A (brute force): cut every window of length p.length() out of s and compare
    // its letter counts with p. O(n * m)
    public static List<Integer> findAnagrams(String s, String p) {
        // l = start of the window s[l .. i - 1]; it is p.length() long
        int l = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = p.length(); i <= s.length(); i++) {
            String temp = s.substring(l, i);
            if (isAnagram(temp, p)) {
                ans.add(l);
            }
            l++;
        }
        return ans;
    }

    // Helper of approach A: counts the letters of the first string up and of the second down;
    // anagrams of equal length cancel out to all zeros. (Parameter names are swapped: charp is
    // the window from s, window is p - harmless, only the counts matter.)
    public static boolean isAnagram(String charp, String window){
        int[] and = new int[26];
        for (int i = 0; i < charp.length(); i++) {
            and[charp.charAt(i) - 'a']++;
            and[window.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(and[i] != 0){
                return false;
            }
        }
        return true;
    }


    // Approach B: slide a window of length p.length() over s and keep `matching` = how many of
    // the 26 letters have equal counts in the window and in p. O(n + m)
    public static List<Integer> findAnagramsOpt(String s, String p) {
        // KNOWN ISSUE: a shorter s has no window and the right answer is [], not null (see STATUS)
        if (s.length() < p.length()){
            return null;
        }
        // need[c] = count of letter c in p; have[c] = count of letter c in the current window
        int[] need = new int[26];
        int[] have = new int[26];
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }

        // letters that p does not contain already match (0 == 0)
        int matching = 0;
        for (int i = 0; i < 26; i++) {
            if(need[i] == 0){
                matching++;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            // letter entering on the right: if its count matched before, it stops matching;
            // after the increment it may match again
            int in = s.charAt(i) - 'a';
            if(need[in] == have[in]){
                matching--;
            }
            have[in]++;
            if(need[in] == have[in]){
                matching++;
            }

            // the window is now one letter too long: the letter at i - p.length() leaves
            // (the same two checks around the decrement)
            if(i >= p.length()){
                int out = s.charAt(i - p.length()) - 'a';
                if(need[out] == have[out]){
                    matching--;
                }
                have[out]--;
                if(need[out] == have[out]){
                    matching++;
                }
            }
            // all 26 counts agree: the window ending at i is an anagram, record its start
            if(matching == 26){
                ans.add(i - p.length() + 1);
            }
        }
        return ans;

    }

    // ================================ TESTS =================================
    // Tests for this class (LC 438. Find All Anagrams in a String)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (p longer than s, no anagram, s equal to p,
    // single letters, overlapping windows, an anagram at the very end, a duplicate letter in p,
    // s.length = 3 * 10^4). Every input is run through BOTH methods of the solution,
    // findAnagrams and findAnagramsOpt. The problem accepts any order, so each result is sorted
    // before it is compared. Expected values come from the statement or from a brute force that
    // sorts every window, done outside the solution.
    //   Expected result: 21/22 passed
    //   The failure is the known issue described in the STATUS of LC438FindAllAnagramsInAString.java:
    //   findAnagramsOpt returns null instead of [] when s is shorter than p.
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: cbaebabacd / abc", "cbaebabacd", "abc", list(0, 6));
        both("example 2: abab / ab", "abab", "ab", list(0, 1, 2));
        // edge: p is longer than s - no window fits, the answer is an empty list
        both("p longer than s: a / ab", "a", "ab", list());
        // edge: no window is an anagram
        both("no anagram: abcdef / xyz", "abcdef", "xyz", list());
        // edge: s is exactly p, and the smallest input
        both("s equals p: abc / abc", "abc", "abc", list(0));
        both("single letters: a / a", "a", "a", list(0));
        // edge: overlapping windows of a repeated letter
        both("overlapping windows: aaaa / aa", "aaaa", "aa", list(0, 1, 2));
        // edge: the only anagram sits at the very end
        both("anagram at the end: xxxcba / abc", "xxxcba", "abc", list(3));
        // edge: same length as p but not an anagram
        both("same length, not an anagram: abc / abd", "abc", "abd", list());
        // edge: p has a duplicate letter
        both("duplicate letter in p: baa / aa", "baa", "aa", list(1));
        // edge: the length limit 3 * 10^4 - every window of "abab..." is an anagram of "ab"
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i < 29999; i++) all.add(i);
        both("3 * 10^4 letters: abab... / ab", "ab".repeat(15000), "ab", all);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // run the same input through both methods of the solution
    private static void both(String name, String s, String p, List<Integer> expected) {
        check(name + "  [findAnagrams]", expected,
                () -> sorted(LC438FindAllAnagramsInAString.findAnagrams(s, p)));
        check(name + "  [findAnagramsOpt]", expected,
                () -> sorted(LC438FindAllAnagramsInAString.findAnagramsOpt(s, p)));
    }

    private static List<Integer> list(Integer... values) {
        return Arrays.asList(values);
    }

    // any order is accepted, so compare sorted copies (a null result stays null and fails)
    private static List<Integer> sorted(List<Integer> list) {
        if (list == null) return null;
        List<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;
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

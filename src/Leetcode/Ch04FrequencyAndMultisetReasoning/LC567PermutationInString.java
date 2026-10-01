package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 567. Permutation in String   [Medium]
 * https://leetcode.com/problems/permutation-in-string/
 * Topics: Hash Table, Two Pointers, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #9
 *
 * PROBLEM
 *   You get two strings, s1 and s2. Decide whether s2 contains, as a contiguous piece, some
 *   rearrangement (permutation) of s1 - the same letters with the same counts, in any order.
 *   Return true if it does and false otherwise.
 *
 * EXAMPLES
 *   s1 = "ab", s2 = "eidbaooo"  ->  true     the substring "ba" is a rearrangement of "ab"
 *   s1 = "ab", s2 = "eidboaoo"  ->  false    'a' and 'b' are never next to each other
 *
 * CONSTRAINTS
 *   1 <= s1.length, s2.length <= 10^4,   s1 and s2 consist of lowercase English letters
 *
 * INTUITION
 *   A rearrangement of s1 has exactly s1's letter counts and exactly s1's length, so the only
 *   candidates in s2 are the windows of s1.length() consecutive letters. Slide such a window
 *   along s2 and ask at every position: "do the window's letter counts equal s1's?" Comparing
 *   all 26 counts each time would cost 26 steps per position, so the code keeps `matching` =
 *   how many of the 26 letters currently have equal counts. Sliding by one step changes just
 *   two counts (one letter enters, one leaves), so matching can be repaired in O(1).
 *   matching == 26 means "this window is a rearrangement of s1".
 *
 * APPROACH (as implemented below)
 *   1. If s1 is longer than s2 no window fits -> return false.
 *   2. need[c] = how often letter c occurs in s1. have[c] = the same for the current window
 *      of s2, all 0 at the start.
 *   3. matching = the number of letters with need[c] == 0 (they equal have[c] == 0 already).
 *   4. For each i, the right end of the window:
 *      a. in = s2[i] enters. If have[in] == need[in] before the change, matching-- (the count
 *         is about to stop matching); have[in]++; if they are equal after it, matching++.
 *      b. Once i >= s1.length() the window is one letter too long, so out = s2[i - s1.length()]
 *         leaves: the same two checks around have[out]--.
 *      c. matching == 26 -> return true.
 *   5. No window matched -> return false.
 *
 * DRY RUN   s1 = "ab", s2 = "eidbaooo"   (what main() runs)
 *   start      need: a=1, b=1;  matching = 24 (the other 24 letters have 0 == 0)
 *   i=0 'e' in                                    matching 23
 *   i=1 'i' in                                    matching 22
 *   i=2 'd' in -> 21;   s2[0]='e' out             matching 22
 *   i=3 'b' in -> 23;   s2[1]='i' out             matching 24
 *   i=4 'a' in -> 25;   s2[2]='d' out             matching 26 -> return true   (window "ba")
 *
 * COMPLEXITY   (n1 = s1.length(), n2 = s2.length())
 *   Time : O(n1 + n2) - counting s1, then at most n2 steps with O(1) work each (plus a fixed
 *          26-step scan for the initial matching)
 *   Space: O(1) - two 26-slot tables
 *
 * GOTCHAS
 *   - matching counts all 26 letters, including the ones that appear in neither string, so
 *     the target is 26 and not "the number of distinct letters of s1".
 *   - Every count change needs both checks (equal before -> matching--, equal after ->
 *     matching++), because a count can move out of a match or into one. They also stay correct
 *     when the entering and the leaving letter are the same.
 *   - The early steps (i < s1.length() - 1) cannot give a false "true": matching == 26 means
 *     the window's counts equal s1's, which is only possible when the window is s1.length() long.
 *   - Indexing with ch - 'a' only works for lowercase a-z, which the constraints guarantee.
 *
 * STATUS
 *   Correct (checked against a sort-every-window brute force on random strings).
 *
 * RELATED
 *   - LC 438 Find All Anagrams in a String - the same fixed-size window and matching counter,
 *     but it collects every start index instead of stopping at the first match; solved in
 *     LC438FindAllAnagramsInAString.java in this folder.
 *   - LC 76 Minimum Window Substring - a similar "enough of every letter" test, on a window
 *     that grows and shrinks; solved in LC76MinimumWindowSubstring.java in this folder.
 *
 * SEE ALSO
 *   src/Practice/SlidingWindow/LC567PermutationInString.java
 *       - the same matching counter, written with the first window counted up front and a
 *         while loop that slides it, testing the counter at the top of each pass and once
 *         more after the loop
 * </pre>
 */
public class LC567PermutationInString {
    public static void main(String[] args) {
        // Example 1 - prints true: the window "ba" is a rearrangement of "ab"
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Slides a window of s1.length() letters over s2; true as soon as the window's letter
    // counts equal s1's. O(n1 + n2)
    public static boolean checkInclusion(String s1, String s2) {
        // s1 cannot fit inside a shorter s2
        if(s1.length() > s2.length()){
            return false;
        }
        // need = letter counts of s1; have = letter counts of the current window of s2
        int[] need = new int[26];
        int[] have = new int[26];
        // matching = how many of the 26 letters have equal counts in need and have
        int matching = 0;
        for (int i = 0; i < s1.length(); i++) {
            need[s1.charAt(i) - 'a']++;
        }

        // letters that s1 does not contain match already (0 == 0)
        for (int i = 0; i < 26; i++) {
            if(need[i] == 0){
                matching++;
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            // letter entering on the right: if its count matched before, it stops matching;
            // after the increment it may match again
            int in = s2.charAt(i) - 'a';
            if(have[in] == need[in]){
                matching--;
            }
            have[in]++;
            if(have[in] == need[in]){
                matching++;
            }

            // the window is now one letter too long: the letter at i - s1.length() leaves
            // (the same two checks around the decrement)
            if(i >= s1.length()){
                int out = s2.charAt(i - s1.length()) - 'a';
                if(have[out] == need[out]){
                    matching--;
                }
                have[out]--;
                if(have[out] == need[out]){
                    matching++;
                }
            }
            // all 26 counts agree: the window ending at i is a rearrangement of s1
            if (matching == 26){
                return true;
            }
        }
        return false;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 567. Permutation in String)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (s1 longer than s2, s1 and s2 rearrangements of
    // each other, the match at the very end, single letters, right letters but not contiguous,
    // a duplicate letter in s1, s2.length = 10^4). Expected values come from the statement or
    // from a brute force that sorts every window, done outside the solution.
    //   Expected result: 12/12 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: ab / eidbaooo", true,
                () -> LC567PermutationInString.checkInclusion("ab", "eidbaooo"));
        check("example 2: ab / eidboaoo", false,
                () -> LC567PermutationInString.checkInclusion("ab", "eidboaoo"));
        // edge: s1 is longer than s2 - no window can fit
        check("s1 longer than s2: abc / ab", false,
                () -> LC567PermutationInString.checkInclusion("abc", "ab"));
        // edge: the whole of s2 is a rearrangement of s1
        check("s2 is a rearrangement of s1: ab / ba", true,
                () -> LC567PermutationInString.checkInclusion("ab", "ba"));
        // edge: the only match is the last window
        check("match at the very end: abc / xxxxcba", true,
                () -> LC567PermutationInString.checkInclusion("abc", "xxxxcba"));
        // edge: single letters
        check("single letters, equal: a / a", true,
                () -> LC567PermutationInString.checkInclusion("a", "a"));
        check("single letters, different: a / b", false,
                () -> LC567PermutationInString.checkInclusion("a", "b"));
        // edge: all the right letters are there, but not next to each other
        check("not contiguous: abc / abxc", false,
                () -> LC567PermutationInString.checkInclusion("abc", "abxc"));
        // edge: s1 has a duplicate letter; the window must contain it twice
        check("duplicate letter in s1: aab / bcaaab", true,
                () -> LC567PermutationInString.checkInclusion("aab", "bcaaab"));
        // edge: the length limit 10^4 - the window "ab" is at the very end / never appears
        check("10^4 letters, match at the end", true,
                () -> LC567PermutationInString.checkInclusion("ab", "a".repeat(9999) + "b"));
        check("10^4 letters, no match", false,
                () -> LC567PermutationInString.checkInclusion("ab", "a".repeat(10000)));
        // edge: the letters of s1 are all in s2, but only one copy of a doubled letter
        check("one copy of a doubled letter: aa / abab", false,
                () -> LC567PermutationInString.checkInclusion("aa", "abab"));
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

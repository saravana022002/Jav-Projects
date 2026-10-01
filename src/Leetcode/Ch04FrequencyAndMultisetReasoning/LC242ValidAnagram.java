package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 242. Valid Anagram   [Easy]
 * https://leetcode.com/problems/valid-anagram/
 * Topics: Hash Table, String, Sorting
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #1
 *
 * PROBLEM
 *   You get two strings, s and t. Return true when t uses exactly the same letters as s, each
 *   letter the same number of times, only possibly in a different order (a string is also an
 *   anagram of itself); otherwise return false.
 *
 * EXAMPLES
 *   s = "anagram", t = "nagaram"  ->  true     the same letters on both sides: a three times,
 *                                              n, g, r and m once each
 *   s = "rat",     t = "car"      ->  false    t has a 'c' where s has a 't'
 *
 * CONSTRAINTS
 *   1 <= s.length, t.length <= 5 * 10^4,   s and t consist of lowercase English letters
 *   Follow-up: what if the inputs contain Unicode characters? (The 26-slot table below would
 *   break for them; counts keyed by character in a HashMap would be needed.)
 *
 * INTUITION
 *   Two strings are anagrams exactly when every letter occurs equally often in both, so
 *   compare COUNTS instead of positions. One shared table of 26 counters goes UP by 1 for
 *   each letter of s and DOWN by 1 for each letter of t. If the letter counts match, every
 *   counter cancels back to 0; a non-zero counter names a letter that one string has more of.
 *
 * APPROACH (as implemented below)
 *   1. Different lengths -> return false. Anagrams always have the same length, and with
 *      equal lengths one index i is valid for both strings.
 *   2. anagram = new int[26]: one counter per letter, the slot of a letter is (letter - 'a').
 *   3. One loop over i: anagram[sChars[i] - 'a']++ and anagram[tChars[i] - 'a']--.
 *   4. Scan the 26 counters: any non-zero one -> return false, otherwise return true.
 *
 * DRY RUN   s = "anagram", t = "nagaram"   (what main() runs; + letter of s, - letter of t)
 *   i=0  +a -n   non-zero counters: a=1 n=-1
 *   i=1  +n -a   none
 *   i=2  +a -g   non-zero counters: a=1 g=-1
 *   i=3  +g -a   none
 *   i=4  +r -r   none
 *   i=5  +a -a   none
 *   i=6  +m -m   none
 *   the final scan finds 26 zeros -> return true
 *   (With "rat" / "car" the counters c=-1 and t=1 are left over -> return false.)
 *
 * COMPLEXITY
 *   Time : O(n) - the length check is O(1), then one pass over the n characters and a fixed
 *          26-slot scan (n = s.length() = t.length() once the lengths are known to be equal)
 *   Space: O(1) for the 26 counters; toCharArray() also copies both strings, O(n)
 *
 * GOTCHAS
 *   - The length check is what makes the single loop safe: both char arrays have the same
 *     length, so index i exists in each.
 *   - Counters may go negative along the way; only the final state matters.
 *   - Only 'a'..'z' work. Any other character (e.g. 'A') gives a slot outside 0..25 and
 *     throws ArrayIndexOutOfBoundsException - that is the Unicode follow-up in a nutshell.
 *
 * STATUS
 *   Correct for the lowercase input the problem promises. main() calls isAnagram() but does
 *   not print the result (it would be true).
 *
 * RELATED
 *   - LC 49 Group Anagrams - group words that are anagrams of each other; solved in
 *     LC49GroupAnagrams.java in this folder.
 *   - LC 438 Find All Anagrams in a String - the same letter counting on a sliding window;
 *     solved in LC438FindAllAnagramsInAString.java in this folder.
 *
 * SEE ALSO
 *   src/Practice/HashMap/LC242ValidAnagramV1.java - same int[26] counting, but no length check
 *     and two separate loops (s up, then t down)
 *   src/Practice/HashMap/LC242ValidAnagramV2.java - the same as this file: early length check
 *     and ONE loop doing ++ and -- together (written as a while loop)
 *   src/Practice/HashMap/LC242ValidAnagramV3.java - length check + int[128] table (any ASCII):
 *     count s, then spend one count per letter of t with an early exit
 * </pre>
 */
public class LC242ValidAnagram {
    public static void main(String[] args) {
        // Example 1 - an anagram pair; the result is not printed (isAnagram returns true)
        String s = "anagram", t = "nagaram";
        isAnagram(s, t);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Counts s's letters up and t's letters down in one table; anagrams cancel out to all zeros.
    public static boolean isAnagram(String s, String t) {
        // different lengths can never be anagrams (and one index can then walk both strings)
        if(s.length() != t.length()){
            return false;
        }
        // one counter per letter 'a'..'z'
        int[] anagram = new int[26];
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        // +1 for the letter of s and -1 for the letter of t at the same position
        for (int i = 0; i < sChars.length; i++) {
            anagram[sChars[i] - 'a']++;
            anagram[tChars[i] - 'a']--;
        }
        // any leftover count means the two strings do not have the same letters
        for (int i = 0; i < 26; i++) {
            if(anagram[i] != 0){
                return false;
            }
        }
        return true;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 242. Valid Anagram)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (different lengths, a single letter, same
    // letters with different counts, all 26 letters, the 5 * 10^4 length limit). Expected values
    // come from the statement or from comparing the sorted letters, done outside the solution.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: anagram / nagaram", true,
                () -> LC242ValidAnagram.isAnagram("anagram", "nagaram"));
        check("example 2: rat / car", false, () -> LC242ValidAnagram.isAnagram("rat", "car"));
        // edge: different lengths can never be anagrams
        check("different lengths: ab / a", false, () -> LC242ValidAnagram.isAnagram("ab", "a"));
        // edge: a string is an anagram of itself
        check("single letter, equal: a / a", true, () -> LC242ValidAnagram.isAnagram("a", "a"));
        // edge: same length and same set of letters, but different counts
        check("same letters, different counts: aacc / ccac", false,
                () -> LC242ValidAnagram.isAnagram("aacc", "ccac"));
        // edge: all 26 letters, reversed
        check("all 26 letters reversed", true, () -> LC242ValidAnagram.isAnagram(
                "abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba"));
        // edge: the length limit 5 * 10^4
        check("5 * 10^4 letters, a rearrangement", true,
                () -> LC242ValidAnagram.isAnagram("ab".repeat(25000), "ba".repeat(25000)));
        check("5 * 10^4 letters, one letter differs", false,
                () -> LC242ValidAnagram.isAnagram("a".repeat(50000), "a".repeat(49999) + "b"));
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

package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 383. Ransom Note   [Easy]
 * https://leetcode.com/problems/ransom-note/
 * Topics: Hash Table, String, Counting
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #2
 *
 * PROBLEM
 *   You have a ransom note and a magazine, both strings. Letters are cut out of the magazine
 *   to spell the note, and each letter of the magazine can be used only once. Return true if
 *   the whole note can be spelled that way, otherwise false.
 *
 * EXAMPLES
 *   ransomNote = "a",  magazine = "b"    ->  false   the magazine has no 'a'
 *   ransomNote = "aa", magazine = "ab"   ->  false   two a's are needed, the magazine has one
 *   ransomNote = "aa", magazine = "aab"  ->  true    two a's are there (the b is spare)
 *
 * CONSTRAINTS
 *   1 <= ransomNote.length, magazine.length <= 10^5
 *   ransomNote and magazine consist of lowercase English letters
 *
 * INTUITION
 *   Only how often each letter occurs matters, not where it sits - a multiset question. So
 *   tally the magazine's letters in 26 counters, then spend one unit for every letter of the
 *   note. If a letter of the note meets a counter that is already 0, the magazine ran out of
 *   that letter and the answer is false. (The code below tallies the wrong string - see STATUS.)
 *
 * APPROACH (as implemented below)
 *   1. count = new int[26]: one counter per letter, the slot of a letter is (letter - 'a').
 *   2. charm = ransomNote.toCharArray(); count[c - 'a']++ for every c.
 *      BUG: this should tally the letters of magazine, but it tallies the note itself.
 *   3. chars = ransomNote.toCharArray(); for every letter of the note: counter is 0 ->
 *      return false, otherwise count[..]--.
 *   4. Every letter found a counter -> return true.
 *
 * DRY RUN   ransomNote = "a", magazine = "b"   (what main() runs)
 *   step 2: count['a'] = 1       (counted from the NOTE; the magazine's 'b' is never looked at)
 *   step 3: i=0 'a': count['a'] is 1, not 0 -> count['a'] = 0
 *   step 4: return true          <- WRONG: the magazine has no 'a', the correct answer is false
 *   (Tallying magazine instead gives count['b'] = 1 and count['a'] = 0, so step 3 meets a 0
 *   on the very first letter and returns false.)
 *
 * COMPLEXITY
 *   Time : O(r) as written, r = ransomNote.length(), because magazine is never read; the
 *          intended version is O(r + m), m = magazine.length() (tally pass + spending pass)
 *   Space: O(1) for the 26 counters; toCharArray() copies the note twice, O(r)
 *
 * GOTCHAS
 *   - The counter is tested for 0 BEFORE it is decremented, so a counter never goes negative
 *     and the run fails exactly at the first letter the supply cannot cover.
 *   - Duplicates matter: "aa" needs two a's, so a magazine with a single 'a' is not enough.
 *   - Spare letters in the magazine are fine; only the note has to be covered completely.
 *   - Only 'a'..'z' work. Any other character gives a slot outside 0..25 and throws
 *     ArrayIndexOutOfBoundsException.
 *
 * STATUS
 *   Known issue: the tally loop reads ransomNote instead of magazine, so the second loop
 *   spends the note's own letters against themselves and can never meet a 0. canConstruct
 *   returns true for EVERY input and magazine is never used: Example 1 ("a", "b") and
 *   Example 2 ("aa", "ab") return true instead of false; only Example 3 comes out right.
 *   The fix is to build charm from magazine.toCharArray(). The code is left unchanged on
 *   purpose. main() also does not print the result (it would be true; correct: false).
 *
 * RELATED
 *   - LC 1160 Find Words That Can Be Formed by Characters - the same "enough letters?" test,
 *     repeated for a list of words against one set of letters.
 *   - LC 691 Stickers to Spell Word - spelling a word from letters supplied by stickers.
 *   - LC 242 Valid Anagram - the same letter counting with the two strings of equal length;
 *     solved in LC242ValidAnagram.java in this folder.
 * </pre>
 */
public class LC383RansomNote {
    public static void main(String[] args) {
        // Example 1 - the magazine has no 'a'. The result is not printed; canConstruct returns
        // true here, but the correct answer is false (see STATUS)
        String ransomNote = "a", magazine = "b";
        canConstruct(ransomNote, magazine);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Intended: can the note be spelled with the magazine's letters, each used once?
    // KNOWN ISSUE: magazine is never read - see STATUS
    public static boolean canConstruct(String ransomNote, String magazine) {
        // one counter per letter 'a'..'z'
        int[] count = new int[26];
        // tally loop: it reads ransomNote, but it should read magazine
        char[] charm = ransomNote.toCharArray();
        for (char c : charm) {
            count[c - 'a']++;
        }
        // spend one unit per letter of the note; a counter already at 0 means the supply ran out
        char[] chars = ransomNote.toCharArray();
        for(int i = 0; i < ransomNote.length(); i++){
            if(count[chars[i] - 'a'] == 0){
                return false;
            }
            count[chars[i] - 'a']--;
        }
        return true;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 383. Ransom Note)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (spare letters in the magazine, a duplicate
    // letter the magazine has only once, a letter the magazine lacks). Expected values come from
    // the statement or from a letter-count containment check done outside the solution.
    //   Expected result: 3/7 passed
    //   The 4 failures are the known issue described in the STATUS of LC383RansomNote.java:
    //   canConstruct never reads the magazine, so it returns true for every input.
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: a / b", false, () -> LC383RansomNote.canConstruct("a", "b"));
        check("example 2: aa / ab", false, () -> LC383RansomNote.canConstruct("aa", "ab"));
        check("example 3: aa / aab", true, () -> LC383RansomNote.canConstruct("aa", "aab"));
        // edge: the smallest possible input, a match
        check("single letter, available: a / a", true,
                () -> LC383RansomNote.canConstruct("a", "a"));
        // edge: the magazine has spare letters
        check("spare letters in the magazine: abc / cbaxyz", true,
                () -> LC383RansomNote.canConstruct("abc", "cbaxyz"));
        // edge: a letter that is needed twice but is in the magazine once
        check("duplicate letter, only one in the magazine: aab / ab", false,
                () -> LC383RansomNote.canConstruct("aab", "ab"));
        // edge: a letter that the magazine does not have at all
        check("letter missing from the magazine: z / a..y", false,
                () -> LC383RansomNote.canConstruct("z", "abcdefghijklmnopqrstuvwxy"));
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

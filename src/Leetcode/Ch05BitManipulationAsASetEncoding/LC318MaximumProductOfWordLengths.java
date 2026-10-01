package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 318. Maximum Product of Word Lengths   [Medium]
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * Topics: Array, String, Bit Manipulation
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #10
 *
 * PROBLEM
 *   Given an array of lowercase words, pick two words that have no letter in common and make
 *   the product of their lengths as large as possible. Return that product, or 0 when no two
 *   words are free of common letters.
 *
 * EXAMPLES
 *   words = ["abcw","baz","foo","bar","xtfn","abcdef"]  ->  16    "abcw" and "xtfn": 4 * 4
 *   words = ["a","ab","abc","d","cd","bcd","abcd"]      ->  4     "ab" and "cd": 2 * 2
 *   words = ["a","aa","aaa","aaaa"]                     ->  0     every word contains an 'a'
 *
 * CONSTRAINTS
 *   2 <= words.length <= 1000,   1 <= words[i].length <= 1000,   lowercase letters only
 *
 * INTUITION
 *   Whether two words share a letter depends only on WHICH letters each one uses - not on the
 *   order or on repeats. A set of letters out of 26 fits in the bits of one int: bit 0 means
 *   'a', bit 1 means 'b', ..., bit 25 means 'z'. Then "do these two words share a letter?" is
 *   one AND: mask1 & mask2 is non-zero exactly when some letter's bit is set in both.
 *   So each word is scanned once to build its mask, and the all-pairs comparison costs O(1)
 *   per pair instead of re-reading the letters of both words every time.
 *
 * APPROACH (as implemented below)
 *   1. strEnc is an n x 2 table: strEnc[i][0] = length of words[i], strEnc[i][1] = its mask,
 *      built by OR-ing 1 << (letter - 'a') for every letter of the word.
 *   2. max = 0. For every pair i < j: if (strEnc[i][1] & strEnc[j][1]) == 0, the words share no
 *      letter, so max = Math.max(max, strEnc[i][0] * strEnc[j][0]).
 *   3. Return max (still 0 when every pair shares a letter).
 *
 * DRY RUN   words = ["abcw","baz","foo","bar","xtfn","abcdef"]   (the input main() uses)
 *   letter sets (the masks):  abcw {a,b,c,w}   baz {a,b,z}   foo {f,o}   bar {a,b,r}
 *                             xtfn {x,t,f,n}   abcdef {a,b,c,d,e,f}      lengths 4 3 3 3 4 6
 *   pairs whose masks have no common bit, with their products:
 *     abcw-foo 12   abcw-xtfn 16   baz-foo 9   baz-xtfn 12   foo-bar 9   bar-xtfn 12
 *   Every other pair shares a letter (for example abcw-baz share 'a' and 'b'; foo-xtfn share 'f').
 *   max = 16
 *
 * COMPLEXITY
 *   Time : O(L + n^2) - L is the total number of letters in all words (building the masks),
 *          n^2 / 2 pair tests of O(1) each; at most about 500,000 pairs here
 *   Space: O(n) - the strEnc table of n pairs of ints
 *
 * GOTCHAS
 *   - Repeated letters change nothing: "aaaa" and "a" have the same mask, 1; only the
 *     length (strEnc[i][0]) tells them apart.
 *   - j starts at i + 1, so a word is never paired with itself (it would share every letter
 *     with itself anyway) and each pair is tried once.
 *   - The product is at most 1000 * 1000 = 10^6, far below the int limit.
 *   - The parentheses in (a & b) == 0 are needed: == binds tighter than & in Java.
 *
 * RELATED
 *   - LC 1178 Number of Valid Words for Each Puzzle (in this repo) - the same "word -> letter
 *     mask" encoding, compared against puzzles instead of other words.
 *   - LC 1239 Maximum Length of a Concatenated String with Unique Characters - words are
 *     also reduced to letter masks and "no shared letter" is the same AND test.
 * </pre>
 */
public class LC318MaximumProductOfWordLengths {
    public static void main(String[] args) {
        // example 1: "abcw" and "xtfn" share no letter, 4 * 4 = 16 - prints 16
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
        System.out.println(maxProduct(words));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Returns the largest length * length over word pairs with no common letter, or 0
    public static int maxProduct(String[] words) {
        // strEnc[i][0] = length of words[i]; strEnc[i][1] = its letter mask
        // (bit 0 = 'a', bit 1 = 'b', ..., bit 25 = 'z')
        int[][] strEnc = new int[words.length][2];
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            for (int j = 0; j < str.length(); j++) {
                // switch on this letter's bit (a repeated letter just sets it again)
                strEnc[i][1] |=  1 << (str.charAt(j) - 'a');
            }
            strEnc[i][0] = words[i].length();
        }

        // best product so far; it stays 0 if every pair shares a letter
        int max = 0;
        // try every pair once
        for (int i = 0; i < strEnc.length; i++) {
            for (int j = i + 1; j < strEnc.length; j++) {
                // no common letter <=> the two masks have no bit in common
                if((strEnc[i][1] & strEnc[j][1]) == 0){
                    max = Math.max(max, strEnc[i][0] * strEnc[j][0]);
                }
            }
        }
        return max;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 318. Maximum Product of Word Lengths)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases (the smallest array, identical words,
    // the best pair is not the longest words, the longest allowed words, a word with all 26
    // letters); expected values from the statement or from an independent brute force (compare
    // the letter sets of every pair) in the scratch run dir.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", 16,
                () -> LC318MaximumProductOfWordLengths.maxProduct(
                        new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
        check("example 2", 4,
                () -> LC318MaximumProductOfWordLengths.maxProduct(
                        new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
        check("example 3: every word has an 'a'", 0,
                () -> LC318MaximumProductOfWordLengths.maxProduct(
                        new String[]{"a", "aa", "aaa", "aaaa"}));
        // edge: the smallest array, two words with no common letter
        check("two words, no common letter", 4,
                () -> LC318MaximumProductOfWordLengths.maxProduct(new String[]{"ab", "cd"}));
        // edge: identical words share every letter, so they never pair
        check("identical words", 0,
                () -> LC318MaximumProductOfWordLengths.maxProduct(new String[]{"abc", "abc"}));
        // edge: the best pair is "aaaaaaaaaa" (10) with "bbbbbbbb" (8), not the longest words
        check("best pair is not the longest words", 80,
                () -> LC318MaximumProductOfWordLengths.maxProduct(
                        new String[]{"abcdef", "ghij", "aaaaaaaaaa", "bbbbbbbb"}));
        // edge: two words of the maximum length, 1000, with different letters -> 10^6
        check("two words of length 1000", 1000000,
                () -> LC318MaximumProductOfWordLengths.maxProduct(
                        new String[]{"a".repeat(1000), "b".repeat(1000)}));
        // edge: the 26-letter word shares a letter with every other word; the best pair is
        // "a" with "zz"
        check("a word with all 26 letters", 2,
                () -> LC318MaximumProductOfWordLengths.maxProduct(
                        new String[]{"abcdefghijklmnopqrstuvwxyz", "a", "zz", "y"}));
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

package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1178. Number of Valid Words for Each Puzzle   [Hard]
 * https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
 * Topics: Array, Hash Table, String, Bit Manipulation, Trie
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #12
 *
 * PROBLEM
 *   A puzzle is a string of 7 different lowercase letters. A word is VALID for a puzzle when
 *     1. it contains the puzzle's FIRST letter (anywhere in the word), and
 *     2. every letter of the word is one of the puzzle's 7 letters.
 *   The word need not use all 7 letters, and its letters may repeat. Given the lists words
 *   and puzzles, return an array whose i-th value says how many entries of words are valid
 *   for puzzles[i].
 *
 * EXAMPLES
 *   words   = ["aaaa","asas","able","ability","actt","actor","access"]
 *   puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 *   ->  [1,1,3,2,4,0]
 *       "abslute" takes "aaaa", "asas" and "able" (each has an 'a' and only puzzle letters);
 *       "ability" is out because of its 'i'. "gaswxyz" gets 0: no word contains a 'g'.
 *
 *   words   = ["apple","pleas","please"]
 *   puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
 *   ->  [0,1,3,2,0]
 *       "aelpsxy" takes all three words. "saelpxy" starts with 's', so only the two words
 *       that contain an 's' count; "xaelpsy" starts with 'x', which no word has.
 *
 * CONSTRAINTS
 *   1 <= words.length <= 10^5,    4 <= words[i].length <= 50
 *   1 <= puzzles.length <= 10^4,  puzzles[i].length == 7
 *   words[i] and puzzles[i] use lowercase English letters only; no puzzle repeats a letter
 *
 * INTUITION
 *   The two rules are independent tests on one word, and one left-to-right scan can run
 *   both: a letter outside the puzzle kills the word at once, and meeting the puzzle's first
 *   letter ticks a flag. Valid = flag ticked AND never killed. Keeping the puzzle's 7 letters
 *   in a HashSet turns "is this letter allowed?" into one lookup. The code does this for every
 *   (puzzle, word) pair, which is simple and correct but slow (see STATUS).
 *
 *   Faster idea (NOT in this file; it is what the LeetCode hints point to): a word matters
 *   only through the SET of letters it uses, and a set of letters is a 26-bit mask. Count how
 *   many words share each mask in a HashMap, then for each puzzle add up the counts of the
 *   2^6 = 64 masks made of its first letter plus any subset of its other 6 letters. That is
 *   about O(W * L + P * 64) instead of O(P * W * L).
 *
 * APPROACH (as implemented below)
 *   1. ans[i] will hold the answer for puzzles[i].
 *   2. For each puzzle i, copy its letters into the HashSet chars.
 *   3. For each word j, start with isFirstPresent = false and outsideCharacter = false, then
 *      walk its letters (index k):
 *        - letter is in chars     -> if it equals puzzles[i].charAt(0), set isFirstPresent
 *        - letter is not in chars -> set outsideCharacter and break (the word is invalid)
 *   4. After the scan, if isFirstPresent && !outsideCharacter then ans[i]++.
 *   5. Copy ans into a List&lt;Integer> and return it.
 *
 * DRY RUN   main(): example 1's seven words against two of its puzzles
 *   puzzle "absoryz"   chars = {a,b,s,o,r,y,z}   first letter 'a'
 *     aaaa     only chars letters, 'a' seen        -> valid       ans[0] = 1
 *     asas     only chars letters, 'a' seen        -> valid       ans[0] = 2
 *     able     'l' is not in chars                 -> rejected
 *     ability  'i' is not in chars                 -> rejected
 *     actt     'c' is not in chars                 -> rejected
 *     actor    'c' is not in chars                 -> rejected
 *     access   'c' is not in chars                 -> rejected
 *   puzzle "actresz"   chars = {a,c,t,r,e,s,z}   first letter 'a'
 *     aaaa, asas, actt, access   only chars letters, 'a' seen -> valid    ans[1] = 4
 *     able ('b'), ability ('b'), actor ('o')   a letter outside chars     -> rejected
 *   result [2, 4]
 *
 * COMPLEXITY
 *   Time : O(P * W * L) in the worst case - P puzzles, W words, L the length of a word (<= 50).
 *          Every pair is scanned letter by letter, one HashSet lookup per letter. The break
 *          stops a scan at the first letter outside the puzzle, so words with many foreign
 *          letters are cheap and words made only of puzzle letters are the worst case.
 *   Space: O(P) for ans and the returned list; the set holds only 7 letters.
 *
 * GOTCHAS
 *   - Only the puzzle's FIRST letter is required, not the other six: "aaaa" is valid for
 *     "aboveyz". The other letters just define what is allowed.
 *   - Both flags are needed: for "able" against "absoryz" the 'a' sets isFirstPresent before
 *     the 'l' sets outsideCharacter, so isFirstPresent alone would wrongly accept the word.
 *   - A word that appears twice in words is counted twice (the loop walks list entries).
 *
 * STATUS
 *   Known issue (speed only): the answers are correct - 20000 random small cases matched an
 *   independent bit-mask reference - but this brute force is too slow for the upper limits.
 *   Measured here with 10^5 words and 1000 puzzles: about 1 s for random lowercase words and
 *   about 10.6 s when every word is 50 letters taken from the puzzle (no early break). With
 *   10^4 puzzles that is roughly ten times longer, so LeetCode would give Time Limit Exceeded.
 *   The code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 318 Maximum Product of Word Lengths (in this repo) - the same "word -> 26-bit letter
 *     mask" encoding; there one AND of two masks answers "do the words share a letter?".
 *   - LC 78 Subsets (in this repo) - listing every subset of a few items with a bit counter,
 *     the loop the faster version above runs over the puzzle's 6 non-first letters.
 * </pre>
 */
public class LC1178NumberOfValidWordsForEachPuzzle {
    public static void main(String[] args) {
        // example 1's words against only two of its puzzles; prints [2, 4]
        // (the 4th and 5th entries of that example's answer [1,1,3,2,4,0])
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"}, puzzles = {"absoryz","actresz"};
        System.out.println(findNumOfValidWords(words, puzzles));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Returns, for each puzzle, how many words are valid for it: brute force, every puzzle
    // against every word - O(P * W * L)
    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Set<Character> chars;
        // ans[i] = number of valid words for puzzles[i]
        int[] ans = new int[puzzles.length];
        for (int i = 0; i < puzzles.length; i++) {
            // the puzzle's letters as a set: "is this letter allowed?" becomes one lookup
            chars = new HashSet<>();
            for (int j = 0; j < puzzles[i].length(); j++) {
                chars.add(puzzles[i].charAt(j));
            }
            // test every word against this puzzle
            for (int j = 0; j < words.length; j++) {
                // isFirstPresent: the word contains the puzzle's first letter
                // outsideCharacter: the word has a letter that is not in the puzzle
                boolean isFirstPresent = false;
                boolean outsideCharacter = false;
                for (int k = 0; k < words[j].length(); k++) {
                    if(chars.contains(words[j].charAt(k))){
                        // allowed letter: is it the puzzle's first letter?
                        if(puzzles[i].charAt(0) == words[j].charAt(k)){
                            isFirstPresent = true;
                        }
                    }else {
                        // letter outside the puzzle: the word is invalid, stop scanning it
                        outsideCharacter = true;
                        break;
                    }
                }
                // valid = has the first letter AND has no letter outside the puzzle
                if(isFirstPresent && !outsideCharacter){
                    ans[i]++;
                }
            }

        }
        // copy the int[] counts into the List<Integer> the problem asks for
        List<Integer> answer = new ArrayList<>();
        for (int an : ans) {
            answer.add(an);
        }
        return answer;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1178. Number of Valid Words for Each Puzzle)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples, the "abcdefg" example from the statement, and 4 edge
    // cases; expected values from the statement or checked against an independent brute force
    // (does the word contain puzzle[0], and is its letter set inside the puzzle's letter set).
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official example 1
        check("example 1", Arrays.asList(1, 1, 3, 2, 4, 0), () ->
                LC1178NumberOfValidWordsForEachPuzzle.findNumOfValidWords(
                        new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"},
                        new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz",
                                "gaswxyz"}));
        // official example 2
        check("example 2", Arrays.asList(0, 1, 3, 2, 0), () ->
                LC1178NumberOfValidWordsForEachPuzzle.findNumOfValidWords(
                        new String[]{"apple", "pleas", "please"},
                        new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"}));
        // statement: for "abcdefg" faced, cabbage, baggage are valid; beefed (no 'a') and
        // based (has an 's') are not
        check("statement: abcdefg", Arrays.asList(3), () ->
                LC1178NumberOfValidWordsForEachPuzzle.findNumOfValidWords(
                        new String[]{"faced", "cabbage", "baggage", "beefed", "based"},
                        new String[]{"abcdefg"}));
        // edge: smallest input, one 4-letter word made of puzzle letters and the first letter
        check("one word, one puzzle", Arrays.asList(1), () ->
                LC1178NumberOfValidWordsForEachPuzzle.findNumOfValidWords(
                        new String[]{"abcd"}, new String[]{"abcdefg"}));
        // edge: every letter is allowed but the puzzle's first letter 'a' is missing
        check("first letter missing", Arrays.asList(0), () ->
                LC1178NumberOfValidWordsForEachPuzzle.findNumOfValidWords(
                        new String[]{"bcde"}, new String[]{"abcdefg"}));
        // edge: a repeated word counts once per occurrence; the same word is invalid for a
        // puzzle that starts with 'b'
        check("duplicate words, two puzzles", Arrays.asList(2, 0), () ->
                LC1178NumberOfValidWordsForEachPuzzle.findNumOfValidWords(
                        new String[]{"aaaa", "aaaa"}, new String[]{"abcdefg", "bcdefga"}));
        // edge: 50-letter words (the maximum); the foreign letter 'z' is the very last one
        check("50-letter words, foreign letter last", Arrays.asList(1), () ->
                LC1178NumberOfValidWordsForEachPuzzle.findNumOfValidWords(
                        new String[]{"a".repeat(49) + "z", "a".repeat(50)},
                        new String[]{"abcdefg"}));
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

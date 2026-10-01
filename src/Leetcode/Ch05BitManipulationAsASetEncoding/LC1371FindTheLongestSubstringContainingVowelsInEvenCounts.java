package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1371. Find the Longest Substring Containing Vowels in Even Counts   [Medium]
 * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 * Topics: Hash Table, String, Bit Manipulation, Prefix Sum
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #11
 *
 * PROBLEM
 *   Given a lowercase string s, find the longest substring (a contiguous piece) in which
 *   each of the five vowels a, e, i, o, u appears an even number of times; zero counts as
 *   even. Consonants are not restricted at all. Return the LENGTH of that substring.
 *
 * EXAMPLES
 *   s = "eleetminicoworoep"  ->  13    "leetminicowor": e, i, o twice each, no a or u
 *   s = "leetcodeisgreat"    ->  5     "leetc": two e's
 *   s = "bcbcbc"             ->  6     no vowels at all, so the whole string qualifies
 *
 * CONSTRAINTS
 *   1 <= s.length <= 5 * 10^5,   s contains only lowercase English letters
 *
 * INTUITION
 *   Only the PARITY (odd or even) of each vowel's count matters, and 5 vowels need 5 bits:
 *   a set bit means "this vowel has appeared an odd number of times so far". Call that
 *   5-bit number the parity mask of a prefix. Reading a vowel flips its bit (XOR), reading a
 *   consonant changes nothing.
 *   The substring s[p+1..i] has every vowel even exactly when the masks of the prefixes
 *   ending at p and at i are EQUAL: the vowels in between flipped each bit an even number of
 *   times, so the bits came back to where they were. The task therefore becomes "find two
 *   equal prefix masks that are as far apart as possible". Remember only the FIRST index at
 *   which each of the 32 possible masks appeared; for every later repeat, that first index
 *   gives the longest substring.
 *
 * APPROACHES IN THIS FILE
 *   A. findTheLongestSubstring()    - brute force: every start i, extend the end j, keep 5
 *                                     vowel counters and test that all are even - O(n^2);
 *                                     it has an off-by-one bug (see STATUS)
 *   B. findTheLongestSubstringOpt() - prefix parity mask + table of first indices - O(n)
 *                                     <- main() runs this one
 *   (APPROACH and DRY RUN below describe B.)
 *
 * APPROACH (as implemented below, method B)
 *   1. BIT[] (filled in the static block) gives each vowel its own bit: a=1, e=2, i=4, o=8,
 *      u=16; consonants stay 0.
 *   2. map has 32 slots, one per possible mask, holding the first index where that mask was
 *      seen; -2 means "not seen yet". map[0] = -1: the empty prefix before index 0 has mask 0.
 *   3. Walk i over s. If s[i] is a vowel, mask ^= BIT[s[i] - 'a'] (flip that vowel's parity).
 *   4. If map[mask] has been set, the piece after that index has all vowels even:
 *      res = max(res, i - map[mask]). Otherwise store map[mask] = i (first sighting only).
 *   5. Return res.
 *
 * DRY RUN   s = "leet" (the input main() uses)       start: mask = 0, map[0] = -1, res = 0
 *   i=0 'l'  consonant, mask stays 0;  map[0] is set -> res = max(0, 0 - (-1)) = 1
 *   i=1 'e'  flips the e bit, mask = 2 (binary 00010);  map[2] unseen -> map[2] = 1
 *   i=2 'e'  flips it back, mask = 0;  map[0] is set -> res = max(1, 2 - (-1)) = 3
 *   i=3 't'  consonant, mask stays 0;  res = max(3, 3 - (-1)) = 4
 *   return 4     ("leet" contains two e's, so the whole string is valid)
 *
 * COMPLEXITY
 *   B: Time O(n) - one pass with O(1) work per character; map is a plain 32-int array, so
 *      there is no hashing
 *      Space O(1) - the 32-int table and the 26-int BIT table do not grow with n
 *   A: Time O(n^2) - every start i is extended to the end of the string (measured 0.56 s for
 *      n = 20000; the limit n = 5 * 10^5 is 625 times more work)
 *      Space O(1) - a fresh int[5] for each start
 *
 * GOTCHAS
 *   - map[0] = -1 is what lets a valid PREFIX (one that starts at index 0) be found: its
 *     length is i - (-1) = i + 1. Without it "bcbcbc" would come out as 5.
 *   - Store the index only on the FIRST sighting of a mask; overwriting it with later
 *     indices would make every substring shorter.
 *   - -2 means "unseen" because both -1 (the empty prefix) and 0 (first character) are real
 *     index values.
 *   - Zero is even, so a string without any vowel is valid in full ("bcbcbc" -> 6, "b" -> 1),
 *     and the answer is 0 when every substring has some vowel an odd number of times
 *     ("a", "aeiou").
 *   - The if (k > -1) test in B is only a guard: BIT[] is 0 for consonants, so the XOR
 *     would be a no-op anyway.
 *
 * STATUS
 *   Known issue: findTheLongestSubstring() (approach A, not called by main()) is off by one.
 *   It records j - i, but the substring s[i..j] has j - i + 1 characters, so whenever a
 *   valid non-empty substring exists it returns one less than the true length:
 *     "eleetminicoworoep" -> 12 (correct 13),   "leetcodeisgreat" -> 4 (correct 5),
 *     "bcbcbc" -> 5 (correct 6),                "b" -> 0 (correct 1).
 *   When the true answer is 0 (for example "a" or "aeiou") it is right. On 30000 random
 *   strings, compared with a plain counting reference, A returned exactly the true answer
 *   minus 1 whenever that answer was above 0, while findTheLongestSubstringOpt() (B, the
 *   one main() runs) matched the reference every time.
 *   The code is left unchanged on purpose. (HashMap and Map are imported but never used.)
 *
 * RELATED
 *   - LC 1442 Count Triplets That Can Form Two Arrays of Equal XOR (in this repo) - the same
 *     cancellation idea: two equal prefix XOR values mean the part between them XORs to 0.
 *   - LC 1915 Number of Wonderful Substrings - the same prefix parity mask, there over ten
 *     letters, counting substrings with at most one odd letter instead of none.
 * </pre>
 */
public class LC1371FindTheLongestSubstringContainingVowelsInEvenCounts {
    public static void main(String[] args) {
        // "leet" has two e's, so the whole string is valid: prints 4 (approach B)
        // (approach A would return 3 for it - see STATUS)
        String s = "leet";
        System.out.println(findTheLongestSubstringOpt(s));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Approach A: brute force, try every start i and every end j - O(n^2)
    // KNOWN BUG: the result is 1 too small (j - i below should be j - i + 1), see STATUS
    public static int findTheLongestSubstring(String s) {
        int length = s.length();
        // longest valid length found so far
        int max = 0;
        for (int i = 0; i < length; i++) {
            // counts of a, e, i, o, u inside the substring that starts at i
            int[] count = new int[5];
            for (int j = i; j < length; j++) {
                // position of s[j] in "aeiou", or -1 for a consonant
                int k = "aeiou".indexOf(s.charAt(j));
                if(k > -1) {
                    count[k]++;
                }
                // valid when all five counts are even
                if(count[0] % 2 == 0 && count[1] % 2 == 0  && count[2] % 2 == 0  && count[3] % 2 == 0  && count[4] % 2 == 0){
                    // off by one: s[i..j] has j - i + 1 characters ("b" gives 0 instead of 1)
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }



    // mask bit of each vowel: a=1, e=2, i=4, o=8, u=16; every other letter has no bit (0)
    static final int[] BIT = new int[26];
    static {
        BIT[0] = 1;  BIT['e' - 'a'] = 2;  BIT['i' - 'a'] = 4;
        BIT['o' - 'a'] = 8;  BIT['u' - 'a'] = 16;          // consonants stay 0
    }
    // Approach B: parity mask of the prefix + first index of each mask - O(n)
    public static int findTheLongestSubstringOpt(String s) {
        int res = 0;
        // bit set = that vowel has appeared an odd number of times in s[0..i]
        int mask = 0;
        // map[m] = first index at which the prefix mask was m; -2 = not seen yet
        int[] map = new int[32];
        Arrays.fill(map, -2);
        // the empty prefix (before index 0) has mask 0 and sits at "index" -1
        map[0] = -1;
        for (int i = 0; i < s.length(); i++) {
            int k = "aeiou".indexOf(s.charAt(i));
            // vowel: flip its parity bit
            if(k > -1){
                mask ^=  BIT[s.charAt(i) - 'a'];
            }
            // this mask was seen before: everything after that index has every vowel even
            if(map[mask] > -2){
                res = Math.max(res, i - map[mask]);
            }else {
                // first sighting: keep the earliest index (earliest = longest substring later)
                map[mask] = i;
            }
        }
        return res;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1371. Find the Longest Substring Containing Vowels in Even Counts)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + 5 edge cases, each run through BOTH methods
    // (findTheLongestSubstringOpt = the O(n) one, findTheLongestSubstring = the brute force),
    // plus two maximum-size strings for the O(n) method only (the brute force is O(n^2) and
    // would never finish on them). Expected values come from the statement or from an
    // independent brute force (every substring, counting the vowels, length j - i + 1).
    //   Expected result: 12/18 passed
    //   The 6 failures are all findTheLongestSubstring (the brute force): the off-by-one known
    //   issue described in the STATUS of the solution file
    //   LC1371FindTheLongestSubstringContainingVowelsInEvenCounts.java.
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1", 13, "eleetminicoworoep");
        both("example 2", 5, "leetcodeisgreat");
        both("example 3: no vowels", 6, "bcbcbc");
        // edge: a single vowel - no non-empty substring qualifies, the answer is 0
        both("single vowel", 0, "a");
        // edge: a single consonant - zero vowels is even, so the answer is 1
        both("single consonant", 1, "b");
        // edge: every vowel exactly once - any substring has an odd vowel, the answer is 0
        both("each vowel once", 0, "aeiou");
        // edge: the best piece starts after the first character ("baeaeb"; the 'u' is odd)
        both("best piece in the middle", 6, "ubaeaeb");
        // edge: every vowel twice - the whole string is valid
        both("each vowel twice", 10, "aeiouaeiou");
        // edge: the maximum length, 5 * 10^5; an even number of 'a' - the whole string
        check("opt: 500000 x 'a'", 500000, () ->
                LC1371FindTheLongestSubstringContainingVowelsInEvenCounts
                        .findTheLongestSubstringOpt("a".repeat(500000)));
        // edge: an odd number of 'a' - drop one of them
        check("opt: 499999 x 'a'", 499998, () ->
                LC1371FindTheLongestSubstringContainingVowelsInEvenCounts
                        .findTheLongestSubstringOpt("a".repeat(499999)));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through both methods; the expected value is the same for both
    private static void both(String name, int expected, String s) {
        check("opt:   " + name, expected, () ->
                LC1371FindTheLongestSubstringContainingVowelsInEvenCounts
                        .findTheLongestSubstringOpt(s));
        check("brute: " + name, expected, () ->
                LC1371FindTheLongestSubstringContainingVowelsInEvenCounts
                        .findTheLongestSubstring(s));
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
